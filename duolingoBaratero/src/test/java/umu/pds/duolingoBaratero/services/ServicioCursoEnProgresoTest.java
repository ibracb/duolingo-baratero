package umu.pds.duolingoBaratero.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import umu.pds.duolingoBaratero.models.*;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.persistence.DBCursoEnProgresoDAO;

public class ServicioCursoEnProgresoTest {

    private ServicioCursoProgreso servicio;
    private DBCursoEnProgresoDAO cursoEnProgresoDAO;

    @BeforeEach
    void inicializaar() {
        cursoEnProgresoDAO = mock(DBCursoEnProgresoDAO.class);
        servicio = new ServicioCursoProgreso(cursoEnProgresoDAO);
    }

    @Test
    void crearCurzoEnProgreso() {
        CursoPlantilla plantilla = new CursoPlantilla("A1", "Alice", "desc", "obj");
        Usuario usuario = new Usuario("u", "n", "c", "pw");
        CursoEnProgreso curso = servicio.crearCursoEnProgreso(plantilla, usuario);
        assertEquals(plantilla, curso.getCursoPlantilla());
        assertEquals(usuario, curso.getUsuario());
    }

    @Test
    void pruebaSetAprendisaje() {
        CursoEnProgreso curso = mock(CursoEnProgreso.class);
        AprendizajeSeleccionado aprendizaje = mock(AprendizajeSeleccionado.class);
        assertTrue(servicio.setAprendizaje(curso, aprendizaje));
        verify(curso).setAprendizaje(aprendizaje);
    }

    @Test
    void testEstadoNuevoCurso() {
        CursoEnProgreso curso = mock(CursoEnProgreso.class);
        when(curso.isNuevo()).thenReturn(true);
        assertTrue(servicio.esCursoNuevo(curso));
    }

    @Test
    void testActualizaarCurso() {
        CursoEnProgreso curso = mock(CursoEnProgreso.class);
        assertTrue(servicio.actualizarCurso(curso));
        verify(cursoEnProgresoDAO).update(curso);
    }
}
