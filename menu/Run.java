package menu;

import main.*;
import main.Habitaciones.*;

import java.time.LocalDate;

public class Run {
	MainMenu menu = new MainMenu();

	public static void main(String[] args) {
		Habitaciones habitacion1 = new Habitaciones(1, TipoCama.INDIVIDUAL, Categoria.NORMAL, estadoHabitacion.LIBRE);
		Habitaciones habitacion2 = new Habitaciones(1, TipoCama.INDIVIDUAL, Categoria.NORMAL, estadoHabitacion.LIBRE);

		Habitaciones habitacion3 = new Habitaciones(2, TipoCama.DOBLE, Categoria.BUSINESS, estadoHabitacion.LIBRE);
		Habitaciones habitacion4 = new Habitaciones(2, TipoCama.DOBLE, Categoria.BUSINESS, estadoHabitacion.LIBRE);

		Habitaciones habitacion5 = new Habitaciones(3, TipoCama.TRIPLE, Categoria.SUPERIOR, estadoHabitacion.LIBRE);
		Habitaciones habitacion6 = new Habitaciones(3, TipoCama.TRIPLE, Categoria.SUPERIOR, estadoHabitacion.LIBRE);

		Clientes cliente1 = new Clientes("Alfonso", "Perez", "854856387G", 56);
		Clientes cliente2 = new Clientes("Pedro", "dede", "736458376H", 89);
		Reservas reserva1 = new Reservas();
		reserva1.setHabitacion(null);
		reserva1.setFechaInicio(LocalDate.of(2024, 1, 19));
		reserva1.setFechaFin(LocalDate.of(2024, 1, 23));

		Reservas reserva2 = new Reservas();
		reserva2.setHabitacion(null);
		reserva2.setFechaInicio(LocalDate.of(2024, 1, 16));
		reserva2.setFechaFin(LocalDate.of(2024, 1, 18));

		Reservas reserva3 = new Reservas();
		// reserva3.setHabitacion(null);
		reserva3.setFechaInicio(LocalDate.of(2024, 1, 16));
		reserva3.setFechaFin(LocalDate.of(2024, 1, 18));

		Reservas reserva4 = new Reservas();
		reserva4.setHabitacion(null);
		reserva4.setFechaInicio(LocalDate.of(2024, 1, 18));
		reserva4.setFechaFin(LocalDate.of(2024, 1, 19));

		Reservas reserva5 = new Reservas();
		reserva5.setHabitacion(null);
		reserva5.setFechaInicio(LocalDate.of(2024, 1, 16));
		reserva5.setFechaFin(LocalDate.of(2024, 1, 18));

		Hotel h = new Hotel();

		h.registrarCliente(cliente1);
		h.registrarCliente(cliente2);
		h.agregarHabitacion(habitacion1);
		h.agregarHabitacion(habitacion2);
		h.agregarHabitacion(habitacion3);
		h.agregarHabitacion(habitacion4);
		h.agregarHabitacion(habitacion5);
		h.agregarHabitacion(habitacion6);

		h.agregarReserva(reserva1);
		h.agregarReserva(reserva2);
		h.agregarReserva(reserva3);
		h.agregarReserva(reserva4);
		h.agregarReserva(reserva5);

		MainMenu menu = new MainMenu();
		menu.MenuApp(h);

	}

}
