package main;

import java.time.LocalDate;
import java.util.Date;

public class Reservas {

    public Reservas(Clientes cliente, Habitaciones habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;

    }


    
    //Constructor de la Clase Reserva, devuelve el cliente habitacion la fechainicio y la fechafin de la reserva
    
    public Reservas(){
        
    }

    //Getters y Setters
    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
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


    //Variables
    private Clientes cliente;
    private Habitaciones habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String codigoReserva;

}
