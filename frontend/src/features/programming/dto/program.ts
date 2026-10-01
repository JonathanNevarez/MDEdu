export const valueTypes = ['INTEGER', 'BOOLEAN'] as const;
export const comparisonOperators = ['EQUAL', 'NOT_EQUAL', 'LESS_THAN', 'LESS_OR_EQUAL', 'GREATER_THAN', 'GREATER_OR_EQUAL'] as const;
export const booleanOperators = ['AND', 'OR', 'NOT'] as const;
export const sensorKinds = ['FRONT_CLEAR', 'LEFT_CLEAR', 'RIGHT_CLEAR', 'AT_GOAL', 'HAS_KEY', 'ON_KEY', 'DOOR_AHEAD', 'DOOR_OPEN'] as const;
export type ValueType = typeof valueTypes[number];
export type ExpressionDto =
  | { kind: 'literal'; valueType: ValueType; value: string }
  | { kind: 'variableReference'; declarationId: string }
  | { kind: 'comparison'; operator: typeof comparisonOperators[number]; left: ExpressionDto; right: ExpressionDto }
  | { kind: 'booleanExpression'; operator: typeof booleanOperators[number]; left: ExpressionDto; right?: ExpressionDto }
  | { kind: 'sensorExpression'; sensor: typeof sensorKinds[number] };
export type StatementDto =
  | { kind: 'move' | 'turnLeft' | 'turnRight' }
  | { kind: 'variableDeclaration'; declarationId: string; name: string; valueType: ValueType; initialValue?: ExpressionDto }
  | { kind: 'assignment'; targetDeclarationId: string; value: ExpressionDto }
  | { kind: 'repeat'; count: ExpressionDto; body: StatementDto[] }
  | { kind: 'while'; condition: ExpressionDto; body: StatementDto[] }
  | { kind: 'if'; condition: ExpressionDto; thenBranch: StatementDto[] }
  | { kind: 'ifElse'; condition: ExpressionDto; thenBranch: StatementDto[]; elseBranch: StatementDto[] };
export interface ProgramDto { contractVersion: 1; name: string; statements: StatementDto[] }
export interface Diagnostic { code: string; message: string; blockId?: string }
export interface ModelResult {
  valid: boolean;
  diagnostics: Diagnostic[];
  summary: { rootType: string; statementCount: number; namespace: string } | null;
  xmi: string | null;
}
