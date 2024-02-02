package main;

import java.util.ArrayList;

import main.Clientes;
import main.Habitaciones;
import main.Reservas;

public class Hotel {

	private ArrayList<Habitaciones> habitaciones;
	private ArrayList<Reservas> reservas;
	private ArrayList<Clientes> clientes;

	public Hotel() {
		this.habitaciones = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.clientes = new ArrayList<>();
	}

	public void agregarReserva(Reservas reserva) {
		reservas.add(reserva);
	}

	public ArrayList<Reservas> getListaReservas() {
		return reservas;
	}

	public void agregarHabitacion(Habitaciones habitacion) {
		habitaciones.add(habitacion);
	}

	public ArrayList<Habitaciones> getListaHabitaciones() {
		return habitaciones;
	}

	public void registrarCliente(Clientes cliente) {
		clientes.add(cliente);
	}

	public ArrayList<Clientes> getListaClientes() {
		return clientes;
	}
}
