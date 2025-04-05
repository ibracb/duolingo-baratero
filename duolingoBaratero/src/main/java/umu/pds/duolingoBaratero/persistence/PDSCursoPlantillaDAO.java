package umu.pds.duolingoBaratero.persistence;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class PDSCursoPlantillaDAO extends PDSEntidadDAO<CursoPlantilla> {
	
	@Override
	protected Class<CursoPlantilla> getEntityClass() {
		return CursoPlantilla.class;
	}

	@Override
	protected String getCreateExceptionMessage() {
		return "Exception creating plantilla";
	}

	@Override
	protected String getUpdateExceptionMessage() {
		return "Exception updating plantilla";
	}

	@Override
	protected String getDeleteExceptionMessage() {
		return "Exception deleting plantilla";
	}

	@Override
	protected String getGetExceptionMessage() {
		return "Exception getting plantilla";
	}

	@Override
	protected String getGetAllExceptionMessage() {
		return "Exception getting all plantillas";
	}

	@Override
	protected String getAllQuery() {
		return "SELECT p FROM CursoPlantilla p";
	}
}
