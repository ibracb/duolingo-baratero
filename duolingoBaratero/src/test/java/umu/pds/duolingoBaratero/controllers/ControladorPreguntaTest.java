package umu.pds.duolingoBaratero.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import umu.pds.duolingoBaratero.models.*;
import umu.pds.duolingoBaratero.services.ServicioPregunta;

public class ControladorPreguntaTest {

	@Mock
	private ServicioPregunta servicio;

	@InjectMocks
	private ControladorPregunta controlador;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		controlador = new ControladorPregunta(servicio);
	}

	// ----------- generarLeccion -----------

	@Test
	public void testGenerarLeccion_valido() {
		long bloqueId = 1L;
		JPanel[] esperado = new JPanel[] { new JPanel(), new JPanel() };

		when(servicio.generarLeccion(bloqueId)).thenReturn(esperado);

		JPanel[] resultado = controlador.generarLeccion(bloqueId);
		assertNotNull(resultado);
		assertArrayEquals(esperado, resultado);
	}

	@Test
	public void testGenerarLeccion_nullDevuelto() {
		when(servicio.generarLeccion(anyLong())).thenReturn(null);
		JPanel[] resultado = controlador.generarLeccion(999);
		assertNull(resultado);
	}

	// ----------- obtenerPreguntasDelBloque -----------

	@Test
	public void testObtenerPreguntasDelBloque_valido() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		Set<Pregunta> preguntas = Set.of(mock(Pregunta.class));

		when(servicio.obtenerPreguntasDelBloque(curso)).thenReturn(preguntas);

		Set<Pregunta> resultado = controlador.obtenerPreguntasDelBloque(curso);
		assertEquals(preguntas, resultado);
	}

	@Test
	public void testObtenerPreguntasDelBloque_vacio() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		when(servicio.obtenerPreguntasDelBloque(curso)).thenReturn(Collections.emptySet());

		Set<Pregunta> resultado = controlador.obtenerPreguntasDelBloque(curso);
		assertTrue(resultado.isEmpty());
	}

	@Test
	public void testObtenerPreguntasDelBloque_null() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		when(servicio.obtenerPreguntasDelBloque(curso)).thenReturn(null);

		Set<Pregunta> resultado = controlador.obtenerPreguntasDelBloque(curso);
		assertNull(resultado);
	}

	// ----------- procesarRespuesta -----------

	@Test
	public void testProcesarRespuesta_correcta() {
		Pregunta pregunta = mock(Pregunta.class);
		when(servicio.procesarRespuesta(pregunta, "sí")).thenReturn(true);

		assertTrue(controlador.procesarRespuesta(pregunta, "sí"));
	}

	@Test
	public void testProcesarRespuesta_incorrecta() {
		Pregunta pregunta = mock(Pregunta.class);
		when(servicio.procesarRespuesta(pregunta, "no")).thenReturn(false);

		assertFalse(controlador.procesarRespuesta(pregunta, "no"));
	}

	@Test
	public void testProcesarRespuesta_conNull() {
		when(servicio.procesarRespuesta(null, null)).thenReturn(false);

		assertFalse(controlador.procesarRespuesta(null, null));
	}

	// ----------- obtenerNumPreguntas -----------

	@Test
	public void testObtenerNumPreguntas_valido() {
		when(servicio.obtenerNumPreguntas(123L)).thenReturn(4);

		int resultado = controlador.obtenerNumPreguntas(123L);
		assertEquals(4, resultado);
	}

	@Test
	public void testObtenerNumPreguntas_cero() {
		when(servicio.obtenerNumPreguntas(0L)).thenReturn(0);

		int resultado = controlador.obtenerNumPreguntas(0L);
		assertEquals(0, resultado);
	}

	@Test
	public void testObtenerNumPreguntas_negativo() {
		when(servicio.obtenerNumPreguntas(-1L)).thenReturn(-1);

		int resultado = controlador.obtenerNumPreguntas(-1L);
		assertEquals(-1, resultado);
	}

	// ----------- guardarPreguntas -----------

	@Test
	public void testGuardarPreguntas_valido() {
		List<Pregunta> preguntas = List.of(mock(Pregunta.class));
		CursoPlantilla curso = mock(CursoPlantilla.class);

		assertDoesNotThrow(() -> controlador.guardarPreguntas(preguntas, curso));
		verify(servicio).guardarPreguntas(preguntas, curso);
	}

	@Test
	public void testGuardarPreguntas_listaVacia() {
		List<Pregunta> preguntas = new ArrayList<>();
		CursoPlantilla curso = mock(CursoPlantilla.class);

		controlador.guardarPreguntas(preguntas, curso);
		verify(servicio).guardarPreguntas(preguntas, curso);
	}

	@Test
	public void testGuardarPreguntas_nulls() {
		doThrow(new RuntimeException("Fallo interno")).when(servicio).guardarPreguntas(null, null);

		assertThrows(RuntimeException.class, () -> controlador.guardarPreguntas(null, null));
	}
}
