import { Link } from 'react-router-dom';

export function NotFoundPage() {
  return (
    <section className="welcome-panel" aria-labelledby="not-found-title">
      <p className="eyebrow">Página no disponible</p>
      <h1 id="not-found-title">No encontramos esta página</h1>
      <p>El enlace que abriste no corresponde a una página disponible.</p>
      <Link className="return-link" to="/">Volver al inicio</Link>
    </section>
  );
}
