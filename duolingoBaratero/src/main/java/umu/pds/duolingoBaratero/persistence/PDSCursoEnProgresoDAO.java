package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;

public class PDSCursoEnProgresoDAO extends PDSEntidadDAO<CursoEnProgreso> {
	
	private static PDSCursoEnProgresoDAO instance;
	
	public static PDSCursoEnProgresoDAO getInstance() {
		if (instance == null) {
			instance = new PDSCursoEnProgresoDAO();
		}
		return instance;
	}
	
	@Override
	protected Class<CursoEnProgreso> getEntityClass() {
		return CursoEnProgreso.class;
	}

	@Override
	protected String getCreateExceptionMessage() {
		return "Exception creating progreso";
	}

	@Override
	protected String getUpdateExceptionMessage() {
		return "Exception updating progreso";
	}

	@Override
	protected String getDeleteExceptionMessage() {
		return "Exception deleting progreso";
	}

	@Override
	protected String getGetExceptionMessage() {
		return "Exception getting progreso";
	}

	@Override
	protected String getGetAllExceptionMessage() {
		return "Exception getting all progresos";
	}

	@Override
	protected String getAllQuery() {
		return "SELECT p FROM CursoEnProgreso p";
	}
	
}
