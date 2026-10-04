import {studentMe} from '../student/studentAuth';
import { httpFetch, ApiError, checkResponse } from '../../shared/http';
import { ensureSession, studentFetch } from '../telemetry/client';
import type { ProgramDto } from '../programming/dto/program';
import type { ExecutionResult } from './types';

const base = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export const studentKey = 'mdedu.student.id.v1';
export interface ProgressEntry {
  levelId: string; conceptId: string; completed: boolean; unlocked: boolean; masteryScore: number; attemptCount: number;
}
export interface StudentProgress { levels: ProgressEntry[] }
export interface AttemptResponse { attemptId: string; execution: ExecutionResult; progress: StudentProgress }
async function request<T>(path: string, body?: unknown, signal?: AbortSignal): Promise<T> {
  const response = await httpFetch(`${base}/api${path}`, { ...(body === undefined ? {} : {
    method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body),
  }), ...(signal ? { signal } : {}) });
  checkResponse(response);
  return response.json() as Promise<T>;
}
let initializing: Promise<{ studentId: string; progress: StudentProgress }> | null = null;
async function initialize() {
  const identity=await studentMe();
  if(!identity)throw new ApiError(401);
  const id=identity.studentId;
  const progress=await request<StudentProgress>(`/students/${encodeURIComponent(id)}/progress`);
  // Compatibility display cache only. Never read to select identity or claim historical data.
  try {localStorage.setItem(studentKey,id);localStorage.removeItem('mdedu.game.progress.v1');}catch{/* Optional cache. */}
  await ensureSession(id);
  return {studentId:id,progress};
}
export function ensureStudent() {
  // React StrictMode and concurrent mounts share the authenticated identity lookup.
  initializing ??= initialize().finally(() => { initializing = null; });
  return initializing;
}
export function serverLevelStatus(id: string, progress: StudentProgress) {
  const entry = progress.levels.find(level => level.levelId === id);
  return entry?.completed ? 'COMPLETED' : entry?.unlocked ? 'UNLOCKED' : 'LOCKED';
}
export async function submitAttempt(studentId: string, levelId: string, program: ProgramDto, resolutionTimeMs: number, signal: AbortSignal) {
  const response = await studentFetch(studentId, '/attempts', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({studentId, levelId, program, resolutionTimeMs, hintCount: 0}), signal});
  checkResponse(response);
  return response.json() as Promise<AttemptResponse>;
}
