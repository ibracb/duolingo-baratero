package umu.pds.duolingoBaratero.persistence;

import java.util.List;

import jakarta.persistence.EntityManager;

public abstract class DBEntityDAO<T> implements EntityDAO<T> {

	protected EntityManager em;

	protected DBEntityDAO() {
		em = EntityManagerHelper.getEntityManager();
	}

	@Override
	public void create(T entidad) {
		try {
			em.getTransaction().begin();
			em.persist(entidad);
			em.getTransaction().commit();
		} catch (Exception e) {
			manejarExcepcion(e, getCreateExceptionMessage());
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
		} 
	}

	@Override
	public T get(long id) {
		try {
			return em.find(getEntityClass(), id);
		} catch (Exception e) {
			throw new DAOException(getGetExceptionMessage(), e);
		} 
	}

	@Override
	public List<T> getAll() {
		try {
			return em.createQuery(getAllQuery(), getEntityClass()).getResultList();
		} catch (Exception e) {
			throw new DAOException(getGetAllExceptionMessage(), e);
		} 
	}

	private void manejarExcepcion(Exception e, String message) {
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
		throw new DAOException(message, e);
	}

	protected abstract Class<T> getEntityClass();

	protected abstract String getCreateExceptionMessage();

	protected abstract String getUpdateExceptionMessage();

	protected abstract String getDeleteExceptionMessage();

	protected abstract String getGetExceptionMessage();

	protected abstract String getGetAllExceptionMessage();

	protected abstract String getAllQuery();
}
