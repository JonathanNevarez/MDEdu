package com.project.mde.adaptation.validation;
import java.util.Set;
/** IDs supplied from the actual application catalog; no duplicated pedagogical catalog. */
public record RuleCatalog(Set<String> concepts, Set<String> patterns) {
    public RuleCatalog { concepts = Set.copyOf(concepts); patterns = Set.copyOf(patterns); }
}
