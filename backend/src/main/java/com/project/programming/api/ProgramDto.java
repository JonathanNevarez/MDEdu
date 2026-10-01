package com.project.programming.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;

/** V1 transport contract. No EMF objects or visual workspace properties. */
public record ProgramDto(Integer contractVersion, String name, List<StatementDto> statements) {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "kind")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = MoveDto.class, name = "move"),
        @JsonSubTypes.Type(value = TurnLeftDto.class, name = "turnLeft"),
        @JsonSubTypes.Type(value = TurnRightDto.class, name = "turnRight"),
        @JsonSubTypes.Type(value = DeclarationDto.class, name = "variableDeclaration"),
        @JsonSubTypes.Type(value = AssignmentDto.class, name = "assignment"),
        @JsonSubTypes.Type(value = RepeatDto.class, name = "repeat"),
        @JsonSubTypes.Type(value = WhileDto.class, name = "while"),
        @JsonSubTypes.Type(value = IfDto.class, name = "if"),
        @JsonSubTypes.Type(value = IfElseDto.class, name = "ifElse")
    })
    public sealed interface StatementDto permits MoveDto, TurnLeftDto, TurnRightDto,
            DeclarationDto, AssignmentDto, RepeatDto, WhileDto, IfDto, IfElseDto {}
    public record MoveDto() implements StatementDto {}
    public record TurnLeftDto() implements StatementDto {}
    public record TurnRightDto() implements StatementDto {}
    public record DeclarationDto(String declarationId, String name, String valueType,
                                 ExpressionDto initialValue) implements StatementDto {}
    public record AssignmentDto(String targetDeclarationId, ExpressionDto value) implements StatementDto {}
    public record RepeatDto(ExpressionDto count, List<StatementDto> body) implements StatementDto {}
    public record WhileDto(ExpressionDto condition, List<StatementDto> body) implements StatementDto {}
    public record IfDto(ExpressionDto condition, List<StatementDto> thenBranch) implements StatementDto {}
    public record IfElseDto(ExpressionDto condition, List<StatementDto> thenBranch,
                            List<StatementDto> elseBranch) implements StatementDto {}

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "kind")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = LiteralDto.class, name = "literal"),
        @JsonSubTypes.Type(value = ReferenceDto.class, name = "variableReference"),
        @JsonSubTypes.Type(value = ComparisonDto.class, name = "comparison"),
        @JsonSubTypes.Type(value = BooleanDto.class, name = "booleanExpression"),
        @JsonSubTypes.Type(value = SensorDto.class, name = "sensorExpression")
    })
    public sealed interface ExpressionDto permits LiteralDto, ReferenceDto, ComparisonDto, BooleanDto, SensorDto {}
    public record LiteralDto(String valueType, String value) implements ExpressionDto {}
    public record ReferenceDto(String declarationId) implements ExpressionDto {}
    public record ComparisonDto(String operator, ExpressionDto left, ExpressionDto right) implements ExpressionDto {}
    public record BooleanDto(String operator, ExpressionDto left, ExpressionDto right) implements ExpressionDto {}
    public record SensorDto(String sensor) implements ExpressionDto {}
}
