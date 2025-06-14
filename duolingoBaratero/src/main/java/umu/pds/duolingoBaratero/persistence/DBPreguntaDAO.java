package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.Pregunta;

public class DBPreguntaDAO extends DBEntityDAO<Pregunta> {

	private static final String ERROR_MESSAGE_CREATION = "Exception creating pregunta";
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin  pregunta";
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating  pregunta";
	private static final String ERROR_MESSAGE_GET = "Exception getting  pregunta";
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all  pregunta";
	private static final String QUERY_GET_ALL = "SELECT model FROM Pregunta model";

	private static DBPreguntaDAO unicaInstancia;

	public static DBPreguntaDAO getDBPreguntaDAOO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBPreguntaDAO();
		}
		return unicaInstancia;
	}

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