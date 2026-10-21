package Vista;

import java.util.List;
import modelo.Celular;

public class MostrarCelular {

    public void lista(List<Celular> celulares) {
        for (Celular c : celulares) {
            System.out.println(c);
        }
    }
}
