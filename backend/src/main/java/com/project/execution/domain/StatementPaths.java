package com.project.execution.domain;

import java.util.List;
import org.eclipse.emf.ecore.EObject;

/** Stable containment paths shared by tracing and structural analysis. */
public final class StatementPaths {
    private StatementPaths() {}
    public static String of(EObject node) {
        if (node.eContainer() == null) return "";
        var parent = node.eContainer(); var feature = node.eContainingFeature();
        String prefix = of(parent);
        String part = feature.getName();
        if (feature.isMany()) part += "[" + ((List<?>) parent.eGet(feature)).indexOf(node) + "]";
        return prefix.isEmpty() ? part : prefix + "." + part;
    }
}
