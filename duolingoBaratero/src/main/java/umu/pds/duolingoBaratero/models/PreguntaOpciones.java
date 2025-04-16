package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaImagenes;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaOpciones;

/**
 * Esta clase almacena tanto preguntasOpciones como preguntaImagenes; Se puede
 * diferenciar con el atributo "tipo" de la clase "Pregunta"
 * 
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PreguntaOpciones extends Pregunta {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	private String[] opciones;

	public PreguntaOpciones() {
		super();

	}

	public PreguntaOpciones(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
	}

	public PreguntaOpciones(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
			String[] opciones) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		this.opciones = opciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
