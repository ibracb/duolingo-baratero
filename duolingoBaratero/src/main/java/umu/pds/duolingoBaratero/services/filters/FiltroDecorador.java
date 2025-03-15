package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroDecorador implements Filtro {
	protected Filtro filtro;

	public FiltroDecorador(Filtro filtro) {
		super();
		this.filtro = filtro;
	}

	@Override
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista){
		return filtro.filtrar(lista);
	}

}
