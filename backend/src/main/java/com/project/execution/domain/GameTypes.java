package com.project.execution.domain;

import java.util.List;

/** Immutable API/domain values. Coordinates start at the top-left, y increases south. */
public final class GameTypes {
    private GameTypes() {}
    public record Position(int x, int y) {}
    public enum Direction {
        NORTH, EAST, SOUTH, WEST;
        public Direction left() { return values()[(ordinal() + 3) % 4]; }
        public Direction right() { return values()[(ordinal() + 1) % 4]; }
        public Position ahead(Position p) {
            return switch (this) {
                case NORTH -> new Position(p.x(), p.y() - 1);
                case EAST -> new Position(p.x() + 1, p.y());
                case SOUTH -> new Position(p.x(), p.y() + 1);
                case WEST -> new Position(p.x() - 1, p.y());
            };
        }
    }
    public record WorldConfig(int width, int height, Position playerPosition,
        Direction playerDirection, Position goalPosition, List<Position> obstacles,
        List<Position> keys, List<Position> doors) {}
    public record Level(String id, String concept, String title, String description, int order,
        List<String> prerequisiteLevelIds, WorldConfig worldConfig, List<String> allowedBlockGroups) {}
    public record Catalog(int version, List<Level> levels) {}
    public record Door(Position position, boolean open) {}
    public record Variable(String id, String name, String type, Object value) {}
    public record State(Position playerPosition, Direction playerDirection, boolean hasKey,
        List<Position> keys, List<Door> doors, List<Variable> variables, boolean atGoal) {}
    public record Event(int index, String type, String detail, State state) {}
    public record Failure(String code, String message) {}
    public record Result(boolean success, String status, int steps, State finalState,
        List<Event> trace, List<Failure> errors) {}
}
