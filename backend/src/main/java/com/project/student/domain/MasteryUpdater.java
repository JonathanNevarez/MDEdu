package com.project.student.domain;
import java.math.BigDecimal;
import java.util.*;
public final class MasteryUpdater {
    public record State(double score,int attempts,int successes,int failures,int consecutiveFailures,double averageTime,int hints) {}
    public record Change(State state,double before,double delta,double after,List<String> reasons) {}
    public static Change update(State old,boolean passed,int hints,long time,Collection<String> patterns,List<List<String>> previous,StudentModelPolicy.Values policy) {
        if(hints<0 || time<0 || !Double.isFinite(old.score()) || old.score()<0 || old.score()>1) throw new IllegalArgumentException("Invalid learning input");
        var reasons=new ArrayList<String>();
        double delta=passed?(hints==0?policy.successWithoutHintDelta():policy.successWithHintDelta()):policy.failureDelta();
        reasons.add(passed?(hints==0?"ACTIVITY_SUCCESS_NO_HINT":"ACTIVITY_SUCCESS_WITH_HINT"):"ACTIVITY_FAILURE");
        boolean repeated=previous.stream().limit(policy.recentErrorWindow()).flatMap(Collection::stream).anyMatch(patterns::contains);
        if(repeated) { delta=add(delta,policy.repeatedErrorDelta()); reasons.add("REPEATED_ERROR_PATTERN"); }
        double raw=add(old.score(),delta),after=Math.max(0,Math.min(1,raw));
        if(raw!=after) reasons.add("MASTERY_CLAMPED");
        int count=Math.addExact(old.attempts(),1);
        var next=new State(after,count,Math.addExact(old.successes(),passed?1:0),Math.addExact(old.failures(),passed?0:1),
            passed?0:Math.addExact(old.consecutiveFailures(),1),old.averageTime()+(time-old.averageTime())/count,Math.addExact(old.hints(),hints));
        return new Change(next,old.score(),delta,after,List.copyOf(reasons));
    }
    public static List<String> recentPatterns(List<List<String>> newestFirst,StudentModelPolicy.Values policy) {
        return newestFirst.stream().limit(policy.recentErrorWindow()).flatMap(Collection::stream).distinct().limit(policy.maxRecentErrorPatterns()).toList();
    }
    private static double add(double a,double b) { return BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue(); }
}
