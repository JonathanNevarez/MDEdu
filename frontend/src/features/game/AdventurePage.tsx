import { recordInteraction } from '../telemetry/client';
import { useAdaptiveUi } from '../adaptive/useAdaptiveUi';
import { AdaptiveRenderer } from '../adaptive/AdaptiveRenderer';
import { useCallback, useEffect, useRef, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getCatalog } from './api';
import { ensureStudent, serverLevelStatus, submitAttempt, type StudentProgress } from './learning';
import { evaluationMessage } from './evaluation';
import { initialState, replayState } from './replay';
import { GameEditor } from './GameEditor';
import { WorldBoard } from './WorldBoard';
import type { Catalog, ExecutionResult, Level } from './types';
import type { ProgramDto } from '../programming/dto/program';
import './game.css';
import { Icon, Landscape, LoadingState, LumaPortrait, MasteryBar, conceptIcons } from '../../shared/Visuals';

export function AdventurePage({progressOnly = false}: {progressOnly?: boolean}) {
  const { levelId } = useParams();
  const [catalog, setCatalog] = useState<Catalog | null>(null);
  const [progress, setProgress] = useState<StudentProgress | null>(null);
  const [studentId, setStudentId] = useState<string | null>(null);
  const [message, setMessage] = useState('');
  const [selectedConcept, setSelectedConcept] = useState<string | null>(null);
  useEffect(() => { const request = new AbortController();
    void Promise.all([getCatalog(request.signal), ensureStudent()]).then(([loaded, student]) => {
      if (!request.signal.aborted) { setCatalog(loaded); setStudentId(student.studentId); setProgress(student.progress); }
    }).catch(e => { if (!request.signal.aborted) setMessage(e instanceof Error ? e.message : 'No se pudo cargar la aventura.'); });
    return () => request.abort();
  }, []);
  const updateProgress = useCallback((next: StudentProgress) => setProgress(next), []);
  if (!catalog || !progress || !studentId) return <section className="adventure"><h1>Mi primera programación</h1>{message ? <div className="welcome-panel"><Icon name="info"/><p role="status">{message}</p><button onClick={() => window.location.reload()}>Reintentar</button></div> : <LoadingState/>}</section>;
  if (levelId) {
    const level = catalog.levels.find(l => l.id === levelId);
    if (!level) return <section className="welcome-panel"><Icon name="route"/><h1>Reto no encontrado</h1><Link className="return-link" to="/aprender">Volver al mapa</Link></section>;
    if (serverLevelStatus(level.id, progress) === 'LOCKED') return <section className="welcome-panel"><Icon name="lock"/><h1>Reto bloqueado</h1><p>Supera el reto anterior y los requisitos del concepto para continuar.</p><Link className="return-link" to="/aprender">Volver al mapa</Link></section>;
    return <Activity key={level.id} level={level} studentId={studentId} onProgress={updateProgress} total={catalog.levels.filter(l => l.conceptId === level.conceptId).length} />;
  }
  const completed = catalog.levels.filter(l => serverLevelStatus(l.id, progress) === 'COMPLETED').length;
  const current = catalog.levels.find(l => serverLevelStatus(l.id, progress) === 'UNLOCKED');
  const concepts = [...new Set(catalog.levels.map(l => l.conceptId ?? l.id))].map(id => {
    const challenges = catalog.levels.filter(l => (l.conceptId ?? l.id) === id);
    const done = challenges.filter(l => serverLevelStatus(l.id, progress) === 'COMPLETED').length;
    const unlocked = challenges.some(l => serverLevelStatus(l.id, progress) !== 'LOCKED');
    return { id, name: challenges[0]!.concept, challenges, done, unlocked };
  });
  const selected = concepts.find(c => c.id === selectedConcept) ?? concepts[0]!;
  if (progressOnly) return <section className="progress-page"><p className="eyebrow">TU APRENDIZAJE</p><h1>Mi progreso</h1><p>{completed} de {catalog.levels.length} retos completados</p><div className="progress-grid">{concepts.map(concept => {const entry=progress.levels.find(p=>p.conceptId===concept.id);return <article className="progress-card" key={concept.id}><h2>{concept.name}</h2><p>{concept.done} / {concept.challenges.length} retos completados</p><MasteryBar label="Dominio del concepto" value={entry?.masteryScore ?? 0}/><p>{entry?.attemptCount ?? 0} intentos · {concept.done === concept.challenges.length ? 'Completado' : concept.unlocked ? 'Disponible' : 'Bloqueado'}</p><ol>{concept.challenges.map(l=><li key={l.id}><Icon name={serverLevelStatus(l.id,progress)==='COMPLETED'?'check':serverLevelStatus(l.id,progress)==='LOCKED'?'lock':'arrow'}/>{l.title}</li>)}</ol></article>;})}</div></section>;
  return <section className="adventure" aria-labelledby="adventure-title">
    <div className="adventure-intro"><div><p className="eyebrow">TU AVENTURA CON BLOQUES</p><h1 id="adventure-title">Aprender</h1><p>Elige un concepto y explora sus retos de razonamiento.</p></div>
      <Link to="/progreso" className="progress-badge" aria-label={`${completed} de ${catalog.levels.length} retos completados`}><Icon name="flag"/><div><strong>{completed} / {catalog.levels.length}</strong><span>retos completados</span></div><Icon name="arrow"/></Link></div>
    <div className="adventure-map" aria-label="Mapa de cuatro conceptos">
      <Landscape/><span className="map-label">EL CAMINO DE TUS IDEAS</span>
      <svg className="map-trail" viewBox="0 0 1200 440" preserveAspectRatio="none" aria-hidden="true"><path d="M175 290C245 420 390 405 455 265S650 82 735 170 970 355 1025 235"/><path className="trail-center" d="M175 290C245 420 390 405 455 265S650 82 735 170 970 355 1025 235"/></svg>
      {concepts.map((concept, index) => { const status = !concept.unlocked ? 'locked' : concept.done === concept.challenges.length ? 'completed' : 'unlocked'; return <button key={concept.id}
        className={`level-node ${status}`} aria-pressed={selected.id === concept.id} aria-label={`${concept.name}: ${concept.challenges.length} retos. ${concept.unlocked ? 'Disponible' : 'Bloqueado'}`}
        onClick={() => setSelectedConcept(concept.id)}>
        <span className="node-circle" aria-hidden="true"><Icon name={conceptIcons[index] ?? 'route'}/><span className="node-mark"><Icon name={status === 'locked' ? 'lock' : status === 'completed' ? 'check' : 'arrow'}/></span></span>
        <span className="node-label"><span className="node-number">0{index + 1} · CONCEPTO</span><span className="node-concept">{concept.name}</span><span className="node-title">{concept.done} / {concept.challenges.length} retos</span><span className="node-status">Ver retos</span></span>
      </button>; })}
    </div>
    <section className="student-progress" aria-label={`Retos de ${selected.name}`}><h2>{selected.name} → Retos</h2><div className="challenge-trail">{selected.challenges.map(level => {
      const status = serverLevelStatus(level.id, progress); return <article className={`challenge-node ${status.toLowerCase()}`} key={level.id}><span className="challenge-marker"><Icon name={status === 'COMPLETED' ? 'check' : status === 'LOCKED' ? 'lock' : 'flag'}/></span><p className="eyebrow">RETO {level.order} · {level.difficulty?.replaceAll('_', ' ')}</p><h3>{level.title}</h3><p>{level.shortDescription ?? level.description}</p>
        {status === 'LOCKED' ? <p><Icon name="lock"/> Bloqueado · Supera el reto anterior y los requisitos del concepto.</p> : <Link className="button primary" to={`/aprender/${level.id}`}>{status === 'COMPLETED' ? 'Completado · volver a jugar' : 'Abrir reto'}<Icon name="arrow"/></Link>}</article>;
    })}</div></section>
    <div className="map-guide"><LumaPortrait state={completed === catalog.levels.length ? 'SUCCESS' : 'GUIDE'}/><div><strong>Luma · Tu compañera de aventura</strong><p role="status" className="map-message">{message || (current ? `Tu próximo reto: ${current.title}. Prueba, observa y descubre tu camino.` : '¡Completaste todos los retos! Puedes volver a explorarlos.')}</p></div>{current && <Link className="button primary" to={`/aprender/${current.id}`}>Ir al reto <Icon name="arrow"/></Link>}</div>
    <p className="local-progress-note">Tu avance se guarda para tu identidad de estudiante. <Link to="/laboratorio">Explorar el laboratorio libre</Link></p>
  </section>;
}
function Activity({ level, studentId, onProgress, total }: { level: Level; studentId: string; total: number; onProgress: (next: StudentProgress) => void }) {
  const adaptive = useAdaptiveUi(studentId, level.id);
  const opening = useRef(crypto.randomUUID());
  const navigation = useRef(new Map<string, string>());
  useEffect(() => { void recordInteraction(studentId, level.id, 'ACTIVITY_OPENED', opening.current); }, [studentId, level.id]);
  useEffect(() => {
    const value = adaptive.value;
    if (adaptive.loading || !value.attemptId || !value.fingerprint || value.safeDefault) return;
    const c = value.configuration;
    if (!(c.navigationMode === 'REPEAT' && c.repeatCurrentActivity) && !(c.navigationMode === 'ADVANCE' && c.nextActivityId)) return;
    const identity = `${value.attemptId}:${value.fingerprint}`;
    let eventId = navigation.current.get(identity);
    if (!eventId) { eventId = crypto.randomUUID(); navigation.current.set(identity, eventId); }
    void recordInteraction(studentId, level.id, 'NAVIGATION_PRESENTED', eventId, value.attemptId, value.fingerprint);
  }, [studentId, level.id, adaptive.value, adaptive.loading]);

  const openedAt = useRef(performance.now());
  const [result, setResult] = useState<ExecutionResult | null>(null);
  const [index, setIndex] = useState(-1); const [playing, setPlaying] = useState(false);
  const [busy, setBusy] = useState(false); const [message, setMessage] = useState('Construye tu camino con bloques.');
  const pending = useRef<AbortController | null>(null);
  useEffect(() => () => pending.current?.abort(), []);
  useEffect(() => {
    if (!playing || !result) return;
    if (index >= result.trace.length - 1) {
      setPlaying(false);
      setMessage(evaluationMessage(result));
      return;
    }
    const timer = window.setTimeout(() => setIndex(i => i + 1), 220);
    return () => window.clearTimeout(timer);
  }, [playing, index, result]);
  const initial = initialState(level.worldConfig); const state = replayState(result?.trace ?? [], index, initial);
  async function run(dto: ProgramDto) {
    pending.current?.abort(); const request = new AbortController(); pending.current = request;
    setPlaying(false); setIndex(-1); setResult(null); setBusy(true); setMessage('Preparando el recorrido…');
    try { const attempt = await submitAttempt(studentId, level.id, dto, Math.min(86400000, Math.max(0, Math.round(performance.now() - openedAt.current))), request.signal);
      const response = attempt.execution;
      if (request.signal.aborted) return;
      onProgress(attempt.progress);
      void adaptive.load(attempt.attemptId);
      setResult(response); setPlaying(response.trace.length > 0);
      setMessage(response.trace.length ? 'Recorriendo tu programa…' : response.errors[0]?.message ?? 'Revisa tu programa.');
    } catch (e) { if (!request.signal.aborted) setMessage(e instanceof Error ? e.message : 'No se pudo ejecutar.'); }
    finally { if (!request.signal.aborted) setBusy(false); }
  }
  function reset() { pending.current?.abort(); setBusy(false); setPlaying(false); setResult(null); setIndex(-1); setMessage('Tablero reiniciado. Tus bloques siguen aquí.'); }
  return <section className="game-activity" data-layout={adaptive.value.configuration.activityLayout}>
    <header className="challenge-header"><div className="challenge-art" aria-hidden="true"><WorldBoard world={level.worldConfig} state={initial}/></div><div className="challenge-copy"><span className="challenge-chip">Reto guiado</span><h1>{level.title}</h1><p>{level.shortDescription ?? level.description}</p><Link to="/aprender">← Volver al mapa</Link></div>
      <div className="challenge-objective"><Icon name="flag"/><div><h2>Objetivo de aprendizaje</h2><p>{level.learningObjective ?? level.description}</p></div></div>
      <div className="challenge-progress"><strong>{level.concept}</strong><span>Reto {level.order} de {total}</span><progress aria-label="Posición del reto en el concepto" value={level.order} max={total}/></div></header>
    <div className="game-layout"><GameEditor level={level} busy={busy || playing} onRun={dto => void run(dto)} onMessage={setMessage}/>
      <aside className="game-simulation" aria-label="Simulación del reto"><div className="panel-heading"><h2><Icon name="flask"/>Simulación</h2><button aria-label="Reiniciar" onClick={reset}><span aria-hidden="true">↻</span> Reiniciar</button></div><WorldBoard world={level.worldConfig} state={state}/>
        <p role="status" className={result?.evaluation?.activityPassed && !playing && index >= 0 ? 'game-success' : 'game-status'}>{message}</p>
        {result && !playing && result.errors.length > 0 && <p className="simulation-error">{result.errors[0]?.message}</p>}
        {state.doors.some(d=>d.open) && <p className="state-chip">✓ Puerta abierta</p>}
        {state.variables.length > 0 && <section aria-label="Variables actuales" className="game-variables"><h3>Valores guardados</h3><ul>{state.variables.map(v=><li key={v.id}>{v.name} = <strong>{String(v.value)}</strong></li>)}</ul></section>}
        <button className="replay-button" disabled={!result?.trace.length || playing || busy} onClick={()=>{setIndex(-1);setPlaying(true);setMessage('Reproduciendo el mismo recorrido…');}}>Reproducir</button>
      </aside>
      <AdaptiveRenderer value={adaptive.value} loading={adaptive.loading} error={adaptive.error} success={!!result?.evaluation?.activityPassed && !playing} evaluation={!playing && index>=0 ? result?.evaluation : null} onRepeat={reset}/>
    </div>
  </section>;
}
