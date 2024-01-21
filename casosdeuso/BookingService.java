package casosdeuso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import main.Clientes;
import main.Habitaciones;

import main.Hotel;

import static main.Hotel.*;
import main.Reservas;

public class BookingService {

    public BookingService(Hotel hotel) {
        this.hotel = hotel;
    }

    public void consultarDisponibilidad(LocalDate fechaInicio, LocalDate fechaFin, int personas) {
        if (fechaInicio.isBefore(fechaFin)) {
            if (personas < 1 || personas > 3) {
                System.out.println("No hay habitaciones con esas caracteristicas");
                
            } else {
            	ArrayList<Habitaciones> habi = filtroHabitaciones(hotel.listaHabitaciones);
                for (Habitaciones h : habi) {
                    if (h.getTipo().equals(obtenerCategoria(personas))) {
                    	System.out.println("Habitación disponible - Categoría: " + h.getCategoria() + ", Tipo: "
                                + h.getTipo() + ", Estado: " + h.getEstado());
                    }
                }
            }

        } else {
            System.out.println("La fecha de inicio no puede ser mayor a la fecha de fin");
        }

    }
    public ArrayList<Habitaciones> filtroHabitaciones(ArrayList<Habitaciones> habitaciones) {
    	for(int i = 0 ;i<hotel.listaHabitaciones.size();i++) {
    		for(Reservas r : hotel.listaReservas) {
    			if(hotel.listaHabitaciones.get(i).equals(r.getHabitacion())) {
    				habitaciones.remove(i);
    			}

    		}
    		
    	}
    	return habitaciones;
    }

    public String obtenerCategoria(int persona) {

        switch (persona) {
            case 1:
                return "INDIVIDUAL";
            case 2:
                return "DOBLE";
            case 3:
                return "TRIPLE";

        }
        return null;
    }

    private Hotel hotel;

}
