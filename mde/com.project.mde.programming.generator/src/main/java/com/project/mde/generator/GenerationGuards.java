package com.project.mde.generator;

import org.eclipse.emf.ecore.EObject;

/** Technical fail-fast service for an unsupported runtime dispatch; emits no text. */
public final class GenerationGuards {
    public String unsupported(EObject value) {
        throw new IllegalArgumentException("Unsupported M2T node: " + value.eClass().getName());
    }
}
