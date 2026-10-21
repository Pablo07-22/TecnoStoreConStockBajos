package dao.Empleado;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Empleado;

public class BuscarEmpleadoDao {

    Conexion c = new Conexion();

    public Empleado buscar(String id) {
        String sql = "{call ObtenerEmpleado(?)}";

        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, id.trim());
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    String idReal = rs.getString("id");
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    Empleado empleado = new Empleado(nombre, telefono);
                    empleado.setId(idReal);
                    return empleado;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el empleado: " + e.getMessage());
        }
        return null;
    }
}
