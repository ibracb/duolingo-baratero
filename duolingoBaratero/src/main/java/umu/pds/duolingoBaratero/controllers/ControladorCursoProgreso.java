package umu.pds.duolingoBaratero.controllers;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.services.ServicioCursoProgreso;

public class ControladorCursoProgreso {

	private final ServicioCursoProgreso servicio;

	public ControladorCursoProgreso(ServicioCursoProgreso servicio) {
		this.servicio = servicio;
	}

	public CursoEnProgreso crearCurso(CursoPlantilla curso, Usuario usuario) {
		return servicio.crearCursoEnProgreso(curso, usuario);
	}

	public boolean configurarCursoProgreso(CursoEnProgreso curso, AprendizajeSeleccionado aprendizaje) {
		servicio.iniciarCurso(curso);
		return servicio.setAprendizaje(curso, aprendizaje);

	}

	public boolean esNuevo(CursoEnProgreso curso) {
		return servicio.esCursoNuevo(curso);
	}

	public boolean estaEnMarcha(CursoEnProgreso curso) {
		return servicio.esCursoEnMarcha(curso);
	}

	public boolean estaFinalizado(CursoEnProgreso curso) {
		return servicio.esCursoFinalizado(curso);
	}

	public void avanzar(CursoEnProgreso curso, boolean aprobado) {
		servicio.avanzarBloque(curso, aprobado);
	}

	public void reiniciar(CursoEnProgreso curso) {
		servicio.reiniciarCurso(curso);
		actualizarCurso(curso);
	}

	/**
	 * Actualiza un curso
	 */
	public boolean actualizarCurso(CursoEnProgreso curso) {
		try {
			return servicio.actualizarCurso(curso);
		} catch (Exception e) {
			System.err.println("Error al actualizar cursoProgreso: " + e.getMessage());
			return false;
		}
	}
}
