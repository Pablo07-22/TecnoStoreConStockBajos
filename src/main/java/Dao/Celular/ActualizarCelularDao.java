package Dao.Celular;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.Celular;

public class ActualizarCelularDao {

    Conexion c = new Conexion();

    public boolean ActualizarCelularDao(Celular celular) {
        String sql = "{call ActualizarCelular(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        if (celular.getId() == null || celular.getId().trim().isEmpty()) {
            System.out.println("El objeto Celular no tiene un ID asignado");
            return false;
        }
        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, celular.getId());
            cs.setString(2, celular.getMarca().getId());
            cs.setString(3, celular.getMarca().getMarca());
            cs.setString(4, celular.getModelo());
            cs.setString(5, celular.getSistemaop().name());
            cs.setString(6, celular.getGama().name());
            cs.setDouble(7, celular.getPrecio());
            cs.setInt(8, celular.getStock());
            cs.setInt(9, celular.getStockMinimo());
            int filasAfectadas = cs.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Celular actualizado correctamente");
                return true;
            } else {
                System.out.println("No se encontró el celular con el ID especificado");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el celular: " + e.getMessage());
            return false;
        }
    }
}
