import type { EvaluationResult } from './types';
import { Icon } from '../../shared/Visuals';

export function EvaluationFeedback({ evaluation }: { evaluation: EvaluationResult }) {
  return <section className="evaluation-feedback" aria-label="Evaluación de tu solución" data-passed={evaluation.activityPassed}>
    <h3><Icon name={evaluation.activityPassed ? 'check' : 'spark'}/>Tu solución</h3>
    <p className="evaluation-result" data-passed={evaluation.functionalCorrectness.passed}><Icon name={evaluation.functionalCorrectness.passed ? 'check' : 'flag'}/>Recorrido: {evaluation.functionalCorrectness.passed ? 'meta alcanzada' : 'por completar'}.</p>
    <p className="evaluation-result" data-passed={evaluation.structuralCorrectness.requiredConceptUsed}><Icon name={evaluation.structuralCorrectness.requiredConceptUsed ? 'check' : 'book'}/>Concepto del nivel: {evaluation.structuralCorrectness.requiredConceptUsed ? 'aplicado' : 'por practicar'}.</p>
    {evaluation.patterns.length > 0 && <ul aria-label="Recomendaciones">{evaluation.patterns.map((pattern, index) =>
      <li key={`${pattern.id}-${index}`} data-pattern={pattern.id}>
        <p>{pattern.pedagogicalMeaning}</p><p><strong>Prueba esto:</strong> {pattern.recommendedAction}</p>
      </li>)}</ul>}
  </section>;
}
