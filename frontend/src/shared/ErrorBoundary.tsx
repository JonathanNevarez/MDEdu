import { Component } from 'react';
import type { ReactNode } from 'react';
export class ErrorBoundary extends Component<{children: ReactNode}, {failed: boolean}> {
 state = {failed: false};
 static getDerivedStateFromError() {return {failed: true};}
 render() {
  return this.state.failed ? <main className="page-content"><h1>No se pudo mostrar esta pantalla</h1><p role="alert">Ocurrió un problema inesperado. Puedes volver al inicio o recargar la página.</p><a href="/">Volver al inicio</a> <button onClick={() => window.location.reload()}>Recargar</button></main> : this.props.children;
 }
}
