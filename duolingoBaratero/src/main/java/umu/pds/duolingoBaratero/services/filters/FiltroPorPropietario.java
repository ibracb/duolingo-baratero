package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import java.util.stream.Collectors;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Filtro que permite filtrar cursos por su propietario.
 * Este filtro se utiliza como parte del patrón Decorator.
 */
public class FiltroPorPropietario extends FiltroDecorador {

	/**
	 * Nombre del propietario por el que se filtran los cursos.
	 */
	private String nombrePropietario;

	/**
	 * Constructor que inicializa el filtro y el nombre del propietario.
	 * 
	 * @param filtro          Filtro que se va a decorar.
	 * @param nombrePropietario Nombre del propietario por el que se filtran los cursos.
	 */
	public FiltroPorPropietario(Filtro filtro, String nombrePropietario) {
		super(filtro);
		this.nombrePropietario = nombrePropietario;
	}

	@Override
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista) {
		List<CursoPlantilla> l = lista.stream().filter(c -> c.getPropietario().equals(nombrePropietario))
				.collect(Collectors.toList());
		return super.filtrar(l);

	}

}