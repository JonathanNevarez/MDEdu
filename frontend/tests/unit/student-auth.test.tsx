import {afterEach,expect,it,vi} from 'vitest';
import {render,screen} from '@testing-library/react';
import {MemoryRouter,Routes,Route} from 'react-router-dom';
import {StudentAccess} from '../../src/features/student/StudentAccess';
import {httpFetch} from '../../src/shared/http';
import {studentLogin,studentLogout} from '../../src/features/student/studentAuth';
afterEach(()=>{vi.unstubAllGlobals();localStorage.clear();sessionStorage.clear();});
it('protects student screens without creating an anonymous identity',async()=>{
 const fetcher=vi.fn().mockResolvedValue(new Response(null,{status:401}));vi.stubGlobal('fetch',fetcher);
 render(<MemoryRouter initialEntries={['/aprender']}><Routes><Route path="/aprender" element={<StudentAccess><p>Private content</p></StudentAccess>}/><Route path="/ingresar" element={<h1>Login</h1>}/></Routes></MemoryRouter>);
 expect(await screen.findByRole('heading',{name:'Login'})).toBeVisible();expect(screen.queryByText('Private content')).not.toBeInTheDocument();expect(fetcher.mock.calls.every(c=>String(c[0]).endsWith('/me'))).toBe(true);
});
it('unsafe student requests include session credentials and a fresh csrf token',async()=>{
 const f=vi.fn().mockResolvedValueOnce(Response.json({token:'test-token',headerName:'X-CSRF-TOKEN'})).mockResolvedValueOnce(new Response(null,{status:204}));vi.stubGlobal('fetch',f);
 await httpFetch('http://127.0.0.1:8080/api/telemetry/events',{method:'POST',body:'{}'});
 expect(f.mock.calls[0]![0]).toMatch(/auth\/student\/csrf$/);expect(f.mock.calls[1]![1].credentials).toBe('include');expect(f.mock.calls[1]![1].headers.get('X-CSRF-TOKEN')).toBe('test-token');
});
it('login and logout clear old telemetry and never persist a password or token',async()=>{
 localStorage.setItem('mdedu.student.id.v1','old');sessionStorage.setItem('mdedu.session.v1','old');
 const f=vi.fn(async(input:string)=>input.endsWith('/csrf')?Response.json({token:'test-token',headerName:'X-CSRF-TOKEN'}):input.endsWith('/login')?Response.json({studentId:'id',studentCode:'EST-001',authenticated:true}):new Response(null,{status:204}));vi.stubGlobal('fetch',f);
 await studentLogin('EST-001','synthetic-test-input');expect(localStorage.length+sessionStorage.length).toBe(0);
 await studentLogout();expect(localStorage.length+sessionStorage.length).toBe(0);
});
