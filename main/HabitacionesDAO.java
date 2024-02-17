package main;

import java.util.List;

public interface HabitacionesDAO {
	
	public Habitaciones obtenerId(int id);
	public void guardar(Habitaciones h);
	public void eliminar(Habitaciones h);
	public List<Habitaciones> obtenerTodo();
	public void modificar(Habitaciones h);
	public void eliminarDatos();

}
