package main;
import java.time.LocalDate;

/**
 * @author Chris
 */
public class Reservas {
    private Habitaciones habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String codigoReserva;

    

    
    public Reservas() {
    	
    }

    
    public Reservas(String codigoReserva, Habitaciones habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.codigoReserva = codigoReserva;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    
    public Habitaciones getHabitacion() {
        return habitacion;
    }

   
    public void setHabitacion(Habitaciones habitacion) {
        this.habitacion = habitacion;
    }

   
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

   
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    public String getCodigoReserva() {
        return codigoReserva;
    }

    
    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }
}

