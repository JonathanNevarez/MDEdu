import { NavLink, Outlet, Route, Routes } from 'react-router-dom';
import { HomePage } from '../pages/HomePage';
import { NotFoundPage } from '../pages/NotFoundPage';

function AppLayout() {
  return (
    <>
      <a className="skip-link" href="#contenido">Saltar al contenido</a>
      <header className="site-header">
        <span className="site-name">Lógica de programación</span>
        <nav aria-label="Navegación principal">
          <NavLink to="/" end>Inicio</NavLink>
        </nav>
      </header>
      <main id="contenido" tabIndex={-1} className="page-content">
        <Outlet />
      </main>
      <footer className="site-footer">Proyecto académico · Educación universitaria</footer>
    </>
  );
}

export function App() {
  return (
    <Routes>
      <Route element={<AppLayout />}>
        <Route index element={<HomePage />} />
        <Route path="*" element={<NotFoundPage />} />
      </Route>
    </Routes>
  );
}
