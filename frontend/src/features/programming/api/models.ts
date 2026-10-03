import { httpFetch, checkResponse } from '../../../shared/http';
import type { ModelResult, ProgramDto } from '../dto/program';

const baseUrl = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export async function createModel(dto: ProgramDto): Promise<ModelResult> {
  const response = await httpFetch(`${baseUrl}/api/programming/models`, {
    method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(dto),
  });
  checkResponse(response, [400, 422]);
  return response.json() as Promise<ModelResult>;
}
