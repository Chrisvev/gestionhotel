package main;

import java.time.LocalDate;
import java.util.Date;
public class Reservas {
    private static int contadorReservas = 1;
    private int codigoReserva;
    private Habitaciones habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Clientes cliente;

    
    public Reservas(Habitaciones habitacion, LocalDate fechaInicio, LocalDate fechaFin, Clientes cliente) {
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;


        this.habitacion.setEstado("Ocupado");
        this.codigoReserva = contadorReservas++;
    }

    public int getCodigoReserva() {
        return codigoReserva;
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

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
}
