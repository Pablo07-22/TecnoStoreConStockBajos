package Dao.Celular;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.CategoriaGama;
import modelo.Celular;
import modelo.Marca;
import modelo.SistemaOperativo;

public class BuscarCelularDao {

    private final Conexion c = new Conexion();

    public Celular buscar(String id) {
        String sql = "{call ObtenerCelular(?)}";

        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, id.trim());
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Marca marca = new Marca(rs.getString("nombre_marca"));
                    SistemaOperativo so = SistemaOperativo.valueOf(rs.getString("sistema_operativo").toUpperCase());
                    CategoriaGama gama = CategoriaGama.valueOf(rs.getString("gama").toUpperCase());
                    Celular celular = new Celular(
                            marca,
                            rs.getString("modelo"),
                            so,
                            gama,
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            rs.getInt("stock_minimo")
                    );
                    celular.setId(rs.getString("id"));
                    return celular;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el celular: " + e.getMessage());
        }
        return null;
    }
}
