import {test, expect, type Page} from '@playwright/test';
import AxeBuilder from '@axe-core/playwright';
import {mkdirSync, readFileSync, writeFileSync} from 'node:fs';
import {loginTeacherPage} from './teacherAuth';

const api=process.env.VITE_API_BASE_URL??'http://127.0.0.1:8080';
// Only export documentary images when explicitly requested; regular CI stays in test-results.
const evidence=process.env.UI_EVIDENCE_DIR;
type Audit = Pick<Awaited<ReturnType<AxeBuilder['analyze']>>, 'violations'> & {name:string; passes:number; incomplete:number};
for(const viewport of [{width:1440,height:900},{width:1366,height:768}]) {
 test(`visual routes, real progress and teacher inspection at ${viewport.width}x${viewport.height}`,async({page,request},info)=>{
  test.setTimeout(180_000);
  await page.setViewportSize(viewport);await page.emulateMedia({reducedMotion:'reduce'});
  const audits:Audit[]=[];const errors:string[]=[];page.on('pageerror',e=>errors.push(e.message));
  async function shot(name:string, locator?:ReturnType<Page['locator']>){
   name=name.normalize('NFD').replace(/\p{Diacritic}/gu,'');
   expect(await page.locator('html').evaluate(el=>el.scrollWidth<=el.ownerDocument.defaultView!.innerWidth)).toBe(true);
   const path=evidence?`${evidence}/${viewport.width}-${name}.jpg`:info.outputPath(`${name}.jpg`);
   if(evidence)mkdirSync(evidence,{recursive:true});
   if(locator)await locator.screenshot({path,type:'jpeg',quality:78});
   else await page.screenshot({path,type:'jpeg',quality:78,fullPage:true});
  }
  async function audit(name:string){const result=await new AxeBuilder({page}).analyze();audits.push({name,violations:result.violations,passes:result.passes.length,incomplete:result.incomplete.length});expect(result.violations.filter(v=>v.impact==='serious'||v.impact==='critical')).toEqual([]);}
  await page.goto('/');await expect(page.getByRole('heading',{name:'Las grandes ideas empiezan con un bloque.'})).toBeVisible();await shot('inicio');await audit('inicio');
  await page.getByRole('link',{name:'Comenzar mi aventura'}).click();await expect(page.getByRole('button',{name:/Secuencias:.*Disponible/})).toBeVisible();await shot('mapa');
  const mapBounds=await page.locator('.adventure-map').boundingBox();expect(mapBounds).not.toBeNull();
  for(const label of await page.locator('.node-label').all()){const bounds=await label.boundingBox();expect(bounds).not.toBeNull();expect(bounds!.y+bounds!.height).toBeLessThanOrEqual(mapBounds!.y+mapBounds!.height);}
  const student=await page.evaluate(()=>localStorage.getItem('mdedu.student.id.v1'));
  expect(student).toBeTruthy();
  await page.getByRole('button',{name:/Secuencias:.*Disponible/}).click();await expect(page.getByTestId('game-blockly').locator('.blocklySvg')).toBeVisible();await shot('secuencias');
  for(const name of ['+ Avanzar','+ Avanzar','+ Girar derecha','+ Avanzar','+ Avanzar'])await page.getByRole('button',{name,exact:true}).click();
  await page.getByRole('button',{name:'▶ Ejecutar',exact:true}).click();await expect(page.getByRole('status').filter({hasText:'¡Nivel completado!'})).toBeVisible();await expect(page.getByRole('region',{name:'Evaluación de tu solución'})).toHaveAttribute('data-passed','true');await shot('feedback-exito');
  // Unlock through real evaluation, never through forged client-side progress.
  for(const levelId of ['VARIABLES','CONDITIONALS']){
   await page.goto(`/aventura/${levelId}`);await expect(page.getByTestId('game-blockly').locator('.blocklySvg')).toBeVisible();await shot(levelId.toLowerCase());
   const program=JSON.parse(readFileSync(`../backend/src/test/resources/evaluation/${levelId}.json`,'utf8'));
   const response=await request.post(`${api}/api/attempts`,{data:{studentId:student,levelId,program,hintCount:0,resolutionTimeMs:1000}});expect(response.status()).toBe(201);expect((await response.json()).execution.evaluation.activityPassed).toBe(true);
  }
  await page.goto('/aventura/LOOPS');await expect(page.getByTestId('game-blockly').locator('.blocklySvg')).toBeVisible();await shot('ciclos');
  for(let i=0;i<7;i++)await page.getByRole('button',{name:'+ Avanzar',exact:true}).click();
  const result=page.waitForResponse(r=>r.url().endsWith('/api/attempts')&&r.request().method()==='POST');
  await page.getByRole('button',{name:'▶ Ejecutar',exact:true}).click();const attempt=await(await result).json();expect(attempt.execution.evaluation.activityPassed).toBe(false);
  await expect(page.getByRole('status').filter({hasText:'todavía falta aplicar el concepto'})).toBeVisible();await expect(page.getByRole('region',{name:'Pista'})).toBeVisible();await shot('feedback-pedagogico');await audit('feedback-pedagogico');
  await page.getByRole('link',{name:'← Volver al mapa'}).click();await expect(page.getByRole('button',{name:/Ciclos:.*Disponible/})).toBeVisible();await page.getByText('Mi progreso · 3 de 4 conceptos completados',{exact:true}).click();await expect(page.getByRole('meter')).toHaveCount(4);await shot('progreso');await audit('progreso');
  await page.goto('/laboratorio');await expect(page.getByTestId('blockly-editor').locator('.blocklySvg')).toBeVisible();await shot('laboratorio');
  await page.goto('/docente/login');await shot('docente-login');await loginTeacherPage(page);await shot('docente-vacio');
  await page.getByLabel('Estudiante pseudónimo').selectOption(student!);await expect(page.getByRole('heading',{name:'LOOPS',exact:true})).toBeVisible();await shot('docente-modelo');await audit('docente-modelo');
  await page.getByRole('button',{name:'Intentos',exact:true}).click();await expect(page.getByRole('button',{name:`Abrir timeline ${attempt.attemptId}`,exact:true})).toBeVisible();await shot('docente-intentos');await page.getByRole('button',{name:`Abrir timeline ${attempt.attemptId}`,exact:true}).click();
  const timeline=page.getByRole('region',{name:'Timeline del intento'});await expect(timeline).toContainText('COMPLETE');await shot('docente-timeline',timeline);await audit('timeline');
  for(const section of ['Adaptaciones','Reglas','Parámetros']){await page.getByRole('button',{name:section,exact:true}).click();await expect(page.getByRole('heading',{name:section==='Reglas'?'Reglas de adaptación':section==='Parámetros'?'Parámetros activos':'Adaptaciones',exact:true})).toBeVisible();await shot(`docente-${section.toLowerCase()}`);await audit(section);}
  await page.getByRole('button',{name:'Cerrar sesión docente'}).click();await expect(page).toHaveURL(/docente\/login$/);
  await page.goto('/no-existe');await shot('404');await audit('404');
  // Controlled transport states; no persisted evidence or production logic is modified.
  let release:()=>void=()=>{};const held=new Promise<void>(resolve=>{release=resolve;});
  // Hold transport only while capturing the loading state, then release immediately.
  await page.route('**/api/**',async route=>{await held;await route.continue();});
  await page.goto('/aventura');await expect(page.getByRole('status')).toContainText('Preparando tu aventura');await shot('carga');release();await page.unrouteAll({behavior:'wait'});
  await page.route('**/api/**',route=>route.abort('connectionrefused'));await page.goto('/aventura');await expect(page.getByRole('button',{name:'Reintentar'})).toBeVisible();await shot('error');await audit('error');
  expect(errors).toEqual([]);
  writeFileSync(info.outputPath('visual-audits.json'),JSON.stringify(audits,null,2));
  if(evidence)writeFileSync(`${evidence}/${viewport.width}-audits.json`,JSON.stringify(audits.map(a=>({name:a.name,violations:a.violations.map(v=>({id:v.id,impact:v.impact,nodes:v.nodes.map(n=>n.target)})),passes:a.passes,incomplete:a.incomplete})),null,2));
 });
}
