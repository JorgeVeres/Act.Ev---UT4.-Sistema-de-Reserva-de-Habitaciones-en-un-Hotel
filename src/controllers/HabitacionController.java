package controllers;

import java.util.ArrayList;
import java.util.List;

import model.Habitacion;

public class HabitacionController {
    private List<Habitacion> habitaciones;

    public HabitacionController(){
        this.habitaciones = new ArrayList<>();
        inicializarHabitaciones();
    }
    private void inicializarHabitaciones(){
        //Planta 1
        agregarHabitacion(101, Habitacion.Tipo.INDIVIDUAL, "Vista al jardin");
        agregarHabitacion(102, Habitacion.Tipo.INDIVIDUAL, "Vista a la calle");
        agregarHabitacion(103, Habitacion.Tipo.DOBLE, "Cama matrimonial");
        agregarHabitacion(104, Habitacion.Tipo.DOBLE, "Dos camas individuales");
        agregarHabitacion(105, Habitacion.Tipo.SUITE, "Suite con jacuzzi");

        //Planta 2
        agregarHabitacion(201, Habitacion.Tipo.INDIVIDUAL, "Vista al mar");
        agregarHabitacion(202, Habitacion.Tipo.INDIVIDUAL, "Vista a la montaña");
        agregarHabitacion(203, Habitacion.Tipo.DOBLE, "Cama king size");
        agregarHabitacion(204, Habitacion.Tipo.DOBLE, "Dos camas individuales");
        agregarHabitacion(205, Habitacion.Tipo.SUITE, "Suite ejecutiva");

        //Planta 3
        agregarHabitacion(301, Habitacion.Tipo.INDIVIDUAL, "Vista al panoramica");
        agregarHabitacion(302, Habitacion.Tipo.INDIVIDUAL, "Vista a la ciudad");
        agregarHabitacion(303, Habitacion.Tipo.DOBLE, "Terraza privada");
        agregarHabitacion(304, Habitacion.Tipo.DOBLE, "Balcon con vista al mar");
        agregarHabitacion(305, Habitacion.Tipo.SUITE, "Suite presidencial");
    }

    private void agregarHabitacion(int numero, Habitacion.Tipo tipo, String descripcion){
        Habitacion habitacion = new Habitacion(numero, tipo, descripcion);
        habitaciones.add(habitacion);
    }
    public List<Habitacion> listarHabitaciones(){
        return new ArrayList<>(habitaciones);
    }
    public Habitacion buscarHabitacionPorNumero(int numero) {
        return habitaciones.stream()
                .filter(h -> h.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }
    public List<Habitacion> buscarHabitacionesPorTipo(Habitacion.Tipo tipo) {
        return habitaciones.stream()
                .filter(h -> h.getTipo() == tipo)
                .toList();
    }
    public List<Habitacion> buscarHabitacionesPorEstado(Habitacion.Estado estado) {
        return habitaciones.stream()
                .filter(h -> h.getEstado() == estado)
                .toList();
    }
}
