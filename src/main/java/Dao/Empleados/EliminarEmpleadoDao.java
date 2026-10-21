package Dao.Empleados;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EliminarEmpleadoDao {

    Conexion c = new Conexion();

    public boolean eliminar(String idEmpleado) {
        String sql = "{call EliminarEmpleado(?)}";

        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, idEmpleado);
            int empleadoEncontrado = cs.executeUpdate();
            if (empleadoEncontrado > 0) {
                System.out.println("Empleado Eliminado Corectamente");
            } else {
                System.out.println("NO se encontro ningun Empleado con ese id");
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }
}
