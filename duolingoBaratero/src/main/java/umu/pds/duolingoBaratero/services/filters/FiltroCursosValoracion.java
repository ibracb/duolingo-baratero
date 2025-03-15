package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import java.util.stream.Collectors;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroCursosValoracion extends FiltroDecorador {
	
	private int cursoValoracionMinima;
	
	public FiltroCursosValoracion(Filtro filtro, int cursoValoracionMinima) {
		super(filtro);
		this.cursoValoracionMinima = cursoValoracionMinima;
	}
	
	@Override
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista) {
		List<CursoPlantilla> l = lista.stream().filter(c -> c.getValoracionMedia() > cursoValoracionMinima)
				.collect(Collectors.toList());
		return super.filtrar(l);

	}
}