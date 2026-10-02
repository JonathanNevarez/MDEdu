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
    public LevelCatalog(ObjectMapper mapper) throws IOException {
        try (var input = new ClassPathResource("game/levels.v1.json").getInputStream()) {
            catalog = mapper.readValue(input, Catalog.class);
        }
        validate(catalog);
    }
    public Catalog all() { return catalog; }
    public Optional<Level> find(String id) { return catalog.levels().stream().filter(l -> l.id().equals(id)).findFirst(); }
    public static void validate(Catalog catalog) {
        if (catalog.version() != 1 || catalog.levels().size() != 4) throw new IllegalArgumentException("Expected four V1 levels");
        var ids = new HashSet<String>();
        for (var l : catalog.levels()) {
            if (!ids.add(l.id())) throw new IllegalArgumentException("Duplicate level");
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
