# Proyecto Final Generation Colombia

**Proyecto:** Orígenes Colombia  
**Grupo 1**

- Jessica Acevedo
- Angie Gamboa
- Manuel Gómez
- Luis Salgado
- Lukas Muñoz

---

## Descripción de la aplicación

**Orígenes Colombia API** es el backend de un comercio electrónico enfocado en productos artesanales y locales de Colombia. La aplicación expone una API REST construida con **Spring Boot** y **PostgreSQL**, y permite gestionar usuarios, catálogo de productos, pedidos y el detalle de cada pedido desde clientes o herramientas como Postman y Swagger.

El sistema está pensado como capa de servicios para un frontend o para pruebas de integración. Los usuarios pueden **registrarse** (`POST /registro`) e **iniciar sesión** (`POST /login`). Tras autenticarse, reciben un **token JWT** que deben enviar en las peticiones protegidas mediante el encabezado `Authorization: Bearer <token>`. Las contraseñas se almacenan cifradas con **BCrypt**; nunca se devuelven en las respuestas JSON.

### Funcionalidades principales

- **Autenticación:** registro de nuevos clientes, login y validación de token en cada solicitud.
- **Usuarios:** consulta y administración de cuentas (`/usuarios`), con roles como `CLIENTE` y `ADMIN`.
- **Productos:** CRUD del catálogo (`/productos`) con nombre, precio, cantidad, categoría (café, artesanías, moda, etc.) y marca.
- **Pedidos:** creación y consulta de pedidos (`/pedidos`) asociados a un cliente, con estado (pendiente, en proceso, completado), total y dirección de envío.
- **Detalle de pedido:** líneas de cada pedido (`/detalle-pedidos`) que vinculan un producto, cantidad y precio unitario.

### Arquitectura

El proyecto sigue una estructura en capas habitual en Spring:

- **Model:** entidades JPA (`Usuario`, `Producto`, `Pedido`, `DetallePedido`).
- **Repository:** acceso a datos con Spring Data JPA.
- **Service:** lógica de negocio y operaciones CRUD.
- **Controller:** endpoints REST.
- **Security:** JWT (`JwtService`, `JwtAuthFilter`) y configuración de rutas públicas y protegidas.

La documentación interactiva de la API está disponible en **Swagger UI** (`/swagger-ui.html`) cuando la aplicación está en ejecución.

### Requisitos y ejecución

- Java 17
- PostgreSQL con la base de datos `origenesshop` configurada en `application.properties`
- Maven (incluye `mvnw` en el proyecto)

```bash
./mvnw spring-boot:run
```

El servidor inicia por defecto en `http://localhost:8080`.

### Flujo de uso recomendado

1. Registrar un usuario con `POST /registro`.
2. Copiar el `token` de la respuesta (o usar `POST /login` si ya existe la cuenta).
3. Llamar a los endpoints protegidos (por ejemplo `GET /productos`) enviando el token en el header `Authorization`.

Este backend corresponde a la **Tarea 13** del proyecto final: API REST con persistencia en base de datos, operaciones CRUD y seguridad basada en JWT.
