package com.project.mde.learning.validation;

import com.project.mde.learning.*;
import java.util.*;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

/** Handwritten invariants outside generated code. */
public final class LearningModels {
    private LearningModels() {}
    public static void validate(StudentModel model) {
        if (Diagnostician.INSTANCE.validate(model).getSeverity() != Diagnostic.OK)
            throw new IllegalArgumentException("Invalid learning model structure");
        if (model.getModelVersion() != 1) throw new IllegalArgumentException("Unknown learning model version");
        unique(model.getConcepts().stream().map(Concept::getId).toList());
        unique(model.getActivities().stream().map(Activity::getId).toList());
        unique(model.getConceptMasteries().stream().map(m -> m.getConcept().getId()).toList());
        for (var m : model.getConceptMasteries()) {
            if (!Double.isFinite(m.getMasteryScore()) || m.getMasteryScore() < 0 || m.getMasteryScore() > 1 ||
                m.getAttemptCount() < 0 || m.getSuccessCount() < 0 || m.getFailureCount() < 0 ||
                m.getHintCount() < 0 || m.getConsecutiveFailures() < 0 || m.getConsecutiveFailures() > m.getFailureCount() ||
                m.getSuccessCount() + m.getFailureCount() != m.getAttemptCount() ||
                !Double.isFinite(m.getAverageResolutionTime()) || m.getAverageResolutionTime() < 0 || m.getStudent() != model.getStudent())
                throw new IllegalArgumentException("Invalid concept mastery");
        }
        var objects = new ArrayList<org.eclipse.emf.ecore.EObject>(); objects.add(model);
        model.eAllContents().forEachRemaining(objects::add);
        for (var object : objects) for (var ref : object.eClass().getEAllReferences()) {
            Object value = object.eGet(ref);
            var targets = value instanceof Collection<?> list ? list : value == null ? List.of() : List.of(value);
            for (var target : targets) if (target instanceof org.eclipse.emf.ecore.EObject e &&
                (e.eIsProxy() || EcoreUtil.getRootContainer(e) != model))
                throw new IllegalArgumentException("Snapshot has an external or unresolved reference");
        }
    }
    private static void unique(List<String> ids) {
        if (ids.stream().anyMatch(id -> id == null || id.isBlank()) || new HashSet<>(ids).size() != ids.size())
            throw new IllegalArgumentException("Duplicate or empty learning ID");
    }
}
