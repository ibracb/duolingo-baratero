package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CursoPlantillaTest {
    private CursoPlantilla curso;
    private Usuario propietario;
    private BloqueContenido bloqueMock;
    private CursoEnProgreso cursoEnProgresoMock;
    
    @BeforeEach
    void setUp() {
        propietario = mock(Usuario.class);
        bloqueMock = mock(BloqueContenido.class);
        cursoEnProgresoMock = mock(CursoEnProgreso.class);
        
        curso = new CursoPlantilla("Java Básico", propietario, "Curso de introducción a Java", "Aprender los fundamentos de Java");
    }
    
    @Test
    void testCursoPlantillaInicializacion() {
        assertEquals("Java Básico", curso.getNombre());
        assertEquals("Curso de introducción a Java", curso.getDescripcion());
        assertEquals("Aprender los fundamentos de Java", curso.getObjetivos());
        assertEquals(propietario, curso.getPropietario());
        assertEquals(0, curso.getNumAlumnos());
        assertTrue(curso.getContenidos().isEmpty());
        assertEquals(0, curso.getLastBloqueContenido());
    }
    
    @Test
    void testAddAlumno() {
        curso.addAlumno();
        assertEquals(1, curso.getNumAlumnos());
    }
    
    @Test
    void testAddBloqueContenido() {
        curso.addBloqueContenido(bloqueMock);
        assertFalse(curso.getContenidos().isEmpty());
        assertEquals(1, curso.getLastBloqueContenido());
    }
    
    @Test
    void testRemoveBloqueContenido() {
        curso.addBloqueContenido(bloqueMock);
        curso.removeBloqueContenido(bloqueMock);
        assertTrue(curso.getContenidos().isEmpty());
    }
    
    @Test
    void testSetImagen() {
        curso.setImagen("imagen.jpg");
        assertTrue(curso.getImagen().isPresent());
        assertEquals("imagen.jpg", curso.getImagen().get());
    }
    
    @Test
    void testHasImage() {
        curso.setImagen("imagen.jpg");
        assertTrue(curso.hasImage());
    }
    
    @Test
    void testGetNumCursosEnProgreso() {
        curso.setCursosEnProgreso(Set.of(cursoEnProgresoMock));
        assertEquals(1, curso.getNumCursosEnProgreso());
    }
    
    @Test
    void testGetValoracionMedia() {
        when(cursoEnProgresoMock.getValoracion()).thenReturn(Valoracion.CUATRO);
        curso.setCursosEnProgreso(Set.of(cursoEnProgresoMock));
        assertEquals(4.0, curso.getValoracionMedia());
    }
    
    @Test
    void testGetPreguntasDeBloque() {
        when(bloqueMock.getId()).thenReturn(1L);
        when(bloqueMock.getPreguntas()).thenReturn(List.of(mock(Pregunta.class)));
        curso.addBloqueContenido(bloqueMock);
        assertFalse(curso.getPreguntasDeBloque(1L).isEmpty());
    }
}
