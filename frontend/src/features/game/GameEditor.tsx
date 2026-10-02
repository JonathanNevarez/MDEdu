import { useEffect, useRef } from 'react';
import * as Blockly from 'blockly/core';
import { registerBlocks } from '../programming/blockly/blocks';
import { toProgramDto } from '../programming/blockly/toDto';
import type { ProgramDto } from '../programming/dto/program';
import type { Level } from './types';

const groups: Record<string, string[]> = {
  Movimiento: ['start', 'move', 'turn_left', 'turn_right'],
  Variables: ['variable_declaration', 'assignment', 'variable_reference'],
  Condicionales: ['if', 'if_else'], Ciclos: ['repeat', 'while'],
  Condiciones: ['comparison', 'boolean_expression', 'sensor'], Valores: ['integer_literal', 'boolean_literal'],
};
export function gameToolbox(allowed: string[]) {
  return { kind: 'categoryToolbox', contents: allowed.map(name => ({ kind: 'category', name,
    contents: (groups[name] ?? []).map(type => ({ kind: 'block', type: `mdedu_${type}` })) })) };
}
registerBlocks();
export function GameEditor({ level, busy, onRun, onMessage }: {
  level: Level; busy: boolean; onRun: (dto: ProgramDto) => void; onMessage: (text: string) => void;
}) {
  const host = useRef<HTMLDivElement>(null);
  const workspace = useRef<Blockly.WorkspaceSvg | null>(null);
  useEffect(() => {
    if (!host.current) return;
    const ws = Blockly.inject(host.current, { toolbox: gameToolbox(level.allowedBlockGroups), trashcan: true,
      scrollbars: true, zoom: { controls: true, startScale: 0.85 }, media: '/blockly-media/' });
    workspace.current = ws;
    Blockly.serialization.workspaces.load({ blocks: { languageVersion: 0, blocks: [{ type: 'mdedu_start', x: 25, y: 25 }] } }, ws);
    const resize = new ResizeObserver(() => Blockly.svgResize(ws)); resize.observe(host.current);
    return () => { resize.disconnect(); ws.dispose(); workspace.current = null; };
  }, [level]);
  function append(type: string) {
    const ws = workspace.current; if (!ws) return;
    const start = ws.getAllBlocks(false).find(b => b.type === 'mdedu_start');
    if (!start) { onMessage('Añade el bloque Inicio desde Movimiento.'); return; }
    let last = start; while (last.getNextBlock()) last = last.getNextBlock()!;
    const block = ws.newBlock(`mdedu_${type}`); block.initSvg(); block.render();
    if (block.previousConnection) last.nextConnection?.connect(block.previousConnection);
  }
  function run() {
    if (!workspace.current) return;
    const result = toProgramDto(workspace.current, level.title);
    if (result.dto) onRun(result.dto);
    else onMessage(result.diagnostics.map(d => d.message).join(' '));
  }
  return <section className="game-editor" aria-label="Programa con bloques">
    <div className="editor-heading"><strong>Tu programa</strong><span>Conecta los bloques desde Inicio</span></div>
    <div className="quick-blocks" aria-label="Añadir movimientos al final">
      <button disabled={busy} onClick={() => append('move')}>+ Avanzar</button>
      <button disabled={busy} onClick={() => append('turn_left')}>+ Girar izquierda</button>
      <button disabled={busy} onClick={() => append('turn_right')}>+ Girar derecha</button>
    </div>
    <div ref={host} className="game-blockly" data-testid="game-blockly" aria-label="Editor Blockly del nivel" />
    <button className="game-primary run-button" disabled={busy} onClick={run}>▶ Ejecutar</button>
  </section>;
}
