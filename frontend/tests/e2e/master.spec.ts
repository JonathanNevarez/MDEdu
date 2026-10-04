import {test,expect,createStudent,loginStudentById} from './studentFixtures';
import {readFileSync} from 'node:fs';
import {authenticateTeacher,loginTeacherPage} from './teacherAuth';
test('master: repeated pedagogical error, fallback, Luma, trace and legacy inspection',async({page,request})=>{
 const api=process.env.VITE_API_BASE_URL??'http://127.0.0.1:8080';
 await authenticateTeacher(request);
 const studentId=(await(await createStudent(request)).json()).id as string;
 let attemptId='';
 for(const levelId of ['SEQUENCES','VARIABLES','CONDITIONALS','LOOPS','LOOPS','LOOPS']){
  const program=JSON.parse(readFileSync(`../backend/src/test/resources/evaluation/${levelId==='LOOPS'?'LOOPS_MANUAL':levelId}.json`,'utf8'));
  const response=await request.post(`${api}/api/attempts`,{data:{studentId,levelId,program,hintCount:0,resolutionTimeMs:1000}});expect(response.status()).toBe(201);
  const attempt=await response.json();attemptId=attempt.attemptId;
  if(levelId==='LOOPS'){expect(attempt.execution.success).toBe(true);expect(attempt.execution.evaluation.activityPassed).toBe(false);}
 }
 const model=await(await request.get(`${api}/api/students/${studentId}/model`)).json();
 const loops=model.conceptMasteries.find((m:{conceptId:string})=>m.conceptId==='LOOPS');expect(loops.consecutiveFailures).toBe(3);
 const feedback=await(await request.post(`${api}/api/feedback/generate`,{data:{studentId,attemptId}})).json();expect(feedback.source).toBe('FALLBACK');
 await page.goto('/');await loginStudentById(page,studentId);await page.evaluate(id=>localStorage.setItem('mdedu.student.id.v1',id),studentId);
 await page.goto('/aventura/LOOPS');await expect(page.getByRole('region',{name:'Luma'})).toBeVisible();await expect(page.getByRole('region',{name:'Pista'})).toBeVisible();
 await page.getByRole('button',{name:'Intentar de nuevo'}).focus();await page.keyboard.press('Enter');await expect(page.getByRole('button',{name:'▶ Ejecutar',exact:true})).toBeEnabled();
 const trace=await(await request.get(`${api}/api/students/${studentId}/attempts/${attemptId}/timeline`)).json();expect(trace.traceStatus).toBe('COMPLETE');
 expect(trace.attempt.patterns).toContain('REPETITIVE_SEQUENCE_WITHOUT_LOOP');expect(trace.attempt.masteryDelta).toBeLessThanOrEqual(0);
 await loginTeacherPage(page);await page.getByLabel('Estudiante pseudónimo').selectOption(studentId);
 const attempts=page.getByRole('button',{name:'Intentos',exact:true});await attempts.focus();await page.keyboard.press('Enter');
 const open=page.getByRole('button',{name:`Abrir timeline ${attemptId}`,exact:true});await open.focus();await page.keyboard.press('Enter');
 await expect(page.getByRole('region',{name:'Timeline del intento'})).toContainText('COMPLETE');
 // A controlled legacy response tests presentation without changing persisted audit evidence.
 await page.route(`**/api/students/${studentId}/attempts/${attemptId}/timeline`,route=>route.fulfill({json:{...trace,traceStatus:'PARTIAL',gaps:['LEGACY_EVENT_MISSING'],events:[]}}));
 await page.reload();await page.getByLabel('Estudiante pseudónimo').selectOption(studentId);await page.getByRole('button',{name:'Intentos',exact:true}).click();await page.getByRole('button',{name:`Abrir timeline ${attemptId}`,exact:true}).click();
 await expect(page.getByRole('region',{name:'Timeline del intento'})).toContainText('PARTIAL');
});
