package com.project.ui.application;
import com.project.ui.infrastructure.AttemptProgramStore;
import com.project.programming.application.ProgrammingModelService;
import com.project.mde.generator.Generate;
import java.nio.file.*;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AttemptCodeService {
    public record Code(boolean available,String text,String reason) {}
    private final AttemptProgramStore programs;private final ProgrammingModelService models;
    public AttemptCodeService(AttemptProgramStore programs,ProgrammingModelService models){this.programs=programs;this.models=models;}
    public Code generate(UUID student,UUID attempt) {
        Path directory=null;
        try {
            var snapshot=programs.find(student,attempt);
            if(snapshot.isEmpty())return new Code(false,null,"PROGRAM_SNAPSHOT_UNAVAILABLE");
            var model=models.build(snapshot.get());
            if(!model.valid())return new Code(false,null,"INVALID_PROGRAM_MODEL");
            directory=Files.createTempDirectory("mdedu-readonly-code-");
            var input=directory.resolve("input.programming");var output=directory.resolve("output");
            Files.writeString(input,model.xmi());
            Generate.generate(input,output);
            return new Code(true,Files.readString(output.resolve("program.js")),null);
        } catch(Exception ex) {return new Code(false,null,"CODE_GENERATION_UNAVAILABLE");}
        finally {
            if(directory!=null)try {
                Files.deleteIfExists(directory.resolve("output/program.js"));Files.deleteIfExists(directory.resolve("output"));
                Files.deleteIfExists(directory.resolve("input.programming"));Files.deleteIfExists(directory);
            } catch(java.io.IOException ex) {org.slf4j.LoggerFactory.getLogger(getClass()).warn("Temporary code output cleanup failed");}
        }
    }
}
