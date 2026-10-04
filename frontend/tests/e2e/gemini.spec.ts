import { authenticateTeacher, loginTeacherPage } from './teacherAuth';
import { readFileSync, writeFileSync } from 'node:fs';
import {test,expect,createStudent,loginStudentById} from './studentFixtures';

// Opt-in only with the backend test-classpath launcher. Never calls the live provider.
test('Gemini local HTTP adapter delivers validated feedback to the adaptive UI', async ({ page, request }, testInfo) => {
  test.skip(process.env.GEMINI_MOCK_E2E !== 'true', 'Requires GeminiMockApplication with loopback HTTP');
  await authenticateTeacher(request);
  const api = process.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
  const student = await createStudent(request);
  expect(student.status()).toBe(201);
  const id = (await student.json()).id as string;
  const sessionResponse = await request.post(`${api}/api/sessions`, {data: {studentId: id}});
  expect(sessionResponse.status()).toBe(201); const session = await sessionResponse.json();
  let attempt;
  for (const level of ['SEQ-01', 'VAR-01', 'COND-01', 'LOOP-01']) {
    const fixture = level === 'LOOP-01' ? 'LOOP-01_MANUAL' : level;
    const program = JSON.parse(readFileSync(`../backend/src/test/resources/challenges/${fixture}.json`, 'utf8'));
    const response = await request.post(`${api}/api/attempts`, { headers: {'X-Session-Id': session.id}, data: { studentId: id, levelId: level, program, hintCount: 0, resolutionTimeMs: 1000 } });
    expect(response.status()).toBe(201); attempt = await response.json();
  }
  const response = await request.post(`${api}/api/feedback/generate`, { headers: {'X-Session-Id': session.id}, data: { studentId: id, attemptId: attempt.attemptId } });
  expect(response.status()).toBe(200);
  const feedback = await response.json();
  expect(feedback.provider).toBe('GEMINI'); expect(feedback.source).toBe('GEMINI'); expect(feedback.llmUsed).toBe(true);
  await page.goto('/'); await loginStudentById(page,id);await page.evaluate(({studentId, sessionId}) => {localStorage.setItem('mdedu.student.id.v1', studentId);sessionStorage.setItem('mdedu.session.v1', JSON.stringify({id: sessionId, studentId, status: 'ACTIVE'}));}, {studentId: id, sessionId: session.id});
  await page.goto('/aventura/LOOP-01');
  await expect(page.getByRole('region', { name: 'Luma' })).toBeVisible();
  await expect(page.getByRole('region', { name: 'Pista' })).toContainText(feedback.message);
  await expect(page.getByRole('button', { name: 'Intentar de nuevo' })).toBeVisible();
  const config = await (await request.get(`${api}/api/students/${id}/attempts/${attempt.attemptId}/ui-configuration`)).json();
  expect(config.safeDefault).toBe(false); expect(config.feedback.source).toBe('GEMINI');
  await page.reload(); await expect(page.getByRole('region', { name: 'Pista' })).toContainText(feedback.message);
  writeFileSync(testInfo.outputPath('gemini-configuration.json'), JSON.stringify({ feedback, config }, null, 2));
  const trace = await (await request.get(`${api}/api/students/${id}/attempts/${attempt.attemptId}/timeline`)).json();
  expect(trace.traceStatus).toBe('COMPLETE'); expect(trace.sessionId).toBe(session.id);
  expect(trace.events.some((event: {type: string; payload: {provider?: string; llmUsed?: boolean}}) => event.type === 'FEEDBACK_GENERATED' && event.payload.provider === 'GEMINI' && event.payload.llmUsed)).toBe(true);
  expect(JSON.stringify(trace)).not.toContain('gemini-test-secret-never-log');
  writeFileSync(testInfo.outputPath('gemini-timeline.json'), JSON.stringify(trace, null, 2));
  await page.screenshot({ path: testInfo.outputPath('gemini-luma.png'), fullPage: true });
  await loginTeacherPage(page);
  await page.goto('/docente');
  await page.getByLabel('Estudiante pseudónimo').selectOption(id);
  await page.getByRole('button', {name:'Intentos',exact:true}).click();
  await page.getByRole('button', {name:`Abrir timeline ${attempt.attemptId}`,exact:true}).click();
  await expect(page.getByRole('heading', {name:'GEMINI / GEMINI',exact:true})).toBeVisible();
  await expect(page.getByText('llmUsed: true · Fallback: No', {exact:true})).toBeVisible();
  await page.screenshot({path:testInfo.outputPath('gemini-teacher.png'),fullPage:true});
});
