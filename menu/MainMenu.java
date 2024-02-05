package menu;

import java.util.Scanner;

import main.BookingService;
import main.Hotel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static main.BookingService.*;

public class MainMenu {

	static Scanner sc = new Scanner(System.in);
	Hotel hotel = new Hotel();
	BookingService boo = new BookingService(hotel);

	

	

	public void MenuApp(Hotel hotel) {

		System.out.println("Bienvenido al hotel Factory");
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
		LocalDate fechaInicio = boo.pedirFechaCliente("Introduzca la fecha de entrada dd/mm/aaaa.");
		LocalDate fechaFin = boo.pedirFechaCliente("Introduzca la fecha de salida dd/mm/aaaa.");
		while (fechaInicio.isAfter(fechaFin)) {
			System.out.println("La fecha de salida no debe ser mas antigua a la fecha de entrada");
			fechaInicio = boo.pedirFechaCliente("Introduzca la fecha de entrada dd/mm/aaaa.");
			fechaFin = boo.pedirFechaCliente("Introduzca la fecha de salida dd/mm/aaaa.");
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
			MenuApp(hotel);
		} else {
			System.out.println("Opcion no valida");
			preguntaReserva();
		}
	}
	
	int opcion = 0, cantidadPersonas = 0;

}
