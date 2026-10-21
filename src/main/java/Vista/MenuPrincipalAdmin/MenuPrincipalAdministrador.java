package Vista.MenuPrincipalAdmin;

import Vista.Validaciones.Validaciones;
import java.util.Scanner;

public class MenuPrincipalAdministrador {

    Validaciones v = new Validaciones();

    public int MenuPrincipalAdmins() {
        System.out.println("""
                           Bienvenido ¿A que menu deseas ir?
                             1. Menu Celulares 
                             2. Menu Empleados
                             3. Reporte Ventas Realizadas
                             4. Informacion de celulares
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
}
