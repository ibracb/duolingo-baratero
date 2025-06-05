package umu.pds.duolingoBaratero.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerHelper {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static void cerrar() {
            if (emf.isOpen()) {
                emf.close();
            }
	}
}
