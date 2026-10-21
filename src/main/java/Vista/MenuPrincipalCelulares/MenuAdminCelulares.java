package Vista.MenuPrincipalCelulares;

import Vista.Validaciones.Validaciones;
import java.util.Scanner;

public class MenuAdminCelulares {

    Validaciones v = new Validaciones();

    public int MenuAdminCelulares() {
        System.out.println("""
                           Bienvenido ¿Que deseas hacer?
                           1. Añadir Celular
                           2. Eliminar Celular
                           3. Listar Celulares
                           4. Actualizar Celular
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

    public int MenuActualizarCelular() {
        System.out.println("""
                           Bienvenido ¿Que deseas Actualizar?
                           1. Actualizar Marca
                           2. Actualizar Modelo 
                           3. Actualizar Sistema Operativo
                           4. Actualizar Gama
                           5. Actualizar Precio
                           6. Actualizar Stock
                           7. Actualizar Stock Minimo
                           8. Regresar
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
