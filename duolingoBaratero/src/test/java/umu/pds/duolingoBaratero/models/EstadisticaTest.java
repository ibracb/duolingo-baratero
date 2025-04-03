package umu.pds.duolingoBaratero.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class EstadisticaTest {

    private Estadistica estadistica;
    private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        usuarioMock = Mockito.mock(Usuario.class);
        estadistica = new Estadistica(usuarioMock);
    }

    @Test
    void testInicializacionValores() {
        assertEquals(0.0, estadistica.getTiempoUso());
        assertEquals(0, estadistica.getNumAccesos());
        assertEquals(0, estadistica.getRachaVictorias());
        assertEquals(0.0, estadistica.getPorcentajeAciertos());
    }

    @Test
    void testSettersYGetters() {
        estadistica.setTiempoUso(120.5);
        assertEquals(120.5, estadistica.getTiempoUso());

        estadistica.setNumAccesos(10);
        assertEquals(10, estadistica.getNumAccesos());

        estadistica.setRachaVictorias(5);
        assertEquals(5, estadistica.getRachaVictorias());

        estadistica.setPorcentajeAciertos(80.5);
        assertEquals(80.5, estadistica.getPorcentajeAciertos());
    }

    @Test
    void testGetYSetUsuario() {
        Usuario nuevoUsuario = Mockito.mock(Usuario.class);
        estadistica.setUsuario(nuevoUsuario);
        assertEquals(nuevoUsuario, estadistica.getUsuario());
    }
}
