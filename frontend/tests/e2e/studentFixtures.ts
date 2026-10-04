import {test as base,expect,type APIRequestContext,type Page,type BrowserContext} from '@playwright/test';
import {authenticateTeacher} from './teacherAuth';
const api=process.env.VITE_API_BASE_URL??'http://127.0.0.1:8080';
export interface Account {participant:{studentId:string;studentCode:string};temporaryPassword:string}
const accounts=new Map<string,Account>();
export function csrfRequest(request:APIRequestContext):APIRequestContext {
 return new Proxy(request,{get(target,key){const value=Reflect.get(target,key);if(['post','put','patch','delete'].includes(String(key)))return async(url:string,options:Record<string,unknown>={})=>{
  const token=await(await target.get(`${api}/api/teacher/csrf`)).json() as {headerName:string;token:string};
  return (value as (url:string,options:unknown)=>Promise<unknown>).call(target,url,{...options,headers:{...(options.headers as object??{}),[token.headerName]:token.token}});
 };return typeof value==='function'?value.bind(target):value;}});
}
// Provision through the real teacher API; no UUID-to-account migration or test backdoor.
export async function createStudent(request:APIRequestContext){
 const token=await(await request.get(`${api}/api/teacher/csrf`)).json();
 const r=await request.post(`${api}/api/teacher/participants`,{headers:{[token.headerName]:token.token}});expect(r.status()).toBe(201);
 const account=await r.json() as Account;accounts.set(account.participant.studentId,account);
 return {status:()=>201,json:async()=>({id:account.participant.studentId})};
}
export async function loginStudentContext(context:BrowserContext,account:Account){
 const token=await(await context.request.get(`${api}/api/auth/student/csrf`)).json();
 const r=await context.request.post(`${api}/api/auth/student/login`,{headers:{[token.headerName]:token.token},data:{studentCode:account.participant.studentCode,password:account.temporaryPassword}});expect(r.status()).toBe(200);
}
export async function loginStudentById(page:Page,id:string){const account=accounts.get(id);if(!account)throw new Error('Account fixture missing');await loginStudentContext(page.context(),account);}
export const test=base.extend({
 request:async({request},use)=>{await authenticateTeacher(request);await use(csrfRequest(request));},
 page:async({page,request},use)=>{const id=(await(await createStudent(request)).json()).id;await loginStudentById(page,id);await use(page);},
});
export {expect};
