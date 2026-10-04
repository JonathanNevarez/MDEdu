import {createModel} from '../programming/api/models';
import {ModelDialog} from '../programming/pages/ModelDialog';
import type {ModelResult} from '../programming/dto/program';
import {editorOptions,blockStyle} from '../programming/blockly/theme';
import {Icon} from '../../shared/Visuals';
import { useEffect, useRef, useState } from 'react';
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
  return { kind: 'categoryToolbox', contents: allowed.map(name => ({ kind: 'category', name: name === 'Movimiento' ? 'Secuencias' : name, categorystyle: name === 'Movimiento' ? 'sequence' : blockStyle((groups[name] ?? ['move'])[0]!),
    contents: (groups[name] ?? []).map(type => ({ kind: 'block', type: `mdedu_${type}` })) })) };
}
registerBlocks();
export function GameEditor({ level, busy, onRun, onMessage }: {
  level: Level; busy: boolean; onRun: (dto: ProgramDto) => void; onMessage: (text: string) => void;
}) {
  const [modelOpen,setModelOpen]=useState(false);const [model,setModel]=useState<ModelResult|null>(null);
  const host = useRef<HTMLDivElement>(null);
  const workspace = useRef<Blockly.WorkspaceSvg | null>(null);
  useEffect(() => {
    if (!host.current) return;
    const ws = Blockly.inject(host.current, { ...editorOptions, toolbox: gameToolbox(level.allowedBlockGroups) });
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
  async function inspect(){
    if(!workspace.current)return;
    const adapted=toProgramDto(workspace.current,level.title);
    if(!adapted.dto){onMessage(adapted.diagnostics.map(d=>d.message).join(' '));return;}
    setModel(null);setModelOpen(true);
    try{setModel(await createModel(adapted.dto));}catch{setModelOpen(false);onMessage('No se pudo consultar el modelo.');}
  }
  function run() {
    if (!workspace.current) return;
    const result = toProgramDto(workspace.current, level.title);
    if (result.dto) onRun(result.dto);
    else onMessage(result.diagnostics.map(d => d.message).join(' '));
  }
  return <section className="game-editor" aria-label="Programa con bloques">
    <div className="editor-heading"><h2 className="toolbox-title"><Icon name="sequence"/>Bloques</h2><h2><Icon name="book"/>Espacio de programación</h2><div className="workspace-tools"><button aria-label="Deshacer" title="Deshacer" disabled={busy} onClick={()=>workspace.current?.undo(false)}>↶</button><button aria-label="Rehacer" title="Rehacer" disabled={busy} onClick={()=>workspace.current?.undo(true)}>↷</button><button className="danger" disabled={busy} onClick={()=>{const ws=workspace.current;if(ws){ws.clear();const start=ws.newBlock('mdedu_start');start.initSvg();start.render();start.moveBy(25,25);}}}>Limpiar</button><button className="game-primary" disabled={busy} onClick={run}>▶ Ejecutar</button></div></div>
    <details className="quick-access"><summary>Añadir con teclado</summary><div className="quick-blocks" aria-label="Añadir movimientos al final">
      <button disabled={busy} onClick={() => append('move')}>+ Avanzar</button>
      <button disabled={busy} onClick={() => append('turn_left')}>+ Girar izquierda</button>
      <button disabled={busy} onClick={() => append('turn_right')}>+ Girar derecha</button>
    </div></details>
    <button className="inspect-model" onClick={()=>void inspect()} disabled={busy}>Ver modelo</button>
    {modelOpen && <ModelDialog model={model} onClose={()=>setModelOpen(false)}/>}
    <div ref={host} className="game-blockly" data-testid="game-blockly" aria-label="Editor Blockly del reto" />

  </section>;
}
