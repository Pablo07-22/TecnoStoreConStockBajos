package modelo;

import java.util.UUID;
import java.util.function.Supplier;

public class Marca {

    private String id;
    private final String marca;

    public Marca(String marca) {
        this.id = generarID.get();
        this.marca = marca;
    }

    private static final Supplier<String> generarID = ()
            -> "ID-" + UUID.randomUUID().toString().substring(0, 3).toUpperCase();

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return this.marca;
    }
}
