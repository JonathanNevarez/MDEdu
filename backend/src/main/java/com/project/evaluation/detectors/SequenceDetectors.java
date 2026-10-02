package com.project.evaluation.detectors;
import com.project.evaluation.domain.*;
import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.mde.programming.*;
import java.util.*;
final class SequenceDetectors {
    static List<Evidence> detect(EvaluationContext c, String id) {
        var observed=new ArrayList<String>();
        for(var s:c.program.getStatements()) {
            if(s instanceof Move) observed.add("MOVE"); else if(s instanceof TurnLeft) observed.add("TURN_LEFT");
            else if(s instanceof TurnRight) observed.add("TURN_RIGHT"); else return List.of();
        }
        var expected=c.config.referenceActions(); if(expected.isEmpty())return List.of();
        boolean found=switch(id) {
            case "MISSING_ACTION" -> observed.size()<expected.size() && subsequence(observed,expected);
            case "UNNECESSARY_INSTRUCTION" -> observed.size()>expected.size() && subsequence(expected,observed);
            case "WRONG_ORDER" -> observed.size()==expected.size() && !observed.equals(expected) && counts(observed).equals(counts(expected));
            default -> false;
        };
        return found?List.of(c.evidence(null,observed.toString(),expected.toString())):List.of();
    }
    static boolean subsequence(List<String> a,List<String> b) {int i=0;for(String value:b)if(i<a.size() && a.get(i).equals(value))i++;return i==a.size();}
    static Map<String,Integer> counts(List<String> values) {var result=new TreeMap<String,Integer>();for(String s:values)result.merge(s,1,Integer::sum);return result;}
}
