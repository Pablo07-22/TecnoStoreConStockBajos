package Controlador.MenuAdmin;

import Controlador.AlertaStock.Alertas;
import Controlador.AlertaStock.CelularAlerta;
import Controlador.GestionMenuPrincipal;
import Controlador.ReporteVentasRealizadas;
import Vista.MenuPrincipalAdmin.MenuPrincipalAdministrador;

public class MenuPrincipalAdmin {

    public void MenuPrincipalAdmin() {
        MenuPrincipalAdministrador mpa = new MenuPrincipalAdministrador();
        int op;
        do {
            op = mpa.MenuPrincipalAdmins();
            switch (op) {
                case 1:
                    MenusAdminCelulares mac = new MenusAdminCelulares();
                    mac.MenusAdminCelulares();
                    break;
                case 2:
                    MenuAdminEmpleados mae = new MenuAdminEmpleados();
                    mae.MenuAdminEmpleados();
                    break;
                case 3:
                    System.out.println("MODULO REPORTE DE VENTAS");
                    ReporteVentasRealizadas rvr = new ReporteVentasRealizadas();
                    Alertas a = new Alertas();
                    a.mostrar();
                    rvr.mostrarReportesDesdeDat();
                    break;
                case 4:
                    System.out.println("MODULO ALERTA CELULARES");
                    CelularAlerta ca = new CelularAlerta();
                    ca.listarCelularesConAlertas();
                    break;
                case 5:
                    System.out.println("REGRESANDO");
                    GestionMenuPrincipal gmp = new GestionMenuPrincipal();
                    gmp.RespuestaMenuPrincipal();
                    break;
                default:
                    System.out.println("Opcion no encontrada");
            }
        } while (op != 5);
    }
}
