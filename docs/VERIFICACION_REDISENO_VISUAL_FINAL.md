# Verificación del rediseño visual final

Fecha: 2026-10-03. HEAD inicial: `6697077f2fe5131169ad78a5d0b5768f633ea573`.

## Resultado

Rediseño validado sobre los 18 retos reales. Luma conserva el SVG anterior por indicación final del usuario. No se abre Fase 14. Se añade únicamente una API de laboratorio libre que reutiliza mapper y motor existentes, sin evaluar ni persistir progreso.

| Comprobación | Resultado |
| --- | --- |
| Backend Maven verify | 295 unitarias/MVC + 98 integración, cero fallos/errores/omitidas |
| Frontend unitarias | 80 PASS |
| Typecheck y build Vite | PASS |
| Chromium con LLM DISABLED | 19 casos únicos PASS; una primera ejecución detectó el nombre accesible de Reiniciar, corregido y repetido satisfactoriamente |
| Gemini mock local | 2 PASS, sin API externa |
| MDE clean verify | 49 PASS: Xtext 22, Acceleo 25, ATL 2 |
| Docker smoke final | 6 E2E PASS, base nueva, readiness y recuperación tras detener/reiniciar PostgreSQL PASS |
| Secret scan con bundle y logs | PASS |
| git diff --check | PASS |

Las suites se ejecutaron secuencialmente, con heaps de 384 MiB y un worker de Chromium. Evidencia operativa local en `.quality/final-ui-*.log` (ignorada por Git).

## Integridad y accesibilidad

338 archivos protegidos de MDE y recursos backend conservan sus SHA-256 iniciales, incluidas migraciones V1–V9, reglas, parámetros y prompts. No se modifica el motor ni la serialización Blockly.

En 1440×900 y 1366×768 se verificaron diez estados por tamaño: ingreso, mapa, submapa, COND03/programa, COND03/éxito, LOOP01/pedagogía, progreso, laboratorio, login docente y Meta-IU. Cero infracciones axe serious/critical y cero desbordamiento horizontal. Persiste el aviso moderado `region` de Blockly documentado; no se excluyeron selectores para ocultarlo.

[Capturas y auditorías](evidencia-rediseno-final/) registran la UI real. Se comprobaron drag/drop, ejecución, modelo XMI, recuperación de progreso desde otra sesión y ausencia de escritura de progreso desde laboratorio. El caso de modelo incompleto muestra diagnóstico y permite cerrar el diálogo.

## Incidencias resueltas y límites

Se corrigió el nombre accesible de Reiniciar y el estado de carga del modelo inválido del laboratorio. Los avisos de tamaño de chunk Vite y FORCE_COLOR no impiden las pruebas. La apariencia sigue la composición de referencia con gráficos SVG reales, sin convertir la captura en interfaz. Docker conserva los volúmenes de prueba y elimina solo sus contenedores/red temporal.

Comandos principales: `backend/mvnw.cmd verify`, `backend/mvnw.cmd -f mde/pom.xml clean verify`, `npm test`, `npm run build`, Playwright con un worker, `scripts/docker-smoke.ps1 -Browser`, `scripts/check-secrets.ps1 -IncludeBundle -IncludeLogs`.
