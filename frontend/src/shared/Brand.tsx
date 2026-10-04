import {NavLink} from 'react-router-dom';
import {Icon} from './Visuals';
export function Brand(){return <NavLink className="site-name" to="/aprender" aria-label="Mi primera programación"><span className="brand-symbol"><Icon name="book"/></span><strong>Mi primera programación</strong></NavLink>;}
