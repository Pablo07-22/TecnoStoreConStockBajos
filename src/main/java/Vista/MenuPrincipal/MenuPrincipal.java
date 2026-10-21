package Vista.MenuPrincipal;

import Vista.Validaciones.Validaciones;
import java.util.Scanner;

public class MenuPrincipal {

    Validaciones v = new Validaciones();

    public int MenuPrincipal() {
        System.out.println("""
                           Bienvenido A TecnologiaStore
                             1. Administrador 
                             2. Cliente
                             3. Salir
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
