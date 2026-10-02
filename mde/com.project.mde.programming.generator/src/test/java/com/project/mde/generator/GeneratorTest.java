package com.project.mde.generator;

import com.project.mde.programming.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {
    private static final ProgrammingFactory F = ProgrammingFactory.eINSTANCE;
    private static final Path OUTPUT = Path.of("target", "generation-tests");

    @ParameterizedTest
    @ValueSource(strings = {"sequence-basic", "variables-basic", "conditionals-basic", "loops-basic"})
    void canonicalSnapshotsAndDeterminism(String name) throws Exception {
        var input = Path.of("../com.project.mde.programming.model/examples", name + ".programming");
        var a = Generate.generate(input, OUTPUT.resolve(name + "-A"));
        var b = Generate.generate(input, OUTPUT.resolve(name + "-B"));
        var bytes = Files.readAllBytes(a);
        assertArrayEquals(bytes, Files.readAllBytes(b));
        assertArrayEquals(Files.readAllBytes(Path.of("expected", name + ".expected.js")), bytes);
        checkSyntax(a); checkSyntax(b);
        System.out.println(name + " SHA256 A=" + sha(a) + " B=" + sha(b));
    }

    @ParameterizedTest @EnumSource(ComparisonOperator.class)
    void allComparisonOperators(ComparisonOperator operator) throws Exception {
        var p = program(); var c = F.createComparison(); c.setOperator(operator);
        c.setLeft(literal("1")); c.setRight(literal("2"));
        var conditional = F.createIf(); conditional.setCondition(c);
        conditional.getThenBranch().add(F.createMove()); p.getStatements().add(conditional);
        var text = generate(p, "comparison-" + operator);
        assertTrue(text.contains("runtime.ifThen(() => runtime.compare(\"" + operator + "\", runtime.literal(\"INTEGER\", \"1\"), runtime.literal(\"INTEGER\", \"2\"))"));
    }

    @ParameterizedTest @EnumSource(BooleanOperator.class)
    void allBooleanOperatorsAndArity(BooleanOperator operator) throws Exception {
        var p = program(); var booleanExpression = F.createBooleanExpression();
        booleanExpression.setOperator(operator); booleanExpression.setLeft(sensor(SensorKind.AT_GOAL));
        if (operator != BooleanOperator.NOT) booleanExpression.setRight(sensor(SensorKind.HAS_KEY));
        var conditional = F.createIf(); conditional.setCondition(booleanExpression); p.getStatements().add(conditional);
        var text = generate(p, "boolean-" + operator);
        var right = operator == BooleanOperator.NOT ? "" : ", runtime.sensor(\"HAS_KEY\")";
        assertTrue(text.contains("runtime.boolean(\"" + operator + "\", runtime.sensor(\"AT_GOAL\")" + right + ")"));
    }

    @ParameterizedTest @EnumSource(SensorKind.class)
    void allSensors(SensorKind sensorKind) throws Exception {
        var p = program(); var conditional = F.createIf(); conditional.setCondition(sensor(sensorKind));
        p.getStatements().add(conditional);
        assertTrue(generate(p, "sensor-" + sensorKind).contains("runtime.sensor(\"" + sensorKind + "\")"));
    }

    @Test void referencesUseIdentityEvenForEqualNamesAndForwardReferences() throws Exception {
        var p = program(); var first = declaration("same"); var second = declaration("same");
        var reference = F.createVariableReference(); reference.setDeclaration(first);
        var assignment = F.createAssignment(); assignment.setTarget(second); assignment.setValue(reference);
        p.getStatements().add(assignment); p.getStatements().add(first);
        var loop = F.createRepeat(); loop.setCount(literal("1")); loop.getBody().add(second); p.getStatements().add(loop);
        var text = generate(p, "references");
        assertTrue(text.contains("runtime.assignVariable(\"v2\", runtime.getVariable(\"v1\"));"));
        assertTrue(text.contains("runtime.declareVariable(\"v1\", \"same\", \"INTEGER\", null);"));
        assertTrue(text.contains("runtime.declareVariable(\"v2\", \"same\", \"INTEGER\", null);"));
        assertSame(second, assignment.getTarget()); assertSame(first, reference.getDeclaration());
    }

    @Test void stringsAreEscapedDataAndNeverExecuted() throws Exception {
        var payload = "\"; throw new Error(\"NO\"); //\\\r\n\tá漢字";
        var p = program(); p.setName(payload);
        var d = declaration(payload); d.setInitialValue(literal(payload)); p.getStatements().add(d);
        var text = generate(p, "escaping");
        var escaped = "\\\"; throw new Error(\\\"NO\\\"); //\\\\\\r\\n\\tá漢字";
        assertTrue(text.contains("\"" + escaped + "\""));
        assertFalse(text.contains(payload));
        assertEquals(2, text.split(java.util.regex.Pattern.quote(escaped), -1).length - 1);
        assertTrue(text.contains("function runProgram(runtime)"));
        assertFalse(text.contains("\r"));
    }

    @Test void booleanLiteralAndEmptyProgram() throws Exception {
        var p = program(); assertTrue(generate(p, "empty").endsWith("{\n}\n"));
        var d = declaration("flag"); d.setType(ValueType.BOOLEAN);
        var l = literal("true"); l.setType(ValueType.BOOLEAN); d.setInitialValue(l); p.getStatements().add(d);
        assertTrue(generate(p, "boolean-literal").contains("runtime.literal(\"BOOLEAN\", \"true\")"));
    }

    @Test void invalidEmfInputFailsBeforeGeneration() throws Exception {
        var p = program(); p.getStatements().add(F.createAssignment());
        var input = save(p, "invalid");
        assertThrows(IllegalArgumentException.class, () -> Generate.generate(input, OUTPUT.resolve("invalid-output")));
        assertFalse(Files.exists(OUTPUT.resolve("invalid-output/program.js")));
    }

    private String generate(Program program, String name) throws Exception {
        var output = Generate.generate(save(program, name), OUTPUT.resolve(name));
        checkSyntax(output);
        return Files.readString(output, StandardCharsets.UTF_8);
    }
    private Path save(Program program, String name) throws Exception {
        var input = OUTPUT.resolve("inputs").resolve(name + ".programming"); Files.createDirectories(input.getParent());
        var rs = new ResourceSetImpl();
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("programming", new XMIResourceFactoryImpl());
        var resource = rs.createResource(URI.createFileURI(input.toAbsolutePath().toString()));
        resource.getContents().add(program); resource.save(Map.of(XMLResource.OPTION_ENCODING, "UTF-8"));
        return input;
    }
    private static Program program() { var p = F.createProgram(); p.setName("Test"); return p; }
    private static VariableDeclaration declaration(String name) { var d = F.createVariableDeclaration(); d.setName(name); return d; }
    private static Literal literal(String value) { var l = F.createLiteral(); l.setValue(value); return l; }
    private static SensorExpression sensor(SensorKind kind) { var s = F.createSensorExpression(); s.setSensor(kind); return s; }
    private static String sha(Path path) throws Exception {
        return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(path)));
    }
    private static void checkSyntax(Path file) throws Exception {
        var bytes = Files.readAllBytes(file);
        assertFalse(new String(bytes, StandardCharsets.UTF_8).contains("\r"), "Output must use LF");
        var process = new ProcessBuilder("node", "--check", file.toAbsolutePath().toString()).redirectErrorStream(true).start();
        assertTrue(process.waitFor(20, TimeUnit.SECONDS), "node --check timed out");
        assertEquals(0, process.exitValue(), new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
    }
}
