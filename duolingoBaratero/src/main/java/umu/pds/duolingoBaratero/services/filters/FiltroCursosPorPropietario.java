package umu.pds.duolingoBaratero.services.filters;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroCursosPorPropietario extends FiltroDecorador {
	
	private String cursoPropietario;
	
	public FiltroCursosPorPropietario(FiltroCursos<CursoPlantilla> filtro, String cursoPropietario) {
		super(filtro);
		this.cursoPropietario = cursoPropietario;
	}
	
	@Override
	public boolean test(CursoPlantilla curso) {
		return curso.getPropietario().equals(cursoPropietario) && super.test(curso);
	}

}