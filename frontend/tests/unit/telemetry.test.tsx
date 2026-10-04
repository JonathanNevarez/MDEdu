import { beforeEach, afterEach, expect, it, vi } from 'vitest';
import { StrictMode, useEffect, useRef } from 'react';
import { renderHook, waitFor } from '@testing-library/react';
vi.mock('../../src/shared/http', async importOriginal => {
 const original=await importOriginal<typeof import('../../src/shared/http')>();
 return {...original,httpFetch:(input:RequestInfo|URL,init?:RequestInit)=>fetch(input,init)};
});
const student = '11111111-1111-4111-8111-111111111111';
const session = {id: '22222222-2222-4222-8222-222222222222', studentId: student, status: 'ACTIVE'};
function response(value: unknown, status = 200, headers?: HeadersInit) {return new Response(JSON.stringify(value), {status, headers});}
beforeEach(() => {sessionStorage.clear();localStorage.clear();vi.resetModules();});
afterEach(() => vi.unstubAllGlobals());
it('creates one session across concurrent initialization and uses sessionStorage only', async () => {
 const fetcher=vi.fn(async()=>response(session,201));vi.stubGlobal('fetch',fetcher);
 const api=await import('../../src/features/telemetry/client');const [a,b]=await Promise.all([api.ensureSession(student),api.ensureSession(student)]);
 expect(a).toEqual(b);expect(fetcher).toHaveBeenCalledTimes(1);expect(JSON.parse(sessionStorage.getItem(api.sessionKey)!)).toEqual(session);expect(localStorage.length).toBe(0);
});
it('validates stored session and replaces stale session once', async()=>{
 sessionStorage.setItem('mdedu.session.v1',JSON.stringify(session));const replacement={...session,id:'33333333-3333-4333-8333-333333333333'};
 const fetcher=vi.fn().mockResolvedValueOnce(response({},404)).mockResolvedValueOnce(response(replacement,201));vi.stubGlobal('fetch',fetcher);
 const api=await import('../../src/features/telemetry/client');expect((await api.ensureSession(student)).id).toBe(replacement.id);expect(fetcher).toHaveBeenCalledTimes(2);
});
it('does not loop if replacement session fails',async()=>{
 sessionStorage.setItem('mdedu.session.v1',JSON.stringify(session));const fetcher=vi.fn(async()=>response({},404));vi.stubGlobal('fetch',fetcher);
 const api=await import('../../src/features/telemetry/client');await expect(api.ensureSession(student)).rejects.toThrow();expect(fetcher).toHaveBeenCalledTimes(2);
});
it('propagates session header and captures only a safe response request ID',async()=>{
 const requestId='44444444-4444-4444-8444-444444444444';const fetcher=vi.fn().mockResolvedValueOnce(response(session,201)).mockResolvedValueOnce(response({},200,{'X-Request-Id':requestId}));vi.stubGlobal('fetch',fetcher);
 const api=await import('../../src/features/telemetry/client');await api.studentFetch(student,'/attempts',{method:'POST',body:'{}',headers:{'Content-Type':'application/json'}});
 const headers=fetcher.mock.calls[1]![1].headers as Headers;expect(headers.get('X-Session-Id')).toBe(session.id);expect(headers.get('Content-Type')).toBe('application/json');expect(api.lastRequestId).toBe(requestId);
});
it('StrictMode double effect sends one activity-open event',async()=>{
 const fetcher=vi.fn(async(path:string)=>path.endsWith('/sessions')?response(session,201):new Response(null,{status:204}));vi.stubGlobal('fetch',fetcher);
 const api=await import('../../src/features/telemetry/client');
 renderHook(()=>{const id=useRef(crypto.randomUUID());useEffect(()=>{void api.recordInteraction(student,'SEQUENCES','ACTIVITY_OPENED',id.current);},[]);},{wrapper:StrictMode});
 await waitFor(()=>expect(fetcher.mock.calls.filter(c=>c[0].endsWith('/telemetry/events'))).toHaveLength(1));
});
it('separate browser sessions retain student identity but obtain another session',async()=>{
 const fetcher=vi.fn(async()=>response(session,201));vi.stubGlobal('fetch',fetcher);const first=await import('../../src/features/telemetry/client');await first.ensureSession(student);
 sessionStorage.clear();vi.resetModules();const second=await import('../../src/features/telemetry/client');await second.ensureSession(student);expect(fetcher).toHaveBeenCalledTimes(2);
});

it('recovers a session invalidated while the tab stays open, with one bounded retry',async()=>{
 const replacement={...session,id:'33333333-3333-4333-8333-333333333333'};
 const fetcher=vi.fn().mockResolvedValueOnce(response(session,201)).mockResolvedValueOnce(response({},404)).mockResolvedValueOnce(response({},404)).mockResolvedValueOnce(response(replacement,201)).mockResolvedValueOnce(response({},201));vi.stubGlobal('fetch',fetcher);
 const api=await import('../../src/features/telemetry/client');const result=await api.studentFetch(student,'/attempts',{method:'POST',body:'{}'});
 expect(result.status).toBe(201);expect(fetcher).toHaveBeenCalledTimes(5);expect(JSON.parse(sessionStorage.getItem(api.sessionKey)!).id).toBe(replacement.id);
});
it('a business conflict with an active session does not retry a mutation',async()=>{
 const fetcher=vi.fn().mockResolvedValueOnce(response(session,201)).mockResolvedValueOnce(response({},409)).mockResolvedValueOnce(response(session));vi.stubGlobal('fetch',fetcher);
 const api=await import('../../src/features/telemetry/client');expect((await api.studentFetch(student,'/attempts',{method:'POST',body:'{}'})).status).toBe(409);expect(fetcher).toHaveBeenCalledTimes(3);
});
