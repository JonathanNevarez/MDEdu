import {useEffect,useState,type ReactNode,type FormEvent} from 'react';
import {Navigate,useNavigate} from 'react-router-dom';
import {Landscape,LumaPortrait,LoadingState} from '../../shared/Visuals';
import {ApiError} from '../../shared/http';
import {StudentAppShell} from './StudentAppShell';
import {studentMe,studentLogin,studentLogout,type StudentIdentity} from './studentAuth';
export function StudentAccess({children}:{children:ReactNode}){
 const [identity,setIdentity]=useState<StudentIdentity|null|undefined>();const [error,setError]=useState(false);
 useEffect(()=>{const controller=new AbortController();void studentMe(controller.signal).then(setIdentity).catch(()=>{if(!controller.signal.aborted)setError(true);});
 const expired=()=>setIdentity(null);window.addEventListener('student-session-expired',expired);
 return()=>{controller.abort();window.removeEventListener('student-session-expired',expired);};},[]);
 if(identity===null)return <Navigate to="/ingresar" replace/>;
 if(error)return <main className="page-content"><section className="welcome-panel"><h1>No se pudo comprobar la sesión</h1><p role="alert">Revisa la conexión y vuelve a intentar.</p><button onClick={()=>window.location.reload()}>Reintentar</button></section></main>;
 if(!identity)return <LoadingState text="Comprobando sesión de estudiante…"/>;
 return <StudentAppShell identity={identity} onLogout={()=>{void studentLogout().then(()=>setIdentity(null)).catch(()=>setError(true));}}>{children}</StudentAppShell>;
}
export function StudentLoginPage(){
 const navigate=useNavigate();const [code,setCode]=useState('');const [password,setPassword]=useState('');const [busy,setBusy]=useState(false);const [error,setError]=useState('');
 useEffect(()=>{const c=new AbortController();void studentMe(c.signal).then(i=>{if(i&&!c.signal.aborted)navigate('/aprender',{replace:true});}).catch(()=>{});return()=>c.abort();},[navigate]);
 async function submit(e:FormEvent){e.preventDefault();setBusy(true);setError('');try{await studentLogin(code,password);navigate('/aprender',{replace:true});}catch(e){setError(e instanceof ApiError&&e.status===429?'Demasiados intentos. Espera unos minutos antes de volver a intentar.':e instanceof ApiError&&e.status===401?'Código o clave incorrectos.':'No se pudo ingresar. Comprueba la conexión e intenta nuevamente.');}finally{setPassword('');setBusy(false);}}
 return <section className="welcome-panel teacher-login student-login"><div className="login-intro"><Landscape/><LumaPortrait state="GUIDE"/><h1>Bienvenido</h1><p>Usa el código y la clave que te entregó tu docente. Tu progreso te acompaña en cualquier computadora.</p></div><div className="login-form"><h2>Ingresa a tu aventura</h2><form onSubmit={e=>{void submit(e);}} className="lab-controls"><label>Código de estudiante<input autoComplete="username" required maxLength={10} pattern="[Ee][Ss][Tt]-[0-9]{3,6}" placeholder="EST-001" value={code} onChange={e=>setCode(e.target.value)}/></label><label>Clave<input type="password" autoComplete="current-password" required maxLength={72} value={password} onChange={e=>setPassword(e.target.value)}/></label><button className="primary" disabled={busy}>{busy?'Comprobando…':'Ingresar'}</button></form>{error&&<p role="alert">{error}</p>}</div></section>;
}
