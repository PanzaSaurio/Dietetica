# Proyecto Dietética Dietin

## Descripción

Este proyecto es una aplicación de gestión para una dietética llamada "Dietética Dietin". La aplicación permite gestionar productos, clientes y facturas a través de una interfaz de línea de comandos (CLI). Utiliza una base de datos MySQL para almacenar los datos, y está implementado en Java siguiendo un patrón de acceso a datos (DAO) y utilizando clases modelo para cada entidad.

El proyecto se organiza de manera modular utilizando clases DAO genéricas para interactuar con la base de datos y una clase controladora (`App`) que maneja el flujo principal del programa.

![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)

## Estructura del Proyecto

El proyecto está organizado en paquetes con diferentes responsabilidades, como se detalla a continuación:

### Paquetes y Clases

- **`model`**:
  - `Cliente.java`: Representa la entidad cliente, con atributos como `idCliente`, `dni`, `nombreCliente`, `email`, y `telefono`.
  - `Producto.java`: Representa la entidad producto, con atributos como `idProducto`, `nombreProducto`, `stock`, y `precio`.
  - `Factura.java`: Representa la entidad factura, con atributos como `numeroFactura`, `idCliente`, `idProducto`, `fecha`, `cantidad`, y `total`.

- **`DAO`**:
  - `DAO.java`: Es una interfaz genérica con métodos para crear y listar entidades. Todas las clases DAO implementan esta interfaz.
  - `ClienteDAO.java`: Implementa los métodos `crear()` y `listar()` para interactuar con la tabla de clientes en la base de datos.
  - `ProductoDAO.java`: Implementa los métodos `crear()` y `listar()` para interactuar con la tabla de productos en la base de datos.
  - `FacturaDAO.java`: Implementa los métodos `crear()` y `listar()` para interactuar con la tabla de facturas. Además, genera un número de factura aleatorio y único.

- **`controller`**:
  - `Conexion.java`: Gestiona la conexión a la base de datos MySQL.
  - `Inicio.java`: Proporciona funciones para realizar una carga masiva de datos iniciales y ejecutar pruebas de CRUD en las tablas de productos, clientes y facturas.
  - `App.java`: Es el punto de entrada de la aplicación, donde se ejecuta el menú principal y se invocan las pruebas y la carga masiva.

- **`view`**:
  - `Menu.java`: Define el menú principal de la aplicación, permitiendo al usuario crear, listar, modificar y eliminar productos, clientes y facturas.

## Configuración de la Conexión a la Base de Datos

Antes de ejecutar la aplicación, es necesario configurar las credenciales de acceso a la base de datos MySQL en la clase `Conexion.java`.

### Pasos para Configurar la Conexión

1. Abre el archivo `Conexion.java` en el paquete `controller`.
2. Localiza las siguientes líneas de código:

   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/DieteticaDietinBD";
   private static final String USUARIO = "tu_usuario";
   private static final String PASSWORD = "tu_password";
   ```
3. Reemplaza "tu_usuario" y "tu_password" con tu usuario y contraseña correspondientes a tu instalación de MySQL.
    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/DieteticaDietinBD";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "password123";
    ```
Una vez configurado, puedes proceder a compilar y ejecutar la aplicación como se detalla en la sección de [Ejecución del Proyecto](#ejecución-del-proyecto).

## Base de Datos

La base de datos se llama `DieteticaDietinBD`. El archivo SQL `DieteticaDietinBD.sql` contiene las instrucciones necesarias para crear la base de datos y sus tablas. Las tablas principales son:

- **producto**: Almacena los productos con `idProducto`, `nombreProducto`, `stock`, y `precio`.
- **cliente**: Almacena los clientes con `idCliente`, `dni`, `nombreCliente`, `email`, y `telefono`.
- **factura**: Almacena las facturas con `numeroFactura`, `idCliente`, `idProducto`, `fecha`, `cantidad`, y `total`.

### Estructura de las Tablas

#### Tabla `producto`
```sql
CREATE TABLE producto (
  idProducto INT PRIMARY KEY AUTO_INCREMENT,
  nombreProducto VARCHAR(100),
  stock INT,
  precio DOUBLE
);

#### Tabla `cliente`
```sql
CREATE TABLE cliente (
  idCliente INT PRIMARY KEY AUTO_INCREMENT,
  dni INT,
  nombreCliente VARCHAR(100),
  email VARCHAR(100),
  telefono INT
);
```

#### Tabla `factura`
```sql
CREATE TABLE factura (
  numeroFactura INT PRIMARY KEY,
  idCliente INT,
  idProducto INT,
  fecha DATE,
  cantidad INT,
  total DOUBLE,
  FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
  FOREIGN KEY (idProducto) REFERENCES producto(idProducto)
);
```

## Ejecución del Proyecto

### Requisitos Previos

- **Java 8** o superior.
- **Apache Maven** para gestionar las dependencias.
- **MySQL** para la base de datos.

### Pasos para Ejecutar

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/usuario/proyecto-dietetica.git
   cd proyecto-dietetica
   ```
2. **Configurar la base de datos**:
    Importa el archivo DieteticaDietinBD.sql en MySQL para crear la base de datos y las tablas necesarias.
   ```bash
   mysql -u usuario -p < DieteticaDietinBD.sql
   ```
3. **Configurar la conexión a la base de datos**:
    Asegúrate de que los parámetros de conexión en Conexion.java sean correctos (usuario, contraseña, URL de la base de datos).
    
4. **Compilar el proyecto con Maven**:
   ```bash
    mvn clean install
    ```
5. **Ejecutar la aplicación**:
   ```bash
    java -cp target/dietetica-dietin.jar controller.App
    ```
## Uso de la Aplicación

Al ejecutar la aplicación, se mostrará un menú principal con las siguientes opciones:

- **Gestión de Clientes**:
  - Crear, listar, modificar y eliminar clientes.

- **Gestión de Facturas**:
  - Crear, listar, modificar y anular facturas.

- **Gestión de Productos**:
  - Crear, listar, modificar y eliminar productos.

El menú permite al usuario interactuar con el sistema de manera sencilla utilizando la línea de comandos.

## Funcionalidades

- **CRUD de Clientes, Productos y Facturas**: Permite gestionar las tres entidades principales.
- **Eliminación lógica**: La anulación de facturas y eliminación de clientes y productos se realiza de forma lógica, sin eliminar físicamente los datos.
- **Generación de número de factura único**: Se genera un número de factura aleatorio y único para cada factura creada.
- **Auditoría**: Se registran los campos de auditoría como `created_at`, `updated_at`, y `deleted_at`.
