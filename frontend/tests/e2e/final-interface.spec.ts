import {test,expect} from './studentFixtures';
import {type Page,type Locator} from '@playwright/test';
import AxeBuilder from '@axe-core/playwright';
import {readFileSync,mkdirSync,writeFileSync} from 'node:fs';
import {loginTeacherPage} from './teacherAuth';
const api=process.env.VITE_API_BASE_URL??'http://127.0.0.1:8080';
async function drag(page:Page,source:Locator,target:Locator,dx:number,dy:number){
 const a=await source.boundingBox();const b=await target.boundingBox();expect(a).not.toBeNull();expect(b).not.toBeNull();
 await page.mouse.move(a!.x+20,a!.y+10);await page.mouse.down();await page.mouse.move(a!.x+35,a!.y+15,{steps:3});await page.mouse.move(b!.x+dx,b!.y+dy,{steps:15});await page.mouse.up();
}
for(const viewport of [{width:1440,height:900},{width:1366,height:768}])test(`final composition, real conditional and persistent identity ${viewport.width}`,async({browser,request},info)=>{
 test.setTimeout(120000);
 const issued=await(await request.post(`${api}/api/teacher/participants`)).json();const id=issued.participant.studentId;
 const context=await browser.newContext({viewport,reducedMotion:'reduce'});const page=await context.newPage();page.setDefaultTimeout(12000);const errors:string[]=[];page.on('pageerror',e=>errors.push(e.message));
 const audits:unknown[]=[];const evidence=process.env.FINAL_UI_EVIDENCE_DIR;
 async function shot(name:string){
  expect(await page.locator('html').evaluate(el=>el.scrollWidth<=el.ownerDocument.defaultView!.innerWidth)).toBe(true);
  for(const font of await page.locator('h1,h2').evaluateAll(els=>els.map(el=>el.ownerDocument.defaultView!.getComputedStyle(el).fontFamily)))expect(font).toContain('Tahoma');
  const audit=await new AxeBuilder({page}).analyze();audits.push({name,violations:audit.violations.map(v=>({id:v.id,impact:v.impact,targets:v.nodes.map(n=>n.target)}))});
  expect(audit.violations.filter(v=>v.impact==='serious'||v.impact==='critical')).toEqual([]);
  if(evidence)mkdirSync(evidence,{recursive:true});
  await page.screenshot({path:evidence?`${evidence}/${viewport.width}-${name}.jpg`:info.outputPath(`${name}.jpg`),type:'jpeg',quality:82,fullPage:true});
 }
 async function login(p:Page){await p.goto('/ingresar');await p.getByLabel('Código de estudiante').fill(issued.participant.studentCode);await p.getByLabel('Clave',{exact:true}).fill(issued.temporaryPassword);await p.getByRole('button',{name:'Ingresar',exact:true}).click();await expect(p.getByRole('heading',{name:'Aprender',exact:true})).toBeVisible();}
 try{
  await page.goto('/ingresar');await expect(page.getByRole('heading',{name:'Bienvenido'})).toBeVisible();await shot('ingresar');await login(page);
  expect(await page.getByRole('navigation',{name:'Navegación principal'}).getByRole('link').allTextContents()).toEqual(['Aprender','Laboratorio','Progreso']);
  await expect(page.locator('.student-menu summary')).toContainText(issued.participant.studentCode);await shot('aprender');
  let count=0;for(const concept of ['Secuencias','Variables','Condicionales','Ciclos']){await page.getByRole('button',{name:new RegExp(`${concept}:`)}).click();count+=await page.locator('.challenge-node').count();}expect(count).toBe(18);
  await page.getByRole('button',{name:/Condicionales:/}).click();await shot('concept-map');
  for(const levelId of ['SEQ-01','VAR-01','COND-01','COND-02']){
   const program=JSON.parse(readFileSync(`../backend/src/test/resources/challenges/${levelId}.json`,'utf8'));
   const r=await request.post(`${api}/api/attempts`,{data:{studentId:id,levelId,program,hintCount:0,resolutionTimeMs:1000}});expect(r.status()).toBe(201);expect((await r.json()).execution.evaluation.activityPassed).toBe(true);
  }
  await page.goto('/aprender/COND-03');await expect(page.getByTestId('game-blockly').locator('.blocklySvg')).toBeVisible();
  await expect(page.getByText('Reto 3 de 5',{exact:true})).toBeVisible();
  await expect(page.getByText('Resultado de la ejecución',{exact:true})).toHaveCount(0);await expect(page.getByText('Tu progreso en este tema',{exact:true})).toHaveCount(0);await expect(page.getByRole('dialog')).toHaveCount(0);
  await page.getByText('Añadir con teclado',{exact:true}).click();await page.getByRole('button',{name:'+ Avanzar',exact:true}).click();await page.getByRole('button',{name:'+ Avanzar',exact:true}).click();await page.getByText('Añadir con teclado',{exact:true}).click();
  const workspace=page.getByRole('region',{name:'Blocks workspace.'});
  await page.getByRole('treeitem',{name:'Condicionales',exact:true}).click();
  const initialMove=workspace.getByRole('figure',{name:/Avanzar/}).last();const mb=await initialMove.boundingBox();
  await drag(page,page.getByRole('option',{name:/^Si,.*has 2 branches/}),initialMove,20,mb!.height+6);
  const branch=workspace.getByRole('figure',{name:/^Si,.*has 2 branches/}).first();await expect(branch).toBeVisible();
  await page.getByRole('treeitem',{name:'Condiciones',exact:true}).click();const box=await branch.boundingBox();await drag(page,page.getByRole('option',{name:/^Sensor/}),branch,box!.width-8,12);
  await workspace.getByRole('button',{name:/dropdown: FRONT_CLEAR/}).click();await page.getByText('HAS_KEY',{exact:true}).click();
  await page.getByRole('treeitem',{name:'Secuencias',exact:true}).click();const body=await branch.boundingBox();await drag(page,page.getByRole('option',{name:/^Avanzar/}),branch,body!.width-8,40);
  await page.getByRole('treeitem',{name:'Secuencias',exact:true}).click();const eb=await branch.boundingBox();await drag(page,page.getByRole('option',{name:/^Girar a la derecha/}),branch,eb!.width-8,eb!.height-24);
  await page.getByText('Añadir con teclado',{exact:true}).click();for(let n=0;n<2;n++)await page.getByRole('button',{name:'+ Avanzar',exact:true}).click();await page.getByText('Añadir con teclado',{exact:true}).click();
  await shot('cond-03-programa');
  const response=page.waitForResponse(r=>r.url().endsWith('/api/attempts')&&r.request().method()==='POST');await page.getByRole('button',{name:'▶ Ejecutar',exact:true}).click();const attempt=await(await response).json();expect(attempt.execution.evaluation.activityPassed).toBe(true);
  await expect(page.getByRole('status').filter({hasText:'¡Reto completado!'})).toBeVisible();await expect(page.getByRole('region',{name:'Luma',exact:true})).toHaveAttribute('data-state','SUCCESS');await shot('cond-03-exito');
  await page.getByRole('button',{name:'Ver modelo',exact:true}).click();await expect(page.getByRole('dialog')).toContainText('Modelo válido');await page.keyboard.press('Escape');await expect(page.getByRole('dialog')).toHaveCount(0);
  await page.goto('/aprender/LOOP-01');await page.getByText('Añadir con teclado',{exact:true}).click();for(let n=0;n<7;n++)await page.getByRole('button',{name:'+ Avanzar',exact:true}).click();await page.getByText('Añadir con teclado',{exact:true}).click();
  await page.getByRole('button',{name:'▶ Ejecutar',exact:true}).click();await expect(page.getByRole('status').filter({hasText:'todavía falta aplicar el concepto'})).toBeVisible();await expect(page.locator('.evaluation-feedback')).toHaveAttribute('data-passed','false');await expect(page.getByRole('region',{name:'Pista'})).toBeVisible();await shot('loop-01-pedagogia');
  await page.goto('/progreso');await expect(page.getByRole('meter')).toHaveCount(4);await shot('progreso');
  const progress=await(await context.request.get(`${api}/api/students/${id}/progress`)).json();
  await page.goto('/laboratorio');await expect(page.getByTestId('blockly-editor').locator('.blocklySvg')).toBeVisible();await shot('laboratorio');
  await page.getByRole('button',{name:'Cargar ejemplo',exact:true}).click();const sandbox=page.waitForResponse(r=>r.url().endsWith('/api/laboratory/execute'));await page.getByRole('button',{name:'▶ Ejecutar',exact:true}).click();expect((await sandbox).status()).toBe(200);expect(await(await context.request.get(`${api}/api/students/${id}/progress`)).json()).toEqual(progress);
  await page.locator('.student-menu summary').click();await page.getByRole('button',{name:'Cerrar sesión',exact:true}).click();await expect(page).toHaveURL(/ingresar$/);
  const fresh=await browser.newContext({viewport});try{const p=await fresh.newPage();await login(p);expect(await(await fresh.request.get(`${api}/api/students/${id}/progress`)).json()).toEqual(progress);}finally{await fresh.close();}
  await page.goto('/docente/login');await shot('docente-login');await loginTeacherPage(page);await shot('docente');
  expect(errors).toEqual([]);
 }finally{writeFileSync(info.outputPath('final-audits.json'),JSON.stringify(audits,null,2));if(evidence)writeFileSync(`${evidence}/${viewport.width}-audits.json`,JSON.stringify(audits,null,2));await context.close().catch(()=>{});}
});
