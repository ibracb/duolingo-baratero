package umu.pds.duolingoBaratero.persistence;

public enum PDSFactoriaDAO {
	
	INSTANCE;
	
	public PDSUsuarioDAO getUsuarioDAO() {
		return PDSUsuarioDAO.getInstance();
	}
	
	public PDSCursoPlantillaDAO getCursoPlantillaDAO() {
		return PDSCursoPlantillaDAO.getInstance();
	}
	
	public PDSCursoEnProgresoDAO getCursoEnProgresoDAO() {
		return PDSCursoEnProgresoDAO.getInstance();
	}
	
}
