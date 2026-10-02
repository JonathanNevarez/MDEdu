package com.project.mde.ui.transformations;

import com.project.mde.ui.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.m2m.atl.core.emf.*;
import org.eclipse.m2m.atl.engine.compiler.atl2006.Atl2006Compiler;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;
import org.junit.Test;
import static org.junit.Assert.*;

/** Executes compiled ATL in the real Eclipse VM, without a UI or Java transformation substitute. */
public class UiPipelineTest {
    private ResourceSetImpl resources() {
        var rs=new ResourceSetImpl();
        rs.getPackageRegistry().put(UiPackage.eNS_URI,UiPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ui",new XMIResourceFactoryImpl());
        return rs;
    }
    private EObject transform(String module,EObject input) throws Exception {
        var factory=new EMFModelFactory();var injector=new EMFInjector();
        var meta=factory.newReferenceModel();
        var metaResource=new org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl().createResource(URI.createURI("memory:/ui.ecore"));
        metaResource.getContents().add(UiPackage.eINSTANCE);
        injector.inject(meta,metaResource);
        var source=factory.newModel(meta);var target=factory.newModel(meta);
        var resource=resources().createResource(URI.createURI("memory:/input.ui"));
        resource.getContents().add(input);injector.inject(source,resource);
        var asm=new ByteArrayOutputStream();
        try(var atl=getClass().getResourceAsStream("/transformations/"+module+".atl")) {
            assertNotNull(atl);
            var errors=new Atl2006Compiler().compileWithProblemModel(atl,asm);
            for(var error:errors) {
                var severity=error.eClass().getEStructuralFeature("severity");
                assertFalse(error.toString(), severity!=null && "error".equals(String.valueOf(error.eGet(severity))));
            }
        }
        assertTrue("Compiled ATL ASM",asm.size()>0);
        var vm=new EMFVMLauncher();vm.initialize(Map.of());vm.addInModel(source,"IN","UI");vm.addOutModel(target,"OUT","UI");
        vm.launch("run",null,Map.of(),new ByteArrayInputStream(asm.toByteArray()));
        var output=new ByteArrayOutputStream();new EMFExtractor().extract(target,output,Map.of());
        var result=resources().createResource(URI.createURI("memory:/result.ui"));
        result.load(new ByteArrayInputStream(output.toByteArray()),Map.of());
        assertEquals(1,result.getContents().size());
        return result.getContents().getFirst();
    }
    private EObject roundtrip(EObject model,Path path) throws Exception {
        assertEquals(Diagnostic.OK,Diagnostician.INSTANCE.validate(model).getSeverity());
        var resource=resources().createResource(URI.createFileURI(path.toAbsolutePath().toString()));
        resource.getContents().add(model);resource.save(Map.of());resource.unload();resource.load(Map.of());
        var reloaded=resource.getContents().getFirst();
        assertEquals(Diagnostic.OK,Diagnostician.INSTANCE.validate(reloaded).getSeverity());
        var expected=resources().createResource(URI.createURI("memory:/expected.ui"));
        try(var in=UiPackage.class.getResourceAsStream("/examples/"+path.getFileName())) {
            assertNotNull("Checked ATL output or input example",in);expected.load(in,Map.of());
        }
        assertTrue("Versioned model matches actual pipeline: "+path,org.eclipse.emf.ecore.util.EcoreUtil.equals(reloaded,expected.getContents().getFirst()));
        return reloaded;
    }
    @Test public void fourRealAtlChainsAndXmiRoundtrips() throws Exception {
        var output=Path.of("target","ui-evidence");Files.createDirectories(output);
        for(String id:List.of("SEQUENCES","VARIABLES","CONDITIONALS","LOOPS")) {
            var source=resources().createResource(URI.createURI("memory:/task.ui"));
            try(var in=UiPackage.class.getResourceAsStream("/examples/task-"+id.toLowerCase()+".ui")){assertNotNull(in);source.load(in,Map.of());}
            var task=(TaskAndDomainModel)roundtrip(source.getContents().getFirst(),output.resolve("task-"+id.toLowerCase()+".ui"));
            var a=(AbstractUIModel)roundtrip(transform("TaskAndDomain2AbstractUI",task),output.resolve("abstract-"+id.toLowerCase()+".ui"));
            assertEquals(8,a.getElements().size());assertEquals(id,a.getOriginId());
            var c=(ConcreteUIModel)roundtrip(transform("AbstractUI2ConcreteUI",a),output.resolve("concrete-"+id.toLowerCase()+".ui"));
            assertEquals(8,c.getElements().size());assertEquals(ConcreteKind.BLOCKLY_EDITOR,c.getElements().getFirst().getKind());
            for(var e:c.getElements())assertTrue(e.getOriginId().startsWith(id+":"));
        }
        for(boolean assisted:List.of(false,true)) {
            var config=UiFactory.eINSTANCE.createFinalUIConfiguration();config.setActivityId("LOOPS");
            config.setShowCodePanel(assisted);config.setGeneratedCodeVisible(assisted);config.setEnabledAssistance(assisted);
            config.setHintPanelMode(assisted?HintPanelMode.EXPANDED:HintPanelMode.HIDDEN);
            config.setActivityLayout(assisted?ActivityLayout.ASSISTED:ActivityLayout.STANDARD);
            config.setTutorMode(assisted?TutorMode.HINT:TutorMode.HIDDEN);
            config.setHintStage(assisted?HintStage.CONCEPTUAL_HINT:HintStage.NONE);
            roundtrip(config,output.resolve("ui-config-"+(assisted?"assisted":"standard")+".ui"));
        }
    }
    @Test public void unsupportedCapabilitiesAreOmittedByAtl() throws Exception {
        var task=UiFactory.eINSTANCE.createTaskAndDomainModel();task.setActivityId("LOOPS");task.setConceptId("LOOPS");
        var a=(AbstractUIModel)transform("TaskAndDomain2AbstractUI",task);
        assertEquals(1,a.getElements().size());assertEquals(AbstractKind.INSTRUCTION_AREA,a.getElements().getFirst().getKind());
    }
}
