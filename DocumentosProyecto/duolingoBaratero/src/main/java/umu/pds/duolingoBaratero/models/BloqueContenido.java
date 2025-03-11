package umu.pds.duolingoBaratero.models;

import java.util.List;

public class BloqueContenido {
	
	private List<Pregunta> preguntas;

	public BloqueContenido(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	 

}
