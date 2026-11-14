# StudyHub: plataforma de aprendizaje

Plataforma web colaborativa donde los estudiantes comparten apuntes, plantean dudas y las resuelven entre ellos en foros organizados por asignatura.

> Proyecto final de Servicios y Sistemas Web (SSW) · 3.º curso, 2.º cuatrimestre · Curso 2023-2024 · Grado en Ingeniería Informática, Universidad de Valladolid

## Qué hace

- **Registro y autenticación de usuarios.** Alta con comprobación asíncrona de nickname y correo duplicados, inicio de sesión con formulario y restablecimiento de contraseña por correo electrónico.
- **Foros por asignatura.** Cada asignatura (con su curso) tiene su foro, y en cada foro se abren temas para plantear dudas.
- **Preguntas y respuestas.** Los usuarios comentan en los temas y votan los comentarios con *like*/*dislike*, que se actualiza por AJAX sin recargar la página.
- **Gestión de apuntes.** Los ficheros (PDF, JPEG o PNG, hasta 16 MB) se suben arrastrándolos o seleccionándolos. Luego se pueden consultar, descargar, ver en «Mis ficheros» y eliminar.
- **Búsqueda** de asignaturas y de temas.
- **Perfil de usuario** con la actividad reciente y una «posición en el podio» que clasifica a los usuarios por sus aportaciones.

## Tecnologías

- **Backend:** Java 8, Jakarta EE 8 (Servlets, JSP), JDBC
- **Frontend:** HTML5, CSS3 (diseño adaptable con *media queries*), JavaScript, jQuery, AJAX
- **Base de datos:** MySQL
- **Servidor:** Apache Tomcat (JDBC Connection Pool, `DataSourceRealm`)
- **Otros:** JavaMail (envío de correos por SMTP), Maven (empaquetado WAR), NetBeans

## Lo que aprendí / Aspectos técnicos destacados

- **Arquitectura por capas MVC sin framework.** Los modelos están en `business`, el acceso a datos en `data` (una clase `*DB` por entidad), los servlets controladores en `controlador` y las vistas son JSP.
- **Seguridad gestionada por el contenedor.** `web.xml` define recursos públicos y privados, autenticación `FORM` y cuatro roles: `estudiante`, `profesor`, `moderador` y `administrador`. Tomcat valida las credenciales contra MySQL con un `CombinedRealm` que acepta tanto el nickname como el correo.
- **Permisos por rol en los controladores.** Borrar asignaturas o temas está reservado a moderadores y administradores. Un comentario o un fichero lo pueden borrar ellos o su propietario (`request.isUserInRole(...)`).
- **Pool de conexiones JNDI.** El `DataSource` `jdbc/studyhub` se declara en `META-INF/context.xml` y se obtiene mediante un `ConnectionPool` singleton.
- **Contraseñas con hash SHA-256** y tokens de restablecimiento con caducidad (tabla `reset_tokens`).
- **Ficheros guardados en la base de datos** como `MEDIUMBLOB`, asociados a un foro o a un comentario.

## Arquitectura

```
Navegador (JSP + CSS + jQuery/AJAX)
        │
        ▼
Servlets (studyhub.controlador) ──► Modelos (studyhub.business)
        │
        ▼
Acceso a datos (studyhub.data, JDBC) ──► Pool JNDI de Tomcat ──► MySQL (studyhub)
```

## Estructura del repositorio

```
StudyHub/
├── pom.xml
└── src/main/
    ├── java/studyhub/
    │   ├── business/      # Modelos: User, Asignatura, Tema, Comentario, Fichero...
    │   ├── controlador/   # Servlets (registro, foros, temas, ficheros, votos...)
    │   ├── data/          # Acceso a datos y ConnectionPool
    │   └── mysql.sql      # Esquema y datos de ejemplo
    ├── resources/META-INF/persistence.xml
    └── webapp/
        ├── *.jsp          # Vistas
        ├── scripts/       # JavaScript (validación, drag & drop, votos)
        ├── styles/        # CSS
        ├── images/
        ├── META-INF/context.xml   # DataSource y Realm de Tomcat
        └── WEB-INF/web.xml        # Seguridad, roles y páginas de error
```

## Requisitos

1. **Java JDK 8 o superior** (el `pom.xml` compila con nivel 1.8)
2. **Apache Tomcat 9**: la aplicación usa el espacio de nombres `javax.*` de Jakarta EE 8, así que no funciona en Tomcat 10 o posterior sin migrarla.
3. **MySQL** y su conector JDBC (`com.mysql.jdbc.Driver`) en el `lib/` de Tomcat, porque no viene en el `pom.xml`
4. **Maven**

## Cómo compilar y ejecutar

Estos pasos se han deducido de los archivos de configuración y no se han ejecutado.

1. Crear la base de datos con el esquema y los datos de ejemplo:
   ```bash
   mysql -u root -p < src/main/java/studyhub/mysql.sql
   ```
2. Crear en MySQL el usuario `studyhub` con permisos sobre la base de datos `studyhub`, o cambiar `username`, `password` y `url` en `src/main/webapp/META-INF/context.xml`.
3. Para restablecer contraseñas por correo, poner una cuenta SMTP propia (remitente y clave) en `SendResetMail.java`. Está configurado para `smtp.gmail.com:587`.
4. Empaquetar el WAR:
   ```bash
   mvn package
   ```
5. Desplegar `target/studyhub-1.0.war` en Tomcat 9 (o ejecutarlo desde NetBeans) y abrir la página de inicio (`index.jsp`).

## Autoría

Proyecto en grupo. Commits de Javier García ([JavivuG](https://github.com/JavivuG)), [huglope](https://github.com/huglope), Sergio Herrero ([sergihejo](https://github.com/sergihejo)), dajimen y subse.

---

Parte de [UVa-Projects](https://github.com/JavivuG/UVa-Projects).
