package com.project.llm.prompt;
import com.project.llm.domain.LlmTypes.*;
import com.project.llm.application.LlmPolicy;
import com.project.adaptation.manager.CanonicalHashes;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class PromptSupport {
    private final ObjectMapper json=JsonMapper.builder().enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY).enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS).build();
    private final LlmPolicy policy;
    public PromptSupport(LlmPolicy policy){this.policy=policy;}
    public Prompt build(Purpose purpose,PedagogicalLlmContext context) {
        try {
            String name=purpose==Purpose.FEEDBACK_GENERATION?"pedagogical-feedback":"unknown-case";
            String schemaName=purpose==Purpose.FEEDBACK_GENERATION?"feedback":"classification";
            String instructions=resource("llm/prompts/"+name+"-v1.txt");String schema=resource("llm/schemas/"+schemaName+"-v1.json");
            ObjectNode data=json.valueToTree(context);
            // Deterministic removal of least important context first. Diagnosis/objective survive.
            String body=encode(data);
            if(instructions.length()+body.length()>policy.values().maxPromptChars()){data.putArray("previousHints");body=encode(data);}
            if(instructions.length()+body.length()>policy.values().maxPromptChars()){data.putNull("executionSummary");body=encode(data);}
            if(instructions.length()+body.length()>policy.values().maxPromptChars())throw new IllegalArgumentException("PROMPT_TOO_LARGE");
            return new Prompt(purpose,1,instructions,body,schemaName,schema,
                CanonicalHashes.hash(Map.of("purpose",purpose,"version",1,"instructions",instructions,"data",body,"schema",json.readTree(schema))),CanonicalHashes.hash(data));
        }catch(java.io.IOException e){throw new IllegalStateException("Missing versioned prompt resource");}
    }
    private String encode(JsonNode node) throws java.io.IOException{return "<pedagogical_data>\n"+json.writeValueAsString(node)+"\n</pedagogical_data>";}
    private String resource(String path) throws java.io.IOException {try(var in=new ClassPathResource(path).getInputStream()){return new String(in.readAllBytes(),StandardCharsets.UTF_8).replace("\r\n","\n");}}
}
