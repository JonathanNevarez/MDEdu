export type Position = { x: number; y: number };
export type Direction = 'NORTH' | 'EAST' | 'SOUTH' | 'WEST';
export interface WorldConfig { width: number; height: number; playerPosition: Position; playerDirection: Direction;
  goalPosition: Position; obstacles: Position[]; keys: Position[]; doors: Position[] }
export interface Level { id: string; concept: string; title: string; description: string; order: number;
  prerequisiteLevelIds: string[]; worldConfig: WorldConfig; allowedBlockGroups: string[] }
export interface Catalog { version: number; levels: Level[] }
export interface WorldState { playerPosition: Position; playerDirection: Direction; hasKey: boolean;
  keys: Position[]; doors: { position: Position; open: boolean }[];
  variables: { id: string; name: string; type: string; value: number | boolean }[]; atGoal: boolean }
export interface TraceEvent { index: number; type: string; detail: string; state: WorldState; statementPath?: string | null }
export interface EvaluationResult {
  evaluationVersion: number; levelId: string; activityPassed: boolean;
  functionalCorrectness: { passed: boolean; goalReached: boolean; executionStatus: string; runtimeError: boolean; steps: number };
  structuralCorrectness: { requiredConcept: string; requiredConceptUsed: boolean; requiredConstructsSatisfied: boolean;
    constraintsSatisfied: boolean; structuralConstraints: Record<string, boolean> };
  pedagogicalEfficiency: { status: 'OK' | 'NEEDS_REVIEW' | 'NEEDS_RETRY' };
  patterns: { id: string; concept: string; severity: 'INFO' | 'WARNING' | 'ERROR'; pedagogicalMeaning: string;
    recommendedAction: string; defaultHintLevel: number;
    evidence: { statementPath: string; traceIndex: number | null; observed: string; expected: string } }[];
}
export interface ExecutionResult { success: boolean; status: string; steps: number; finalState: WorldState | null;
  trace: TraceEvent[]; errors: { code: string; message: string }[]; evaluation?: EvaluationResult | null }
