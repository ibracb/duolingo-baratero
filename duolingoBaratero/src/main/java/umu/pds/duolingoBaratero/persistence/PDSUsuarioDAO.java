package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.Usuario;

public class PDSUsuarioDAO extends PDSEntidadDAO<Usuario> {
	
	private static PDSUsuarioDAO instance;
	
	public static PDSUsuarioDAO getInstance() {
		if (instance == null) {
			instance = new PDSUsuarioDAO();
		}
		return instance;
	}
	
	@Override
	protected Class<Usuario> getEntityClass() {
		return Usuario.class;
	}

	@Override
	protected String getCreateExceptionMessage() {
		return "Exception creating user";
	}

	@Override
	protected String getUpdateExceptionMessage() {
		return "Exception updating user";
	}

	@Override
	protected String getDeleteExceptionMessage() {
		return "Exception deleting user";
	}

	@Override
	protected String getGetExceptionMessage() {
		return "Exception getting user";
	}

	@Override
	protected String getGetAllExceptionMessage() {
		return "Exception getting all users";
	}

	@Override
	protected String getAllQuery() {
		return "SELECT u FROM Usuario u";
	}
	
}
