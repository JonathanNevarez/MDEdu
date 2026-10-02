package com.project.mde.adaptation.dsl.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

/** Xtext extension point: Phase 7 evaluates models and does not generate application code. */
public class AdaptationRulesGenerator extends AbstractGenerator {
    @Override
    public void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
        // Intentionally no M2T output for adaptation rules.
    }
}
