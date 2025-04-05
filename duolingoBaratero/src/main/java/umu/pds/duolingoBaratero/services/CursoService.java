package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.persistence.PDSEntidadDAO;

public enum CursoService {
	INSTANCE;
	
	private PDSEntidadDAO<CursoPlantilla> plantillaDAO;
	private PDSEntidadDAO<CursoEnProgreso> progresoDAO;
	
	private CursoService() {
		//this.plantillaDAO = PDSFactoriaDAO.INSTANCE.getCursoPlantillaDAO();	por ahora, no inicializamos el DAO para evitar excepciones
		//this.progresoDAO = PDSFactoriaDAO.INSTANCE.getCursoEnProgresoDAO();	por ahora, no inicializamos el DAO para evitar excepciones
	}
	
}
