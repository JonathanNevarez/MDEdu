import { afterEach, expect, it, vi } from 'vitest';
vi.mock('../../src/features/telemetry/client', () => ({
  ensureSession: vi.fn(async () => ({id: 'session-fixture', status: 'ACTIVE'})),
  studentFetch: (_student: string, path: string, init?: RequestInit) => fetch(`http://127.0.0.1:8080/api${path}`, init),
}));
import { renderHook, waitFor } from '@testing-library/react';
import { useAdaptiveUi } from '../../src/features/adaptive/useAdaptiveUi';
import { safeConfiguration, type UiResponse } from '../../src/features/adaptive/types';
afterEach(()=>vi.unstubAllGlobals());
it('assistance loads config, feedback and config once; rerender never repeats the provider request',async()=>{
 const initial:UiResponse={configuration:{...safeConfiguration('LOOPS'),enabledAssistance:true,hintPanelMode:'COMPACT',hintStage:'CONCEPTUAL_HINT',tutorMode:'HINT'},attemptId:'attempt',feedback:null,code:null,fingerprint:'a',safeDefault:false,reason:null};
 const final:UiResponse={...initial,feedback:{message:'Una pista',question:null,focus:null,hintStage:'CONCEPTUAL_HINT'}};
 const fetcher=vi.fn().mockResolvedValueOnce(new Response(JSON.stringify(initial))).mockResolvedValueOnce(new Response('{}')).mockResolvedValueOnce(new Response(JSON.stringify(final)));
 vi.stubGlobal('fetch',fetcher);
 const {result,rerender}=renderHook(()=>useAdaptiveUi('student','LOOPS'));
 await waitFor(()=>expect(result.current.loading).toBe(false));expect(result.current.value.feedback?.message).toBe('Una pista');
 rerender();expect(fetcher).toHaveBeenCalledTimes(3);expect(fetcher.mock.calls[1]?.[1].body).toBe(JSON.stringify({studentId:'student',attemptId:'attempt'}));
});
it('failed configuration request produces safe default without retry loop',async()=>{
 const fetcher=vi.fn().mockRejectedValue(new Error('offline'));vi.stubGlobal('fetch',fetcher);
 const {result}=renderHook(()=>useAdaptiveUi('student','LOOPS'));
 await waitFor(()=>expect(result.current.loading).toBe(false));expect(result.current.error).toBe(true);
 expect(result.current.value.configuration.navigationMode).toBe('STAY');expect(result.current.value.configuration.showCodePanel).toBe(false);expect(fetcher).toHaveBeenCalledTimes(1);
});
