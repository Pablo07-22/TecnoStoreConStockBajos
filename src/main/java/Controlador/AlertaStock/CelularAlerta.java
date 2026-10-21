package Controlador.AlertaStock;

import Dao.Celular.ListarCelularDao;
import java.util.List;
import modelo.Celular;

public class CelularAlerta {

    ListarCelularDao lcd = new ListarCelularDao();
    EstrategyAlertaStock eas = new AlertaStockMinimo();

    public void listarCelularesConAlertas() {

        List<Celular> lista = lcd.listarCelulares();
        if (lista.isEmpty()) {
            System.out.println("No hay celulares registrados.");
            return;
        }
        System.out.println("INVENTARIO GENERAL DE CELULARES");
        lista.forEach(c -> System.out.println(
                " ID: " + c.getId()
                + " Modelo: " + c.getModelo()
                + " Precio: $" + c.getPrecio()
                + " Stock Actual: " + c.getStock()
                + " Stock Mínimo: " + c.getStockMinimo()));

        System.out.println("EVALUACIÓN DE ALERTAS DE STOCK MÍNIMO");
        lista.stream().forEach(eas::Notificar);
        long vacia = lista.stream().filter(c -> c.getStock() <= c.getStockMinimo()).count();
        if (vacia == 0) {
            System.out.println("NO tenemos alerta de stock minimo ;) ");
        }
    }
}
