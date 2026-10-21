package Vista.IngresoCelular;

import Vista.Validaciones.Validaciones;
import modelo.CategoriaGama;
import modelo.Celular;
import modelo.Marca;
import modelo.SistemaOperativo;

public class IngresarCelular {

    Validaciones v = new Validaciones();

    public Celular IngresarCelular() {
        String nombreMarca = v.validar_solo_letras("Ingrese la marca del celular ");
        Marca marca = new Marca(nombreMarca);
        return new Celular(marca,
                v.validar_texto("Ingrese el modelo"),
                SistemaOperativo.SeleccionarSistemaOperativo(),
                CategoriaGama.SeleccionarGama(),
                v.validar_decimal("Ingrese el precio"),
                v.validar_entero("Ingrese el Stock"),
                v.validar_entero("Ingrese el StockMinimo"));
    }
}
