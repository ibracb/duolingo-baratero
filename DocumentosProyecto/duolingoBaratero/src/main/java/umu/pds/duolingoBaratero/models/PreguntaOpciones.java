package umu.pds.duolingoBaratero.models;

/**
 * Esta clase almacena tanto preguntasOpciones como preguntaImagenes; Se puede
 * diferenciar con el atributo "tipo" de la clase "Pregunta"
 * 
 */
public class PreguntaOpciones extends Pregunta {

	private String[] opciones;

	public PreguntaOpciones(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
			String[] opciones) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		this.opciones = opciones;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

}
