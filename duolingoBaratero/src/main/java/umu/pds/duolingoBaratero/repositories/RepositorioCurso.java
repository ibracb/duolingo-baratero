package umu.pds.duolingoBaratero.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.BloqueContenido;

/**
 * RepositorioCurso es un singleton que actúa como repositorio de cursos.
 */
public enum RepositorioCurso {
	
	/**
	 * Instancia única del repositorio.
	 */
    INSTANCE;

	/**
	 * Mapa que almacena las plantillas de cursos, donde la clave es el ID del curso.
	 */
    private final Map<Long, CursoPlantilla> cursoPlantillas = new HashMap<>();
    
    /**
     * Mapa que almacena los cursos en progreso, donde la clave es el ID del curso.
     */
    private final Map<Long, CursoEnProgreso> cursosEnProgreso = new HashMap<>();
    
    /**
	 * Mapa que almacena los bloques de contenido, donde la clave es el ID del bloque.
	 */
    private final Map<Long, BloqueContenido> bloquesContenido = new HashMap<>();

    /**
     * Agrega un curso plantilla al repositorio.
     * @param curso
     */
    public void agregarCursoPlantilla(CursoPlantilla curso) {
        cursoPlantillas.put(curso.getId(), curso);
    }

    /**
	 * Obtiene un curso plantilla por su ID.
	 * @param id
	 * @return CursoPlantilla
	 */
    public CursoPlantilla obtenerCursoPlantilla(long id) {
        return cursoPlantillas.get(id);
    }

    /**
     * 
     * @return Collection de todas las plantillas de cursos.
     */
    public Collection<CursoPlantilla> obtenerTodosCursoPlantillas() {
        return cursoPlantillas.values();
    }

    /**
     * agrega un curso en progreso al repositorio.
     * @param curso
     */
    public void agregarCursoEnProgreso(CursoEnProgreso curso) {
        cursosEnProgreso.put(curso.getId(), curso);
    }

    /**
	 * Obtiene un curso en progreso por su ID.
	 * @param id
	 * @return CursoEnProgreso
	 */
    public CursoEnProgreso obtenerCursoEnProgreso(long id) {
        return cursosEnProgreso.get(id);
    }

    /**
	 * 
	 * @return Collection de todos los cursos en progreso.
	 */
    public Collection<CursoEnProgreso> obtenerTodosCursosEnProgreso() {
        return cursosEnProgreso.values();
    }

    
    /**
	 * Agrega un bloque de contenido al repositorio.
	 * @param bloque
	 */
    public void agregarBloqueContenido(BloqueContenido bloque) {
        bloquesContenido.put(bloque.getId(), bloque);
    }

    /**
     * Recupera un bloque de contenido por su ID.
     * @param id
     * @return BloqueContenido
     */
    public BloqueContenido obtenerBloqueContenido(long id) {
        return bloquesContenido.get(id);
    }

    /**
	 * 
	 * @return Collection de todos los bloques de contenido.
	 */
    public Collection<BloqueContenido> obtenerTodosBloquesContenido() {
        return bloquesContenido.values();
    }
}
