package Vista.RealizarCompra;

import Controlador.Celular.ListarCelular;
import Controlador.ReporteVentasRealizadas;
import Dao.Celular.BuscarCelularDao;
import Dao.RegistrarCliente.RegistrarClienteDao;
import Dao.RegistrarVenta.RegistrarVentaDao;
import Vista.Validaciones.Validaciones;
import dao.Empleado.BuscarEmpleadoDao;
import dao.Empleado.ListarEmpleadoDao;
import java.util.ArrayList;
import java.util.List;
import modelo.Celular;
import modelo.Cliente;
import modelo.Empleado;

public class RealizarCompra {

    Validaciones v = new Validaciones();
    ListarCelular lc = new ListarCelular();
    BuscarCelularDao bcd = new BuscarCelularDao();
    ListarEmpleadoDao led = new ListarEmpleadoDao();
    BuscarEmpleadoDao bed = new BuscarEmpleadoDao();
    RegistrarClienteDao rcd = new RegistrarClienteDao();
    RegistrarVentaDao rvd = new RegistrarVentaDao();
    ReporteVentasRealizadas rvr = new ReporteVentasRealizadas();

    private static class ItemCarrito {
        Celular celular;
        int cantidad;

        public ItemCarrito(Celular celular, int cantidad) {
            this.celular = celular;
            this.cantidad = cantidad;
        }
    }

    public void RealizarCompras() {
        String cedula = v.validar_texto("Ingrese su cedula").trim();
        
        Cliente clienteExistente = rcd.buscarPorCedula(cedula);
        String idCliente = null;

        if (clienteExistente != null) {
            idCliente = clienteExistente.getId();
            System.out.println("Cliente ya registrado,Bienvenido de nuevo, " + clienteExistente.getNombre());
        } else {
            System.out.println("El cliente no se encuentra registrado. Ingrese sus datos por única vez:");
            String nombre = v.validar_solo_letras("Ingrese su nombre");
            String correo = v.validar_texto("Ingrese correo").trim();
            String tele = v.validar_texto("Ingrese su numero de telefono").trim();

            Cliente nuevoCliente = new Cliente(nombre, cedula, correo, tele);
            idCliente = rcd.registrarCliente(nuevoCliente);

            if (idCliente == null) {
                System.out.println("No se pudo procesar la información del cliente");
                return;
            }
            System.out.println("Cliente registrado con exito");
        }

        List<ItemCarrito> carrito = new ArrayList<>();
        boolean agregarMas = true;

        while (agregarMas) {
            System.out.println("Celulares Disponibles");
            lc.Listar();

            Celular celularSeleccionado = null;
            String idCelular;

            do {
                idCelular = v.validar_texto("Ingrese el ID del celular que deseas comprar").trim();
                celularSeleccionado = bcd.buscar(idCelular);
                if (celularSeleccionado == null) {
                    System.out.println("ID no encontrado, intenta de nuevo");
                }
            } while (celularSeleccionado == null);

            int cantidadEnCarrito = 0;
            for (ItemCarrito item : carrito) {
                if (item.celular.getId().equals(celularSeleccionado.getId())) {
                    cantidadEnCarrito += item.cantidad;
                }
            }

            int stockDisponibleReal = celularSeleccionado.getStock() - cantidadEnCarrito;

            if (stockDisponibleReal <= 0) {
                System.out.println("Ya has añadido todo el stock disponible de este celular al carrito.");
            } else {
                int cantidad;
                do {
                    cantidad = v.validar_entero("Ingrese la cantidad a llevar");
                    if (cantidad <= 0) {
                        System.out.println("Ingrese una cantidad mayor a cero");
                    } else if (cantidad > stockDisponibleReal) {
                        System.out.println("Stock no disponible. El disponible restante es: " + stockDisponibleReal);
                    }
                } while (cantidad <= 0 || cantidad > stockDisponibleReal);

                carrito.add(new ItemCarrito(celularSeleccionado, cantidad));
                System.out.println("Producto agregado al carrito.");
            }

            String respuesta = v.validar_texto("¿Desea comprar otro celular? (s/n)").trim();
            if (respuesta.equalsIgnoreCase("n")) {
                agregarMas = false;
            }
        }

        if (carrito.isEmpty()) {
            System.out.println("No se agregaron productos a la compra");
            return;
        }

        System.out.println("Empleados Disponibles");
        led.ListarEmpleados().forEach(e
                -> System.out.println("ID: " + e.getId() + " NOMBRE: " + e.getNombre()));

        Empleado empleadoSeleccionado = null;
        String idEmpleado;
        do {
            idEmpleado = v.validar_texto("Ingrese el id del empleado que le atendio").trim();
            empleadoSeleccionado = bed.buscar(idEmpleado);
            if (empleadoSeleccionado == null) {
                System.out.println("Empleado no encontrado, intente de nuevo");
            }
        } while (empleadoSeleccionado == null);

        double totalGeneral = 0;
        System.out.println("         RESUMEN FINAL DE LA COMPRA       ");
        
        for (ItemCarrito item : carrito) {
            double totalItem = item.celular.getPrecio() * item.cantidad;
            totalGeneral += totalItem;
            System.out.println("- Celular ID: " + item.celular.getId() 
                    + " | Modelo: " + item.celular.getModelo() 
                    + " | Cantidad: " + item.cantidad 
                    + " | Subtotal: $" + totalItem);
        }
        System.out.println("TOTAL COMPRA: $" + totalGeneral);
        
        boolean todasExitosas = true;
        for (ItemCarrito item : carrito) {
            double subtotal = item.celular.getPrecio() * item.cantidad;
            boolean exitoVenta = rvd.procesarVenta(
                    idCliente,
                    empleadoSeleccionado.getId(),
                    item.celular.getId(),
                    item.cantidad,
                    item.celular.getPrecio(),
                    subtotal
            );

            if (!exitoVenta) {
                todasExitosas = false;
            }
        }
        if (todasExitosas) {
            System.out.println("Compra procesada y guardada con exito");
        } else {
            System.out.println("Ocurrió un inconveniente al registrar algunos productos en la venta");
        }
    }
}