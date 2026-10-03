import { recordInteraction } from '../telemetry/client';
import { useAdaptiveUi } from '../adaptive/useAdaptiveUi';
import { AdaptiveRenderer } from '../adaptive/AdaptiveRenderer';
import { useCallback, useEffect, useRef, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { getCatalog } from './api';
import { ensureStudent, serverLevelStatus, submitAttempt, type StudentProgress } from './learning';
import { evaluationMessage } from './evaluation';
import { EvaluationFeedback } from './EvaluationFeedback';
import { initialState, replayState } from './replay';
import { GameEditor } from './GameEditor';
import { WorldBoard } from './WorldBoard';
import type { Catalog, ExecutionResult, Level } from './types';
import type { ProgramDto } from '../programming/dto/program';
import './game.css';
import { Icon, Landscape, LoadingState, LumaPortrait, MasteryBar, conceptIcons } from '../../shared/Visuals';

export function AdventurePage() {
  const { levelId } = useParams(); const navigate = useNavigate();
  const [catalog, setCatalog] = useState<Catalog | null>(null);
  const [progress, setProgress] = useState<StudentProgress | null>(null);
  const [studentId, setStudentId] = useState<string | null>(null);
  const [message, setMessage] = useState('');
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
    if (!level) return <section className="welcome-panel"><Icon name="route"/><h1>Nivel no encontrado</h1><Link className="return-link" to="/aventura">Volver al mapa</Link></section>;
    if (serverLevelStatus(level.id, progress) === 'LOCKED') return <section className="welcome-panel"><Icon name="lock"/><h1>Nivel bloqueado</h1><p>Completa el nivel anterior para continuar.</p><Link className="return-link" to="/aventura">Volver al mapa</Link></section>;
    return <Activity key={level.id} level={level} studentId={studentId} onProgress={updateProgress} />;
  }
  const completed = catalog.levels.filter(l => serverLevelStatus(l.id, progress) === 'COMPLETED').length;
  const current = catalog.levels.find(l => serverLevelStatus(l.id, progress) === 'UNLOCKED');
  return <section className="adventure" aria-labelledby="adventure-title">
    <div className="adventure-intro"><div><p className="eyebrow">TU AVENTURA CON BLOQUES</p><h1 id="adventure-title">Mi primera programación</h1>
      <p>Un pequeño paso, una gran idea. Programa el camino y descubre lo que puedes crear.</p></div>
      <a href="#mi-progreso" className="progress-badge" aria-label={`${completed} de 4 niveles completados`}><Icon name="flag"/><div><strong>{completed} / 4</strong><span>conceptos completados</span></div><Icon name="arrow"/></a></div>
    <div className="adventure-map" aria-label="Mapa de cuatro niveles">
      <Landscape/><span className="map-label">EL CAMINO DE TUS IDEAS</span>
      <svg className="map-trail" viewBox="0 0 1200 440" preserveAspectRatio="none" aria-hidden="true"><path d="M175 290C245 420 390 405 455 265S650 82 735 170 970 355 1025 235"/><path className="trail-center" d="M175 290C245 420 390 405 455 265S650 82 735 170 970 355 1025 235"/></svg>
      {catalog.levels.map(level => { const status = serverLevelStatus(level.id, progress); return <button key={level.id}
        className={`level-node ${status.toLowerCase()}`} aria-disabled={status === 'LOCKED'}
        aria-label={`${level.concept}: ${level.title}. ${status === 'LOCKED' ? 'Bloqueado' : status === 'COMPLETED' ? 'Completado' : 'Disponible'}`}
        onClick={() => { if (status === 'LOCKED') setMessage('Completa el nivel anterior para continuar.'); else navigate(`/aventura/${level.id}`); }}>
        <span className="node-circle" aria-hidden="true"><Icon name={conceptIcons[level.order - 1] ?? 'route'}/><span className="node-mark"><Icon name={status === 'LOCKED' ? 'lock' : status === 'COMPLETED' ? 'check' : 'arrow'}/></span></span>
        <span className="node-label"><span className="node-number">0{level.order} · DOMINIO {((progress.levels.find(p => p.levelId === level.id)?.masteryScore ?? 0) * 100).toFixed(1)} %</span><span className="node-concept">{level.concept}</span><span className="node-title">{level.title}</span>
        <span className="node-status">{status === 'LOCKED' ? 'Bloqueado' : status === 'COMPLETED' ? 'Completado · volver a jugar' : 'Continuar aventura'}</span></span>
      </button>; })}
    </div>
    <div className="map-guide"><LumaPortrait state={completed === 4 ? 'SUCCESS' : 'GUIDE'}/><div><strong>Luma · Tu compañera de aventura</strong><p role="status" className="map-message">{message || (current ? `Tu próximo destino: ${current.concept}. Prueba, observa y descubre tu camino.` : '¡Completaste los cuatro conceptos! Puedes volver a explorar cada reto.')}</p></div>{current && <Link className="button primary" to={`/aventura/${current.id}`}>Ir al reto <Icon name="arrow"/></Link>}</div>
    <details className="student-progress" id="mi-progreso"><summary>Mi progreso · {completed} de 4 conceptos completados</summary><div className="progress-grid">{catalog.levels.map(level => { const entry = progress.levels.find(p => p.levelId === level.id); return <article key={level.id}><h2><Icon name={conceptIcons[level.order - 1] ?? 'route'}/>{level.concept}</h2><MasteryBar label="Dominio del concepto" value={entry?.masteryScore ?? 0}/><p>{entry?.attemptCount ?? 0} intentos · {entry?.completed ? 'Completado' : entry?.unlocked ? 'Disponible' : 'Bloqueado'}</p></article>; })}</div></details>
    <p className="local-progress-note">Tu avance se guarda para tu identidad de estudiante. <Link to="/laboratorio">Explorar el laboratorio libre</Link></p>
  </section>;
}
function Activity({ level, studentId, onProgress }: { level: Level; studentId: string; onProgress: (next: StudentProgress) => void }) {
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
    <Link to="/aventura">← Volver al mapa</Link>
    <header className="activity-heading"><div><p className="eyebrow">{level.concept} · RETO {level.order} DE 4</p><h1>{level.title}</h1><p>{level.description}</p></div>
      <span className="goal-chip"><Icon name="flag"/>Objetivo: llegar a la bandera</span></header>
    <div className="game-layout"><GameEditor level={level} busy={busy || playing} onRun={dto => void run(dto)} onMessage={setMessage} />
      <aside className="game-simulation" aria-label="Simulación del nivel"><h2>Tu recorrido</h2><WorldBoard world={level.worldConfig} state={state} />
        <div className="replay-controls"><button onClick={reset}>Reiniciar</button><button disabled={!result?.trace.length || playing || busy}
          onClick={() => { setIndex(-1); setPlaying(true); setMessage('Reproduciendo el mismo recorrido…'); }}>Reproducir</button></div>
        <p role="status" className={result?.evaluation?.activityPassed && !playing && index >= 0 ? 'game-success' : 'game-status'}>{message}</p>
        {result?.evaluation && !playing && index >= 0 && <EvaluationFeedback evaluation={result.evaluation} />}
        {result && !playing && result.errors.length > 0 && <p>{result.errors[0]?.message}</p>}
        <section aria-label="Variables actuales" className="game-variables"><h3>Valores guardados</h3>
          {state.variables.length ? <ul>{state.variables.map(v => <li key={v.id}>{v.name} = <strong>{String(v.value)}</strong></li>)}</ul> : <p>Aún no hay variables.</p>}</section>
        {result && <details><summary>Ver recorrido · {result.steps} operaciones</summary><ol className="game-trace">{result.trace.map(event => <li key={event.index} aria-current={event.index === index ? 'step' : undefined}>{event.index + 1}. {event.detail}</li>)}</ol></details>}
      </aside></div>
    <AdaptiveRenderer value={adaptive.value} loading={adaptive.loading} error={adaptive.error} success={!!result?.evaluation?.activityPassed && !playing} onRepeat={reset}/>
  </section>;
}
