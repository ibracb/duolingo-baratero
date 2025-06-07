package umu.pds.duolingoBaratero.persistence;

import jakarta.persistence.EntityManager;
import umu.pds.duolingoBaratero.models.Usuario;

public class DBUsuarioDAO extends DBEntityDAO<Usuario> {

	private static final String ERROR_MESSAGE_CREATION = "Exception creating user";
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin user";
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating user";
	private static final String ERROR_MESSAGE_GET = "Exception getting user";
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all users";
	private static final String QUERY_GET_ALL = "SELECT model FROM Usuario model";

	private static DBUsuarioDAO unicaInstancia;

	public static DBUsuarioDAO getDBUsuarioDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBUsuarioDAO();
		}
		return unicaInstancia;
	}

	private DBUsuarioDAO() {
		super();
	}
	
	public boolean existeUsuario(long id) {
	    EntityManager em = EntityManagerHelper.getEntityManager();

	    try {
	        Long count = em.createQuery(
	                "SELECT COUNT(u) FROM Usuario u WHERE u.id = :id", Long.class)
	            .setParameter("id", id)
	            .getSingleResult();

	        return count != null && count > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } 
	}
	
	public boolean existeUsuario(String correo) {
	    EntityManager em = EntityManagerHelper.getEntityManager();

	    try {
	        Long count = em.createQuery(
	                "SELECT COUNT(u) FROM Usuario u WHERE u.correo= :correo", Long.class)
	            .setParameter("correo", correo)
	            .getSingleResult();

	        return count != null && count > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public Usuario get(String correo) {
	    EntityManager em = EntityManagerHelper.getEntityManager();

	    try {
	        return em.createQuery(
	                "SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
	            .setParameter("correo", correo)
	            .getSingleResult();
	    } catch (jakarta.persistence.NoResultException e) {
	        return null; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	@Override
	protected Class<Usuario> getEntityClass() {
		return Usuario.class;
	}

	@Override
	protected String getCreateExceptionMessage() {
		return ERROR_MESSAGE_CREATION;
	}

	@Override
	protected String getUpdateExceptionMessage() {
		return ERROR_MESSAGE_UPDATE;
	}

	@Override
	protected String getDeleteExceptionMessage() {
		return ERROR_MESSAGE_DELETE;
	}

	@Override
	protected String getGetExceptionMessage() {
		return ERROR_MESSAGE_GET;
	}

	@Override
	protected String getGetAllExceptionMessage() {
		return ERROR_MESSAGE_GETALL;
	}

	@Override
	protected String getAllQuery() {
		return QUERY_GET_ALL;
	}

}