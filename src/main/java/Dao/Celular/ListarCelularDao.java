package Dao.Celular;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.CategoriaGama;
import modelo.Celular;
import modelo.Marca;
import modelo.SistemaOperativo;

public class ListarCelularDao {

    Conexion c = new Conexion();

    public List<Celular> listarCelulares() {
        List<Celular> listaCelulares = new ArrayList<>();
        String sql = "{call ListarCelulares()}";
        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql); ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                String idReal = rs.getString("id");
                Marca marca = new Marca(rs.getString("nombre_marca"));
                SistemaOperativo so = SistemaOperativo.valueOf(rs.getString("sistema_operativo").toUpperCase());
                CategoriaGama gama = CategoriaGama.valueOf(rs.getString("gama").toUpperCase());
                Celular celular = new Celular(
                        idReal,
                        marca,
                        rs.getString("modelo"),
                        so,
                        gama,
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("stock_minimo")
                );
                listaCelulares.add(celular);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los celulares: " + e.getMessage());
        }
        return listaCelulares;
    }
}
