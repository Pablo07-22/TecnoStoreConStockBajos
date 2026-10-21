package actividad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import modelo.ReporteVenta;

public class DatosVenta {

    private List<ReporteVenta> ventas = new ArrayList<>();
    private final String nombreArchivo = "StockBajo.dat";

    @SuppressWarnings("unchecked")
    public void cargar() {
        File f = new File(nombreArchivo);
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
                ventas = (List<ReporteVenta>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar el archivo" + e.getMessage());
            }
        }
    }

    public void reemplazar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(ventas);
        } catch (Exception e) {
            System.out.println("Error al guardar " + e.getMessage());
        }
    }

    public DatosVenta() {
        cargar();
    }

    public void guardarVentas(List<ReporteVenta> nuevasVentas) {
        this.ventas = nuevasVentas;
        reemplazar();
    }

    public List<ReporteVenta> getObtenerLista() {
        cargar();
        return ventas;
    }
}
