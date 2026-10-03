import { readFileSync, writeFileSync } from 'node:fs';
import { test, expect } from '@playwright/test';

// Opt-in only with the backend test-classpath launcher. Never calls the live provider.
test('Gemini local HTTP adapter delivers validated feedback to the adaptive UI', async ({ page, request }, testInfo) => {
  test.skip(process.env.GEMINI_MOCK_E2E !== 'true', 'Requires GeminiMockApplication with loopback HTTP');
  const api = process.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
  const student = await request.post(`${api}/api/students`, { data: { displayName: 'Local browser fixture' } });
  expect(student.status()).toBe(201);
  const id = (await student.json()).id as string;
  let attempt;
  for (const level of ['SEQUENCES', 'VARIABLES', 'CONDITIONALS', 'LOOPS']) {
    const fixture = level === 'LOOPS' ? 'LOOPS_MANUAL' : level;
    const program = JSON.parse(readFileSync(`../backend/src/test/resources/evaluation/${fixture}.json`, 'utf8'));
    const response = await request.post(`${api}/api/attempts`, { data: { studentId: id, levelId: level, program, hintCount: 0, resolutionTimeMs: 1000 } });
    expect(response.status()).toBe(201); attempt = await response.json();
  }
  const response = await request.post(`${api}/api/feedback/generate`, { data: { studentId: id, attemptId: attempt.attemptId } });
  expect(response.status()).toBe(200);
  const feedback = await response.json();
  expect(feedback.provider).toBe('GEMINI'); expect(feedback.source).toBe('GEMINI'); expect(feedback.llmUsed).toBe(true);
  await page.goto('/'); await page.evaluate(studentId => localStorage.setItem('mdedu.student.id.v1', studentId), id);
  await page.goto('/aventura/LOOPS');
  await expect(page.getByRole('region', { name: 'Luma' })).toBeVisible();
  await expect(page.getByRole('region', { name: 'Pista' })).toContainText(feedback.message);
  await expect(page.getByRole('button', { name: 'Intentar de nuevo' })).toBeVisible();
  const config = await (await request.get(`${api}/api/students/${id}/attempts/${attempt.attemptId}/ui-configuration`)).json();
  expect(config.safeDefault).toBe(false); expect(config.feedback.source).toBe('GEMINI');
  await page.reload(); await expect(page.getByRole('region', { name: 'Pista' })).toContainText(feedback.message);
  writeFileSync(testInfo.outputPath('gemini-configuration.json'), JSON.stringify({ feedback, config }, null, 2));
  await page.screenshot({ path: testInfo.outputPath('gemini-luma.png'), fullPage: true });
});
