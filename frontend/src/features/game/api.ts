import { httpFetch, checkResponse } from '../../shared/http';
import type { ProgramDto } from '../programming/dto/program';
import type { Catalog, ExecutionResult } from './types';
const base = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export async function getCatalog(signal?: AbortSignal): Promise<Catalog> {
  const response = await httpFetch(`${base}/api/game/levels`, { signal });
  checkResponse(response);
  return response.json() as Promise<Catalog>;
}
export async function execute(id: string, program: ProgramDto, signal: AbortSignal): Promise<ExecutionResult> {
  const response = await httpFetch(`${base}/api/game/levels/${encodeURIComponent(id)}/execute`, {
    method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(program), signal,
  });
  checkResponse(response, [400, 422]);
  return response.json() as Promise<ExecutionResult>;
}
