package umu.pds.duolingoBaratero.services.filters;

import java.util.List;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroBasico implements Filtro{

	@Override
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista) {
		return lista;
	}
	
	
	

}
