package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UsuarioTest {

    private Usuario usuarioSinImagen;
    private Usuario usuarioConImagen;
    private CursoEnProgreso cursoEnProgresoMock;
    private CursoPlantilla cursoPlantillaMock;
    private Estadistica estadisticaMock;

    @BeforeEach
    void setUp() {
        // Mock de objetos relacionados
        cursoEnProgresoMock = Mockito.mock(CursoEnProgreso.class);
        cursoPlantillaMock = Mockito.mock(CursoPlantilla.class);
        estadisticaMock = Mockito.mock(Estadistica.class);

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
        assertNull(usuarioSinImagen.getImagen());
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
    void testUsuario_AddCursoPlantilla() {
//        usuarioSinImagen.addCursoPlantilla(cursoPlantillaMock);
//        assertTrue(usuarioSinImagen.getCursosCreados().contains(cursoPlantillaMock));
    }

    @Test
    void testUsuario_Estadisticas() {
        usuarioSinImagen.setEstadistica(estadisticaMock);

        Mockito.when(estadisticaMock.getPorcentajeAciertos()).thenReturn(85.5);
        Mockito.when(estadisticaMock.getTiempoUso()).thenReturn(12.3);
        Mockito.when(estadisticaMock.getRachaVictorias()).thenReturn(5);
        Mockito.when(estadisticaMock.getNumAccesos()).thenReturn(10);

        assertEquals(85.5, usuarioSinImagen.getPorcentajeAcierto());
        assertEquals(12.3, usuarioSinImagen.getTiempoUso());
        assertEquals(5, usuarioSinImagen.getRachaVictorias());
        assertEquals(10, usuarioSinImagen.getNumMaxAccesos());
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
}
