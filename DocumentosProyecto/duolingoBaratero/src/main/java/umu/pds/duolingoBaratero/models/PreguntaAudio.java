package umu.pds.duolingoBaratero.models;

public class PreguntaAudio extends Pregunta {

	private String[] opciones;
	private String rutaAudio;

	public PreguntaAudio(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
			String[] opciones, String rutaAudio) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		this.opciones = opciones;
		this.rutaAudio = rutaAudio;
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
