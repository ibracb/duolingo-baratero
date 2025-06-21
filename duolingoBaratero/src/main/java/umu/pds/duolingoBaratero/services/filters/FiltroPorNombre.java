package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import java.util.stream.Collectors;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Filtro que permite filtrar cursos por su nombre.
 * Este filtro se utiliza como parte del patrón Decorator.
 */
public class FiltroPorNombre extends FiltroDecorador {
	
	/**
	 * Nombre del curso por el que se filtran los cursos.
	 */
	private String cursoNombre;
	
	/**
	 * Constructor que inicializa el filtro y el nombre del curso.
	 * 
	 * @param filtro Filtro que se va a decorar.
	 * @param cursoNombre Nombre del curso por el que se filtran los cursos.
	 */
	public FiltroPorNombre(Filtro filtro, String cursoNombre) {
		super(filtro);
		this.cursoNombre = cursoNombre;
	}
	
	@Override
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista){
		List<CursoPlantilla> l = lista.stream()
				.filter(c -> c.getNombre().equals(cursoNombre))
				.collect(Collectors.toList());
		return super.filtrar(l);

	}

}
