import * as Blockly from 'blockly/core';
import { afterEach, beforeEach, describe, expect, it } from 'vitest';
import { registerBlocks } from '../../src/features/programming/blockly/blocks';
import { examples, loadExample } from '../../src/features/programming/blockly/examples';
import { toProgramDto } from '../../src/features/programming/blockly/toDto';
import { restoreWorkspace, saveWorkspace, STORAGE_KEY } from '../../src/features/programming/persistence/workspace';
import type { ProgramDto } from '../../src/features/programming/dto/program';

registerBlocks();
let ws: Blockly.Workspace;
beforeEach(() => { ws = new Blockly.Workspace(); localStorage.clear(); });
afterEach(() => ws.dispose());
const block = (type: string) => ws.newBlock(`mdedu_${type}`);
const next = (a: Blockly.Block, b: Blockly.Block) => a.nextConnection!.connect(b.previousConnection!);
const value = (a: Blockly.Block, key: string, b: Blockly.Block) => a.getInput(key)!.connection!.connect(b.outputConnection!);
const codes = () => toProgramDto(ws, 'Test').diagnostics.map(d => d.code);

describe('Contrato Blockly → DTO V1', () => {
  it('registra exactamente los 16 bloques MDEdu', () => {
    expect(Object.keys(Blockly.Blocks).filter(k => k.startsWith('mdedu_'))).toHaveLength(16);
  });
  it('recorre next en orden sin incluir Inicio en el DTO', () => {
    let previous = block('start');
    for (const type of ['move', 'move', 'turn_left', 'move', 'turn_right']) { const b = block(type); next(previous, b); previous = b; }
    expect(toProgramDto(ws, examples.sequence!.name).dto).toEqual(examples.sequence);
  });
  for (const key of ['variables', 'conditionals', 'loops']) it(`produce el fixture compartido ${key}`, () => {
    const fixture = examples[key]!; loadExample(ws, fixture);
    expect(toProgramDto(ws, fixture.name)).toEqual({ dto: fixture, diagnostics: [] });
  });
  it('preserva IDs, campos, conexiones y nombre al guardar/limpiar/restaurar', () => {
    const fixture = examples.variables!; loadExample(ws, fixture);
    const before = toProgramDto(ws, fixture.name);
    saveWorkspace(ws, fixture.name); ws.clear();
    expect(ws.getAllBlocks(false)).toHaveLength(0);
    expect(toProgramDto(ws, restoreWorkspace(ws))).toEqual(before);
    expect(before.dto!.statements[0]).toMatchObject({ declarationId: 'contador-declaration' });
    expect(before.dto!.statements[1]).toMatchObject({ targetDeclarationId: 'contador-declaration' });
  });
  it('admite referencias a declaraciones cargadas después', () => {
    const dto: ProgramDto = { contractVersion: 1, name: 'Orden', statements: [
      { kind: 'assignment', targetDeclarationId: 'later', value: { kind: 'variableReference', declarationId: 'later' } },
      { kind: 'variableDeclaration', declarationId: 'later', name: 'n', valueType: 'INTEGER' },
    ] };
    loadExample(ws, dto); expect(toProgramDto(ws, dto.name).dto).toEqual(dto);
    saveWorkspace(ws, dto.name); ws.clear(); expect(toProgramDto(ws, restoreWorkspace(ws)).dto).toEqual(dto);
  });
  it('cubre If, Comparison, BooleanExpression NOT/AND y literales booleanos', () => {
    const start = block('start'); const conditional = block('if'); next(start, conditional);
    const logical = block('boolean_expression'); value(conditional, 'CONDITION', logical);
    const comparison = block('comparison'); value(logical, 'LEFT', comparison);
    value(comparison, 'LEFT', block('integer_literal')); value(comparison, 'RIGHT', block('integer_literal'));
    const not = block('boolean_expression'); not.setFieldValue('NOT', 'OPERATOR');
    expect(not.getInput('RIGHT')).toBeNull(); value(not, 'LEFT', block('boolean_literal')); value(logical, 'RIGHT', not);
    expect(toProgramDto(ws, 'Expresiones').dto?.statements[0]).toMatchObject({ kind: 'if', condition: {
      kind: 'booleanExpression', operator: 'AND', left: { kind: 'comparison', operator: 'EQUAL' }, right: { operator: 'NOT', left: { valueType: 'BOOLEAN' } },
    } });
    expect(codes()).toEqual([]);
  });
  it('cubre todos los sensores', () => {
    for (const sensor of ['FRONT_CLEAR', 'LEFT_CLEAR', 'RIGHT_CLEAR', 'AT_GOAL', 'HAS_KEY', 'ON_KEY', 'DOOR_AHEAD', 'DOOR_OPEN']) {
      ws.clear(); const s = block('start'); const i = block('if'); next(s, i);
      const b = block('sensor'); b.setFieldValue(sensor, 'SENSOR'); value(i, 'CONDITION', b);
      expect(toProgramDto(ws, 'Sensor').dto?.statements[0]).toMatchObject({ condition: { sensor } });
    }
  });
});
describe('Diagnósticos controlados', () => {
  it('rechaza ausencia de Inicio', () => expect(codes()).toEqual(['NO_START']));
  it('rechaza dos Inicio', () => { block('start'); block('start'); expect(codes()).toEqual(['MULTIPLE_START']); });
  it('detecta bloques desconectados', () => { block('start'); block('move'); expect(codes()).toEqual(['DISCONNECTED_BLOCK']); });
  it('rechaza assignment a declaración inexistente', () => {
    const s = block('start'); const a = block('assignment'); next(s, a); a.setFieldValue('missing', 'DECLARATION');
    value(a, 'VALUE', block('integer_literal')); expect(codes()).toEqual(['MISSING_VARIABLE_DECLARATION']);
  });
  it('rechaza input obligatorio ausente', () => { next(block('start'), block('repeat')); expect(codes()).toEqual(['MISSING_INPUT']); });
  it('rechaza un bloque desconocido', () => {
    Blockly.Blocks['test_unknown'] = { init(this: Blockly.Block) { this.setPreviousStatement(true, 'Statement'); } };
    next(block('start'), ws.newBlock('test_unknown')); expect(codes()).toEqual(['UNKNOWN_BLOCK']);
    delete Blockly.Blocks['test_unknown'];
  });
  it('rechaza configuración literal inválida', () => {
    const r = block('repeat'); next(block('start'), r); const n = block('integer_literal'); n.setFieldValue('abc', 'VALUE');
    value(r, 'COUNT', n); expect(codes()).toEqual(['INVALID_BLOCK_CONFIGURATION']);
  });
  it('no pierde el workspace si el guardado está corrupto', () => {
    block('start'); localStorage.setItem(STORAGE_KEY, '{invalid');
    expect(() => restoreWorkspace(ws)).toThrow(); expect(ws.getAllBlocks(false)).toHaveLength(1);
  });
});
