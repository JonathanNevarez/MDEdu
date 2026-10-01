import { useEffect, useRef, useState } from 'react';
import * as Blockly from 'blockly/core';
import { registerBlocks, toolbox } from '../blockly/blocks';
import { examples, loadExample } from '../blockly/examples';
import { toProgramDto } from '../blockly/toDto';
import { saveWorkspace, restoreWorkspace } from '../persistence/workspace';
import { createModel } from '../api/models';
import type { Diagnostic, ModelResult } from '../dto/program';

registerBlocks();
export function LaboratoryPage() {
  const container = useRef<HTMLDivElement>(null);
  const workspace = useRef<Blockly.WorkspaceSvg | null>(null);
  const request = useRef(0);
  const [name, setName] = useState('Mi programa');
  const [example, setExample] = useState('sequence');
  const [message, setMessage] = useState('Conecta instrucciones al bloque Inicio.');
  const [diagnostics, setDiagnostics] = useState<Diagnostic[]>([]);
  const [result, setResult] = useState<ModelResult | null>(null);
  const [busy, setBusy] = useState(false);
  const invalidate = () => { request.current++; setBusy(false); setResult(null); setDiagnostics([]); };
  useEffect(() => {
    if (!container.current) return;
    const ws = Blockly.inject(container.current, { toolbox, trashcan: true, scrollbars: true,
      zoom: { controls: true, wheel: false, startScale: 0.9 }, media: '/blockly-media/' });
    workspace.current = ws;
    Blockly.serialization.workspaces.load({ blocks: { languageVersion: 0, blocks: [{ type: 'mdedu_start', x: 30, y: 30 }] } }, ws);
    const resize = new ResizeObserver(() => Blockly.svgResize(ws)); resize.observe(container.current);
    ws.addChangeListener(event => { if (!event.isUiEvent) { request.current++; setBusy(false); setResult(null); } });
    return () => { request.current++; resize.disconnect(); ws.dispose(); workspace.current = null; };
  }, []);
  const action = (fn: (ws: Blockly.WorkspaceSvg) => void) => {
    if (!workspace.current) return;
    try { fn(workspace.current); } catch (e) { setMessage(e instanceof Error ? e.message : 'No se pudo completar la operación.'); }
  };
  async function generate() {
    if (!workspace.current) return;
    const adapted = toProgramDto(workspace.current, name);
    setDiagnostics(adapted.diagnostics); setResult(null);
    if (!adapted.dto) { setMessage('Revisa las conexiones indicadas.'); return; }
    const current = ++request.current; setBusy(true); setMessage('Construyendo modelo…');
    try {
      const response = await createModel(adapted.dto);
      if (request.current !== current) return;
      setResult(response); setDiagnostics(response.diagnostics);
      setMessage(response.valid ? 'Modelo válido' : 'Error de modelo');
    } catch { if (request.current === current) setMessage('No se pudo contactar con el servicio de modelos.'); }
    finally { if (request.current === current) setBusy(false); }
  }
  return <section className="laboratory" aria-labelledby="lab-title">
    <h1 id="lab-title">Laboratorio libre</h1>
    <p>Construye un programa y comprueba su estructura. En esta fase el programa no se ejecuta.</p>
    <div className="lab-controls">
      <label>Nombre del programa <input value={name} onChange={e => { setName(e.target.value); invalidate(); }} /></label>
      <label>Ejemplo <select value={example} onChange={e => setExample(e.target.value)}>
        <option value="sequence">Secuencia</option><option value="variables">Variables</option>
        <option value="conditionals">Condicionales</option><option value="loops">Ciclos</option>
      </select></label>
      <button onClick={() => action(ws => { const dto = examples[example]!; invalidate(); loadExample(ws, dto); setName(dto.name); setMessage('Ejemplo cargado.'); })}>Cargar ejemplo</button>
      <button onClick={() => action(ws => { saveWorkspace(ws, name); setMessage('Workspace guardado.'); })}>Guardar workspace</button>
      <button onClick={() => action(ws => { invalidate(); setName(restoreWorkspace(ws)); setMessage('Workspace restaurado.'); })}>Restaurar workspace</button>
      <button onClick={() => action(ws => { invalidate(); ws.clear(); setMessage('Workspace limpio. Añade Inicio o carga un ejemplo.'); })}>Limpiar</button>
      <button disabled={busy} onClick={() => void generate()}>Generar modelo EMF</button>
    </div>
    <div ref={container} className="blockly-host" aria-label="Editor Blockly" data-testid="blockly-editor" />
    <p role="status">{message}</p>
    {diagnostics.length > 0 && <ul aria-label="Diagnósticos">{diagnostics.map((d, i) => <li key={i}>
      <strong>{d.code}</strong>: {d.message}
      {d.blockId && <button onClick={() => workspace.current?.centerOnBlock(d.blockId!)}>Ver bloque</button>}
    </li>)}</ul>}
    {result?.summary && <p>Instrucciones: {result.summary.statementCount} (incluidas las anidadas).</p>}
    {result?.xmi && <details><summary>Ver XMI</summary><pre className="xmi-view">{result.xmi}</pre></details>}
  </section>;
}
