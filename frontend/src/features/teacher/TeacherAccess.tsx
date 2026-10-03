import { Icon, LoadingState } from '../../shared/Visuals';
import { useEffect, useState } from 'react';
import type { ReactNode, FormEvent } from 'react';
import { Navigate, useNavigate } from 'react-router-dom';
import {teacherLogin,teacherLogout,teacherSession} from './teacherApi';
export function TeacherAccess({children}:{children:ReactNode}) {
 const [state,setState]=useState<'loading'|'allowed'|'denied'|'error'>('loading');
 const [logoutError,setLogoutError]=useState(false);
 useEffect(()=>{const controller=new AbortController();teacherSession(controller.signal).then(allowed=>{if(!controller.signal.aborted)setState(allowed?'allowed':'denied');}).catch(()=>{if(!controller.signal.aborted)setState('error');});const expired=()=>setState('denied');window.addEventListener('teacher-session-expired',expired);return()=>{controller.abort();window.removeEventListener('teacher-session-expired',expired);};},[]);
 if(state==='denied') return <Navigate to="/docente/login" replace/>;
 if(state==='loading') return <LoadingState text="Comprobando sesión docente…"/>;
 if(state==='error') return <p role="alert">No se pudo comprobar la sesión. Comprueba la conexión y recarga.</p>;
 return <><button className="teacher-logout" onClick={()=>{void teacherLogout().then(()=>setState('denied')).catch(()=>setLogoutError(true));}}>Cerrar sesión docente</button>{logoutError&&<p role="alert">No se pudo cerrar la sesión. Intenta nuevamente.</p>}{children}</>;
}
export function TeacherLoginPage() {
 const navigate=useNavigate();const [username,setUsername]=useState('');const [password,setPassword]=useState('');const [busy,setBusy]=useState(false);const [error,setError]=useState(false);
 async function submit(event:FormEvent){event.preventDefault();setBusy(true);setError(false);try{await teacherLogin(username,password);setPassword('');navigate('/docente',{replace:true});}catch{setPassword('');setError(true);}finally{setBusy(false);}}
 return <section className="welcome-panel teacher-login"><div className="login-intro"><Icon name="book"/><p className="eyebrow">Mi primera programación</p><h1>Acceso docente</h1><p>Una mirada al aprendizaje, intento a intento.</p><p>Consulta el progreso y la trazabilidad en un espacio de solo lectura.</p></div><div className="login-form"><p className="eyebrow">Bienvenido de nuevo</p><h2>Entrar a la vista docente</h2><form className="lab-controls" onSubmit={event=>{void submit(event);}}><label>Usuario<input name="username" autoComplete="username" required maxLength={80} value={username} onChange={e=>setUsername(e.target.value)}/></label><label>Contraseña<input name="password" type="password" autoComplete="current-password" required maxLength={72} value={password} onChange={e=>setPassword(e.target.value)}/></label><button className="primary" disabled={busy} type="submit">{busy?'Comprobando…':'Iniciar sesión'}</button></form>{error&&<p role="alert">No se pudo iniciar sesión. Revisa las credenciales y la conexión.</p>}<p className="login-note">Las credenciales las configura el responsable del prototipo.</p></div></section>;
}
