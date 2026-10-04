import {test,expect} from '@playwright/test';
import {authenticateTeacher,loginTeacherPage} from './teacherAuth';
import {csrfRequest} from './studentFixtures';
const api=process.env.VITE_API_BASE_URL??'http://127.0.0.1:8080';
test('teacher provisions once; fresh devices recover identical progress and cannot impersonate another student',async({browser,request})=>{
 test.setTimeout(90000);
 await authenticateTeacher(request);const teacher=csrfRequest(request);
 const c=await browser.newContext();const a=await browser.newContext();const b=await browser.newContext();
 try{
  const docent=await c.newPage();await loginTeacherPage(docent);await docent.getByRole('button',{name:'Participantes',exact:true}).click();
  await docent.getByRole('button',{name:'Crear participante',exact:true}).click();const issued=docent.getByRole('region',{name:'Clave temporal'});await expect(issued).toBeVisible();
  const code=await issued.getByRole('heading').innerText();const password=await issued.locator('code').innerText();await issued.getByRole('button',{name:'Ocultar clave'}).click();await expect(issued).toHaveCount(0);
  const pa=await a.newPage();await pa.goto('/aventura');await expect(pa).toHaveURL(/ingresar$/);
  await pa.getByLabel('Código de estudiante').fill(code);await pa.getByLabel('Clave',{exact:true}).fill(password);await pa.getByRole('button',{name:'Ingresar',exact:true}).click();
  await pa.getByRole('button',{name:/Secuencias:.*Disponible/}).click();
  for(const name of ['+ Avanzar','+ Avanzar','+ Girar derecha','+ Avanzar','+ Avanzar'])await pa.getByRole('button',{name,exact:true}).click();
  await pa.getByRole('button',{name:'▶ Ejecutar',exact:true}).click();await expect(pa.getByRole('status').filter({hasText:'¡Nivel completado!'})).toBeVisible();
  const identity=await(await a.request.get(`${api}/api/auth/student/me`)).json();const id=identity.studentId;
  const model=await(await a.request.get(`${api}/api/students/${id}/model`)).json();const progress=await(await a.request.get(`${api}/api/students/${id}/progress`)).json();
  await pa.getByRole('button',{name:'Cerrar sesión',exact:true}).click();await expect(pa).toHaveURL(/ingresar$/);
  const pb=await b.newPage();await pb.goto('/ingresar');expect(await pb.evaluate(()=>localStorage.length+sessionStorage.length)).toBe(0);
  await pb.getByLabel('Código de estudiante').fill(code);await pb.getByLabel('Clave',{exact:true}).fill(password);await pb.getByRole('button',{name:'Ingresar',exact:true}).click();await expect(pb.getByRole('button',{name:/Variables:.*Disponible/})).toBeVisible();
  expect(await(await b.request.get(`${api}/api/auth/student/me`)).json()).toEqual(identity);
  expect(await(await b.request.get(`${api}/api/students/${id}/model`)).json()).toEqual(model);expect(await(await b.request.get(`${api}/api/students/${id}/progress`)).json()).toEqual(progress);
  const other=await(await teacher.post(`${api}/api/teacher/participants`)).json();const otherId=other.participant.studentId;
  expect((await b.request.get(`${api}/api/students/${otherId}/model`)).status()).toBe(403);
  await pb.evaluate(v=>localStorage.setItem('mdedu.student.id.v1',v),otherId);await pb.reload();await expect(pb.getByRole('button',{name:/Variables:.*Disponible/})).toBeVisible();expect((await(await b.request.get(`${api}/api/auth/student/me`)).json()).studentId).toBe(id);
  for(const target of [id,otherId])expect((await teacher.get(`${api}/api/meta/students/${target}`)).status()).toBe(200);
  const reset=await teacher.post(`${api}/api/teacher/participants/${id}/reset-password`);expect(reset.status()).toBe(200);expect((await b.request.get(`${api}/api/auth/student/me`)).status()).toBe(401);
  await pb.reload();await expect(pb).toHaveURL(/ingresar$/);await pb.getByLabel('Código de estudiante').fill(code);await pb.getByLabel('Clave',{exact:true}).fill(password);await pb.getByRole('button',{name:'Ingresar',exact:true}).click();await expect(pb.getByRole('alert')).toHaveText('Código o clave incorrectos.');await expect(pb.getByLabel('Clave',{exact:true})).toHaveValue('');
 }finally{await c.close();await a.close();await b.close();}
});
