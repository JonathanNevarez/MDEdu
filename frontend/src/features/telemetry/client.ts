import { httpFetch, checkResponse } from '../../shared/http';
const base = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export const sessionKey = 'mdedu.session.v1';
interface Session { id: string; studentId: string; status: 'ACTIVE' | 'ENDED' }
let current: Session | null = null;
let pending: { studentId: string; promise: Promise<Session> } | null = null;
export let lastRequestId: string | null = null;
function remember(session: Session) {
  current = session;
  try { sessionStorage.setItem(sessionKey, JSON.stringify(session)); } catch { /* In-memory session only. */ }
  return session;
}
async function initialize(studentId: string): Promise<Session> {
  let stored = current;
  try { stored = JSON.parse(sessionStorage.getItem(sessionKey) ?? 'null') as Session | null; } catch { /* Replace malformed storage. */ }
  if (stored?.studentId === studentId && /^[0-9a-f-]{36}$/i.test(stored.id)) {
    const response = await httpFetch(`${base}/api/sessions/${stored.id}?studentId=${studentId}`);
    if (response.ok) { const session = await response.json() as Session; if (session.status === 'ACTIVE') return remember(session); }
    else if (response.status !== 404 && response.status !== 409) checkResponse(response);
  }
  const response = await httpFetch(`${base}/api/sessions`, { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({ studentId }) });
  if (!response.ok) checkResponse(response);
  return remember(await response.json() as Session);
}
export function ensureSession(studentId: string): Promise<Session> {
  if (current?.studentId === studentId && current.status === 'ACTIVE') return Promise.resolve(current);
  if (pending?.studentId === studentId) return pending.promise;
  const promise = initialize(studentId).finally(() => { if (pending?.promise === promise) pending = null; });
  pending = { studentId, promise }; return promise;
}
export async function studentFetch(studentId: string, path: string, init: RequestInit = {}): Promise<Response> {
  const session = await ensureSession(studentId);
  const headers = new Headers(init.headers); headers.set('X-Session-Id', session.id);
  let response = await httpFetch(`${base}/api${path}`, { ...init, headers });
  if (response.status === 404 || response.status === 409) {
    // Only retry when the session itself is stale. Business 404/409 never imply a retry.
    const check = await httpFetch(`${base}/api/sessions/${session.id}?studentId=${studentId}`, init.signal ? {signal: init.signal} : {});
    const ended = check.ok && (await check.json() as Session).status === 'ENDED';
    if (check.status === 404 || ended) {
      if (current?.id === session.id) {
        current = null; try { sessionStorage.removeItem(sessionKey); } catch { /* Memory fallback. */ }
      }
      const replacement = await ensureSession(studentId); headers.set('X-Session-Id', replacement.id);
      response = await httpFetch(`${base}/api${path}`, { ...init, headers }); // At most one retry.
    }
  }
  const id = response.headers?.get('X-Request-Id');
  lastRequestId = id && /^[0-9a-f-]{36}$/i.test(id) ? id : null;
  return response;
}
const sent = new Map<string, Promise<void>>();
export function recordInteraction(studentId: string, activityId: string, type: 'ACTIVITY_OPENED' | 'NAVIGATION_PRESENTED', clientEventId: string, attemptId?: string, configurationFingerprint?: string): Promise<void> {
  const key = `${studentId}:${clientEventId}`;
  const existing = sent.get(key); if (existing) return existing;
  const promise = studentFetch(studentId, '/telemetry/events', { method: 'POST', headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ studentId, activityId, type, clientEventId, attemptId, configurationFingerprint }) }).then(response => {
    checkResponse(response);
  }).catch(() => { sent.delete(key); /* UI remains usable; do not fabricate successful telemetry. */ });
  sent.set(key, promise);
  if (sent.size > 256) sent.delete(sent.keys().next().value!);
  return promise;
}
