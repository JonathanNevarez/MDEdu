import { useCallback, useEffect, useRef, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { getCatalog, execute } from './api';
import { readProgress, levelStatus, completeLevel, saveProgress } from './progress';
import { initialState, replayState } from './replay';
import { GameEditor } from './GameEditor';
import { WorldBoard } from './WorldBoard';
import type { Catalog, ExecutionResult, Level } from './types';
import type { ProgramDto } from '../programming/dto/program';
import './game.css';

export function AdventurePage() {
  const { levelId } = useParams(); const navigate = useNavigate();
  const [catalog, setCatalog] = useState<Catalog | null>(null);
  const [progress, setProgress] = useState(readProgress);
  const progressRef = useRef(progress);
  const [message, setMessage] = useState('');
  useEffect(() => { const request = new AbortController(); void getCatalog(request.signal).then(setCatalog).catch(e => {
    if (!request.signal.aborted) setMessage(e instanceof Error ? e.message : 'No se pudo cargar la aventura.');
  }); return () => request.abort(); }, []);
  const complete = useCallback((id: string) => {
    const next = completeLevel(progressRef.current, id); progressRef.current = next; setProgress(next);
    if (!saveProgress(next)) setMessage('El progreso se conserva en esta sesión; el navegador no permite guardarlo.');
  }, []);
  if (!catalog) return <section className="adventure"><h1>Mi primera programación</h1><p role="status">{message || 'Preparando tu aventura…'}</p>
    {message && <button onClick={() => window.location.reload()}>Reintentar</button>}</section>;
  if (levelId) {
    const level = catalog.levels.find(l => l.id === levelId);
    if (!level) return <section><h1>Nivel no encontrado</h1><Link to="/aventura">Volver al mapa</Link></section>;
    if (levelStatus(level, progress) === 'LOCKED') return <section><h1>Nivel bloqueado</h1><p>Completa el nivel anterior para continuar.</p><Link to="/aventura">Volver al mapa</Link></section>;
    return <Activity key={level.id} level={level} onComplete={complete} />;
  }
  const completed = catalog.levels.filter(l => levelStatus(l, progress) === 'COMPLETED').length;
  return <section className="adventure" aria-labelledby="adventure-title">
    <div className="adventure-intro"><div><p className="eyebrow">TU AVENTURA CON BLOQUES</p><h1 id="adventure-title">Mi primera programación</h1>
      <p>Un pequeño paso, una gran idea. Programa el camino y descubre lo que puedes crear.</p></div>
      <div className="progress-badge" aria-label={`${completed} de 4 niveles completados`}><strong>{completed} / 4</strong><span>niveles completados</span></div></div>
    <div className="adventure-map" aria-label="Mapa de cuatro niveles">
      <div className="map-trail" aria-hidden="true" />
      {catalog.levels.map(level => { const status = levelStatus(level, progress); return <button key={level.id}
        className={`level-node ${status.toLowerCase()}`} aria-disabled={status === 'LOCKED'}
        aria-label={`${level.concept}: ${level.title}. ${status === 'LOCKED' ? 'Bloqueado' : status === 'COMPLETED' ? 'Completado' : 'Disponible'}`}
        onClick={() => { if (status === 'LOCKED') setMessage('Completa el nivel anterior para continuar.'); else navigate(`/aventura/${level.id}`); }}>
        <span className="node-circle" aria-hidden="true">{status === 'COMPLETED' ? '✓' : status === 'LOCKED' ?
          <svg width="32" height="36" viewBox="0 0 32 36" fill="none" stroke="currentColor" strokeWidth="3"><path d="M8 15V10a8 8 0 0116 0v5" /><rect x="3" y="15" width="26" height="18" rx="4" /><path d="M16 22v5" /></svg> : level.order}</span>
        <span className="node-concept">{level.concept}</span><strong>{level.title}</strong>
        <span className="node-status">{status === 'LOCKED' ? 'Bloqueado' : status === 'COMPLETED' ? 'Completado · volver a jugar' : '¡Vamos a jugar!'}</span>
      </button>; })}
    </div>
    <p role="status" className="map-message">{message || 'Empieza con Secuencias. Cada reto completado abre un nuevo camino.'}</p>
    <p className="local-progress-note">Tu avance se guarda en este navegador. <Link to="/laboratorio">Explorar el laboratorio libre</Link></p>
  </section>;
}
function Activity({ level, onComplete }: { level: Level; onComplete: (id: string) => void }) {
  const [result, setResult] = useState<ExecutionResult | null>(null);
  const [index, setIndex] = useState(-1); const [playing, setPlaying] = useState(false);
  const [busy, setBusy] = useState(false); const [message, setMessage] = useState('Construye tu camino con bloques.');
  const pending = useRef<AbortController | null>(null);
  useEffect(() => () => pending.current?.abort(), []);
  useEffect(() => {
    if (!playing || !result) return;
    if (index >= result.trace.length - 1) {
      setPlaying(false);
      if (result.success) { setMessage('¡Nivel completado!'); onComplete(level.id); }
      else setMessage(result.errors[0]?.message ?? 'El programa terminó. Prueba otro camino hasta la bandera.');
      return;
    }
    const timer = window.setTimeout(() => setIndex(i => i + 1), 220);
    return () => window.clearTimeout(timer);
  }, [playing, index, result, level.id, onComplete]);
  const initial = initialState(level.worldConfig); const state = replayState(result?.trace ?? [], index, initial);
  async function run(dto: ProgramDto) {
    pending.current?.abort(); const request = new AbortController(); pending.current = request;
    setPlaying(false); setIndex(-1); setResult(null); setBusy(true); setMessage('Preparando el recorrido…');
    try { const response = await execute(level.id, dto, request.signal);
      if (request.signal.aborted) return;
      setResult(response); setPlaying(response.trace.length > 0);
      setMessage(response.trace.length ? 'Recorriendo tu programa…' : response.errors[0]?.message ?? 'Revisa tu programa.');
    } catch (e) { if (!request.signal.aborted) setMessage(e instanceof Error ? e.message : 'No se pudo ejecutar.'); }
    finally { if (!request.signal.aborted) setBusy(false); }
  }
  function reset() { pending.current?.abort(); setBusy(false); setPlaying(false); setResult(null); setIndex(-1); setMessage('Tablero reiniciado. Tus bloques siguen aquí.'); }
  return <section className="game-activity">
    <Link to="/aventura">← Volver al mapa</Link>
    <header className="activity-heading"><div><p className="eyebrow">{level.concept} · RETO {level.order} DE 4</p><h1>{level.title}</h1><p>{level.description}</p></div>
      <span className="goal-chip">Objetivo: llegar a la bandera</span></header>
    <div className="game-layout"><GameEditor level={level} busy={busy || playing} onRun={dto => void run(dto)} onMessage={setMessage} />
      <aside className="game-simulation" aria-label="Simulación del nivel"><h2>Tu recorrido</h2><WorldBoard world={level.worldConfig} state={state} />
        <div className="replay-controls"><button onClick={reset}>Reiniciar</button><button disabled={!result?.trace.length || playing || busy}
          onClick={() => { setIndex(-1); setPlaying(true); setMessage('Reproduciendo el mismo recorrido…'); }}>Reproducir</button></div>
        <p role="status" className={result?.success && !playing && index >= 0 ? 'game-success' : 'game-status'}>{message}</p>
        <section aria-label="Variables actuales" className="game-variables"><h3>Valores guardados</h3>
          {state.variables.length ? <ul>{state.variables.map(v => <li key={v.id}>{v.name} = <strong>{String(v.value)}</strong></li>)}</ul> : <p>Aún no hay variables.</p>}</section>
        {result && <details><summary>Ver recorrido · {result.steps} operaciones</summary><ol className="game-trace">{result.trace.map(event => <li key={event.index} aria-current={event.index === index ? 'step' : undefined}>{event.index + 1}. {event.detail}</li>)}</ol></details>}
      </aside></div>
  </section>;
}
