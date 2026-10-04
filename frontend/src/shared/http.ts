export const apiBase = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export class ApiError extends Error {
 constructor(public readonly status: number) {
  super(status === 401 ? 'La sesión no está activa.' : status === 403 ? 'Solicitud no autorizada. Vuelve a iniciar sesión.' : status === 0 ? 'No se pudo conectar con el servidor. Comprueba la conexión.' : 'No se pudo completar la solicitud. Intenta nuevamente.');
 }
}
export async function httpFetch(input: RequestInfo | URL, init?: RequestInit): Promise<Response> {
 try {
  const headers=new Headers(init?.headers);
  if(!['GET','HEAD','OPTIONS'].includes((init?.method??'GET').toUpperCase())&&!headers.has('X-CSRF-TOKEN')){
   const endpoint=String(input).includes('/api/teacher/')?'/api/teacher/csrf':'/api/auth/student/csrf';
   const tokenResponse=await fetch(`${apiBase}${endpoint}`,{credentials:'include',signal:init?.signal});
   checkResponse(tokenResponse);const token=await tokenResponse.json() as {headerName:string;token:string};headers.set(token.headerName,token.token);
  }
  const response=await fetch(input,{...init,credentials:'include',headers});
  if(response.status===401&&!String(input).includes('/api/auth/')&&!String(input).includes('/api/teacher/'))window.dispatchEvent(new Event('student-session-expired'));
  return response;
 } catch (error) {
  if (error instanceof ApiError) throw error;
  if (error instanceof DOMException && error.name === 'AbortError') throw error;
  throw new ApiError(0);
 }
}
export function checkResponse(response: Response, accepted: readonly number[] = []): Response {
 if (!response.ok && !accepted.includes(response.status)) throw new ApiError(response.status);
 return response;
}
