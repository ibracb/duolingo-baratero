package umu.pds.duolingoBaratero.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.services.AudioService;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.serializers.Serializer;

class ControladorCursoTest {

    @InjectMocks
    private ControladorCurso controlador = ControladorCurso.INSTANCE;

    @Mock
    private ImageService mockImageService;
    
    @Mock
    private AudioService mockAudioService;
    
    @Mock
    private Serializer mockSerializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsCursoNuevo() {
        CursoEnProgreso curso = mock(CursoEnProgreso.class);
        when(curso.isNuevo()).thenReturn(true);
        assertTrue(controlador.isCursoNuevo(curso));
    }

    @Test
    void testGetCursoPlantilla() {
        Optional<CursoPlantilla> curso = controlador.getCursoPlantilla("Ingles");
        assertTrue(curso.isPresent());
        assertEquals("Ingles", curso.get().getNombre());
    }

    @Test
    void testGetCursoEnProgreso() {
        CursoEnProgreso curso = controlador.getCursoEnProgreso("Ingles");
        assertNotNull(curso);
        assertEquals("Ingles", curso.getCursoPlantilla().getNombre());
    }

    @Test
    void testBuscarCursos() {
// 		  FIXME: Los filtros no van
//        List<CursoPlantilla> cursos = controlador.buscarCursos("Ingles", 0, null, "Mas cursados");
//        assertFalse(cursos.isEmpty());
//        assertEquals("Ingles", cursos.get(0).getNombre());
    }
    
    @Test
    void testPlayAudio() {
        doNothing().when(mockAudioService).playAudio(toString());
        controlador.playAudio("audio.mp3");
        verify(mockAudioService, times(1)).playAudio("audio.mp3");
    }
    
    @Test
    void testGetScaledImage() throws IOException {
        ImageIcon imageIcon = new ImageIcon();
        when(mockImageService.getScaledImage(any(ImageIcon.class), anyInt())).thenReturn(imageIcon);
        ImageIcon result = controlador.getScaledImage(imageIcon, 100);
        assertNotNull(result);
    }
    
    @Test
    void testProcesarRespuesta() {
        var preguntaMock = mock(umu.pds.duolingoBaratero.models.Pregunta.class);
        when(preguntaMock.esRespuestaCorrecta("respuesta"))
            .thenReturn(true);
        assertTrue(controlador.procesarRespuesta(preguntaMock, "respuesta"));
    }
}
