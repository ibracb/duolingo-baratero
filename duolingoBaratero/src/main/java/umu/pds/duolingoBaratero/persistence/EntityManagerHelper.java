package umu.pds.duolingoBaratero.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase que proporciona un EntityManager para interactuar con la base de datos.
 * Utiliza un EntityManagerFactory para crear instancias de EntityManager.
 */
public class EntityManagerHelper {

	/**
	 * EntityManagerFactory para crear EntityManagers.
	 */
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DB");

	/**
	 * Obtiene una instancia de EntityManager.
	 * 
	 * @return un EntityManager para interactuar con la base de datos.
	 */
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Cierra el EntityManagerFactory.
	 * Debe ser llamado al finalizar la aplicación para liberar recursos.
	 */
	public static void cerrar() {
            if (emf.isOpen()) {
                emf.close();
            }
	}
}
