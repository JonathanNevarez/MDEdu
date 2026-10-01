package com.project.programming;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mde.programming.*;
import com.project.programming.api.ProgramDto;
import com.project.programming.api.ProgramDto.*;
import com.project.programming.application.*;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ProgrammingModelTest {
    final ProgrammingModelMapper mapper = new ProgrammingModelMapper();
    final ProgrammingModelService service = new ProgrammingModelService(mapper);
    final ObjectMapper json = new ObjectMapper();
    ProgramDto fixture(String name) throws Exception {
        return json.readValue(Path.of("../contracts/programming/v1/" + name + ".json").toFile(), ProgramDto.class);
    }
    static ProgramDto program(StatementDto... statements) { return new ProgramDto(1, "Prueba", List.of(statements)); }
    static LiteralDto integer(String value) { return new LiteralDto("INTEGER", value); }
    static DeclarationDto declaration(String id) { return new DeclarationDto(id, "contador", "INTEGER", integer("0")); }

    @ParameterizedTest @ValueSource(strings = {"sequence", "variables", "conditionals", "loops"})
    void sharedFixturesValidateAndSerialize(String name) throws Exception {
        var result = service.build(fixture(name));
        assertTrue(result.valid()); assertTrue(result.diagnostics().isEmpty());
        assertEquals(ProgrammingPackage.eNS_URI, result.summary().namespace());
        assertTrue(result.xmi().contains("UTF-8"));
    }
    @Test void sequenceOrder() throws Exception {
        var p = mapper.map(fixture("sequence"));
        assertEquals(List.of("Move", "Move", "TurnLeft", "Move", "TurnRight"),
            p.getStatements().stream().map(s -> s.eClass().getName()).toList());
    }
    @Test void assignmentIdentity() throws Exception {
        var p = mapper.map(fixture("variables"));
        assertSame(p.getStatements().get(0), ((Assignment)p.getStatements().get(1)).getTarget());
    }
    @Test void referenceAndForwardResolution() {
        var p = mapper.map(program(new AssignmentDto("v", new ReferenceDto("v")), declaration("v")));
        var a = (Assignment)p.getStatements().get(0);
        assertSame(p.getStatements().get(1), a.getTarget());
        assertSame(a.getTarget(), ((VariableReference)a.getValue()).getDeclaration());
    }
    @Test void roundtripPreservesBothReferences() throws Exception {
        var result = service.build(program(declaration("v"), new AssignmentDto("v", new ReferenceDto("v"))));
        assertTrue(result.valid());
        var rs = new ResourceSetImpl();
        rs.getPackageRegistry().put(ProgrammingPackage.eNS_URI, ProgrammingPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("programming", new XMIResourceFactoryImpl());
        var r = rs.createResource(URI.createURI("memory:/reload.programming"));
        r.load(new ByteArrayInputStream(result.xmi().getBytes(StandardCharsets.UTF_8)), Map.of());
        var p = assertInstanceOf(Program.class, r.getContents().get(0));
        assertEquals(ProgrammingPackage.eNS_URI, p.eClass().getEPackage().getNsURI());
        assertEquals(2, p.getStatements().size());
        var a = (Assignment)p.getStatements().get(1);
        assertSame(p.getStatements().get(0), a.getTarget());
        assertSame(a.getTarget(), ((VariableReference)a.getValue()).getDeclaration());
        assertEquals(0, Diagnostician.INSTANCE.validate(p).getSeverity());
        assertFalse(result.xmi().contains("declarationId"));
    }
    @Test void controlsAndExpressions() throws Exception {
        var loops = mapper.map(fixture("loops"));
        assertInstanceOf(Repeat.class, loops.getStatements().get(0));
        assertInstanceOf(While.class, loops.getStatements().get(1));
        assertInstanceOf(IfElse.class, mapper.map(fixture("conditionals")).getStatements().getFirst());
        var condition = new BooleanDto("AND", new ComparisonDto("LESS_THAN", integer("1"), integer("2")),
            new BooleanDto("NOT", new SensorDto("AT_GOAL"), null));
        var p = mapper.map(program(new IfDto(condition, List.of(new MoveDto()))));
        var i = assertInstanceOf(If.class, p.getStatements().getFirst());
        var b = assertInstanceOf(BooleanExpression.class, i.getCondition());
        assertEquals(BooleanOperator.AND, b.getOperator());
        assertInstanceOf(Comparison.class, b.getLeft());
        var not = (BooleanExpression)b.getRight();
        assertNull(not.getRight()); assertInstanceOf(SensorExpression.class, not.getLeft());
        assertEquals(0, Diagnostician.INSTANCE.validate(p).getSeverity());
    }
    @Test void duplicateIdsRejected() {
        var e = assertThrows(ContractException.class, () -> mapper.map(program(declaration("v"), declaration("v"))));
        assertEquals("DUPLICATE_DECLARATION_ID", e.code());
    }
    @Test void missingAssignmentRejected() {
        assertEquals("MISSING_VARIABLE_DECLARATION", assertThrows(ContractException.class,
            () -> mapper.map(program(new AssignmentDto("missing", integer("1"))))).code());
    }
    @Test void missingReferenceRejected() {
        assertThrows(ContractException.class, () -> mapper.map(program(new RepeatDto(new ReferenceDto("missing"), List.of()))));
    }
    @Test void incompleteContractRejected() {
        assertThrows(ContractException.class, () -> mapper.map(new ProgramDto(1, "p", null)));
        assertThrows(ContractException.class, () -> mapper.map(new ProgramDto(2, "p", List.of())));
        assertThrows(ContractException.class, () -> mapper.map(program(new RepeatDto(null, List.of()))));
    }
    @Test void booleanArityRejected() {
        assertThrows(ContractException.class, () -> mapper.map(program(new IfDto(new BooleanDto("AND", new SensorDto("AT_GOAL"), null), List.of()))));
        assertThrows(ContractException.class, () -> mapper.map(program(new IfDto(new BooleanDto("NOT", new SensorDto("AT_GOAL"), new SensorDto("HAS_KEY")), List.of()))));
    }
    @Test void realEmfDiagnosticForMissingRequiredAttribute() {
        var result = service.build(program(new DeclarationDto("v", null, "INTEGER", null)));
        assertFalse(result.valid()); assertFalse(result.diagnostics().isEmpty()); assertNull(result.xmi());
        assertTrue(result.diagnostics().getFirst().code().startsWith("EMF_"));
    }
}
