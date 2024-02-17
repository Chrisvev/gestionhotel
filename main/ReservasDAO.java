package main;

import java.util.List;

public interface ReservasDAO {
	
	public Reservas obtenerPorDni(String dni);
	public void guardar(Reservas c);
	public void eliminar(Reservas c);
	public List<Reservas> obtenerTodo();
	public void modificar(Reservas c);
	public void eliminarDatos();

}
