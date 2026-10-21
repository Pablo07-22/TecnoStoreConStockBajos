package Controlador.Empleado;

import Vista.MostrarEmpleado;
import dao.Empleado.ListarEmpleadoDao;

public class ListarEmpleado {

    ListarEmpleadoDao led = new ListarEmpleadoDao();
    MostrarEmpleado me = new MostrarEmpleado();

    public void Listar() {
        me.lista(led.ListarEmpleados());
    }
}
