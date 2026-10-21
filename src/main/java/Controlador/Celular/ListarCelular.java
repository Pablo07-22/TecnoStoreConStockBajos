package Controlador.Celular;

import Vista.MostrarCelular;
import Dao.Celular.ListarCelularDao;

public class ListarCelular {

    ListarCelularDao lcd = new ListarCelularDao();
    MostrarCelular mc = new MostrarCelular();

    public void Listar() {
        mc.lista(lcd.listarCelulares());
    }
}
