package com.project.evaluation.application;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.evaluation.domain.*;
import com.project.evaluation.domain.EvaluationTypes.EvaluationResult;
import com.project.execution.domain.GameTypes.Result;
import com.project.mde.programming.Program;
import org.springframework.stereotype.Service;
@Service
public class SolutionEvaluationService {
    private final PatternCatalog catalog;
    public SolutionEvaluationService(PatternCatalog catalog){this.catalog=catalog;}
    public EvaluationResult evaluate(String levelId,Program program,Result execution) {
        return new SolutionEvaluator(catalog).evaluate(new EvaluationContext(program,execution,catalog.level(levelId)));
    }
}
