import { authenticateTeacher } from './teacherAuth';
import { readFileSync, writeFileSync } from 'node:fs';
import {test,expect,createStudent,loginStudentById} from './studentFixtures';
const api = process.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
test('real session and manual LOOP-01 reconstruct a complete isolated audit without replay', async ({page, request}, testInfo) => {
 await authenticateTeacher(request);
 const student=(await (await createStudent(request)).json()).id as string;
 for(const level of ['SEQ-01','VAR-01','COND-01']) {
  const program=JSON.parse(readFileSync(`../backend/src/test/resources/challenges/${level}.json`,'utf8'));
  expect((await request.post(`${api}/api/attempts`,{data:{studentId:student,levelId:level,program,hintCount:0,resolutionTimeMs:1000}})).status()).toBe(201);
 }
 await page.goto('/');await loginStudentById(page,student);await page.evaluate(id=>localStorage.setItem('mdedu.student.id.v1',id),student);await page.goto('/aprender/LOOP-01');
 await expect(page.getByTestId('game-blockly').locator('.blocklySvg')).toBeVisible();
 const session=await page.evaluate(()=>JSON.parse(sessionStorage.getItem('mdedu.session.v1')!).id as string);
  await page.getByText('Añadir con teclado',{exact:true}).click();
 for(let i=0;i<7;i++)await page.getByRole('button',{name:'+ Avanzar',exact:true}).click();
 const pending=page.waitForResponse(r=>r.url().endsWith('/api/attempts')&&r.request().method()==='POST');
 await page.getByRole('button',{name:'▶ Ejecutar',exact:true}).click();const response=await pending;
 expect(response.status()).toBe(201);expect(response.request().headers()['x-session-id']).toBe(session);expect(response.headers()['x-request-id']).toMatch(/^[0-9a-f-]{36}$/);
 const attempt=await response.json();await expect(page.getByRole('region',{name:'Pista'})).toBeVisible();await expect(page.getByRole('region',{name:'Luma'})).toBeVisible();
 const url=`${api}/api/students/${student}/attempts/${attempt.attemptId}/timeline`;
 await expect.poll(async()=>{const t=await (await request.get(url)).json();return t.events.some((e:{type:string})=>e.type==='NAVIGATION_PRESENTED');}).toBe(true);
 const trace=await (await request.get(url)).json();expect(trace.traceStatus).toBe('COMPLETE');expect(trace.sessionId).toBe(session);
 expect(trace.attempt.functionalPassed).toBe(true);expect(trace.attempt.activityPassed).toBe(false);expect(trace.attempt.patterns).toEqual(['REPETITIVE_SEQUENCE_WITHOUT_LOOP']);
 expect(trace.feedback[0].source).toBe('FALLBACK');expect(trace.activityOpenEvents).toHaveLength(1);
 expect(JSON.stringify(trace)).not.toContain('E2E_PRIVATE_SENTINEL');
 const repeated=await (await request.get(url)).json();expect(repeated).toEqual(trace);
 const other=(await (await createStudent(request)).json()).id;
 expect((await request.get(`${api}/api/students/${other}/attempts/${attempt.attemptId}/timeline`)).status()).toBe(404);
 writeFileSync(testInfo.outputPath('attempt-timeline.json'),JSON.stringify(trace,null,2));await page.screenshot({path:testInfo.outputPath('audited-loops.png'),fullPage:true});
 // Ending a session explicitly then reloading obtains a new session for the same student.
 expect((await request.post(`${api}/api/sessions/${session}/end`,{data:{studentId:student}})).status()).toBe(200);
 await page.reload();await expect(page.getByTestId('game-blockly').locator('.blocklySvg')).toBeVisible();
 expect(await page.evaluate(()=>JSON.parse(sessionStorage.getItem('mdedu.session.v1')!).id)).not.toBe(session);
 expect(await page.evaluate(()=>localStorage.getItem('mdedu.student.id.v1'))).toBe(student);
});
