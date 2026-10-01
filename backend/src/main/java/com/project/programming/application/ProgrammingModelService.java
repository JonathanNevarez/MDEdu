package com.project.programming.application;

import com.project.mde.programming.*;
import com.project.programming.api.ProgramDto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ProgrammingModelService {
    public record ModelDiagnostic(String code, String message) {}
    public record Summary(String rootType, int statementCount, String namespace) {}
    public record Result(boolean valid, List<ModelDiagnostic> diagnostics, Summary summary, String xmi) {}
    private final ProgrammingModelMapper mapper;
    public ProgrammingModelService(ProgrammingModelMapper mapper) { this.mapper = mapper; }

    public Result build(ProgramDto dto) {
        var program = mapper.map(dto);
        var diagnostic = Diagnostician.INSTANCE.validate(program);
        if (diagnostic.getSeverity() != Diagnostic.OK) {
            var messages = new ArrayList<ModelDiagnostic>(); flatten(diagnostic, messages);
            return new Result(false, messages, null, null);
        }
        int count = 0;
        for (var it = program.eAllContents(); it.hasNext();) if (it.next() instanceof Statement) count++;
        return new Result(true, List.of(), new Summary("Program", count, ProgrammingPackage.eNS_URI), serialize(program));
    }
    private static void flatten(Diagnostic d, List<ModelDiagnostic> result) {
        if (d.getChildren().isEmpty()) result.add(new ModelDiagnostic("EMF_" + d.getCode(), d.getMessage()));
        else d.getChildren().forEach(child -> flatten(child, result));
    }
    public static String serialize(Program program) {
        var rs = new ResourceSetImpl();
        rs.getPackageRegistry().put(ProgrammingPackage.eNS_URI, ProgrammingPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("programming", new XMIResourceFactoryImpl());
        var resource = rs.createResource(URI.createURI("memory:/program.programming"));
        resource.getContents().add(program);
        try (var output = new ByteArrayOutputStream()) {
            resource.save(output, Map.of(XMLResource.OPTION_ENCODING, "UTF-8"));
            return output.toString(StandardCharsets.UTF_8);
        } catch (IOException e) { throw new IllegalStateException("No se pudo serializar el modelo.", e); }
        finally { resource.unload(); }
    }
}
