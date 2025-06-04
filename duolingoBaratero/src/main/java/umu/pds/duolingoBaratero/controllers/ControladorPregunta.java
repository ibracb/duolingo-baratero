package umu.pds.duolingoBaratero.controllers;

import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;

public enum ControladorPregunta {
	INSTANCE;
	
	// ------RENDERIZACION PREGUNTAS--------

	public JPanel[] generarLeccion(long bloqueContenido) {
		return new JPanel[0];
	}

	public Set<Pregunta> getPreguntasDeBloqueContenido(CursoEnProgreso curso) {
		return curso.getPreguntasBloqueContenido();
	}
	
	// ------Procesamiento preguntas y respuestas----------

	public boolean procesarRespuesta(Pregunta pregunta, String respuestaUsuario) {
		// TODO Si la respuesta es correcta
		// Hacer algo si es falsa hacer algo
		boolean respuestaCorrecta = pregunta.esRespuestaCorrecta(respuestaUsuario);
		if (respuestaCorrecta) {

		}
		return respuestaCorrecta;
	}

	public int getNumPreguntas(long bloqueContenido) {
		return RepositorioCurso.INSTANCE.obtenerBloqueContenido(bloqueContenido).getNumPreguntas();
	}
	
	public void guardarPreguntas(List<Pregunta> preguntas, CursoPlantilla curso) {

	}


}
