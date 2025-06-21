package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import java.util.stream.Collectors;

import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;

/**
 * Filtro que permite filtrar cursos por su nivel.
 * Este filtro se utiliza como parte del patrón Decorator.
 */
public class FiltroPorNivel extends FiltroDecorador{
	
	/**
	 * Nivel por el que se filtran los cursos.
	 */
    private final Nivel nivel;
    
    /**
	 * Constructor que inicializa el filtro y el nivel.
	 * 
	 * @param filtro Filtro que se va a decorar.
	 * @param nivel Nivel por el que se filtran los cursos.
	 */
    public FiltroPorNivel(Filtro filtro, Nivel nivel) {
        super(filtro);
        this.nivel = nivel;
    }
    
    @Override
    public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista){
        List<CursoPlantilla> l = lista.stream()
                .filter(c -> c.getNivel().equals(nivel))
                .collect(Collectors.toList());
        return super.filtrar(l);

    }

}