package umu.pds.duolingoBaratero.services;

import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;

public class ServicioPregunta {

	public JPanel[] generarLeccion(long bloqueContenido) {
		// Implementación futura
		return new JPanel[0];
	}

	public Set<Pregunta> obtenerPreguntasDelBloque(CursoEnProgreso curso) {
		return curso.getPreguntasBloqueContenido();
	}

	public boolean procesarRespuesta(Pregunta pregunta, String respuestaUsuario) {
		boolean respuestaCorrecta = pregunta.esRespuestaCorrecta(respuestaUsuario);
		// TODO: realizar acciones adicionales según resultado
		return respuestaCorrecta;
	}

	public int obtenerNumPreguntas(long bloqueContenido) {
		return RepositorioCurso.INSTANCE
			.obtenerBloqueContenido(bloqueContenido)
			.getNumPreguntas();
	}

	public void guardarPreguntas(List<Pregunta> preguntas, CursoPlantilla curso) {
		// TODO: implementación pendiente
	}
}
