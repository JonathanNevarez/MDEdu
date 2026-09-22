# Frontend · Fase 0

Configuración inicial de React, TypeScript, Vite y React Router. Incluye una
pantalla de preparación en español y una ruta de recuperación para enlaces
inexistentes. La pantalla inicial funciona de manera independiente del backend.

## Estado de verificación

**Build, pruebas unitarias y HTTP verificados el 21/09/2026.** Node.js24.19.0 x64
y npm11.17.0 instalados. `npm.cmd install` generó `package-lock.json` (114 paquetes
instalados, 0 vulnerabilidades reportadas). TypeScript y Vite8.3.0 compilan;
Vitest5.0.1: 1 archivo, 2 pruebas correctas, 0 fallidas y 0 omitidas.
Vite respondió HTTP200 con HTML y elemento raíz en 127.0.0.1:5173 y se detuvo
después de la comprobación. Playwright/E2E sigue pendiente de autorización
para descargar Chromium y auxiliares (~327 MB); no se probó navegador real.

## Requisitos e instalación

Instalar [Node.js 24 LTS](https://nodejs.org/en/download), versión 24.15.0 o
superior dentro de la rama 24, con npm. El mínimo contempla `jsdom 30.1.0`.
Reabrir PowerShell después de instalar Node.js y comprobar `node --version` y
`npm.cmd --version`.

Desde esta carpeta:

```powershell
npm.cmd ci
npm.cmd run build
npm.cmd test
npm.cmd run dev
```

El primer `npm.cmd install` ya generó y verificó `package-lock.json`; conservarlo
y versionarlo. Usar `npm.cmd ci` en instalaciones posteriores. Las versiones
declaradas no se modificaron. En PowerShell se usa `npm.cmd` porque la política
existente bloquea `npm.ps1`; no es necesario cambiar esa política.

Solo tras autorización para descargar los navegadores, ejecutar:

```powershell
npm.cmd run test:e2e:install
npm.cmd run test:e2e
```

La interfaz de desarrollo se sirve en `http://127.0.0.1:5173`. El puerto debe estar
libre; se utiliza `strictPort` para evitar cambios silenciosos. `npm run preview`
sirve el resultado de una compilación previa en `http://127.0.0.1:4173`.

## Comprobaciones preparadas

- `npm run typecheck`: tipos de la aplicación, pruebas y configuración.
- `npm test`: inicio informativo y recuperación de una ruta inexistente con la
  aplicación real dentro de `MemoryRouter`.
- `npm run test:e2e`: compila y abre la aplicación en Chromium con Playwright;
  verifica carga, ruta inexistente, retorno al inicio y errores de JavaScript.
  Levanta su propio servidor preview y no requiere backend o PostgreSQL.

El resultado de cada comando debe registrarse en
[`../docs/ESTADO_PROYECTO.md`](../docs/ESTADO_PROYECTO.md). Ningún criterio de
arranque se declara superado por la sola existencia de estas pruebas.

## Organización y alcance

```text
src/
  app/App.tsx          # Rutas y contenedor común
  pages/              # Inicio y página no encontrada
  main.tsx            # Montaje de React y BrowserRouter
  styles.css          # Estilos y foco visible
tests/
  unit/               # Vitest + React Testing Library
  e2e/                # Playwright sobre build/preview
```

Context + reducer es la elección prevista para estado compartido cuando una fase
lo necesite. Esta preparación no tiene estado pedagógico que almacenar.
Las nuevas pantallas se agregarán en las fases correspondientes, sin asumir
que la navegación pedagógica sea una secuencia fija. Blockly se incorporará en
Fase 2. El editor y sus DTO no sustituirán al modelo canónico EMF del backend.
La decisión de adaptación y la integración LLM pertenecen al backend; aquí no se
definen claves ni variables de entorno de proveedores.

En un despliegue estático futuro, el servidor deberá devolver `index.html` para
rutas de navegación de la SPA. El servidor preview se usa únicamente para
comprobaciones locales.

## Versiones y fuentes

Metadatos oficiales de npm consultados el 20 de septiembre de 2026, incluidos
`engines`, `peerDependencies` y los pares opcionales del plugin React. La selección
mantiene TypeScript 5.9.3 y los tipos de Node 24; todas las versiones siguientes
son valores exactos en `package.json`.

| Paquete | Versión | Fuente oficial |
| --- | --- | --- |
| React | 19.3.0 | [npm registry](https://registry.npmjs.org/react/19.3.0) |
| React DOM | 19.3.0 | [npm registry](https://registry.npmjs.org/react-dom/19.3.0) |
| React Router DOM | 7.18.4 | [npm registry](https://registry.npmjs.org/react-router-dom/7.18.4) |
| Vite | 8.3.0 | [npm registry](https://registry.npmjs.org/vite/8.3.0) |
| Plugin React de Vite | 6.1.1 | [npm registry](https://registry.npmjs.org/@vitejs/plugin-react/6.1.1) |
| TypeScript | 5.9.3 | [npm registry](https://registry.npmjs.org/typescript/5.9.3) |
| Vitest | 5.0.1 | [npm registry](https://registry.npmjs.org/vitest/5.0.1) |
| Testing Library React | 16.3.3 | [npm registry](https://registry.npmjs.org/@testing-library/react/16.3.3) |
| Testing Library DOM | 10.4.2 | [npm registry](https://registry.npmjs.org/@testing-library/dom/10.4.2) |
| jest-dom | 7.0.1 | [npm registry](https://registry.npmjs.org/@testing-library/jest-dom/7.0.1) |
| user-event | 14.6.7 | [npm registry](https://registry.npmjs.org/@testing-library/user-event/14.6.7) |
| Tipos React | 19.3.0 | [npm registry](https://registry.npmjs.org/@types/react/19.3.0) |
| Tipos React DOM | 19.3.0 | [npm registry](https://registry.npmjs.org/@types/react-dom/19.3.0) |
| Tipos Node | 24.0.0 | [npm registry](https://registry.npmjs.org/@types/node/24.0.0) |
| jsdom | 30.1.0 | [npm registry](https://registry.npmjs.org/jsdom/30.1.0) |
| Playwright Test | 1.63.0 | [npm registry](https://registry.npmjs.org/@playwright/test/1.63.0) |

Configuración contrastada con las guías de [Vite](https://vite.dev/guide/),
[Vitest](https://vitest.dev/guide/) y
[servidor de pruebas de Playwright](https://playwright.dev/docs/test-webserver).
