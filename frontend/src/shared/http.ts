export const apiBase = import.meta.env.VITE_API_BASE_URL ?? 'http://127.0.0.1:8080';
export class ApiError extends Error {
 constructor(public readonly status: number) {
  super(status === 401 ? 'La sesión docente no está activa.' : status === 403 ? 'Solicitud no autorizada. Vuelve a iniciar sesión.' : status === 0 ? 'No se pudo conectar con el servidor. Comprueba la conexión.' : 'No se pudo completar la solicitud. Intenta nuevamente.');
 }
}
export async function httpFetch(input: RequestInfo | URL, init?: RequestInit): Promise<Response> {
 try {return await fetch(input, init);} catch (error) {
  if (error instanceof DOMException && error.name === 'AbortError') throw error;
  throw new ApiError(0);
 }
}
export function checkResponse(response: Response, accepted: readonly number[] = []): Response {
 if (!response.ok && !accepted.includes(response.status)) throw new ApiError(response.status);
 return response;
}
