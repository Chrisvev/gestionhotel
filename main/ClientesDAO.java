package main;

import java.util.List;

public interface ClientesDAO {
	
	public Clientes obtenerPorDni(String dni);
	public void guardar(Clientes c);
	public void eliminar(Clientes c);
	public List<Clientes> obtenerTodo();
	public void modificar(Clientes c);
	public void eliminarDatos();

}
