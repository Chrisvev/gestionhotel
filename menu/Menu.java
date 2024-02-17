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

	boolean bandera = true;

	private BookingService boo;
	Hotel h;

	public Menu() {
		
		Habitaciones habitacion1 = new Habitaciones(1, TipoCama.INDIVIDUAL, Categoria.NORMAL, estadoHabitacion.LIBRE);
		Habitaciones habitacion2 = new Habitaciones(2, TipoCama.INDIVIDUAL, Categoria.NORMAL, estadoHabitacion.LIBRE);

		Habitaciones habitacion3 = new Habitaciones(3, TipoCama.DOBLE, Categoria.BUSINESS, estadoHabitacion.LIBRE);
		Habitaciones habitacion4 = new Habitaciones(4, TipoCama.DOBLE, Categoria.BUSINESS, estadoHabitacion.LIBRE);

		Habitaciones habitacion5 = new Habitaciones(5, TipoCama.TRIPLE, Categoria.SUPERIOR, estadoHabitacion.LIBRE);
		Habitaciones habitacion6 = new Habitaciones(6, TipoCama.TRIPLE, Categoria.SUPERIOR, estadoHabitacion.LIBRE);
		
		

		h = new Hotel();
		h.rdao.eliminarDatos();
		h.cdao.eliminarDatos();
		h.hdao.eliminarDatos();
		h.agregarHabitacion(habitacion1);
		h.agregarHabitacion(habitacion2);
		h.agregarHabitacion(habitacion3);
		h.agregarHabitacion(habitacion4);
		h.agregarHabitacion(habitacion5);
		h.agregarHabitacion(habitacion6);
		boo = new BookingService(h);

	}

	public void MenuApp() {
		while (bandera) {
			System.out.println("Hotel Factory");
			System.out.println(
					"Que operacion desea realizar\n1. Consultar disponibilidad y Reservar habitacion\n2. CheckIn\n3. CheckOut\n4. Cancelar reserva\n5. Salir ");
			System.out.println("-----------------------");
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				consultarHabitaciones();
				break;
			case 2:
				String codigo = boo.obtenerCodigoReserva();
				boo.checkIn(codigo);
				break;
			case 3:
				String dnicliente = boo.pedirDNIUsuario();
				boo.checkOut(dnicliente);

				break;
			case 4:
				String codigores = boo.obtenerCodigoReserva();
				boo.cancelarReserva(codigores);

				break;
			case 5:
				bandera=false;

				break;
			}
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
