import { Component } from 'react';
import type { ReactNode } from 'react';
export class ErrorBoundary extends Component<{children: ReactNode}, {failed: boolean}> {
 state = {failed: false};
 static getDerivedStateFromError() {return {failed: true};}
 render() {
  return this.state.failed ? <main className="page-content"><section className="welcome-panel"><h1>No se pudo mostrar esta pantalla</h1><p role="alert">Ocurrió un problema inesperado. Puedes volver a Aprender o recargar la página.</p><a className="return-link" href="/aprender">Volver a Aprender</a> <button onClick={() => window.location.reload()}>Recargar</button></section></main> : this.props.children;
 }
}
