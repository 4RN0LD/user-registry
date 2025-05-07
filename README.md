# API RESTful para la Creación de Usuarios

Este proyecto es una API RESTful desarrollada con Spring Boot que permite crear usuarios. La API acepta y retorna solamente JSON, incluso para los mensajes de error, siguiendo un formato estándar.

## Características

- Registro de usuarios con los campos: `nombre`, `correo`, `contraseña` y un listado de `teléfonos`.
- Validación de correo y contraseña con expresiones regulares configurables.
- Generación y persistencia de un token de acceso (JWT o UUID).
- Respuesta con los detalles del usuario creado, incluyendo `id`, `created`, `modified`, `last_login`, `token`, e `isactive`.
- Validación para evitar registros duplicados de correo electrónico.
- Almacenamiento en base de datos en memoria (HSQLDB o H2).
- Uso de JPA para persistencia de datos.
- Framework: Spring Boot, Java 8+.

## Requisitos para levantar la API en local

- JDK 17
- Algun gestor de base de datos. Opcional, solo si se quiere ver la BD (ejemplo: https://dbeaver.io/download/)

## Diagrama de solucion

![Diagrama](src/main/resources/Diagrama.svg)

## Instalación

1. Clona este repositorio:

    ```bash
    git clone https://github.com/4RN0LD/user-registry.git
    ```

2. Navega al directorio del proyecto:

    ```bash
    cd user-registry
    ```

3. Si usas Maven:

    ```bash
    ./mvnw clean install
    ```

4. Ejecuta la aplicación:

    ```bash
    ./mvnw spring-boot:run
    ```
## Configuracion

1. Configuracion formato en RegEx para aplicar a la contrasena:
   - para configurar el formato de la expresion regular debe hacerlo desde el archivo application.yaml en el nodo **security.password.regex** actualmente configurado para un minimo de 8 caracteres

## Endpoints

### Registro de Usuario

**POST localhost:8080/user**

Este endpoint permite registrar un nuevo usuario. El cuerpo de la solicitud debe tener el siguiente formato:

```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "hunter2",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
