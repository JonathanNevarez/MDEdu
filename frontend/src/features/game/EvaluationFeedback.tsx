import type { EvaluationResult } from './types';

export function EvaluationFeedback({ evaluation }: { evaluation: EvaluationResult }) {
  return <section className="evaluation-feedback" aria-label="Evaluación de tu solución">
    <h3>Tu solución</h3>
    <p>Recorrido: {evaluation.functionalCorrectness.passed ? 'meta alcanzada' : 'por completar'}.</p>
    <p>Concepto del nivel: {evaluation.structuralCorrectness.requiredConceptUsed ? 'aplicado' : 'por practicar'}.</p>
    {evaluation.patterns.length > 0 && <ul aria-label="Recomendaciones">{evaluation.patterns.map((pattern, index) =>
      <li key={`${pattern.id}-${index}`} data-pattern={pattern.id}>
        <p>{pattern.pedagogicalMeaning}</p><p><strong>Prueba esto:</strong> {pattern.recommendedAction}</p>
      </li>)}</ul>}
  </section>;
}
