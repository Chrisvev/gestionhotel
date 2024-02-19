package main;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientesDAOImpl implements ClientesDAO {

	private Session session;

	public ClientesDAOImpl() {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		session = factory.openSession();

	}

	@Override
	public Clientes obtenerPorDni(String dni) {

		return session.get(Clientes.class, dni);

	}

	@Override
	public void guardar(Clientes c) {

		Transaction tx = session.beginTransaction();
		session.save(c);
		tx.commit();

	}

	@Override
	public void eliminar(Clientes c) {
		Transaction tx = session.beginTransaction();
		session.delete(c);
		tx.commit();
	}

	@Override
	public List<Clientes> obtenerTodo() {

		return session.createQuery("SELECT c FROM Clientes c").getResultList();
	}

	@Override
	public void eliminarDatos() {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.createQuery("DELETE FROM Clientes").executeUpdate();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}

	}

	@Override
	public void modificar(Clientes c) {
		Transaction tx = session.beginTransaction();
		session.update(c);
		tx.commit();

	}

}
