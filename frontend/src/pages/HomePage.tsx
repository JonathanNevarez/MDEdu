import { Link } from 'react-router-dom';
import { Icon, Landscape, LumaPortrait } from '../shared/Visuals';
export function HomePage() {
  return (
    <section aria-labelledby="welcome-title">
      <div className="home-hero"><div className="home-copy">
        <p className="eyebrow">Mi primera programación</p>
        <h1 id="welcome-title">Las grandes ideas empiezan <span>con un bloque.</span></h1>
        <p>Aprende lógica de programación a tu ritmo. Cuatro retos, un camino por descubrir y Luma para acompañarte.</p>
        <div className="home-actions"><Link className="return-link" to="/aventura">Comenzar mi aventura <Icon name="arrow"/></Link><Link to="/laboratorio">Explorar libremente</Link></div>
      </div><div className="home-world"><Landscape/><div className="world-caption"><strong>Hola, soy Luma.</strong>Vamos a dar forma a tus ideas, paso a paso.</div><LumaPortrait/><span className="floating-block one"><Icon name="sequence"/>Conecta tus ideas</span><span className="floating-block two"><Icon name="flag"/>Encuentra tu camino</span></div></div>
      <div className="home-features"><article><Icon name="route"/><div><h2>Un camino, cuatro conceptos</h2><p>Secuencias, variables, condicionales y ciclos.</p></div></article><article><Icon name="variable"/><div><h2>Aprende haciendo</h2><p>Conecta bloques y observa cómo cobra vida tu programa.</p></div></article><article><Icon name="spark"/><div><h2>Cada intento cuenta</h2><p>Recibe orientación y descubre qué puedes mejorar.</p></div></article></div>
    </section>
  );
}
