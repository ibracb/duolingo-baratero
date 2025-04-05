package umu.pds.duolingoBaratero.persistence;

import java.util.List;

import jakarta.persistence.EntityManager;

public abstract class PDSEntidadDAO<T> implements EntidadDAO<T> {
	private EntityManager em;

	public PDSEntidadDAO() {
		em = JPAUtils.emf.createEntityManager();
	}
	
	@Override
	public void create(T entidad) {
		try {
			em.getTransaction().begin();
			em.persist(entidad);
			em.getTransaction().commit();
		} catch (Exception e) {
			manejarExcepcion(e, getCreateExceptionMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public void update(T entidad) {
		try {
			em.getTransaction().begin();
			em.merge(entidad);
			em.getTransaction().commit();
		} catch (Exception e) {
			manejarExcepcion(e, getUpdateExceptionMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(long id) {
		try {
			em.getTransaction().begin();
			T entidad = em.find(getEntityClass(), id);
			if (entidad != null) {
				em.remove(entidad);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			manejarExcepcion(e, getDeleteExceptionMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public T get(long id) {
		try {
			em.getTransaction().begin();
			T entidad = em.find(getEntityClass(), id);
			em.getTransaction().commit();
			return entidad;
		} catch (Exception e) {
			manejarExcepcion(e, getGetExceptionMessage());
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		try {
			em.getTransaction().begin();
			List<T> entidades = em.createQuery(getAllQuery(), getEntityClass()).getResultList();
			em.getTransaction().commit();
			return entidades;
		} catch (Exception e) {
			manejarExcepcion(e, getGetAllExceptionMessage());
		} finally {
			em.close();
		}
		return null;
	}
	
	protected abstract Class<T> getEntityClass();
	protected abstract String getCreateExceptionMessage();
	protected abstract String getUpdateExceptionMessage();
	protected abstract String getDeleteExceptionMessage();
	protected abstract String getGetExceptionMessage();
	protected abstract String getGetAllExceptionMessage();
	protected abstract String getAllQuery();

	private void manejarExcepcion(Exception e, String message) {
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
		throw new DAOException(message, e);
	}
}
