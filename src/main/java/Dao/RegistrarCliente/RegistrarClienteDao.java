package Dao.RegistrarCliente;

import dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;

public class RegistrarClienteDao {

    Conexion c = new Conexion();

    public String registrarCliente(Cliente cliente) {
        String idCliente = null;
        String sqlSelect = "SELECT id FROM clientes WHERE cedula = ? LIMIT 1";
        String sqlInsert = "INSERT INTO clientes (id, nombre, cedula, correo, telefono) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = c.conexion()) {
            try (PreparedStatement psSelect = con.prepareStatement(sqlSelect)) {
                psSelect.setString(1, cliente.getCedula());
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("id");
                    }
                }
            }

            try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
                psInsert.setString(1, cliente.getId());
                psInsert.setString(2, cliente.getNombre());
                psInsert.setString(3, cliente.getCedula());
                psInsert.setString(4, cliente.getCorreo());
                psInsert.setString(5, cliente.getTelefono());

                if (psInsert.executeUpdate() > 0) {
                    idCliente = cliente.getId();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
        }
        return idCliente;
    }
    
    public Cliente buscarPorCedula(String cedula) {
        String sql = "SELECT id, nombre, cedula, correo, telefono FROM clientes WHERE cedula = ? LIMIT 1";
        try (Connection con = c.conexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // ⭐ Usamos el constructor que recibe el ID real de la base de datos
                    return new Cliente(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por cédula: " + e.getMessage());
        }
        return null;
    }
}