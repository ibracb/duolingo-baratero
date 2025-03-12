package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.LinkedList;
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

	public String getNombre() {
		return cursoPlantilla.getNombre();
	}

	public String getDescripcion() {
		return cursoPlantilla.getDescripcion();
	}

	public String getObjetivos() {
		return cursoPlantilla.getObjetivos();
	}

	public Nivel getNivel() {
		return cursoPlantilla.getNivel();
	}

	public List<BloqueContenido> getContenidos() {
		return cursoPlantilla.getContenidos();
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
	
	public List<Pregunta> getPregunta() {
		List<BloqueContenido> listaContenidos = getContenidos();
		LinkedList<Pregunta> listaPreguntas = new LinkedList<>();
		switch(this.aprendizaje) {
			case ALEATORIO: listaPreguntas = (LinkedList<Pregunta>) (listaContenidos.get(0)).getPreguntasAleatoriamente();
		}
		return listaPreguntas;
		
	}
	
}
