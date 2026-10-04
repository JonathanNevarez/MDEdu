import type {ReactNode} from 'react';
import {NavLink,Link} from 'react-router-dom';
import {Brand} from '../../shared/Brand';
import {Icon,HeaderLandscape} from '../../shared/Visuals';
import type {StudentIdentity} from './studentAuth';
export function StudentAppShell({identity,onLogout,children}:{identity:StudentIdentity;onLogout:()=>void;children:ReactNode}){
 return <><a className="skip-link" href="#contenido">Saltar al contenido</a><header className="site-header student-header"><HeaderLandscape/><Brand/>
  <nav aria-label="Navegación principal"><NavLink to="/aprender"><Icon name="book"/>Aprender</NavLink><NavLink to="/laboratorio"><Icon name="flask"/>Laboratorio</NavLink><NavLink to="/progreso"><Icon name="flag"/>Progreso</NavLink></nav>
  <details className="student-menu"><summary><span className="student-avatar" aria-hidden="true">●</span>{identity.studentCode}<span aria-hidden="true">⌄</span></summary><div><Link to="/progreso">Mi progreso</Link><button onClick={onLogout}>Cerrar sesión</button></div></details>
 </header><main id="contenido" tabIndex={-1} className="page-content student-content">{children}</main></>;
}
