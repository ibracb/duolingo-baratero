package umu.pds.duolingoBaratero.models;

import java.util.List;

public class Pregunta implements Comparable<Pregunta> {
	
	private Nivel nivel;
	private int numero;
	private String pregunta;
	private List<String> respuestas;
	private String respuestaCorrecta;
	private TipoPregunta tipo;
	
	public Pregunta(Nivel nivel, int numero, String pregunta, List<String> respuestas, String respuestaCorrecta, TipoPregunta tipo) {
		this.nivel = nivel;
		this.numero = numero;
		this.pregunta = pregunta;
		this.respuestas = respuestas;
		this.respuestaCorrecta = respuestaCorrecta;
		this.tipo = tipo;
	}

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

	public List<String> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
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

	@Override
	public int compareTo(Pregunta o) {
		return Integer.compare(this.numero, o.numero);
	}

}
