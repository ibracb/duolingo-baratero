package umu.pds.duolingoBaratero.models;

public class EstadoEnMarcha implements EstadoCursoEnProgreso {
	
	private CursoEnProgreso curso;
	
	public EstadoEnMarcha(CursoEnProgreso curso) {
		this.setCurso(curso);
	}
	
	public CursoEnProgreso getCurso() {
		return curso;
	}

	public void setCurso(CursoEnProgreso curso) {
		this.curso = curso;
	}

	@Override
	public void iniciar(CursoEnProgreso curso) {
		curso.setEstadoNuevo(new EstadoNuevo(curso));
	}

	@Override
	public void finalizar(CursoEnProgreso curso) {
		curso.setEstadoFinalizado(new EstadoFinalizado(curso));
	}

}
