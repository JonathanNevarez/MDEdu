import type { ProgramDto } from '../programming/dto/program';
import type { Catalog, ExecutionResult } from './types';
const base = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export async function getCatalog(signal?: AbortSignal): Promise<Catalog> {
  const response = await fetch(`${base}/api/game/levels`, { signal });
  if (!response.ok) throw new Error('No se pudo cargar la aventura. Comprueba la conexión e inténtalo de nuevo.');
  return response.json() as Promise<Catalog>;
}
export async function execute(id: string, program: ProgramDto, signal: AbortSignal): Promise<ExecutionResult> {
  const response = await fetch(`${base}/api/game/levels/${encodeURIComponent(id)}/execute`, {
    method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(program), signal,
  });
  if (![200, 400, 422].includes(response.status)) throw new Error('No se pudo ejecutar. Inténtalo de nuevo.');
  return response.json() as Promise<ExecutionResult>;
}
