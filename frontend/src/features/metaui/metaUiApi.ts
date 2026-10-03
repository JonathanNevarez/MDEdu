import type { Adaptation, Attempt, Capabilities, Overview, Page, Parameters, Rules, StudentSummary, Timeline } from './types';
const base = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
// The prototype uses native fetch; teacher reads must not initialize a student/session.
async function get<T>(path: string, signal: AbortSignal): Promise<T> {
  const response = await fetch(`${base}/api${path}`, {signal, method: 'GET'});
  if (!response.ok) throw new Error('No se pudo cargar la información. Intenta nuevamente.');
  return response.json() as Promise<T>;
}
const id = encodeURIComponent;
export const metaUiApi = {
  capabilities: (s: AbortSignal) => get<Capabilities>('/meta/capabilities', s),
  students: (page: number, s: AbortSignal) => get<Page<StudentSummary>>(`/meta/students?page=${page}&size=20`, s),
  overview: (student: string, s: AbortSignal) => get<Overview>(`/meta/students/${id(student)}`, s),
  attempts: (student: string, page: number, s: AbortSignal) => get<Page<Attempt>>(`/meta/students/${id(student)}/attempts?page=${page}&size=20`, s),
  adaptations: (student: string, page: number, s: AbortSignal) => get<Page<Adaptation>>(`/meta/students/${id(student)}/adaptations?page=${page}&size=20`, s),
  rules: (s: AbortSignal) => get<Rules>('/adaptation/rules', s),
  parameters: (s: AbortSignal) => get<Parameters>('/adaptation/parameters', s),
  timeline: (student: string, attempt: string, s: AbortSignal) => get<Timeline>(`/students/${id(student)}/attempts/${id(attempt)}/timeline`, s),
};
