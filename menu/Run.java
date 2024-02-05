package menu;

import main.*;
import main.Habitaciones.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Run {

	static Scanner sc = new Scanner(System.in);
	Hotel hotel = new Hotel();
	BookingService boo = new BookingService(hotel);
	

	public static void main(String[] args) {
		
		
		Menu me = new Menu();
		me.MenuApp();
	}

	
}
