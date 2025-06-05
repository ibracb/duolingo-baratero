package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.Estadistica;

public class DBEstadisticaDAO extends DBEntityDAO<Estadistica> {

	private static final String ERROR_MESSAGE_CREATION = "Exception creating estadistica";
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin estadistica";
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating estadistica";
	private static final String ERROR_MESSAGE_GET = "Exception getting estadistica";
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all estadisticas";
	private static final String QUERY_GET_ALL = "SELECT u FROM estadisticas u";

	private static DBEstadisticaDAO unicaInstancia;

	public DBEstadisticaDAO getDBUsuarioDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBEstadisticaDAO();
		}
		return unicaInstancia;
	}

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