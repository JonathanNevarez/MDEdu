import {test,expect,createStudent,loginStudentById} from './studentFixtures';
import {readFileSync} from 'node:fs';
test('Gemini owner quota yields safe fallback on the next attempt',async({request,page})=>{
 test.skip(process.env.GEMINI_MOCK_E2E!=='true','Only the loopback mock launcher');
 const api=process.env.VITE_API_BASE_URL??'http://127.0.0.1:8080';
 const studentId=(await(await createStudent(request)).json()).id as string;
 const program=JSON.parse(readFileSync('../backend/src/test/resources/evaluation/SEQUENCES.json','utf8'));
 for(let i=0;i<2;i++){
  const attempt=await request.post(`${api}/api/attempts`,{data:{studentId,levelId:'SEQUENCES',program,hintCount:0,resolutionTimeMs:1000}});expect(attempt.status()).toBe(201);
  const attemptId=(await attempt.json()).attemptId;
  const response=await request.post(`${api}/api/feedback/generate`,{data:{studentId,attemptId}});expect(response.status()).toBe(200);
  const feedback=await response.json();expect(feedback.source).toBe(i===0?'GEMINI':'FALLBACK');
  if(i===1){expect(feedback.fallbackReason).toBe('RATE_LIMITED');expect(feedback.llmUsed).toBe(false);}
 }
 await page.goto('/');await loginStudentById(page,studentId);await page.evaluate(id=>localStorage.setItem('mdedu.student.id.v1',id),studentId);
 await page.goto('/aventura/SEQUENCES');await expect(page.locator('.game-activity')).toBeVisible();
});
