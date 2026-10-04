import { describe, expect, it } from 'vitest';
import { render, screen } from '@testing-library/react';
import { EvaluationFeedback } from '../../src/features/game/EvaluationFeedback';
import { applyEvaluation, evaluationMessage } from '../../src/features/game/evaluation';
import { emptyProgress } from '../../src/features/game/progress';
import type { EvaluationResult, ExecutionResult } from '../../src/features/game/types';

const evaluation: EvaluationResult = {
  evaluationVersion: 1, levelId: 'LOOPS', activityPassed: false,
  functionalCorrectness: { passed: true, goalReached: true, executionStatus: 'COMPLETED', runtimeError: false, steps: 7 },
  structuralCorrectness: { requiredConcept: 'LOOPS', requiredConceptUsed: false, requiredConstructsSatisfied: false,
    constraintsSatisfied: false, structuralConstraints: { meaningfulUse: false } },
  pedagogicalEfficiency: { status: 'NEEDS_RETRY' },
  patterns: [{ id: 'REPETITIVE_SEQUENCE_WITHOUT_LOOP', concept: 'LOOPS', severity: 'ERROR',
    pedagogicalMeaning: 'Tu programa repite manualmente varias veces las mismas instrucciones.',
    recommendedAction: 'Prueba a representar esa repetición con un ciclo.', defaultHintLevel: 1,
    evidence: { statementPath: 'statements[0]', traceIndex: null, observed: 'repetitions=7', expected: 'loop' } }],
};
const result: ExecutionResult = { success: true, status: 'COMPLETED', steps: 7, finalState: null, trace: [], errors: [], evaluation };
describe('Evaluación determinista de un intento', () => {
  it('explica objetivos pendientes aunque la meta esté alcanzada', () => {
    render(<EvaluationFeedback evaluation={{...evaluation, patterns: [], structuralCorrectness: {...evaluation.structuralCorrectness, structuralConstraints: {variableCheckpoints: false, orderedItems: true}}}} />);
    expect(screen.getByRole('list', {name: 'Objetivos por completar'})).toHaveTextContent('lee cada valor antes de cambiarlo');
    expect(screen.queryByText('Recoge las llaves en el orden indicado antes de terminar.')).not.toBeInTheDocument();
  });
  it('functional fail invita a intentar sin declarar completado', () => {
    expect(evaluationMessage({ ...result, success: false })).toBe('No alcanzaste la meta todavía. Intenta nuevamente.');
  });
  it('separa llegada funcional de aprobación pedagógica', () => {
    expect(evaluationMessage(result)).toBe('¡Llegaste a la meta! Pero todavía falta aplicar el concepto de este reto.');
  });
  it('activityPassed muestra completado', () => {
    expect(evaluationMessage({ ...result, evaluation: { ...evaluation, activityPassed: true } })).toBe('¡Reto completado!');
  });
  it('renderiza significado y acción recomendada del catálogo', () => {
    render(<EvaluationFeedback evaluation={evaluation} />);
    expect(screen.getByText(evaluation.patterns[0]!.pedagogicalMeaning)).toBeVisible();
    expect(screen.getByText(/Prueba a representar esa repetición con un ciclo/)).toBeVisible();
    expect(screen.getByText('Recorrido: meta alcanzada.')).toBeVisible();
    expect(screen.getByText('Concepto del reto: por practicar.')).toBeVisible();
  });
  it('el progreso no avanza por éxito funcional sin aprobación', () => {
    const progress = emptyProgress();
    expect(applyEvaluation(progress, 'LOOPS', result.evaluation)).toBe(progress);
    expect(applyEvaluation(progress, 'LOOPS', null)).toBe(progress);
    expect(applyEvaluation(progress, 'LOOPS', undefined)).toBe(progress);
  });
  it('el progreso avanza exclusivamente con activityPassed', () => {
    expect(applyEvaluation(emptyProgress(), 'LOOPS', { ...evaluation, activityPassed: true })).toEqual({ version: 1, completedLevelIds: ['LOOPS'] });
  });
  it('un intento posterior fallido no borra completados previos', () => {
    const completed = { version: 1 as const, completedLevelIds: ['SEQUENCES', 'VARIABLES', 'CONDITIONALS', 'LOOPS'] };
    expect(applyEvaluation(completed, 'LOOPS', evaluation)).toEqual(completed);
    expect(applyEvaluation(completed, 'LOOPS', { ...evaluation, activityPassed: true })).toEqual(completed);
  });
  it('respuesta antigua sin evaluación nunca implica completar', () => {
    expect(evaluationMessage({ ...result, evaluation: null })).toContain('evaluación no está disponible');
  });
});
