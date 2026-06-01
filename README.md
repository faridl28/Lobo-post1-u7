# Gestión de Productos — Spring Boot + Thymeleaf

Aplicación web CRUD desarrollada con **Spring Boot 3.2** y **Thymeleaf** como motor de plantillas.  
Permite crear, listar, editar y eliminar productos usando una lista en memoria como capa de persistencia temporal.  
Implementa el patrón **MVC** y el patrón **Post/Redirect/Get (PRG)** para el manejo de formularios.

---

## Estructura del proyecto

```
productos-web/
├── src/main/java/com/universidad/productosweb/
│   ├── ProductosWebApplication.java   # Punto de entrada de Spring Boot
│   ├── model/
│   │   └── Producto.java              # Entidad con id, nombre, descripcion, precio
│   ├── service/
│   │   └── ProductoService.java       # CRUD en memoria (HashMap singleton)
│   └── controller/
│       └── ProductoController.java    # Rutas MVC bajo /productos
├── src/main/resources/
│   ├── templates/productos/
│   │   ├── lista.html                 # Vista: tabla con todos los productos
│   │   └── formulario.html            # Vista: formulario crear / editar
│   └── application.properties
└── pom.xml
```

---

## Requisitos

| Herramienta | Versión mínima |
|-------------|---------------|
| Java JDK    | 17            |
| Maven       | 3.8+          |

---

## Cómo ejecutar

```bash
# 1. Clonar el repositorio
git clone https://github.com/TU_USUARIO/Lobo-post1-u7.git
cd Lobo-post1-u7

# 2. Ejecutar con Maven
mvn spring-boot:run

# 3. Abrir en el navegador
# http://localhost:8080/productos
```

---

## Rutas disponibles

| Método | Ruta                        | Descripción                        |
|--------|-----------------------------|------------------------------------|
| GET    | `/productos`                | Lista todos los productos          |
| GET    | `/productos/nuevo`          | Formulario de creación             |
| POST   | `/productos/guardar`        | Guarda (crea o edita) un producto  |
| GET    | `/productos/editar/{id}`    | Formulario prellenado para editar  |
| GET    | `/productos/eliminar/{id}`  | Elimina un producto                |

---

## Capturas de pantalla

### Lista de productos
> Al acceder a `http://localhost:8080/productos` se muestran los 3 productos de ejemplo.

### Formulario Crear / Editar
> El mismo formulario (`formulario.html`) se reutiliza para crear y editar, cambiando el atributo `accion`.

---

## Componentes clave

- **`ProductoService`** — actúa como repositorio simulado; `@Service` garantiza una única instancia (singleton).
- **`ProductoController`** — recibe peticiones HTTP, llama al servicio y devuelve el nombre de la vista o una redirección.
- **Patrón PRG** — después de un `POST /guardar`, el servidor responde con `redirect:/productos`, evitando que F5 reenvíe el formulario.
- **Thymeleaf `th:field`** — enlaza los campos del formulario con las propiedades del objeto `Producto` del modelo.
