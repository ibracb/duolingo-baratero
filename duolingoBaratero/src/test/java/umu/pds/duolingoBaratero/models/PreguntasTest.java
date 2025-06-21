package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import umu.pds.duolingoBaratero.windows.vista.PanelFlashcard;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaAudio;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaImagenes;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaOpciones;

class PreguntasTest {

    private PreguntaOpciones preguntaOpciones;
    private PreguntaAudio preguntaAudio;
    private Flashcard flashcard;

    @SuppressWarnings("unused")
	@BeforeEach
    void setUp() {
        String[] opciones = {"Opción A", "Opción B", "Opción C"};

        // Creación de preguntas de diferentes tipos
      preguntaOpciones = new PreguntaOpciones(Nivel.BASICO, 1, "¿Cuál es la capital de Francia?", "París", TipoPregunta.OPCIONES, Arrays.asList(opciones));
        preguntaAudio = new PreguntaAudio(Nivel.INTERMEDIO, 2, "Escucha y elige la respuesta correcta", "Bonjour",  Arrays.asList(opciones), "/ruta/audio.mp3");
        flashcard = new Flashcard(Nivel.AVANZADO, 3, "Flashcard", "acierto", TipoPregunta.FLASHCARD);
    }

    @Test
    void testPreguntaOpciones_CrearPanel() {
        JPanel panel = preguntaOpciones.crearPanel();
        assertNotNull(panel);
        assertTrue(panel instanceof PanelPreguntaOpciones);
    }

    @Test
    void testPreguntaAudio_CrearPanel() {
        JPanel panel = preguntaAudio.crearPanel();
        assertNotNull(panel);
        assertTrue(panel instanceof PanelPreguntaAudio);
    }

    @Test
    void testFlashcard_CrearPanel() {
        JPanel panel = flashcard.crearPanel();
        assertNotNull(panel);
        assertTrue(panel instanceof PanelFlashcard);
    }

    @Test
    void testFlashcard_EsRespuestaCorrecta() {
        assertTrue(flashcard.esRespuestaCorrecta("acierto"));
        assertFalse(flashcard.esRespuestaCorrecta("fallo"));
    }

    @SuppressWarnings("unused")
	@Test
    void testPreguntaOpciones_GettersSetters() {
        String[] nuevasOpciones = {"Nueva A", "Nueva B", "Nueva C"};
//        preguntaOpciones.setOpciones(nuevasOpciones);
//        assertArrayEquals(nuevasOpciones, preguntaOpciones.getOpciones());
    }

    @Test
    void testPreguntaAudio_GettersSetters() {
        String nuevaRuta = "/nuevo/audio.mp3";
        preguntaAudio.setRutaAudio(nuevaRuta);
        assertEquals(nuevaRuta, preguntaAudio.getRutaAudio());
    }

}
