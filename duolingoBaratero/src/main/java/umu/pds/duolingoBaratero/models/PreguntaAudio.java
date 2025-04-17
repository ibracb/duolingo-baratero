package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaAudio;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PreguntaAudio extends Pregunta {
	
	@JsonIgnore
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
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
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
