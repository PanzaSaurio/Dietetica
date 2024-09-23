package view;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.ProductoDAO;
import model.*;

import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Menu {

    private ClienteDAO clienteDAO;
    private FacturaDAO facturaDAO;
    private ProductoDAO productoDAO;
    private Scanner scanner;

    public Menu() {
        clienteDAO = new ClienteDAO();
        facturaDAO = new FacturaDAO();
        productoDAO = new ProductoDAO();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("----- MENÚ PRINCIPAL -----");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Facturas");
            System.out.println("3. Gestión de Productos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarMenuClientes();
                    break;
                case 2:
                    mostrarMenuFacturas();
                    break;
                case 3:
                    mostrarMenuProductos();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuClientes() {
        int opcion;
        do {
            System.out.println("----- GESTIÓN DE CLIENTES -----");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Modificar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    modificarCliente();
                    break;
                case 4:
                    eliminarCliente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuFacturas() {
        int opcion;
        do {
            System.out.println("----- GESTIÓN DE FACTURAS -----");
            System.out.println("1. Crear Factura");
            System.out.println("2. Listar Facturas");
            System.out.println("3. Modificar Factura");
            System.out.println("4. Anular Factura");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearFactura();
                    break;
                case 2:
                    listarFacturas();
                    break;
                case 3:
                    modificarFactura();
                    break;
                case 4:
                    eliminarFactura();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuProductos() {
        int opcion;
        do {
            System.out.println("----- GESTIÓN DE PRODUCTOS -----");
            System.out.println("1. Crear Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    modificarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    private void crearCliente() {
        System.out.println("Ingrese el DNI del cliente: ");
        int dni = scanner.nextInt();
        System.out.println("Ingrese el nombre del cliente: ");
        String nombre = scanner.next();
        System.out.println("Ingrese el email del cliente: ");
        String email = scanner.next();
        System.out.println("Ingrese el teléfono del cliente: ");
        int telefono = scanner.nextInt();

        Cliente cliente = new Cliente(dni, nombre, email, telefono);
        clienteDAO.crear(cliente);
    }

    private void listarClientes() {
        clienteDAO.listar().forEach(System.out::println);
    }

    private void modificarCliente() {
        System.out.println("Ingrese el ID del cliente a modificar: ");
        int id = scanner.nextInt();
        Cliente cliente = clienteDAO.obtenerPorId(id);
        if (cliente != null) {
            System.out.println("Ingrese el nuevo nombre del cliente: ");
            cliente.setNombreCliente(scanner.next());
            System.out.println("Ingrese el nuevo email del cliente: ");
            cliente.setEmail(scanner.next());
            System.out.println("Ingrese el nuevo teléfono del cliente: ");
            cliente.setTelefono(scanner.nextInt());
            clienteDAO.actualizar(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void eliminarCliente() {
        System.out.println("Ingrese el ID del cliente a eliminar: ");
        int id = scanner.nextInt();
        clienteDAO.eliminar(id);
    }

    private void crearFactura() {
        System.out.println("Ingrese el ID del cliente: ");
        int idCliente = scanner.nextInt();
        System.out.println("Ingrese el ID del producto: ");
        int idProducto = scanner.nextInt();
        System.out.println("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();
        System.out.println("Ingrese el total: ");
        double total = scanner.nextDouble();

        int numeroFactura = facturaDAO.generarNumeroFactura();

        Factura factura = new Factura(numeroFactura, idCliente, idProducto, new Date(), cantidad, total);
        facturaDAO.crear(factura);
        System.out.println("Factura creada exitosamente con número: " + numeroFactura);
    }

    private void listarFacturas() {
        List<Factura> facturas = facturaDAO.listar();
        for (Factura factura : facturas) {
            System.out.println(factura);
        }
    }

    private void modificarFactura() {
        System.out.println("Ingrese el número de la factura a modificar: ");
        int numeroFactura = scanner.nextInt();
        Factura factura = facturaDAO.obtenerPorId(numeroFactura);
        if (factura != null) {
            System.out.println("Ingrese la nueva cantidad: ");
            factura.setCantidad(scanner.nextInt());
            System.out.println("Ingrese el nuevo total: ");
            factura.setTotal(scanner.nextDouble());
            facturaDAO.actualizar(factura);
            System.out.println("Factura modificada exitosamente.");
        } else {
            System.out.println("Factura no encontrada.");
        }
    }

    private void eliminarFactura() {
        System.out.println("Ingrese el número de la factura a anular: ");
        int numeroFactura = scanner.nextInt();
        facturaDAO.eliminar(numeroFactura);
        System.out.println("Factura anulada exitosamente.");
    }


    private void crearProducto() {
        System.out.println("Ingrese el nombre del producto: ");
        String nombreProducto = scanner.next();
        System.out.println("Ingrese el stock del producto: ");
        int stock = scanner.nextInt();
        System.out.println("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();

        Producto producto = new Producto(nombreProducto, stock, precio);
        productoDAO.crear(producto);
        System.out.println("Producto creado exitosamente.");
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listar();
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private void modificarProducto() {
        System.out.println("Ingrese el ID del producto a modificar: ");
        int idProducto = scanner.nextInt();
        Producto producto = productoDAO.obtenerPorId(idProducto);
        if (producto != null) {
            System.out.println("Ingrese el nuevo stock: ");
            producto.setStock(scanner.nextInt());
            System.out.println("Ingrese el nuevo precio: ");
            producto.setPrecio(scanner.nextDouble());
            productoDAO.actualizar(producto);
            System.out.println("Producto modificado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void eliminarProducto() {
        System.out.println("Ingrese el ID del producto a eliminar: ");
        int idProducto = scanner.nextInt();
        productoDAO.eliminar(idProducto);
        System.out.println("Producto eliminado exitosamente.");
    }



}
