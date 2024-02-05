package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import main.Habitaciones.TipoCama;
import main.Habitaciones.Categoria;

public class BookingService {

	private Hotel hotel = new Hotel();
	private ArrayList<Habitaciones> habitaciones;
	private int superior, normal, business;

	public BookingService(Hotel hotel) {
		this.hotel = hotel;
	}

	public LocalDate pedirFechaCliente(String mensaje) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaHoy = LocalDate.now();

		while (true) {
			System.out.println(mensaje);
			String fechaStr = sc.nextLine();

			try {
				LocalDate fechaIntroducida = LocalDate.parse(fechaStr, formatter);

				if (fechaIntroducida.isEqual(fechaHoy) || fechaIntroducida.isAfter(fechaHoy)) {
					return fechaIntroducida;
				} else {
					System.out.println("La fecha debe ser igual o posterior a la fecha de hoy.");
				}
			} catch (Exception e) {
				System.out.println("Formato de fecha incorrecto. Inténtalo de nuevo.");
			}
		}
	}

	public int pedirCantidadHuespedes() {
		Scanner sc = new Scanner(System.in);
		int personas;
		do {
			System.out.println("Indique número de personas: (max. 3)");
			personas = sc.nextInt();
		} while (personas < 1 || personas > 3);
		return personas;
	}

	public void consultarDisponibilidadHabitaciones(LocalDate fechaInicio, LocalDate fechaFin, int numeroPersonas) {
		habitaciones = hotel.getListaHabitaciones();

		filtrarFechasReserva(fechaInicio, fechaFin);
		filtrarHabitacionPersonas(numeroPersonas);
		encontrarTipoHabitacion();
		if (normal < 0)
			normal = 0;
		if (superior < 0)
			superior = 0;
		if (business < 0)
			business = 0;
		System.out.println("Resultado de la consulta");
		System.out.println("---------------------------------------------------------------------------");

		System.out.println(normal + " NORMAL (" + Categoria.NORMAL.getPrecioBase() + " €).");
		System.out.println(business + " BUSINESS (" + Categoria.BUSINESS.getPrecioBase() + " €).");
		System.out.println(superior + " SUPERIOR (" + Categoria.SUPERIOR.getPrecioBase() + " €).");
		System.out.println("---------------------------------------------------------------------------");

	}

	private void filtrarFechasReserva(LocalDate fechaInicio, LocalDate fechaFin) {
		for (int i = habitaciones.size() - 1; i >= 0; i--) {
			Habitaciones habitacion = habitaciones.get(i);
			for (Reservas reserva : hotel.getListaReservas()) {
				if (reserva.getHabitacion() != null && reserva.getHabitacion().equals(habitacion)) {
					if ((reserva.getFechaInicio().isEqual(fechaInicio) && reserva.getFechaFin().isEqual(fechaFin))
							|| (fechaInicio.isAfter(reserva.getFechaInicio())
									&& fechaFin.isBefore(reserva.getFechaFin()))) {
						habitaciones.remove(i);
						break;
					}
				}
			}
		}
	}

	private void filtrarHabitacionPersonas(int personasCantidad) {
		for (int i = habitaciones.size() - 1; i >= 0; i--) {
			Habitaciones habitacion = habitaciones.get(i);
			if (personasCantidad == 2) {
				if (habitacion.getNumeroCamas().equals(TipoCama.INDIVIDUAL))
					habitaciones.remove(i);
			}
			if (personasCantidad == 3) {
				if (habitacion.getNumeroCamas().equals(TipoCama.INDIVIDUAL)
						|| habitacion.getNumeroCamas().equals(TipoCama.DOBLE))
					habitaciones.remove(i);
			}
		}
	}

	private void encontrarTipoHabitacion() {
		for (Habitaciones habi : habitaciones) {
			switch (habi.getCategoria()) {
			case NORMAL:
				normal++;
				break;
			case BUSINESS:
				business++;
				break;
			case SUPERIOR:
				superior++;
				break;
			}
		}
	}

	public void reservarHabitacionCliente(int tipo, String dni, LocalDate fechaInicio, LocalDate fechaFin) {
		if (tipo < 1 || tipo > 3) {
			System.err.println("Opcion no valida");
			return;
		}
		Habitaciones habitacion = obtenerHabitacion(tipo);
		if (habitacion == null) {
			System.out.println("No hay habitaciones disponibles.");
			return;
		}
		boolean bandera = verificarDNI(dni);
		if (!bandera) {
			solicitarDatos(dni);
		}
		System.out.println("Precio de la habitacion: " + habitacion.getCategoria().getPrecioTotal());
		char confirmar = confirmarReserva();

		if (confirmar == 'Y' || confirmar == 'y') {
			System.out.println("Reserva realizada");
			Reservas r = new Reservas(generarCodigoReserva(dni), habitacion, fechaInicio, fechaFin);
			System.out.println("Código de reserva: " + r.getCodigoReserva());
			hotel.getListaReservas().add(r);
		}
	}

	private String generarCodigoReserva(String dni) {
		return dni + "-768754";
	}

	private char confirmarReserva() {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Desea confirmar la reserva? (Y/N)");
		return sc.next().charAt(0);
	}

	public String pedirDNIUsuario() {
		Scanner sc = new Scanner(System.in);
		String dni;
		do {
			System.out.println("Introduzca DNI:");
			dni = sc.nextLine().trim();
			if (!esDNIValido(dni)) {
				System.out.println("El formato inválido.");
			}
		} while (!esDNIValido(dni));

		return dni;
	}

	private boolean esDNIValido(String dni) {
		if (dni.length() != 9)
			return false;

		for (int i = 0; i < 8; i++) {
			if (!Character.isDigit(dni.charAt(i)))
				return false;
		}

		char lastChar = dni.charAt(8);
		return Character.isLetter(lastChar) || lastChar == 'X';
	}

	private void solicitarDatos(String dni) {
		Scanner sc = new Scanner(System.in);
		String nombre = "", apellidos = "";
		int edad = -1;
		do {
			System.out.println("Nombre: ");
			nombre = sc.nextLine();
			if (nombre.isEmpty())
				System.err.println("Introduce un nombre válido.");
		} while (nombre.isEmpty());
		do {
			System.out.println("Apellidos: ");
			apellidos = sc.nextLine();
			if (apellidos.isEmpty())
				System.err.println("Apellido no valido.");
		} while (apellidos.isEmpty());
		do {
			System.out.println("Edad: ");
			edad = sc.nextInt();
			if (edad < 18)
				System.err.println("No es mayor de edad");
		} while (edad < 18);
		hotel.getListaClientes().add(new Clientes(nombre, apellidos, dni, edad));
	}

	private boolean verificarDNI(String dni) {
		boolean bandera = false;
		for (Clientes c : hotel.getListaClientes()) {
			if (c.getDni().equals(dni)) {
				bandera = true;
				break;
			}
		}
		return bandera;
	}

	private Habitaciones obtenerHabitacion(int tipo) {
		Habitaciones habitacion = null;
		for (Habitaciones habi : habitaciones) {
			switch (tipo) {
			case 1:
				if (habi.getNumeroCamas().equals(TipoCama.INDIVIDUAL)) {
					habitacion = habi;
				}
				break;
			case 2:
				if (habi.getNumeroCamas().equals(TipoCama.DOBLE)) {
					habitacion = habi;
				}
				break;

			case 3:
				if (habi.getNumeroCamas().equals(TipoCama.TRIPLE)) {
					habitacion = habi;
				}
				break;
			}
		}
		return habitacion;
	}

	public int elegirTipoReservaUsuario() {
		Scanner sc = new Scanner(System.in);
		int opcion = -1;
		boolean bandera = true;
		do {
			System.out.println("1. Reservar Habitación NORMAL");
			System.out.println("2. Reservar Habitación BUSINESS");
			System.out.println("3. Reservar Habitación SUPERIOR");
			System.out.println("-----------------------------------");
			System.out.println("Cual desea?:");
			opcion = sc.nextInt();
			if (opcion == 1 && normal == 0) {
				bandera = false;
				System.err.println("No quedan habitaciones de ese tipo.");
			} else if (opcion == 2 && business == 0) {
				bandera = false;
				System.err.println("No quedan habitaciones de ese tipo");
			} else if (opcion == 3 && superior == 0) {
				bandera = false;
				System.err.println("No quedan habitaciones de ese tipo");
			} else if (opcion < 1 || opcion > 3) {
				bandera = false;
				System.err.println("Introduce una opción válida.");
			} else {
				bandera = true;
			}
		} while (bandera == false);
		return opcion;
	}
}