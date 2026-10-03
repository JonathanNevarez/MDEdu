import { useCallback, useEffect, useState } from 'react';
import type { ReactNode } from 'react';
import { metaUiApi as api } from './metaUiApi';
import type { Decision, Timeline, Page } from './types';
import './metaui.css';
import { Icon, LoadingState, MasteryBar } from '../../shared/Visuals';

function useRead<T>(key: string, read: (signal: AbortSignal) => Promise<T>) {
  const [state, setState] = useState<{key: string; value?: T; error?: boolean}>({key});
  useEffect(() => {
    const controller = new AbortController();
    read(controller.signal).then(value => {if (!controller.signal.aborted) setState({key, value});})
      .catch(() => {if (!controller.signal.aborted) setState({key, error: true});});
    return () => controller.abort();
    // key defines the complete identity of this read, including selected student/page.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [key]);
  return state.key === key ? state : {key};
}
function ReadState({error}: {error?: boolean}) {return error ? <p role="alert">No se pudo cargar la información. Vuelve a seleccionar la sección para intentar nuevamente.</p> : <LoadingState text="Cargando información…"/>;}
export function Fields({value}: {value: object}) {
  return <dl className="meta-fields">{Object.entries(value).map(([key, item]: [string, unknown]) => <div key={key}><dt>{key}</dt><dd>{item === null || item === undefined ? 'No registrado' : typeof item === 'object' ? <pre>{JSON.stringify(item, null, 2)}</pre> : String(item)}</dd></div>)}</dl>;
}
function Pager({value, change}: {value: Page<unknown>; change: (page: number) => void}) {
  return <div className="meta-pagination"><button disabled={value.page === 0} onClick={() => change(value.page - 1)}>Anterior</button><span>Página {value.page + 1} · {value.total} registros</span><button disabled={(value.page + 1) * value.size >= value.total} onClick={() => change(value.page + 1)}>Siguiente</button></div>;
}
export function DecisionView({decision}: {decision: Decision | null}) {
  if (!decision) return <p>Sin adaptación registrada.</p>;
  return <article><h3>{decision.selectedRule ?? 'No se activó ninguna regla aplicable'}</h3><p>Acciones: {decision.actions.map(a => a.type).join(' · ') || 'Ninguna'}</p><p>Reglas coincidentes: {decision.rulesMatched.join(', ') || 'Ninguna'} · Contribuyentes: {decision.contributingRules.join(', ') || 'Ninguna'} · Descartadas: {decision.discardedRules.length}</p><details><summary>Detalles técnicos · adaptación y hashes</summary><Fields value={decision}/></details></article>;
}
export function TimelineView({trace}: {trace: Timeline}) {
  const {programHash, ...attemptSummary} = trace.attempt;
  return <section aria-label="Timeline del intento" className="meta-timeline"><h2>Trazabilidad del intento</h2><p className="meta-badge" data-state={trace.traceStatus}>{trace.traceStatus}</p>
    {trace.traceStatus === 'INCONSISTENT' && <p role="alert">Advertencia: evidencia inconsistente. Revisa los gaps antes de interpretar el intento.</p>}
    {trace.gaps.includes('LEGACY_NO_EVENTS') && <p>Trazabilidad parcial: intento creado antes de la telemetría completa.</p>}
    {trace.gaps.length > 0 && <p>Gaps: {trace.gaps.join(', ')}</p>}
    <p>Intento: <code>{trace.attemptId}</code><br/>Sesión: <code>{trace.sessionId ?? 'No registrada'}</code></p>
    <Fields value={attemptSummary}/><details><summary>Detalles técnicos · programa</summary><Fields value={{programHash}}/></details>
    <h3>Eventos en orden de secuencia</h3>
    {!trace.events.length && <p>Sin eventos registrados. No se reconstruyen eventos ficticios.</p>}
    <ol>{[...trace.events].sort((a, b) => a.sequence - b.sequence).map(event => <li key={event.id}><details><summary><Icon name="route"/><strong>{event.sequence}. {event.type}</strong> · {event.source} · <time>{event.occurredAt}</time></summary>{event.payload ? <Fields value={event.payload}/> : <p>Payload no disponible o inconsistente.</p>}<p>Hash: <code>{event.payloadHash}</code></p></details></li>)}</ol>
    <h3>Adaptación persistida</h3><DecisionView decision={trace.adaptation}/>
    <h3>Feedback</h3>{!trace.feedback.length && <p>No solicitado</p>}{trace.feedback.map(f => <article key={f.feedbackId}><h4>{f.provider} / {f.source}{f.provider === 'FAKE' ? ' · Proveedor de prueba' : ''}</h4><p>llmUsed: {String(f.llmUsed)} · Fallback: {f.fallbackReason ?? 'No'}</p><p>{f.message}</p>{f.question && <p>{f.question}</p>}<details><summary>Metadata del feedback</summary><Fields value={f}/></details></article>)}
    <section aria-label="Configuraciones UI auditadas"><h3>Configuraciones UI auditadas</h3>{!trace.uiConfigurations.length && <p>Sin configuración UI registrada.</p>}{trace.uiConfigurations.map((config, i) => <details key={`${config.configurationFingerprint}-${i}`}><summary>Detalles técnicos · configuración UI {i + 1}</summary><Fields value={config}/></details>)}
    <p>feedbackDetailLevel: no registrado en el contrato histórico de telemetría. Esta vista no vuelve a proyectar la UI.</p></section>
  </section>;
}
function Trace({student, attempt, loaded}: {student: string; attempt: string; loaded: (trace: Timeline) => void}) {
  const state = useRead(`${student}/${attempt}`, s => api.timeline(student, attempt, s));
  useEffect(() => {if (state.value) loaded(state.value);}, [state.value, loaded]);
  return state.value ? <TimelineView trace={state.value}/> : <ReadState error={state.error}/>;
}
function Model({student}: {student: string}) {
  const state = useRead(student, s => api.overview(student, s));
  if (!state.value) return <ReadState error={state.error}/>;
  const model = state.value;
  return <section><h2>Modelo del estudiante</h2><p>Versión {model.modelVersion} · Última interacción: {model.lastUpdated}</p><div className="meta-cards">{model.conceptMasteries.map(m => <article key={m.conceptId}><h3>{m.conceptId}</h3><p className="meta-score">{m.masteryScore} <small>({(m.masteryScore * 100).toFixed(1)} %)</small></p><MasteryBar value={m.masteryScore} label="Dominio del concepto"/><p className="mastery-attempts">{m.attemptCount} intentos · {m.successCount} aciertos · {m.failureCount} errores</p><details><summary>Detalles del aprendizaje</summary><Fields value={m}/></details></article>)}</div><h3>Progreso y prerrequisitos</h3><div className="meta-table"><table><thead><tr><th>Actividad</th><th>Estado</th><th>Completada</th><th>Prerrequisitos</th><th>Umbral mastery</th></tr></thead><tbody>{model.progress.levels.map(p => {const c=model.concepts.find(c => c.conceptId === p.conceptId);return <tr key={p.levelId}><th>{p.levelId}</th><td>{p.unlocked ? 'UNLOCKED' : 'LOCKED'}</td><td>{String(p.completed)}</td><td>{c?.prerequisites.join(', ') || 'Ninguno'}</td><td>{c?.masteryThreshold}</td></tr>;})}</tbody></table></div></section>;
}
function Attempts({student}: {student: string}) {
  const [page, setPage] = useState(0);const [selected, select] = useState<string | null>(null);
  const [statuses, setStatuses] = useState<Record<string, Timeline['traceStatus']>>({});
  const loaded = useCallback((trace: Timeline) => setStatuses(previous => ({...previous, [trace.attemptId]: trace.traceStatus})), []);
  const state = useRead(`${student}/${page}`, s => api.attempts(student, page, s));
  if (!state.value) return <ReadState error={state.error}/>;
  return <section><h2>Intentos</h2>{!state.value.items.length && <p>Sin intentos registrados.</p>}<div className="meta-table"><table><thead><tr><th>Actividad / concepto</th><th>Fecha</th><th>Funcional</th><th>Pedagógico</th><th>Patrones</th><th>Trazabilidad</th></tr></thead><tbody>{state.value.items.map(a => <tr key={a.attemptId}><th>{a.activityId} / {a.conceptId}</th><td>{a.submittedAt}</td><td>{String(a.functionalPassed)}</td><td>{String(a.activityPassed)}</td><td>{a.patterns.join(', ') || 'Sin patrones'}</td><td><span>{statuses[a.attemptId] ?? 'Pendiente de consulta'}</span> <button onClick={() => select(a.attemptId)} aria-label={`Abrir timeline ${a.attemptId}`}>Ver timeline</button></td></tr>)}</tbody></table></div><Pager value={state.value} change={setPage}/>{selected && <Trace key={`${student}/${selected}`} student={student} attempt={selected} loaded={loaded}/>}</section>;
}
function Adaptations({student}: {student: string}) {
  const [page, setPage]=useState(0);const state=useRead(`${student}/${page}`, s => api.adaptations(student, page, s));
  if (!state.value) return <ReadState error={state.error}/>;
  return <section><h2>Adaptaciones</h2>{!state.value.items.length && <p>Sin adaptaciones registradas.</p>}{state.value.items.map(a => <article key={a.decision.decisionId}><p>{a.activityId} / {a.conceptId} · {a.decision.createdAt}</p><DecisionView decision={a.decision}/></article>)}<Pager value={state.value} change={setPage}/></section>;
}
function Rules() {
  const state=useRead('rules', api.rules);if (!state.value) return <ReadState error={state.error}/>;
  const r=state.value;
  if (!r.rules.length) return <p role="alert">Reglas no disponibles. No se puede confirmar la configuración activa.</p>;
  return <section><h2>Reglas de adaptación</h2><p>Origen: {r.source} · rulesetVersion: {r.rulesetVersion}</p><details><summary>Detalles técnicos · rulesetHash</summary><code>{r.rulesetHash}</code></details>{r.rules.map(rule => <article key={rule.ruleId}><h3>{rule.name || rule.ruleId}</h3><p><strong>{rule.ruleId}</strong> · Prioridad {rule.priority} · {rule.enabled ? 'Activa' : 'Inactiva'} · Versión {rule.version}</p><p className="rule-event">{rule.eventType}</p><div className="rule-body"><div><h4>Condiciones</h4><pre>{rule.conditionSummary}</pre></div><div><h4>Acciones</h4><ul>{rule.actions.map((a, i) => <li key={i}>{a.type}{a.hintLevel && ` · ${a.hintLevel}`}{a.feedbackStyle && ` · ${a.feedbackStyle}`}</li>)}</ul></div></div></article>)}</section>;
}
function Parameters() {
  const state=useRead('parameters', api.parameters);if (!state.value) return <ReadState error={state.error}/>;
  return <section><h2>Parámetros activos</h2><p>Solo lectura · {state.value.source} · parametersVersion: {state.value.parametersVersion}</p><div className="parameter-grid"><article><h3>Ayuda y feedback</h3><Fields value={{hintLevel:state.value.values.hintLevel,feedbackDetail:state.value.values.feedbackDetail,maxHintsPerActivity:state.value.values.maxHintsPerActivity}}/></article><article><h3>Dificultad y ruta</h3><Fields value={{difficultyAdjustmentEnabled:state.value.values.difficultyAdjustmentEnabled,routeAdaptationEnabled:state.value.values.routeAdaptationEnabled,maxAttemptsBeforeReinforcement:state.value.values.maxAttemptsBeforeReinforcement}}/></article><article><h3>Umbrales</h3><Fields value={{failureThreshold:state.value.values.failureThreshold,successThreshold:state.value.values.successThreshold,masteryThreshold:state.value.values.masteryThreshold}}/></article></div><details><summary>Detalles técnicos · parametersHash</summary><Fields value={{version:state.value.values.version,parametersHash:state.value.parametersHash}}/></details></section>;
}
const sections=['Modelo del estudiante','Intentos','Adaptaciones','Reglas','Parámetros'] as const;
type Section=typeof sections[number];
export function MetaUiPage() {
  const [page, setPage]=useState(0);const [student, select]=useState('');const [section, setSection]=useState<Section>('Modelo del estudiante');
  const students=useRead(`students/${page}`, s => api.students(page, s));const caps=useRead('capabilities', api.capabilities);
  let content: ReactNode;
  if (section === 'Reglas') content=<Rules/>;
  else if (section === 'Parámetros') content=<Parameters/>;
  else if (!student) content=<div className="meta-empty"><Icon name="book"/><h2>El aprendizaje, en perspectiva</h2><p>Selecciona un estudiante para consultar su información.</p><p>Su progreso, intentos y adaptaciones aparecerán aquí.</p></div>;
  else if (section === 'Modelo del estudiante') content=<Model key={student} student={student}/>;
  else if (section === 'Intentos') content=<Attempts key={student} student={student}/>;
  else content=<Adaptations key={student} student={student}/>;
  return <div className="meta-ui"><header className="meta-header"><p className="eyebrow">Mi primera programación · Meta-IU</p><h1>Vista docente</h1><p>Consulta del aprendizaje, las reglas y la trazabilidad.</p><p className="meta-notice">Solo lectura · Sesión docente protegida.</p></header>
    {!caps.value ? <ReadState error={caps.error}/> : <details><summary>Capacidades disponibles</summary><Fields value={caps.value}/></details>}
    <section className="student-selector" aria-label="Selección de estudiante"><label htmlFor="meta-student">Estudiante pseudónimo</label>{!students.value ? <ReadState error={students.error}/> : <><select id="meta-student" value={student} onChange={e => select(e.target.value)}><option value="">Seleccionar estudiante</option>{students.value.items.map(s => <option key={s.studentId} value={s.studentId}>Estudiante …{s.studentId.slice(-8)} · {s.attemptCount} intentos</option>)}</select>{!students.value.items.length && <p>Sin estudiantes registrados.</p>}<Pager value={students.value} change={p => {select('');setPage(p);}}/></>}{student && <p>ID pseudónimo: <code>{student}</code></p>}</section>
    <div className="meta-workspace"><nav aria-label="Secciones docentes">{sections.map(s => <button key={s} aria-pressed={section === s} onClick={() => setSection(s)}>{s}</button>)}</nav>
    <div className="meta-content" key={`${student}/${section}`}>{content}</div></div>
  </div>;
}
