package com.project.mde.adaptation.dsl.validation;

import com.google.inject.Inject;
import com.project.mde.adaptation.AdaptationRuleSet;
import com.project.mde.adaptation.validation.AdaptationModels;
import com.project.mde.adaptation.validation.RuleCatalog;
import org.eclipse.xtext.validation.Check;

public class AdaptationRulesValidator extends AbstractAdaptationRulesValidator {
    @Inject private RuleCatalog catalog;
    @Check public void checkRuleSet(AdaptationRuleSet root) {
        for (var p : AdaptationModels.problems(root, catalog))
            error(p.message(), p.object(), p.feature(), "adaptation.semantic");
    }
}
