package model;

public class Habitacion {
    public enum Tipo {
        INDIVIDUAL(50),
        DOBLE(80),
        SUITE(150);

        private final double precioPorNoche;

        Tipo(double precioPorNoche) {
            this.precioPorNoche = precioPorNoche;
        }

        public double getPrecioPorNoche() {
            return precioPorNoche;
        }
    }

    public enum Estado {
        DISPONIBLE,
        RESERVADA,
        OCUPADA
    }

    // Atributos de la clase Habitacion
    private int numero;
    private Tipo tipo;
    private Estado estado;
    private String descripcion;

    public Habitacion(int numero, Tipo tipo, String descripcion) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = Estado.DISPONIBLE;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public int getNumero() { return numero; }
    public Tipo getTipo() { return tipo; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public String getDescripcion() { return descripcion; }
    
    @Override
    public String toString() {
        return "Habitación " + numero + " - " + tipo +" - "+ descripcion + " (" + estado + ")";
    }
}
