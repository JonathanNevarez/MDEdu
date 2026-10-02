package com.project.mde.adaptation.dsl;

import com.google.inject.*;
import com.google.inject.util.Modules;
import com.project.mde.adaptation.*;
import com.project.mde.adaptation.validation.*;
import java.io.*;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.validation.*;
import org.eclipse.xtext.util.CancelIndicator;

/** Every invocation owns its ResourceSet; parsing is exclusively generated Xtext. */
public final class HeadlessRules {
    private final Injector injector;
    private final RuleCatalog catalog;
    public record Diagnostic(String message, Integer line, Integer column) {}
    public static final class InvalidRules extends IllegalArgumentException {
        private final List<Diagnostic> diagnostics;
        InvalidRules(List<Diagnostic> diagnostics) { super(diagnostics.toString()); this.diagnostics = List.copyOf(diagnostics); }
        public List<Diagnostic> diagnostics() { return diagnostics; }
    }
    public HeadlessRules(RuleCatalog catalog) {
        this.catalog = catalog;
        AdaptationPackage.eINSTANCE.eClass();
        injector = new AdaptationRulesStandaloneSetup() {
            @Override public Injector createInjector() {
                return Guice.createInjector(Modules.override(new AdaptationRulesRuntimeModule())
                    .with(binder -> binder.bind(RuleCatalog.class).toInstance(catalog)));
            }
        }.createInjectorAndDoEMFRegistration();
    }
    public AdaptationRuleSet parse(InputStream input, String sourceName) throws IOException {
        var resources = injector.getInstance(XtextResourceSet.class);
        var resource = resources.createResource(URI.createURI(sourceName));
        if (resource == null) throw new IllegalArgumentException("Expected .adapt source");
        resource.load(input, null);
        var issues = injector.getInstance(IResourceValidator.class).validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        var errors = issues.stream().filter(i -> i.getSeverity() == org.eclipse.xtext.diagnostics.Severity.ERROR)
            .map(i -> new Diagnostic(i.getMessage(), i.getLineNumber(), i.getColumn())).toList();
        if (!errors.isEmpty()) throw new InvalidRules(errors);
        if (resource.getContents().isEmpty() || !(resource.getContents().getFirst() instanceof AdaptationRuleSet root))
            throw new IllegalArgumentException("Expected AdaptationRuleSet");
        AdaptationModels.validate(root, catalog);
        return root;
    }
}
