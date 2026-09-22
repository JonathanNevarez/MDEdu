import { expect, test } from '@playwright/test';

test('el frontend compilado abre y permite recuperar una ruta inexistente', async ({ page }) => {
  const browserErrors: string[] = [];
  page.on('pageerror', (error) => browserErrors.push(error.message));

  await page.goto('/');
  await expect(page).toHaveTitle('Lógica de programación');
  await expect(page.getByRole('heading', {
    name: 'Un espacio para aprender lógica de programación',
  })).toBeVisible();

  await page.goto('/pagina-inexistente');
  await expect(page.getByRole('heading', { name: 'No encontramos esta página' })).toBeVisible();
  await page.getByRole('link', { name: 'Volver al inicio' }).click();
  await expect(page).toHaveURL('http://127.0.0.1:4173/');
  await expect(page.getByRole('heading', {
    name: 'Un espacio para aprender lógica de programación',
  })).toBeVisible();
  expect(browserErrors).toEqual([]);
});
