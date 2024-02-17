package main;

import java.util.ArrayList;

import main.Clientes;
import main.Habitaciones;
import main.Reservas;

public class Hotel {
	
	public static ClientesDAOImpl cdao = new ClientesDAOImpl();
	public static HabitacionesDAOImpl hdao = new HabitacionesDAOImpl();
	public static ReservasDAOImpl rdao = new ReservasDAOImpl();

	

	public Hotel() {
		
	}

	public void agregarReserva(Reservas reserva) {
		rdao.guardar(reserva);
	}

	public ArrayList<Reservas> getListaReservas() {
		return (ArrayList<Reservas>) rdao.obtenerTodo();
	}

	public void agregarHabitacion(Habitaciones habitacion) {
		hdao.guardar(habitacion);
	}

	public ArrayList<Habitaciones> getListaHabitaciones() {
		return (ArrayList<Habitaciones>) hdao.obtenerTodo();
	}

	public void registrarCliente(Clientes cliente) {
		cdao.guardar(cliente);
	}

	public ArrayList<Clientes> getListaClientes() {
		return (ArrayList<Clientes>) cdao.obtenerTodo();
	}
}
