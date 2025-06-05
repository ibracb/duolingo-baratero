package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class DBCursoPlantillaDAO extends DBEntityDAO<CursoPlantilla> {

	private static final String ERROR_MESSAGE_CREATION = "Exception creating curso plantilla";
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin curso plantilla";
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating curso plantilla";
	private static final String ERROR_MESSAGE_GET = "Exception getting curso plantilla";
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all plantilla";
	private static final String QUERY_GET_ALL = "SELECT u FROM cursos_plantilla u";

	private static DBCursoPlantillaDAO unicaInstancia;

	public DBCursoPlantillaDAO getDBUsuarioDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBCursoPlantillaDAO();
		}
		return unicaInstancia;
	}

	private DBCursoPlantillaDAO() {
		super();
	}

	@Override
	protected Class<CursoPlantilla> getEntityClass() {
		return CursoPlantilla.class;
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