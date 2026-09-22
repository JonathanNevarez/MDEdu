# Backend — Fase 0

Base Java 21, Maven y Spring Boot 3.5.16 con Web, Validation, Data JPA, PostgreSQL,
Flyway, Jackson y Actuator. Solo implementa el arranque y la configuración técnica.
El único endpoint disponible es `GET /actuator/health`. Comprueba también la base
de datos y devuelve `{"status":"UP"}` cuando sus componentes están disponibles,
sin revelar detalles internos. PostgreSQL debe estar disponible para arrancar.

## Requisitos y estado de verificación

Se necesita JDK21. Usar el Wrapper incluido, que fija Maven3.9.16 y su hash;
no se requiere Maven global para las tareas normales. Docker solo es necesario
para pruebas de integración y PostgreSQL. La primera ejecución del Wrapper
necesita acceso a Maven Central; las integraciones también al registro de imágenes.

**Verificado:** Maven/Wrapper3.9.16 con Java21.0.12.1, POM efectivo, validate,
compilación de 42 fuentes y 3 pruebas MVC sin fallos/errores/omitidas. **Pendiente:**
Docker/PostgreSQL, 4 pruebas BackendBootstrapIT, arranque completo y health real.
No se cambió el POM ni se simuló una base de datos para obtener estos resultados.

## Arranque local

Desde la raíz del repositorio, preparar `.env` como explica el README principal y
levantar la base de datos con `docker compose up -d --wait postgres`. Después:

```powershell
Set-Location backend
.\mvnw.cmd spring-boot:run
```

Desde otra terminal:

```powershell
Invoke-RestMethod http://localhost:8080/actuator/health
```

El directorio de trabajo debe ser `backend/`, también al ejecutar el JAR. Spring
importa `../.env` como archivo de propiedades mediante
`optional:file:../.env[.properties]`. Utilizar valores `CLAVE=valor`, sin `export`,
sin comillas ni interpolaciones de shell; para compartirlo con Compose se recomienda
una contraseña local aleatoria alfanumérica. Las variables del proceso tienen
prioridad sobre el archivo.

| Variable | Valor predeterminado | Uso |
| --- | --- | --- |
| `DB_HOST` | `localhost` | Host PostgreSQL |
| `DB_PORT` | `5432` | Puerto PostgreSQL publicado localmente |
| `DB_NAME` | `adaptativa` | Base de datos |
| `DB_USER` | `adaptativa` | Usuario de base de datos |
| `DB_PASSWORD` | Sin valor predeterminado | Contraseña obligatoria; no dejar vacía |
| `BACKEND_ADDRESS` | `127.0.0.1` | Dirección de escucha local |
| `BACKEND_PORT` | `8080` | Puerto HTTP |
| `CORS_ALLOWED_ORIGINS` | `http://localhost:5173,http://127.0.0.1:5173` | Orígenes separados por comas |

La configuración permite cambiar la dirección de escucha si se añade un contenedor
para el backend en una fase posterior. CORS se configura tanto para `/api/**` como
para Actuator. Todavía no hay endpoints educativos ni autenticación. Los errores
HTTP omiten mensajes internos, excepciones y stack traces.

## Compilación y pruebas

Ejecutar desde `backend/`:

```powershell
.\mvnw.cmd clean compile
.\mvnw.cmd test
# Solo cuando Docker esté disponible:
.\mvnw.cmd verify
```

`mvnw.cmd test` ejecuta las pruebas MVC de CORS con JUnit 5 sin requerir PostgreSQL ni
Docker. Comprueban los dos orígenes permitidos y el rechazo de un origen ajeno.
Mockito está disponible a través de `spring-boot-starter-test`; esta fase no necesita
simular servicios educativos.

`mvnw.cmd verify` compila, ejecuta las pruebas anteriores, empaqueta el JAR y ejecuta las
pruebas `*IT` mediante Failsafe. Testcontainers inicia una base PostgreSQL aislada,
sin usar las credenciales locales ni importar `.env`. Comprueba HTTP health, el
registro del indicador de salud de PostgreSQL, la migración inicial y que no existan
tablas educativas. También verifica CORS en health, que `/actuator/env` no esté
expuesto y que los errores no revelen mensajes internos ni stack traces. Estas
pruebas fallan si Docker no está disponible: no se omiten silenciosamente.

Compose y Testcontainers usan `postgres:17-bookworm`. La etiqueta fija la versión
mayor y la distribución, pero no es inmutable. Registrar y fijar el digest tras el
primer pull validado antes de realizar experimentos reproducibles.

Tras una verificación satisfactoria puede arrancarse el artefacto desde `backend/`:

```powershell
java -jar target/adaptativa-backend-0.1.0-SNAPSHOT.jar
```

Flyway es el único responsable de las migraciones; JPA usa `ddl-auto=validate` y no
crea tablas. `V1__bootstrap.sql` ejecuta `SELECT 1` y establece el historial de
migraciones. No modificar una migración ya aplicada: añadir una nueva versión.

## Límites arquitectónicos

Los módulos `student`, `activity`, `programming`, `execution`, `evaluation`,
`adaptation`, `telemetry`, `llm` y `persistence` reservan paquetes `domain`,
`application`, `infrastructure` y `api`. `shared` contiene únicamente configuración
transversal. Las carpetas reservadas no son funcionalidades implementadas.

Los metamodelos EMF/Ecore, ATL, Acceleo y Xtext se incorporarán en las fases MDE.
Antes de declarar sus dependencias hay que verificar artefactos, compatibilidad
con Java 21 y ejecución headless. El código generado se mantendrá separado del
dominio manual. Esta base no reemplaza MDE mediante DTO, JSON Schema ni interfaces.
No incorpora llamadas a LLM ni reglas de adaptación.

## Fuentes técnicas

Consultadas el 20/09/2026:

- [Publicación oficial de Spring Boot 3.5.16](https://spring.io/blog/2026/06/25/spring-boot-3-5-16-available-now/): confirma disponibilidad en Maven Central y fin del soporte OSS de la rama 3.5. Revisar la versión antes de un despliegue; esta base no certifica compatibilidad de Spring Boot 4 con la futura cadena MDE.
- [Requisitos de Spring Boot 3.5](https://docs.spring.io/spring-boot/3.5/system-requirements.html): Java 21 está dentro del rango soportado; Maven mínimo 3.6.3.
- [Configuración externa e importaciones](https://docs.spring.io/spring-boot/3.5/reference/features/external-config.html): propiedades, precedencia de variables de entorno e indicaciones de extensión.
- [Actuator](https://docs.spring.io/spring-boot/3.5/reference/actuator/endpoints.html): health, exposición y configuración CORS propia.
- [Testcontainers con Spring Boot](https://docs.spring.io/spring-boot/3.5/reference/testing/testcontainers.html): configuración de conexiones para pruebas con contenedores.
