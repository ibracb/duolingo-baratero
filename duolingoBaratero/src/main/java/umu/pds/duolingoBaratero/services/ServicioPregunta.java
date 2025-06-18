package umu.pds.duolingoBaratero.services;

import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;

/**
 * Servicio que gestiona la lógica relacionada con preguntas y lecciones.
 */
public class ServicioPregunta {

	/**
	 * Genera un array de paneles (JPanel) que conforman la lección de un bloque de contenido.
	 * 
	 * @param bloqueContenido ID del bloque de contenido.
	 * @return Array de JPanel que representa la lección.
	 */
	public JPanel[] generarLeccion(long bloqueContenido) {
		// Implementación futura
		return new JPanel[0];
	}

	/**
	 * Obtiene el conjunto de preguntas asociadas al bloque actual del curso en progreso.
	 * 
	 * @param curso Curso en progreso.
	 * @return Conjunto de preguntas del bloque.
	 */
	public Set<Pregunta> obtenerPreguntasDelBloque(CursoEnProgreso curso) {
		return curso.getPreguntasBloqueContenido();
	}

	/**
	 * Procesa la respuesta del usuario para una pregunta dada y devuelve si es correcta.
	 * 
	 * @param pregunta       Pregunta a evaluar.
	 * @param respuestaUsuario Respuesta proporcionada por el usuario.
	 * @return true si la respuesta es correcta, false si no.
	 */
	public boolean procesarRespuesta(Pregunta pregunta, String respuestaUsuario) {
		boolean respuestaCorrecta = pregunta.esRespuestaCorrecta(respuestaUsuario);
		// TODO: realizar acciones adicionales según resultado
		return respuestaCorrecta;
	}

	/**
	 * Obtiene el número de preguntas de un bloque de contenido.
	 * 
	 * @param bloqueContenido ID del bloque de contenido.
	 * @return Número de preguntas del bloque.
	 */
	public int obtenerNumPreguntas(long bloqueContenido) {
		return RepositorioCurso.INSTANCE
			.obtenerBloqueContenido(bloqueContenido)
			.getNumPreguntas();
	}

	/**
	 * Guarda una lista de preguntas asociadas a una plantilla de curso.
	 * 
	 * @param preguntas Lista de preguntas a guardar.
	 * @param curso     Plantilla de curso a la que se asocian las preguntas.
	 */
	public void guardarPreguntas(List<Pregunta> preguntas, CursoPlantilla curso) {
		// TODO: implementación pendiente
	}
}
