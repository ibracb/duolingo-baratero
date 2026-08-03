package umu.pds.duolingoBaratero.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.persistence.DBUsuarioDAO;

public class ServicioUsuarioTest {
    private DBUsuarioDAO usuarioDAO;
    private ServicioCursoProgreso servicioCursoProgreso;
    private ServicioCursoPlantilla servicioCursoPlantilla;
    private ServicioEstadistica servicioEstadistica;
    private ServicioUsuario servicio;

    @BeforeEach
    void init() {
        usuarioDAO = mock(DBUsuarioDAO.class);
        servicioCursoProgreso = mock(ServicioCursoProgreso.class);
        servicioCursoPlantilla = mock(ServicioCursoPlantilla.class);
        servicioEstadistica = mock(ServicioEstadistica.class);
        servicio = new ServicioUsuario(usuarioDAO, servicioCursoProgreso, servicioCursoPlantilla, servicioEstadistica);
    }

    @Test
    void testRegistrarUsuarioNuevo() {
        when(usuarioDAO.existeUsuario("mail")).thenReturn(false);
        boolean ok = servicio.registrarUsuario("Pepe", "Gomez", "mail", "123");
        assertTrue(ok);
    }

    @Test
    void testRegistrarUsuarioExistente() {
        when(usuarioDAO.existeUsuario("mail")).thenReturn(true);
        assertFalse(servicio.registrarUsuario("Pepe", "Gomez", "mail", "123"));
    }

    @Test
    void testComprobarUsuarioCorrecto() {
        Usuario u = new Usuario("A", "B", "mail", "pw");
        when(usuarioDAO.get("mail")).thenReturn(u);
        assertTrue(servicio.comprobarUsuario("mail", "pw"));
    }

    @Test
    void testComprobarUsuarioInexistente() {
        when(usuarioDAO.get("mail")).thenReturn(null);
        assertFalse(servicio.comprobarUsuario("mail", "pw"));
    }

    @Test
    void testGetNombreUsuarioActual() {
        Usuario u = new Usuario("Laura", "X", "correo", "123");
        when(usuarioDAO.get("correo")).thenReturn(u);
        servicio.comprobarUsuario("correo", "123");
        assertEquals("Laura", servicio.getNombreUsuarioActual());
    }

    @Test
    void testLogOut() {
        Usuario u = new Usuario("Laura", "X", "correo", "123");
        when(usuarioDAO.get("correo")).thenReturn(u);
        servicio.comprobarUsuario("correo", "123");
        servicio.logOut();
        assertNull(servicio.getUsuarioActual());
    }

    @Test
    void testSetImagen() {
        Usuario u = new Usuario("Luis", "K", "correo", "pw");
        when(usuarioDAO.get("correo")).thenReturn(u);
        servicio.comprobarUsuario("correo", "pw");
        servicio.setImagen("img.png");
        assertEquals("img.png", servicio.getUsuarioActual().getImagen());
    }

    @Test
    void testQuitarVidaYRecuperar() {
        Usuario u = new Usuario("Ana", "G", "mail", "pw");
        when(usuarioDAO.get("mail")).thenReturn(u);
        servicio.comprobarUsuario("mail", "pw");
        int vidasAntes = u.getVidas();
        servicio.quitarVida();
        assertEquals(vidasAntes - 1, servicio.getVidasUsuario());
    }

    @Test
    void testActualizarUsuario() {
        Usuario u = new Usuario("Mario", "Z", "mario@mail", "pw");
        when(usuarioDAO.get("mario@mail")).thenReturn(u);
        servicio.comprobarUsuario("mario@mail", "pw");
        servicio.actualizarUsuario();
        verify(usuarioDAO).update(u);
    }
}
