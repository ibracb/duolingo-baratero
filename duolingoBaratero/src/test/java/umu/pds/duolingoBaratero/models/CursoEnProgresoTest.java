package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

class CursoEnProgresoTest {

    private CursoEnProgreso cursoEnProgreso;
    private CursoPlantilla cursoPlantillaMock;
    private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        cursoPlantillaMock = Mockito.mock(CursoPlantilla.class);
        usuarioMock = Mockito.mock(Usuario.class);
        cursoEnProgreso = new CursoEnProgreso(cursoPlantillaMock, usuarioMock);
    }

    /**
     * Test del constructor y getters básicos.
     */
    @Test
    void testConstructorYGetters() {
        assertEquals(cursoPlantillaMock, cursoEnProgreso.getCursoPlantilla());
        assertEquals(usuarioMock, cursoEnProgreso.getUsuario());
        assertEquals(0, cursoEnProgreso.getBloqueActual());
        assertEquals(EstadoCursoEnProgreso.NUEVO, cursoEnProgreso.getEstado());
    }

    /**
     * Test que verifica avanzarBloqueActual cuando se aprueba un bloque.
     * Debe incrementar bloqueActual y finalizar si es el último bloque.
     */
    @Test
    void testAvanzarBloqueActual_Aprobado() {
    	cursoEnProgreso.iniciar();
        Mockito.when(cursoPlantillaMock.isCursoFinalizado(1)).thenReturn(true);

        cursoEnProgreso.avanzarBloqueActual(true);

        assertEquals(EstadoCursoEnProgreso.FINALIZADO, cursoEnProgreso.getEstado());
        assertEquals(0, cursoEnProgreso.getBloqueActual()); // reiniciado tras finalizar
    }

    /**
     * Test que verifica que avanzarBloqueActual sin aprobar no cambia nada.
     */
    @Test
    void testAvanzarBloqueActual_NoAprobado() {
        cursoEnProgreso.setBloqueActual(2);
        cursoEnProgreso.avanzarBloqueActual(false);

        assertEquals(2, cursoEnProgreso.getBloqueActual());
        assertEquals(EstadoCursoEnProgreso.NUEVO, cursoEnProgreso.getEstado());
    }

    /**
     * Test que verifica la obtención de preguntas del bloque actual y específico.
     */
    @SuppressWarnings("unchecked")
	@Test
    void testGetPreguntasBloqueContenido() {
        Set<Pregunta> preguntasMock = Mockito.mock(Set.class);
        Mockito.when(cursoPlantillaMock.getPreguntasDeBloque(0)).thenReturn(preguntasMock);
        Mockito.when(cursoPlantillaMock.getPreguntasDeBloque(5)).thenReturn(preguntasMock);

        assertEquals(preguntasMock, cursoEnProgreso.getPreguntasBloqueContenido());
        assertEquals(preguntasMock, cursoEnProgreso.getPreguntasBloqueContenido(5));
    }

    /**
     * Test que verifica iniciar un curso en estado NUEVO cambia a EN_MARCHA.
     */
    @Test
    void testIniciarDesdeNuevo() {
        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.NUEVO);
        cursoEnProgreso.iniciar();
        assertEquals(EstadoCursoEnProgreso.EN_MARCHA, cursoEnProgreso.getEstado());
    }

    /**
     * Test que iniciar en estado distinto a NUEVO lanza excepción.
     */
    @Test
    void testIniciarEstadoInvalido() {
        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.EN_MARCHA);
        assertThrows(IllegalStateException.class, () -> cursoEnProgreso.iniciar());
    }

    /**
     * Test que verifica finalizar un curso en estado EN_MARCHA.
     */
    @Test
    void testFinalizarDesdeEnMarcha() {
        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.EN_MARCHA);
        cursoEnProgreso.setBloqueActual(3);
        cursoEnProgreso.finalizar();

        assertEquals(EstadoCursoEnProgreso.FINALIZADO, cursoEnProgreso.getEstado());
        assertEquals(0, cursoEnProgreso.getBloqueActual());
    }

    /**
     * Test que finalizar en estado distinto a EN_MARCHA lanza excepción.
     */
    @Test
    void testFinalizarEstadoInvalido() {
        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.NUEVO);
        assertThrows(IllegalStateException.class, () -> cursoEnProgreso.finalizar());
    }

    /**
     * Test que reiniciar un curso finalizado pone estado NUEVO.
     */
    @Test
    void testReiniciarDesdeFinalizado() {
        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.FINALIZADO);
        cursoEnProgreso.reiniciar();
        assertEquals(EstadoCursoEnProgreso.NUEVO, cursoEnProgreso.getEstado());
    }

    /**
     * Test que reiniciar en estado distinto a FINALIZADO lanza excepción.
     */
    @Test
    void testReiniciarEstadoInvalido() {
        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.NUEVO);
        assertThrows(IllegalStateException.class, () -> cursoEnProgreso.reiniciar());
    }

    /**
     * Test que verifica los métodos isNuevo, isEnMarcha e isFinalizado.
     */
    @Test
    void testIsEstado() {
        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.NUEVO);
        assertTrue(cursoEnProgreso.isNuevo());
        assertFalse(cursoEnProgreso.isEnMarcha());
        assertFalse(cursoEnProgreso.isFinalizado());

        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.EN_MARCHA);
        assertFalse(cursoEnProgreso.isNuevo());
        assertTrue(cursoEnProgreso.isEnMarcha());
        assertFalse(cursoEnProgreso.isFinalizado());

        cursoEnProgreso.setEstado(EstadoCursoEnProgreso.FINALIZADO);
        assertFalse(cursoEnProgreso.isNuevo());
        assertFalse(cursoEnProgreso.isEnMarcha());
        assertTrue(cursoEnProgreso.isFinalizado());
    }

}

