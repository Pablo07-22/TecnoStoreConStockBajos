package Dao.Celular;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.Marca;

public class AgregarMarcaDao {

    Conexion c = new Conexion();

    public void crear(Marca marca) {
        String sql = "INSERT INTO marca (id, nombre_marca) VALUES (?, ?)";
        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, marca.getId());
            cs.setString(2, marca.getMarca());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar marca: " + e.getMessage());
        }
    }
}
