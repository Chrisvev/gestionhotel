package menu;

import java.time.LocalDate;
import java.util.Scanner;

import main.Hotel;
import main.Habitaciones.Categoria;
import main.Habitaciones.TipoCama;
import main.Habitaciones.estadoHabitacion;
import main.BookingService;
import main.Clientes;
import main.Habitaciones;
import main.Hotel;

public class Menu {
	static Scanner sc = new Scanner(System.in);

	private BookingService boo;
	Hotel h;

	public Menu() {
		Habitaciones habitacion1 = new Habitaciones(1, TipoCama.INDIVIDUAL, Categoria.NORMAL, estadoHabitacion.LIBRE);
		Habitaciones habitacion2 = new Habitaciones(1, TipoCama.INDIVIDUAL, Categoria.NORMAL, estadoHabitacion.LIBRE);

		Habitaciones habitacion3 = new Habitaciones(2, TipoCama.DOBLE, Categoria.BUSINESS, estadoHabitacion.LIBRE);
		Habitaciones habitacion4 = new Habitaciones(2, TipoCama.DOBLE, Categoria.BUSINESS, estadoHabitacion.LIBRE);

		Habitaciones habitacion5 = new Habitaciones(3, TipoCama.TRIPLE, Categoria.SUPERIOR, estadoHabitacion.LIBRE);
		Habitaciones habitacion6 = new Habitaciones(3, TipoCama.TRIPLE, Categoria.SUPERIOR, estadoHabitacion.LIBRE);

		Clientes cliente1 = new Clientes("Alfonso", "Perez", "854856387G", 56);
		Clientes cliente2 = new Clientes("Pedro", "dede", "736458376H", 89);
		h = new Hotel();

		h.registrarCliente(cliente1);
		h.registrarCliente(cliente2);
		h.agregarHabitacion(habitacion1);
		h.agregarHabitacion(habitacion2);
		h.agregarHabitacion(habitacion3);
		h.agregarHabitacion(habitacion4);
		h.agregarHabitacion(habitacion5);
		h.agregarHabitacion(habitacion6);
		boo = new BookingService(h);

	}

	public void MenuApp() {

		System.out.println("Hotel Factory");
		System.out
				.println("Que operacion desea realizar\n 1. Consultar disponibilidad y Reservar habitacion\n 2. Salir");
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:
			consultarHabitaciones();
			break;
		case 2:
			reservarHabitaciones();
			break;
		}
	}

	public void consultarHabitaciones() {

		LocalDate fechaInicio = boo.pedirFechaCliente("Introduce fecha de entrada Dia/mes/agno.");
		LocalDate fechaFin = boo.pedirFechaCliente("Introduce fecha de salida Dia/mes/agno.");
		while (fechaInicio.isAfter(fechaFin)) {
			System.out.println("La fecha de salida no debe ser mas antigua a la fecha de entrada");
			fechaInicio = boo.pedirFechaCliente("Introduce fecha de entrada Dia/mes/agno.");
			fechaFin = boo.pedirFechaCliente("Introduce fecha de salida Dia/mes/agno.");
		}

		cantidadPersonas = boo.pedirCantidadHuespedes();
		boo.consultarDisponibilidadHabitaciones(fechaInicio, fechaFin, cantidadPersonas);
		int tipo = boo.elegirTipoReservaUsuario();
		String dni = boo.pedirDNIUsuario();
		boo.reservarHabitacionCliente(tipo, dni, fechaInicio, fechaFin);

	}

	public void reservarHabitaciones() {
		System.out.println("Hasta luego!");
	}

	public void preguntaReserva() {
		System.out.println("Desea reservar alguna habitacion?\n1. Si\n2. No");
		int opcion = sc.nextInt();
		if (opcion == 1) {
			reservarHabitaciones();
		} else if (opcion == 2) {
			MenuApp();
		} else {
			System.out.println("Opcion no valida");
			preguntaReserva();
		}
	}

	int opcion = 0, cantidadPersonas = 0;

}
