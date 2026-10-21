package Controlador.Empleado;

import Dao.Empleados.EliminarEmpleadoDao;
import Vista.Validaciones.Validaciones;

public class EliminarEmpleado {

    EliminarEmpleadoDao eed = new EliminarEmpleadoDao();
    Validaciones v = new Validaciones();
    ListarEmpleado le = new ListarEmpleado();

    public void Eliminar() {
        le.Listar();
        String idEmpleado = v.validar_texto("Ingrese el id a eliminar");
        eed.eliminar(idEmpleado);
    }
}
