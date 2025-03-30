package view;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Cliente;
import model.Habitacion;
import model.Reserva;

public class HotelView {
     public void mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE RESERVAS DE HOTEL ===");
        System.out.println("1. Gestionar Habitaciones");
        System.out.println("2. Gestionar Clientes");
        System.out.println("3. Gestionar Reservas");
        System.out.println("4. Mostrar Resumen del Hotel");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void mostrarMenuHabitaciones() {
        System.out.println("\n=== GESTIÓN DE HABITACIONES ===");
        System.out.println("1. Listar todas las habitaciones");
        System.out.println("2. Buscar habitación por número");
        System.out.println("3. Buscar habitaciones por tipo");
        System.out.println("4. Buscar habitaciones por estado");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    public void mostrarMenuClientes() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        System.out.println("1. Registrar nuevo cliente");
        System.out.println("2. Buscar cliente por ID");
        System.out.println("3. Listar todos los clientes");
        System.out.println("4. Ver reservas activas de un cliente");
        System.out.println("5. Ver historial de reservas de un cliente");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    public void mostrarMenuReservas() {
        System.out.println("\n=== GESTIÓN DE RESERVAS ===");
        System.out.println("1. Crear nueva reserva");
        System.out.println("2. Cancelar reserva");
        System.out.println("3. Listar todas las reservas");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    public void mostrarHabitaciones(List<Habitacion> habitaciones) {
        System.out.println("\n=== LISTA DE HABITACIONES ===");
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
        } else {
            habitaciones.forEach(System.out::println);
        }
    }

    public void mostrarClientes(List<Cliente> clientes) {
        System.out.println("\n=== LISTA DE CLIENTES ===");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    public void mostrarReservas(List<Reserva> reservas) {
        System.out.println("\n=== LISTA DE RESERVAS ===");
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            reservas.forEach(r -> {
                System.out.println(r + " - Cliente: " + r.getCliente().getNombreCompleto());
            });
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    public void mostrarError(String error) {
        System.err.println("\n⚠️ ERROR: " + error);
        System.out.println("Presione Enter para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
        // Ignorar excepción al leer Enter
     }
    }

    public void mostrarTiposHabitacion() {
        System.out.println("\nTipos de habitación disponibles:");
        for (Habitacion.Tipo tipo : Habitacion.Tipo.values()) {
            System.out.println(tipo.name() + " - " + tipo.getPrecioPorNoche() + "€ por noche");
        }
    }

    public void mostrarEstadosHabitacion() {
        System.out.println("\nEstados de habitación:");
        for (Habitacion.Estado estado : Habitacion.Estado.values()) {
            System.out.println(estado.name());
        }
    }
    
}
