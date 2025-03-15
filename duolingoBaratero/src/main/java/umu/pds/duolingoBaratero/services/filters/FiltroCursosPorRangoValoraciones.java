package umu.pds.duolingoBaratero.services.filters;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class FiltroCursosPorRangoValoraciones extends FiltroDecorador {
	
	private int cursoValoracionMinima;
	private int cursoValoracionMaxima;
	
	public FiltroCursosPorRangoValoraciones(FiltroCursos<CursoPlantilla> filtro, int cursoValoracionMinima, int cursoValoracionMaxima) {
		super(filtro);
		this.cursoValoracionMinima = cursoValoracionMinima;
		this.cursoValoracionMaxima = cursoValoracionMaxima;
	}
	
	@Override
	public boolean test(CursoPlantilla curso) {
		return curso.getValoracionMedia() >= cursoValoracionMinima && curso.getValoracionMedia() <= cursoValoracionMaxima
				&& super.test(curso);
	}
}