package Vista.Validaciones;

import java.util.Scanner;

public class Validaciones {

    public boolean Validar_menus(String op) {
        for (int i = 0; i < op.length(); i++) {
            if (!Character.isDigit(op.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String validar_solo_letras(String mensaje) {
        String texto;
        boolean valido;
        do {
            texto = validar_texto(mensaje);
            valido = true;
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);
                // Permite letras y espacios
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    System.out.println("Error, Solo se permiten letras.");
                    valido = false;
                    break;
                }
            }
        } while (!valido);
        return texto;
    }

    public String validar_texto(String mensaje) {
        boolean validacion;
        String texto = "";
        Scanner leer = new Scanner(System.in);
        do {
            System.out.println(mensaje);
            texto = leer.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Error, El campo no puede estar vacio");
                validacion = false;
                break;
            } else {
                validacion = true;
            }
        } while (!validacion);
        return texto;
    }

    public double validar_decimal(String mensaje) {
        System.out.println(mensaje);
        Scanner leer = new Scanner(System.in);
        while (!leer.hasNextDouble()) {
            System.out.println("Error, se espera un decimal");
            leer.next();
        }
        return leer.nextDouble();
    }

    public int validar_entero(String mensaje) {
        System.out.println(mensaje);
        Scanner leer = new Scanner(System.in);
        while (!leer.hasNextInt()) {
            System.out.println("Error, se espera un entero");
            leer.next();
        }
        return leer.nextInt();
    }
}
