package com.project.execution.domain;

import com.project.execution.domain.GameTypes.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/** Per-execution mutable world. Never shared between requests. */
final class GridWorld {
    final WorldConfig config;
    Position position;
    Direction direction;
    boolean hasKey;
    final List<Position> keys;
    final LinkedHashMap<Position, Boolean> doors = new LinkedHashMap<>();
    GridWorld(WorldConfig config) {
        this.config = config; position = config.playerPosition(); direction = config.playerDirection();
        keys = new ArrayList<>(config.keys()); config.doors().forEach(p -> doors.put(p, false));
    }
    boolean inBounds(Position p) { return p.x() >= 0 && p.y() >= 0 && p.x() < config.width() && p.y() < config.height(); }
    boolean clear(Direction d) {
        var p = d.ahead(position);
        return inBounds(p) && !config.obstacles().contains(p)
            && (!doors.containsKey(p) || doors.get(p) || hasKey);
    }
    boolean atGoal() { return position.equals(config.goalPosition()); }
    State snapshot(List<Variable> variables) {
        return new State(position, direction, hasKey, List.copyOf(keys),
            doors.entrySet().stream().map(e -> new Door(e.getKey(), e.getValue())).toList(), List.copyOf(variables), atGoal());
    }
}
