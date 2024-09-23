package controller;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.ProductoDAO;
import model.Cliente;
import model.Factura;
import model.Producto;

import java.util.Date;

public class Inicio {

    private ClienteDAO clienteDAO;
    private FacturaDAO facturaDAO;
    private ProductoDAO productoDAO;

    public Inicio() {
        clienteDAO = new ClienteDAO();
        facturaDAO = new FacturaDAO();
        productoDAO = new ProductoDAO();
    }

    public void cargaMasiva() {
        // Carga de clientes
        Cliente cliente1 = new Cliente(12345678, "Juan Perez", "juan@mail.com", 123456);
        Cliente cliente2 = new Cliente(87654321, "Maria Gomez", "maria@mail.com", 654321);
        clienteDAO.crear(cliente1);
        clienteDAO.crear(cliente2);

        // Carga de productos
        Producto producto1 = new Producto("Laptop", 10, 750.99);
        Producto producto2 = new Producto("Mouse", 50, 15.99);
        productoDAO.crear(producto1);
        productoDAO.crear(producto2);

        // Carga de facturas
        Factura factura1 = new Factura(1, 2, 1, new Date(), 2, 1501.98);
        Factura factura2 = new Factura(2, 3, 2, new Date(), 1, 15.99);
        facturaDAO.crear(factura1);
        facturaDAO.crear(factura2);
    }

    public void ejecutarPruebas() {
        // Llamada a los métodos para probar CRUD
        System.out.println("Probando CRUD de Clientes:");
        clienteDAO.listar().forEach(System.out::println);
        clienteDAO.eliminar(1);
        clienteDAO.listar().forEach(System.out::println);

        System.out.println("Probando CRUD de Productos:");
        productoDAO.listar().forEach(System.out::println);
        productoDAO.eliminar(1);
        productoDAO.listar().forEach(System.out::println);

        System.out.println("Probando CRUD de Facturas:");
        facturaDAO.listar().forEach(System.out::println);
        facturaDAO.eliminar(1);
        facturaDAO.listar().forEach(System.out::println);
    }
}
