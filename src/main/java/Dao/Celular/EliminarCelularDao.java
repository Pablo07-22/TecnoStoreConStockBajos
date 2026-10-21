package Dao.Celular;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EliminarCelularDao {

    Conexion c = new Conexion();

    public boolean eliminar(String idCelular) {
        String sql = "{call EliminarCelular(?)}";

        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, idCelular);
            int celularencontrado = cs.executeUpdate();
            if (celularencontrado > 0) {
                System.out.println("Celular Eliminado Corectamente");
            } else {
                System.out.println("No se encontro ningun celular con ese id");
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el celular: " + e.getMessage());
            return false;
        }
    }
}
