import type { Block, Workspace } from 'blockly/core';
import { booleanOperators, comparisonOperators, sensorKinds, valueTypes } from '../dto/program';
import type { Diagnostic, ExpressionDto, ProgramDto, StatementDto } from '../dto/program';

export type AdaptResult = { dto: ProgramDto; diagnostics: [] } | { dto: null; diagnostics: Diagnostic[] };
class InvalidWorkspace extends Error {
  constructor(readonly diagnostic: Diagnostic) { super(diagnostic.message); }
}
export function toProgramDto(workspace: Workspace, name: string): AdaptResult {
  const visited = new Set<string>();
  const declarations = new Set<string>();
  const references: { id: string; blockId: string }[] = [];
  const fail = (code: string, message: string, block?: Block): never => {
    throw new InvalidWorkspace({ code, message, ...(block ? { blockId: block.id } : {}) });
  };
  const enter = (b: Block) => {
    if (visited.has(b.id)) fail('INVALID_BLOCK_CONFIGURATION', 'Conexión repetida.', b);
    if (!b.isEnabled()) fail('INVALID_BLOCK_CONFIGURATION', 'Activa o elimina el bloque deshabilitado.', b);
    visited.add(b.id);
  };
  const choice = <T extends string>(b: Block, field: string, choices: readonly T[]): T => {
    const v: unknown = b.getFieldValue(field);
    if (typeof v !== 'string' || !choices.includes(v as T)) fail('INVALID_BLOCK_CONFIGURATION', `Valor inválido: ${field}.`, b);
    return v as T;
  };
  const ref = (b: Block): string => {
    const id = String(b.getFieldValue('DECLARATION') ?? '');
    references.push({ id, blockId: b.id }); return id;
  };
  const input = (b: Block, key: string): ExpressionDto => {
    const child = b.getInputTargetBlock(key);
    if (!child) return fail('MISSING_INPUT', `Falta conectar ${key}.`, b);
    return expression(child);
  };
  const expression = (b: Block): ExpressionDto => {
    enter(b);
    switch (b.type) {
      case 'mdedu_integer_literal': {
        const value = String(b.getFieldValue('VALUE'));
        if (!/^-?\d+$/.test(value)) fail('INVALID_BLOCK_CONFIGURATION', 'Escribe un entero.', b);
        return { kind: 'literal', valueType: 'INTEGER', value };
      }
      case 'mdedu_boolean_literal': return { kind: 'literal', valueType: 'BOOLEAN', value: choice(b, 'VALUE', ['true', 'false']) };
      case 'mdedu_variable_reference': return { kind: 'variableReference', declarationId: ref(b) };
      case 'mdedu_sensor': return { kind: 'sensorExpression', sensor: choice(b, 'SENSOR', sensorKinds) };
      case 'mdedu_comparison': return { kind: 'comparison', operator: choice(b, 'OPERATOR', comparisonOperators), left: input(b, 'LEFT'), right: input(b, 'RIGHT') };
      case 'mdedu_boolean_expression': {
        const operator = choice(b, 'OPERATOR', booleanOperators);
        return { kind: 'booleanExpression', operator, left: input(b, 'LEFT'), ...(operator === 'NOT' ? {} : { right: input(b, 'RIGHT') }) };
      }
      default: return fail('UNKNOWN_BLOCK', `Expresión no soportada: ${b.type}.`, b);
    }
  };
  const chain = (first: Block | null): StatementDto[] => {
    const result: StatementDto[] = [];
    for (let b = first; b; b = b.getNextBlock()) {
      enter(b);
      switch (b.type) {
        case 'mdedu_move': result.push({ kind: 'move' }); break;
        case 'mdedu_turn_left': result.push({ kind: 'turnLeft' }); break;
        case 'mdedu_turn_right': result.push({ kind: 'turnRight' }); break;
        case 'mdedu_variable_declaration': {
          if (declarations.has(b.id)) fail('DUPLICATE_DECLARATION_ID', 'Declaración duplicada.', b);
          declarations.add(b.id);
          const initial = b.getInputTargetBlock('INITIAL');
          result.push({ kind: 'variableDeclaration', declarationId: b.id, name: String(b.getFieldValue('NAME')),
            valueType: choice(b, 'TYPE', valueTypes), ...(initial ? { initialValue: expression(initial) } : {}) }); break;
        }
        case 'mdedu_assignment': result.push({ kind: 'assignment', targetDeclarationId: ref(b), value: input(b, 'VALUE') }); break;
        case 'mdedu_repeat': result.push({ kind: 'repeat', count: input(b, 'COUNT'), body: chain(b.getInputTargetBlock('BODY')) }); break;
        case 'mdedu_while': result.push({ kind: 'while', condition: input(b, 'CONDITION'), body: chain(b.getInputTargetBlock('BODY')) }); break;
        case 'mdedu_if': result.push({ kind: 'if', condition: input(b, 'CONDITION'), thenBranch: chain(b.getInputTargetBlock('THEN')) }); break;
        case 'mdedu_if_else': result.push({ kind: 'ifElse', condition: input(b, 'CONDITION'), thenBranch: chain(b.getInputTargetBlock('THEN')), elseBranch: chain(b.getInputTargetBlock('ELSE')) }); break;
        default: fail('UNKNOWN_BLOCK', `Instrucción no soportada: ${b.type}.`, b);
      }
    }
    return result;
  };
  try {
    const blocks = workspace.getAllBlocks(false);
    const starts = blocks.filter(b => b.type === 'mdedu_start');
    if (!starts.length) fail('NO_START', 'Añade un bloque Inicio.');
    if (starts.length !== 1) fail('MULTIPLE_START', 'Debe existir exactamente un Inicio.');
    const start = starts[0]!; enter(start);
    const statements = chain(start.getNextBlock());
    const diagnostics: Diagnostic[] = blocks.filter(b => !visited.has(b.id))
      .map(b => ({ code: 'DISCONNECTED_BLOCK', message: 'Conecta o elimina este bloque.', blockId: b.id }));
    for (const r of references) if (!declarations.has(r.id)) diagnostics.push({ code: 'MISSING_VARIABLE_DECLARATION', message: 'Selecciona una declaración conectada al programa.', blockId: r.blockId });
    if (diagnostics.length) return { dto: null, diagnostics };
    return { dto: { contractVersion: 1, name, statements }, diagnostics: [] };
  } catch (e) {
    if (e instanceof InvalidWorkspace) return { dto: null, diagnostics: [e.diagnostic] };
    throw e;
  }
}
