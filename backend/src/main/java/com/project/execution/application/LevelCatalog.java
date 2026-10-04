package com.project.execution.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.execution.domain.GameTypes.*;
import java.io.IOException;
import java.util.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class LevelCatalog {
    private final Catalog catalog;
    private final Catalog legacy;
    public LevelCatalog(ObjectMapper mapper) throws IOException {
        try (var input = new ClassPathResource("game/challenges.v1.json").getInputStream()) {
            catalog = mapper.readValue(input, Catalog.class);
        }
        try (var input = new ClassPathResource("game/levels.v1.json").getInputStream()) { legacy = mapper.readValue(input, Catalog.class); }
        validate(catalog);
    }
    public Catalog all() { return catalog; }
    public Optional<Level> find(String id) { return java.util.stream.Stream.concat(catalog.levels().stream(),legacy.levels().stream()).filter(l -> l.id().equals(id)).findFirst(); }
    public static void validate(Catalog catalog) {
        if (catalog.version() != 1 || catalog.levels().size() != 18) throw new IllegalArgumentException("Expected eighteen challenges");
        var expected=Map.of("SEQUENCES",4L,"VARIABLES",4L,"CONDITIONALS",5L,"LOOPS",5L);
        var counts=catalog.levels().stream().collect(java.util.stream.Collectors.groupingBy(Level::conceptId,java.util.stream.Collectors.counting()));
        if(!expected.equals(counts))throw new IllegalArgumentException("Expected four concepts with 4/4/5/5 challenges");
        var ids = new HashSet<String>();
        for (var l : catalog.levels()) {
            if (!ids.add(l.id())) throw new IllegalArgumentException("Duplicate level");
            if(l.title()==null || l.title().isBlank() || l.learningObjective()==null || l.learningObjective().isBlank() || l.difficulty()==null || l.criteria()==null ||
                l.order()<1 || l.order()>expected.get(l.conceptId()) || catalog.levels().stream().filter(other->other.conceptId().equals(l.conceptId())&&other.order()==l.order()).count()!=1)
                throw new IllegalArgumentException("Invalid challenge metadata/order");
            var previous=catalog.levels().stream().filter(other->other.conceptId().equals(l.conceptId())&&other.order()==l.order()-1).map(Level::id).toList();
            if(!previous.equals(l.prerequisiteLevelIds()))throw new IllegalArgumentException("Invalid challenge predecessor");
            var w = l.worldConfig();
            if (w.width() < 1 || w.height() < 1 || w.width() > 20 || w.height() > 20 || w.playerDirection() == null)
                throw new IllegalArgumentException("Invalid world dimensions/direction");
            var cells = new ArrayList<Position>(); cells.add(w.playerPosition()); cells.add(w.goalPosition());
            cells.addAll(w.obstacles()); cells.addAll(w.keys()); cells.addAll(w.doors());
            if (cells.stream().anyMatch(p -> p == null || p.x() < 0 || p.y() < 0 || p.x() >= w.width() || p.y() >= w.height()))
                throw new IllegalArgumentException("Invalid cell");
            if (new HashSet<>(cells).size() != cells.size()) throw new IllegalArgumentException("Overlapping world features");
            if (l.allowedBlockGroups().isEmpty()) throw new IllegalArgumentException("Missing toolbox");
        }
        var resolved = new HashSet<String>();
        while (resolved.size() < ids.size()) {
            int before = resolved.size();
            for (var l : catalog.levels()) if (resolved.containsAll(l.prerequisiteLevelIds())) resolved.add(l.id());
            if (before == resolved.size()) throw new IllegalArgumentException("Unknown prerequisite or cycle");
        }
    }
}
