package com.project.evaluation.detectors;
import com.project.evaluation.domain.EvaluationContext;
import com.project.evaluation.domain.EvaluationTypes.Evidence;
import java.util.List;
@FunctionalInterface
public interface PatternDetector { List<Evidence> detect(EvaluationContext context); }
