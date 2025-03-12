package umu.pds.duolingoBaratero.models;

import java.util.List;

public class CursoEnProgreso {
	
	private Usuario estudiante;
	private CursoPlantilla cursoPlantilla;
	private Aprendizaje aprendizaje;
	private EstadoCursoEnProgreso estado;
	
	public CursoEnProgreso(Usuario usuario, CursoPlantilla cursoPlantilla, Aprendizaje aprendizaje) {
		this.estudiante = usuario;
		this.cursoPlantilla = cursoPlantilla;
		this.aprendizaje = aprendizaje;
		this.estado = EstadoCursoEnProgreso.NUEVO;
	}

	public Usuario getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Usuario estudiante) {
		this.estudiante = estudiante;
	}

	public CursoPlantilla getCursoPlantilla() {
		return cursoPlantilla;
	}

	public void setCursoPlantilla(CursoPlantilla cursoPlantilla) {
		this.cursoPlantilla = cursoPlantilla;
	}

	public Aprendizaje getAprendizaje() {
		return aprendizaje;
	}

	public void setAprendizaje(Aprendizaje aprendizaje) {
		this.aprendizaje = aprendizaje;
	}

	public EstadoCursoEnProgreso getEstado() {
		return estado;
	}

	public void setEstado(EstadoCursoEnProgreso estado) {
		this.estado = estado;
	}
	
}
