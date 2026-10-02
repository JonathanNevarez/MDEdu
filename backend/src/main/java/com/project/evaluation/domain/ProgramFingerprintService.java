package com.project.evaluation.domain;

import com.project.mde.programming.*;
import java.util.*;
import org.eclipse.emf.ecore.EObject;

/** Collision-free length-prefixed structural representation; no XMI, names or object addresses. */
public final class ProgramFingerprintService {
    private final IdentityHashMap<VariableDeclaration, Integer> ordinals = new IdentityHashMap<>();
    public ProgramFingerprintService(Program program) {
        program.eAllContents().forEachRemaining(o -> { if (o instanceof VariableDeclaration d) ordinals.put(d, ordinals.size() + 1); });
    }
    public int ordinal(VariableDeclaration d) { return ordinals.getOrDefault(d, 0); }
    private String pack(String... parts) { var out = new StringBuilder(); for (String p : parts) out.append(p.length()).append(':').append(p); return out.toString(); }
    public String branch(List<? extends Statement> statements) { return pack(statements.stream().map(this::node).toArray(String[]::new)); }
    public String node(EObject n) {
        if (n == null) return "absent";
        String type = n.eClass().getName();
        if (n instanceof VariableDeclaration d) return pack(type, "v" + ordinal(d), d.getType().getName(), node(d.getInitialValue()));
        if (n instanceof Assignment a) return pack(type, "v" + ordinal(a.getTarget()), node(a.getValue()));
        if (n instanceof VariableReference r) return pack(type, "v" + ordinal(r.getDeclaration()));
        if (n instanceof Literal l) return pack(type, l.getType().getName(), Objects.toString(l.getValue(), ""));
        if (n instanceof SensorExpression s) return pack(type, s.getSensor().getName());
        if (n instanceof Comparison c) return pack(type, c.getOperator().getName(), node(c.getLeft()), node(c.getRight()));
        if (n instanceof BooleanExpression x) return pack(type, x.getOperator().getName(), node(x.getLeft()), node(x.getRight()));
        if (n instanceof Repeat r) return pack(type, node(r.getCount()), branch(r.getBody()));
        if (n instanceof While w) return pack(type, node(w.getCondition()), branch(w.getBody()));
        if (n instanceof IfElse i) return pack(type, node(i.getCondition()), branch(i.getThenBranch()), branch(i.getElseBranch()));
        if (n instanceof If i) return pack(type, node(i.getCondition()), branch(i.getThenBranch()));
        return pack(type);
    }
}
