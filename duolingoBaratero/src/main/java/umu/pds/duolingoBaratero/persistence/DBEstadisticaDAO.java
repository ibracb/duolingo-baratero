package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.Estadistica;

/**
 * Clase que implementa el acceso a la base de datos para las estadisticas.
 */
public class DBEstadisticaDAO extends DBEntityDAO<Estadistica> {

	/**
	 * Mensaje de error para la creación de una estadística.
	 */
	private static final String ERROR_MESSAGE_CREATION = "Exception creating estadistica";
	
	/**
	 * Mensaje de error para la eliminación de una estadística.
	 */
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin estadistica";
	
	/**
	 * Mensaje de error para la actualización de una estadística.
	 */
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating estadistica";
	
	/**
	 * Mensaje de error para la obtención de una estadística.
	 */
	private static final String ERROR_MESSAGE_GET = "Exception getting estadistica";
	
	/**
	 * Mensaje de error para la obtención de todas las estadísticas.
	 */
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all estadisticas";
	
	/**
	 * Consulta para obtener todas las estadísticas.
	 */
	private static final String QUERY_GET_ALL = "SELECT model FROM Estadistica model";

	/**
	 * Instancia única de la clase DBEstadisticaDAO.
	 */
	private static DBEstadisticaDAO unicaInstancia;

	/**
	 * Método para obtener la instancia única de DBEstadisticaDAO.
	 * 
	 * @return La instancia única de DBEstadisticaDAO.
	 */
	public static DBEstadisticaDAO getDBEstadisticaDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBEstadisticaDAO();
		}
		return unicaInstancia;
	}

	/**
	 * Constructor privado para evitar la creación de instancias fuera de la clase.
	 */
	private DBEstadisticaDAO() {
		super();
	}

	@Override
	protected Class<Estadistica> getEntityClass() {
		return Estadistica.class;
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