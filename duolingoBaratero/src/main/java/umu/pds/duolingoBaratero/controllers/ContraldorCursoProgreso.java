package umu.pds.duolingoBaratero.controllers;


import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;

/**
 * Responsabilidad: manejar el progreso del usuario en un curso
 * (CursoEnProgreso).
 */
public enum ContraldorCursoProgreso {
	INSTANCE;
	
    public CursoEnProgreso getCursoEnProgreso(CursoPlantilla curso, AprendizajeSeleccionado aprendizaje) {
        return new CursoEnProgreso(curso, aprendizaje);
    }

	public boolean isCursoNuevo(CursoEnProgreso curso) {
		if (curso == null) {
			System.out.println("Curso nulo");
			return false;
		}
		return curso.isNuevo();
	}

	public boolean isCursoEnMarcha(CursoEnProgreso curso) {
		return curso.isEnMarcha();
	}

	public boolean isCursoFinalizado(CursoEnProgreso curso) {
		return curso.isFinalizado();
	}

	public long getNumLastBloqueContenido(CursoEnProgreso curso) {
		return curso.getNumLastBloqueContenido();
	}

	public void avanzarBloqueContenido(CursoEnProgreso curso, boolean aprobado) {
		curso.avanzarBloqueActual(aprobado);
	}

	public void reiniciarCurso(CursoEnProgreso curso) {
		curso.reiniciar();
	}
}
