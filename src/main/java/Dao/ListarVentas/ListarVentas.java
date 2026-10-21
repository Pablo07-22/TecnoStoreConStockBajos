package dao.Venta;

import dao.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Celular;
import modelo.ReporteVenta;

public class ListarVentas {

    private final Conexion c = new Conexion();

    public List<ReporteVenta> obtenerTodasLasVentasBD() {
     
        List<ReporteVenta> lista = new ArrayList<>();
        String sql = "{call ObtenerReporteVentas()}";

        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                ReporteVenta dto = new ReporteVenta(
                        rs.getString("id_venta"),
                        rs.getString("fecha"),
                        rs.getString("nombre_cliente"),
                        rs.getString("cedula_cliente"),
                        rs.getString("nombre_empleado"),
                        rs.getString("modelo_celular"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("total")
                );
                lista.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reporte" + e.getMessage());
        }
        return lista;
    }
    
    public List<Celular> obtenerTodas() {
     
        List<Celular> lista = new ArrayList<>();
        String sql = "{call ObtenerReporteVentas()}";

        try (Connection con = c.conexion(); CallableStatement cs = con.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                ReporteVenta dto = new ReporteVenta(
                        rs.getString("id_venta"),
                        rs.getString("fecha"),
                        rs.getString("nombre_cliente"),
                        rs.getString("cedula_cliente"),
                        rs.getString("nombre_empleado"),
                        rs.getString("modelo_celular"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("total")
                );
//                lista.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reporte" + e.getMessage());
        }
        return lista;
    }
}
