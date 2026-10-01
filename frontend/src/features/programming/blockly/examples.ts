import * as Blockly from 'blockly/core';
import type { ProgramDto, StatementDto, ExpressionDto } from '../dto/program';
import sequence from '../../../../../contracts/programming/v1/sequence.json';
import variables from '../../../../../contracts/programming/v1/variables.json';
import conditionals from '../../../../../contracts/programming/v1/conditionals.json';
import loops from '../../../../../contracts/programming/v1/loops.json';

export const examples = { sequence, variables, conditionals, loops } as Record<string, ProgramDto>;
type State = Blockly.serialization.blocks.State;
const connection = (block: State | undefined) => block ? { block } : {};
function expr(e: ExpressionDto): State {
  switch (e.kind) {
    case 'literal': return { type: e.valueType === 'INTEGER' ? 'mdedu_integer_literal' : 'mdedu_boolean_literal', fields: { VALUE: e.value } };
    case 'variableReference': return { type: 'mdedu_variable_reference', fields: { DECLARATION: e.declarationId } };
    case 'sensorExpression': return { type: 'mdedu_sensor', fields: { SENSOR: e.sensor } };
    case 'comparison': return { type: 'mdedu_comparison', fields: { OPERATOR: e.operator }, inputs: { LEFT: connection(expr(e.left)), RIGHT: connection(expr(e.right)) } };
    case 'booleanExpression': return { type: 'mdedu_boolean_expression', fields: { OPERATOR: e.operator }, inputs: {
      LEFT: connection(expr(e.left)), ...(e.right ? { RIGHT: connection(expr(e.right)) } : {}),
    } };
  }
}
function stmt(s: StatementDto): State {
  switch (s.kind) {
    case 'move': return { type: 'mdedu_move' };
    case 'turnLeft': return { type: 'mdedu_turn_left' };
    case 'turnRight': return { type: 'mdedu_turn_right' };
    case 'variableDeclaration': return { type: 'mdedu_variable_declaration', id: s.declarationId, fields: { NAME: s.name, TYPE: s.valueType }, inputs: s.initialValue ? { INITIAL: connection(expr(s.initialValue)) } : {} };
    case 'assignment': return { type: 'mdedu_assignment', fields: { DECLARATION: s.targetDeclarationId }, inputs: { VALUE: connection(expr(s.value)) } };
    case 'repeat': return { type: 'mdedu_repeat', inputs: { COUNT: connection(expr(s.count)), BODY: connection(chain(s.body)) } };
    case 'while': return { type: 'mdedu_while', inputs: { CONDITION: connection(expr(s.condition)), BODY: connection(chain(s.body)) } };
    case 'if': return { type: 'mdedu_if', inputs: { CONDITION: connection(expr(s.condition)), THEN: connection(chain(s.thenBranch)) } };
    case 'ifElse': return { type: 'mdedu_if_else', inputs: { CONDITION: connection(expr(s.condition)), THEN: connection(chain(s.thenBranch)), ELSE: connection(chain(s.elseBranch)) } };
  }
}
function chain(statements: StatementDto[]): State | undefined {
  let next: State | undefined;
  for (const s of [...statements].reverse()) next = { ...stmt(s), ...(next ? { next: { block: next } } : {}) };
  return next;
}
/** Example loading only, not the canonical model or a code generator. */
export function loadExample(workspace: Blockly.Workspace, dto: ProgramDto) {
  Blockly.serialization.workspaces.load({ blocks: { languageVersion: 0, blocks: [
    { type: 'mdedu_start', x: 30, y: 30, next: connection(chain(dto.statements)) },
  ] } }, workspace);
}
