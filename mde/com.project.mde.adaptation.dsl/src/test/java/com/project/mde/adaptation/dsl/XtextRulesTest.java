package com.project.mde.adaptation.dsl;

import com.project.mde.adaptation.*;
import com.project.mde.adaptation.validation.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.util.*;
import org.eclipse.emf.common.util.URI;
import static org.junit.jupiter.api.Assertions.*;

class XtextRulesTest {
    static final RuleCatalog CATALOG=new RuleCatalog(Set.of("LOOPS"),Set.of("REPETITIVE_SEQUENCE_WITHOUT_LOOP"));
    static HeadlessRules parser;
    @BeforeAll static void setup() { parser=new HeadlessRules(CATALOG); }
    static java.util.stream.Stream<Path> invalid() throws IOException { return Files.list(Path.of("src/test/resources/invalid")).sorted(); }
    @ParameterizedTest @MethodSource("invalid") void rejectsInvalidWithLocation(Path file) throws Exception {
        try(var in=Files.newInputStream(file)) {
            var error=assertThrows(HeadlessRules.InvalidRules.class,()->parser.parse(in,file.getFileName().toString()));
            assertFalse(error.diagnostics().isEmpty());
            assertTrue(error.diagnostics().stream().allMatch(d->d.line()!=null && d.line()>0 && !d.message().isBlank()),error.toString());
            System.out.println(file.getFileName()+": "+error.diagnostics());
        }
    }
    @Test void parseCanonicalGeneratedModel() throws Exception {
        try(var in=Files.newInputStream(Path.of("src/test/resources/valid.adapt"))) {
            var root=parser.parse(in,"valid.adapt");
            assertSame(AdaptationPackage.eINSTANCE,root.eClass().getEPackage());
            assertEquals("ReforzarCiclos",root.getRules().getFirst().getId());
            assertInstanceOf(LogicalCondition.class,root.getRules().getFirst().getCondition());
            assertEquals(HintLevel.CONCEPTUAL,root.getRules().getFirst().getActions().getFirst().getParameters().getHintLevel());
        }
    }
    @Test void ecoreStructure() throws Exception {
        var rs=new ResourceSetImpl();rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore",new org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl());
        var pkg=(EPackage)rs.getResource(URI.createFileURI(Path.of("../com.project.mde.adaptation.model/model/adaptation.ecore").toAbsolutePath().toString()),true).getContents().getFirst();
        assertEquals(0,Diagnostician.INSTANCE.validate(pkg).getSeverity());
        assertEquals(16,pkg.getEClassifiers().stream().filter(EClass.class::isInstance).count());
        assertEquals(6,pkg.getEClassifiers().stream().filter(EEnum.class::isInstance).count());
        assertEquals(10,ActionType.VALUES.size());
        assertEquals("https://mdedu.espoch.edu.ec/model/adaptation/1.0",pkg.getNsURI());
        assertTrue(AdaptationPackage.Literals.ADAPTATION_RULE__CONDITION.isContainment());
        assertTrue(AdaptationPackage.Literals.CONDITION.isAbstract());
    }
    @Test void xmiExamplesRoundTrip() throws Exception {
        for(String name:List.of("reforzar-ciclos","dominio-alto")) {
            var rs=resources();var source=rs.getResource(URI.createFileURI(Path.of("../com.project.mde.adaptation.model/examples/"+name+".adaptation").toAbsolutePath().toString()),true);
            var root=(AdaptationRuleSet)source.getContents().getFirst();AdaptationModels.validate(root,CATALOG);
            var copy=EcoreUtil.copy(root);var bytes=new ByteArrayOutputStream();source.save(bytes,Map.of());source.unload();
            var loaded=resources().createResource(URI.createURI("roundtrip.adaptation"));loaded.load(new ByteArrayInputStream(bytes.toByteArray()),Map.of());
            var next=(AdaptationRuleSet)loaded.getContents().getFirst();AdaptationModels.validate(next,CATALOG);assertTrue(EcoreUtil.equals(copy,next));
            System.out.println(name+": load/validate/save/unload/reload/validate PASS");
        }
    }
    @Test void formalDecisionAndExplanationRemainRepresentable() {
        var f=AdaptationFactory.eINSTANCE;var d=f.createAdaptationDecision();d.setRuleId("Example");
        var a=f.createAction();a.setType(ActionType.REPEAT_ACTIVITY);d.getActions().add(a);
        var e=f.createAdaptationExplanation();e.setRuleId("Example");e.setReason("Candidate only");e.getEvidence().add("fixture");d.setExplanation(e);
        assertEquals(0,Diagnostician.INSTANCE.validate(d).getSeverity());
    }
    private static ResourceSetImpl resources() {
        var rs=new ResourceSetImpl();rs.getPackageRegistry().put(AdaptationPackage.eNS_URI,AdaptationPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("adaptation",new XMIResourceFactoryImpl());return rs;
    }
}
