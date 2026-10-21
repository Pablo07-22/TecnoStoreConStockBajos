package Controlador;

import actividad.DatosVenta;
import dao.Venta.ListarVentas;
import java.util.List;
import modelo.Celular;
import modelo.ReporteVenta;

public class ReporteVentasRealizadas {

    ListarVentas lv = new ListarVentas();
    DatosVenta dv = new DatosVenta();

    public void sincronizarGuardarEnDat() {
        List<ReporteVenta> rv = lv.obtenerTodasLasVentasBD();

        if (rv.isEmpty()) {
            System.out.println("No hay ventas registradas");
            return;
        }

        System.out.println("VENTAS ENCONTRADAS");
        rv.stream().forEach(System.out::println);

        dv.guardarVentas(rv);
        System.out.println("Ventas guardadas exitosamente");
    }

    public void mostrarReportesDesdeDat() {
        List<ReporteVenta> listaDat = dv.getObtenerLista();

        if (listaDat.isEmpty()) {
            System.out.println("Esta vacio");
            return;
        }

        System.out.println("REPORTE");

        System.out.println("1.Todas las Ventas");
        listaDat.stream().forEach(System.out::println);

        System.out.println("2.Ventas Mayores a $1,000,000 ");
        listaDat.stream()
                .filter(v -> v.getTotal() > 1000000)
                .forEach(System.out::println);

        System.out.println("3.Clientes que han comprado (Sin repetir)");
        listaDat.stream()
                .map(ReporteVenta::getNombreCliente)
                .distinct()
                .forEach(nombre -> System.out.println("Cliente: " + nombre));

        double totalGlobal = listaDat.stream()
                .mapToDouble(ReporteVenta::getTotal)
                .sum();
        System.out.println("4.Recaudo Total de Ventas: " + totalGlobal);
    }
    
}
