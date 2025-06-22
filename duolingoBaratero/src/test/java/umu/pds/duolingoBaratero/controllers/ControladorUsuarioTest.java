package umu.pds.duolingoBaratero.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Set;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.ServicioUsuario;

class ControladorUsuarioTest {

	private ServicioUsuario servicioUsuario;
	private ImageService imageService;
	private ControladorUsuario controlador;

	@BeforeEach
	void setUp() {
		servicioUsuario = mock(ServicioUsuario.class);
		imageService = mock(ImageService.class);
		controlador = new ControladorUsuario(servicioUsuario, imageService);
	}

	@Test
	void testRegistrarUsuario() {
		when(servicioUsuario.registrarUsuario("Antonio", "Martínez", "antonio@gmail.com", "1234")).thenReturn(true);
		assertTrue(controlador.registrarUsuario("Antonio", "Martínez", "antonio@gmail.com", "1234"));
	}

	@Test
	void testComprobarUsuario() {
		when(servicioUsuario.comprobarUsuario("john@gmail.com", "1234")).thenReturn(true);
		assertTrue(controlador.comprobarUsuario("john@gmail.com", "1234"));
	}

	@Test
	void testGetUsuarioActual() {
		Usuario usuario = new Usuario();
		when(servicioUsuario.getUsuarioActual()).thenReturn(usuario);
		assertEquals(usuario, controlador.getUsuarioActual());
	}

	@Test
	void testLogOut() {
		controlador.logOut();
		verify(servicioUsuario).logOut();
	}

	@Test
	void testSetImagen() {
		controlador.setImagen("ruta.jpg");
		verify(servicioUsuario).setImagen("ruta.jpg");
	}

	@Test
	void testSetCursos() {
		String[] cursos = {"Inglés", "Francés"};
		controlador.setCursos(cursos);
		verify(servicioUsuario).setCursos(cursos);
	}

	@Test
	void testAddCursosEnProgreso() {
		CursoPlantilla curso = new CursoPlantilla();
		when(servicioUsuario.addCursosEnProgreso(curso)).thenReturn(true);
		assertTrue(controlador.addCursosEnProgreso(curso));
	}

	@Test
	void testEstaCursando() {
		CursoPlantilla curso = new CursoPlantilla();
		when(servicioUsuario.estaCursando(curso)).thenReturn(true);
		assertTrue(controlador.estaCursando(curso));
	}

	@Test
	void testAddCursoPlantilla() {
		when(servicioUsuario.addCursoPlantilla("A1", "Aprender lo básico", "Curso introductorio"))
			.thenReturn(true);
		assertTrue(controlador.addCursoPlantilla("A1", "Aprender lo básico", "Curso introductorio"));
	}

	@SuppressWarnings("unchecked")
	@Test
	void testGetCursosUsuarioActual() {
		Set<CursoEnProgreso> cursos = mock(Set.class);
		when(servicioUsuario.getCursosUsuarioActual()).thenReturn(cursos);
		assertEquals(cursos, controlador.getCursosUsuarioActual());
	}

	@Test
	void testGetScaledImageBufferedImage() {
		BufferedImage bufferedImage = mock(BufferedImage.class);
		ImageIcon icon = new ImageIcon();
		when(imageService.getScaledImage(bufferedImage, 100)).thenReturn(icon);
		assertEquals(icon, controlador.getScaledImage(bufferedImage, 100));
	}

	@Test
	void testGetScaledImageImageIcon() {
		ImageIcon imageIcon = mock(ImageIcon.class);
		ImageIcon result = new ImageIcon();
		when(imageService.getScaledImage(imageIcon, 80)).thenReturn(result);
		assertEquals(result, controlador.getScaledImage(imageIcon, 80));
	}

	@Test
	void testBorrarCurso() {
		CursoEnProgreso curso = new CursoEnProgreso();
		controlador.borrarCurso(curso);
		verify(servicioUsuario).borrarCurso(curso);
	}

	@Test
	void testRestarVidaUsuario() {
		when(servicioUsuario.quitarVida()).thenReturn(2);
		int vidas = controlador.restarVidaUsuario();
		assertEquals(2, vidas);
		verify(servicioUsuario).actualizarUsuario();
	}

	@Test
	void testGetVidasUsuario() {
		when(servicioUsuario.getVidasUsuario()).thenReturn(3);
		assertEquals(3, controlador.getVidasUsuario());
	}

	@Test
	void testRecuperarVida() {
		when(servicioUsuario.recuperarVida()).thenReturn(true);
		assertTrue(controlador.recuperarVida());
		verify(servicioUsuario).actualizarUsuario();
	}

	@Test
	void testGetUltimaRecuperacion() {
		LocalDateTime now = LocalDateTime.now();
		when(servicioUsuario.getUltimaRecuperacion()).thenReturn(now);
		assertEquals(now, controlador.getUltimaRecuperacion());
	}

	@Test
	void testActualizarUsuarioHandlesException() {
		doThrow(new RuntimeException("DB error")).when(servicioUsuario).actualizarUsuario();
		assertDoesNotThrow(() -> controlador.actualizarUsuario());
	}

}
