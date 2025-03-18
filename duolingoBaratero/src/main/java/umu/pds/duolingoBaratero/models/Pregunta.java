package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

public abstract class Pregunta implements Comparable<Pregunta> {

	private Nivel nivel;
	private int numero;
	private String pregunta;
	private String respuestaCorrecta;
	private TipoPregunta tipo;

	public Pregunta(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		this.nivel = nivel;
		this.numero = numero;
		this.pregunta = pregunta;
		this.respuestaCorrecta = respuestaCorrecta;
		this.tipo = tipo;
	}
	
	public abstract JPanel crearPanel();  // Método abstracto para crear el panel
	
	public boolean esRespuestaCorrecta(String respuestaUsuario) {
		return respuestaCorrecta.equals(respuestaUsuario);
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

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public TipoPregunta getTipo() {
		return tipo;
	}
	
	public boolean isImagen() {
		return tipo.equals(TipoPregunta.IMAGEN);
	}
	
	public void setTipo(TipoPregunta tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Pregunta o) {
		return Integer.compare(this.numero, o.numero);
	}

}
