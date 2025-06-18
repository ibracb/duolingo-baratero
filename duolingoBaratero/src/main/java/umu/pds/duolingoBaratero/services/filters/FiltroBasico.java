package umu.pds.duolingoBaratero.services.filters;

import java.util.List;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Filtro básico utilizaod por el patrón decoractor, este filatrado no tiene  ningún efecto
 * sobre la lista.
 */
public class FiltroBasico implements Filtro{

	@Override
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista) {
		return lista;
	}
	
}
