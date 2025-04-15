package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import java.util.stream.Collectors;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroPorPropietario extends FiltroDecorador {

	private String nombrePropietario;

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