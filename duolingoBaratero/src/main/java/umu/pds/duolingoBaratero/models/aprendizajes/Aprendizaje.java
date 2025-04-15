package umu.pds.duolingoBaratero.models.aprendizajes;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;

public interface Aprendizaje {
	
	public void setPreguntasByEstrategia(CursoEnProgreso progreso);
	
	public AprendizajeSeleccionado getSeleccion();
	
}
