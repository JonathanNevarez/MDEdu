import {readFileSync} from 'node:fs';
import {expect} from '@playwright/test';
import type {APIRequestContext, Page} from '@playwright/test';
const settings=Object.fromEntries(readFileSync('../backend/src/test/resources/teacher-test.properties','utf8').split(/\r?\n/).filter(s=>s.includes('=')).map(s=>{const at=s.indexOf('=');return [s.slice(0,at),s.slice(at+1)];}));
export const teacherCredentials={username:settings.META_UI_USERNAME!,password:settings.META_UI_PASSWORD!};
const api=process.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export async function authenticateTeacher(request: APIRequestContext) {
 const token=await (await request.get(`${api}/api/teacher/csrf`)).json();
 const response=await request.post(`${api}/api/teacher/login`,{headers:{[token.headerName]:token.token},form:teacherCredentials});
 expect(response.status()).toBe(204);
}
export async function loginTeacherPage(page:Page) {
 await page.goto('/docente/login');await page.getByLabel('Usuario',{exact:true}).fill(teacherCredentials.username);await page.getByLabel('Contraseña',{exact:true}).fill(teacherCredentials.password);await page.getByLabel('Contraseña',{exact:true}).press('Enter');await expect(page.getByRole('heading',{name:'Vista docente',exact:true})).toBeVisible();
}
