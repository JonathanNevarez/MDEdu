import type { ModelResult, ProgramDto } from '../dto/program';

const baseUrl = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export async function createModel(dto: ProgramDto): Promise<ModelResult> {
  const response = await fetch(`${baseUrl}/api/programming/models`, {
    method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(dto),
  });
  if (![200, 400, 422].includes(response.status)) throw new Error('No se pudo construir el modelo. Intenta nuevamente.');
  return response.json() as Promise<ModelResult>;
}
