import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.project.mde.programming.*;

/** Explicit structural validation utility, not a semantic or transformation test suite. */
public class ValidateProgramming {
    static final ProgrammingFactory F = ProgrammingFactory.eINSTANCE;
    static final String NS = "https://mdedu.espoch.edu.ec/model/programming/1.0";
    static void require(boolean ok, String message) {
        if (!ok) throw new IllegalStateException(message);
    }
    static ResourceSet resources() {
        ResourceSet rs = new ResourceSetImpl();
        rs.getPackageRegistry().put(NS, ProgrammingPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("programming", new XMIResourceFactoryImpl());
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        return rs;
    }
    static URI uri(Path p) { return URI.createFileURI(p.toAbsolutePath().toString()); }
    static void validate(EObject object, String label) {
        Diagnostic d = Diagnostician.INSTANCE.validate(object);
        System.out.println(label + ": severity=" + d.getSeverity() + ", diagnostics=" + d.getChildren());
        require(d.getSeverity() == Diagnostic.OK, label + ": " + d);
    }
    static void metamodel(Path plugin) {
        EPackage p = (EPackage) resources().getResource(uri(plugin.resolve("model/programming.ecore")), true).getContents().get(0);
        validate(p, "Ecore Validate");
        require(p.getNsURI().equals(NS) && p.getName().equals("programming") && p.getNsPrefix().equals("programming"), "EPackage identity");
        var classes = p.getEClassifiers().stream().filter(EClass.class::isInstance).map(EClass.class::cast).toList();
        var enums = p.getEClassifiers().stream().filter(EEnum.class::isInstance).map(EEnum.class::cast).toList();
        require(classes.size() == 17 && enums.size() == 4, "Classifier counts");
        require(classes.stream().filter(EClass::isAbstract).map(EClass::getName).collect(Collectors.toSet()).equals(Set.of("Statement", "Expression")), "Abstract classes");
        require(classes.stream().noneMatch(EClass::isInterface), "No interface EClasses");
        Map<String, String> parents = Map.ofEntries(
            Map.entry("Move", "Statement"), Map.entry("TurnLeft", "Statement"), Map.entry("TurnRight", "Statement"),
            Map.entry("VariableDeclaration", "Statement"), Map.entry("Assignment", "Statement"), Map.entry("Repeat", "Statement"),
            Map.entry("While", "Statement"), Map.entry("If", "Statement"), Map.entry("IfElse", "If"),
            Map.entry("Literal", "Expression"), Map.entry("VariableReference", "Expression"), Map.entry("Comparison", "Expression"),
            Map.entry("BooleanExpression", "Expression"), Map.entry("SensorExpression", "Expression"));
        for (EClass c : classes) {
            String parent = parents.get(c.getName());
            require(parent == null ? c.getESuperTypes().isEmpty() : c.getESuperTypes().size() == 1 && c.getESuperTypes().get(0).getName().equals(parent), "Inheritance: " + c.getName());
        }
        Map<String, List<String>> literals = Map.of(
            "ValueType", List.of("INTEGER", "BOOLEAN"),
            "ComparisonOperator", List.of("EQUAL", "NOT_EQUAL", "LESS_THAN", "LESS_OR_EQUAL", "GREATER_THAN", "GREATER_OR_EQUAL"),
            "BooleanOperator", List.of("AND", "OR", "NOT"),
            "SensorKind", List.of("FRONT_CLEAR", "LEFT_CLEAR", "RIGHT_CLEAR", "AT_GOAL", "HAS_KEY", "ON_KEY", "DOOR_AHEAD", "DOOR_OPEN"));
        for (EEnum e : enums) require(e.getELiterals().stream().map(EEnumLiteral::getName).toList().equals(literals.get(e.getName())), "Enum " + e.getName());
        String[] features = {
            "Program.name EString 1 1 attribute", "Program.statements Statement 0 -1 containment",
            "VariableDeclaration.name EString 1 1 attribute", "VariableDeclaration.type ValueType 1 1 attribute",
            "VariableDeclaration.initialValue Expression 0 1 containment", "Assignment.target VariableDeclaration 1 1 reference",
            "Assignment.value Expression 1 1 containment", "Repeat.count Expression 1 1 containment", "Repeat.body Statement 0 -1 containment",
            "While.condition Expression 1 1 containment", "While.body Statement 0 -1 containment", "If.condition Expression 1 1 containment",
            "If.thenBranch Statement 0 -1 containment", "IfElse.elseBranch Statement 0 -1 containment",
            "Literal.type ValueType 1 1 attribute", "Literal.value EString 1 1 attribute",
            "VariableReference.declaration VariableDeclaration 1 1 reference", "Comparison.operator ComparisonOperator 1 1 attribute",
            "Comparison.left Expression 1 1 containment", "Comparison.right Expression 1 1 containment",
            "BooleanExpression.operator BooleanOperator 1 1 attribute", "BooleanExpression.left Expression 1 1 containment",
            "BooleanExpression.right Expression 0 1 containment", "SensorExpression.sensor SensorKind 1 1 attribute"
        };
        require(classes.stream().mapToInt(c -> c.getEStructuralFeatures().size()).sum() == features.length, "No extra features");
        for (String spec : features) {
            String[] a = spec.split(" "); String[] path = a[0].split("\\.");
            EClass c = (EClass) p.getEClassifier(path[0]); EStructuralFeature f = c.getEStructuralFeature(path[1]);
            require(f != null && f.getEContainingClass() == c && f.getEType().getName().equals(a[1]) && f.getLowerBound() == Integer.parseInt(a[2]) && f.getUpperBound() == Integer.parseInt(a[3]), "Feature: " + spec);
            require(f.isOrdered() && f.isUnique(), "Ordered/unique: " + spec);
            require(a[4].equals("attribute") ? f instanceof EAttribute : f instanceof EReference && ((EReference) f).isContainment() == a[4].equals("containment"), "Containment: " + spec);
        }
        System.out.println("Inventory: 17 EClasses, 2 abstract, 4 EEnums, 24 own features; inheritance/containment/cross-references OK");
    }
    static Program program(String name) { Program p = F.createProgram(); p.setName(name); return p; }
    static Literal literal(String value) { Literal l = F.createLiteral(); l.setType(ValueType.INTEGER); l.setValue(value); return l; }
    static SensorExpression sensor(SensorKind kind) { SensorExpression s = F.createSensorExpression(); s.setSensor(kind); return s; }
    static void writeExamples(Path folder) throws Exception {
        Program seq = program("Secuencia b\u00e1sica");
        seq.getStatements().addAll(List.of(F.createMove(), F.createMove(), F.createTurnLeft(), F.createMove(), F.createTurnRight()));
        Program vars = program("Variables b\u00e1sicas");
        VariableDeclaration v = F.createVariableDeclaration(); v.setName("contador"); v.setType(ValueType.INTEGER); v.setInitialValue(literal("0"));
        Assignment a = F.createAssignment(); a.setTarget(v); a.setValue(literal("1")); vars.getStatements().addAll(List.of(v, a));
        Program cond = program("Condicionales b\u00e1sicos");
        IfElse i = F.createIfElse(); i.setCondition(sensor(SensorKind.HAS_KEY)); i.getThenBranch().add(F.createMove()); i.getElseBranch().add(F.createTurnLeft()); cond.getStatements().add(i);
        Program loops = program("Ciclos b\u00e1sicos");
        Repeat r = F.createRepeat(); r.setCount(literal("3")); r.getBody().addAll(List.of(F.createMove(), F.createTurnRight()));
        While w = F.createWhile(); w.setCondition(sensor(SensorKind.FRONT_CLEAR)); w.getBody().add(F.createMove()); loops.getStatements().addAll(List.of(r, w));
        Files.createDirectories(folder);
        for (var entry : Map.of("sequence-basic", seq, "variables-basic", vars, "conditionals-basic", cond, "loops-basic", loops).entrySet()) {
            Path file = folder.resolve(entry.getKey() + ".programming"); require(!Files.exists(file), "Refusing to overwrite " + file);
            Resource res = resources().createResource(uri(file)); res.getContents().add(entry.getValue()); res.save(Map.of()); res.unload();
        }
    }
    static Program inspect(Resource resource, String name) {
        require(resource.getErrors().isEmpty() && resource.getWarnings().isEmpty(), "Resource diagnostics: " + name);
        require(resource.getContents().size() == 1 && resource.getContents().get(0) instanceof Program, "Root Program: " + name);
        Program p = (Program) resource.getContents().get(0);
        require(p.eClass().getEPackage().getNsURI().equals(NS), "Namespace: " + name);
        require(EcoreUtil.UnresolvedProxyCrossReferencer.find(resource).isEmpty(), "Unresolved reference: " + name);
        switch (name) {
            case "sequence-basic" -> require(p.getStatements().stream().map(s -> s.eClass().getName()).toList().equals(List.of("Move", "Move", "TurnLeft", "Move", "TurnRight")), "Sequence order");
            case "variables-basic" -> {
                require(p.getStatements().size() == 2, "Variable statements");
                VariableDeclaration v = (VariableDeclaration) p.getStatements().get(0);
                Assignment a = (Assignment) p.getStatements().get(1);
                require(v.getName().equals("contador") && v.getType() == ValueType.INTEGER && ((Literal)v.getInitialValue()).getValue().equals("0"), "Variable declaration");
                require(a.getTarget() == v && ((Literal)a.getValue()).getValue().equals("1") && v.eContainer() == p, "Assignment.target object identity");
                System.out.println("Assignment.target == original contained VariableDeclaration: true");
            }
            case "conditionals-basic" -> {
                require(p.getStatements().size() == 1, "Conditional statements"); IfElse i = (IfElse) p.getStatements().get(0);
                require(((SensorExpression)i.getCondition()).getSensor() == SensorKind.HAS_KEY && i.getThenBranch().size() == 1 && i.getThenBranch().get(0) instanceof Move && i.getElseBranch().size() == 1 && i.getElseBranch().get(0) instanceof TurnLeft, "Conditional structure");
            }
            case "loops-basic" -> {
                require(p.getStatements().size() == 2, "Loop statements"); Repeat r = (Repeat)p.getStatements().get(0); While w = (While)p.getStatements().get(1);
                require(((Literal)r.getCount()).getValue().equals("3") && r.getBody().stream().map(s -> s.eClass().getName()).toList().equals(List.of("Move", "TurnRight")) && ((SensorExpression)w.getCondition()).getSensor() == SensorKind.FRONT_CLEAR && w.getBody().size() == 1 && w.getBody().get(0) instanceof Move, "Loop structure");
            }
            default -> throw new IllegalArgumentException(name);
        }
        validate(p, name); return p;
    }
    public static void main(String[] args) throws Exception {
        Path plugin = Path.of(args[0]).toAbsolutePath(); metamodel(plugin);
        if (args.length > 1 && args[1].equals("--create-examples")) writeExamples(plugin.resolve("examples"));
        Path output = plugin.resolve("target/roundtrip"); Files.createDirectories(output);
        for (String name : List.of("sequence-basic", "variables-basic", "conditionals-basic", "loops-basic")) {
            ResourceSet rs = resources(); Resource first = rs.getResource(uri(plugin.resolve("examples/" + name + ".programming")), true);
            Program root = inspect(first, name); EObject snapshot = EcoreUtil.copy(root);
            first.setURI(uri(output.resolve(name + ".programming"))); first.save(Map.of()); first.unload(); rs.getResources().clear();
            Resource reopened = resources().getResource(uri(output.resolve(name + ".programming")), true);
            Program reloaded = inspect(reopened, name); require(EcoreUtil.equals(snapshot, reloaded), "Round-trip structure: " + name);
            System.out.println(name + ": load/inspect/save/unload/reopen/validate OK; structure preserved"); reopened.unload();
        }
        System.out.println("STRUCTURAL VALIDATION SUCCESS (not semantic validation)");
    }
}
