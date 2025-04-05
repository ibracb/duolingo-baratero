package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.persistence.PDSEntidadDAO;
import umu.pds.duolingoBaratero.persistence.PDSFactoriaDAO;

public enum CursoService {
	INSTANCE;
	
	private PDSEntidadDAO<CursoPlantilla> plantillaDAO;
	private PDSEntidadDAO<CursoEnProgreso> progresoDAO;
	
	private CursoService() {
		this.plantillaDAO = PDSFactoriaDAO.INSTANCE.getCursoPlantillaDAO();
		this.progresoDAO = PDSFactoriaDAO.INSTANCE.getCursoEnProgresoDAO();
	}
	
}
