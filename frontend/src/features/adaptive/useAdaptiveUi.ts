import { useCallback, useEffect, useRef, useState } from 'react';
import { safeConfiguration, type UiResponse } from './types';
const base = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
async function read(path: string, signal: AbortSignal): Promise<UiResponse> {
  const response = await fetch(`${base}/api${path}`, { signal });
  if (!response.ok) throw new Error('UI unavailable');
  const data = await response.json() as UiResponse;
  if (data.configuration.configurationVersion !== 1) throw new Error('Unsupported UI version');
  return data;
}
export function useAdaptiveUi(studentId: string, activityId: string) {
  const empty = useCallback((): UiResponse => ({configuration: safeConfiguration(activityId), attemptId: null,
    feedback: null, code: null, fingerprint: '', safeDefault: true, reason: null}), [activityId]);
  const [value, setValue] = useState<UiResponse>(empty);
  const [loading, setLoading] = useState(true); const [error, setError] = useState(false);
  const pending = useRef<AbortController | null>(null);
  const load = useCallback(async (attemptId?: string) => {
    pending.current?.abort(); const controller = new AbortController(); pending.current = controller;
    const { signal } = controller; setLoading(true); setError(false);
    const root = `/students/${encodeURIComponent(studentId)}`;
    try {
      let next = await read(attemptId ? `${root}/attempts/${encodeURIComponent(attemptId)}/ui-configuration`
        : `${root}/activities/${encodeURIComponent(activityId)}/ui-configuration`, signal);
      // At most one feedback request and one re-read per explicit load, never an effect on config.
      if (next.attemptId && !next.feedback && next.configuration.enabledAssistance && !next.safeDefault) {
        const response = await fetch(`${base}/api/feedback/generate`, {method: 'POST', signal,
          headers: {'Content-Type': 'application/json'}, body: JSON.stringify({studentId, attemptId: next.attemptId})});
        if (response.ok) next = await read(`${root}/attempts/${encodeURIComponent(next.attemptId)}/ui-configuration`, signal);
      }
      if (!signal.aborted) setValue(next);
    } catch { if (!signal.aborted) {setValue(empty());setError(true);} }
    finally {if (!signal.aborted) setLoading(false);}
  }, [studentId, activityId, empty]);
  useEffect(() => {void load(); return () => pending.current?.abort();}, [load]);
  return {value, loading, error, load};
}
