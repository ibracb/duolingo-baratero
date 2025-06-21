package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.Pregunta;

/**
 * Clase que implementa el acceso a datos para la entidad Pregunta.
 */
public class DBPreguntaDAO extends DBEntityDAO<Pregunta> {

	/**
	 * Mensaje de error para la creación de una pregunta.
	 */
	private static final String ERROR_MESSAGE_CREATION = "Exception creating pregunta";
	
	/**
	 * Mensaje de error para la eliminación de una pregunta.
	 */
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin  pregunta";
	
	/**
	 * Mensaje de error para la actualización de una pregunta.
	 */
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating  pregunta";
	
	/**
	 * Mensaje de error para la obtención de una pregunta.
	 */
	private static final String ERROR_MESSAGE_GET = "Exception getting  pregunta";
	
	/**
	 * Mensaje de error para la obtención de todas las preguntas.
	 */
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all  pregunta";
	
	/**
	 * Consulta para obtener todas las preguntas.
	 */
	private static final String QUERY_GET_ALL = "SELECT model FROM Pregunta model";

	/**
	 * Instancia única de la clase DBPreguntaDAO.
	 */
	private static DBPreguntaDAO unicaInstancia;

	/**
	 * Método para obtener la instancia única de DBPreguntaDAO.
	 * 
	 * @return Instancia única de DBPreguntaDAO.
	 */
	public static DBPreguntaDAO getDBPreguntaDAOO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBPreguntaDAO();
		}
		return unicaInstancia;
	}

	/**
	 * Constructor privado para evitar la creación de instancias externas.
	 */
	private DBPreguntaDAO() {
		super();
	}

	@Override
	protected Class<Pregunta> getEntityClass() {
		return Pregunta.class;
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