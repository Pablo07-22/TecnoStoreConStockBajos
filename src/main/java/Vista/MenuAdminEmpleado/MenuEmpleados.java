package Vista.MenuAdminEmpleado;

import Vista.Validaciones.Validaciones;
import java.util.Scanner;

public class MenuEmpleados {

    Validaciones v = new Validaciones();

    public int MenuAdminEmpleados() {
        System.out.println("""
                           Bienvenido ¿Que deseas hacer?
                           1. Añadir Empleados
                           2. Eliminar Empleados
                           3. Listar Empleados
                           4. Actualizar Empleados
                           5. Regresar
                           """);
        Scanner leer = new Scanner(System.in);
        String op = leer.nextLine();
        while (!v.Validar_menus(op)) {
            System.out.println("Opcion incorrecta intenta de nuevo");
            op = leer.nextLine();
        }
        return Integer.parseInt(op);
    }

    public int MenuActualizarEmpleados() {
        System.out.println("""
                          Bienvenido ¿Que decea actualizar?
                          1. Actualizar Nombre
                          2. Actualizar Telefono
                          3. Regresar
                          """);
        Scanner leer = new Scanner(System.in);
        String op = leer.nextLine();
        while (!v.Validar_menus(op)) {
            System.out.println("Opcion incorrecta intenta de nuevo");
            op = leer.nextLine();
        }
        return Integer.parseInt(op);
    }
}
