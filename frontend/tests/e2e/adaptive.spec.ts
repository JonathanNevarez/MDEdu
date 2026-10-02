import { readFileSync, writeFileSync } from 'node:fs';
import { test, expect, type APIRequestContext } from '@playwright/test';
const api = process.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
async function submit(request: APIRequestContext, studentId: string, levelId: string, fixture = levelId) {
  const program = JSON.parse(readFileSync(`../backend/src/test/resources/evaluation/${fixture}.json`, 'utf8'));
  const r = await request.post(`${api}/api/attempts`, {data: {studentId, levelId, program, hintCount: 0, resolutionTimeMs: 1000}});
  expect(r.status()).toBe(201);return r.json();
}
test('two real histories produce distinct UI; hints, Luma, repeat and refresh need no OpenAI', async ({browser, request}, testInfo) => {
  const a = await browser.newContext(); const b = await browser.newContext();
  try {
    const ids: string[] = [];
    for (let i=0;i<2;i++) {
      const r = await request.post(`${api}/api/students`, {data: {}});ids.push((await r.json()).id as string);
      for(const level of ['SEQUENCES','VARIABLES','CONDITIONALS']) await submit(request,ids[i]!,level);
    }
    const failure = await submit(request,ids[0]!,'LOOPS','LOOPS_MANUAL');
    const success = await submit(request,ids[1]!,'LOOPS');
    expect(failure.execution.success).toBe(true);expect(failure.execution.evaluation.activityPassed).toBe(false);
    expect(success.execution.evaluation.activityPassed).toBe(true);
    const pa=await a.newPage();const pb=await b.newPage();
    for(const [page,id] of [[pa,ids[0]!],[pb,ids[1]!]] as const) {
      await page.goto('/');await page.evaluate(student => localStorage.setItem('mdedu.student.id.v1',student),id);
      await page.goto('/aventura/LOOPS');
    }
    await expect(pa.getByRole('region',{name:'Pista'})).toBeVisible();
    await expect(pa.getByRole('region',{name:'Luma'})).toBeVisible();
    await expect(pa.getByRole('button',{name:'Intentar de nuevo'})).toBeVisible();
    await expect(pb.locator('.game-activity')).toBeVisible();
    await expect(pb.getByRole('region',{name:'Pista'})).toHaveCount(0);
    const ca=await (await request.get(`${api}/api/students/${ids[0]}/attempts/${failure.attemptId}/ui-configuration`)).json();
    const cb=await (await request.get(`${api}/api/students/${ids[1]}/attempts/${success.attemptId}/ui-configuration`)).json();
    expect(ca.fingerprint).not.toBe(cb.fingerprint);expect(ca.feedback.llmUsed).toBe(false);
    const evidence=testInfo.outputPath('real-configurations.json');writeFileSync(evidence,JSON.stringify({ca,cb},null,2));
    await testInfo.attach('real-configurations',{path:evidence,contentType:'application/json'});
    await pa.getByRole('button',{name:'Intentar de nuevo'}).click();await expect(pa.getByRole('button',{name:'▶ Ejecutar',exact:true})).toBeEnabled();
    await pa.reload();await expect(pa.getByRole('region',{name:'Pista'})).toBeVisible();
    await expect(pa.getByRole('button',{name:'Intentar de nuevo'})).toBeVisible();
    await pa.screenshot({path:testInfo.outputPath('student-a-assisted.png'),fullPage:true});
    await pb.screenshot({path:testInfo.outputPath('student-b-standard.png'),fullPage:true});
    expect((await (await request.get(`${api}/api/students/${ids[0]}/attempts/${failure.attemptId}/ui-configuration`)).json()).fingerprint).toBe(ca.fingerprint);
  } finally {await a.close();await b.close();}
});
test('advance uses a real backend target; local tampering cannot unlock a level', async ({page,request}) => {
  const id=(await (await request.post(`${api}/api/students`,{data:{}})).json()).id as string;
  const denied=await request.get(`${api}/api/students/${id}/activities/LOOPS/ui-configuration`);expect(denied.status()).toBe(409);
  let attempt;
  for(let i=0;i<9;i++) attempt=await submit(request,id,'SEQUENCES');
  const c=await (await request.get(`${api}/api/students/${id}/attempts/${attempt.attemptId}/ui-configuration`)).json();
  expect(c.configuration.navigationMode).toBe('ADVANCE');expect(c.configuration.nextActivityId).toBe('VARIABLES');
  await page.goto('/');await page.evaluate(student=>localStorage.setItem('mdedu.student.id.v1',student),id);
  await page.goto('/aventura/SEQUENCES');await page.getByRole('link',{name:'Continuar',exact:true}).click();
  await expect(page).toHaveURL(/aventura\/VARIABLES$/);
  // Controlled client-side tampering: server progress and submission remain authoritative.
  await page.route('**/api/students/*/activities/SEQUENCES/ui-configuration',route=>route.fulfill({json:{...c,configuration:{...c.configuration,nextActivityId:'LOOPS'}}}));
  await page.goto('/aventura/SEQUENCES');await page.getByRole('link',{name:'Continuar',exact:true}).click();
  const lockedProgram=JSON.parse(readFileSync('../backend/src/test/resources/evaluation/LOOPS.json','utf8'));
  expect((await request.post(`${api}/api/attempts`,{data:{studentId:id,levelId:'LOOPS',program:lockedProgram,hintCount:0,resolutionTimeMs:1000}})).status()).toBe(409);
await expect(page.getByRole('heading',{name:'Nivel bloqueado'})).toBeVisible();
});
test('controlled configurations show and hide escaped code in the same bundle, with reduced motion', async ({page,request},testInfo) => {
  const id=(await (await request.post(`${api}/api/students`,{data:{}})).json()).id as string;
  const original=await (await request.get(`${api}/api/students/${id}/activities/SEQUENCES/ui-configuration`)).json();
  let show=false;
  await page.route('**/api/students/*/activities/SEQUENCES/ui-configuration',async route=>{
    await route.fulfill({json:{...original,configuration:{...original.configuration,showCodePanel:show,generatedCodeVisible:show,
      hintPanelMode:show?'EXPANDED':'HIDDEN',hintStage:show?'CONCEPTUAL_HINT':'NONE',tutorMode:show?'HINT':'HIDDEN',transitionMode:'SUBTLE'},
      code:{available:true,text:'<script>window.injected=true</script>',reason:null}}});
  });
  await page.emulateMedia({reducedMotion:'reduce'});await page.goto('/');
  await page.evaluate(student=>localStorage.setItem('mdedu.student.id.v1',student),id);await page.goto('/aventura/SEQUENCES');
  await expect(page.locator('.game-activity')).toBeVisible();await expect(page.getByRole('region',{name:'Código generado'})).toHaveCount(0);
  show=true;await page.reload();await expect(page.getByRole('region',{name:'Código generado'})).toBeVisible();
  await expect(page.getByRole('region',{name:'Pista'})).toBeVisible();
  await expect(page.locator('.code-panel code')).toHaveText('<script>window.injected=true</script>');
  expect(await page.locator('.code-panel script').count()).toBe(0);
  expect(await page.locator('.ui-transition').evaluate(el=>el.ownerDocument.defaultView!.getComputedStyle(el).animationName)).toBe('none');
  await page.screenshot({path:testInfo.outputPath('controlled-config-b.png'),fullPage:true});
  show=false;await page.reload();await expect(page.getByRole('region',{name:'Código generado'})).toHaveCount(0);
});
