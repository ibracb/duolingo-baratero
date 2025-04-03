package umu.pds.duolingoBaratero.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class BloqueContenidoProgresoTest {

    private BloqueContenidoProgreso bloqueContenidoProgreso;
    private CursoEnProgreso cursoEnProgresoMock;
    private BloqueContenido bloqueContenidoMock;
    private PreguntaProgreso pregunta1;
    private PreguntaProgreso pregunta2;

    @BeforeEach
    void setUp() {
        // Se utilizan mocks para las dependencias
        cursoEnProgresoMock = Mockito.mock(CursoEnProgreso.class);
        bloqueContenidoMock = Mockito.mock(BloqueContenido.class);
        bloqueContenidoProgreso = new BloqueContenidoProgreso(cursoEnProgresoMock, bloqueContenidoMock);

        // Se crean mocks para las preguntas de progreso
        pregunta1 = Mockito.mock(PreguntaProgreso.class);
        pregunta2 = Mockito.mock(PreguntaProgreso.class);
        bloqueContenidoProgreso.setPreguntasProgreso(Arrays.asList(pregunta1, pregunta2));
    }

    @Test
    void testGettersAndSetters() {
        // Verifica curso y bloqueContenido
        assertEquals(cursoEnProgresoMock, bloqueContenidoProgreso.getCursoEnProgreso());
        assertEquals(bloqueContenidoMock, bloqueContenidoProgreso.getBloqueContenido());
        
        CursoEnProgreso nuevoCurso = Mockito.mock(CursoEnProgreso.class);
        bloqueContenidoProgreso.setCursoEnProgreso(nuevoCurso);
        assertEquals(nuevoCurso, bloqueContenidoProgreso.getCursoEnProgreso());
    }

    @Test
    void testEstadoBloqueContenido() {
        // El estado inicial debe ser INICIO (definido en el constructor)
        assertEquals(EstadoBloqueContenido.INICIO, bloqueContenidoProgreso.getEstado());
        
        // Se cambia el estado a EN_MARCHA y se verifica
        bloqueContenidoProgreso.setEstado(EstadoBloqueContenido.EN_MARCHA);
        assertEquals(EstadoBloqueContenido.EN_MARCHA, bloqueContenidoProgreso.getEstado());
        
        // Se cambia el estado a FIN y se verifica
        bloqueContenidoProgreso.setEstado(EstadoBloqueContenido.FIN);
        assertEquals(EstadoBloqueContenido.FIN, bloqueContenidoProgreso.getEstado());
    }

    @Test
    void testGetPreguntasAleatoriamente() {
        List<PreguntaProgreso> preguntasAleatorias = bloqueContenidoProgreso.getPreguntasAleatoriamente();
        // Se verifica que la lista contenga ambas preguntas
        assertEquals(2, preguntasAleatorias.size());
        assertTrue(preguntasAleatorias.contains(pregunta1));
        assertTrue(preguntasAleatorias.contains(pregunta2));
    }

    @Test
    void testGetPreguntasSecuencialmente() {
        Set<PreguntaProgreso> preguntasOrdenadas = bloqueContenidoProgreso.getPreguntasSecuencialmente();
        // Se verifica que el conjunto contenga ambas preguntas
        assertEquals(2, preguntasOrdenadas.size());
        assertTrue(preguntasOrdenadas.contains(pregunta1));
        assertTrue(preguntasOrdenadas.contains(pregunta2));
    }
}
