package Controlador.Empleado;

import Dao.Empleados.AgregarEmpleadoDao;
import Vista.IngresarEmpleado.IngresarEmpleado;

public class AgregarEmpleado {

    AgregarEmpleadoDao aed = new AgregarEmpleadoDao();
    IngresarEmpleado ie = new IngresarEmpleado();

    public void Agregar() {
        aed.crear(ie.IngresarEmpleado());
    }
}
