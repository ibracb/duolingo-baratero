package umu.pds.duolingoBaratero.services.filters;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroCursosPorNombre extends FiltroDecorador {
	
	private String cursoNombre;
	
	public FiltroCursosPorNombre(FiltroCursos<CursoPlantilla> filtro, String cursoNombre) {
		super(filtro);
		this.cursoNombre = cursoNombre;
	}
	
	@Override
	public boolean test(CursoPlantilla curso) {
		return curso.getNombre().equals(cursoNombre) && super.test(curso);
	}

}
