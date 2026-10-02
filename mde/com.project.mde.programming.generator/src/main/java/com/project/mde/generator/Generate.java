package com.project.mde.generator;

import com.project.mde.programming.Program;
import com.project.mde.programming.ProgrammingPackage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.eclipse.acceleo.Module;
import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.aql.evaluation.AcceleoEvaluator;
import org.eclipse.acceleo.aql.evaluation.strategy.DefaultGenerationStrategy;
import org.eclipse.acceleo.aql.evaluation.strategy.DefaultWriterFactory;
import org.eclipse.acceleo.aql.parser.AcceleoParser;
import org.eclipse.acceleo.aql.parser.ModuleLoader;
import org.eclipse.acceleo.aql.validation.AcceleoValidator;
import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.runtime.impl.namespace.ClassLoaderQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/** Standalone wiring based on Acceleo 4.2.2's standaloneMain.mtl and API guide.
 * All emitted JavaScript and model-to-text decisions belong to main.mtl. */
public final class Generate {
    private static final String MODULE = "com::project::mde::generator::main";
    private Generate() { }
    public static void main(String[] args) throws Exception {
        if (args.length != 2) throw new IllegalArgumentException("Usage: Generate <model.programming> <output-directory>");
        generate(Path.of(args[0]), Path.of(args[1]));
    }
    public static Path generate(Path input, Path output) throws Exception {
        ProgrammingPackage.eINSTANCE.getName();
        var rs = new ResourceSetImpl();
        rs.getPackageRegistry().put(ProgrammingPackage.eNS_URI, ProgrammingPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("programming", new XMIResourceFactoryImpl());
        var resource = rs.getResource(URI.createFileURI(input.toAbsolutePath().toString()), true);
        if (resource.getContents().size() != 1 || !(resource.getContents().getFirst() instanceof Program program))
            throw new IllegalArgumentException("Expected one Program root");
        requireValid(Diagnostician.INSTANCE.validate(program));
        Files.createDirectories(output);
        var resolver = new ClassLoaderQualifiedNameResolver(Generate.class.getClassLoader(), EPackage.Registry.INSTANCE, AcceleoParser.QUALIFIER_SEPARATOR);
        var options = Map.of(AcceleoUtil.NEW_LINE_OPTION, "\n");
        var environment = AcceleoUtil.newAcceleoQueryEnvironment(options, resolver, rs, false);
        var evaluator = new AcceleoEvaluator(environment.getLookupEngine(), "\n");
        resolver.addLoader(new ModuleLoader(new AcceleoParser(), evaluator));
        resolver.addLoader(new JavaLoader(AcceleoParser.QUALIFIER_SEPARATOR, false));
        var module = (Module) resolver.resolve(MODULE);
        if (module == null) throw new IllegalStateException("Acceleo module not resolved");
        AQLUtils.registerEPackages(environment, EPackage.Registry.INSTANCE, AQLUtils.getAllNeededEPackages(resolver, MODULE));
        var validation = new AcceleoValidator(environment).validate(module.getAst(), MODULE);
        System.out.println("MTL validation: " + validation.getValidationMessages());
        if (!validation.getValidationMessages().isEmpty()) throw new IllegalStateException("MTL validation diagnostics");
        var strategy = new DefaultGenerationStrategy(rs.getURIConverter(), new DefaultWriterFactory());
        var target = URI.createFileURI(output.toAbsolutePath().toString() + "/");
        try {
            AcceleoUtil.generate(evaluator, environment, module, resource, strategy, target, null, new BasicMonitor());
            var result = evaluator.getGenerationResult();
            requireValid(result.getDiagnostic());
            System.out.println("Acceleo files=" + result.getGeneratedFiles().size() + ", diagnostic=" + result.getDiagnostic());
        } finally {
            AcceleoUtil.cleanServices(environment, rs);
            resource.unload();
        }
        var generated = output.resolve("program.js");
        if (!Files.isRegularFile(generated) || Files.size(generated) == 0) throw new IllegalStateException("Empty generation");
        return generated;
    }
    private static void requireValid(Diagnostic diagnostic) {
        if (diagnostic.getSeverity() != Diagnostic.OK) throw new IllegalArgumentException(diagnostic.toString());
    }
}
