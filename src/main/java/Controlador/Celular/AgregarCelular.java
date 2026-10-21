package Controlador.Celular;

import Vista.IngresoCelular.IngresarCelular;
import Dao.Celular.AgregarCelularDao;

public class AgregarCelular {

    AgregarCelularDao acd = new AgregarCelularDao();
    IngresarCelular ic = new IngresarCelular();

    public void agregar() {
        acd.crear(ic.IngresarCelular());
    }
}
