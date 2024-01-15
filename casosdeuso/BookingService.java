package casosdeuso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import main.Clientes;
import main.Habitaciones;
import static main.Hotel.*;
import main.Reservas;

public class BookingService {

    private List<Habitaciones> habitaciones;
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        int opcion = 0;
       

        opcion = sc.nextInt();
        switch (opcion) {
            case 1 : 

            break;
            case 2 : 

            break;
            default : {
                System.out.println("Opcion no valida");
                menu();
            }
        }
    }

    //Metodo para gestionar las reservas
    public List<Habitaciones> consultarDisponibilidad(Date fechaInicio, Date fechaFin, int numPersonas) {

        return new ArrayList<>();
    }

    //Metodo para reservar habitaciones
    public Reservas reservarHabitacion(Clientes cliente, Habitaciones habitacion, Date fechaInicio, Date fechaFin) {

        //Una vez realizada la reserva se cambia la habitacion a ocupada
        //habitacion.setEstado(Habitaciones.EstadoHabitacion.OCUPADO);
        return new Reservas(cliente, habitacion, fechaInicio, fechaFin);
    }
}