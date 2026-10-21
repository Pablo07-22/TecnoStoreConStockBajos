package Controlador.Celular;

import Controlador.MenuAdmin.MenusAdminCelulares;
import Vista.Validaciones.Validaciones;
import Dao.Celular.BuscarCelularDao;
import modelo.Celular;

public class ActualizarCelular {

    BuscarCelularDao bcd = new BuscarCelularDao();
    Validaciones v = new Validaciones();
    ListarCelular lc = new ListarCelular();

    public void Actualizar() {
        lc.Listar();
        String id = v.validar_texto("Ingrese el id a actualizar: ").trim();
        Celular entidadActualizar = bcd.buscar(id);
        if (entidadActualizar == null) {
            System.out.println("NO existe ningun celular con dicho id");
        } else {
            entidadActualizar.setId(id);
            System.out.println("Celular encontrado");
            MenusAdminCelulares ma = new MenusAdminCelulares();
            ma.MenuActualizarCelular(entidadActualizar);
        }
    }
}
