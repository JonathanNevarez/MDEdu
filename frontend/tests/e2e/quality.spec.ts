import AxeBuilder from '@axe-core/playwright';
import {test,expect,type Page} from '@playwright/test';
import {loginTeacherPage} from './teacherAuth';
import {readFileSync,writeFileSync} from 'node:fs';
const api=process.env.VITE_API_BASE_URL??'http://127.0.0.1:8080';
async function audit(page:Page){
 const result=await new AxeBuilder({page}).analyze();
 writeFileSync(test.info().outputPath('axe-'+new URL(page.url()).pathname.replaceAll('/','-')+'.json'),JSON.stringify({url:page.url(),violations:result.violations,passes:result.passes.length,incomplete:result.incomplete.length},null,2));
 await test.info().attach('axe-'+new URL(page.url()).pathname.replaceAll('/','-'),{body:JSON.stringify({url:page.url(),violations:result.violations,passes:result.passes.length,incomplete:result.incomplete.length},null,2),contentType:'application/json'});
 expect(result.violations.filter(v=>v.impact==='serious'||v.impact==='critical')).toEqual([]);
}
test('accessible home, keyboard skip link and teacher login',async({page})=>{
 await page.goto('/');await audit(page);
 await page.keyboard.press('Tab');await expect(page.getByRole('link',{name:'Saltar al contenido'})).toBeFocused();
 await page.keyboard.press('Enter');await expect(page.locator('#contenido')).toBeFocused();
 await page.goto('/docente');await expect(page).toHaveURL(/docente\/login$/);await audit(page);
 await page.getByLabel('Usuario',{exact:true}).fill('invalid');await page.getByLabel('Contraseña',{exact:true}).fill('invalid');
 await page.getByRole('button',{name:'Iniciar sesión',exact:true}).click();await expect(page.getByRole('alert')).toBeVisible();
 await expect(page.getByLabel('Contraseña',{exact:true})).toHaveValue('');
 await loginTeacherPage(page);await audit(page);
 await page.getByRole('button',{name:'Cerrar sesión docente'}).click();await expect(page).toHaveURL(/docente\/login$/);
 await page.goto('/docente');await expect(page).toHaveURL(/docente\/login$/);
});
test('accessible map, activity and persisted feedback at laptop size',async({page,request})=>{
 const cspErrors:string[]=[];page.on('console',message=>{if(message.text().includes('Content Security Policy'))cspErrors.push(message.text());});
 await page.setViewportSize({width:1280,height:800});await page.emulateMedia({reducedMotion:'reduce'});
 await page.goto('/aventura');await expect(page.getByRole('button',{name:/Secuencias:.*Disponible/})).toBeVisible();await audit(page);
 await page.getByRole('button',{name:/Secuencias:.*Disponible/}).click();await expect(page.getByTestId('game-blockly').locator('.blocklySvg')).toBeVisible();await audit(page);
 const id=await page.evaluate(()=>localStorage.getItem('mdedu.student.id.v1'));
 let attemptId='';
 for(const levelId of ['SEQUENCES','VARIABLES','CONDITIONALS','LOOPS']){
  const program=JSON.parse(readFileSync(`../backend/src/test/resources/evaluation/${levelId==='LOOPS'?'LOOPS_MANUAL':levelId}.json`,'utf8'));
  const response=await request.post(`${api}/api/attempts`,{data:{studentId:id,levelId,program,hintCount:0,resolutionTimeMs:1000}});
  expect(response.status()).toBe(201);attemptId=(await response.json()).attemptId;
 }
 expect((await request.post(`${api}/api/feedback/generate`,{data:{studentId:id,attemptId}})).status()).toBe(200);
 await page.goto('/aventura/LOOPS');await expect(page.getByRole('region',{name:'Pista'})).toBeVisible();await audit(page);
 expect(await page.locator('html').evaluate(el=>el.scrollWidth<=el.ownerDocument.defaultView!.innerWidth)).toBe(true);
 expect(cspErrors).toEqual([]);
});
test('backend unavailable produces recoverable UI and no leaked exception',async({page})=>{
 await page.route('**/api/**',route=>route.abort('connectionrefused'));
 await page.goto('/docente');await expect(page.getByRole('alert')).toContainText('No se pudo comprobar');
 await expect(page.locator('body')).not.toContainText('TypeError');
 await page.unroute('**/api/**');await page.reload();await expect(page).toHaveURL(/docente\/login$/);
});
