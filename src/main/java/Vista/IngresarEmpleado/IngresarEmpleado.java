package Vista.IngresarEmpleado;

import Vista.Validaciones.Validaciones;
import modelo.Empleado;

public class IngresarEmpleado {

    Validaciones v = new Validaciones();

    public Empleado IngresarEmpleado() {
        return new Empleado(v.validar_solo_letras("Ingrese el nombre del empleado"),
                v.validar_texto("Ingrese el telefono del empleado"));
    }
}
