package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;

public class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Alejandro", "alex123", "alex@mail.com", "password", "imagen.png");
    }

    @Test
    void testNombreUsuario() {
        assertEquals("Alejandro", usuario.getNombre());
    }

    @Test
    void testRolesIniciales() {
        Set<Rol> roles = usuario.getRoles();
        assertTrue(roles.contains(Rol.ESTUDIANTE));
        assertTrue(roles.contains(Rol.CREADOR));
    }

    @Test
    void testSetNombre() {
        usuario.setNombre("Carlos");
        assertEquals("Carlos", usuario.getNombre());
    }

    @Test
    void testSetNickname() {
        usuario.setNickname("carlitos");
        assertEquals("carlitos", usuario.getNickname());
    }

    @Test
    void testSetCorreo() {
        usuario.setCorreo("carlos@mail.com");
        assertEquals("carlos@mail.com", usuario.getCorreo());
    }

    @Test
    void testSetPasswd() {
        usuario.setPasswd("newpassword");
        assertEquals("newpassword", usuario.getPasswd());
    }

    @Test
    void testHasImage() {
        assertTrue(usuario.hasImage());
    }

    @Test
    void testIsAdministrador() {
        assertFalse(usuario.isAdministrador());
    }

    @Test
    void testIsCreador() {
        assertTrue(usuario.isCreador());
    }

    @Test
    void testIsEstudiante() {
        assertTrue(usuario.isEstudiante());
    }
}
