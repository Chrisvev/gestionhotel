package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import main.Habitaciones.TipoCama;
import main.Habitaciones.estadoHabitacion;
import main.Habitaciones.Categoria;

public class BookingService {

	private Scanner sc = new Scanner(System.in);
	private Hotel hotel;
	private ArrayList<Habitaciones> habitaciones;
	private ArrayList<Clientes> clientes;
	private ArrayList<Reservas> reservas;
	private int superior, normal, business;

	public BookingService(Hotel hotel) {
		this.hotel = hotel;
		habitaciones = hotel.getListaHabitaciones();
		reservas = hotel.getListaReservas();
		clientes = hotel.getListaClientes();
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
		localizarTipoHabitacion();
		filtroFechasReserva(fechaInicio, fechaFin);
		filtrarHabitacionPersonas(numeroPersonas);
		filtrarEstadoHabitacion();

		System.out.println(normal + " NORMAL (" + Categoria.NORMAL.getPrecioBase() + " €).");
		System.out.println(business + " BUSINESS (" + Categoria.BUSINESS.getPrecioBase() + " €).");
		System.out.println(superior + " SUPERIOR (" + Categoria.SUPERIOR.getPrecioBase() + " €).");
	}

	private void filtroFechasReserva(LocalDate fechaInicio, LocalDate fechaFin) {
		for (Reservas reserva : hotel.getListaReservas()) {
			LocalDate reservaInicio = reserva.getFechaInicio();
			LocalDate reservaFin = reserva.getFechaFin();

			if ((fechaInicio.isBefore(reservaFin) || fechaInicio.isEqual(reservaFin))
					&& (fechaFin.isAfter(reservaInicio) || fechaFin.isEqual(reservaInicio))) {
				Habitaciones habitacionReservada = reserva.getHabitacion();
				if (habitacionReservada != null) {
					switch (habitacionReservada.getCategoria()) {
					case NORMAL:
						normal--;
						break;
					case BUSINESS:
						business--;
						break;
					case SUPERIOR:
						superior--;
						break;
					}
				}
			}
		}
	}

