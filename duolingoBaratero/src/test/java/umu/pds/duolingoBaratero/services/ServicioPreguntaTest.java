package umu.pds.duolingoBaratero.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import umu.pds.duolingoBaratero.models.*;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;

import javax.swing.JPanel;

import java.util.List;


public class ServicioPreguntaTest {

	private ServicioPregunta servicio;

	@BeforeEach
	void setUp() {
		servicio = new ServicioPregunta();
	}

	@Test
	void testGenerarLeccionVacia() {
		JPanel[] leccion = servicio.generarLeccion(999);
		assertNotNull(leccion);
		assertEquals(0, leccion.length);
	}

	@Test
	void testProcesarRespuestaCorrecta() {
		PreguntaOpciones p = new PreguntaOpciones(Nivel.BASICO, 1, "Capital de Francia", "Paris", TipoPregunta.OPCIONES,
				List.of("Paris", "Roma", "Madrid"));
		assertTrue(servicio.procesarRespuesta(p, "Paris"));
	}

	@Test
	void testProcesarRespuestaIncorrecta() {
		PreguntaOpciones p = new PreguntaOpciones(Nivel.BASICO, 1, "Capital de Francia", "Paris", TipoPregunta.OPCIONES,
				List.of("Paris", "Roma", "Madrid"));
		assertFalse(servicio.procesarRespuesta(p, "Madrid"));
	}



	@Test
	void testGuardarPreguntasNoRevienta() {
		List<Pregunta> preguntas = List
				.of(new PreguntaOpciones(Nivel.BASICO, 1, "5+5", "10", TipoPregunta.OPCIONES, List.of("10", "11")));
		CursoPlantilla curso = new CursoPlantilla("Mate Básica", "Juan", "intro", "sumas");
		assertDoesNotThrow(() -> servicio.guardarPreguntas(preguntas, curso));
	}
}
