package dao.Empleado;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class ListarEmpleadoDao {

    Conexion c = new Conexion();

    public List<Empleado> ListarEmpleados() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "{call ListarEmpleados()}";

        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                String idReal = rs.getString("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                Empleado empleado = new Empleado(nombre, telefono);
                empleado.setId(idReal);
                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los empleados: " + e.getMessage());
        }

        return listaEmpleados;
    }
}
