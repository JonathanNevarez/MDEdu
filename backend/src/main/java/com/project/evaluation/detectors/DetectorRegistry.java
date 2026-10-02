package com.project.evaluation.detectors;
import java.util.*;
public final class DetectorRegistry {
    private final Map<String,PatternDetector> detectors;
    public DetectorRegistry() {
        var map=new LinkedHashMap<String,PatternDetector>();
        for(String id:List.of("WRONG_ORDER","UNNECESSARY_INSTRUCTION","MISSING_ACTION"))map.put(id,c->SequenceDetectors.detect(c,id));
        map.put("UNUSED_VARIABLE",VariableDetectors::unused);map.put("REDUNDANT_REASSIGNMENT",VariableDetectors::redundant);map.put("INCORRECT_UPDATE",VariableDetectors::update);
        map.put("MISSING_CONDITION",ConditionalDetectors::missing);map.put("IDENTICAL_BRANCHES",ConditionalDetectors::identical);map.put("CONSTANT_CONDITION",ConditionalDetectors::constant);map.put("MISSING_REQUIRED_BRANCH",ConditionalDetectors::branch);
        map.put("REPETITIVE_SEQUENCE_WITHOUT_LOOP",LoopDetectors::manual);map.put("LOOP_NEVER_EXECUTES",LoopDetectors::never);map.put("INCORRECT_REPETITION_COUNT",LoopDetectors::count);map.put("UNNECESSARY_LOOP",LoopDetectors::unnecessary);map.put("POSSIBLE_INFINITE_LOOP",LoopDetectors::infinite);
        detectors=Collections.unmodifiableMap(map);
    }
    public Set<String> ids(){return detectors.keySet();}
    public PatternDetector get(String id){var detector=detectors.get(id);if(detector==null)throw new IllegalArgumentException("Unknown detector: "+id);return detector;}
}
