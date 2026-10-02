import { Link } from 'react-router-dom';
export function HomePage() {
  return (
    <section className="welcome-panel" aria-labelledby="welcome-title">
      <p className="eyebrow">Aprendizaje de programación</p>
      <h1 id="welcome-title">Un espacio para aprender lógica de programación</h1>
      <p className="intro">Tu primera aventura empieza con una instrucción.</p>
      <p>
        Explora cuatro retos de secuencias, variables, condicionales y ciclos.
        Conecta bloques, observa tu recorrido y abre el siguiente camino.
      </p>
      <Link className="return-link" to="/aventura">Comenzar mi aventura</Link>
    </section>
  );
}