	private void localizarTipoHabitacion() {
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

	private void filtrarHabitacionPersonas(int personasCantidad) {
		for (Habitaciones habitacion : habitaciones) {
			if (personasCantidad == 2 && habitacion.getNumeroCamas().equals(TipoCama.INDIVIDUAL)) {
				switch (habitacion.getCategoria()) {
				case NORMAL:
					normal--;
					break;
				case BUSINESS:
					business--;
					break;
				case SUPERIOR:
					superior--;
					break;
				}
			} else if (personasCantidad == 3 && (habitacion.getNumeroCamas().equals(TipoCama.INDIVIDUAL)
					|| habitacion.getNumeroCamas().equals(TipoCama.DOBLE))) {
				switch (habitacion.getCategoria()) {
				case NORMAL:
					normal--;
					break;
				case BUSINESS:
					business--;
					break;
				case SUPERIOR:
					superior--;
					break;
				}
			}
		}
	}

	private void filtrarEstadoHabitacion() {
		for (Habitaciones habi : hotel.hdao.obtenerTodo()) {
			switch (habi.getCategoria()) {
			case NORMAL:
				if (habi.getEstado().equals(estadoHabitacion.OCUPADO)
						|| habi.getEstado().equals(estadoHabitacion.NO_DISPONIBLE)) {
					normal--;
				}
				break;
			case BUSINESS:
				if (habi.getEstado().equals(estadoHabitacion.OCUPADO)
						|| habi.getEstado().equals(estadoHabitacion.NO_DISPONIBLE)) {
					business--;
				}

				break;
			case SUPERIOR:

				if (habi.getEstado().equals(estadoHabitacion.OCUPADO)
						|| habi.getEstado().equals(estadoHabitacion.NO_DISPONIBLE)) {
					superior--;
				}
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
		Clientes cliente = new Clientes();
		for (Clientes c : hotel.cdao.obtenerTodo()) {
			if (c.getDni().equals(dni)) {
				cliente = c;
			}
		}
		System.out.println("Precio de la habitacion: " + habitacion.getCategoria().getPrecioTotal());
		char confirmar = confirmarReserva();

		if (confirmar == 'Y' || confirmar == 'y') {
			System.out.println("Reserva realizada");
			Reservas r = new Reservas(generarCodigoReserva(), null, fechaInicio, fechaFin, cliente);
			System.out.println("Código de reserva: " + r.getCodigoReserva());
			hotel.agregarReserva(r);
			habitacion.setCliente(cliente);
			hotel.hdao.modificar(habitacion);
		}
	}

	// Generardos de codigos de barras (Gracias Chat)
	private String generarCodigoReserva() {
		UUID uuid = UUID.randomUUID();
		String codigo = uuid.toString().toUpperCase().replaceAll("-", "");
		StringBuilder sb = new StringBuilder();
		int len = codigo.length();
		for (int i = 0; i < len; i++) {
			sb.append(codigo.charAt(i));
			if ((i + 1) % 6 == 0 && i != len - 1) {
				sb.append("-");
			}
		}
		return sb.toString();
	}

	public String obtenerCodigoReserva() {
		boolean bandera = false;
		String codigo = "";
		do {
			System.out.println("Introduce el codigo de la reserva");
			codigo = sc.nextLine();
			for (Reservas r : hotel.rdao.obtenerTodo()) {
				if (r.getCodigoReserva().equals(codigo)) {
					bandera = true;
				}
			}
		} while (!bandera);
		return codigo;
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
		int edad = 0;
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
		hotel.registrarCliente(new Clientes(nombre, apellidos, dni, edad));
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

	public void checkIn(String codigo) {
		boolean bandera = false;
		for (Reservas r : hotel.rdao.obtenerTodo()) {
			if (r.getCodigoReserva().equals(codigo)) {
				if (r.getFechaInicio().isAfter(LocalDate.now())) {
					System.err.println("No puedes hacer el checkIn todavia");
				} else {
					bandera = true;
				}
			}
		}
		if (bandera) {
			for (Reservas r : hotel.rdao.obtenerTodo()) {
				if (r.getCodigoReserva().equals(codigo)) {
					for (Habitaciones h : hotel.hdao.obtenerTodo()) {
						if (h.getCliente() != null && h.getCliente().getDni().equals(r.getCliente().getDni())) {
							h.setEstado(estadoHabitacion.OCUPADO);
							r.setHabitacion(h);
							hotel.hdao.modificar(h);
							hotel.rdao.modificar(r);
							System.out.println("Estancia confirmada");
						}
					}
				}
			}
		}
	}

	public void checkOut(String dni) {
		boolean bandera = false;
		for (Reservas r : hotel.rdao.obtenerTodo()) {
			if (r.getCliente().getDni().equals(dni)) {
				bandera = true;
			}
		}

		if (bandera) {
			for (Reservas r : hotel.rdao.obtenerTodo()) {
				if (r.getCliente().getDni().equals(dni)) {
					for (Habitaciones h : hotel.hdao.obtenerTodo()) {
						if (h.getCliente() != null && h.getCliente().getDni().equals(r.getCliente().getDni())) {
							h.setEstado(estadoHabitacion.LIBRE);
							h.setCliente(null);
							hotel.hdao.modificar(h);
							long dias = ChronoUnit.DAYS.between(r.getFechaInicio(), r.getFechaFin());
							hotel.rdao.eliminar(r);

							System.out.println("CheckOut realizado, coste total de: "
									+ (dias * h.getCategoria().getPrecioTotal()));
						}
					}
				}
			}
		} else {
			System.out.println("No se ha encontrado ningun Dni asocidado");
		}
	}

	public void cancelarReserva(String codigo) {

		for (Reservas r : hotel.rdao.obtenerTodo()) {
			if (r.getCodigoReserva().equals(codigo)) {
				if (r.getHabitacion() == null) {
					for (Habitaciones h : hotel.hdao.obtenerTodo()) {
						if (r.getCliente().equals(h.getCliente())) {
							h.setCliente(null);
							hotel.hdao.modificar(h);
						}
					}
					hotel.rdao.eliminar(r);
					System.out.println("Reserva cancelada xD");
				}
			}
		}


	}
}