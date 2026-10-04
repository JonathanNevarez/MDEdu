import {test,expect} from './studentFixtures';

test('el frontend compilado abre y permite recuperar una ruta inexistente', async ({ page }) => {
  const browserErrors: string[] = [];
  page.on('pageerror', (error) => browserErrors.push(error.message));

  await page.goto('/');
  await expect(page).toHaveTitle('Lógica de programación');
  await expect(page.getByRole('heading', {
    name: 'Aprender',
  })).toBeVisible();

  await page.goto('/pagina-inexistente');
  await expect(page.getByRole('heading', { name: 'No encontramos esta página' })).toBeVisible();
  await page.getByRole('link', { name: 'Volver a Aprender' }).click();
  await expect(page).toHaveURL(/aprender$/);
  await expect(page.getByRole('heading', {
    name: 'Aprender',
  })).toBeVisible();
  expect(browserErrors).toEqual([]);
});
