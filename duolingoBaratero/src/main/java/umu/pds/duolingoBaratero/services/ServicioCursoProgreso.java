package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;

public class ServicioCursoProgreso {

	public CursoEnProgreso crearCursoEnProgreso(CursoPlantilla curso, Usuario usuario) {
		return new CursoEnProgreso(curso, usuario);
	}

	// FIXME: Boolean true de prueba

	public boolean setAprendizaje(CursoEnProgreso curso, AprendizajeSeleccionado aprendizaje) {
		curso.setAprendizaje(aprendizaje);
		return true;
	}

	public void iniciarCurso(CursoEnProgreso curso) {
		curso.iniciar();
	}

	public boolean esCursoNuevo(CursoEnProgreso curso) {
		return curso != null && curso.isNuevo();
	}

	public boolean esCursoEnMarcha(CursoEnProgreso curso) {
		return curso != null && curso.isEnMarcha();
	}

	public boolean esCursoFinalizado(CursoEnProgreso curso) {
		return curso != null && curso.isFinalizado();
	}

	public long obtenerUltimoBloque(CursoEnProgreso curso) {
		return curso != null ? curso.getNumLastBloqueContenido() : -1;
	}

	public void avanzarBloque(CursoEnProgreso curso, boolean aprobado) {
		if (curso != null) {
			curso.avanzarBloqueActual(aprobado);
		}
	}

	public void reiniciarCurso(CursoEnProgreso curso) {
			curso.reiniciar();
	}

}
