package main;

public class Habitaciones {

    public Habitaciones(int numCamas, EstadoHabitacion estado, categoriaHabitacion categoria) {
        switch (categoria) {
            case INDIVIDUAL:
                this.precio = 100.00;
                break;
            case DOBLE :
                this.precio = 200.00;
                break;
            case TRIPLE :
                this.precio = 300.00;
                break;
        }
    }
    public Habitaciones() {
    	
    }
    

    public void setNumCamas(int num) {
        this.numCamas = num;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public categoriaHabitacion getCategoria() {
        return categoria;
    }

    public void setCategoria(categoriaHabitacion categoria) {
        this.categoria = categoria;
    }

    public void setEstado(EstadoHabitacion nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public double getPrecio() {
        return precio;
    }

    //Enum que representa los posibles estado de la habitacion
    public enum EstadoHabitacion {
        LIBRE,
        OCUPADO,
        NO_DISPONIBLE
    }

    public enum categoriaHabitacion {
        INDIVIDUAL,
        DOBLE,
        TRIPLE
    }

    //Variables
    int numCamas;
    private EstadoHabitacion estado;
    private categoriaHabitacion categoria;
    private double precio;
}