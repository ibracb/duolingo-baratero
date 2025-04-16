package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaAudio;

public class PreguntaAudio extends Pregunta {

	private String[] opciones;
	private String rutaAudio;

	
	
	public PreguntaAudio() {
		super();
	}


	public PreguntaAudio(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		// TODO Auto-generated constructor stub
	}


	public PreguntaAudio(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, String[] opciones, String rutaAudio) {
		super(nivel, numero, pregunta, respuestaCorrecta, TipoPregunta.AUDIO);
		this.opciones = opciones;
		this.rutaAudio = rutaAudio;
	}

	
	@Override
	public JPanel crearPanel() {
		return new PanelPreguntaAudio(this);
	}


	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

	public String getRutaAudio() {
		return rutaAudio;
	}

	public void setRutaAudio(String rutaAudio) {
		this.rutaAudio = rutaAudio;
	}

}
