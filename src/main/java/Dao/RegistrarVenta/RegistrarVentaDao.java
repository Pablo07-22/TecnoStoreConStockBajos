package Dao.RegistrarVenta;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class RegistrarVentaDao {

    Conexion c = new Conexion();

    // Registra la venta y su detalle
    public boolean procesarVenta(String idCliente, String idEmpleado, String idCelular, int cantidad, double precioUnitario, double total) {
        String sql = "{call RealizarVenta(?, ?, ?, ?, ?, ?, ?)}";
        String idVenta = "VEN-" + UUID.randomUUID().toString().substring(0, 9).toUpperCase();

        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, idVenta);
            cs.setString(2, idCliente);
            cs.setString(3, idEmpleado);
            cs.setString(4, idCelular);
            cs.setInt(5, cantidad);
            cs.setDouble(6, precioUnitario);
            cs.setDouble(7, total);

            cs.executeUpdate();
            System.out.println("COMPRA REALIZADA CON ÉXITO");
            System.out.println("Código de Factura: " + idVenta);
            return true;

        } catch (SQLException e) {
            System.out.println("Error al procesar la venta: " + e.getMessage());
            return false;
        }
    }
}
