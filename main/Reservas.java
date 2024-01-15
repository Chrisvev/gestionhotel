package main;


import java.util.Date;

public class Reservas {

    //Constructor de la Clase Reserva, devuelve el cliente habitacion la fechainicio y la fechafin de la reserva
    public Reservas(Clientes cliente, Habitaciones habitacion, Date fechaInicio, Date fechaFin) {

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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    //Variables
    private Clientes cliente;
    private Habitaciones habitacion;
    private Date fechaInicio;
    private Date fechaFin;
    private double importe;

}
