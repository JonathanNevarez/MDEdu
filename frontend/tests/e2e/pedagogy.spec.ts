import { test, expect, type Locator, type Page } from '@playwright/test';

async function dragBlock(page: Page, source: Locator, target: Locator, dx: number, dy: number) {
  await expect(source).toBeVisible(); await expect(target).toBeVisible();
  const from = await source.boundingBox(); const to = await target.boundingBox();
  expect(from).not.toBeNull(); expect(to).not.toBeNull();
  await page.mouse.move(from!.x + 20, from!.y + 10);
  await page.mouse.down();
  await page.mouse.move(from!.x + 35, from!.y + 15, { steps: 3 });
  await page.mouse.move(to!.x + dx, to!.y + dy, { steps: 15 });
  await page.mouse.up();
}

test('Ciclos distingue repetición manual y un ciclo correcto', async ({ page }, testInfo) => {
  const errors: string[] = []; page.on('pageerror', error => errors.push(error.message));
  await page.goto('/aventura');
  await page.evaluate(() => localStorage.setItem('mdedu.game.progress.v1', JSON.stringify({
    version: 1, completedLevelIds: ['SEQUENCES', 'VARIABLES', 'CONDITIONALS'],
  })));
  await page.reload();
  await page.getByRole('button', { name: /Ciclos:.*Disponible/ }).click();
  for (let i = 0; i < 7; i++) await page.getByRole('button', { name: '+ Avanzar', exact: true }).click();
  const manualResponse = page.waitForResponse(r => r.url().endsWith('/LOOPS/execute') && r.request().method() === 'POST');
  await page.getByRole('button', { name: '▶ Ejecutar', exact: true }).click();
  const manual = await (await manualResponse).json();
  expect(manual.success).toBe(true); expect(manual.evaluation.activityPassed).toBe(false);
  expect(manual.evaluation.patterns.map((p: { id: string }) => p.id)).toContain('REPETITIVE_SEQUENCE_WITHOUT_LOOP');
  await expect(page.getByRole('status').filter({ hasText: 'todavía falta aplicar el concepto' })).toBeVisible();
  await expect(page.getByText('Prueba a representar esa repetición con un ciclo.', { exact: false })).toBeVisible();
  await page.screenshot({ path: testInfo.outputPath('manual-pedagogical-failure.png'), fullPage: true });
  expect(await page.evaluate(() => JSON.parse(localStorage.getItem('mdedu.game.progress.v1')!).completedLevelIds)).not.toContain('LOOPS');
  await page.getByRole('link', { name: '← Volver al mapa' }).click();
  await expect(page.getByRole('button', { name: /Ciclos:.*Disponible/ })).toBeVisible();
  await page.getByRole('button', { name: /Ciclos:.*Disponible/ }).click();
  await page.getByRole('treeitem', { name: 'Ciclos', exact: true }).click();
  const repeatSource = page.getByRole('option', { name: /^Repetir,/ });
  const repeat = page.getByRole('region', { name: 'Blocks workspace.' }).getByRole('figure', { name: /^Repetir, Empty, Hacer/ });
  const start = page.getByRole('figure', { name: /Inicio,/ });
  const startBox = await start.boundingBox(); expect(startBox).not.toBeNull();
  await dragBlock(page, repeatSource, start, 20, startBox!.height + 6);
  await page.getByRole('treeitem', { name: 'Valores', exact: true }).click();
  const integer = page.getByRole('option', { name: /^Entero/ });
  const repeatBox = await repeat.boundingBox(); expect(repeatBox).not.toBeNull();
  await dragBlock(page, integer, repeat, repeatBox!.width - 8, 12);
  await page.getByRole('button', { name: 'Edit text: 0', exact: true }).click();
  const input = page.locator('input.blocklyHtmlInput');
  await input.fill('7'); await input.press('Enter');
  await page.getByRole('treeitem', { name: 'Movimiento', exact: true }).click();
  const move = page.getByRole('option', { name: /^Avanzar/ });
  const bodyBox = await repeat.boundingBox(); expect(bodyBox).not.toBeNull();
  await dragBlock(page, move, repeat, bodyBox!.width - 8, 31);
  await expect(page.getByRole('figure', { name: '1 stack of blocks', exact: true })).toBeAttached();
  const loopResponse = page.waitForResponse(r => r.url().endsWith('/LOOPS/execute') && r.request().method() === 'POST');
  await page.getByRole('button', { name: '▶ Ejecutar', exact: true }).click();
  const loop = await (await loopResponse).json();
  expect(loop.success).toBe(true); expect(loop.evaluation.activityPassed).toBe(true);
  await expect(page.getByRole('status').filter({ hasText: '¡Nivel completado!' })).toBeVisible();
  await page.screenshot({ path: testInfo.outputPath('loop-pedagogical-pass.png'), fullPage: true });
  expect(await page.evaluate(() => JSON.parse(localStorage.getItem('mdedu.game.progress.v1')!).completedLevelIds)).toContain('LOOPS');
  expect(errors).toEqual([]);
});
