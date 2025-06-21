package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import umu.pds.duolingoBaratero.models.CursoPlantilla;


/**
 * Implementación base que actúa como decorador para filtros.
 */
public class FiltroDecorador implements Filtro {
	
	/**
	 * Filtro que se está decorando.
	 */
	protected Filtro filtro;

	/**
	 * Constructor que inicializa el filtro a decorar.
	 * @param filtro Filtro que se va a decorar.
	 */
	public FiltroDecorador(Filtro filtro) {
		super();
		this.filtro = filtro;
	}

	@Override
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista){
		return filtro.filtrar(lista);
	}

}
