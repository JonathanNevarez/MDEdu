import com.project.mde.learning.*;
import com.project.mde.learning.validation.LearningModels;
import java.nio.file.*;
import java.util.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.*;
import org.eclipse.emf.ecore.util.*;

public class ValidateLearning {
    static ResourceSetImpl resources() {
        var set=new ResourceSetImpl();set.getPackageRegistry().put(LearningPackage.eNS_URI,LearningPackage.eINSTANCE);
        set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("learning",new XMIResourceFactoryImpl());
        set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore",new EcoreResourceFactoryImpl());return set;
    }
    public static void main(String[] args)throws Exception {
        var project=Path.of(args[0]).toAbsolutePath();var set=resources();
        var pack=(EPackage)set.getResource(URI.createFileURI(project.resolve("model/learning.ecore").toString()),true).getContents().getFirst();
        var diagnostic=Diagnostician.INSTANCE.validate(pack);if(diagnostic.getSeverity()!=Diagnostic.OK)throw new IllegalStateException(diagnostic.toString());
        if(pack.getEClassifiers().size()!=10)throw new IllegalStateException("Expected ten EClasses");
        System.out.println("learning.ecore: severity=0; EClasses=10; namespace="+pack.getNsURI());
        Files.createDirectories(project.resolve("target/roundtrip"));
        for(String name:List.of("student-a.learning","student-b.learning")) {
            var input=resources().getResource(URI.createFileURI(project.resolve("examples/"+name).toString()),true);
            var model=(StudentModel)input.getContents().getFirst();LearningModels.validate(model);var before=EcoreUtil.copy(model);
            var out=resources().createResource(URI.createFileURI(project.resolve("target/roundtrip/"+name).toString()));out.getContents().add(model);out.save(Map.of());out.unload();
            var loaded=(StudentModel)resources().getResource(out.getURI(),true).getContents().getFirst();LearningModels.validate(loaded);
            if(!EcoreUtil.equals(before,loaded))throw new IllegalStateException("Roundtrip mismatch");
            System.out.println(name+": load/validate/save/unload/reload/validate PASS; attempts="+loaded.getConceptMasteries().getFirst().getAttemptCount()+"; successes="+loaded.getConceptMasteries().getFirst().getSuccessCount()+"; mastery="+loaded.getConceptMasteries().getFirst().getMasteryScore());
        }
    }
}
