import {afterEach, expect, it, vi} from 'vitest';
import {render, screen, cleanup} from '@testing-library/react';
import {ErrorBoundary} from '../../src/shared/ErrorBoundary';
import {ApiError, checkResponse, httpFetch} from '../../src/shared/http';
import {teacherLogin, teacherLogout, teacherSession} from '../../src/features/teacher/teacherApi';
afterEach(()=>{cleanup();vi.unstubAllGlobals();vi.restoreAllMocks();});
it('normalizes network failures without leaking the original error',async()=>{
 vi.stubGlobal('fetch',vi.fn().mockRejectedValue(new Error('private-server-secret')));
 await expect(httpFetch('/api/test')).rejects.toEqual(new ApiError(0));
});
it('preserves abort semantics',async()=>{
 const error=new DOMException('Aborted','AbortError');vi.stubGlobal('fetch',vi.fn().mockRejectedValue(error));
 await expect(httpFetch('/api/test')).rejects.toBe(error);
});
it('checks HTTP status without exposing server bodies',()=>{
 expect(()=>checkResponse(new Response('private',{status:500}))).toThrow(ApiError);
 expect(checkResponse(new Response(null,{status:404}),[404]).status).toBe(404);
});
it('uses a CSRF token and cookies for login and logout without persisting credentials',async()=>{
 localStorage.clear();sessionStorage.clear();
 const fetch=vi.fn().mockResolvedValueOnce(Response.json({token:'test-csrf',headerName:'X-CSRF-TOKEN'})).mockResolvedValueOnce(new Response(null,{status:204})).mockResolvedValueOnce(Response.json({token:'logout-csrf',headerName:'X-CSRF-TOKEN'})).mockResolvedValueOnce(new Response(null,{status:204}));vi.stubGlobal('fetch',fetch);
 await teacherLogin('test-user','test-only');await teacherLogout();
 expect(fetch.mock.calls[1]![1].credentials).toBe('include');
 expect(fetch.mock.calls[1]![1].headers.get('X-CSRF-TOKEN')).toBe('test-csrf');
 expect(fetch.mock.calls[3]![1].headers.get('X-CSRF-TOKEN')).toBe('logout-csrf');
 expect(localStorage.length+sessionStorage.length).toBe(0);
});
it('treats an expired teacher session as unauthenticated',async()=>{
 vi.stubGlobal('fetch',vi.fn().mockResolvedValue(new Response(null,{status:401})));
 expect(await teacherSession()).toBe(false);
});
it('renders a safe recovery screen after a render error',()=>{
 vi.spyOn(console,'error').mockImplementation(()=>{});
 function Broken(): never {throw new Error('private-stack-detail');}
 render(<ErrorBoundary><Broken/></ErrorBoundary>);
 expect(screen.getByRole('heading').textContent).toContain('No se pudo mostrar');
 expect(screen.queryByText('private-stack-detail')).toBeNull();
 expect(screen.getByRole('link',{name:'Volver al inicio'}).getAttribute('href')).toBe('/');
});
