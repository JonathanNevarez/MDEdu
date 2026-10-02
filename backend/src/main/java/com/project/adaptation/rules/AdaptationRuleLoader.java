package com.project.adaptation.rules;

import com.project.evaluation.catalog.PatternCatalog;
import com.project.evaluation.domain.EvaluationTypes.Concept;
import com.project.mde.adaptation.*;
import com.project.mde.adaptation.dsl.HeadlessRules;
import com.project.mde.adaptation.validation.RuleCatalog;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public final class AdaptationRuleLoader {
    private final RuleCatalog catalog;
    private final AdaptationRuleSet rules;
    private final String hash;
    public AdaptationRuleLoader(PatternCatalog patterns) throws IOException {
        catalog=new RuleCatalog(Arrays.stream(Concept.values()).map(Enum::name).collect(Collectors.toSet()),
            patterns.patterns().stream().map(p->p.id()).collect(Collectors.toSet()));
        try(var in=new ClassPathResource("adaptation/rules/rules-v1.adapt").getInputStream()) {
            byte[] bytes=in.readAllBytes();
            hash=com.project.adaptation.manager.CanonicalHashes.bytes(new String(bytes,java.nio.charset.StandardCharsets.UTF_8).replace("\r\n","\n").replace("\r","\n"));
            rules=new HeadlessRules(catalog).parse(new java.io.ByteArrayInputStream(bytes),"rules-v1.adapt");
        }
    }
    public String hash() { return hash; }
    public RuleCatalog catalog() { return catalog; }
    /** Isolate the validated startup model from consumers' EMF mutations. */
    public AdaptationRuleSet snapshot() { return EcoreUtil.copy(rules); }
}
