package umu.pds.duolingoBaratero.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.services.ServicioCursoProgreso;

class ControladorCursoProgresoTest {

	@Mock
	private ServicioCursoProgreso servicio;

	@InjectMocks
	private ControladorCursoProgreso controlador;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		controlador = new ControladorCursoProgreso(servicio);
	}

	@Test
	public void testCrearCurso() {
		CursoPlantilla plantilla = mock(CursoPlantilla.class);
		Usuario usuario = mock(Usuario.class);
		CursoEnProgreso cursoEsperado = mock(CursoEnProgreso.class);

		when(servicio.crearCursoEnProgreso(plantilla, usuario)).thenReturn(cursoEsperado);

		CursoEnProgreso curso = controlador.crearCurso(plantilla, usuario);
		assertNotNull(curso);
		assertEquals(cursoEsperado, curso);
	}

	@Test
	public void testConfigurarCursoProgreso_retornaTrue() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		AprendizajeSeleccionado aprendizaje = mock(AprendizajeSeleccionado.class);

		doNothing().when(servicio).iniciarCurso(curso);
		when(servicio.setAprendizaje(curso, aprendizaje)).thenReturn(true);

		boolean resultado = controlador.configurarCursoProgreso(curso, aprendizaje);
		assertTrue(resultado);
		verify(servicio).iniciarCurso(curso);
		verify(servicio).setAprendizaje(curso, aprendizaje);
	}

	@Test
	public void testEsNuevo() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		when(servicio.esCursoNuevo(curso)).thenReturn(true);

		assertTrue(controlador.esNuevo(curso));
	}

	@Test
	public void testEstaEnMarcha() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		when(servicio.esCursoEnMarcha(curso)).thenReturn(true);

		assertTrue(controlador.estaEnMarcha(curso));
	}

	@Test
	public void testEstaFinalizado() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		when(servicio.esCursoFinalizado(curso)).thenReturn(true);

		assertTrue(controlador.estaFinalizado(curso));
	}

	@Test
	public void testAvanzar() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		doNothing().when(servicio).avanzarBloque(curso, true);

		controlador.avanzar(curso, true);
		verify(servicio).avanzarBloque(curso, true);
	}

	@Test
	public void testReiniciar() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		when(servicio.actualizarCurso(curso)).thenReturn(true);

		controlador.reiniciar(curso);
		verify(servicio).reiniciarCurso(curso);
		verify(servicio).actualizarCurso(curso);
	}

	@Test
	public void testActualizarCurso_retornaTrue() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		when(servicio.actualizarCurso(curso)).thenReturn(true);

		boolean resultado = controlador.actualizarCurso(curso);
		assertTrue(resultado);
	}

	@Test
	public void testActualizarCurso_lanzaExcepcion() {
		CursoEnProgreso curso = mock(CursoEnProgreso.class);
		when(servicio.actualizarCurso(curso)).thenThrow(new RuntimeException("Fallo"));

		boolean resultado = controlador.actualizarCurso(curso);
		assertFalse(resultado);
	}

}
