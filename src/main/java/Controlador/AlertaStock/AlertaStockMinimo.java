package Controlador.AlertaStock;

import modelo.Celular;

public class AlertaStockMinimo implements EstrategyAlertaStock {

    @Override
    public void Notificar(Celular celular) {
        if (celular.getStock() <= celular.getStockMinimo()) {
            System.out.println("ALERTA DE CELULARES: ");
            System.out.println(" Celular " + celular.getModelo() + " ID: " + celular.getId());
            System.out.println(" Tiene un stock crítico: " + celular.getStock());
            System.out.println(" Stock Mínimo Permitido: " + celular.getStockMinimo());
        
        }
    }
}
