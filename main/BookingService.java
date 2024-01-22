package main;


import java.time.LocalDate;
import java.util.Scanner;

public class BookingService {

	private Hotel hotel;

	public BookingService(Hotel hotel) {
		this.hotel = hotel;
	}


	public void consultarDisponibilidad(LocalDate fechaIni, LocalDate fechaFin, int numPersonas) {
		System.out.println("Consulta de disponibilidad para " + numPersonas + " personas del " + fechaIni + " al "
				+ fechaFin + ":");

		for (Habitaciones habitacion : hotel.getListaHabitaciones()) {
			if (habitacion.getNumPersonas() >= numPersonas && verificarDisponibilidad(habitacion, fechaIni, fechaFin)
					&& habitacion.getEstado().equals("Libre")) {
				System.out.println("Habitación disponible - Categoría: " + habitacion.getCategoria() + ", Tipo: "
						+ habitacion.getTipo() + ", Estado: " + habitacion.getEstado());
			}
		}
	}


	private boolean verificarDisponibilidad(Habitaciones habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
		for (Reservas reserva : hotel.getListaReservas()) {
			if (reserva.getHabitacion().equals(habitacion) && !fechaFin.isBefore(reserva.getFechaInicio())
					&& !fechaInicio.isAfter(reserva.getFechaFin())) {
				return false;
			}
		}
		return true;
	}


	public void reservarHabitacion(LocalDate fechaIni, LocalDate fechaFin, int numPersonas, String categoria,
			String dniCliente) {
		boolean bandera = verificarCliente(dniCliente);
		if (bandera == false) {
			System.err.println("Cliente no existente");
			registrarCliente();
		}
			Habitaciones habitacionDeseada = habitacionEscogida(numPersonas, categoria, fechaIni, fechaFin);

			if (habitacionDeseada != null) {
				double costo = calculoAlojamiento();

				System.out.println("Costo: " + costo);
				boolean confirmacion = confirmarReservaHotel();

				if (confirmacion) {
					Clientes c = obtenerDatoCliente(dniCliente);
					Reservas reserva = new Reservas(habitacionDeseada, fechaIni, fechaFin, c);
					hotel.getListaReservas().add(reserva);
					habitacionDeseada.setEstado("Reservado");
					System.out.println("Reserva realizada con éxito. Código de reserva: " + reserva.getCodigoReserva());
				} else {
					System.out.println("Se cancelo la reserva.");
				}
			} else {
				System.out.println("Habitacion no disponible según los criterios proporcionados.");
			}
		}

		
	


	

	private Clientes obtenerDatoCliente(String dni) {

		for (Clientes cliente : hotel.getListaClientes()) {
			if (cliente.getDni().equals(dni)) {
				return cliente;
			}
		}
		return null;
	}

	public void registrarCliente() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Apellidos: ");
		String apellidos = sc.nextLine();
		System.out.println("DNI: ");
		String DNI = sc.nextLine();

		if (nombre.equalsIgnoreCase("") || apellidos.equalsIgnoreCase("") || DNI.equalsIgnoreCase("")) {
			System.err.println("Faltan datos por ingresar");
		} else {
			Clientes nuevoCliente = new Clientes(nombre, apellidos, DNI);
			hotel.getListaClientes().add(nuevoCliente);
		}

	}
	
	public boolean verificarCliente(String dni) {

		for (Clientes cliente : hotel.getListaClientes()) {
			if (cliente.getDni().equals(dni)) {
				return true;
			}
		}

		return false;
	}

	private Habitaciones habitacionEscogida(int numPersonas, String categoria, LocalDate fechaIni, LocalDate fechaFin) {
		for (Habitaciones habitacion : hotel.getListaHabitaciones()) {
			if (habitacion.getNumPersonas() >= numPersonas && verificarDisponibilidad(habitacion, fechaIni, fechaFin)
					&& habitacion.getCategoria().equals(categoria)) {
				return habitacion;
			}
		}
		return null;
	}

	public double calculoAlojamiento() {
		return (int) (Math.random() * 100) + 30;
	}

	
	public boolean confirmarReservaHotel() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Realizar la reserva? (S/N)");
		String respuesta = sc.nextLine();

		if (respuesta.equalsIgnoreCase("Si")) {
			return true;
		} else {
			return false;
		}
	}
}