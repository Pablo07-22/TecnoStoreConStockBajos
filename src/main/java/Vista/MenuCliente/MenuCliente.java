package Vista.MenuCliente;

import Vista.Validaciones.Validaciones;
import java.util.Scanner;

public class MenuCliente {

    Validaciones v = new Validaciones();

    public int MenuCliente() {
        System.out.println("""
                       Bienvenido a TecnoStorePablo
                       - ¿QUE DECEAS HACER?
                        1. Ver Celulares
                        2. Comprar Celulares
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
