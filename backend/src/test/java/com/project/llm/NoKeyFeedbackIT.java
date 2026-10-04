package com.project.llm;
import com.project.llm.application.FeedbackOrchestrator;
import com.project.llm.domain.LlmTypes.*;
import com.project.student.application.LearningService;
import com.project.student.api.LearningDtos.SubmitAttempt;
import com.project.programming.api.ProgramDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
@Testcontainers @SpringBootTest(properties={"spring.config.import=","LLM_PROVIDER=OPENAI","LLM_API_KEY=","LLM_MODEL=configured-test-model"})
class NoKeyFeedbackIT {
    @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
    @DynamicPropertySource static void db(DynamicPropertyRegistry r){r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);}
    @Autowired LearningService learning;@Autowired FeedbackOrchestrator feedback;@Autowired ObjectMapper json;
    @Test void applicationStartsAttemptsAdaptAndFeedbackFallsBackWithoutKey() throws Exception {
        var id=learning.create(null).id();ProgramDto program;
        try(var in=getClass().getResourceAsStream("/challenges/SEQ-01.json")){program=json.readValue(in,ProgramDto.class);}
        var attempt=learning.submit(new SubmitAttempt(id,"SEQ-01",100L,0,program));assertNotNull(attempt.adaptation());assertTrue(attempt.execution().evaluation().activityPassed());
        var result=feedback.generate(id,attempt.attemptId());assertEquals(Source.FALLBACK,result.source());assertEquals("DISABLED",result.fallbackReason());assertFalse(result.llmUsed());
    }
}
