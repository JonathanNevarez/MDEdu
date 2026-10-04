import {EvaluationFeedback} from '../game/EvaluationFeedback';
import type {EvaluationResult} from '../game/types';
import { useEffect, useRef, useState } from 'react';
import { Link } from 'react-router-dom';
import type { Feedback, HintStage, UiConfiguration, UiResponse } from './types';
import './adaptive.css';
import { Icon, LumaPortrait } from '../../shared/Visuals';
const stageLabels: Record<HintStage, string> = {NONE: 'Ayuda', SOCRATIC_QUESTION: 'Una pregunta para pensar',
  CONCEPTUAL_HINT: 'Pista conceptual', ANALOGOUS_EXAMPLE: 'Ejemplo parecido', PARTIAL_HELP: 'Ayuda parcial'};
export function HintPanel({hintStage, message, question, focus}: Feedback) {
  return <section className="hint-panel" aria-label="Pista"><h3><Icon name="spark"/>{stageLabels[hintStage]}</h3><p>{message}</p>
    {question && <p>{question}</p>}{focus && <p><strong>Observa: </strong>{focus}</p>}</section>;
}
export function CodePanel({text}: {text: string | null}) {
  return <section className="code-panel" aria-label="Código generado"><h2>Tu programa en código</h2>
    <p>Vista de lectura de tu último intento.</p>{text === null ? <p>El código de este intento no está disponible.</p> : <pre tabIndex={0}><code>{text}</code></pre>}</section>;
}
export function LumaTutor({mode, success, message}: {mode: UiConfiguration['tutorMode']; success: boolean; message: string | null}) {
  if (mode === 'HIDDEN') return null;
  const state = success ? 'SUCCESS' : mode === 'GUIDE' ? 'GUIDE' : mode === 'HINT' ? 'HINT' : mode === 'FEEDBACK' ? 'FEEDBACK' : 'IDLE';
  return <section className="luma-tutor" aria-label="Luma" data-state={state}>
    <LumaPortrait state={state}/><div><h2>Luma — Tu tutora <span className="luma-state">{{SUCCESS:'¡Lo lograste!', GUIDE:'Vamos paso a paso', HINT:'Una pista para ti', FEEDBACK:'Miremos tu intento', IDLE:'A tu lado'}[state]}</span></h2>
      <p>{success ? '¡Buen trabajo! Has completado este reto.' : message ?? 'Puedes explorar tus bloques y probar tu programa.'}</p></div></section>;
}
export function Transitioner({configuration}: {configuration: UiConfiguration}) {
  const previous = useRef(configuration); const [notice, setNotice] = useState('');
  useEffect(() => {
    const before = previous.current; previous.current = configuration;
    if (configuration.transitionMode === 'NONE') {setNotice('');return;}
    const changes: string[] = [];
    if (before.hintPanelMode !== configuration.hintPanelMode) changes.push('La presentación de la ayuda cambió.');
    if (before.showCodePanel !== configuration.showCodePanel) changes.push(configuration.showCodePanel ? 'La vista de código está disponible.' : 'La vista de código se ocultó.');
    if (before.activityLayout !== configuration.activityLayout) changes.push('La distribución de tu espacio cambió.');
    if (before.navigationMode !== configuration.navigationMode) changes.push('Hay una nueva orientación para continuar.');
    setNotice(changes.join(' '));
  }, [configuration]);
  return <p className={`ui-transition ${configuration.transitionMode.toLowerCase()}`} role="status" aria-live="polite">{notice}</p>;
}
export function AdaptiveRenderer({value, loading, error, success, onRepeat, evaluation}: {
  value: UiResponse; loading: boolean; error: boolean; success: boolean; onRepeat: () => void; evaluation?: EvaluationResult | null;
}) {
  const c = value.configuration;
  return <aside aria-label="Luma — Tu tutora" className="adaptive-panels" data-layout={c.activityLayout} data-difficulty={c.difficultyMode}>
    <Transitioner configuration={c}/>
    {loading && <p role="status">Preparando tu espacio…</p>}
    {error && <p role="status">La ayuda no está disponible. Puedes seguir trabajando en el reto.</p>}
    {c.difficultyMode !== 'STANDARD' && <p className="experience-mode">{c.difficultyMode === 'REDUCED' ? 'Presentación con apoyo visual' : 'Presentación enfocada'} · El objetivo del reto se mantiene.</p>}
    <LumaTutor mode={c.tutorMode === 'HIDDEN' ? 'GUIDE' : c.tutorMode} success={success} message={c.hintPanelMode !== 'HIDDEN' ? 'Observa la pista y prueba tu idea.' : c.feedbackMessage ?? 'Piensa en el recorrido antes de conectar tus bloques.'}/>
    {evaluation && <EvaluationFeedback evaluation={evaluation}/>}
    {c.hintPanelMode !== 'HIDDEN' && <div className={`hint-${c.hintPanelMode.toLowerCase()}`}><HintPanel
      hintStage={c.hintStage} message={value.feedback?.message ?? c.feedbackMessage ?? 'Revisa los bloques y vuelve a probar.'}
      question={value.feedback?.question ?? null} focus={value.feedback?.focus ?? null}/></div>}
    {value.feedback && c.hintPanelMode === 'HIDDEN' && <section aria-label="Orientación" className={`feedback-${c.feedbackDetailLevel.toLowerCase()}`}>
      <h2>Orientación</h2><p>{value.feedback.message}</p>{c.feedbackDetailLevel === 'DETAILED' && <><p>{value.feedback.question}</p><p>{value.feedback.focus}</p></>}</section>}
    <details className="model-details"><summary>Ver código</summary><p>El programa se transforma en un modelo EMF al ejecutar. Esta representación de lectura proviene del backend.</p>{c.showCodePanel && c.generatedCodeVisible ? <CodePanel text={value.code?.available ? value.code.text : null}/> : <p>La vista de código no está habilitada para este intento.</p>}</details>
    {c.navigationMode === 'REPEAT' && c.repeatCurrentActivity && <button onClick={onRepeat}>Intentar de nuevo</button>}
    {success && !(c.navigationMode === 'ADVANCE' && c.nextActivityId) && <Link className="adaptive-continue" to="/aprender">Continuar</Link>}
    {c.navigationMode === 'ADVANCE' && c.nextActivityId && <Link className="adaptive-continue" to={`/aprender/${encodeURIComponent(c.nextActivityId)}`}>Continuar</Link>}
  </aside>;
}
