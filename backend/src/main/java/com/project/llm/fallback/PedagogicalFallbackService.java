package com.project.llm.fallback;
import com.project.llm.domain.LlmTypes.*;
import org.springframework.stereotype.Component;
@Component
public class PedagogicalFallbackService {
    public Feedback generate(PedagogicalLlmContext c) {
        String concept=switch(c.concept()){case "LOOPS"->"ciclos";case "VARIABLES"->"variables";case "CONDITIONALS"->"condicionales";default->"secuencias";};
        String message=c.detectedPattern()!=null?c.patternMeaning()+" "+c.recommendedAction():
            c.activityPassed()?"Cumpliste el objetivo. Explica con tus palabras cómo aplicaste el concepto de "+concept+".":
            "Tu solución todavía no cumple completamente el objetivo. Revisa cómo aplicas el concepto de "+concept+" y vuelve a probar.";
        String question=switch(c.hintStage()) {
            case SOCRATIC_QUESTION -> "¿Qué parte de tu programa podrías revisar primero?";
            case CONCEPTUAL_HINT -> "¿Cómo te ayuda este concepto a expresar tu intención?";
            case ANALOGOUS_EXAMPLE -> "Piensa en organizar una tarea cotidiana en pasos: ¿qué estructura usarías?";
            case PARTIAL_HELP -> "Revisa una estructura cada vez y comprueba su efecto antes de continuar.";
        };
        return new Feedback(message,question,c.detectedPattern()==null?c.concept():c.detectedPattern(),c.hintStage(),"es");
    }
}
