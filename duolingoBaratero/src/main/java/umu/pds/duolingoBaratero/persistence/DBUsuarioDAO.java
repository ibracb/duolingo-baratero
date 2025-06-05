package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.Usuario;

public class DBUsuarioDAO extends DBEntityDAO<Usuario> {

	private static final String ERROR_MESSAGE_CREATION = "Exception creating user";
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin user";
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating user";
	private static final String ERROR_MESSAGE_GET = "Exception getting user";
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all users";
	private static final String QUERY_GET_ALL = "SELECT u FROM Usuario u";

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