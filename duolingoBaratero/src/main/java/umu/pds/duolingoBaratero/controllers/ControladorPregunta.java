package umu.pds.duolingoBaratero.controllers;

import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.services.ServicioPregunta;

/**
 * Controlador para gestionar preguntas y lecciones en el sistema.
 */
public class ControladorPregunta {

	private final ServicioPregunta servicio;

	/**
	 * Constructor del controlador.
	 * 
	 * @param servicio Servicio que maneja la lógica relacionada con preguntas.
	 */
	public ControladorPregunta(ServicioPregunta servicio) {
		this.servicio = servicio;
	}

	/**
	 * Genera una lección basada en el bloque de contenido especificado.
	 * 
	 * @param bloqueContenido Identificador del bloque de contenido.
	 * @return Array de JPanel con la lección generada.
	 */
	public JPanel[] generarLeccion(long bloqueContenido) {
		return servicio.generarLeccion(bloqueContenido);
	}

	/**
	 * Obtiene las preguntas asociadas al bloque actual del curso en progreso.
	 * 
	 * @param curso Curso en progreso.
	 * @return Conjunto de preguntas del bloque.
	 */
	public Set<Pregunta> obtenerPreguntasDelBloque(CursoEnProgreso curso) {
		return servicio.obtenerPreguntasDelBloque(curso);
	}

	/**
	 * Procesa la respuesta del usuario a una pregunta determinada.
	 * 
	 * @param pregunta        Pregunta a responder.
	 * @param respuestaUsuario Respuesta dada por el usuario.
	 * @return true si la respuesta es correcta, false si no.
	 */
	public boolean procesarRespuesta(Pregunta pregunta, String respuestaUsuario) {
		return servicio.procesarRespuesta(pregunta, respuestaUsuario);
	}

	/**
	 * Obtiene el número total de preguntas en un bloque de contenido.
	 * 
	 * @param bloqueContenido Identificador del bloque.
	 * @return Número de preguntas.
	 */
	public int obtenerNumPreguntas(long bloqueContenido) {
		return servicio.obtenerNumPreguntas(bloqueContenido);
	}

	/**
	 * Guarda una lista de preguntas asociadas a una plantilla de curso.
	 * 
	 * @param preguntas Lista de preguntas a guardar.
	 * @param curso     Curso plantilla al que se asocian las preguntas.
	 */
	public void guardarPreguntas(List<Pregunta> preguntas, CursoPlantilla curso) {
		servicio.guardarPreguntas(preguntas, curso);
	}
}
