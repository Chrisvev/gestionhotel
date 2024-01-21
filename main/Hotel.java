package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    //Obtener reservas
    public ArrayList<Clientes> listaClientes = new ArrayList<>();
     public ArrayList<Habitaciones> listaHabitaciones = new ArrayList<>();
      public ArrayList<Reservas> listaReservas = new ArrayList<>();
	public ArrayList<Clientes> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ArrayList<Clientes> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public ArrayList<Habitaciones> getListaHabitaciones() {
		return listaHabitaciones;
	}
	public void setListaHabitaciones(ArrayList<Habitaciones> listaHabitaciones) {
		this.listaHabitaciones = listaHabitaciones;
	}
	public ArrayList<Reservas> getListaReservas() {
		return listaReservas;
	}
	public void setListaReservas(ArrayList<Reservas> listaReservas) {
		this.listaReservas = listaReservas;
	}

      
}

