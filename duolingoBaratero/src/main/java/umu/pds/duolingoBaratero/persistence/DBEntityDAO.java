package umu.pds.duolingoBaratero.persistence;

import java.util.List;

import jakarta.persistence.EntityManager;

/**
 * Clase abstracta que implementa la interfaz EntityDAO para manejar operaciones
 * de creación, actualización, eliminación y obtención de entidades en una base
 * de datos utilizando JPA.
 *
 * @param <T> Tipo de entidad que maneja este DAO.
 */
public abstract class DBEntityDAO<T> implements EntityDAO<T> {


	/**
	 * Constructor protegido para evitar la instanciación directa de esta clase.
	 * Las subclases deben proporcionar una implementación concreta.
	 */
	protected DBEntityDAO() {
	}

	@Override
	public void create(T entidad) {
	    EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entidad);
			em.getTransaction().commit();
		} catch (Exception e) {
			manejarExcepcion(em, e, getCreateExceptionMessage());
		} finally {
	        em.close();
	    }
	}

	@Override
	public void update(T entidad) {
	    EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entidad);
			em.getTransaction().commit();
		} catch (Exception e) {
			manejarExcepcion(em, e, getUpdateExceptionMessage());
		} finally {
	        em.close();
	    }
	}

	@Override
	public void delete(long id) {
	    EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			em.getTransaction().begin();
			T entidad = em.find(getEntityClass(), id);
			if (entidad != null) {
				em.remove(entidad);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			manejarExcepcion(em, e, getDeleteExceptionMessage());
		} finally {
	        em.close();
	    }
	}

	@Override
	public T get(long id) {
	    EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			return em.find(getEntityClass(), id);
		} catch (Exception e) {
			throw new DAOException(getGetExceptionMessage(), e);
		} finally {
			em.close();
		}
	}

	@Override
	public List<T> getAll() {
	    EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			return em.createQuery(getAllQuery(), getEntityClass()).getResultList();
		} catch (Exception e) {
			throw new DAOException(getGetAllExceptionMessage(), e);
		} finally {
			em.close();
		}
	}

	private void manejarExcepcion(EntityManager em, Exception e, String message) {
		if (em != null && em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
		throw new DAOException(message, e);
	}

	/**
	 * Método abstracto que debe ser implementado por las subclases para
	 * proporcionar la clase de entidad que maneja este DAO.
	 *
	 * @return Clase de entidad manejada por este DAO.
	 */
	protected abstract Class<T> getEntityClass();

	/**
	 * Métodos abstractos que deben ser implementados por las subclases para
	 * proporcionar mensajes de excepción específicos para cada operación.
	 */
	protected abstract String getCreateExceptionMessage();

	/**
	 * Método abstracto que debe ser implementado por las subclases para
	 * proporcionar un mensaje de excepción específico para la operación de
	 * actualización.
	 *
	 * @return Mensaje de excepción para la operación de actualización.
	 */
	protected abstract String getUpdateExceptionMessage();

	/**
	 * Método abstracto que debe ser implementado por las subclases para
	 * proporcionar un mensaje de excepción específico para la operación de
	 * eliminación.
	 *
	 * @return Mensaje de excepción para la operación de eliminación.
	 */
	protected abstract String getDeleteExceptionMessage();

	/**
	 * Método abstracto que debe ser implementado por las subclases para
	 * proporcionar un mensaje de excepción específico para la operación de
	 * obtención de una entidad por su ID.
	 *
	 * @return Mensaje de excepción para la operación de obtención.
	 */
	protected abstract String getGetExceptionMessage();

	/**
	 * Método abstracto que debe ser implementado por las subclases para
	 * proporcionar un mensaje de excepción específico para la operación de
	 * obtención de todas las entidades.
	 *
	 * @return Mensaje de excepción para la operación de obtención de todas las
	 *         entidades.
	 */
	protected abstract String getGetAllExceptionMessage();

	/**
	 * Método abstracto que debe ser implementado por las subclases para
	 * proporcionar la consulta SQL que se utilizará para obtener todas las
	 * entidades de este tipo.
	 *
	 * @return Consulta SQL para obtener todas las entidades.
	 */
	protected abstract String getAllQuery();
}
