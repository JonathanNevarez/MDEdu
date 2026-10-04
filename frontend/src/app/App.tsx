import {StudentAccess,StudentLoginPage} from '../features/student/StudentAccess';
import { NavLink, Outlet, Route, Routes } from 'react-router-dom';
import { HomePage } from '../pages/HomePage';
import { NotFoundPage } from '../pages/NotFoundPage';
import { lazy, Suspense } from 'react';
import { TeacherAccess, TeacherLoginPage } from '../features/teacher/TeacherAccess';
import { Icon, LoadingState } from '../shared/Visuals';
const LaboratoryPage = lazy(() => import('../features/programming/pages/LaboratoryPage').then(m => ({ default: m.LaboratoryPage })));
const AdventurePage = lazy(() => import('../features/game/AdventurePage').then(m => ({ default: m.AdventurePage })));

const MetaUiPage = lazy(() => import('../features/metaui/MetaUiPage').then(m => ({ default: m.MetaUiPage })));

function AppLayout() {
  return (
    <>
      <a className="skip-link" href="#contenido">Saltar al contenido</a>
      <header className="site-header">
        <NavLink className="site-name" to="/" aria-label="MDEdu · Inicio"><span className="brand-symbol"><Icon name="route"/></span><span className="brand-copy"><strong>MDEdu</strong><small>Mi primera programación</small></span></NavLink>
        <nav aria-label="Navegación principal">
          <NavLink to="/" end>Inicio</NavLink>
          <NavLink to="/aventura"><Icon name="route"/>Aventura</NavLink>
          <NavLink to="/laboratorio"><Icon name="flask"/>Laboratorio libre</NavLink>
          <NavLink to="/docente"><Icon name="book"/>Vista docente</NavLink>
        </nav>
      </header>
      <main id="contenido" tabIndex={-1} className="page-content">
        <Outlet />
      </main>
      <footer className="site-footer"><span>MDEdu · Mi primera programación</span><span>Proyecto académico · Educación universitaria</span></footer>
    </>
  );
}

export function App() {
  return (
    <Routes>
      <Route element={<AppLayout />}>
        <Route index element={<HomePage />} />
        <Route path="ingresar" element={<StudentLoginPage/>}/>
        <Route path="laboratorio" element={<Suspense fallback={<LoadingState text="Preparando el laboratorio…"/>}><StudentAccess><LaboratoryPage /></StudentAccess></Suspense>} />
        <Route path="aventura" element={<Suspense fallback={<LoadingState/>}><StudentAccess><AdventurePage /></StudentAccess></Suspense>} />
        <Route path="aventura/:levelId" element={<Suspense fallback={<LoadingState text="Preparando tu reto…"/>}><StudentAccess><AdventurePage /></StudentAccess></Suspense>} />
        <Route path="docente/login" element={<TeacherLoginPage />} />
        <Route path="docente" element={<Suspense fallback={<LoadingState text="Preparando la vista docente…"/>}><TeacherAccess><MetaUiPage /></TeacherAccess></Suspense>} />
        <Route path="*" element={<NotFoundPage />} />
      </Route>
    </Routes>
  );
}
