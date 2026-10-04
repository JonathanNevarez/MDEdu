import {LoadingState} from '../../../shared/Visuals';
import {editorOptions} from '../blockly/theme';
import {apiBase,httpFetch,checkResponse} from '../../../shared/http';
import {WorldBoard} from '../../game/WorldBoard';
import {initialState,replayState} from '../../game/replay';
import type {WorldConfig,ExecutionResult} from '../../game/types';
import {LumaTutor} from '../../adaptive/AdaptiveRenderer';
import {ModelDialog} from './ModelDialog';
import '../../game/game.css';
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
  const [world,setWorld]=useState<WorldConfig|null>(null);const [worldError,setWorldError]=useState(false);const [execution,setExecution]=useState<ExecutionResult|null>(null);const [index,setIndex]=useState(-1);const [playing,setPlaying]=useState(false);const [modelOpen,setModelOpen]=useState(false);
  useEffect(()=>{const c=new AbortController();void httpFetch(`${apiBase}/api/laboratory/world`,{signal:c.signal}).then(checkResponse).then(r=>r.json()).then((w:WorldConfig)=>setWorld(w)).catch(()=>{if(!c.signal.aborted){setWorldError(true);setMessage('No se pudo cargar la simulación.');}});return()=>c.abort();},[]);
  useEffect(()=>{if(!playing||!execution)return;if(index>=execution.trace.length-1){setPlaying(false);return;}const timer=setTimeout(()=>setIndex(i=>i+1),220);return()=>clearTimeout(timer);},[playing,execution,index]);
  const container = useRef<HTMLDivElement>(null);
  const workspace = useRef<Blockly.WorkspaceSvg | null>(null);
  const request = useRef(0);
  const [name, setName] = useState('Mi programa');
  const [example, setExample] = useState('sequence');
  const [message, setMessage] = useState('Conecta instrucciones al bloque Inicio.');
  const [diagnostics, setDiagnostics] = useState<Diagnostic[]>([]);
  const [result, setResult] = useState<ModelResult | null>(null);
  const [busy, setBusy] = useState(false);
  const invalidate = () => { request.current++; setPlaying(false);setExecution(null);setIndex(-1); setBusy(false); setResult(null); setDiagnostics([]); };
  useEffect(() => {
    if (!container.current) return;
    const ws = Blockly.inject(container.current, {...editorOptions,toolbox});
    workspace.current = ws;
    Blockly.serialization.workspaces.load({ blocks: { languageVersion: 0, blocks: [{ type: 'mdedu_start', x: 30, y: 30 }] } }, ws);
    const resize = new ResizeObserver(() => Blockly.svgResize(ws)); resize.observe(container.current);
    ws.addChangeListener(event => { if (!event.isUiEvent) { request.current++; setBusy(false); setResult(null);setPlaying(false);setExecution(null);setIndex(-1); } });
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
    if (!adapted.dto) { setMessage('Revisa las conexiones indicadas.'); setResult({valid:false,diagnostics:adapted.diagnostics,summary:null,xmi:null}); return; }
    const current = ++request.current; setBusy(true); setMessage('Construyendo modelo…');
    try {
      const response = await createModel(adapted.dto);
      if (request.current !== current) return;
      setResult(response); setDiagnostics(response.diagnostics);
      setMessage(response.valid ? 'Modelo válido' : 'Error de modelo');
    } catch { if (request.current === current) {setMessage('No se pudo contactar con el servicio de modelos.');setResult({valid:false,diagnostics:[{code:'MODEL_UNAVAILABLE',message:'No se pudo consultar el modelo. Cierra esta ventana y vuelve a intentar.'}],summary:null,xmi:null});} }
    finally { if (request.current === current) setBusy(false); }
  }
  async function run(){
    if(!workspace.current)return;
    const adapted=toProgramDto(workspace.current,name);setDiagnostics(adapted.diagnostics);
    if(!adapted.dto){setMessage('Revisa las conexiones indicadas.');return;}
    const current=++request.current;setBusy(true);setExecution(null);setIndex(-1);setPlaying(false);
    try{const response=checkResponse(await httpFetch(`${apiBase}/api/laboratory/execute`,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(adapted.dto)}),[400,422]);const value=await response.json() as ExecutionResult;if(request.current!==current)return;setExecution(value);setPlaying(value.trace.length>0);setMessage(value.errors[0]?.message ?? 'Recorrido ejecutado. Puedes seguir experimentando.');}catch{if(request.current===current)setMessage('No se pudo ejecutar el programa.');}finally{if(request.current===current)setBusy(false);}
  }
  return <section className="laboratory" aria-labelledby="lab-title">
    <p className="eyebrow">Experimenta libremente</p><h1 id="lab-title">Laboratorio libre</h1>
    <p>Construye un programa y comprueba su estructura. Prueba tus ideas en la simulación, sin calificaciones ni cambios en tu progreso.</p>
    <div className="lab-controls">
      <label>Nombre del programa <input value={name} onChange={e => { setName(e.target.value); invalidate(); }} /></label>
      <label>Ejemplo <select value={example} onChange={e => setExample(e.target.value)}>
        <option value="sequence">Secuencia</option><option value="variables">Variables</option>
        <option value="conditionals">Condicionales</option><option value="loops">Ciclos</option>
      </select></label>
      <button onClick={() => action(ws => { const dto = examples[example]!; invalidate(); loadExample(ws, dto); setName(dto.name); setMessage('Ejemplo cargado.'); })}>Cargar ejemplo</button>
      <button onClick={() => action(ws => { saveWorkspace(ws, name); setMessage('Workspace guardado.'); })}>Guardar workspace</button>
      <button onClick={() => action(ws => { invalidate(); setName(restoreWorkspace(ws)); setMessage('Workspace restaurado.'); })}>Restaurar workspace</button>
      <button className="danger" onClick={() => action(ws => { invalidate(); ws.clear(); setMessage('Workspace limpio. Añade Inicio o carga un ejemplo.'); })}>Limpiar</button>
      <button disabled={busy} onClick={() => {setModelOpen(true);void generate();}}>Ver modelo</button><button className="primary" disabled={busy||playing||!world} onClick={()=>void run()}>▶ Ejecutar</button>
    </div>
    <div className="lab-workspace"><section className="lab-blocks"><h2>Bloques · Espacio de programación</h2><div ref={container} className="blockly-host" aria-label="Editor Blockly" data-testid="blockly-editor" /></section><aside className="game-simulation"><div className="panel-heading"><h2>Simulación</h2><button onClick={()=>{request.current++;setBusy(false);setExecution(null);setIndex(-1);setPlaying(false);setMessage('Tablero reiniciado. Tus bloques siguen aquí.');}}>Reiniciar</button></div>{world ? <WorldBoard world={world} state={replayState(execution?.trace ?? [],index,initialState(world))}/> : worldError ? <div className="welcome-panel"><p role="alert">La simulación no está disponible.</p><button onClick={()=>window.location.reload()}>Reintentar</button></div> : <LoadingState text="Preparando la simulación…"/>}<p role="status">{message}</p></aside><aside className="lab-luma"><LumaTutor mode="GUIDE" success={false} message="Prueba una idea, observa el recorrido y cambia tus bloques. Aquí puedes experimentar libremente."/></aside></div>

    {diagnostics.length > 0 && <ul aria-label="Diagnósticos">{diagnostics.map((d, i) => <li key={i}>
      <strong>{d.code}</strong>: {d.message}
      {d.blockId && <button onClick={() => workspace.current?.centerOnBlock(d.blockId!)}>Ver bloque</button>}
    </li>)}</ul>}
    {modelOpen && <ModelDialog model={result} onClose={()=>setModelOpen(false)}/>}
  </section>;
}
