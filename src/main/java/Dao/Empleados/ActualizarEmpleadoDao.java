package Dao.Empleados;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.Empleado;

public class ActualizarEmpleadoDao {

    Conexion c = new Conexion();

    public boolean ActualizarEmpleado(Empleado empleado) {
        String sql = "{call ActualizarEmpleado(?,?,?)}";

        if (empleado.getId() == null || empleado.getId().trim().isEmpty()) {
            System.out.println("El objeto Celular no tiene un ID asignado");
            return false;
        }
        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, empleado.getId());
            cs.setString(2, empleado.getNombre());
            cs.setString(3, empleado.getTelefono());
            int filasAfectadas = cs.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Empleado actualizado corectamente");
                return true;
            } else {
                System.out.println("NO se encontro dicho empleado");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar Empleado " + e.getMessage());
            return false;
        }
    }
}
