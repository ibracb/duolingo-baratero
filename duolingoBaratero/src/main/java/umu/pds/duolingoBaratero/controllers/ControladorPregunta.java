package umu.pds.duolingoBaratero.controllers;

import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.services.ServicioPregunta;

public class ControladorPregunta {

	private final ServicioPregunta servicio;

	public ControladorPregunta(ServicioPregunta servicio) {
		this.servicio = servicio;
	}

	public JPanel[] generarLeccion(long bloqueContenido) {
		return servicio.generarLeccion(bloqueContenido);
	}

	public Set<Pregunta> obtenerPreguntasDelBloque(CursoEnProgreso curso) {
		return servicio.obtenerPreguntasDelBloque(curso);
	}

	public boolean procesarRespuesta(Pregunta pregunta, String respuestaUsuario) {
		return servicio.procesarRespuesta(pregunta, respuestaUsuario);
	}

	public int obtenerNumPreguntas(long bloqueContenido) {
		return servicio.obtenerNumPreguntas(bloqueContenido);
	}

	public void guardarPreguntas(List<Pregunta> preguntas, CursoPlantilla curso) {
		servicio.guardarPreguntas(preguntas, curso);
	}
	
	public void incrementarErrores(Pregunta pregunta) {
		servicio.incrementarErrores(pregunta);
	}
	
}
