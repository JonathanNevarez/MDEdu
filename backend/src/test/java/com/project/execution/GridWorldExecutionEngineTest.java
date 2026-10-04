package com.project.execution;

import com.project.execution.domain.*;
import com.project.execution.domain.GameTypes.*;
import com.project.execution.application.LevelCatalog;
import com.project.mde.programming.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;

class GridWorldExecutionEngineTest {
    static final ProgrammingFactory F = ProgrammingFactory.eINSTANCE;
    static final GridWorldExecutionEngine ENGINE = new GridWorldExecutionEngine();
    static WorldConfig world(Direction direction) { return new WorldConfig(5,5,new Position(2,2),direction,new Position(4,4),List.of(),List.of(),List.of()); }
    static Program program(Statement... statements) { var p=F.createProgram();p.setName("test");p.getStatements().addAll(List.of(statements));return p; }
    static Literal integer(int n) { return literal(ValueType.INTEGER,Integer.toString(n)); }
    static Literal literal(ValueType type,String value) {var l=F.createLiteral();l.setType(type);l.setValue(value);return l;}
    static Literal bool(boolean b) {return literal(ValueType.BOOLEAN,Boolean.toString(b));}
    static SensorExpression sensor(SensorKind kind) {var s=F.createSensorExpression();s.setSensor(kind);return s;}
    static VariableDeclaration declaration(String name,ValueType type,Expression value) {var d=F.createVariableDeclaration();d.setName(name);d.setType(type);d.setInitialValue(value);return d;}
    static Result run(Statement... s) {return ENGINE.execute(program(s),world(Direction.EAST),200);}
    static Object value(Expression expression) {return run(declaration("result",ValueType.BOOLEAN,expression)).finalState().variables().getFirst().value();}
    @ParameterizedTest @EnumSource(Direction.class) void movementAndAllTurns(Direction direction) {
        assertEquals(direction.ahead(new Position(2,2)),ENGINE.execute(program(F.createMove()),world(direction),200).finalState().playerPosition());
        assertEquals(Direction.values()[(direction.ordinal()+3)%4],ENGINE.execute(program(F.createTurnLeft()),world(direction),200).finalState().playerDirection());
        assertEquals(Direction.values()[(direction.ordinal()+1)%4],ENGINE.execute(program(F.createTurnRight()),world(direction),200).finalState().playerDirection());
    }
    @Test void boundariesAndObstacles() {
        var edge=new WorldConfig(2,2,new Position(0,0),Direction.NORTH,new Position(1,1),List.of(),List.of(),List.of());
        assertEquals("OUT_OF_BOUNDS",ENGINE.execute(program(F.createMove()),edge,200).errors().getFirst().code());
        var blocked=new WorldConfig(3,3,new Position(0,0),Direction.EAST,new Position(2,2),List.of(new Position(1,0)),List.of(),List.of());
        assertEquals("OBSTACLE",ENGINE.execute(program(F.createMove()),blocked,200).errors().getFirst().code());
    }
    @Test void keyDoorAndImmutableTrace() {
        var w=new WorldConfig(4,1,new Position(0,0),Direction.EAST,new Position(3,0),List.of(),List.of(new Position(1,0)),List.of(new Position(2,0)));
        var p=program(F.createMove(),F.createMove(),F.createMove());var result=ENGINE.execute(p,w,200);
        assertTrue(result.success());assertTrue(result.finalState().hasKey());assertTrue(result.finalState().doors().getFirst().open());assertTrue(result.finalState().keys().isEmpty());
        assertEquals(List.of("PROGRAM_STARTED","MOVE","ITEM_COLLECTED","DOOR_OPENED","MOVE","MOVE","GOAL_REACHED","PROGRAM_FINISHED"),result.trace().stream().map(Event::type).toList());
        for(int i=0;i<result.trace().size();i++)assertEquals(i,result.trace().get(i).index());
        assertFalse(result.trace().getFirst().state().hasKey());assertFalse(result.trace().getFirst().state().doors().getFirst().open());
        assertEquals(result,ENGINE.execute(p,w,200));
        var noKey=new WorldConfig(3,1,new Position(0,0),Direction.EAST,new Position(2,0),List.of(),List.of(),List.of(new Position(1,0)));
        assertEquals("DOOR_LOCKED",ENGINE.execute(program(F.createMove()),noKey,200).errors().getFirst().code());
    }
    @ParameterizedTest @EnumSource(SensorKind.class) void allSensors(SensorKind kind) {
        boolean expected=Set.of(SensorKind.FRONT_CLEAR,SensorKind.LEFT_CLEAR,SensorKind.RIGHT_CLEAR).contains(kind);
        assertEquals(expected,value(sensor(kind)));
    }
    @Test void sensorDoorAndKeySemantics() {
        var w=new WorldConfig(4,1,new Position(0,0),Direction.EAST,new Position(3,0),List.of(),List.of(new Position(1,0)),List.of(new Position(2,0)));
        var p=program(F.createMove(),declaration("key",ValueType.BOOLEAN,sensor(SensorKind.HAS_KEY)),
            declaration("on",ValueType.BOOLEAN,sensor(SensorKind.ON_KEY)),declaration("door",ValueType.BOOLEAN,sensor(SensorKind.DOOR_AHEAD)),
            declaration("clear",ValueType.BOOLEAN,sensor(SensorKind.FRONT_CLEAR)),declaration("closed",ValueType.BOOLEAN,sensor(SensorKind.DOOR_OPEN)),
            F.createMove(),F.createMove(),F.createTurnLeft(),F.createTurnLeft(),declaration("open",ValueType.BOOLEAN,sensor(SensorKind.DOOR_OPEN)),
            declaration("goal",ValueType.BOOLEAN,sensor(SensorKind.AT_GOAL)));
        assertEquals(List.of(true,false,true,true,false,true,true),ENGINE.execute(p,w,200).finalState().variables().stream().map(Variable::value).toList());
        var locked=new WorldConfig(2,1,new Position(0,0),Direction.EAST,new Position(1,0),List.of(),List.of(),List.of(new Position(1,0)));
        assertEquals(false,ENGINE.execute(program(declaration("x",ValueType.BOOLEAN,sensor(SensorKind.FRONT_CLEAR))),locked,200).finalState().variables().getFirst().value());
        var onKey=new WorldConfig(2,1,new Position(0,0),Direction.EAST,new Position(1,0),List.of(),List.of(new Position(0,0)),List.of());
        assertEquals(true,ENGINE.execute(program(declaration("x",ValueType.BOOLEAN,sensor(SensorKind.ON_KEY))),onKey,200).finalState().variables().getFirst().value());
    }
    @Test void clearanceSensorsRespectWallsAndObstacles() {
        var w=new WorldConfig(2,2,new Position(0,0),Direction.NORTH,new Position(1,1),List.of(new Position(1,0)),List.of(),List.of());
        var p=program(declaration("front",ValueType.BOOLEAN,sensor(SensorKind.FRONT_CLEAR)),
            declaration("left",ValueType.BOOLEAN,sensor(SensorKind.LEFT_CLEAR)),declaration("right",ValueType.BOOLEAN,sensor(SensorKind.RIGHT_CLEAR)));
        assertEquals(List.of(false,false,false),ENGINE.execute(p,w,200).finalState().variables().stream().map(Variable::value).toList());
    }
    @Test void goalMustBeFinalAndFailureDoesNotComplete() {
        var w=new WorldConfig(3,1,new Position(0,0),Direction.EAST,new Position(1,0),List.of(),List.of(),List.of());
        var result=ENGINE.execute(program(F.createMove(),F.createMove()),w,200);
        assertFalse(result.success());assertEquals("COMPLETED",result.status());
        assertTrue(result.trace().stream().anyMatch(e->e.type().equals("GOAL_REACHED")));
        result=ENGINE.execute(program(F.createMove(),F.createTurnLeft(),F.createMove()),w,200);
        assertTrue(result.finalState().atGoal());assertFalse(result.success());assertEquals("RUNTIME_ERROR",result.status());
    }
    @Test void variablesDefaultsReferencesAndIdentity() {
        var a=declaration("same",ValueType.INTEGER,null);var b=declaration("same",ValueType.INTEGER,integer(9));
        var flag=declaration("flag",ValueType.BOOLEAN,null);var ref=F.createVariableReference();ref.setDeclaration(b);
        var assignment=F.createAssignment();assignment.setTarget(a);assignment.setValue(ref);
        var result=run(a,b,flag,assignment);
        assertEquals(List.of(9,9,false),result.finalState().variables().stream().map(Variable::value).toList());
        assertEquals(0,result.trace().get(1).state().variables().getFirst().value());
        assertEquals("VARIABLE_ASSIGNED",result.trace().get(result.trace().size()-2).type());
    }
    @Test void wrongTypesAndForwardReferenceAreControlled() {
        var d=declaration("x",ValueType.INTEGER,null);var a=F.createAssignment();a.setTarget(d);a.setValue(bool(true));
        assertEquals("TYPE_MISMATCH",run(d,a).errors().getFirst().code());
        assertEquals("UNDECLARED_VARIABLE",run(a,d).errors().getFirst().code());
        assertEquals("TYPE_MISMATCH",run(declaration("x",ValueType.INTEGER,bool(true))).errors().getFirst().code());
    }
    @ParameterizedTest @ValueSource(strings={"abc","1.0","2147483648"," 1","\"; throw new Error(\"NO\"); //"})
    void malformedIntegerIsData(String text) {assertEquals("INVALID_LITERAL",run(declaration("x",ValueType.INTEGER,literal(ValueType.INTEGER,text))).errors().getFirst().code());}
    @Test void malformedBoolean() {assertEquals("INVALID_LITERAL",run(declaration("x",ValueType.BOOLEAN,literal(ValueType.BOOLEAN,"TRUE"))).errors().getFirst().code());}
    @ParameterizedTest @EnumSource(ComparisonOperator.class) void allComparisons(ComparisonOperator op) {
        var c=F.createComparison();c.setOperator(op);c.setLeft(integer(1));c.setRight(integer(2));
        assertEquals(Set.of(ComparisonOperator.NOT_EQUAL,ComparisonOperator.LESS_THAN,ComparisonOperator.LESS_OR_EQUAL).contains(op),value(c));
    }
    @Test void equalityTypesAndRelationalTypes() {
        var c=F.createComparison();c.setLeft(bool(true));c.setRight(bool(true));assertEquals(true,value(c));
        c.setOperator(ComparisonOperator.LESS_THAN);assertEquals("TYPE_MISMATCH",run(declaration("r",ValueType.BOOLEAN,c)).errors().getFirst().code());
        c.setOperator(ComparisonOperator.EQUAL);c.setRight(integer(1));assertEquals("TYPE_MISMATCH",run(declaration("r",ValueType.BOOLEAN,c)).errors().getFirst().code());
    }
    @ParameterizedTest @EnumSource(BooleanOperator.class) void booleanOperations(BooleanOperator op) {
        var b=F.createBooleanExpression();b.setOperator(op);b.setLeft(bool(true));if(op!=BooleanOperator.NOT)b.setRight(bool(false));
        assertEquals(op==BooleanOperator.OR,value(b));
    }
    @Test void booleanShortCircuit() {
        var b=F.createBooleanExpression();b.setOperator(BooleanOperator.AND);b.setLeft(bool(false));b.setRight(literal(ValueType.BOOLEAN,"invalid"));
        assertEquals(false,value(b));b.setOperator(BooleanOperator.OR);b.setLeft(bool(true));assertEquals(true,value(b));
        b.setLeft(integer(1));assertEquals("TYPE_MISMATCH",run(declaration("r",ValueType.BOOLEAN,b)).errors().getFirst().code());
    }
    @ParameterizedTest @ValueSource(booleans={true,false}) void ifAndIfElse(boolean condition) {
        var i=F.createIf();i.setCondition(bool(condition));i.getThenBranch().add(F.createMove());
        assertEquals(new Position(condition?3:2,2),run(i).finalState().playerPosition());
        var both=F.createIfElse();both.setCondition(bool(condition));both.getThenBranch().add(F.createTurnLeft());both.getElseBranch().add(F.createTurnRight());
        var result=run(both);assertEquals(condition?Direction.NORTH:Direction.SOUTH,result.finalState().playerDirection());
        assertEquals(1,result.trace().stream().filter(e->e.type().equals("CONDITION_EVALUATED")).count());
    }
    @ParameterizedTest @ValueSource(ints={0,1,4}) void repeatCounts(int count) {
        var r=F.createRepeat();r.setCount(integer(count));r.getBody().add(F.createTurnLeft());
        var result=run(r);assertEquals(count,result.trace().stream().filter(e->e.type().equals("TURN_LEFT")).count());
    }
    @Test void negativeAndWrongRepeat() {
        var r=F.createRepeat();r.setCount(integer(-1));assertEquals("NEGATIVE_REPEAT",run(r).errors().getFirst().code());
        r.setCount(bool(true));assertEquals("TYPE_MISMATCH",run(r).errors().getFirst().code());
    }
    @Test void whileFalseMultipleAndInfinite() {
        var w=F.createWhile();w.setCondition(bool(false));w.getBody().add(F.createMove());assertEquals(2,run(w).finalState().playerPosition().x());
        w.setCondition(sensor(SensorKind.FRONT_CLEAR));var result=run(w);assertEquals(4,result.finalState().playerPosition().x());
        w.setCondition(bool(true));w.getBody().clear();result=ENGINE.execute(program(w),world(Direction.EAST),17);
        assertEquals("STEP_LIMIT_EXCEEDED",result.status());assertEquals(17,result.steps());assertFalse(result.success());
        assertTrue(result.trace().size()<30);
        w.setCondition(integer(1));assertEquals("TYPE_MISMATCH",run(w).errors().getFirst().code());
    }
    @Test void hugeEmptyRepeatAndBudgetBoundaries() {
        var r=F.createRepeat();r.setCount(integer(Integer.MAX_VALUE));
        assertEquals("STEP_LIMIT_EXCEEDED",ENGINE.execute(program(r),world(Direction.EAST),10).status());
        assertEquals("COMPLETED",ENGINE.execute(program(F.createMove()),world(Direction.EAST),1).status());
        assertEquals(1,ENGINE.execute(program(F.createMove(),F.createMove()),world(Direction.EAST),1).steps());
    }
    @Test void catalogEighteenWorldsAndFourConcepts() throws Exception {
        var catalog=new LevelCatalog(new ObjectMapper()).all();assertEquals(18,catalog.levels().size());
        assertEquals(List.of("SEQUENCES","VARIABLES","CONDITIONALS","LOOPS"),catalog.levels().stream().map(Level::conceptId).distinct().toList());
        var seq=program(F.createMove(),F.createMove(),F.createTurnRight(),F.createMove(),F.createMove());
        assertTrue(ENGINE.execute(seq,catalog.levels().getFirst().worldConfig(),200).success());
        var levels=new ArrayList<>(catalog.levels());var first=levels.getFirst();
        levels.set(0,new Level(first.id(),first.concept(),first.title(),first.description(),1,List.of("LOOPS"),first.worldConfig(),first.allowedBlockGroups()));
        assertThrows(IllegalArgumentException.class,()->LevelCatalog.validate(new Catalog(1,levels)));
    }
}
