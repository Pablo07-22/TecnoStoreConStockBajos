package Vista;

import java.util.List;
import modelo.Empleado;

public class MostrarEmpleado {

    public void lista(List<Empleado> empleado) {
        for (Empleado e : empleado) {
            System.out.println(e);
        }
    }
}
