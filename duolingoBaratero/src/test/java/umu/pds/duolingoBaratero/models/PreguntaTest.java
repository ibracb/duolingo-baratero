package umu.pds.duolingoBaratero.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JPanel;

public class PreguntaTest {

    private Pregunta pregunta;

    @BeforeEach
    public void setUp() {
        // Creamos una implementación concreta de Pregunta para poder testear
        pregunta = new PreguntaConcreta(Nivel.BASICO, 1, "¿Cuál es la capital de Francia?", "París", TipoPregunta.FLASHCARD);
    }

    @Test
    public void testConstructor() {
        assertNotNull(pregunta);
        assertEquals(Nivel.BASICO, pregunta.getNivel());
        assertEquals(1, pregunta.getNumero());
        assertEquals("¿Cuál es la capital de Francia?", pregunta.getPregunta());
        assertEquals("París", pregunta.getRespuestaCorrecta());
        assertEquals(TipoPregunta.FLASHCARD, pregunta.getTipo());
    }

    @Test
    public void testEsRespuestaCorrecta() {
        assertTrue(pregunta.esRespuestaCorrecta("París"));
        assertFalse(pregunta.esRespuestaCorrecta("Madrid"));
    }

    @Test
    public void testIsImagen() {
        assertFalse(pregunta.isImagen()); // TipoPregunta.TEXTO
        pregunta.setTipo(TipoPregunta.IMAGENES);
        assertTrue(pregunta.isImagen()); // TipoPregunta.IMAGEN
    }

    @Test
    public void testCompararPreguntas() {
        Pregunta otraPregunta = new PreguntaConcreta(Nivel.AVANZADO, 2, "¿Capital de España?", "Madrid", TipoPregunta.FLASHCARD);
        assertTrue(pregunta.compareTo(otraPregunta) < 0); // pregunta.numero = 1, otraPregunta.numero = 2
    }

    @Test
    public void testSettersYGetters() {
        pregunta.setPregunta("¿Cuál es la capital de Italia?");
        assertEquals("¿Cuál es la capital de Italia?", pregunta.getPregunta());

        pregunta.setRespuestaCorrecta("Roma");
        assertEquals("Roma", pregunta.getRespuestaCorrecta());

        pregunta.setNumero(3);
        assertEquals(3, pregunta.getNumero());

        pregunta.setNivel(Nivel.INTERMEDIO);
        assertEquals(Nivel.INTERMEDIO, pregunta.getNivel());

        pregunta.setTipo(TipoPregunta.IMAGENES);
        assertEquals(TipoPregunta.IMAGENES, pregunta.getTipo());
    }

    @Test
    public void testCrearPanel() {
        JPanel panel = pregunta.crearPanel();
        assertNotNull(panel);
    }

    // Clase concreta para probar Pregunta
    private static class PreguntaConcreta extends Pregunta {

        public PreguntaConcreta(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
            super(nivel, numero, pregunta, respuestaCorrecta, tipo);
        }

        @Override
        public JPanel crearPanel() {
            // Devolver un panel vacío para probar
            return new JPanel();
        }
    }
}
