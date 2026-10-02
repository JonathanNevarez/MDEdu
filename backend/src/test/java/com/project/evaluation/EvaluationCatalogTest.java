package com.project.evaluation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.execution.application.LevelCatalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
class EvaluationCatalogTest {
    final ObjectMapper json=new ObjectMapper();
    ObjectNode resource(String name)throws Exception {try(var in=getClass().getResourceAsStream("/evaluation/"+name+".v1.json")){return (ObjectNode)json.readTree(in);}}
    void validate(ObjectNode patterns,ObjectNode levels)throws Exception {PatternCatalog.validate(json.treeToValue(patterns,PatternCatalogData.class),json.treeToValue(levels,LevelEvaluationData.class),new LevelCatalog(json));}
    @Test void exactCatalogValidates()throws Exception {validate(resource("patterns"),resource("levels-evaluation"));assertEquals(15,resource("patterns").path("patterns").size());assertEquals(4,resource("levels-evaluation").path("levels").size());}
    @ParameterizedTest @ValueSource(strings={"id","version","concept","severity","detector","pedagogicalMeaning","recommendedAction","defaultHintLevel"})
    void invalidMetadataRejected(String field)throws Exception {
        var patterns=resource("patterns");var p=(ObjectNode)patterns.path("patterns").get(0);
        if(field.equals("version")||field.equals("defaultHintLevel"))p.put(field,0);else p.put(field,"");
        assertThrows(Exception.class,()->validate(patterns,resource("levels-evaluation")));
    }
    @Test void duplicatePatternsAndUnknownConfigRejected()throws Exception {
        var patterns=resource("patterns");((ObjectNode)patterns.path("patterns").get(1)).put("id","WRONG_ORDER");
        assertThrows(Exception.class,()->validate(patterns,resource("levels-evaluation")));
        var levels=resource("levels-evaluation");((ObjectNode)levels.path("levels").get(0)).put("levelId","UNKNOWN");
        assertThrows(Exception.class,()->validate(resource("patterns"),levels));
    }
    @Test void badConceptBlockingAndParametersRejected()throws Exception {
        var levels=resource("levels-evaluation");var l=(ObjectNode)levels.path("levels").get(0);l.put("requiredConcept","LOOPS");
        assertThrows(Exception.class,()->validate(resource("patterns"),levels));
        l.put("requiredConcept","SEQUENCES");l.putArray("blockingPatternIds").add("UNKNOWN");
        assertThrows(Exception.class,()->validate(resource("patterns"),levels));
        l.putArray("blockingPatternIds").add("WRONG_ORDER");((ObjectNode)l.path("parameters")).put("minimumManualRepetitions",0);
        assertThrows(Exception.class,()->validate(resource("patterns"),levels));
    }
}
