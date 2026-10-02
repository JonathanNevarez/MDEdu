package com.project.adaptation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptation.domain.*;
import com.project.adaptation.engine.*;
import com.project.adaptation.rules.*;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.execution.application.LevelCatalog;
import com.project.mde.adaptation.*;
import com.project.mde.adaptation.dsl.HeadlessRules;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;

class EcaRuleEngineTest {
    static AdaptationRuleLoader loader;
    static EcaRuleEngine engine;
    static HeadlessRules parser;
    @BeforeAll static void setup() throws Exception {
        var mapper=new ObjectMapper();loader=new AdaptationRuleLoader(new PatternCatalog(mapper,new LevelCatalog(mapper)));
        engine=new EcaRuleEngine(loader.catalog());parser=new HeadlessRules(loader.catalog());
    }
    static RuleEvaluationContext context(int failures, String score, int hints, long time, boolean repeated, boolean functional, boolean used) {
        return new RuleEvaluationContext("LOOPS",new BigDecimal(score),10,7,3,failures,hints,new BigDecimal("1000.50"),time,
            functional && used,functional,used,List.of("REPETITIVE_SEQUENCE_WITHOUT_LOOP"),repeated);
    }
    static RuleEvaluationContext neutral() { return context(0,"0.50",0,1000,false,false,true); }
    static AdaptationRuleSet parse(String condition,String actions) throws Exception {
        return parseSource("ruleset \"Test\" version 1\nrule Test version 1 enabled true on ATTEMPT_EVALUATED when "+condition+" then "+actions+" priority 50");
    }
    static AdaptationRuleSet parseSource(String source) throws Exception {
        return parser.parse(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)),"test.adapt");
    }
    static boolean matches(String condition,RuleEvaluationContext context) throws Exception {
        return engine.evaluate(parse(condition,"action REPEAT_ACTIVITY"),context,EventType.ATTEMPT_EVALUATED).rulesMatched()==1;
    }
    @ParameterizedTest @ValueSource(strings={"ReforzarCiclos","ErrorRepetido","DominioAlto","PistasExcesivas","TiempoAlto","FuncionalSinConcepto"})
    void sixSeedsPositiveAndNegative(String id) {
        RuleEvaluationContext yes=switch(id) {
            case "ReforzarCiclos" -> context(3,"0.50",0,1000,false,false,true);
            case "ErrorRepetido" -> context(0,"0.50",0,1000,true,false,true);
            case "DominioAlto" -> context(0,"0.80",0,1000,false,false,true);
            case "PistasExcesivas" -> context(0,"0.50",5,1000,false,false,true);
            case "TiempoAlto" -> context(0,"0.50",0,300001,false,false,true);
            case "FuncionalSinConcepto" -> context(0,"0.50",0,1000,false,true,false);
            default -> throw new AssertionError(id);
        };
        var positive=engine.evaluate(loader.snapshot(),yes,EventType.ATTEMPT_EVALUATED);
        var negative=engine.evaluate(loader.snapshot(),neutral(),EventType.ATTEMPT_EVALUATED);
        assertTrue(positive.matches().stream().anyMatch(m->m.ruleId().equals(id)));
        assertTrue(negative.matches().stream().noneMatch(m->m.ruleId().equals(id)));
        assertEquals(6,positive.rulesEvaluated());
    }
    @Test void criticalDslToCanonicalModelToCandidate() {
        var model=loader.snapshot();assertSame(AdaptationPackage.eINSTANCE,model.eClass().getEPackage());
        var yes=engine.evaluate(model,context(3,"0",0,0,false,false,true),EventType.ATTEMPT_EVALUATED);
        var match=yes.matches().stream().filter(m->m.ruleId().equals("ReforzarCiclos")).findFirst().orElseThrow();
        assertEquals(80,match.priority());assertEquals(ActionType.SHOW_HINT,match.actions().getFirst().type());
        assertEquals(HintLevel.CONCEPTUAL,match.actions().getFirst().hintLevel());assertEquals(2,match.evidence().size());
        var no=engine.evaluate(model,context(2,"0",0,0,false,false,true),EventType.ATTEMPT_EVALUATED);
        assertEquals(6,no.rulesEvaluated());assertTrue(no.matches().stream().noneMatch(m->m.ruleId().equals("ReforzarCiclos")));
    }
    @Test void disabledAndWrongEventAreIgnored() throws Exception {
        var r=parseSource("ruleset \"Disabled\" version 1 rule Disabled version 1 enabled false on ATTEMPT_EVALUATED when masteryScore >= 0 then action SHOW_CODE_VIEW priority 50");
        var disabled=engine.evaluate(r,neutral(),EventType.ATTEMPT_EVALUATED);
        assertEquals(0,disabled.rulesEvaluated());assertEquals(0,disabled.rulesMatched());assertEquals("DISABLED",disabled.ignoredRules().getFirst().reason());
        r.getRules().getFirst().setEnabled(true);
        var other=engine.evaluate(r,neutral(),"UNRELATED_EVENT");
        assertEquals(0,other.rulesMatched());assertEquals("EVENT_MISMATCH",other.ignoredRules().getFirst().reason());
    }
    @ParameterizedTest @CsvSource({"==,10,true","!=,9,true",">,9,true",">=,10,true","<,11,true","<=,10,true","==,9,false","!=,10,false",">,10,false",">=,11,false","<,10,false","<=,9,false"})
    void integerComparisons(String op,String value,boolean expected) throws Exception { assertEquals(expected,matches("attemptCount "+op+" "+value,neutral())); }
    @ParameterizedTest @CsvSource({"==,0.500,true","!=,0.49,true",">,0.49,true",">=,0.50,true","<,0.51,true","<=,0.50,true","==,0.49,false","!=,0.50,false",">,0.50,false",">=,0.51,false","<,0.50,false","<=,0.49,false"})
    void decimalComparisons(String op,String value,boolean expected) throws Exception { assertEquals(expected,matches("masteryScore "+op+" "+value,neutral())); }
    @Test void andOrNotAndPrecedence() throws Exception {
        assertTrue(matches("activityPassed == false and requiredConceptUsed == true",neutral()));
        assertFalse(matches("activityPassed == true and requiredConceptUsed == true",neutral()));
        assertTrue(matches("activityPassed == true or requiredConceptUsed == true",neutral()));
        assertFalse(matches("activityPassed == true or requiredConceptUsed == false",neutral()));
        assertTrue(matches("not (activityPassed == true or requiredConceptUsed == false)",neutral()));
        assertFalse(matches("not requiredConceptUsed == true",neutral()));
        assertTrue(matches("activityPassed == false or functionalPassed == true and requiredConceptUsed == false",neutral()));
        assertFalse(matches("(activityPassed == false or functionalPassed == true) and requiredConceptUsed == false",neutral()));
    }
    @Test void stringsContainsAndEveryAttribute() throws Exception {
        assertTrue(matches("concept != \"SEQUENCES\"",neutral()));
        assertTrue(matches("detectedPatterns contains \"REPETITIVE_SEQUENCE_WITHOUT_LOOP\"",neutral()));
        var without=new RuleEvaluationContext("LOOPS",BigDecimal.ZERO,0,0,0,0,0,BigDecimal.ZERO,0,false,false,false,List.of(),false);
        assertFalse(matches("detectedPatterns contains \"REPETITIVE_SEQUENCE_WITHOUT_LOOP\"",without));
        assertTrue(matches("successCount == 7 and failureCount == 3 and averageResolutionTime == 1000.500",neutral()));
        assertTrue(matches("masteryScore > 0 and repeatedErrorPattern != true",neutral()));
    }
    @Test void deterministicHundredTimesAndSourceOrderNoMutation() throws Exception {
        var context=context(3,"0.80",5,300001,true,true,false);var model=loader.snapshot();
        var before=org.eclipse.emf.ecore.util.EcoreUtil.copy(model);var mapper=new ObjectMapper();
        var result=engine.evaluate(model,context,EventType.ATTEMPT_EVALUATED);var expected=mapper.writeValueAsString(result);
        for(int i=0;i<100;i++) assertEquals(expected,mapper.writeValueAsString(engine.evaluate(model,context,EventType.ATTEMPT_EVALUATED)));
        assertEquals(List.of("ReforzarCiclos","ErrorRepetido","DominioAlto","PistasExcesivas","TiempoAlto","FuncionalSinConcepto"),result.matches().stream().map(m->m.ruleId()).toList());
        assertTrue(org.eclipse.emf.ecore.util.EcoreUtil.equals(before,model));
        java.nio.file.Files.createDirectories(java.nio.file.Path.of("target/adaptation-evidence"));
        java.nio.file.Files.writeString(java.nio.file.Path.of("target/adaptation-evidence/deterministic-result.json"),expected);
    }
    @Test void reportsConflictWithoutChoosingWinner() throws Exception {
        var model=parseSource("""
            ruleset "Conflict" version 1
            rule Up version 1 enabled true on ATTEMPT_EVALUATED when masteryScore >= 0 then action INCREASE_DIFFICULTY priority 0
            rule Down version 1 enabled true on ATTEMPT_EVALUATED when masteryScore >= 0 then action DECREASE_DIFFICULTY priority 100
            """);
        var result=engine.evaluate(model,neutral(),EventType.ATTEMPT_EVALUATED);
        assertEquals(2,result.rulesMatched());assertEquals(1,result.conflictsDetected().size());
        assertEquals(List.of("Up","Down"),result.conflictsDetected().getFirst().ruleIds());
        assertEquals(List.of(ActionType.INCREASE_DIFFICULTY,ActionType.DECREASE_DIFFICULTY),result.matches().stream().flatMap(m->m.actions().stream()).map(a->a.type()).toList());
    }
    @Test void allActionsAndTypedParameters() throws Exception {
        for(var action:ActionType.values()) {
            String params=switch(action) { case SHOW_HINT,CHANGE_HINT_LEVEL -> " level DIRECT"; case CHANGE_FEEDBACK_STYLE -> " style CONCISE"; default -> ""; };
            assertEquals(action,engine.evaluate(parse("masteryScore >= 0","action "+action.getLiteral()+params),neutral(),EventType.ATTEMPT_EVALUATED).matches().getFirst().actions().getFirst().type());
        }
    }
    @Test void malformedModelCannotBypassValidationAndLoaderIsIsolated() {
        var r=loader.snapshot();r.getRules().getFirst().setPriority(101);
        assertThrows(IllegalArgumentException.class,()->engine.evaluate(r,neutral(),EventType.ATTEMPT_EVALUATED));
        assertEquals(80,loader.snapshot().getRules().getFirst().getPriority());
    }
}
