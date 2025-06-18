package umu.pds.duolingoBaratero.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitarios para la clase {@link Estadistica}
 */
public class EstadisticaTest {

    private Estadistica estadistica;
    private Usuario usuarioMock;

    /**
     * Inicializa una nueva instancia de Estadistica antes de cada test
     */
    @BeforeEach
    void setUp() {
        usuarioMock = Mockito.mock(Usuario.class);
        estadistica = new Estadistica(usuarioMock);
    }

    /**
     * Verifica que al registrar un acierto, el porcentaje de aciertos sea 100%
     */
    @Test
    void testActualizarAciertosCorrecto() {
        estadistica.actualizarAciertos(true);
        assertEquals(100.0, estadistica.getPorcentajeAciertos());
    }

    /**
     * Verifica que al registrar una respuesta incorrecta, el porcentaje de aciertos sea 0%
     */
    @Test
    void testActualizarAciertosIncorrecto() {
        estadistica.actualizarAciertos(false);
        assertEquals(0.0, estadistica.getPorcentajeAciertos());
    }

    /**
     * Verifica que al registrar una respuesta correcta y una incorrecta, el porcentaje sea 50%
     */
    @Test
    void testActualizarAciertosMixto() {
        estadistica.actualizarAciertos(true);
        estadistica.actualizarAciertos(false);
        assertEquals(50.0, estadistica.getPorcentajeAciertos());
    }

    /**
     * Verifica que incrementar la racha de victorias funcione correctamente
     */
    @Test
    void testIncrementarRachaVictorias() {
        estadistica.incrementarRachaVictorias();
        assertEquals(1, estadistica.getRachaVictorias());
    }

    /**
     * Verifica que reiniciar la racha de victorias la deje en 0
     */
    @Test
    void testResetRachaVictorias() {
        estadistica.incrementarRachaVictorias();
        estadistica.resetRachaVictorias();
        assertEquals(0, estadistica.getRachaVictorias());
    }

    /**
     * Verifica que iniciar sesión incremente el contador de accesos
     */
    @Test
    void testIniciarSesionYContadorAccesos() {
        int accesoInicial = estadistica.getNumAccesos();
        estadistica.iniciarSesion();
        assertEquals(accesoInicial + 1, estadistica.getNumAccesos());
    }

    /**
     * Verifica que al cerrar sesión se incremente el tiempo de uso
     */
    @Test
    void testCerrarSesionAumentaTiempoUso() throws InterruptedException {
        estadistica.iniciarSesion();
        Thread.sleep(50); // pequeña pausa simulada
        estadistica.cerrarSesion();
        assertTrue(estadistica.getTiempoUsoTotalActual() > 0);
    }

    /**
     * Verifica que el tiempo total de uso incluye la sesión en curso
     */
    @Test
    void testGetTiempoUsoTotalActualIncluyeSesion() throws InterruptedException {
        estadistica.iniciarSesion();
        Thread.sleep(50);
        long tiempo = estadistica.getTiempoUsoTotalActual();
        assertTrue(tiempo > 0);
    }

    /**
     * Verifica el correcto funcionamiento de los métodos getter y setter de Usuario
     */
    @Test
    void testSetYGetUsuario() {
        assertEquals(usuarioMock, estadistica.getUsuario());
        Usuario nuevoUsuario = Mockito.mock(Usuario.class);
        estadistica.setUsuario(nuevoUsuario);
        assertEquals(nuevoUsuario, estadistica.getUsuario());
    }
}
