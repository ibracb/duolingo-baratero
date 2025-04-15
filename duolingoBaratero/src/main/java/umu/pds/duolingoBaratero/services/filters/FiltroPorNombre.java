package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import java.util.stream.Collectors;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroPorNombre extends FiltroDecorador {
	
	private String cursoNombre;
	
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
