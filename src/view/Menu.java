package view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import controllers.ClienteController;
import controllers.HabitacionController;
import controllers.ReservaController;
import exception.ClienteNoEncontradoException;
import exception.ReservaNoDisponibleException;
import model.Cliente;
import model.Habitacion;
import model.Reserva;

public class Menu {
    private Scanner scanner;
    private HotelView vista;
    private HabitacionController habitacionController;
    private ClienteController clienteController;
    private ReservaController reservaController;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.vista = new HotelView();
        this.habitacionController = new HabitacionController();
        this.clienteController = new ClienteController();
        this.reservaController = new ReservaController(habitacionController, clienteController);
    }

    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenuPrincipal();
            opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    menuHabitaciones();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 3:
                    menuReservas();
                    break;
                case 4:
                    mostrarResumenHotel();
                    break;
                case 0:
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarError("Opción no válida");
            }
        } while (opcion != 0);
        
        scanner.close();
    }

    private void menuHabitaciones() {
        int opcion;
        do {
            vista.mostrarMenuHabitaciones();
            opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    vista.mostrarHabitaciones(habitacionController.listarHabitaciones());
                    break;
                case 2:
                    buscarHabitacionPorNumero();
                    break;
                case 3:
                    buscarHabitacionesPorTipo();
                    break;
                case 4:
                    buscarHabitacionesPorEstado();
                    break;
                case 0:
                    break;
                default:
                    vista.mostrarError("Opción no válida");
            }
        } while (opcion != 0);
    }

    private void buscarHabitacionPorNumero() {
        vista.mostrarMensaje("Ingrese el número de habitación:");
        int numero = leerEntero();
        Habitacion habitacion = habitacionController.buscarHabitacionPorNumero(numero);
        
        if (habitacion != null) {
            vista.mostrarMensaje("Habitación encontrada:");
            System.out.println(habitacion);
        } else {
            vista.mostrarError("Habitación no encontrada");
        }
    }

    private void buscarHabitacionesPorTipo() {
        vista.mostrarTiposHabitacion();
        vista.mostrarMensaje("Ingrese el tipo de habitación:");
        String tipoStr = scanner.nextLine().toUpperCase();
        
        try {
            Habitacion.Tipo tipo = Habitacion.Tipo.valueOf(tipoStr);
            vista.mostrarHabitaciones(habitacionController.buscarHabitacionesPorTipo(tipo));
        } catch (IllegalArgumentException e) {
            vista.mostrarError("Tipo de habitación no válido");
        }
    }

    private void buscarHabitacionesPorEstado() {
        vista.mostrarEstadosHabitacion();
        vista.mostrarMensaje("Ingrese el estado de habitación:");
        String estadoStr = scanner.nextLine().toUpperCase();
        
        try {
            Habitacion.Estado estado = Habitacion.Estado.valueOf(estadoStr);
            vista.mostrarHabitaciones(habitacionController.buscarHabitacionesPorEstado(estado));
        } catch (IllegalArgumentException e) {
            vista.mostrarError("Estado de habitación no válido");
        }
    }

    private void menuClientes() {
        int opcion;
        do {
            vista.mostrarMenuClientes();
            opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    buscarClientePorId();
                    break;
                case 3:
                    vista.mostrarClientes(clienteController.listarClientes());
                    break;
                case 4:
                    verReservasActivasCliente();
                    break;
                case 5:
                    verHistorialReservasCliente();
                    break;
                case 0:
                    break;
                default:
                    vista.mostrarError("Opción no válida");
            }
        } while (opcion != 0);
    }

    private void registrarCliente() {
        vista.mostrarMensaje("Ingrese el nombre completo del cliente:");
        String nombre = scanner.nextLine();
    
        Cliente nuevoCliente = clienteController.agregarCliente(nombre);
        vista.mostrarMensaje("Cliente registrado con éxito. ID asignado: " + nuevoCliente.getId());
    }

    private void buscarClientePorId() {
        vista.mostrarMensaje("Ingrese el ID del cliente:");
    String id = scanner.nextLine();
    
    try {
        Cliente cliente = clienteController.buscarClientePorId(id);
        vista.mostrarMensaje("Cliente encontrado:");
        System.out.println("ID: " + cliente.getId() + " - Nombre: " + cliente.getNombreCompleto());
    } catch (ClienteNoEncontradoException e) {
        vista.mostrarError(e.getMessage());
    }
    }

    private void verReservasActivasCliente() {
        vista.mostrarMensaje("Ingrese el ID del cliente:");
        String id = scanner.nextLine();
        
        try {
            List<Reserva> reservas = clienteController.obtenerReservasActivasCliente(id);
            vista.mostrarReservas(reservas);
        } catch (ClienteNoEncontradoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void verHistorialReservasCliente() {
        vista.mostrarMensaje("Ingrese el ID del cliente:");
        String id = scanner.nextLine();
        
        try {
            List<Reserva> reservas = clienteController.obtenerHistorialReservasCliente(id);
            vista.mostrarReservas(reservas);
        } catch (ClienteNoEncontradoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void menuReservas() {
        int opcion = 0;
        do {
            try {
                vista.mostrarMenuReservas();
                opcion = leerEntero();
                
                switch (opcion) {
                    case 1:
                        crearReserva();
                        break;
                    case 2:
                        cancelarReserva();
                        break;
                    case 3:
                        vista.mostrarReservas(reservaController.listarReservas());
                        break;
                    case 0:
                        break;
                    default:
                        vista.mostrarError("Opción no válida");
                }
            } catch (Exception e) {
                vista.mostrarError("Error: " + e.getMessage());
                // El bucle continúa después de mostrar el error
            }
        } while (opcion != 0);
    }

    private void crearReserva() {
        try {
            vista.mostrarMensaje("Ingrese el número de habitación:");
            int numeroHabitacion = leerEntero();
            
            vista.mostrarMensaje("Ingrese el ID del cliente:");
            String idCliente = scanner.nextLine();
            
            vista.mostrarMensaje("Ingrese la fecha de check-in (formato YYYY-MM-DD):");
            LocalDate checkIn = leerFecha();
            
            vista.mostrarMensaje("Ingrese la fecha de check-out (formato YYYY-MM-DD):");
            LocalDate checkOut = leerFecha();
            
            String idReserva = "RES-" + System.currentTimeMillis();
            
            Reserva reserva = reservaController.crearReserva(
                idReserva, numeroHabitacion, idCliente, checkIn, checkOut);
            
            vista.mostrarMensaje("Reserva creada con éxito:");
            System.out.println(reserva);
            
        } catch (ReservaNoDisponibleException | ClienteNoEncontradoException e) {
            vista.mostrarError(e.getMessage());
            // No relanzamos la excepción, solo mostramos el mensaje
        } catch (DateTimeParseException e) {
            vista.mostrarError("Formato de fecha incorrecto. Use YYYY-MM-DD");
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void cancelarReserva() {
        vista.mostrarMensaje("Ingrese el ID de la reserva a cancelar:");
        String idReserva = scanner.nextLine();
        
        try {
            reservaController.cancelarReserva(idReserva);
            vista.mostrarMensaje("Reserva cancelada con éxito");
        } catch (ReservaNoDisponibleException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void mostrarResumenHotel() {
        vista.mostrarMensaje("\n=== RESUMEN DEL HOTEL ===");
        
        // Resumen de habitaciones
        vista.mostrarMensaje("\nHABITACIONES:");
        vista.mostrarHabitaciones(habitacionController.listarHabitaciones());
        
        // Resumen de clientes
        vista.mostrarMensaje("\nCLIENTES:");
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            vista.mostrarMensaje("No hay clientes registrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
                try {
                    List<Reserva> reservas = clienteController.obtenerReservasActivasCliente(cliente.getId());
                    if (reservas.isEmpty()) {
                        System.out.println("  No tiene reservas activas");
                    } else {
                        System.out.println("  Reservas activas:");
                        reservas.forEach(r -> System.out.println("  - " + r));
                    }
                } catch (ClienteNoEncontradoException e) {
                    // No debería ocurrir ya que el cliente viene de la lista
                }
            }
        }
        
        // Resumen de reservas
        vista.mostrarMensaje("\nRESERVAS TOTALES:");
        vista.mostrarReservas(reservaController.listarReservas());
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                vista.mostrarError("Por favor, ingrese un número válido");
            }
        }
    }

    private LocalDate leerFecha() {
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                vista.mostrarError("Formato de fecha incorrecto. Use YYYY-MM-DD");
            }
        }
    }
}
