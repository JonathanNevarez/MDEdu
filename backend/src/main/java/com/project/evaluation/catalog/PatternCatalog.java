package com.project.evaluation.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.evaluation.detectors.DetectorRegistry;
import com.project.execution.application.LevelCatalog;
import java.io.IOException;
import java.util.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class PatternCatalog {
    private final PatternCatalogData patterns;
    private final LevelCatalog game;
    public com.project.execution.domain.GameTypes.Level challenge(String id){return game.find(id).orElseThrow();}
    private final LevelEvaluationData levels;
    private final LevelEvaluationData legacy;
    public PatternCatalog(ObjectMapper mapper, LevelCatalog game) throws IOException {
        this.game=game;
        try(var in=new ClassPathResource("evaluation/patterns.v1.json").getInputStream()){patterns=mapper.readValue(in,PatternCatalogData.class);}
        try(var in=new ClassPathResource("evaluation/challenges-evaluation.v1.json").getInputStream()){levels=mapper.readValue(in,LevelEvaluationData.class);}
        try(var in=new ClassPathResource("evaluation/levels-evaluation.v1.json").getInputStream()){legacy=mapper.readValue(in,LevelEvaluationData.class);}
        validate(patterns,levels,game);
    }
    public List<PatternDefinition> patterns(){return patterns.patterns();}
    public LevelEvaluationConfig level(String id){return java.util.stream.Stream.concat(levels.levels().stream(),legacy.levels().stream()).filter(l->l.levelId().equals(id)).findFirst().orElseThrow();}
    public static void validate(PatternCatalogData patterns,LevelEvaluationData levels,LevelCatalog game) {
        var registry=new DetectorRegistry();var ids=new HashSet<String>();
        if(patterns.version()!=1 || patterns.patterns().size()!=15)throw new IllegalArgumentException("Expected 15 patterns V1");
        for(var p:patterns.patterns()) {
            if(!ids.add(p.id()) || !registry.ids().contains(p.id()) || !registry.ids().contains(p.detector()) || !p.id().equals(p.detector()) || p.version()!=1 || p.concept()==null || p.severity()==null ||
                p.pedagogicalMeaning()==null || p.pedagogicalMeaning().isBlank() || p.recommendedAction()==null || p.recommendedAction().isBlank() || p.defaultHintLevel()<1 || p.defaultHintLevel()>3)
                throw new IllegalArgumentException("Invalid pattern metadata");
        }
        if(!ids.equals(registry.ids()) || levels.version()!=1 || levels.levels().size()!=game.all().levels().size())throw new IllegalArgumentException("Invalid evaluation catalog");
        var levelIds=new HashSet<String>();
        var constructs=Set.of("Move","TurnLeft","TurnRight","VariableDeclaration","Assignment","If","IfElse","Repeat","While");
        for(var l:levels.levels()) {
            if(!levelIds.add(l.levelId()) || game.find(l.levelId()).isEmpty() || l.requiredConcept()==null || !l.requiredConcept().name().equals(game.find(l.levelId()).orElseThrow().conceptId()) ||
                l.requiredConstructs().isEmpty() || !constructs.containsAll(l.requiredConstructs()) || !ids.containsAll(l.blockingPatternIds()))throw new IllegalArgumentException("Invalid level evaluation references");
            if(l.blockingPatternIds().stream().anyMatch(id->patterns.patterns().stream().noneMatch(p->p.id().equals(id)&&p.concept()==l.requiredConcept())))throw new IllegalArgumentException("Cross-concept blocking pattern");
            var p=l.parameters();
            if(p==null || p.minimumManualRepetitions()<2 || p.minimumLoopIterations()<1 || p.expectedIterationCount()<1 || p.declarationOrdinal()<1 || !"INTEGER".equals(p.variableType()))throw new IllegalArgumentException("Invalid evaluation parameters");
            if(l.requiredConcept()==Concept.SEQUENCES && (l.referenceActions().isEmpty() || !Set.of("MOVE","TURN_LEFT","TURN_RIGHT").containsAll(l.referenceActions())))throw new IllegalArgumentException("Invalid reference plan");
        }
        if(!levelIds.equals(new HashSet<>(game.all().levels().stream().map(l->l.id()).toList())))throw new IllegalArgumentException("Missing level evaluation");
    }
}
