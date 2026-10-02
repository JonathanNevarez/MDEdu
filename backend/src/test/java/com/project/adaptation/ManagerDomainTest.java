package com.project.adaptation;

import com.project.adaptation.manager.*;
import com.project.adaptation.manager.DecisionTypes.*;
import com.project.adaptation.domain.*;
import com.project.adaptation.application.ContextProjectionService;
import com.project.mde.adaptation.*;
import com.project.mde.context.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ManagerDomainTest {
    static AdaptationParametersConfig.Values parameters;
    static AdaptationConflictResolver resolver=new AdaptationConflictResolver(new ActionApplicabilityService());
    static final Resources RESOURCES=new Resources("LOOPS",List.of(),List.of());
    static final String ERROR="REPETITIVE_SEQUENCE_WITHOUT_LOOP",WARNING="UNNECESSARY_LOOP";
    @BeforeAll static void setup() throws Exception {EcaRuleEngineTest.setup();parameters=new AdaptationParametersConfig(new ObjectMapper()).values();}
    static String rule(String id,int priority,String condition,String action) {
        return "rule "+id+" version 1 enabled true on ATTEMPT_EVALUATED when "+condition+" then "+action+" priority "+priority+"\n";
    }
    static SemanticDecision resolve(String body,RuleEvaluationContext context,Resources resources,AdaptationParametersConfig.Values p) throws Exception {
        var rules=EcaRuleEngineTest.parseSource("ruleset \"Manager\" version 1\n"+body);
        var eca=EcaRuleEngineTest.engine.evaluate(rules,context,EventType.ATTEMPT_EVALUATED);
        return resolver.resolve(rules,eca,context,resources,p,Map.of(ERROR,"ERROR",WARNING,"WARNING"),CanonicalHashes.bytes(body));
    }
    static SemanticDecision resolve(String body) throws Exception {return resolve(body,EcaRuleEngineTest.neutral(),RESOURCES,parameters);}
    static String increase="action INCREASE_DIFFICULTY",decrease="action DECREASE_DIFFICULTY";
    @Test void priorityWinsAndExplainsDiscard() throws Exception {
        var result=resolve(rule("Low",10,"masteryScore >= 0",increase)+rule("High",80,"masteryScore >= 0",decrease));
        assertEquals("High",result.selectedRule());assertEquals("LOWER_PRIORITY_CONFLICT",result.discardedRules().getFirst().reasonCode());
    }
    @Test void specificityCountsAtomsIncludingNotAndOr() throws Exception {
        var result=resolve(rule("Simple",50,"masteryScore >= 0",increase)+rule("Specific",50,"not masteryScore < 0 and (hintCount == 0 or activityPassed == true)",decrease));
        assertEquals("Specific",result.selectedRule());assertEquals(3,result.ruleAudit().get(1).specificity());
        assertEquals("LESS_SPECIFIC_CONFLICT",result.discardedRules().getFirst().reasonCode());
    }
    @Test void patternSeverityWins() throws Exception {
        var n=EcaRuleEngineTest.neutral();var context=new RuleEvaluationContext(n.concept(),n.masteryScore(),10,7,3,0,0,n.averageResolutionTime(),1000,false,false,true,List.of(ERROR,WARNING),false);
        var result=resolve(rule("Warning",50,"detectedPatterns contains \""+WARNING+"\"",increase)+rule("Error",50,"detectedPatterns contains \""+ERROR+"\"",decrease),context,RESOURCES,parameters);
        assertEquals("Error",result.selectedRule());assertEquals("LOWER_PEDAGOGICAL_SEVERITY",result.discardedRules().getFirst().reasonCode());
    }
    @Test void finalTieUsesSourceOrderNotLexicalId() throws Exception {
        var result=resolve(rule("Z",50,"masteryScore >= 0",increase)+rule("A",50,"masteryScore >= 0",decrease));
        assertEquals("Z",result.selectedRule());assertEquals("INCOMPATIBLE_ACTION_SOURCE_ORDER",result.discardedRules().getFirst().reasonCode());
    }
    @Test void compatibleRulesAndDuplicateParameters() throws Exception {
        var result=resolve(rule("Hint",90,"masteryScore >= 0","action SHOW_HINT level CONCEPTUAL")+
            rule("Repeat",80,"masteryScore >= 0","action REPEAT_ACTIVITY")+rule("SameHint",70,"masteryScore >= 0","action SHOW_HINT level CONCEPTUAL"));
        assertEquals("Hint",result.selectedRule());assertEquals(List.of("Repeat","SameHint"),result.contributingRules());assertEquals(2,result.actions().size());
        assertTrue(result.actionAudit().stream().anyMatch(a->a.reasonCode().equals("DUPLICATE_ACTION")));
    }
    @Test void differentHintParametersConflictRatherThanDeduplicate() throws Exception {
        var result=resolve(rule("Direct",90,"masteryScore >= 0","action SHOW_HINT level DIRECT")+rule("Conceptual",80,"masteryScore >= 0","action SHOW_HINT level CONCEPTUAL"));
        assertEquals(HintLevel.DIRECT,result.actions().getFirst().hintLevel());assertEquals("LOWER_PRIORITY_CONFLICT",result.actionAudit().get(1).reasonCode());
        assertFalse(result.actionAudit().stream().anyMatch(a->a.reasonCode().equals("DUPLICATE_ACTION")));
    }
    @Test void unavailableReinforcementCannotSuppressApplicableRule() throws Exception {
        var result=resolve(rule("Unavailable",100,"masteryScore >= 0","action SELECT_REINFORCEMENT_ACTIVITY")+rule("Repeat",50,"masteryScore >= 0","action REPEAT_ACTIVITY"));
        assertEquals("Repeat",result.selectedRule());assertEquals("NOT_APPLICABLE_NO_REINFORCEMENT_ACTIVITY",result.discardedRules().getFirst().reasonCode());
    }
    static AdaptationParametersConfig.Values flags(boolean difficulty,boolean route) {
        return new AdaptationParametersConfig.Values(1,HintLevel.CONCEPTUAL,difficulty,route,AdaptationParametersConfig.FeedbackDetail.STANDARD,3,1,new BigDecimal("0.80"),5,3);
    }
    @Test void routeGatesAndNoSuccessor() throws Exception {
        var body=rule("Advance",60,"masteryScore >= 0.8","action ADVANCE_TO_NEXT_CONCEPT");
        var context=EcaRuleEngineTest.context(0,"0.8",0,1000,false,true,true);
        assertEquals("VARIABLES",resolve(body,context,new Resources("SEQUENCES",List.of("VARIABLES"),List.of()),parameters).actions().getFirst().targetConceptId());
        assertEquals("ACTION_DISABLED_BY_PARAMETERS",resolve(body,context,new Resources("SEQUENCES",List.of("VARIABLES"),List.of()),flags(true,false)).actionAudit().getFirst().reasonCode());
        assertEquals("NOT_APPLICABLE_NO_NEXT_CONCEPT",resolve(body,context,RESOURCES,parameters).actionAudit().getFirst().reasonCode());
    }
    @Test void difficultyAndCodeViewConflicts() throws Exception {
        var result=resolve(rule("Up",60,"masteryScore >= 0",increase),EcaRuleEngineTest.neutral(),RESOURCES,flags(false,true));
        assertTrue(result.actions().isEmpty());assertEquals("ACTION_DISABLED_BY_PARAMETERS",result.actionAudit().getFirst().reasonCode());
        var code=resolve(rule("Show",60,"masteryScore >= 0","action SHOW_CODE_VIEW")+rule("Hide",50,"masteryScore >= 0","action HIDE_CODE_VIEW"));
        assertEquals(1,code.actions().size());assertEquals(ActionType.SHOW_CODE_VIEW,code.actions().getFirst().type());
    }
    @Test void noMatchAndNoApplicableAreValidEmfDecisions() throws Exception {
        var result=resolve(rule("No",50,"masteryScore > 0.9",increase));
        assertNull(result.selectedRule());assertTrue(result.actions().isEmpty());assertEquals("NO_RULE_MATCHED",result.explanation().reason());
        roundTripDecision(result,"no-adaptation");
        var unavailable=resolve(rule("NoResource",50,"masteryScore >= 0","action SELECT_REINFORCEMENT_ACTIVITY"));
        assertEquals("NO_APPLICABLE_ACTION",unavailable.explanation().reason());roundTripDecision(unavailable,"no-applicable");
    }
    @Test void disabledRuleCannotBecomePrimary() throws Exception {
        var result=resolve(rule("Disabled",100,"masteryScore >= 0",increase).replace("enabled true","enabled false"));
        assertNull(result.selectedRule());assertEquals("DISABLED",result.ruleAudit().getFirst().reasonCode());
    }
    @Test void deterministicHundredEvaluationsAndHashes() throws Exception {
        String body=rule("First",80,"masteryScore >= 0",increase)+rule("Second",20,"masteryScore >= 0",decrease);
        var result=resolve(body);String expected=CanonicalHashes.json(result);
        for(int i=0;i<100;i++)assertEquals(expected,CanonicalHashes.json(resolve(body)));
        assertEquals(CanonicalHashes.hash(parameters),CanonicalHashes.hash(flags(true,true)));
        assertNotEquals(CanonicalHashes.hash(parameters),CanonicalHashes.hash(flags(false,true)));
        assertNotEquals(result.rulesetHash(),resolve(body.replace("priority 80","priority 81")).rulesetHash());
        assertNotEquals(result.contextHash(),resolve(body,EcaRuleEngineTest.context(3,"0.5",0,1000,false,false,true),RESOURCES,parameters).contextHash());
        roundTripDecision(result,"selected");
    }
    @Test void contextValidationAndTwoXmiRoundTrips() throws Exception {
        var projection=new ContextProjectionService();
        for(var entry:Map.of("context-three-failures",EcaRuleEngineTest.context(3,"0.00",0,1000,true,true,false),
                            "context-high-mastery",EcaRuleEngineTest.context(0,"0.80",0,1000,false,true,true)).entrySet()) {
            var model=projection.restore("student-fixture","LOOPS",entry.getValue());
            projection.validate(model,EcaRuleEngineTest.loader.catalog().concepts(),EcaRuleEngineTest.loader.catalog().patterns());
            Files.createDirectories(Path.of("target/context-snapshots"));var path=Path.of("target/context-snapshots",entry.getKey()+".context").toAbsolutePath();
            var resource=resources().createResource(URI.createFileURI(path.toString()));resource.getContents().add(model);resource.save(Map.of());var before=EcoreUtil.copy(model);resource.unload();
            var loaded=(ContextModel)resources().getResource(resource.getURI(),true).getContents().getFirst();
            projection.validate(loaded,EcaRuleEngineTest.loader.catalog().concepts(),EcaRuleEngineTest.loader.catalog().patterns());assertTrue(EcoreUtil.equals(before,loaded));
            assertEquals(CanonicalHashes.hash(ContextProjectionService.toRules(before)),CanonicalHashes.hash(ContextProjectionService.toRules(loaded)));
            loaded.setModelVersion(0);assertThrows(IllegalArgumentException.class,()->projection.validate(loaded,EcaRuleEngineTest.loader.catalog().concepts(),EcaRuleEngineTest.loader.catalog().patterns()));
        }
    }
    @Test void parametersRejectInvalidRanges() {
        assertThrows(IllegalArgumentException.class,()->new AdaptationParametersConfig.Values(0,HintLevel.CONCEPTUAL,true,true,AdaptationParametersConfig.FeedbackDetail.STANDARD,3,1,BigDecimal.ONE,5,3));
        assertThrows(IllegalArgumentException.class,()->new AdaptationParametersConfig.Values(1,HintLevel.CONCEPTUAL,true,true,AdaptationParametersConfig.FeedbackDetail.STANDARD,3,1,new BigDecimal("1.1"),5,3));
    }
    @Test void contextRejectsInvalidRangesIdsAndPatterns() {
        var projection=new ContextProjectionService();
        List<java.util.function.Consumer<ContextModel>> invalid=List.of(
            m->m.getStudentContext().setMasteryScore(new BigDecimal("1.01")),
            m->m.getStudentContext().setAttemptCount(-1),
            m->m.getStudentContext().setAverageResolutionTime(new BigDecimal("-1")),
            m->m.getStudentContext().setCurrentResolutionTime(-1),
            m->m.getStudentContext().setConceptId(""),m->m.getStudentContext().setActivityId(""),
            m->m.getStudentContext().getDetectedPatterns().add("INVENTED"));
        for(var mutation:invalid) {
            var model=projection.restore("fixture","LOOPS",EcaRuleEngineTest.neutral());mutation.accept(model);
            assertThrows(IllegalArgumentException.class,()->projection.validate(model,EcaRuleEngineTest.loader.catalog().concepts(),EcaRuleEngineTest.loader.catalog().patterns()));
        }
        var one=projection.restore("first-student","LOOPS",EcaRuleEngineTest.neutral());
        var two=projection.restore("other-student","LOOPS",EcaRuleEngineTest.neutral());two.getStudentContext().setMasteryScore(new BigDecimal("0.5000"));
        assertEquals(CanonicalHashes.hash(ContextProjectionService.toRules(one)),CanonicalHashes.hash(ContextProjectionService.toRules(two)));
    }
    @Test void contextMetamodelConforms() {
        var rs=new ResourceSetImpl();rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore",new org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl());
        var pkg=(org.eclipse.emf.ecore.EPackage)rs.getResource(URI.createFileURI(Path.of("../mde/com.project.mde.context.model/model/context.ecore").toAbsolutePath().toString()),true).getContents().getFirst();
        assertEquals(0,Diagnostician.INSTANCE.validate(pkg).getSeverity());assertEquals(4,pkg.getEClassifiers().size());
        assertEquals("https://mdedu.espoch.edu.ec/model/context/1.0",pkg.getNsURI());
        assertTrue(ContextPackage.Literals.CONTEXT_MODEL__STUDENT_CONTEXT.isContainment());
    }
    static void roundTripDecision(SemanticDecision result,String name) throws Exception {
        var decision=AdaptationConflictResolver.toModel(result);Files.createDirectories(Path.of("target/adaptation-decisions"));
        var resource=resources().createResource(URI.createFileURI(Path.of("target/adaptation-decisions",name+".adaptation").toAbsolutePath().toString()));
        resource.getContents().add(decision);resource.save(Map.of());var copy=EcoreUtil.copy(decision);resource.unload();
        var loaded=resources().getResource(resource.getURI(),true).getContents().getFirst();assertEquals(0,Diagnostician.INSTANCE.validate(loaded).getSeverity());assertTrue(EcoreUtil.equals(copy,loaded));
    }
    static ResourceSetImpl resources() {
        var rs=new ResourceSetImpl();rs.getPackageRegistry().put(ContextPackage.eNS_URI,ContextPackage.eINSTANCE);rs.getPackageRegistry().put(AdaptationPackage.eNS_URI,AdaptationPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("context",new XMIResourceFactoryImpl());rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("adaptation",new XMIResourceFactoryImpl());return rs;
    }
}
