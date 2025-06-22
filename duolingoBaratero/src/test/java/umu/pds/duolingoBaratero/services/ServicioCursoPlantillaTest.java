package umu.pds.duolingoBaratero.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import umu.pds.duolingoBaratero.models.*;
import umu.pds.duolingoBaratero.persistence.*;
import umu.pds.duolingoBaratero.services.serializers.SerializerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioCursoPlantillaTest {

    private DBCursoPlantillaDAO cursoPlantillaDAO;
    private DBCursoEnProgresoDAO cursoEnProgresoDAO;
    private DBBloqueContenidoDAO bloqueContenidoDAO;
    private DBPreguntaDAO preguntaDAO;
    private SerializerFactory serializerFactory;
    private ServicioCursoPlantilla servicio;

    @BeforeEach
    public void setUp() {
        cursoPlantillaDAO = mock(DBCursoPlantillaDAO.class);
        cursoEnProgresoDAO = mock(DBCursoEnProgresoDAO.class);
        bloqueContenidoDAO = mock(DBBloqueContenidoDAO.class);
        preguntaDAO = mock(DBPreguntaDAO.class);
        serializerFactory = mock(SerializerFactory.class);

        servicio = new ServicioCursoPlantilla(
            cursoPlantillaDAO,
            cursoEnProgresoDAO,
            bloqueContenidoDAO,
            preguntaDAO,
            serializerFactory
        );
    }

    @Test
    public void testObtenerTodosLosCursos() {
        List<CursoPlantilla> cursos = List.of(new CursoPlantilla("Curso1", "User", "Desc", "Obj"));
        when(cursoPlantillaDAO.getAll()).thenReturn(cursos);
        assertEquals(1, servicio.obtenerTodosLosCursos().size());
    }

    @Test
    public void testBuscarCursoExistentePorNombre() {
        CursoPlantilla curso = new CursoPlantilla("Ingles", "User", "Desc", "Obj");
        when(cursoPlantillaDAO.getAll()).thenReturn(List.of(curso));
        Optional<CursoPlantilla> resultado = servicio.buscarCursoPorNombre("Ingles");
        assertTrue(resultado.isPresent());
        assertEquals("Ingles", resultado.get().getNombre());
    }

    @Test
    public void testBuscarCursoNoExistentePorNombre() {
        assertTrue(servicio.buscarCursoPorNombre(null).isEmpty());
        assertTrue(servicio.buscarCursoPorNombre("  ").isEmpty());
    }

    @Test
    public void testCrearCursoPlantilla() {
        when(cursoPlantillaDAO.getAll()).thenReturn(Collections.emptyList());
        CursoPlantilla creado = servicio.crearCursoPlantilla("Frances", "Alice", "Desc", "Obj");
        assertEquals("Frances", creado.getNombre());
    }

    @Test
    public void testCrearCursoPlantillaYaExistente() {
        CursoPlantilla existente = new CursoPlantilla("Frances", "Alice", "Desc", "Obj");
        when(cursoPlantillaDAO.getAll()).thenReturn(List.of(existente));
        assertThrows(IllegalArgumentException.class, () -> {
            servicio.crearCursoPlantilla("Frances", "Alice", "Desc", "Obj");
        });
    }

    @Test
    public void testCrearCursoEnProgresoPorNombre() {
        Usuario usuario = new Usuario("A", "B", "mail", "pw");
        CursoPlantilla curso = new CursoPlantilla("Java", "dev", "desc", "obj");
        when(cursoPlantillaDAO.getAll()).thenReturn(List.of(curso));
        CursoEnProgreso cursoProg = servicio.crearCursoEnProgreso("Java", usuario);
        assertNotNull(cursoProg);
    }

    @Test
    public void testEliminarCurso() {
        CursoPlantilla curso = new CursoPlantilla("Python", "Bob", "desc", "obj");
        when(cursoPlantillaDAO.getAll()).thenReturn(List.of(curso));
        boolean result = servicio.eliminarCurso("Python");
        assertTrue(result);
    }

    @Test
    public void testEliminarCursoQueeNoExiste() {
        when(cursoPlantillaDAO.getAll()).thenReturn(Collections.emptyList());
        boolean result = servicio.eliminarCurso("Scala");
        assertFalse(result);
    }
}
