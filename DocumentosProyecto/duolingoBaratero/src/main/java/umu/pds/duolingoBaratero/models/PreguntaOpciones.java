package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaImagenes;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaOpciones;

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

	@Override
	/**
	 * Devuelve un panel Pregunta Imagenes si es una pregunta de tipo imagen Sino
	 * Devuelve un panel de tipo Opciones
	 */
	public JPanel crearPanel() {
		if (this.isImagen())
			return new PanelPreguntaImagenes(this);
		return new PanelPreguntaOpciones(this);
	}

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

}
