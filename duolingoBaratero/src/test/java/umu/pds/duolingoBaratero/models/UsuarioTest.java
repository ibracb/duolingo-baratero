package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UsuarioTest {

    private Usuario usuarioSinImagen;
    private Usuario usuarioConImagen;
    private CursoEnProgreso cursoEnProgresoMock;
    
    @SuppressWarnings("unused")
	private CursoPlantilla cursoPlantillaMock;
    
    @BeforeEach
    void setUp() {
        // Mock de objetos relacionados
        cursoEnProgresoMock = Mockito.mock(CursoEnProgreso.class);
        cursoPlantillaMock = Mockito.mock(CursoPlantilla.class);

        // Creación de usuarios
        usuarioSinImagen = new Usuario("Juan Pérez", "juan123", "juan@mail.com", "password123");
        usuarioConImagen = new Usuario("Ana López", "ana456", "ana@mail.com", "password456", "imagen.png");
    }

    @Test
    void testUsuario_ConstructorSinImagen() {
        assertEquals("Juan Pérez", usuarioSinImagen.getNombre());
        assertEquals("juan123", usuarioSinImagen.getNickname());
        assertEquals("juan@mail.com", usuarioSinImagen.getCorreo());
        assertEquals("password123", usuarioSinImagen.getPasswd());
        assertEquals("",usuarioSinImagen.getImagen());
    }

    @Test
    void testUsuario_ConstructorConImagen() {
        assertEquals("Ana López", usuarioConImagen.getNombre());
        assertEquals("ana456", usuarioConImagen.getNickname());
        assertEquals("ana@mail.com", usuarioConImagen.getCorreo());
        assertEquals("password456", usuarioConImagen.getPasswd());
        assertEquals("imagen.png", usuarioConImagen.getImagen());
    }

    @Test
    void testUsuario_HasImage() {
        assertFalse(usuarioSinImagen.hasImage());
        assertTrue(usuarioConImagen.hasImage());
    }

    @Test
    void testUsuario_AddCursoEnProgreso() {
        assertTrue(usuarioSinImagen.addCursoEnProgreso(cursoEnProgresoMock));
        assertTrue(usuarioSinImagen.getCursos().contains(cursoEnProgresoMock));
    }

    @Test
    void testUsuario_SettersGetters() {
        usuarioSinImagen.setNombre("Carlos Ruiz");
        assertEquals("Carlos Ruiz", usuarioSinImagen.getNombre());

        usuarioSinImagen.setNickname("carlos99");
        assertEquals("carlos99", usuarioSinImagen.getNickname());

        usuarioSinImagen.setCorreo("carlos@mail.com");
        assertEquals("carlos@mail.com", usuarioSinImagen.getCorreo());

        usuarioSinImagen.setPasswd("newpassword");
        assertEquals("newpassword", usuarioSinImagen.getPasswd());

        usuarioSinImagen.setImagen("newImage.png");
        assertEquals("newImage.png", usuarioSinImagen.getImagen());
    }
    
    @Test
    void testHasVidas_CuandoTieneVidas() {
        usuarioConImagen.setVidas(3);
        assertTrue(usuarioConImagen.hasVidas());
    }

    @Test
    void testHasVidas_CuandoNoTieneVidas() {
        usuarioConImagen.setVidas(0);
        assertFalse(usuarioConImagen.hasVidas());
    }

    @Test
    void testPerderVida_RestaUnaVida() {
        usuarioConImagen.setVidas(3);
        int vidasRestantes = usuarioConImagen.perderVida();
        assertEquals(2, vidasRestantes);
        assertEquals(2, usuarioConImagen.getVidas());
    }

    @Test
    void testPerderVida_ConVidasMaximas_ActualizaUltimaRecuperacion() {
        usuarioConImagen.setVidas(5);
        LocalDateTime antes = usuarioConImagen.getUltimaRecuperacion();
        usuarioConImagen.perderVida();
        LocalDateTime despues = usuarioConImagen.getUltimaRecuperacion();
        assertTrue(despues.isAfter(antes) || despues.isEqual(antes));
        assertEquals(4, usuarioConImagen.getVidas());
    }

    @Test
    void testPerderVida_Con0Vidas_NoBaja() {
        usuarioConImagen.setVidas(0);
        int vidasRestantes = usuarioConImagen.perderVida();
        assertEquals(0, vidasRestantes);
        assertEquals(0, usuarioConImagen.getVidas());
    }

    @Test
    void testRecuperarVidas_NoRecuperaSiNoHaPasadoTiempo() {
        usuarioConImagen.setVidas(3);
        usuarioConImagen.setUltimaRecuperacion(LocalDateTime.now());
        boolean tieneVidas = usuarioConImagen.recuperarVidas();
        assertTrue(tieneVidas);
        assertEquals(3, usuarioConImagen.getVidas());
    }

    @Test
    void testRecuperarVidas_RecuperaVidasSegunTiempoPasado() {
        usuarioConImagen.setVidas(3);
        usuarioConImagen.setUltimaRecuperacion(LocalDateTime.now().minusMinutes(15)); // 15 min -> 3 vidas

        boolean tieneVidas = usuarioConImagen.recuperarVidas();
        assertTrue(tieneVidas);
        assertEquals(5, usuarioConImagen.getVidas()); // Máximo 5 vidas
    }

    @Test
    void testRecuperarVidas_ActualizaUltimaRecuperacionCorrectamente() {
        usuarioConImagen.setVidas(2);
        LocalDateTime ultima = LocalDateTime.now().minusMinutes(10); // 10 min -> 2 vidas a recuperar
        usuarioConImagen.setUltimaRecuperacion(ultima);

        usuarioConImagen.recuperarVidas();

        LocalDateTime expected = ultima.plusMinutes(2 * 5); // 2 vidas * 5 min
        assertEquals(expected, usuarioConImagen.getUltimaRecuperacion());
    }

    @Test
    void testRecuperarVidas_LlegaAVidasMaxYActualizaAAhora() {
        usuarioConImagen.setVidas(4);
        LocalDateTime ultima = LocalDateTime.now().minusMinutes(10); // 10 min -> 2 vidas a recuperar
        usuarioConImagen.setUltimaRecuperacion(ultima);

        usuarioConImagen.recuperarVidas();

        assertEquals(5, usuarioConImagen.getVidas());
        // últimaRecuperacion debe actualizarse a "ahora" porque llegó al máximo
        assertTrue(usuarioConImagen.getUltimaRecuperacion().isAfter(ultima));
    }
}
