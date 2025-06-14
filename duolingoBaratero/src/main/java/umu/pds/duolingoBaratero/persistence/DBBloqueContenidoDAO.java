package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.BloqueContenido;

public class DBBloqueContenidoDAO extends DBEntityDAO<BloqueContenido> {

	private static final String ERROR_MESSAGE_CREATION = "Exception creating bloque de contenido";
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin  bloque de contenido";
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating  bloque de contenido";
	private static final String ERROR_MESSAGE_GET = "Exception getting  bloque de contenido";
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all  bloque de contenido";
	private static final String QUERY_GET_ALL = "SELECT model FROM BloqueContenido model";

	private static DBBloqueContenidoDAO unicaInstancia;

	public static DBBloqueContenidoDAO getDBBloqueContenidoDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBBloqueContenidoDAO();
		}
		return unicaInstancia;
	}

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