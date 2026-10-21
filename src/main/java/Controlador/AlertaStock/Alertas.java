package Controlador.AlertaStock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import modelo.Celular;

public class Alertas {
    private List<Celular> celular = new ArrayList<>();
    private final String nombreStock = "StockBajo.dat";
    
    @SuppressWarnings("unchecked")
    public void cargar() {
        File f = new File(nombreStock);
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreStock))) {
                celular = (List<Celular>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar el archivo" + e.getMessage());
            }
        }
    }
    
    public void reemplazar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreStock))) {
            oos.writeObject(celular);
        } catch (Exception e) {
            System.out.println("Error al guardar " + e.getMessage());
        }
    }
    
    public Alertas(){
        cargar();
    }
    
    
    public void guardarVentas(List<Celular> Stock){
        this.celular = Stock;
        reemplazar();
    }
    
    public List<Celular> Obtener() {
        cargar();
        return celular;
    }
    
    public void CelularStockBajo (){
        celular.stream().filter(c-> c.getStock() <c.getStockMinimo()).forEach(System.out::println);
    }
    
    
    public void mostrar(){
      Alertas a = new Alertas();
      List<Celular> c = a.Obtener();
        
       if (c.isEmpty()){
         System.out.println("Celulares Con STOCK MUY BAJO");
         CelularStockBajo();
       }
        
       System.out.println("Reporte Stock Bajo");
        
       System.out.println("Celulares Con STOCK MUY BAJO");
       CelularStockBajo();
    }
    
    
}
