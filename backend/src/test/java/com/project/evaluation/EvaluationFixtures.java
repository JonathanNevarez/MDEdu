package com.project.evaluation;
import com.project.mde.programming.*;
import java.util.*;
final class EvaluationFixtures {
    static final ProgrammingFactory F=ProgrammingFactory.eINSTANCE;
    static Program program(Statement... s){var p=F.createProgram();p.setName("test");p.getStatements().addAll(List.of(s));return p;}
    static Literal integer(int value){var l=F.createLiteral();l.setValue(Integer.toString(value));return l;}
    static Literal bool(boolean value){var l=F.createLiteral();l.setType(ValueType.BOOLEAN);l.setValue(Boolean.toString(value));return l;}
    static SensorExpression sensor(SensorKind kind){var s=F.createSensorExpression();s.setSensor(kind);return s;}
    static Move move(){return F.createMove();}
    static Repeat repeat(int count,Statement...body){var r=F.createRepeat();r.setCount(integer(count));r.getBody().addAll(List.of(body));return r;}
    static While loop(Expression condition,Statement...body){var w=F.createWhile();w.setCondition(condition);w.getBody().addAll(List.of(body));return w;}
    static VariableDeclaration variable(String name){var v=F.createVariableDeclaration();v.setName(name);v.setInitialValue(integer(0));return v;}
    static Assignment assign(VariableDeclaration d,Expression value){var a=F.createAssignment();a.setTarget(d);a.setValue(value);return a;}
    static VariableReference ref(VariableDeclaration d){var r=F.createVariableReference();r.setDeclaration(d);return r;}
    static IfElse conditional(Expression condition,boolean identical){var i=F.createIfElse();i.setCondition(condition);i.getThenBranch().add(move());i.getElseBranch().add(identical?move():F.createTurnLeft());return i;}
    static Program sequence(){return program(move(),move(),F.createTurnRight(),move(),move());}
    static Program manual(){return program(move(),move(),move(),move(),move(),move(),move());}
    static Program updated(int value){var d=variable("cualquier nombre");return program(d,assign(d,integer(value)),move(),move(),move(),move());}
    static Program conditionSolution(){var p=program(move());var i=conditional(sensor(SensorKind.HAS_KEY),false);i.getThenBranch().addAll(List.of(move(),move(),move()));p.getStatements().add(i);return p;}
    static Program fixture(String id,boolean positive){
        return switch(id){
            case "WRONG_ORDER" -> positive?program(move(),F.createTurnRight(),move(),move(),move()):sequence();
            case "UNNECESSARY_INSTRUCTION" -> {var p=sequence();if(positive)p.getStatements().add(F.createTurnLeft());yield p;}
            case "MISSING_ACTION" -> positive?program(move(),move(),F.createTurnRight(),move()):sequence();
            case "UNUSED_VARIABLE" -> positive?program(variable("libre")):updated(1);
            case "REDUNDANT_REASSIGNMENT" -> {var d=variable("x");yield positive?program(d,assign(d,integer(9)),assign(d,integer(1))):program(d,assign(d,integer(1)));}
            case "INCORRECT_UPDATE" -> updated(positive?2:1);
            case "MISSING_CONDITION" -> positive?program(move(),move(),move(),move(),move()):conditionSolution();
            case "IDENTICAL_BRANCHES" -> program(conditional(sensor(SensorKind.HAS_KEY),positive));
            case "CONSTANT_CONDITION" -> program(conditional(positive?bool(true):sensor(SensorKind.FRONT_CLEAR),false));
            case "MISSING_REQUIRED_BRANCH" -> {if(!positive)yield conditionSolution();var i=F.createIf();i.setCondition(sensor(SensorKind.HAS_KEY));i.getThenBranch().add(move());yield program(i);}
            case "REPETITIVE_SEQUENCE_WITHOUT_LOOP" -> positive?manual():program(repeat(7,move()));
            case "LOOP_NEVER_EXECUTES" -> program(repeat(positive?0:7,move()));
            case "INCORRECT_REPETITION_COUNT" -> program(repeat(positive?6:7,move()));
            case "UNNECESSARY_LOOP" -> program(repeat(positive?1:7,move()));
            case "POSSIBLE_INFINITE_LOOP" -> positive?program(loop(bool(true))):program(repeat(Integer.MAX_VALUE));
            default -> throw new IllegalArgumentException(id);
        };
    }
}
