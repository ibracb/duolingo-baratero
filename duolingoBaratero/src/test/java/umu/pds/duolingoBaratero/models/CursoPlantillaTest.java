package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

class CursoPlantillaTest {

    private CursoPlantilla cursoPlantilla;
    private BloqueContenido bloqueContenido;
    private Pregunta preguntaMock1;
    private Pregunta preguntaMock2;
    private Pregunta preguntaMock3;

    @BeforeEach
    void setup() {
        preguntaMock1 = Mockito.mock(Pregunta.class);
        preguntaMock2 = Mockito.mock(Pregunta.class);
        preguntaMock3 = Mockito.mock(Pregunta.class);
        preguntaMock1.setNumero(0);
        preguntaMock2.setNumero(1);
        // Se configuran los mocks para devolver valores cuando se llamen sus métodos
        Mockito.when(preguntaMock1.getTipo()).thenReturn(TipoPregunta.FLASHCARD);
        Mockito.when(preguntaMock2.getTipo()).thenReturn(TipoPregunta.AUDIO);
        Mockito.when(preguntaMock3.getTipo()).thenReturn(TipoPregunta.IMAGENES);

        // Se inicializa el bloque con preguntas mockeadas
        bloqueContenido = new BloqueContenido(100L, preguntaMock1, preguntaMock2);

        cursoPlantilla = new CursoPlantilla("Java Básico", "AutorX", "Curso intro Java", "Aprender Java", Nivel.BASICO, bloqueContenido);
    }

    /**
     * Test para verificar que el constructor y getters funcionan correctamente.
     */
    @Test
    void testConstructorYGetters() {
        assertEquals("Java Básico", cursoPlantilla.getNombre());
        assertEquals("AutorX", cursoPlantilla.getPropietario());
        assertEquals("Curso intro Java", cursoPlantilla.getDescripcion());
        assertEquals("Aprender Java", cursoPlantilla.getObjetivos());
        assertEquals(Nivel.BASICO, cursoPlantilla.getNivel());
        assertEquals(1, cursoPlantilla.getContenidos().size());
    }

    /**
     * Test para verificar el método isCursoFinalizado con índice igual al tamaño de bloques.
     */
    @Test
    void testIsCursoFinalizado() {
        assertFalse(cursoPlantilla.isCursoFinalizado(2));
        assertTrue(cursoPlantilla.isCursoFinalizado(1));
    }

    /**
     * Test para agregar y eliminar bloques de contenido.
     */
    @Test
    void testAddRemoveBloqueContenido() {
        BloqueContenido nuevoBloque = new BloqueContenido();
        cursoPlantilla.addBloqueContenido(nuevoBloque);
        assertTrue(cursoPlantilla.getContenidos().contains(nuevoBloque));

        cursoPlantilla.removeBloqueContenido(nuevoBloque);
        assertFalse(cursoPlantilla.getContenidos().contains(nuevoBloque));
    }

    /**
     * Test para el método hasImage, verifica cuando la imagen está asignada o no.
     */
    @Test
    void testHasImage() {
        assertFalse(cursoPlantilla.hasImage());
        cursoPlantilla.setImagen("ruta/imagen.jpg");
        assertTrue(cursoPlantilla.hasImage());
    }

    /**
     * Test para obtener tipos de preguntas desde bloques (se mockea contenido vacío).
     */
    @Test
    void testGetTipoPreguntas() {
        // Simular que bloqueMock1 devuelve ciertos tipos de preguntas
        // Aquí debes usar mocks si usas Mockito o implementar getTiposPreguntas en BloqueContenido
        assertNotNull(cursoPlantilla.getTipoPreguntas());
    }

    /**
     * Test para obtener preguntas de un bloque específico.
     */
    @Test
    void testGetPreguntasDeBloque() {
        // Simular que bloqueMock1 devuelve un conjunto de preguntas
        Set<Pregunta> preguntas = cursoPlantilla.getPreguntasDeBloque(0);
        assertNotNull(preguntas);
    }

    /**
     * Test para comparar plantillas por nivel.
     */
    @Test
    void testCompareTo() {
        CursoPlantilla otroCurso = new CursoPlantilla("Avanzado", "AutorY", "Curso avanzado", "Aprender avanzado", Nivel.AVANZADO);
        assertTrue(cursoPlantilla.compareTo(otroCurso) < 0);
        assertTrue(otroCurso.compareTo(cursoPlantilla) > 0);
    }
}

