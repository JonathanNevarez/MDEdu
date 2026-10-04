import {blockStyle} from './theme';
import * as Blockly from 'blockly/core';
import * as Es from 'blockly/msg/es';
import { booleanOperators, comparisonOperators, sensorKinds, valueTypes } from '../dto/program';

const dropdown = (values: readonly string[]) => new Blockly.FieldDropdown(values.map(v => [v, v]));

/** Preserve serialized IDs even when declarations load after their references. */
class DeclarationField extends Blockly.FieldDropdown {
  private pendingId?: string;
  constructor() {
    super(function (this: Blockly.FieldDropdown): Blockly.MenuOption[] {
      const options: Blockly.MenuOption[] = [['Seleccionar variable', '']];
      for (const b of this.getSourceBlock()?.workspace.getAllBlocks(false) ?? []) {
        if (b.type === 'mdedu_variable_declaration') options.push([String(b.getFieldValue('NAME') || 'sin nombre'), b.id]);
      }
      const id = this instanceof DeclarationField ? this.pendingId ?? this.getValue() : this.getValue();
      if (id && !options.some(o => o !== 'separator' && o[1] === id)) options.push(['Declaración no disponible', id]);
      return options;
    });
  }
  protected override doClassValidation_(value: string): string | null | undefined;
  protected override doClassValidation_(value?: string): string | null;
  protected override doClassValidation_(value?: string): string | null {
    if (typeof value !== 'string') return null;
    this.pendingId = value;
    return value;
  }
}

export function registerBlocks() {
  // ESM exposes named messages; TypeScript also includes a synthetic default.
  // Exclude that non-message export without casting or changing the locale.
  const { default: defaultExport, ...esMessages } = Es;
  void defaultExport;
  Blockly.setLocale(esMessages);
  if (Blockly.Blocks['mdedu_start']) return;
  const define = (name: string, init: (block: Blockly.Block) => void, expression = false, start = false) => {
    Blockly.Blocks[name] = { init(this: Blockly.Block) {
      this.setStyle(blockStyle(name));
      if (expression) this.setOutput(true, 'Expression');
      else { if (!start) this.setPreviousStatement(true, 'Statement'); this.setNextStatement(true, 'Statement'); }
      init(this);
    } };
  };
  const value = (b: Blockly.Block, name: string, label: string) => b.appendValueInput(name).setCheck('Expression').appendField(label);
  const body = (b: Blockly.Block, name: string, label: string) => b.appendStatementInput(name).setCheck('Statement').appendField(label);
  define('mdedu_start', b => b.appendDummyInput().appendField('Inicio'), false, true);
  for (const [type, label] of [['move', 'Avanzar'], ['turn_left', 'Girar a la izquierda'], ['turn_right', 'Girar a la derecha']]) {
    define(`mdedu_${type}`, b => b.appendDummyInput().appendField(label!));
  }
  define('mdedu_variable_declaration', b => {
    b.appendDummyInput().appendField('Declarar').appendField(new Blockly.FieldTextInput('contador'), 'NAME')
      .appendField(dropdown(valueTypes), 'TYPE');
    value(b, 'INITIAL', 'Valor inicial (opcional)');
  });
  define('mdedu_assignment', b => {
    b.appendDummyInput().appendField('Asignar a').appendField(new DeclarationField(), 'DECLARATION');
    value(b, 'VALUE', 'Valor');
  });
  define('mdedu_variable_reference', b => b.appendDummyInput().appendField('Variable')
    .appendField(new DeclarationField(), 'DECLARATION'), true);
  define('mdedu_repeat', b => { value(b, 'COUNT', 'Repetir'); body(b, 'BODY', 'Hacer'); });
  define('mdedu_while', b => { value(b, 'CONDITION', 'Mientras'); body(b, 'BODY', 'Hacer'); });
  for (const name of ['mdedu_if', 'mdedu_if_else']) define(name, b => {
    value(b, 'CONDITION', 'Si'); body(b, 'THEN', 'Entonces');
    if (name === 'mdedu_if_else') body(b, 'ELSE', 'Si no');
  });
  define('mdedu_integer_literal', b => b.appendDummyInput().appendField('Entero')
    .appendField(new Blockly.FieldTextInput('0'), 'VALUE'), true);
  define('mdedu_boolean_literal', b => b.appendDummyInput().appendField('Booleano')
    .appendField(new Blockly.FieldDropdown([['verdadero', 'true'], ['falso', 'false']]), 'VALUE'), true);
  define('mdedu_comparison', b => {
    value(b, 'LEFT', 'Comparar').appendField(dropdown(comparisonOperators), 'OPERATOR');
    value(b, 'RIGHT', 'Con');
  }, true);
  define('mdedu_boolean_expression', b => {
    const operator = dropdown(booleanOperators);
    operator.setValidator(function (this: Blockly.Field<string>, next: string): string | null {
      const block = this.getSourceBlock();
      if (block) {
        if (next === 'NOT' && block.getInput('RIGHT')) block.removeInput('RIGHT');
        if (next !== 'NOT' && !block.getInput('RIGHT')) value(block, 'RIGHT', 'Derecha');
      }
      return next;
    });
    value(b, 'LEFT', 'Lógica').appendField(operator, 'OPERATOR');
    value(b, 'RIGHT', 'Derecha');
  }, true);
  define('mdedu_sensor', b => b.appendDummyInput().appendField('Sensor').appendField(dropdown(sensorKinds), 'SENSOR'), true);
}

const category = (name: string, types: string[]) => ({ kind: 'category', name,
  contents: types.map(type => ({ kind: 'block', type: `mdedu_${type}` })) });
export const toolbox = { kind: 'categoryToolbox', contents: [
  category('Movimiento', ['start', 'move', 'turn_left', 'turn_right']),
  category('Variables', ['variable_declaration', 'assignment', 'variable_reference']),
  category('Control', ['repeat', 'while', 'if', 'if_else']),
  category('Condiciones', ['comparison', 'boolean_expression', 'sensor']),
  category('Valores', ['integer_literal', 'boolean_literal']),
] };
