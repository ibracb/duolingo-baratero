package umu.pds.duolingoBaratero.persistence;

import jakarta.persistence.EntityManager;
import umu.pds.duolingoBaratero.models.Usuario;

/**
 * Clase que implementa el patrón DAO para la entidad Usuario.
 */
public class DBUsuarioDAO extends DBEntityDAO<Usuario> {

	/**
	 * Mensaje de error para la creación de un usuario.
	 */
	private static final String ERROR_MESSAGE_CREATION = "Exception creating user";
	
	/**
	 * Mensaje de error para la eliminación de un usuario.
	 */
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin user";
	
	/**
	 * Mensaje de error para la actualización de un usuario.
	 */
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating user";
	
	/**
	 * Mensaje de error para la obtención de un usuario.
	 */
	private static final String ERROR_MESSAGE_GET = "Exception getting user";
	
	/**
	 * Mensaje de error para la obtención de todos los usuarios.
	 */
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all users";
	
	/**
	 * Consulta para obtener todos los usuarios.
	 */
	private static final String QUERY_GET_ALL = "SELECT model FROM Usuario model";

	/**
	 * Instancia única del DAO de Usuario (Singleton).
	 */
	private static DBUsuarioDAO unicaInstancia;

	/**
	 * Método para obtener la instancia única del DAO de Usuario.
	 * 
	 * @return Instancia única de DBUsuarioDAO.
	 */
	public static DBUsuarioDAO getDBUsuarioDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBUsuarioDAO();
		}
		return unicaInstancia;
	}

	/**
	 * Constructor privado para evitar la creación de instancias fuera de la clase.
	 */
	private DBUsuarioDAO() {
		super();
	}
	
	/**
	 * Verifica si un usuario existe en la base de datos por su ID.
	 * 
	 * @param id El ID del usuario a verificar.
	 * @return true si el usuario existe, false en caso contrario.
	 */
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
	    } finally {
	        em.close();
	    }
	}
	
	/**
	 * Verifica si un usuario existe en la base de datos por su correo electrónico.
	 * 
	 * @param correo El correo electrónico del usuario a verificar.
	 * @return true si el usuario existe, false en caso contrario.
	 */
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
	    } finally {
	        em.close();
	    }
	}
	
	/**
	 * Obtiene un usuario de la base de datos por su correo electrónico.
	 * 
	 * @param correo El correo electrónico del usuario a obtener.
	 * @return El usuario correspondiente al correo, o null si no existe.
	 */
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
	    } finally {
	        em.close();
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