package main;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * @author Chris
 */
@Entity
@Table (name="reservas")
public class Reservas {
    

    
    public Reservas() {
    	
    }

    
    public Reservas(String codigoReserva, Habitaciones habitacion, LocalDate fechaInicio, LocalDate fechaFin, Clientes cliente) {
        this.codigoReserva = codigoReserva;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente=cliente;
    }

    
    public Habitaciones getHabitacion() {
        return habitacion;
    }

   
    public void setHabitacion(Habitaciones habitacion) {
        this.habitacion = habitacion;
    }

   
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

   
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    public String getCodigoReserva() {
        return codigoReserva;
    }

	public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }
    public Clientes getCliente() {
		return cliente;
	}


	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
    
	@OneToOne(fetch = FetchType.LAZY)
    private Clientes cliente;
	
	@OneToOne(fetch = FetchType.LAZY)
    private Habitaciones habitacion;
	
	
	@Column(length = 250)
    @Id
    private String codigoReserva;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
	
	

}

