package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private static int contadorId = 1;
    private String id;
    private String nombreCompleto;
    private List<Reserva> reservasActivas;
    private List<Reserva> historialReservas;

    public Cliente(String nombreCompleto){
        this.id = "CLI-" + String.format("%03d", contadorId++); // Formato CLI-001, CLI-002, etc.
        this.nombreCompleto = nombreCompleto;
        this.reservasActivas = new ArrayList<>();
        this.historialReservas = new ArrayList<>();
    }

    //Getters
    public String getId(){
        return id;
    }
    public String getNombreCompleto(){
        return nombreCompleto;
    }
    public List<Reserva> getReservasActivas(){
        return reservasActivas;
    }
    public List<Reserva> getHistorialReservas(){
        return historialReservas;
    }

    public void agregarReserva(Reserva reserva) throws IllegalStateException {
        if (reservasActivas.size() >= 3) {
            throw new IllegalStateException("El cliente ya tiene 3 reservas activas. No puede realizar más reservas.");
        }
        reservasActivas.add(reserva);
    }
    public void cancelarReserva(Reserva reserva){
        reservasActivas.remove(reserva);
        historialReservas.add(reserva);
    }
    @Override
    public String toString() {
        return "Cliente ID: " + id + " - " + nombreCompleto;
    }
}
