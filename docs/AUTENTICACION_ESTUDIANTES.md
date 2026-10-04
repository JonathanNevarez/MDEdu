# Autenticación pseudónima de estudiantes

Evolución post-prototipo desde `c8219382101070373088082355571f7036fcf7c3`, autorizado después de detectar que el checkpoint solicitado `67bebe0` ya tenía el rediseño publicado. Se conserva ese rediseño. No se crea Fase 14 ni se amplían los cuatro retos.

## Identidad y persistencia

`StudentAccount → Student UUID → StudentModel, progreso, intentos, adaptaciones, feedback y telemetría`. La cuenta añade acceso al estudiante existente en el modelo; no sustituye `Student` ni crea otro StudentModel. La creación de un participante nuevo crea Student y cuenta en una única transacción.

V8 añade `student_accounts`: UUID de cuenta, FK única `student_id`, código único, BCrypt, enabled, fechas de creación/último acceso y `credential_version`. La secuencia PostgreSQL genera códigos concurrentes entre EST-001 y EST-999999; puede dejar huecos al abortar una transacción. No se usa MAX+1. V1–V7 permanecen intactas. No se almacenan nombre, correo, cédula ni clave en texto plano en la cuenta.

Los registros históricos sin cuenta permanecen consultables por el docente. No se reclama un Student histórico a partir de localStorage ni se migra automáticamente a una cuenta. El endpoint histórico de creación de Student queda restringido a ROLE_TEACHER; la gestión de participantes usa el endpoint transaccional de cuentas.

## Acceso del participante

1. El docente abre **Vista docente → Participantes → Crear participante**.
2. El sistema asigna el código y genera 18 bytes aleatorios (24 caracteres base64url).
3. La clave aparece una sola vez en el resultado de creación/reset, con `Cache-Control: no-store`. El docente la entrega por el medio acordado, fuera de la aplicación. Ocultar el aviso o abandonar la sección elimina esa presentación; nunca se recupera de la base.
4. El participante entra en `/ingresar` con código y clave. El backend normaliza trim/uppercase y valida `^EST-[0-9]{3,6}$`.
5. Otro navegador, sin cookies ni almacenamiento previo, recupera el mismo UUID y los datos persistidos tras el mismo login.

No hay registro público ni recuperación por correo. El reseteo de clave genera otra clave aleatoria; no permite consultar la anterior. El código identifica una cuenta pseudónima, no una identidad civil.

## Sesión y autorización

Se reutiliza Spring Security, ROLE_TEACHER y la infraestructura de sesión/CSRF. La autenticación estudiante usa ROLE_STUDENT. Login cambia el identificador de sesión y renueva CSRF. No hay JWT ni credenciales en localStorage/sessionStorage. Cookie JSESSIONID: HttpOnly, SameSite Strict, 30 minutos de inactividad y Secure en `prod`; HTTP local usa la configuración de desarrollo existente.

`GET /api/auth/student/me` es la autoridad del frontend. La antigua clave `mdedu.student.id.v1` se conserva solo como caché de compatibilidad de salida; nunca se lee para seleccionar identidad. Modificarla no permite apropiarse de datos. Los guards protegen aventura, retos y laboratorio. La portada y el catálogo descriptivo siguen siendo públicos; no crean estudiantes.

`mdedu.session.v1` sigue siendo la sesión de telemetría, distinta de JSESSIONID. Login/logout limpian su caché en el navegador; las sesiones históricas y sus eventos no se borran. La correlación interna continúa usando UUID.

Cada petición autenticada de estudiante comprueba enabled y credential_version en PostgreSQL. Reset y habilitar/deshabilitar incrementan la versión: las sesiones anteriores son rechazadas e invalidadas al siguiente request, incluso desde otro dispositivo. Logout invalida inmediatamente la sesión actual sin borrar progreso. Las sesiones HTTP viven en la instancia del servidor; reiniciar exige volver a entrar, conservando los datos de aprendizaje.

Las APIs de modelo/progreso/UI, intentos, feedback y telemetría comprueban el UUID de la sesión contra el solicitado. Los servicios siguen comprobando la pertenencia de intentos/sesiones al estudiante. Inspección global, reglas, adaptaciones y timelines siguen protegidos por ROLE_TEACHER. La administración de participantes no hace editables las reglas ni los parámetros pedagógicos.

## Protección de acceso

Todas las escrituras con sesión requieren CSRF, incluido login/logout y participantes. El frontend obtiene el token de sesión antes de escribir y envía cookies con credentials include. No guarda tokens en almacenamiento persistente. Las claves se borran del formulario tras cada envío.

La respuesta de clave incorrecta y código inexistente es idéntica: «Código o clave incorrectos.». Se compara un hash BCrypt ficticio cuando el código no existe. Claves mayores de 72 bytes UTF-8 no se aceptan.

Límite por dirección del peer de conexión: 5 intentos fallidos en 300 segundos por defecto. Se reserva un cupo antes de BCrypt para limitar concurrencia; un login correcto libera su cupo sin borrar fallos anteriores. Configuración Spring: `app.student-auth.max-failures` y `app.student-auth.window-seconds`. Máximo 10000 peers en memoria y expiración de ventanas; no se confía en X-Forwarded-For. Detrás de un proxy, sus usuarios comparten el límite del peer. Es un límite por instancia y se reinicia con el proceso; no pretende coordinar réplicas ni sustituir la protección del despliegue.

HTTPS es obligatorio en producción. No se añaden cuentas reales durante pruebas ni se incluyen claves reales en Git. Los fixtures se provisionan mediante el API docente y sus credenciales efímeras permanecen en memoria de pruebas.

## Límites conservados

Sin cambios en mastery, evaluación, patrones, reglas, AdaptationManager, LLM, Ecore, GenModels, Xtext, ATL o Acceleo. Sin nuevos retos ni rediseño adicional. El docente se configura como antes mediante el entorno; estudiante y docente comparten la cookie de sesión de ese navegador y cambiar de rol sustituye la autenticación activa.

Véase [verificación](VERIFICACION_AUTENTICACION_ESTUDIANTES.md).
