import {test,expect} from '@playwright/test';
import {execFileSync} from 'node:child_process';
import {readFileSync} from 'node:fs';
import {authenticateTeacher} from './teacherAuth';
import {csrfRequest} from './studentFixtures';
const api=process.env.VITE_API_BASE_URL??'';
test.skip(process.env.PRODUCTION_SMOKE!=='true','Runs only against the isolated production Docker smoke.');

test('single HTTPS origin: deep links, assets, headers, real Secure cookies and API isolation',async({page,request})=>{
 const violations:string[]=[];const failures:string[]=[];
 await page.addInitScript("document.addEventListener('securitypolicyviolation', e => console.error('CSP:' + e.violatedDirective))");
 page.on('console',m=>{if(m.text().startsWith('CSP:'))violations.push(m.text());});
 page.on('response',r=>{if(r.status()>=400&&/\.(js|css|svg|woff2?)(\?|$)/.test(r.url()))failures.push(r.url());});
 for(const path of ['/','/ingresar','/aprender','/progreso','/laboratorio','/docente/login','/docente','/aprender/SEQ-01']){
  const r=await request.get(`${api}${path}`);expect(r.status()).toBe(200);expect(await r.text()).toContain('<div id="root">');
  expect(r.headers()['content-security-policy']).toContain("script-src 'self'");
  expect(r.headers()['x-frame-options']).toBe('DENY');expect(r.headers()['cache-control']).toContain('no-store');
 }
 for(const path of ['/assets/missing.js','/blockly-media/missing.svg','/actuator/missing']){
  const r=await request.get(`${api}${path}`);expect(r.status()).toBe(404);expect(await r.text()).not.toContain('<div id="root">');
 }
 expect((await request.get(`${api}/api/meta/students`)).status()).toBe(401);
 expect((await request.post(`${api}/api/teacher/login`,{form:{username:'none',password:'none'}})).status()).toBe(403);
 await page.goto('/docente/login');await page.reload();await expect(page.getByLabel('Usuario',{exact:true})).toBeVisible();
 await page.goto('/ingresar');await expect(page.getByLabel('Código de estudiante')).toBeVisible();
 const assets=await page.locator('script[src],link[rel="stylesheet"]').evaluateAll(els=>els.map(e=>e.getAttribute('src')??e.getAttribute('href')!));
 expect(assets.length).toBeGreaterThan(0);
 for(const asset of assets){
  expect(asset.startsWith('/assets/')).toBe(true);const r=await request.get(`${api}${asset}`);expect(r.status()).toBe(200);
  expect(r.headers()['cache-control']).toContain('immutable');
  expect(await r.text()).not.toMatch(/http:\/\/(localhost|127\.0\.0\.1):8080/);
 }
 const csrf=await page.request.get(`${api}/api/teacher/csrf`);expect(csrf.status()).toBe(200);
 const cookie=(await page.context().cookies()).find(c=>c.name==='JSESSIONID');expect(cookie).toMatchObject({secure:true,httpOnly:true,sameSite:'Strict'});
 expect(violations).toEqual([]);expect(failures).toEqual([]);
});

test('PostgreSQL progress survives a real restart of the 512 MiB production container',async({browser,request})=>{
 test.setTimeout(120000);
 const container=process.env.PRODUCTION_TEST_CONTAINER!;
 expect(container).toMatch(/^mdedu-production-test-\d{14}-app$/);
 await authenticateTeacher(request);const teacher=csrfRequest(request);
 const account=await(await teacher.post(`${api}/api/teacher/participants`)).json();
 async function login(){
  const c=await browser.newContext({ignoreHTTPSErrors:true});
  const token=await(await c.request.get(`${api}/api/auth/student/csrf`)).json();
  expect((await c.request.post(`${api}/api/auth/student/login`,{headers:{[token.headerName]:token.token},data:{studentCode:account.participant.studentCode,password:account.temporaryPassword}})).status()).toBe(200);
  return c;
 }
 const first=await login();const token=await(await first.request.get(`${api}/api/auth/student/csrf`)).json();
 const program=JSON.parse(readFileSync('../backend/src/test/resources/challenges/SEQ-01.json','utf8'));
 const result=await first.request.post(`${api}/api/attempts`,{headers:{[token.headerName]:token.token},data:{studentId:account.participant.studentId,levelId:'SEQ-01',program,hintCount:0,resolutionTimeMs:1000}});expect(result.status()).toBe(201);
 const url=`${api}/api/students/${account.participant.studentId}/progress`;
 const before=await(await first.request.get(url)).json();expect(before.levels.find((l:{levelId:string})=>l.levelId==='SEQ-01').completed).toBe(true);
 execFileSync('docker',['restart',container],{stdio:'pipe',timeout:30000});
 await expect.poll(async()=>{try{return(await request.get(`${api}/actuator/health/readiness`,{timeout:3000})).status();}catch{return 0;}},{timeout:90000,intervals:[1000,2000]}).toBe(200);
 expect((await first.request.get(`${api}/api/auth/student/me`)).status()).toBe(401);await first.close();
 const second=await login();expect(await(await second.request.get(url)).json()).toEqual(before);
 const page=await second.newPage();await page.goto('/progreso');await expect(page.getByRole('heading',{name:'Mi progreso',exact:true})).toBeVisible();await page.reload();await expect(page.getByRole('heading',{name:'Mi progreso',exact:true})).toBeVisible();await second.close();
});
