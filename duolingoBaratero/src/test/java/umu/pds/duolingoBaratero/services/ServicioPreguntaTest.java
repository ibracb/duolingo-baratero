package umu.pds.duolingoBaratero.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;


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
