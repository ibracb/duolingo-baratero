package umu.pds.duolingoBaratero.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;

public class BloqueContenidoProgresoTest {
    private BloqueContenidoProgreso progreso;
    private BloqueContenido bloque;
    private CursoEnProgreso curso;

    @BeforeEach
    void setUp() {
        curso = new CursoEnProgreso(null, null, null, null, null);
        bloque = new BloqueContenido(1L);
        progreso = new BloqueContenidoProgreso(curso, bloque);
    }

    @Test
    void testEstadoInicial() {
        assertEquals(EstadoBloqueContenido.INICIO, progreso.getEstado());
    }
}