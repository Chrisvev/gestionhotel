package main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class ReservasDAOImpl implements ReservasDAO {
	
	private Session session;
	
	public ReservasDAOImpl() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}

	@Override
	public Reservas obtenerPorDni(String dni) {
		
		return session.get(Reservas.class, dni);
	}

	@Override
	public void guardar(Reservas r) {
			Transaction tx =  session.beginTransaction();
			session.save(r);
			tx.commit();
		
	}

	@Override
	public void eliminar(Reservas r) {
		Transaction tx =  session.beginTransaction();
		session.delete(r);
		tx.commit();
		
		
	}

	@Override
	public List<Reservas> obtenerTodo() {
	
		return session.createQuery("SELECT r FROM Reservas r ").getResultList();
	}
	
	@Override
    public void eliminarDatos() {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Reservas").executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }

    }

	@Override
	public void modificar(Reservas r) {
		Transaction tx = session.beginTransaction();
		session.update(r);
		tx.commit();
		
	}

}
