package modelo;

import java.io.Serializable;

public class ReporteVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idVenta;
    private String fecha;
    private String nombreCliente;
    private String cedulaCliente;
    private String nombreEmpleado;
    private String modeloCelular;
    private int cantidad;
    private double precioUnitario;
    private double total;

    public ReporteVenta(String idVenta, String fecha, String nombreCliente, String cedulaCliente, String nombreEmpleado, String modeloCelular, int cantidad, double precioUnitario, double total) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.cedulaCliente = cedulaCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.modeloCelular = modeloCelular;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getModeloCelular() {
        return modeloCelular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return """
               ===== Reporte Ventas ====
               VENTA:              %s
               FECHA:              %s
               CLIENTE:            %s
               CEDULACLIENTE:      %s
               EMPLEADO:           %s
               CELULAR:            %s
               CANTIDAD:           %s
               TOTAL:              %s
               """.formatted(idVenta, fecha, nombreCliente, cedulaCliente, nombreEmpleado, modeloCelular, cantidad, total);
    }

}
