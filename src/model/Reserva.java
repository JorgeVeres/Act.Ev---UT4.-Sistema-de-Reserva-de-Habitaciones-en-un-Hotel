package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private String id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double precioTotal;

    public Reserva(String id, Habitacion habitacion, Cliente cliente,LocalDate checkIn,LocalDate checkOut){
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.precioTotal = calcularPrecioTotal();

        validarFechas();
    }

    private void validarFechas(){
        if(checkIn.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("No se pueden hacer resrvas en fechas pasadas");
        }
        if(checkOut.isBefore(checkIn)){
            throw new IllegalArgumentException("El check-out no puede ser antes del check-in");
        }
        if(ChronoUnit.DAYS.between(checkIn, checkOut) > 90){
            throw new IllegalArgumentException("El máximo de días para una reserva es 90");
        }
    }
    private double calcularPrecioTotal(){
        long noches = ChronoUnit.DAYS.between(checkIn, checkOut);
        return noches * habitacion.getTipo().getPrecioPorNoche();
    }

    //Getters
    public String getId(){
        return id;
    }
    public Habitacion getHabitacion(){
        return habitacion;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public LocalDate getCheckIn(){
        return checkIn;
    }   
    public LocalDate getCheckOut(){
        return checkOut;
    }
    public double getPrecioTotal(){
        return precioTotal;
    }

    public boolean estaActiva(){
        return LocalDate.now().isBefore(checkOut);
    }
    public boolean puedeCancelarse(){
        return LocalDate.now().isBefore(checkIn);
    }
    @Override
    public String toString() {
        return "Reserva ID: " + id + " - Habitación: " + habitacion.getNumero() + 
               " (" + checkIn + " a " + checkOut + ") - Total: " + precioTotal + "€";       
    }
}
