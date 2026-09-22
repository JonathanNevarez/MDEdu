# Entorno local de base de datos

La Fase 0 utiliza `../docker-compose.yml` exclusivamente para PostgreSQL.
Frontend y backend se ejecutarán con sus herramientas locales. Los Dockerfiles
de la aplicación y el empaquetado completo corresponden a Fase 13.

Desde la raíz, crear `.env` a partir de `.env.example`, establecer `DB_PASSWORD`
y ejecutar `docker compose config --quiet`, seguido de
`docker compose up -d --wait postgres`. El puerto se publica solo en loopback.
`docker compose stop` detiene el servicio conservando el volumen.

La etiqueta `postgres:17-bookworm` fija versión mayor y distribución, pero puede
recibir revisiones. Registrar y fijar el digest realmente descargado al validar
el entorno experimental; no se inventa un digest antes de descargar la imagen.
La imagen y el servicio todavía no se ejecutaron en esta máquina.

La contraseña inicial pertenece al volumen creado: cambiar `.env` después no
modifica la contraseña de un volumen existente. No eliminar volúmenes para
resolver discrepancias sin revisar y autorizar antes la pérdida de datos.

Referencia: [imagen oficial PostgreSQL](https://hub.docker.com/_/postgres).
