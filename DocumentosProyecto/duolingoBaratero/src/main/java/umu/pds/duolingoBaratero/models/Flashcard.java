package umu.pds.duolingoBaratero.models;

import java.util.List;

public class Flashcard {
	
	private Nivel nivel;
	private int numero;
	private String pregunta;
	private String respuestaCorrecta;
	private TipoPregunta tipo;
	
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}
	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}
	public TipoPregunta getTipo() {
		return tipo;
	}
	public void setTipo(TipoPregunta tipo) {
		this.tipo = tipo;
	}
	
	
}
