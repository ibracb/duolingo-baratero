package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Interfaz para filtros que procesan listas de CursoPlantilla.
 */
public interface Filtro {

    /**
     * Filtra una lista de cursos según algún criterio.
     *
     * @param lista Lista de cursos a filtrar.
     * @return Lista filtrada de cursos.
     */
    List<CursoPlantilla> filtrar(List<CursoPlantilla> lista);
}