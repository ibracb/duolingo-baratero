package umu.pds.duolingoBaratero.persistence;

public enum PDSFactoriaDAO {
	
	INSTANCE;
	
	public PDSUsuarioDAO getUsuarioDAO() {
		return new PDSUsuarioDAO();
	}
	
	public PDSCursoPlantillaDAO getCursoPlantillaDAO() {
		return new PDSCursoPlantillaDAO();
	}
	
	public PDSCursoEnProgresoDAO getCursoEnProgresoDAO() {
		return new PDSCursoEnProgresoDAO();
	}
	
}
