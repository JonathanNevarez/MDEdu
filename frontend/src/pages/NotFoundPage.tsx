import { Link } from 'react-router-dom';
import { Icon } from '../shared/Visuals';

export function NotFoundPage() {
  return (
    <section className="welcome-panel" aria-labelledby="not-found-title">
      <div className="error-illustration"><Icon name="route"/></div>
      <p className="eyebrow">Página no disponible</p>
      <h1 id="not-found-title">No encontramos esta página</h1>
      <p>El enlace que abriste no corresponde a una página disponible.</p>
      <Link className="return-link" to="/aprender">Volver a Aprender</Link>
    </section>
  );
}
