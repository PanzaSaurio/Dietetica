package DAO;

import model.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.Conexion;

public class FacturaDAO implements DAO<Factura> {

    @Override
    public void crear(Factura factura) {
        String sql = "INSERT INTO factura (numeroFactura, idCliente, idProducto, fecha, cantidad, total, created_at, enabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, factura.getNumeroFactura());
            pstmt.setInt(2, factura.getIdCliente());
            pstmt.setInt(3, factura.getIdProducto());
            pstmt.setDate(4, new java.sql.Date(factura.getFecha().getTime()));
            pstmt.setInt(5, factura.getCantidad());
            pstmt.setDouble(6, factura.getTotal());
            pstmt.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis())); // created_at
            pstmt.setBoolean(8, true);

            pstmt.execute();
            System.out.println("Factura registrada con número: " + factura.getNumeroFactura());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Factura> listar() {
        String sql = "SELECT * FROM factura WHERE enabled = true";
        List<Factura> facturas = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("numeroFactura"),
                        rs.getInt("idCliente"),
                        rs.getInt("idProducto"),
                        rs.getDate("fecha"),
                        rs.getInt("cantidad"),
                        rs.getDouble("total"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getTimestamp("deleted_at"),
                        rs.getBoolean("enabled")
                );
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public void actualizar(Factura factura) {
        String sql = "UPDATE factura SET idCliente = ?, idProducto = ?, fecha = ?, cantidad = ?, total = ?, updated_at = ? WHERE numeroFactura = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, factura.getIdCliente());
            pstmt.setInt(2, factura.getIdProducto());
            pstmt.setDate(3, new Date(factura.getFecha().getTime()));
            pstmt.setInt(4, factura.getCantidad());
            pstmt.setDouble(5, factura.getTotal());
            pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // updated_at
            pstmt.setInt(7, factura.getNumeroFactura());

            pstmt.execute();
            System.out.println("Factura actualizada...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE factura SET enabled = false, deleted_at = ? WHERE numeroFactura = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(2, id);

            pstmt.execute();
            System.out.println("Factura anulada...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Factura obtenerPorId(int id) {
        String sql = "SELECT * FROM factura WHERE numeroFactura = ? AND enabled = true";
        Factura factura = null;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    factura = new Factura(
                            rs.getInt("numeroFactura"),
                            rs.getInt("idCliente"),
                            rs.getInt("idProducto"),
                            rs.getDate("fecha"),
                            rs.getInt("cantidad"),
                            rs.getDouble("total"),
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
        return factura;
    }

    public int generarNumeroFactura() {
        Random random = new Random();
        int numeroFactura;
        boolean existe;

        do {
            numeroFactura = random.nextInt(999999);
            existe = verificarNumeroFactura(numeroFactura);
        } while (existe);

        return numeroFactura;
    }


    private boolean verificarNumeroFactura(int numeroFactura) {
        String sql = "SELECT COUNT(*) FROM factura WHERE numeroFactura = ?";
        boolean existe = false;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numeroFactura);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }

}
