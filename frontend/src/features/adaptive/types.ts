export type HintStage = 'NONE' | 'SOCRATIC_QUESTION' | 'CONCEPTUAL_HINT' | 'ANALOGOUS_EXAMPLE' | 'PARTIAL_HELP';
export interface UiConfiguration {
  configurationVersion: 1; activityId: string; showCodePanel: boolean;
  hintPanelMode: 'HIDDEN' | 'COMPACT' | 'EXPANDED'; feedbackDetailLevel: 'MINIMAL' | 'STANDARD' | 'DETAILED';
  activityLayout: 'STANDARD' | 'ASSISTED' | 'FOCUSED'; enabledAssistance: boolean;
  navigationMode: 'STAY' | 'REPEAT' | 'ADVANCE'; difficultyMode: 'REDUCED' | 'STANDARD' | 'INCREASED';
  tutorMode: 'HIDDEN' | 'GUIDE' | 'HINT' | 'FEEDBACK' | 'SUCCESS'; transitionMode: 'NONE' | 'SUBTLE' | 'NOTICEABLE';
  nextActivityId: string | null; repeatCurrentActivity: boolean; hintStage: HintStage;
  feedbackMessage: string | null; generatedCodeVisible: boolean;
}
export interface Feedback { message: string; question: string | null; focus: string | null; hintStage: HintStage }
export interface UiResponse { configuration: UiConfiguration; attemptId: string | null; feedback: Feedback | null;
  code: { available: boolean; text: string | null; reason: string | null } | null;
  fingerprint: string; safeDefault: boolean; reason: string | null }
export const safeConfiguration = (activityId: string): UiConfiguration => ({configurationVersion: 1, activityId,
  showCodePanel: false, hintPanelMode: 'HIDDEN', feedbackDetailLevel: 'STANDARD', activityLayout: 'STANDARD',
  enabledAssistance: false, navigationMode: 'STAY', difficultyMode: 'STANDARD', tutorMode: 'HIDDEN', transitionMode: 'NONE',
  nextActivityId: null, repeatCurrentActivity: false, hintStage: 'NONE', feedbackMessage: null, generatedCodeVisible: false});
