package com.project.metaui;
import com.project.metaui.application.AdaptationInspectionService;
import com.project.adaptation.rules.AdaptationRuleLoader;
import com.project.adaptation.manager.*;
import com.project.evaluation.catalog.PatternCatalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class AdaptationInspectionTest {
 @Test void readsRuntimeRulesIncludingIsolatedChangedFixture() throws Exception {
  var real=new AdaptationRuleLoader(new PatternCatalog(new ObjectMapper(),new com.project.execution.application.LevelCatalog(new ObjectMapper())));
  var loader=mock(AdaptationRuleLoader.class);var fixture=real.snapshot();
  fixture.getRules().getFirst().setName("ISOLATED_RULE_FIXTURE");fixture.getRules().getFirst().setPriority(123);
  fixture.getRules().getFirst().setEnabled(false);
  when(loader.snapshot()).thenReturn(fixture);when(loader.hash()).thenReturn("fixture-hash");
  var service=new AdaptationInspectionService(loader,new AdaptationParametersConfig(new ObjectMapper()));
  var result=service.rules();assertEquals(fixture.getRules().size(),result.rules().size());assertEquals("fixture-hash",result.rulesetHash());
  var first=result.rules().getFirst();assertEquals("ISOLATED_RULE_FIXTURE",first.name());assertEquals(123,first.priority());assertFalse(first.enabled());
  assertEquals(fixture.getRules().getFirst().getActions().stream().map(a->a.getType()).toList(),first.actions().stream().map(a->a.type()).toList());
  assertTrue(first.conditionSummary().contains("consecutiveFailures >= 3"));assertTrue(first.conditionSummary().contains("AND"));
  assertNotEquals("ISOLATED_RULE_FIXTURE",real.snapshot().getRules().getFirst().getName());
 }
 @Test void changedRuntimeParametersAreNotHardcoded() throws Exception {
  var values=new AdaptationParametersConfig.Values(42,com.project.mde.adaptation.HintLevel.GUIDED,false,false,
   AdaptationParametersConfig.FeedbackDetail.CONCISE,7,8,new BigDecimal("0.71"),2,9);
  var config=mock(AdaptationParametersConfig.class);when(config.values()).thenReturn(values);
  var result=new AdaptationInspectionService(mock(AdaptationRuleLoader.class),config).parameters();
  assertEquals(values,result.values());assertEquals(42,result.parametersVersion());assertEquals(CanonicalHashes.hash(values),result.parametersHash());
 }
 @Test void emptyRuntimeIsAnError() {
  var loader=mock(AdaptationRuleLoader.class);when(loader.snapshot()).thenReturn(com.project.mde.adaptation.AdaptationFactory.eINSTANCE.createAdaptationRuleSet());
  assertThrows(IllegalStateException.class,()->new AdaptationInspectionService(loader,mock(AdaptationParametersConfig.class)).rules());
 }
}
