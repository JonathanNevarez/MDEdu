import type { EvaluationResult } from './types';
import { Icon } from '../../shared/Visuals';

const criterionMessages: Record<string, string> = {
  allowedBlocks: 'Usa los bloques disponibles para este concepto.',
  orderedItems: 'Recoge las llaves en el orden indicado antes de terminar.',
  variableCheckpoints: 'Actualiza la variable en los puntos indicados y lee cada valor antes de cambiarlo.',
  executedVariableReads: 'Consulta la variable: no basta con declararla o cambiarla.',
  meaningfulDecisions: 'Haz que cada decisión necesaria controle movimientos del recorrido.',
  integrationConstructs: 'Combina los conceptos indicados en el objetivo del reto.',
};
export function EvaluationFeedback({ evaluation }: { evaluation: EvaluationResult }) {
  const pending = Object.entries(evaluation.structuralCorrectness.structuralConstraints)
    .filter(([key, passed]) => !passed && criterionMessages[key]).map(([key]) => criterionMessages[key]!);
  return <section className="evaluation-feedback" aria-label="Evaluación de tu solución" data-passed={evaluation.activityPassed}>
    <h3><Icon name={evaluation.activityPassed ? 'check' : 'spark'}/>Tu solución</h3>
    <p className="evaluation-result" data-passed={evaluation.functionalCorrectness.passed}><Icon name={evaluation.functionalCorrectness.passed ? 'check' : 'flag'}/>Recorrido: {evaluation.functionalCorrectness.passed ? 'meta alcanzada' : 'por completar'}.</p>
    <p className="evaluation-result" data-passed={evaluation.structuralCorrectness.requiredConceptUsed}><Icon name={evaluation.structuralCorrectness.requiredConceptUsed ? 'check' : 'book'}/>Concepto del reto: {evaluation.structuralCorrectness.requiredConceptUsed ? 'aplicado' : 'por practicar'}.</p>
    {pending.length > 0 && <ul aria-label="Objetivos por completar">{pending.map(message => <li key={message}>{message}</li>)}</ul>}
    {evaluation.patterns.length > 0 && <ul aria-label="Recomendaciones">{evaluation.patterns.map((pattern, index) =>
      <li key={`${pattern.id}-${index}`} data-pattern={pattern.id}>
        <p>{pattern.pedagogicalMeaning}</p><p><strong>Prueba esto:</strong> {pattern.recommendedAction}</p>
      </li>)}</ul>}
  </section>;
}
