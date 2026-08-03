package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BloqueContenidoTest {

    private BloqueContenido bloqueContenido;
    private Pregunta preguntaMock1;
    private Pregunta preguntaMock2;
    private Pregunta preguntaMock3;

    @BeforeEach
    void setUp() {
        // Se crean mocks de Pregunta
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
        
    }

    @Test
    void testConstructorYGetters() {
        assertNotNull(bloqueContenido.getPreguntas());
        assertEquals(2, bloqueContenido.getPreguntas().size());
        assertEquals(0, bloqueContenido.getId());
    }

    @Test
    void testSetId() {
        bloqueContenido.setId(200L);
        assertEquals(200L, bloqueContenido.getId());
    }

	@Test
    void testGetPreguntas() {
        Set<Pregunta> preguntas =  bloqueContenido.getPreguntas();
        assertEquals(2, preguntas.size());
        assertTrue(preguntas.contains(preguntaMock1));
        assertTrue(preguntas.contains(preguntaMock2));
    }
    
    @Test
    void testGetPreguntasSecuencial() {
        Set<Pregunta> preguntas = bloqueContenido.getPreguntasSecuencialmente();
        assertEquals(2, preguntas.size());
        assertTrue(preguntas.contains(preguntaMock1));
        assertTrue(preguntas.contains(preguntaMock2));

    }
    
	@Test
    void testSetPreguntas() {
        List<Pregunta> nuevasPreguntas = Arrays.asList(preguntaMock3);
        bloqueContenido.setPreguntas(new HashSet<Pregunta>(nuevasPreguntas));
        assertEquals(1, bloqueContenido.getPreguntas().size());
    }

    @Test
    void testAddPregunta() {
        bloqueContenido.addPregunta(preguntaMock3);
        assertEquals(3, bloqueContenido.getPreguntas().size());
        assertTrue(bloqueContenido.getPreguntas().contains(preguntaMock3));
    }

    @Test
    void testRemovePregunta() {
        bloqueContenido.removePregunta(preguntaMock1);
        assertEquals(1, bloqueContenido.getPreguntas().size());
        assertFalse(bloqueContenido.getPreguntas().contains(preguntaMock1));
    }

    @Test
    void testGetTiposPreguntas() {
        Set<TipoPregunta> tipos = bloqueContenido.getTiposPreguntas();
        assertEquals(2, tipos.size());
        assertTrue(tipos.contains(TipoPregunta.FLASHCARD));
        assertTrue(tipos.contains(TipoPregunta.AUDIO));

        // Añadimos otra pregunta y verificamos los tipos
        bloqueContenido.addPregunta(preguntaMock3);
        tipos = bloqueContenido.getTiposPreguntas();
        assertEquals(3, tipos.size());
        assertTrue(tipos.contains(TipoPregunta.IMAGENES));
    }

    @Test
    void testGetNumPreguntas() {
        assertEquals(2, bloqueContenido.getNumPreguntas());
        bloqueContenido.addPregunta(preguntaMock3);
        assertEquals(3, bloqueContenido.getNumPreguntas());
    }
    
    @Test
    void testGetPreguntasInvertidas() {
        Set<Pregunta> invertidas = bloqueContenido.getPreguntasInvertidas();
        assertEquals(bloqueContenido.getPreguntas().size(), invertidas.size());
        assertTrue(invertidas.containsAll(bloqueContenido.getPreguntas()));
        // No se puede garantizar orden en Set, salvo que FactoriaAprendizaje lo implemente
    }

    @Test
    void testGetPreguntasAleatoriamente() {
        Set<Pregunta> aleatorias = bloqueContenido.getPreguntasAleatoriamente();
        assertEquals(bloqueContenido.getPreguntas().size(), aleatorias.size());
        assertTrue(aleatorias.containsAll(bloqueContenido.getPreguntas()));
        // No se puede garantizar orden porque es aleatorio, pero deben contener las mismas preguntas
    }
}

