package modelo;

import java.util.UUID;
import java.util.function.Supplier;

public class Empleado {

    private String id;
    private String nombre;
    private String telefono;

    public Empleado(String nombre, String telefono) {
        this.id = generarID.get();
        this.nombre = nombre;
        this.telefono = telefono;
    }

    private static final Supplier<String> generarID = ()
            -> "ID-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return """
            ID:        %S
            NOMBRE:    %S
            TELEFONO:  %S
            """.formatted(id, nombre, telefono);
    }

}
