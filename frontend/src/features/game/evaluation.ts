import type { EvaluationResult, ExecutionResult } from './types';
import { completeLevel } from './progress';
import type { Progress } from './progress';

export function applyEvaluation(progress: Progress, levelId: string, evaluation?: EvaluationResult | null): Progress {
  return evaluation?.activityPassed === true ? completeLevel(progress, levelId) : progress;
}
export function evaluationMessage(result: ExecutionResult): string {
  if (result.evaluation?.activityPassed) return '¡Nivel completado!';
  if (!result.success) return 'No alcanzaste la meta todavía. Intenta nuevamente.';
  if (!result.evaluation) return 'La evaluación no está disponible. Intenta nuevamente.';
  return '¡Llegaste a la meta! Pero todavía falta aplicar el concepto de este nivel.';
}
