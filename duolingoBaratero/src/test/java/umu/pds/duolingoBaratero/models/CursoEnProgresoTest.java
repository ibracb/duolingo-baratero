package umu.pds.duolingoBaratero.models;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Set;
import java.util.List;

public class CursoEnProgresoTest {

    private CursoEnProgreso cursoEnProgreso;
    private Usuario usuarioMock;
    private CursoPlantilla cursoPlantillaMock;
    private Aprendizaje aprendizajeMock;
    private Valoracion valoracionMock;
    private BloqueContenidoProgreso bloqueMock1;
    private BloqueContenidoProgreso bloqueMock2;
    private BloqueContenido bloqueContenidoMock;
    private Pregunta preguntaMock;
    private PreguntaProgreso preguntaProgresoMock;

    @BeforeEach
    void setUp() {
        // Mocks de dependencias
        usuarioMock = Mockito.mock(Usuario.class);
        cursoPlantillaMock = Mockito.mock(CursoPlantilla.class);
        aprendizajeMock = Aprendizaje.SECUENCIAL;  // Enum, no necesita mock
        valoracionMock = Mockito.mock(Valoracion.class);
        bloqueMock1 = Mockito.mock(BloqueContenidoProgreso.class);
        bloqueMock2 = Mockito.mock(BloqueContenidoProgreso.class);
        bloqueContenidoMock = Mockito.mock(BloqueContenido.class);
        preguntaMock = Mockito.mock(Pregunta.class);
        preguntaProgresoMock = Mockito.mock(PreguntaProgreso.class);
        
        Mockito.when(bloqueMock1.getBloqueContenido()).thenReturn(bloqueContenidoMock);

        // Configuración de métodos mock
        Mockito.when(cursoPlantillaMock.getNombre()).thenReturn("Curso de Prueba");
        Mockito.when(cursoPlantillaMock.getDescripcion()).thenReturn("Descripción de prueba");
        Mockito.when(cursoPlantillaMock.getObjetivos()).thenReturn("Aprender Mockito");
        Mockito.when(cursoPlantillaMock.getPreguntasDeBloque(1L)).thenReturn(Arrays.asList(preguntaMock));
        Mockito.when(bloqueMock1.getPreguntasAleatoriamente()).thenReturn(Arrays.asList(preguntaProgresoMock));
        Mockito.when(bloqueMock1.getPreguntasSecuencialmente()).thenReturn(Set.of(preguntaProgresoMock));

        Mockito.when(valoracionMock.getValor()).thenReturn(5);

        // Inicializar CursoEnProgreso
        cursoEnProgreso = new CursoEnProgreso(usuarioMock, cursoPlantillaMock, aprendizajeMock, valoracionMock, bloqueMock1, bloqueMock2);
    }

    @Test
    void testGetters() {
        assertEquals("Curso de Prueba", cursoEnProgreso.getNombre());
        assertEquals("Descripción de prueba", cursoEnProgreso.getDescripcion());
        assertEquals("Aprender Mockito", cursoEnProgreso.getObjetivos());
        assertEquals(usuarioMock, cursoEnProgreso.getEstudiante());
        assertEquals(cursoPlantillaMock, cursoEnProgreso.getCursoPlantilla());
        assertEquals(aprendizajeMock, cursoEnProgreso.getAprendizaje());
        assertEquals(valoracionMock, cursoEnProgreso.getValoracion());
        assertEquals(5, cursoEnProgreso.getValoracionNumerica());
    }

    @Test
    void testSetters() {
        Usuario nuevoUsuarioMock = Mockito.mock(Usuario.class);
        cursoEnProgreso.setEstudiante(nuevoUsuarioMock);
        assertEquals(nuevoUsuarioMock, cursoEnProgreso.getEstudiante());

        CursoPlantilla nuevoCursoMock = Mockito.mock(CursoPlantilla.class);
        cursoEnProgreso.setCursoPlantilla(nuevoCursoMock);
        assertEquals(nuevoCursoMock, cursoEnProgreso.getCursoPlantilla());

        Valoracion nuevaValoracionMock = Mockito.mock(Valoracion.class);
        Mockito.when(nuevaValoracionMock.getValor()).thenReturn(10);
        cursoEnProgreso.setValoracion(nuevaValoracionMock);
        assertEquals(10, cursoEnProgreso.getValoracionNumerica());

        Aprendizaje nuevoAprendizaje = Aprendizaje.ALEATORIO;
        cursoEnProgreso.setAprendizaje(nuevoAprendizaje);
        assertEquals(nuevoAprendizaje, cursoEnProgreso.getAprendizaje());
    }

    @Test
    void testGetContenidosProgreso() {
        List<BloqueContenidoProgreso> contenidos = cursoEnProgreso.getContenidosProgreso();
        assertEquals(2, contenidos.size());
        assertTrue(contenidos.contains(bloqueMock1));
        assertTrue(contenidos.contains(bloqueMock2));
    }

    @Test
    void testEstados() {
        assertTrue(cursoEnProgreso.isNuevo());

        cursoEnProgreso.iniciar();
        assertTrue(cursoEnProgreso.isEnMarcha());

        cursoEnProgreso.finalizar();
        assertTrue(cursoEnProgreso.isFinalizado());
    }

    @Test
    void testEstadosCambio() {
        // De EstadoNuevo a EstadoEnMarcha
        cursoEnProgreso.iniciar();
        assertTrue(cursoEnProgreso.getEstado() instanceof EstadoEnMarcha);

        // De EstadoEnMarcha a EstadoFinalizado
        cursoEnProgreso.finalizar();
        assertTrue(cursoEnProgreso.getEstado() instanceof EstadoFinalizado);
    }

    @Test
    void testEqualsAndHashCode() {
        CursoEnProgreso otroCurso = new CursoEnProgreso(usuarioMock, cursoPlantillaMock, aprendizajeMock, valoracionMock, bloqueMock1);
        assertEquals(cursoEnProgreso, otroCurso);
        assertEquals(cursoEnProgreso.hashCode(), otroCurso.hashCode());

        CursoEnProgreso cursoDiferente = new CursoEnProgreso(Mockito.mock(Usuario.class), cursoPlantillaMock, aprendizajeMock, valoracionMock);
        assertNotEquals(cursoEnProgreso, cursoDiferente);
    }

    @Test
    void testGetNumLastBloqueContenido() {
        assertEquals(69, cursoEnProgreso.getNumLastBloqueContenido());
    }
    
    @Test
    void testGetPreguntasBloqueContenido() {
        List<Pregunta> preguntas = cursoEnProgreso.getPreguntasBloqueContenido(1L);
        assertNotNull(preguntas);
        assertEquals(1, preguntas.size());
        assertEquals(preguntaMock, preguntas.get(0));
    }

    @Test
    void testGetTodasLasPreguntasSecuencial() {
        List<PreguntaProgreso> preguntas = cursoEnProgreso.getTodasLasPreguntas();
        System.out.println(preguntas);
        assertNotNull(preguntas);
        assertEquals(1, preguntas.size());
        assertInstanceOf(PreguntaProgreso.class, preguntas.get(0)); // Verifica el tipo correcto
    }

    @Test
    void testGetTodasLasPreguntasAleatorio() {
        cursoEnProgreso.setAprendizaje(Aprendizaje.ALEATORIO);
        List<PreguntaProgreso> preguntas = cursoEnProgreso.getTodasLasPreguntas();
        System.out.println(preguntas);
        assertNotNull(preguntas);
        assertEquals(1, preguntas.size());
        assertInstanceOf(PreguntaProgreso.class, preguntas.get(0));
    }
}
