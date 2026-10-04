import {useEffect,useState,type ReactNode,type FormEvent} from 'react';
import {Navigate,useNavigate} from 'react-router-dom';
import {LoadingState} from '../../shared/Visuals';
import {ApiError} from '../../shared/http';
import {studentMe,studentLogin,studentLogout,type StudentIdentity} from './studentAuth';
export function StudentAccess({children}:{children:ReactNode}){
 const [identity,setIdentity]=useState<StudentIdentity|null|undefined>();const [error,setError]=useState(false);
 useEffect(()=>{const controller=new AbortController();void studentMe(controller.signal).then(setIdentity).catch(()=>{if(!controller.signal.aborted)setError(true);});
 const expired=()=>setIdentity(null);window.addEventListener('student-session-expired',expired);
 return()=>{controller.abort();window.removeEventListener('student-session-expired',expired);};},[]);
 if(identity===null)return <Navigate to="/ingresar" replace/>;
 if(error)return <p role="alert">No se pudo comprobar la sesión. Recarga para intentar nuevamente.</p>;
 if(!identity)return <LoadingState text="Comprobando sesión de estudiante…"/>;
 return <><div className="student-session"><span>{identity.studentCode}</span><button onClick={()=>{void studentLogout().then(()=>setIdentity(null)).catch(()=>setError(true));}}>Cerrar sesión</button></div>{children}</>;
}
export function StudentLoginPage(){
 const navigate=useNavigate();const [code,setCode]=useState('');const [password,setPassword]=useState('');const [busy,setBusy]=useState(false);const [error,setError]=useState('');
 useEffect(()=>{const c=new AbortController();void studentMe(c.signal).then(i=>{if(i&&!c.signal.aborted)navigate('/aventura',{replace:true});}).catch(()=>{});return()=>c.abort();},[navigate]);
 async function submit(e:FormEvent){e.preventDefault();setBusy(true);setError('');try{await studentLogin(code,password);navigate('/aventura',{replace:true});}catch(e){setError(e instanceof ApiError&&e.status===429?'Demasiados intentos. Espera unos minutos antes de volver a intentar.':e instanceof ApiError&&e.status===401?'Código o clave incorrectos.':'No se pudo ingresar. Comprueba la conexión e intenta nuevamente.');}finally{setPassword('');setBusy(false);}}
 return <section className="welcome-panel teacher-login"><div className="login-intro"><h1>Ingresar como estudiante</h1><p>Usa el código y la clave que te entregó tu docente. Tu progreso te acompaña en cualquier computadora.</p></div><div className="login-form"><form onSubmit={e=>{void submit(e);}} className="lab-controls"><label>Código de estudiante<input autoComplete="username" required maxLength={10} pattern="[Ee][Ss][Tt]-[0-9]{3,6}" placeholder="EST-001" value={code} onChange={e=>setCode(e.target.value)}/></label><label>Clave<input type="password" autoComplete="current-password" required maxLength={72} value={password} onChange={e=>setPassword(e.target.value)}/></label><button className="primary" disabled={busy}>{busy?'Comprobando…':'Ingresar'}</button></form>{error&&<p role="alert">{error}</p>}</div></section>;
}
