import {test,expect} from './studentFixtures';

// Integration E2E: existing backend must run at VITE_API_BASE_URL (default :8080).
test('mapa, secuencia real, replay, reinicio y progreso persistido', async ({ page }, testInfo) => {
  const errors: string[] = []; let executions = 0;
  page.on('pageerror', error => errors.push(error.message));
  page.on('request', request => { if (request.url().endsWith('/api/attempts')) executions++; });
  await page.goto('/aventura');
  await expect(page.getByRole('heading', { name: 'Mi primera programación' })).toBeVisible();
  await expect(page.getByRole('button', { name: /: .*\.( Disponible| Bloqueado)/ })).toHaveCount(4);
  await expect(page.getByRole('button', { name: /Secuencias:.*Disponible/ })).toHaveAttribute('aria-disabled', 'false');
  const locked = page.getByRole('button', { name: /Variables:.*Bloqueado/ });
  await expect(locked).toHaveAttribute('aria-disabled', 'true');
  await page.screenshot({ path: testInfo.outputPath('map.png'), fullPage: true });
  await locked.focus(); await page.keyboard.press('Enter');
  await expect(page.getByRole('status')).toHaveText('Completa el nivel anterior para continuar.');
  await expect(page).toHaveURL(/\/aventura$/);
  await page.getByRole('button', { name: /Secuencias:.*Disponible/ }).click();
  await expect(page.getByTestId('game-blockly').locator('.blocklySvg')).toBeVisible();
  await expect(page.getByRole('img', { name: /GridWorld: personaje en 1, 1/ })).toBeVisible();
  for (const name of ['+ Avanzar', '+ Avanzar', '+ Girar derecha', '+ Avanzar', '+ Avanzar'])
    { await page.getByRole('button', { name, exact: true }).focus(); await page.keyboard.press('Enter'); }
  await page.getByRole('button', { name: '▶ Ejecutar', exact: true }).focus(); await page.keyboard.press('Enter');
  await expect(page.getByRole('img', { name: /GridWorld: personaje en 3, 3, dirección SOUTH/ })).toBeVisible();
  await expect(page.getByRole('status').filter({ hasText: '¡Nivel completado!' })).toBeVisible();
  expect(executions).toBe(1);
  await page.screenshot({ path: testInfo.outputPath('completed.png'), fullPage: true });
  await page.getByRole('button', { name: 'Reproducir', exact: true }).click();
  await expect(page.getByRole('img', { name: /GridWorld: personaje en 1, 1/ })).toBeVisible();
  await expect(page.getByRole('status').filter({ hasText: '¡Nivel completado!' })).toBeVisible();
  expect(executions).toBe(1);
  await page.getByRole('button', { name: 'Reiniciar', exact: true }).click();
  await expect(page.getByRole('img', { name: /GridWorld: personaje en 1, 1/ })).toBeVisible();
  await expect(page.getByRole('figure', { name: /^Avanzar/ })).toHaveCount(4);
  await page.getByRole('link', { name: '← Volver al mapa' }).click();
  await expect(page.getByRole('button', { name: /Variables:.*Disponible/ })).toBeVisible();
  await page.evaluate(() => localStorage.removeItem('mdedu.game.progress.v1'));
  await page.reload();
  await expect(page.getByRole('button', { name: /Variables:.*Disponible/ })).toBeVisible();
  await expect(page.getByRole('button', { name: /Secuencias:.*Completado/ })).toBeVisible();
  expect(errors).toEqual([]);
});
