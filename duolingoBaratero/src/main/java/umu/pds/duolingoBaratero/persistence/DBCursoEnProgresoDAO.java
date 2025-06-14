package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;

public class DBCursoEnProgresoDAO extends DBEntityDAO<CursoEnProgreso> {

	private static final String ERROR_MESSAGE_CREATION = "Exception creating curso en progreso";
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin curso en progreso";
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating curso en progreso";
	private static final String ERROR_MESSAGE_GET = "Exception getting curso en progreso";
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all curso en progreso";
	private static final String QUERY_GET_ALL = "SELECT model FROM CursosEnProgreso model";

	private static DBCursoEnProgresoDAO unicaInstancia;

	public static DBCursoEnProgresoDAO getDBCursoEnProgresoDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBCursoEnProgresoDAO();
		}
		return unicaInstancia;
	}

	private DBCursoEnProgresoDAO() {
		super();
	}

	@Override
	protected Class<CursoEnProgreso> getEntityClass() {
		return CursoEnProgreso.class;
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