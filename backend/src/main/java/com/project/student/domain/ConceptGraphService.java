package com.project.student.domain;
import java.util.*;
import org.springframework.stereotype.Service;
@Service
public class ConceptGraphService {
    public record Node(String id,double threshold,Set<String> prerequisites) {}
    public record Achievement(int successes,double mastery) {}
    public Set<String> unlocked(List<Node> graph,Map<String,Achievement> state,Set<String> historical) {
        var nodes=new LinkedHashMap<String,Node>();
        for(var n:graph) if(nodes.put(n.id(),n)!=null || !Double.isFinite(n.threshold()) || n.threshold()<0 || n.threshold()>1)
            throw new IllegalArgumentException("Invalid concept graph");
        for(var n:graph) visit(n.id(),nodes,new HashSet<>(),new HashSet<>());
        var result=new LinkedHashSet<String>();
        for(var n:graph) if(historical.contains(n.id()) || n.prerequisites().stream().allMatch(id->{
            var a=state.get(id);return a!=null && a.successes()>0 && a.mastery()>=nodes.get(id).threshold();
        })) result.add(n.id());
        return Collections.unmodifiableSet(result);
    }
    private void visit(String id,Map<String,Node> nodes,Set<String> active,Set<String> done) {
        if(done.contains(id))return;
        if(!nodes.containsKey(id) || !active.add(id))throw new IllegalArgumentException("Missing prerequisite or graph cycle");
        for(String pre:nodes.get(id).prerequisites())visit(pre,nodes,active,done);
        active.remove(id);done.add(id);
    }
}
