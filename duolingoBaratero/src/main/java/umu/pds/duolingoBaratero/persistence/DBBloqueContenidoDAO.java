package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.BloqueContenido;

/**
 * Clase que implementa el acceso a datos para la entidad BloqueContenido.
 */
public class DBBloqueContenidoDAO extends DBEntityDAO<BloqueContenido> {

	/**
	 * Mensaje de error para la creación de un bloque de contenido.
	 */
	private static final String ERROR_MESSAGE_CREATION = "Exception creating bloque de contenido";
	
	/**
	 * Mensaje de error para la eliminación de un bloque de contenido.
	 */
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin  bloque de contenido";
	
	/**
	 * Mensaje de error para la actualización de un bloque de contenido.
	 */
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating  bloque de contenido";
	
	/**
	 * Mensaje de error para la obtención de un bloque de contenido.
	 */
	private static final String ERROR_MESSAGE_GET = "Exception getting  bloque de contenido";
	
	/**
	 * Mensaje de error para la obtención de todos los bloques de contenido.
	 */
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all  bloque de contenido";
	
	/**
	 * Consulta para obtener todos los bloques de contenido.
	 */
	private static final String QUERY_GET_ALL = "SELECT model FROM BloqueContenido model";

	/**
	 * Instancia única de la clase DBBloqueContenidoDAO.
	 */
	private static DBBloqueContenidoDAO unicaInstancia;

	/**
	 * Método para obtener la instancia única de DBBloqueContenidoDAO.
	 * 
	 * @return La instancia única de DBBloqueContenidoDAO.
	 */
	public static DBBloqueContenidoDAO getDBBloqueContenidoDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBBloqueContenidoDAO();
		}
		return unicaInstancia;
	}

	/**
	 * Constructor privado para evitar la creación de instancias fuera de la clase.
	 */
	private DBBloqueContenidoDAO() {
		super();
	}

	@Override
	protected Class<BloqueContenido> getEntityClass() {
		return BloqueContenido.class;
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