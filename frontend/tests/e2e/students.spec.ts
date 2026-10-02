import { writeFileSync } from 'node:fs';
import { test, expect } from '@playwright/test';

test('dos identidades conservan estados independientes en PostgreSQL', async ({ browser, request }, testInfo) => {
  const contextA = await browser.newContext(); const contextB = await browser.newContext();
  try {
    const a = await contextA.newPage(); const b = await contextB.newPage();
    await a.goto('/aventura'); await b.goto('/aventura');
    for (const page of [a, b]) {
      await expect(page.getByRole('button', { name: /Secuencias:.*Disponible/ })).toBeVisible();
      await expect(page.getByRole('button', { name: /Variables:.*Bloqueado/ })).toBeVisible();
    }
    const aId = await a.evaluate(() => localStorage.getItem('mdedu.student.id.v1'));
    const bId = await b.evaluate(() => localStorage.getItem('mdedu.student.id.v1'));
    expect(aId).not.toBe(bId); expect(aId).toBeTruthy(); expect(bId).toBeTruthy();
    await a.getByRole('button', { name: /Secuencias:.*Disponible/ }).click();
    for (const name of ['+ Avanzar', '+ Avanzar', '+ Girar derecha', '+ Avanzar', '+ Avanzar']) await a.getByRole('button', { name, exact: true }).click();
    await a.getByRole('button', { name: '▶ Ejecutar', exact: true }).click();
    await expect(a.getByRole('status').filter({ hasText: '¡Nivel completado!' })).toBeVisible();
    await a.getByRole('link', { name: '← Volver al mapa' }).click(); await a.reload();
    await expect(a.getByRole('button', { name: /Variables:.*Disponible/ })).toBeVisible();
    expect(await a.evaluate(() => localStorage.getItem('mdedu.student.id.v1'))).toBe(aId);
    expect(await a.evaluate(() => localStorage.getItem('mdedu.game.progress.v1'))).toBeNull();
    await a.screenshot({ path: testInfo.outputPath('student-a-persisted.png'), fullPage: true });
    await b.getByRole('button', { name: /Secuencias:.*Disponible/ }).click();
    await b.getByRole('button', { name: '+ Avanzar', exact: true }).click();
    await b.getByRole('button', { name: '▶ Ejecutar', exact: true }).click();
    await expect(b.getByRole('status').filter({ hasText: 'No alcanzaste la meta todavía' })).toBeVisible();
    await b.getByRole('link', { name: '← Volver al mapa' }).click(); await b.reload();
    await expect(b.getByRole('button', { name: /Variables:.*Bloqueado/ })).toBeVisible();
    await b.screenshot({ path: testInfo.outputPath('student-b-independent.png'), fullPage: true });
    const api = process.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
    const modelA = await (await request.get(`${api}/api/students/${aId}/model`)).json();
    const modelB = await (await request.get(`${api}/api/students/${bId}/model`)).json();
    expect(modelA.conceptMasteries[0].successCount).toBe(1); expect(modelB.conceptMasteries[0].successCount).toBe(0);
    expect(modelB.conceptMasteries[0].failureCount).toBe(1); expect(modelA.conceptMasteries[0].masteryScore).toBeGreaterThan(modelB.conceptMasteries[0].masteryScore);
    const evidencePath = testInfo.outputPath('independent-models.json');
    writeFileSync(evidencePath, JSON.stringify({ modelA, modelB }, null, 2));
    await testInfo.attach('independent-models', { path: evidencePath, contentType: 'application/json' });
  } finally { await contextA.close(); await contextB.close(); }
});
