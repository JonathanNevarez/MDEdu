package com.project.evaluation.domain;

import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.execution.domain.GameTypes.*;
import com.project.execution.domain.StatementPaths;
import com.project.mde.programming.*;
import java.util.*;

public final class EvaluationContext {
    public final Program program;
    public final Result execution;
    public final LevelEvaluationConfig config;
    public final ProgramFingerprintService fingerprints;
    public final ConstantFolder constants = new ConstantFolder();
    public final List<Statement> statements = new ArrayList<>();
    public final List<VariableDeclaration> declarations = new ArrayList<>();
    public EvaluationContext(Program p, Result execution, LevelEvaluationConfig config) {
        program = p; this.execution = execution; this.config = config; fingerprints = new ProgramFingerprintService(p);
        p.eAllContents().forEachRemaining(o -> { if (o instanceof Statement s) statements.add(s); if (o instanceof VariableDeclaration d) declarations.add(d); });
    }
    public String path(Statement s) { return StatementPaths.of(s); }
    public List<Event> events(Statement s, String type) { return execution.trace().stream().filter(e -> type.equals(e.type()) && path(s).equals(e.statementPath())).toList(); }
    public Optional<Event> first(Statement s, String type) { return events(s, type).stream().findFirst(); }
    public List<List<Statement>> blocks() {
        var result = new ArrayList<List<Statement>>(); result.add(program.getStatements());
        for (var s : statements) {
            if (s instanceof Repeat r) result.add(r.getBody());
            else if (s instanceof While w) result.add(w.getBody());
            else if (s instanceof IfElse i) { result.add(i.getThenBranch()); result.add(i.getElseBranch()); }
            else if (s instanceof If i) result.add(i.getThenBranch());
        }
        return result;
    }
    public boolean insideLoop(Statement s) { for (var p=s.eContainer(); p!=null; p=p.eContainer()) if (p instanceof Repeat || p instanceof While) return true; return false; }
    public boolean reads(Statement s, VariableDeclaration d) {
        var it=s.eAllContents(); while(it.hasNext()) if(it.next() instanceof VariableReference r && r.getDeclaration()==d) return true;
        return false;
    }
    public Evidence evidence(Statement s, String observed, String expected) { return new Evidence(s==null ? "statements" : path(s), null, observed, expected); }
}
