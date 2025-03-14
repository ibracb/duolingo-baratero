package umu.pds.duolingoBaratero.services.filters;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroCursosPorValoracion extends FiltroDecorador {
	
	private int cursoValoracion;
	
	public FiltroCursosPorValoracion(FiltroCursos<CursoPlantilla> filtro, int cursoValoracion) {
		super(filtro);
		this.cursoValoracion = cursoValoracion;
	}
	
	@Override
	public boolean test(CursoPlantilla curso) {
		return curso.getValoracionMedia() == cursoValoracion && super.test(curso);
	}
}
