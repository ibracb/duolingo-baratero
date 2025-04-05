package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.persistence.PDSCursoEnProgresoDAO;
import umu.pds.duolingoBaratero.persistence.PDSCursoPlantillaDAO;
import umu.pds.duolingoBaratero.persistence.PDSFactoriaDAO;

public enum CursoService {
	INSTANCE;
	
	private PDSCursoPlantillaDAO plantillaDAO;
	private PDSCursoEnProgresoDAO progresoDAO;
	
	private CursoService() {
		this.plantillaDAO = PDSFactoriaDAO.INSTANCE.getCursoPlantillaDAO();
		this.progresoDAO = PDSFactoriaDAO.INSTANCE.getCursoEnProgresoDAO();
	}
	
}
