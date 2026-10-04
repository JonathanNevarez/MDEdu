package com.project.evaluation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.execution.application.*;
import com.project.evaluation.application.SolutionEvaluationService;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.programming.application.ProgrammingModelMapper;
import com.project.programming.api.ProgramDto;
import com.project.mde.programming.*;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.common.util.Diagnostic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class GuidedChallengesTest {
    final ObjectMapper json=new ObjectMapper();
    final LevelCatalog levels=new LevelCatalog(json);
    final PatternCatalog patterns=new PatternCatalog(json,levels);
    final ProgrammingModelMapper mapper=new ProgrammingModelMapper();
    final GameExecutionService execution=new GameExecutionService(mapper,200,new SolutionEvaluationService(patterns));
    GuidedChallengesTest()throws Exception{}
    ProgramDto fixture(String id)throws Exception {
        try(var in=getClass().getResourceAsStream("/challenges/"+id+".json")){return json.readValue(in,ProgramDto.class);}
    }
    @ParameterizedTest @ValueSource(strings={"SEQ-01","SEQ-02","SEQ-03","SEQ-04","VAR-01","VAR-02","VAR-03","VAR-04","COND-01","COND-02","COND-03","COND-04","COND-05","LOOP-01","LOOP-02","LOOP-03","LOOP-04","LOOP-05"})
    void validModelCorrectSolutionAndRepresentativeError(String id)throws Exception {
        var dto=fixture(id);var program=mapper.map(dto);var level=levels.find(id).orElseThrow();
        assertEquals(Diagnostic.OK,Diagnostician.INSTANCE.validate(program).getSeverity());
        var correct=execution.execute(level,dto);
        assertTrue(correct.success(),id+": "+correct.errors());
        assertTrue(correct.evaluation().activityPassed(),id+": "+correct.evaluation());
        String expected;
        if(id.startsWith("SEQ")){program.getStatements().removeLast();expected="MISSING_ACTION";}
        else if(id.startsWith("VAR")){
            ((Literal)((VariableDeclaration)program.getStatements().getFirst()).getInitialValue()).setValue("99");expected="INCORRECT_UPDATE";
        }else if(id.startsWith("COND")){
            program.eAllContents().forEachRemaining(o->{if(o instanceof If i){var b=ProgrammingFactory.eINSTANCE.createLiteral();b.setType(ValueType.BOOLEAN);b.setValue("true");i.setCondition(b);}});expected="CONSTANT_CONDITION";
        }else{
            program.eAllContents().forEachRemaining(o->{if(o instanceof Repeat r){var n=ProgrammingFactory.eINSTANCE.createLiteral();n.setValue("0");r.setCount(n);}else if(o instanceof While w){var b=ProgrammingFactory.eINSTANCE.createLiteral();b.setType(ValueType.BOOLEAN);b.setValue("false");w.setCondition(b);}});expected="LOOP_NEVER_EXECUTES";
        }
        assertEquals(Diagnostic.OK,Diagnostician.INSTANCE.validate(program).getSeverity());
        var result=new com.project.execution.domain.GridWorldExecutionEngine().execute(program,level.worldConfig(),200);
        var bad=new SolutionEvaluationService(patterns).evaluate(id,program,result);
        assertFalse(bad.activityPassed());assertTrue(bad.patterns().stream().anyMatch(p->p.id().equals(expected)),bad.toString());
    }
    @Test void manualLoopIsFunctionallyCorrectButPedagogicallyFails()throws Exception{
        var p=EvaluationFixtures.manual();var level=levels.find("LOOP-01").orElseThrow();
        var result=new com.project.execution.domain.GridWorldExecutionEngine().execute(p,level.worldConfig(),200);
        var e=new SolutionEvaluationService(patterns).evaluate(level.id(),p,result);
        assertTrue(e.functionalCorrectness().passed());assertFalse(e.activityPassed());assertFalse(e.structuralCorrectness().constraintsSatisfied());
        assertTrue(e.patterns().stream().anyMatch(x->x.id().equals("REPETITIVE_SEQUENCE_WITHOUT_LOOP")));
    }
    @Test void forgedAdvancedBlocksCannotPassSequences() {
        var f=ProgrammingFactory.eINSTANCE;
        var p=EvaluationFixtures.program(EvaluationFixtures.repeat(2,f.createMove()),f.createTurnRight(),EvaluationFixtures.repeat(2,f.createMove()));
        var result=assess("SEQ-01",p);assertTrue(result.functionalCorrectness().passed());assertFalse(result.activityPassed());
        assertFalse(result.structuralCorrectness().structuralConstraints().get("allowedBlocks"));
    }
    @Test void variableValuesMustBeReadBeforeOverwriting()throws Exception {
        var p=mapper.map(fixture("VAR-03"));
        p.getStatements().removeIf(s->s instanceof VariableDeclaration d&&!d.getName().equals("energia"));
        var result=assess("VAR-03",p);assertTrue(result.functionalCorrectness().passed());assertFalse(result.activityPassed());
        assertFalse(result.structuralCorrectness().structuralConstraints().get("variableCheckpoints"));
    }
    @Test void efficientRouteReportsUnnecessaryInstructionsWithoutAnImpossibleOptimum()throws Exception {
        var p=mapper.map(fixture("SEQ-04"));p.getStatements().add(ProgrammingFactory.eINSTANCE.createTurnLeft());
        var result=assess("SEQ-04",p);assertTrue(result.functionalCorrectness().passed());assertTrue(result.activityPassed());
        assertTrue(result.patterns().stream().anyMatch(x->x.id().equals("UNNECESSARY_INSTRUCTION")));
        assertEquals("NEEDS_REVIEW",result.pedagogicalEfficiency().status());
    }
    @Test void realSensorWhileWithoutMovementExhaustsBudget()throws Exception {
        var p=mapper.map(fixture("LOOP-04"));((While)p.getStatements().getFirst()).getBody().clear();
        var result=assess("LOOP-04",p);assertFalse(result.activityPassed());
        assertEquals("STEP_LIMIT_EXCEEDED",result.functionalCorrectness().executionStatus());
        assertTrue(result.patterns().stream().anyMatch(x->x.id().equals("POSSIBLE_INFINITE_LOOP")));
    }
    @Test void shortCircuitedVariableReferenceDoesNotCountAsARead()throws Exception {
        var p=mapper.map(fixture("LOOP-05"));var repeat=(Repeat)p.getStatements().get(1);
        repeat.setCount(EvaluationFixtures.integer(4));var branch=(If)repeat.getBody().getFirst();
        var comparison=ProgrammingFactory.eINSTANCE.createComparison();comparison.setOperator(ComparisonOperator.GREATER_THAN);
        comparison.setLeft(EvaluationFixtures.ref((VariableDeclaration)p.getStatements().getFirst()));comparison.setRight(EvaluationFixtures.integer(0));
        var condition=ProgrammingFactory.eINSTANCE.createBooleanExpression();condition.setOperator(BooleanOperator.OR);
        condition.setLeft(EvaluationFixtures.bool(true));condition.setRight(comparison);branch.setCondition(condition);
        var result=assess("LOOP-05",p);assertTrue(result.functionalCorrectness().passed());assertFalse(result.activityPassed());
        assertFalse(result.structuralCorrectness().structuralConstraints().get("executedVariableReads"));
    }
    com.project.evaluation.domain.EvaluationTypes.EvaluationResult assess(String id,Program p) {
        var execution=new com.project.execution.domain.GridWorldExecutionEngine().execute(p,levels.find(id).orElseThrow().worldConfig(),200);
        return new SolutionEvaluationService(patterns).evaluate(id,p,execution);
    }
}
