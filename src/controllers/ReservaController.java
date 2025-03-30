package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.ClienteNoEncontradoException;
import exception.ReservaNoDisponibleException;
import model.Cliente;
import model.Habitacion;
import model.Reserva;

public class ReservaController {
    private List<Reserva> reservas;
    private HabitacionController habitacionController;
    private ClienteController clienteController;

    public ReservaController(HabitacionController habitacionController, ClienteController clienteController) {
        this.reservas = new ArrayList<>();
        this.habitacionController = habitacionController;
        this.clienteController = clienteController;
    }

    public Reserva crearReserva(String idReserva, int numeroHabitacion, String idCliente, LocalDate checkIn, LocalDate checkOut) throws ReservaNoDisponibleException, ClienteNoEncontradoException {

        Habitacion habitacion = habitacionController.buscarHabitacionPorNumero(numeroHabitacion);
        if (habitacion == null) {
            throw new ReservaNoDisponibleException("Habitación no encontrada");
        }

        if (habitacion.getEstado() != Habitacion.Estado.DISPONIBLE) {
            throw new ReservaNoDisponibleException("Habitación no disponible");
        }

        if (!estaHabitacionDisponible(habitacion, checkIn, checkOut)) {
            throw new ReservaNoDisponibleException("Habitación ya reservada en esas fechas");
        }

        Cliente cliente = clienteController.buscarClientePorId(idCliente);

        try {
            Reserva reserva = new Reserva(idReserva, habitacion, cliente, checkIn, checkOut);

            habitacion.setEstado(Habitacion.Estado.RESERVADA);
            cliente.agregarReserva(reserva);
            reservas.add(reserva);

            return reserva;
        } catch (IllegalStateException e) {
           
            throw new ReservaNoDisponibleException(e.getMessage());
        }
    }

    public void cancelarReserva(String idReserva) throws ReservaNoDisponibleException {
        Reserva reserva = reservas.stream()
                .filter(r -> r.getId().equals(idReserva))
                .findFirst()
                .orElseThrow(() -> new ReservaNoDisponibleException("Reserva no encontrada"));
        
        if (!reserva.puedeCancelarse()) {
            throw new ReservaNoDisponibleException("No se puede cancelar una reserva que ya ha comenzado");
        }
        
        reserva.getHabitacion().setEstado(Habitacion.Estado.DISPONIBLE);
        reserva.getCliente().cancelarReserva(reserva);
        reservas.remove(reserva);
    }

    private boolean estaHabitacionDisponible(Habitacion habitacion, LocalDate checkIn, LocalDate checkOut) {
        return reservas.stream()
                .filter(r -> r.getHabitacion().equals(habitacion))
                .noneMatch(r -> checkIn.isBefore(r.getCheckOut()) && checkOut.isAfter(r.getCheckIn()));
    }

    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }
}
