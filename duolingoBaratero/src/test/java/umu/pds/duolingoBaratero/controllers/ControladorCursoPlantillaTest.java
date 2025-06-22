package umu.pds.duolingoBaratero.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.services.AudioService;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.ServicioCursoPlantilla;
import umu.pds.duolingoBaratero.services.ServicioUsuario;

class ControladorCursoPlantillaTest {

	@Mock
	private ServicioCursoPlantilla servicioCursoPlantilla;

	@Mock
	private ImageService imageService;

	@Mock
	private AudioService audioService;

	@Mock
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorCursoPlantilla controlador;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		controlador = new ControladorCursoPlantilla(servicioCursoPlantilla, imageService, audioService, servicioUsuario);
	}

	@Test
	public void testGetCursoPlantilla_found() {
		CursoPlantilla mockCurso = new CursoPlantilla("Español Básico", "admin", "desc", "obj");
		when(servicioCursoPlantilla.buscarCursoPorNombre("Español Básico"))
			.thenReturn(Optional.of(mockCurso));

		Optional<CursoPlantilla> resultado = controlador.getCursoPlantilla("Español Básico");
		assertTrue(resultado.isPresent());
		assertEquals("Español Básico", resultado.get().getNombre());
	}

	@Test
	public void testCrearCurso_exitoso() {
		Usuario usuario = mock(Usuario.class);
		when(usuario.getNickname()).thenReturn("admin");
		when(servicioUsuario.getUsuarioActual()).thenReturn(usuario);

		CursoPlantilla curso = new CursoPlantilla("CursoX", "admin", "desc", "obj");
		when(servicioCursoPlantilla.crearCursoPlantilla("CursoX", "admin", "desc", "obj")).thenReturn(curso);

		CursoPlantilla creado = controlador.crearCurso("CursoX", "desc", "obj");
		assertNotNull(creado);
		assertEquals("CursoX", creado.getNombre());
	}

	@Test
	public void testBuscarCursos_devuelveLista() {
		CursoPlantilla curso = new CursoPlantilla("Inglés", "admin", "desc", "obj");
		when(servicioCursoPlantilla.obtenerTodosLosCursos()).thenReturn(List.of(curso));

		List<CursoPlantilla> resultado = controlador.buscarCursos();
		assertEquals(1, resultado.size());
		assertEquals("Inglés", resultado.get(0).getNombre());
	}

	@Test
	public void testEliminarCurso_exitoso() {
		when(servicioCursoPlantilla.eliminarCurso("CursoY")).thenReturn(true);

		boolean resultado = controlador.eliminarCurso("CursoY");
		assertTrue(resultado);
	}

	@Test
	public void testExportarCurso_error() {
		CursoPlantilla curso = new CursoPlantilla("CursoExport", "admin", "desc", "obj");
		when(servicioCursoPlantilla.exportarCurso(curso, "json")).thenThrow(new RuntimeException("Fallo"));

		boolean resultado = controlador.exportarCurso(curso, "json");
		assertFalse(resultado);
	}

	@Test
	public void testPlayAudio_noLanzaExcepcion() {
		doNothing().when(audioService).playAudio("ruta.mp3");

		assertDoesNotThrow(() -> controlador.playAudio("ruta.mp3"));
	}

	@Test
	public void testGetNombrePropietario_valido() {
		CursoPlantilla curso = new CursoPlantilla("Curso1", "Pepe", "desc", "obj");
		String propietario = controlador.getNombrePropietario(curso);
		assertEquals("Pepe", propietario);
	}

	@Test
	public void testGetNombrePropietario_null() {
		assertEquals("", controlador.getNombrePropietario(null));
	}

	@Test
	public void testActualizarCurso_exitoso() {
		CursoPlantilla curso = new CursoPlantilla("UpdateCurso", "admin", "desc", "obj");
		when(servicioCursoPlantilla.actualizarCurso(curso)).thenReturn(true);

		boolean result = controlador.actualizarCurso(curso);
		assertTrue(result);
	}

	@Test
	public void testImportarCurso_retornaCurso() {
		File archivo = new File("curso.dl");
		CursoPlantilla curso = new CursoPlantilla("Importado", "admin", "desc", "obj");
		when(servicioCursoPlantilla.importarCurso(archivo, "dl")).thenReturn(curso);

		CursoPlantilla result = controlador.importarCurso(archivo, "dl");
		assertNotNull(result);
		assertEquals("Importado", result.getNombre());
	}
	
	@Test
	public void testGetCursoEnProgreso_porNombre() {
		Usuario usuario = mock(Usuario.class);
		CursoEnProgreso cursoEnProgreso = mock(CursoEnProgreso.class);
		when(servicioCursoPlantilla.crearCursoEnProgreso("Inglés", usuario)).thenReturn(cursoEnProgreso);

		CursoEnProgreso resultado = controlador.getCursoEnProgreso("Inglés", usuario);
		assertNotNull(resultado);
	}

	@Test
	public void testGetCursoEnProgreso_porCursoPlantilla() {
		Usuario usuario = mock(Usuario.class);
		CursoPlantilla plantilla = mock(CursoPlantilla.class);
		CursoEnProgreso cursoEnProgreso = mock(CursoEnProgreso.class);
		when(servicioCursoPlantilla.crearCursoEnProgreso(plantilla, usuario)).thenReturn(cursoEnProgreso);

		CursoEnProgreso resultado = controlador.getCursoEnProgreso(plantilla, usuario);
		assertNotNull(resultado);
	}

	@Test
	public void testGetScaledImage_BufferedImage_valido() {
		BufferedImage imagen = mock(BufferedImage.class);
		ImageIcon escalada = new ImageIcon();
		when(imageService.getScaledImage(imagen, 100)).thenReturn(escalada);

		ImageIcon resultado = controlador.getScaledImage(imagen, 100);
		assertNotNull(resultado);
		assertEquals(escalada, resultado);
	}

	@Test
	public void testGetScaledImage_ImageIcon_valido() {
		ImageIcon imagen = new ImageIcon();
		ImageIcon escalada = new ImageIcon();
		when(imageService.getScaledImage(imagen, 100)).thenReturn(escalada);

		ImageIcon resultado = controlador.getScaledImage(imagen, 100);
		assertNotNull(resultado);
		assertEquals(escalada, resultado);
	}

	@Test
	public void testGetScaledDefaultImage_valido() {
		ImageIcon dummy = new ImageIcon();
		when(imageService.getScaledImage(any(ImageIcon.class), eq(80))).thenReturn(dummy);

		ImageIcon resultado = controlador.getScaledDefaultImage(80);
		assertNotNull(resultado);
		assertEquals(dummy, resultado);
	}

	@Test
	public void testRecargarCursosBase_sinExcepcion() {
		doNothing().when(servicioCursoPlantilla).cargarCursosBase();
		assertDoesNotThrow(() -> controlador.recargarCursosBase());
	}

}
