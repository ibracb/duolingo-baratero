package umu.pds.duolingoBaratero.services.filters;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public abstract class FiltroDecorador implements FiltroCursos<CursoPlantilla> {
	protected FiltroCursos<CursoPlantilla> filtro;

	public FiltroDecorador(FiltroCursos<CursoPlantilla> filtro) {
		this.filtro = filtro;
	}

	@Override
	public boolean test(CursoPlantilla curso) {
		return filtro == null || filtro.test(curso);
	}
}
