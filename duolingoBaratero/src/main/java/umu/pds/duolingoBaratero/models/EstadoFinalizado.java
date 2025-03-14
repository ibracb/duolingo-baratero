package umu.pds.duolingoBaratero.models;

public class EstadoFinalizado implements EstadoCursoEnProgreso {
	
	private CursoEnProgreso curso;
	
	public EstadoFinalizado(CursoEnProgreso curso) {
		setCurso(curso);
	}
	
	public CursoEnProgreso getCurso() {
		return curso;
	}

	public void setCurso(CursoEnProgreso curso) {
		this.curso = curso;
	}

	@Override
	public void iniciar(CursoEnProgreso curso) {
		curso.setEstado(new EstadoNuevo(curso));
	}

	@Override
	public void finalizar(CursoEnProgreso curso) {
		curso.setEstado(curso.getEstado());
	}

}
