package main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HabitacionesDAOImpl implements HabitacionesDAO {

	private Session session;

	public HabitacionesDAOImpl() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}

	@Override
	public Habitaciones obtenerId(int id) {
		return session.get(Habitaciones.class, id);
	}

	@Override
	public void guardar(Habitaciones h) {
		Transaction tx = session.beginTransaction();
		session.save(h);
		tx.commit();

	}

	@Override
	public void eliminar(Habitaciones h) {
		Transaction tx = session.beginTransaction();
		session.delete(h);
		tx.commit();

	}

	@Override
	public void modificar(Habitaciones h) {
		Transaction tx = session.beginTransaction();
		session.update(h);
		tx.commit();

	}
	
	@Override
    public void eliminarDatos() {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Habitaciones").executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }

    }

	@Override
	public List<Habitaciones> obtenerTodo() {
		return session.createQuery("SELECT h FROM Habitaciones h").getResultList();
	}

}
