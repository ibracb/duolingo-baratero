package umu.pds.duolingoBaratero.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import umu.pds.duolingoBaratero.services.ServicioEstadistica;

public class ControladorEstadisticaTest {

	@Mock
	private ServicioEstadistica servicio;

	@InjectMocks
	private ControladorEstadistica controlador;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		controlador = new ControladorEstadistica(servicio);
	}

	// ----------- getTiempoUso -----------

	@Test
	public void testGetTiempoUso_valido() {
		when(servicio.getTiempoUso()).thenReturn(123.45);
		assertEquals(123.45, controlador.getTiempoUso());
	}

	@Test
	public void testGetTiempoUso_cero() {
		when(servicio.getTiempoUso()).thenReturn(0.0);
		assertEquals(0.0, controlador.getTiempoUso());
	}

	@Test
	public void testGetTiempoUso_negativo() {
		when(servicio.getTiempoUso()).thenReturn(-10.0);
		assertEquals(-10.0, controlador.getTiempoUso());
	}

	// ----------- cerrarSesion -----------

	@Test
	public void testCerrarSesion_noLanzaExcepcion() {
		doNothing().when(servicio).cerrarSesion();
		assertDoesNotThrow(() -> controlador.cerrarSesion());
		verify(servicio).cerrarSesion();
	}

	@Test
	public void testCerrarSesion_conExcepcion() {
		doThrow(new RuntimeException("Fallo")).when(servicio).cerrarSesion();
		assertThrows(RuntimeException.class, () -> controlador.cerrarSesion());
	}

	// ----------- getRachaVictorias -----------

	@Test
	public void testGetRachaVictorias_valido() {
		when(servicio.getRachaVictorias()).thenReturn(7);
		assertEquals(7, controlador.getRachaVictorias());
	}

	@Test
	public void testGetRachaVictorias_cero() {
		when(servicio.getRachaVictorias()).thenReturn(0);
		assertEquals(0, controlador.getRachaVictorias());
	}

	@Test
	public void testGetRachaVictorias_negativa() {
		when(servicio.getRachaVictorias()).thenReturn(-3);
		assertEquals(-3, controlador.getRachaVictorias());
	}

	// ----------- actualizarRachaVictorias -----------

	@Test
	public void testActualizarRachaVictorias_true() {
		doNothing().when(servicio).actualizarRachaVictorias(true);
		controlador.actualizarRachaVictorias(true);
		verify(servicio).actualizarRachaVictorias(true);
	}

	@Test
	public void testActualizarRachaVictorias_false() {
		doNothing().when(servicio).actualizarRachaVictorias(false);
		controlador.actualizarRachaVictorias(false);
		verify(servicio).actualizarRachaVictorias(false);
	}

	// ----------- getRachaAcceso -----------

	@Test
	public void testGetRachaAcceso_valido() {
		when(servicio.getRachaAcceso()).thenReturn(5);
		assertEquals(5, controlador.getRachaAcceso());
	}

	@Test
	public void testGetRachaAcceso_cero() {
		when(servicio.getRachaAcceso()).thenReturn(0);
		assertEquals(0, controlador.getRachaAcceso());
	}

	@Test
	public void testGetRachaAcceso_negativo() {
		when(servicio.getRachaAcceso()).thenReturn(-1);
		assertEquals(-1, controlador.getRachaAcceso());
	}

	// ----------- actualizarAciertos -----------

	@Test
	public void testActualizarAciertos_correcta() {
		doNothing().when(servicio).actualizarAciertos(true);
		controlador.actualizarAciertos(true);
		verify(servicio).actualizarAciertos(true);
	}

	@Test
	public void testActualizarAciertos_incorrecta() {
		doNothing().when(servicio).actualizarAciertos(false);
		controlador.actualizarAciertos(false);
		verify(servicio).actualizarAciertos(false);
	}

	// ----------- getPorcentajeAciertos -----------

	@Test
	public void testGetPorcentajeAciertos_valido() {
		when(servicio.getPorcentajeAciertos()).thenReturn("85%");
		assertEquals("85%", controlador.getPorcentajeAciertos());
	}

	@Test
	public void testGetPorcentajeAciertos_vacio() {
		when(servicio.getPorcentajeAciertos()).thenReturn("");
		assertEquals("", controlador.getPorcentajeAciertos());
	}

	@Test
	public void testGetPorcentajeAciertos_null() {
		when(servicio.getPorcentajeAciertos()).thenReturn(null);
		assertNull(controlador.getPorcentajeAciertos());
	}
}
