import type { ProgramDto } from '../programming/dto/program';
import type { ExecutionResult } from './types';

const base = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export const studentKey = 'mdedu.student.id.v1';
export interface ProgressEntry {
  levelId: string; conceptId: string; completed: boolean; unlocked: boolean; masteryScore: number; attemptCount: number;
}
export interface StudentProgress { levels: ProgressEntry[] }
export interface AttemptResponse { attemptId: string; execution: ExecutionResult; progress: StudentProgress }
class HttpError extends Error { constructor(public status: number) { super('No se pudo guardar o cargar tu avance. Comprueba la conexión e inténtalo de nuevo.'); } }
async function request<T>(path: string, body?: unknown, signal?: AbortSignal): Promise<T> {
  const response = await fetch(`${base}/api${path}`, { ...(body === undefined ? {} : {
    method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body),
  }), ...(signal ? { signal } : {}) });
  if (!response.ok) throw new HttpError(response.status);
  return response.json() as Promise<T>;
}
let sessionId: string | null = null;
let initializing: Promise<{ studentId: string; progress: StudentProgress }> | null = null;
async function initialize() {
  let id = sessionId;
  try { id = localStorage.getItem(studentKey) ?? id; } catch { /* Session identity still works without storage. */ }
  async function create() {
    const student = await request<{ id: string }>('/students', {});
    sessionId = student.id;
    try { localStorage.setItem(studentKey, student.id); } catch { /* Keep only the identity in memory. */ }
    return student.id;
  }
  if (!id) id = await create();
  let progress: StudentProgress;
  try { progress = await request<StudentProgress>(`/students/${encodeURIComponent(id)}/progress`); }
  catch (error) {
    if (!(error instanceof HttpError) || error.status !== 404) throw error;
    id = await create(); // Exactly one recovery; errors on the replacement propagate.
    progress = await request<StudentProgress>(`/students/${encodeURIComponent(id)}/progress`);
  }
  sessionId = id;
  try { localStorage.removeItem('mdedu.game.progress.v1'); } catch { /* Legacy progress is never read. */ }
  return { studentId: id, progress };
}
export function ensureStudent() {
  // React StrictMode and concurrent mounts share creation, never duplicate a student.
  initializing ??= initialize().finally(() => { initializing = null; });
  return initializing;
}
export function serverLevelStatus(id: string, progress: StudentProgress) {
  const entry = progress.levels.find(level => level.levelId === id);
  return entry?.completed ? 'COMPLETED' : entry?.unlocked ? 'UNLOCKED' : 'LOCKED';
}
export function submitAttempt(studentId: string, levelId: string, program: ProgramDto, resolutionTimeMs: number, signal: AbortSignal) {
  return request<AttemptResponse>('/attempts', { studentId, levelId, program, resolutionTimeMs, hintCount: 0 }, signal);
}
