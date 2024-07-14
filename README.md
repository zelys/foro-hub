# Challenge Back End - ForoHub

## Funcionalidades de la API

La API se centra específicamente en los tópicos, permitiendo realizar las siguientes solicitudes:

- **Crear un nuevo tópico**
- **Mostrar todos los tópicos creados**
- **Mostrar un tópico específico**
- **Actualizar un tópico**
- **Eliminar un tópico**

## Configuración del Entorno de Desarrollo

Para correr el **ForoHub** en tu maquina, asegúrate de tener los siguientes programas, archivos y versiones:

- **Java JDK**: versión 17 en
  adelante - [Descarga la última versión LTS de Java gratuita](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven**: versión 4 en adelante
- **Spring Boot**: versión 3 en adelante - [Spring Initializr](https://start.spring.io/)
- **MySQL**: versión 8 en adelante - [Instalador para Windows](https://dev.mysql.com/downloads/installer/)
- **Insomnia o Postman** - para realizar las solicitudes
- **IDE**: IntelliJ IDEA (opcional)

## Dependecias

- Spring Boot DevTools
- Spring Data JPA
- MySQL Driver
- Spring Web
- Spring Security
- Validation
- Flyway Migration
- Lombok
- Generacion de tokens con: JWT https://jwt.io

## Configuración Inicial

- Crear la base de datos `forohub_db` y configurar las variables de entorno (ver archivo application-properties)

## Configuración de usuario ADMIN en la base de datos

- Crear PROFILE

```sql
/* CREAR ROL DE USUARIO */
INSERT INTO user_profiles (name)
VALUES ('ADMIN');
```

- Crear USER, encriptar password con [Bcrypt Password Generator](https://www.browserling.com/tools/bcrypt) (ej. 123456).

```sql
/* CREAR USUARIO ADMIN */
INSERT INTO users (name, email, password, profile_id)
VALUES ('user.admin', 'user.admin@foro.hub', '$2a$10$U8JvdE7qPbAIGlM7WoY2y.fn.Pwom6jC9hM6f9g9zN1oj/r9p.8J.', 1);
```

- Realizar la solicitud del token

```json
{
  "name": "user.admin",
  "password": "123456"
}
```

![Captura solicitud de token en Insomnia](https://i.imgur.com/8bXTZx0.png?1)

- Copiar el token y agregarlo a `Auth` como Bearer Token para las demas solicitudes

![Captura agregar token a solicitud](https://i.imgur.com/GtPWNPv.png?2)

- Creando un primer tópico

```json
{
    "title": "Tópico Número uno",
    "message": "Este es el mensaje del tema número uno",
    "author": {
        "id": 1
    }
}
```

![Captura agregando un tópico](https://i.imgur.com/4rmZOhg.png?1)

## CONTACTO

Consultas o sugerencias, contactame:

LinkedIn: [Elias Celis](https://www.linkedin.com/in/ecelis/)
Correo electrónico: zelys.dev@gmail.com
