package com.project.adaptation.manager;

import java.util.*;
import com.project.adaptation.domain.*;
import com.project.adaptation.domain.RuleEngineResult.*;
import com.project.adaptation.manager.DecisionTypes.*;
import com.project.mde.adaptation.*;
import org.springframework.stereotype.Service;

@Service
public final class AdaptationConflictResolver {
    private final ActionApplicabilityService applicability;
    public AdaptationConflictResolver(ActionApplicabilityService applicability) {this.applicability=applicability;}
    private record Ranked(RuleMatch match,int specificity,int severity,int order) {}
    private record Accepted(ActionDto action,Ranked owner) {}
    private static final List<String> SEVERITIES=List.of("NONE","INFO","WARNING","ERROR");
    public SemanticDecision resolve(AdaptationRuleSet rules,RuleEngineResult eca,RuleEvaluationContext context,
        Resources resources,AdaptationParametersConfig.Values parameters,Map<String,String> patternSeverity,String rulesHash) {
        var ranked=new ArrayList<Ranked>();
        for(var m:eca.matches()) {
            var rule=rules.getRules().stream().filter(r->r.getId().equals(m.ruleId())).findFirst().orElseThrow();
            ranked.add(new Ranked(m,specificity(rule.getCondition()),severity(rule.getCondition(),context,patternSeverity),rules.getRules().indexOf(rule)));
        }
        ranked.sort(Comparator.<Ranked>comparingInt(r->r.match().priority()).reversed()
            .thenComparing(Comparator.comparingInt(Ranked::specificity).reversed())
            .thenComparing(Comparator.comparingInt(Ranked::severity).reversed()).thenComparingInt(Ranked::order).thenComparing(r->r.match().ruleId()));
        var accepted=new ArrayList<Accepted>();var audits=new ArrayList<RuleAudit>();var actionAudits=new ArrayList<ActionAudit>();
        String primary=null;var contributors=new ArrayList<String>();var codes=new LinkedHashSet<String>();
        for(var r:ranked) {
            boolean used=false;String rejection="ACTION_NOT_APPLICABLE";
            for(var c:r.match().actions()) {
                var check=applicability.check(c,context,resources,parameters);String reason=check.rejection();String status="DISCARDED";
                if(reason==null) {
                    var duplicate=accepted.stream().anyMatch(a->a.action().equals(check.action()));
                    var conflict=accepted.stream().filter(a->ActionApplicabilityService.incompatible(a.action(),check.action())).findFirst();
                    if(duplicate) {reason="DUPLICATE_ACTION";status="DUPLICATE";used=true;}
                    else if(conflict.isPresent()) reason=conflictReason(conflict.get().owner(),r);
                    else {accepted.add(new Accepted(check.action(),r));reason="ACTION_SELECTED";status="SELECTED";used=true;}
                }
                if(!status.equals("SELECTED")) {rejection=reason;codes.add(reason);}
                actionAudits.add(new ActionAudit(c.ruleId(),c.actionIndex(),check.action(),status,reason));
            }
            String status,reason;
            if(used && primary==null) {primary=r.match().ruleId();status="SELECTED";reason="HIGHEST_RANKED_APPLICABLE";}
            else if(used) {contributors.add(r.match().ruleId());status="CONTRIBUTING";reason="COMPATIBLE_ADDITIONAL_RULE";}
            else {status="DISCARDED";reason=rejection;}
            codes.add(reason);audits.add(new RuleAudit(r.match().ruleId(),r.match().version(),r.match().priority(),r.specificity(),
                SEVERITIES.get(r.severity()),r.order(),status,reason,r.match().evidence()));
        }
        for(var rule:rules.getRules()) if(audits.stream().noneMatch(a->a.ruleId().equals(rule.getId()))) {
            String ignored=eca.ignoredRules().stream().filter(i->i.ruleId().equals(rule.getId())).map(IgnoredRule::reason).findFirst().orElse("NO_MATCH");
            audits.add(new RuleAudit(rule.getId(),rule.getVersion(),rule.getPriority(),specificity(rule.getCondition()),"NONE",rules.getRules().indexOf(rule),"IGNORED",ignored,List.of()));
        }
        audits.sort(Comparator.comparingInt(RuleAudit::sourceOrder));
        if(primary==null)codes.add(eca.rulesMatched()==0?"NO_RULE_MATCHED":"NO_APPLICABLE_ACTION");
        var evidence=new ArrayList<String>();
        for(var r:ranked) {evidence.add(r.match().ruleId()+": priority="+r.match().priority()+", specificity="+r.specificity()+", severity="+SEVERITIES.get(r.severity()));
            for(var e:r.match().evidence())evidence.add(e.attribute()+" "+e.operator()+" "+e.expected()+"; observed="+e.observed()+"; satisfied="+e.satisfied());}
        for(var a:actionAudits)evidence.add(a.ruleId()+": "+a.action()+" => "+a.status()+" ("+a.reasonCode()+")");
        String reason=primary==null?(eca.rulesMatched()==0?"NO_RULE_MATCHED":"NO_APPLICABLE_ACTION"):
            "Selected "+primary+" by priority, specificity, pattern severity and source order among applicable compatible actions.";
        var explanation=new Explanation(primary,reason,List.copyOf(codes),evidence);
        return new SemanticDecision(rules.getVersion(),parameters.version(),eca.rulesEvaluated(),eca.matches().stream().map(RuleMatch::ruleId).toList(),
            primary,contributors,audits.stream().filter(a->a.status().equals("DISCARDED")).toList(),accepted.stream().map(Accepted::action).toList(),
            explanation,audits,actionAudits,CanonicalHashes.hash(new Snapshot(context,resources)),rulesHash,CanonicalHashes.hash(parameters),false,"NONE");
    }
    private static String conflictReason(Ranked winner,Ranked loser) {
        if(winner.match().priority()!=loser.match().priority())return "LOWER_PRIORITY_CONFLICT";
        if(winner.specificity()!=loser.specificity())return "LESS_SPECIFIC_CONFLICT";
        if(winner.severity()!=loser.severity())return "LOWER_PEDAGOGICAL_SEVERITY";
        return "INCOMPATIBLE_ACTION_SOURCE_ORDER";
    }
    public static int specificity(Condition c) {
        if(c instanceof ComparisonCondition)return 1;
        if(c instanceof NotCondition n)return specificity(n.getOperand());
        if(c instanceof LogicalCondition l)return specificity(l.getLeft())+specificity(l.getRight());
        throw new IllegalArgumentException("Unknown condition");
    }
    private static int severity(Condition c,RuleEvaluationContext ctx,Map<String,String> catalog) {
        if(c instanceof ComparisonCondition p && p.getAttribute().equals("detectedPatterns") && p.getValue() instanceof StringValue v && ctx.detectedPatterns().contains(v.getValue()))
            return SEVERITIES.indexOf(catalog.getOrDefault(v.getValue(),"NONE"));
        if(c instanceof LogicalCondition l)return Math.max(severity(l.getLeft(),ctx,catalog),severity(l.getRight(),ctx,catalog));
        if(c instanceof NotCondition n)return severity(n.getOperand(),ctx,catalog);
        return 0;
    }
    public static AdaptationDecision toModel(SemanticDecision result) {
        var f=AdaptationFactory.eINSTANCE;var decision=f.createAdaptationDecision();decision.setRuleId(result.selectedRule());
        for(var a:result.actions()) {var action=f.createAction();action.setType(a.type());
            if(a.hintLevel()!=null || a.feedbackStyle()!=null) {var p=f.createAdaptationParameters();if(a.hintLevel()!=null)p.setHintLevel(a.hintLevel());if(a.feedbackStyle()!=null)p.setFeedbackStyle(a.feedbackStyle());action.setParameters(p);}
            decision.getActions().add(action);
        }
        var explanation=f.createAdaptationExplanation();explanation.setRuleId(result.selectedRule());explanation.setReason(result.explanation().reason());
        explanation.getEvidence().addAll(result.explanation().evidence());decision.setExplanation(explanation);
        if(org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(decision).getSeverity()>=4)throw new IllegalStateException("Invalid decision EMF");
        return decision;
    }
    /** API/persistence projection reads the formal EMF decision; audit-only fields remain metadata. */
    public static SemanticDecision project(AdaptationDecision model,SemanticDecision audit) {
        var actions=new ArrayList<ActionDto>();
        for(int i=0;i<model.getActions().size();i++) {
            var a=model.getActions().get(i);var p=a.getParameters();
            actions.add(new ActionDto(a.getType(),p!=null&&p.isSetHintLevel()?p.getHintLevel():null,
                p!=null&&p.isSetFeedbackStyle()?p.getFeedbackStyle():null,audit.actions().get(i).targetConceptId()));
        }
        var e=model.getExplanation();var explanation=new Explanation(e.getRuleId(),e.getReason(),audit.explanation().reasonCodes(),e.getEvidence());
        return new SemanticDecision(audit.rulesetVersion(),audit.parametersVersion(),audit.rulesEvaluated(),audit.rulesMatched(),model.getRuleId(),
            audit.contributingRules(),audit.discardedRules(),actions,explanation,audit.ruleAudit(),audit.actionAudit(),audit.contextHash(),audit.rulesetHash(),audit.parametersHash(),false,"NONE");
    }

}
