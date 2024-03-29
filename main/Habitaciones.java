package main;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Habitaciones")
public class Habitaciones {

	public Habitaciones() {
	}

	public Habitaciones(int numero, TipoCama numeroCamas, Categoria categoria, estadoHabitacion estado) {
		this.numero = numero;
		this.numeroCamas = numeroCamas;
		this.categoria = categoria;
		this.estado = estado;
	}

	public enum estadoHabitacion {
		LIBRE, OCUPADO, NO_DISPONIBLE
	}

	public enum Categoria {

		NORMAL(100.0, 50.0, 70.0, Set.of("Servicio adicional", "Television")),
		BUSINESS(150.0, 80.0, 90.0, Set.of("Servicio adicional", "Television", "Desayuno")),
		SUPERIOR(200.0, 100.0, 120.0, Set.of("Servicio adicional", "Television", "Piscina"));

		Categoria(double precioBase, double precioServicios, double precioMinibar, Set<String> serviciosDisponibles) {
			this.precioBase = precioBase;
			this.precioServicios = precioServicios;
			this.precioMinibar = precioMinibar;
			this.serviciosDisponibles = serviciosDisponibles;
		}

		public double getPrecioBase() {
			return precioBase;
		}

		public double getPrecioServicios() {
			return precioServicios;
		}

		public double getPrecioMinibar() {
			return precioMinibar;
		}

		public double getPrecioTotal() {
			return precioBase + precioServicios + precioMinibar;
		}

		public Set<String> getServiciosDisponibles() {
			return serviciosDisponibles;
		}

		private final double precioBase;
		private final double precioServicios;
		private final double precioMinibar;
		private final Set<String> serviciosDisponibles;
	}

	public enum TipoCama {
		INDIVIDUAL, DOBLE, TRIPLE
	}

	public estadoHabitacion getEstado() {
		return estado;
	}

	public void setEstado(estadoHabitacion estado) {
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoCama getNumeroCamas() {
		return numeroCamas;
	}

	public void setNumeroCamas(TipoCama numeroCamas) {
		this.numeroCamas = numeroCamas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	private Clientes cliente;
	
	
	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}
	

	//No tengo id y hago que al crear la habitacion te genere una id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int numero;
	
	 @Enumerated(EnumType.STRING)
	private TipoCama numeroCamas;
	 
	 @Enumerated(EnumType.STRING)
	private Categoria categoria;
	 
	 @Enumerated(EnumType.STRING)
	private estadoHabitacion estado;
	 
	 
	private int id;

}
