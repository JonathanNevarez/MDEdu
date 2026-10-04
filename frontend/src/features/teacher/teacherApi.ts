import {apiBase, httpFetch, checkResponse} from '../../shared/http';
export async function teacherSession(signal?: AbortSignal) {
 const response=await httpFetch(`${apiBase}/api/teacher/session`,{credentials:'include',signal});
 if(response.status===401 || response.status===403) return false;
 checkResponse(response);return true;
}
async function csrf() {
 const response=checkResponse(await httpFetch(`${apiBase}/api/teacher/csrf`,{credentials:'include'}));
 return response.json() as Promise<{token:string;headerName:string}>;
}
export async function teacherLogin(username:string,password:string) {
 const token=await csrf();
 checkResponse(await httpFetch(`${apiBase}/api/teacher/login`,{method:'POST',credentials:'include',headers:{'Content-Type':'application/x-www-form-urlencoded',[token.headerName]:token.token},body:new URLSearchParams({username,password})}));
}
export async function teacherLogout() {
 const token=await csrf();
 checkResponse(await httpFetch(`${apiBase}/api/teacher/logout`,{method:'POST',credentials:'include',headers:{[token.headerName]:token.token}}));
}
