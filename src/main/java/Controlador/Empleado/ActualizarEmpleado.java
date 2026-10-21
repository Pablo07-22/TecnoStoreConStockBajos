package Controlador.Empleado;

import Controlador.MenuAdmin.MenuAdminEmpleados;
import Vista.Validaciones.Validaciones;
import dao.Empleado.BuscarEmpleadoDao;
import modelo.Empleado;

public class ActualizarEmpleado {

    BuscarEmpleadoDao bed = new BuscarEmpleadoDao();
    Validaciones v = new Validaciones();
    ListarEmpleado le = new ListarEmpleado();

    public void Actualizar() {
        le.Listar();
        String id = v.validar_texto("Ingrese el id a actualizar: ");
        Empleado actualizar = bed.buscar(id);
        if (actualizar == null) {
            System.out.println("No existe ningun empleado con ese id");
        } else {
            actualizar.setId(id);
            System.out.println("Empleado Encontrado");
            MenuAdminEmpleados mae = new MenuAdminEmpleados();
            mae.MenuAdminActualizarEmpleado(actualizar);
        }
    }
}
