package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import controller.Conexion;
import model.Producto;

public class ProductoDAO implements DAO <Producto> {

    @Override
    public void crear(Producto producto) {
        String sql = "INSERT INTO producto (nombreProducto, stock, precio, created_at, enabled) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombreProducto());
            pstmt.setInt(2, producto.getStock());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setBoolean(5, true);

            pstmt.execute();
            System.out.println("Producto registrado...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listar() {
        String sql = "SELECT * FROM producto WHERE enabled = true";
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombreProducto"),
                        rs.getInt("stock"),
                        rs.getDouble("precio")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE producto SET nombreProducto = ?, stock = ?, precio = ?, updated_at = ? WHERE idProducto = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombreProducto());
            pstmt.setInt(2, producto.getStock());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // updated_at
            pstmt.setInt(5, producto.getIdProducto());

            pstmt.execute();
            System.out.println("Producto actualizado...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE producto SET enabled = false, deleted_at = ? WHERE idProducto = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(2, id);

            pstmt.execute();
            System.out.println("Producto eliminado (lógica)...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtenerPorId(int id) {
        String sql = "SELECT * FROM producto WHERE idProducto = ? AND enabled = true";
        Producto producto = null;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(
                            rs.getInt("idProducto"),
                            rs.getString("nombreProducto"),
                            rs.getInt("stock"),
                            rs.getDouble("precio"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            rs.getTimestamp("deleted_at"),
                            rs.getBoolean("enabled")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }





}