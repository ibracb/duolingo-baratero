package umu.pds.duolingoBaratero.services.filters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;

class FiltroTest {

	private CursoPlantilla curso1;
	private CursoPlantilla curso2;
	private CursoPlantilla curso3;
	private List<CursoPlantilla> lista;

	@BeforeEach
	void setUp() {
		curso1 = mock(CursoPlantilla.class);
		curso2 = mock(CursoPlantilla.class);
		curso3 = mock(CursoPlantilla.class);

		when(curso1.getNombre()).thenReturn("Inglés");
		when(curso2.getNombre()).thenReturn("Francés");
		when(curso3.getNombre()).thenReturn("Inglés");

		when(curso1.getPropietario()).thenReturn("Ana");
		when(curso2.getPropietario()).thenReturn("Luis");
		when(curso3.getPropietario()).thenReturn("Ana");

		when(curso1.getNivel()).thenReturn(Nivel.BASICO);
		when(curso2.getNivel()).thenReturn(Nivel.INTERMEDIO);
		when(curso3.getNivel()).thenReturn(Nivel.BASICO);

		lista = Arrays.asList(curso1, curso2, curso3);
	}

	@Test
	void testFiltroBasico() {
		Filtro filtro = new FiltroBasico();
		List<CursoPlantilla> resultado = filtro.filtrar(lista);
		assertEquals(lista, resultado);
	}

	@Test
	void testFiltroPorNombre() {
		Filtro filtro = new FiltroPorNombre(new FiltroBasico(), "Inglés");
		List<CursoPlantilla> resultado = filtro.filtrar(lista);
		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(curso1));
		assertTrue(resultado.contains(curso3));
	}

	@Test
	void testFiltroPorPropietario() {
		Filtro filtro = new FiltroPorPropietario(new FiltroBasico(), "Ana");
		List<CursoPlantilla> resultado = filtro.filtrar(lista);
		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(curso1));
		assertTrue(resultado.contains(curso3));
	}

	@Test
	void testFiltroPorNivel() {
		Filtro filtro = new FiltroPorNivel(new FiltroBasico(), Nivel.BASICO);
		List<CursoPlantilla> resultado = filtro.filtrar(lista);
		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(curso1));
		assertTrue(resultado.contains(curso3));
	}

	@Test
	void testFiltroCombinado() {
		Filtro filtro = new FiltroPorPropietario(
				new FiltroPorNivel(new FiltroPorNombre(new FiltroBasico(), "Inglés"), Nivel.BASICO), "Ana");

		List<CursoPlantilla> resultado = filtro.filtrar(lista);
		assertEquals(2, resultado.size());
		assertEquals(curso1, resultado.get(0));
	}
}
