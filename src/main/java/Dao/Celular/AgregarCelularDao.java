package Dao.Celular;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import modelo.Celular;

public class AgregarCelularDao {

    Conexion c = new Conexion();

    public void crear(Celular celular) {
        String sql = "{call CrearCelular(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        if (celular.getId() == null || celular.getId().trim().isEmpty()) {
            celular.setId("ID-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase());
        }
        if (celular.getMarca() == null) {
            System.out.println("La marca del celular es nula");
            return;
        }
        if (celular.getMarca().getId() == null || celular.getMarca().getId().trim().isEmpty()) {
            celular.getMarca().setId("MAR-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase());
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

            cs.executeUpdate();
            System.out.println("Celular y Marca registrados exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar el celular: " + e.getMessage());
        }
    }
}
