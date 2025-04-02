package umu.pds.duolingoBaratero.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.BloqueContenido;

/**
 * Este es un repositorio de pruebas no tiene pq parecerse al definitivo
 */
public enum RepositorioCurso {
    INSTANCE;

    private final Map<Long, CursoPlantilla> cursoPlantillas = new HashMap<>();
    private final Map<Long, CursoEnProgreso> cursosEnProgreso = new HashMap<>();
    private final Map<Long, BloqueContenido> bloquesContenido = new HashMap<>();

    // Métodos para CursoPlantilla
    public void agregarCursoPlantilla(CursoPlantilla curso) {
        cursoPlantillas.put(curso.getId(), curso);
    }

    public CursoPlantilla obtenerCursoPlantilla(long id) {
        return cursoPlantillas.get(id);
    }

    public Collection<CursoPlantilla> obtenerTodosCursoPlantillas() {
        return cursoPlantillas.values();
    }

    // Métodos para CursoEnProgreso
    public void agregarCursoEnProgreso(CursoEnProgreso curso) {
        cursosEnProgreso.put(curso.getId(), curso);
    }

    public CursoEnProgreso obtenerCursoEnProgreso(long id) {
        return cursosEnProgreso.get(id);
    }

    public Collection<CursoEnProgreso> obtenerTodosCursosEnProgreso() {
        return cursosEnProgreso.values();
    }

    // Métodos para BloqueContenido
    public void agregarBloqueContenido(BloqueContenido bloque) {
        bloquesContenido.put(bloque.getId(), bloque);
    }

    public BloqueContenido obtenerBloqueContenido(long id) {
        return bloquesContenido.get(id);
    }

    public Collection<BloqueContenido> obtenerTodosBloquesContenido() {
        return bloquesContenido.values();
    }
}
