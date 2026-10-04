import {apiBase,httpFetch,checkResponse} from '../../shared/http';
import {clearTelemetrySession} from '../telemetry/client';
export interface StudentIdentity {studentId:string;studentCode:string;authenticated:boolean}
export async function studentMe(signal?:AbortSignal):Promise<StudentIdentity|null>{
 const r=await httpFetch(`${apiBase}/api/auth/student/me`,{signal});
 if(r.status===401)return null;
 return checkResponse(r).json() as Promise<StudentIdentity>;
}
export function clearStudentBrowserState(){
 clearTelemetrySession();
 try {localStorage.removeItem('mdedu.student.id.v1');localStorage.removeItem('mdedu.game.progress.v1');}catch{/* No stored authority. */}
}
export async function studentLogin(studentCode:string,password:string){
 const r=checkResponse(await httpFetch(`${apiBase}/api/auth/student/login`,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({studentCode,password})}));
 clearStudentBrowserState();return r.json() as Promise<StudentIdentity>;
}
export async function studentLogout(){checkResponse(await httpFetch(`${apiBase}/api/auth/student/logout`,{method:'POST'}));clearStudentBrowserState();}
