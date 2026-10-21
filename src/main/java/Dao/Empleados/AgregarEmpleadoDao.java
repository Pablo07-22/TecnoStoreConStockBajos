package Dao.Empleados;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import modelo.Empleado;

public class AgregarEmpleadoDao {

    Conexion c = new Conexion();

    public boolean crear(Empleado empleado) {
        String sql = "{call CrearEmpleado(?,?,?)}";

        if (empleado.getId() == null || empleado.getId().trim().isEmpty()) {
            empleado.setId("ID-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        }
        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, empleado.getId());
            cs.setString(2, empleado.getNombre());
            cs.setString(3, empleado.getTelefono());

            cs.executeUpdate();
            System.out.println("Empleado Registrado Corectamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar un empleado");
            return false;
        }
    }
}
