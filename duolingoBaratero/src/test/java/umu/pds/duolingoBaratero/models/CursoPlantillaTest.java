package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CursoPlantillaTest {

    private CursoPlantilla cursoPlantilla;
    private Usuario propietario;
    private BloqueContenido bloqueContenido;
    private CursoEnProgreso cursoEnProgreso;

    @BeforeEach
    public void setUp() {
        // Crear mock de Usuario
        propietario = mock(Usuario.class);
        
        // Crear un bloque de contenido mock
        bloqueContenido = mock(BloqueContenido.class);
        
        // Crear un curso en progreso mock
        cursoEnProgreso = mock(CursoEnProgreso.class);
        
        // Crear el objeto CursoPlantilla
        cursoPlantilla = new CursoPlantilla("Curso Java", propietario, "Aprender Java", "Aprender los conceptos básicos de Java");
    }

    @Test
    public void testConstructor() {
        assertNotNull(cursoPlantilla);
        assertEquals("Curso Java", cursoPlantilla.getNombre());
        assertEquals(propietario, cursoPlantilla.getPropietario());
        assertEquals("Aprender Java", cursoPlantilla.getDescripcion());
        assertEquals("Aprender los conceptos básicos de Java", cursoPlantilla.getObjetivos());
        assertEquals(0, cursoPlantilla.getNumAlumnos());
        assertEquals(0, cursoPlantilla.getLastBloqueContenido());
    }

    @Test
    public void testAddBloqueContenido() {
        cursoPlantilla.addBloqueContenido(bloqueContenido);
        assertEquals(1, cursoPlantilla.getContenidos().size());
    }

    @Test
    public void testRemoveBloqueContenido() {
        cursoPlantilla.addBloqueContenido(bloqueContenido);
        cursoPlantilla.removeBloqueContenido(bloqueContenido);
        assertEquals(0, cursoPlantilla.getContenidos().size());
    }

    @Test
    public void testAddAlumno() {
        cursoPlantilla.addAlumno();
        assertEquals(1, cursoPlantilla.getNumAlumnos());
    }

    @Test
    public void testGetTipoPreguntas() {
        // Mockear el tipo de pregunta en el bloque de contenido
        Set<TipoPregunta> tiposPreguntas = new HashSet<>();
        tiposPreguntas.add(TipoPregunta.FLASHCARD);
        
        when(bloqueContenido.getTiposPreguntas()).thenReturn(tiposPreguntas);
        cursoPlantilla.addBloqueContenido(bloqueContenido);
        
        Set<TipoPregunta> resultado = cursoPlantilla.getTipoPreguntas();
        assertTrue(resultado.contains(TipoPregunta.FLASHCARD));
    }

    @Test
    public void testGetPreguntasDeBloque() {
        // Simulamos que el bloque de contenido tiene preguntas
        List<Pregunta> preguntas = List.of(mock(Pregunta.class));
        when(bloqueContenido.getPreguntas()).thenReturn(preguntas);
        
        cursoPlantilla.addBloqueContenido(bloqueContenido);
        
        List<Pregunta> resultado = cursoPlantilla.getPreguntasDeBloque(bloqueContenido.getId());
        assertEquals(preguntas, resultado);
    }

    @Test
    public void testSetImagen() {
        cursoPlantilla.setImagen("imagen.png");
        assertTrue(cursoPlantilla.getImagen().isPresent());
        assertEquals("imagen.png", cursoPlantilla.getImagen().get());
    }

    @Test
    public void testMejorJSON() {
        cursoPlantilla.setImagen("imagen.png");
        assertTrue(cursoPlantilla.mejorJSON());
    }

}
