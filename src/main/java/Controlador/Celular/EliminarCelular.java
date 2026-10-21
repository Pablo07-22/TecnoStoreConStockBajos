package Controlador.Celular;

import Vista.Validaciones.Validaciones;
import Dao.Celular.EliminarCelularDao;

public class EliminarCelular {

    EliminarCelularDao acd = new EliminarCelularDao();
    Validaciones v = new Validaciones();
    ListarCelular lc = new ListarCelular();

    public void Eliminar() {
        lc.Listar();
        String idCelular = v.validar_texto("Ingrese el id a eliminar");
        acd.eliminar(idCelular);
    }
}
