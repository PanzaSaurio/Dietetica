package DAO;

import controller.Conexion;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAO<Cliente>{
    //private  List<Cliente> clientes = new ArrayList<>();

    @Override
    public void crear(Cliente cliente) {
        String sql = "INSERT INTO cliente (dni,nombreCliente,email,telefono) VALUES (?,?,?,?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1,String.valueOf(cliente.getDni()));
            pstmt.setString(2,String.valueOf(cliente.getNombreCliente()));
            pstmt.setString(3,String.valueOf(cliente.getEmail()));
            pstmt.setString(4,String.valueOf(cliente.getTelefono()));
            pstmt.execute();
            System.out.println("Cliente registrado.....");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> listar() {
        List <Cliente> clientes = new ArrayList <>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setNombreCliente(rs.getString("nombreCliente"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getInt("telefono"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET dni = ?, nombreCliente = ?, email = ?, telefono = ?, updated_at = ? WHERE idCliente = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cliente.getDni());
            pstmt.setString(2, cliente.getNombreCliente());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setInt(4, cliente.getTelefono());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // updated_at
            pstmt.setInt(6, cliente.getIdCliente());

            pstmt.execute();
            System.out.println("Cliente actualizado...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE cliente SET enabled = false, deleted_at = ? WHERE idCliente = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(2, id);

            pstmt.execute();
            System.out.println("Cliente eliminado (lógica)...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente obtenerPorId(int idCliente) {
        String sql = "SELECT * FROM cliente WHERE idCliente = ? AND enabled = true";
        Cliente cliente = null;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("idCliente"),
                            rs.getInt("dni"),
                            rs.getString("nombreCliente"),
                            rs.getString("email"),
                            rs.getInt("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

}