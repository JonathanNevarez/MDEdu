package com.project.evaluation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.evaluation.detectors.DetectorRegistry;
import com.project.evaluation.domain.*;
import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.execution.application.LevelCatalog;
import com.project.execution.domain.GridWorldExecutionEngine;
import com.project.mde.programming.*;
import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.project.evaluation.EvaluationFixtures.*;

class PatternDetectorsTest {
    static LevelCatalog game;
    static PatternCatalog catalog;
    static final DetectorRegistry DETECTORS=new DetectorRegistry();
    @BeforeAll static void catalogs()throws Exception{game=new LevelCatalog(new ObjectMapper());catalog=new PatternCatalog(new ObjectMapper(),game);}
    static Stream<Arguments> scenarios(){return DETECTORS.ids().stream().flatMap(id->Stream.of(Arguments.of(id,true),Arguments.of(id,false)));}
    static EvaluationContext context(String level,Program p){return new EvaluationContext(p,new GridWorldExecutionEngine().execute(p,game.find(level).orElseThrow().worldConfig(),200),catalog.level(level));}
    @ParameterizedTest(name="{0} positive={1}") @MethodSource("scenarios")
    void positiveAndNegativeEveryPattern(String id,boolean positive){
        var concept=catalog.patterns().stream().filter(p->p.id().equals(id)).findFirst().orElseThrow().concept().name();
        var c=context(concept,fixture(id,positive));var found=DETECTORS.get(id).detect(c);
        assertEquals(positive,!found.isEmpty(),id);
        for(var e:found){assertNotNull(e.statementPath());assertFalse(e.observed().isBlank());assertFalse(e.expected().isBlank());}
    }
    @Test void sequenceMismatchIsNotInventedAndPatternsExclusive(){
        var c=context("SEQUENCES",program(F.createTurnLeft(),F.createTurnLeft()));
        for(String id:List.of("WRONG_ORDER","MISSING_ACTION","UNNECESSARY_INSTRUCTION"))assertTrue(DETECTORS.get(id).detect(c).isEmpty());
        for(String id:List.of("WRONG_ORDER","MISSING_ACTION","UNNECESSARY_INSTRUCTION")){
            c=context("SEQUENCES",fixture(id,true));assertEquals(1,new SolutionEvaluator(catalog).evaluate(c).patterns().size());
        }
        var result=new SolutionEvaluator(catalog).evaluate(context("SEQUENCES",fixture("UNNECESSARY_INSTRUCTION",true)));
        assertTrue(result.activityPassed());assertEquals("NEEDS_REVIEW",result.pedagogicalEfficiency().status());
    }
    @Test void readBetweenWritesAndInitialUpdateAreNotRedundant(){
        var d=variable("x");var p=program(d,assign(d,integer(1)),assign(d,ref(d)));
        assertTrue(DETECTORS.get("REDUNDANT_REASSIGNMENT").detect(context("VARIABLES",p)).isEmpty());
        assertTrue(DETECTORS.get("UNUSED_VARIABLE").detect(context("VARIABLES",p)).isEmpty());
    }
    @Test void namesDoNotControlVariablesOrFingerprint(){
        var a=updated(1);var b=updated(1);((VariableDeclaration)b.getStatements().getFirst()).setName("otro nombre \" á");
        assertEquals(new ProgramFingerprintService(a).branch(a.getStatements()),new ProgramFingerprintService(b).branch(b.getStatements()));
        assertTrue(new SolutionEvaluator(catalog).evaluate(context("VARIABLES",b)).activityPassed());
        var isolated=program(variable("x"),move(),move(),move(),move());
        assertFalse(new SolutionEvaluator(catalog).evaluate(context("VARIABLES",isolated)).structuralCorrectness().requiredConceptUsed());
    }
    @Test void fingerprintsPreserveReferenceIdentityAndBranchStructure(){
        var a=variable("same");var b=variable("same");var i=F.createIfElse();i.setCondition(sensor(SensorKind.HAS_KEY));
        i.getThenBranch().add(assign(a,ref(a)));i.getElseBranch().add(assign(b,ref(b)));var p=program(a,b,i);
        assertTrue(DETECTORS.get("IDENTICAL_BRANCHES").detect(context("CONDITIONALS",p)).isEmpty());
        ((Assignment)i.getElseBranch().getFirst()).setTarget(a);((VariableReference)((Assignment)i.getElseBranch().getFirst()).getValue()).setDeclaration(a);
        assertFalse(DETECTORS.get("IDENTICAL_BRANCHES").detect(context("CONDITIONALS",p)).isEmpty());
    }
    @Test void constantFoldingStrictTypesAndDynamicUnknown(){
        var folder=new ConstantFolder();assertEquals(Optional.of(false),folder.value(bool(false)));
        var cmp=F.createComparison();cmp.setOperator(ComparisonOperator.LESS_THAN);cmp.setLeft(integer(1));cmp.setRight(integer(2));
        assertEquals(Optional.of(true),folder.value(cmp));
        var b=F.createBooleanExpression();b.setOperator(BooleanOperator.NOT);b.setLeft(cmp);assertEquals(Optional.of(false),folder.value(b));
        b.setOperator(BooleanOperator.AND);b.setRight(bool(true));assertEquals(Optional.of(true),folder.value(b));
        b.setOperator(BooleanOperator.OR);b.setLeft(bool(false));assertEquals(Optional.of(true),folder.value(b));
        b.setRight(sensor(SensorKind.HAS_KEY));assertTrue(folder.value(b).isEmpty());
        assertTrue(folder.value(ref(variable("v"))).isEmpty());
        var malformed=integer(1);malformed.setValue("not a number");assertTrue(folder.value(malformed).isEmpty());
        cmp.setLeft(bool(true));cmp.setRight(integer(1));assertTrue(folder.value(cmp).isEmpty());
        assertFalse(DETECTORS.get("CONSTANT_CONDITION").detect(context("CONDITIONALS",program(conditional(bool(false),false)))).isEmpty());
    }
    @Test void emptyRequiredBranchAndNoArtificialRequirement(){
        var i=conditional(sensor(SensorKind.HAS_KEY),false);i.getElseBranch().clear();var c=context("CONDITIONALS",program(i));
        assertFalse(DETECTORS.get("MISSING_REQUIRED_BRANCH").detect(c).isEmpty());
        var p=c.config.parameters();var optional=new Parameters(p.minimumManualRepetitions(),p.minimumLoopIterations(),p.expectedIterationCount(),p.declarationOrdinal(),p.variableType(),p.expectedInitialValue(),p.expectedFinalValue(),true,false);
        var cfg=new LevelEvaluationConfig(c.config.levelId(),c.config.requiredConcept(),List.of("If"),List.of(),List.of(),optional);
        assertTrue(DETECTORS.get("MISSING_REQUIRED_BRANCH").detect(new EvaluationContext(c.program,c.execution,cfg)).isEmpty());
    }
    @Test void manualPairsAndInsideLoops(){
        var pair=program(move(),F.createTurnRight(),move(),F.createTurnRight(),move(),F.createTurnRight());
        assertEquals("unitLength=2, repetitions=3",DETECTORS.get("REPETITIVE_SEQUENCE_WITHOUT_LOOP").detect(context("LOOPS",pair)).getFirst().observed());
        assertTrue(DETECTORS.get("REPETITIVE_SEQUENCE_WITHOUT_LOOP").detect(context("LOOPS",program(repeat(3,move(),move(),move())))).isEmpty());
    }
    @Test void whileTraceAttributesFalseAndCorrectIterations(){
        var p=program(loop(sensor(SensorKind.AT_GOAL),move()));var c=context("LOOPS",p);
        var e=DETECTORS.get("LOOP_NEVER_EXECUTES").detect(c).getFirst();assertEquals("statements[0]",e.statementPath());assertNotNull(e.traceIndex());
        c=context("LOOPS",program(loop(sensor(SensorKind.FRONT_CLEAR),move())));
        assertTrue(new SolutionEvaluator(catalog).evaluate(c).activityPassed());
        assertTrue(DETECTORS.get("POSSIBLE_INFINITE_LOOP").detect(c).isEmpty());
        c=context("LOOPS",program(loop(sensor(SensorKind.FRONT_CLEAR))));
        assertEquals("STEP_LIMIT_EXCEEDED",c.execution.status());assertFalse(DETECTORS.get("POSSIBLE_INFINITE_LOOP").detect(c).isEmpty());
    }
    @Test void deadAndEmptyLoopsCannotSatisfyConcept(){
        for(var r:List.of(repeat(0,move()),repeat(1,move()),repeat(7))){
            var p=manual();p.getStatements().add(0,r);assertFalse(new SolutionEvaluator(catalog).evaluate(context("LOOPS",p)).activityPassed());
        }
        var p=manual();var i=F.createIf();i.setCondition(bool(false));i.getThenBranch().add(repeat(7,move()));p.getStatements().add(0,i);
        assertFalse(new SolutionEvaluator(catalog).evaluate(context("LOOPS",p)).structuralCorrectness().requiredConceptUsed());
    }
    @Test void actualTracePathsAreNestedAndParentRestored(){
        var p=program(repeat(2,F.createTurnLeft(),F.createTurnRight()));var c=context("LOOPS",p);
        assertTrue(c.execution.trace().stream().anyMatch(e->"statements[0].body[1]".equals(e.statementPath())&&e.type().equals("TURN_RIGHT")));
        assertEquals(2,c.events(p.getStatements().getFirst(),"LOOP_ITERATION").size());
        assertNull(c.execution.trace().getLast().statementPath());
    }
    @Test void laterActivationWithIterationsIsNotNeverExecuted(){
        var d=variable("n");var inner=F.createRepeat();inner.setCount(ref(d));inner.getBody().add(F.createTurnLeft());
        var outer=repeat(2,inner,assign(d,integer(1)));var c=context("LOOPS",program(d,outer));
        assertTrue(DETECTORS.get("LOOP_NEVER_EXECUTES").detect(c).isEmpty());
    }
    @Test void repetitionThresholdComesFromConfiguration(){
        var c=context("LOOPS",program(move(),move()));assertTrue(DETECTORS.get("REPETITIVE_SEQUENCE_WITHOUT_LOOP").detect(c).isEmpty());
        var p=c.config.parameters();var params=new Parameters(2,p.minimumLoopIterations(),p.expectedIterationCount(),p.declarationOrdinal(),p.variableType(),p.expectedInitialValue(),p.expectedFinalValue(),p.requireDynamicCondition(),p.requireBothBranches());
        var cfg=new LevelEvaluationConfig(c.config.levelId(),c.config.requiredConcept(),c.config.requiredConstructs(),c.config.blockingPatternIds(),c.config.referenceActions(),params);
        assertFalse(DETECTORS.get("REPETITIVE_SEQUENCE_WITHOUT_LOOP").detect(new EvaluationContext(c.program,c.execution,cfg)).isEmpty());
    }
    @Test void evaluatorDeterminismAndNoMutation(){
        var p=manual();var c=context("LOOPS",p);var before=new ProgramFingerprintService(p).branch(p.getStatements());
        var evaluator=new SolutionEvaluator(catalog);assertEquals(evaluator.evaluate(c),evaluator.evaluate(context("LOOPS",p)));
        assertEquals(before,new ProgramFingerprintService(p).branch(p.getStatements()));
        assertEquals("REPETITIVE_SEQUENCE_WITHOUT_LOOP",evaluator.evaluate(c).patterns().getFirst().id());
        assertTrue(c.execution.success());assertFalse(evaluator.evaluate(c).activityPassed());
        assertTrue(new SolutionEvaluator(catalog).evaluate(context("LOOPS",program(repeat(7,move())))).activityPassed());
    }
}
