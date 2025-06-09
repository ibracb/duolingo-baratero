package umu.pds.duolingoBaratero.models;

import java.util.List;

import javax.swing.JPanel;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaOpciones;

/**
 * Esta clase almacena tanto preguntasOpciones como preguntaImagenes; Se puede
 * diferenciar con el atributo "tipo" de la clase "Pregunta"
 * 
 */
@Entity
@Table(name = "pregunta_opciones")
@DiscriminatorValue("OPCIONES")
public class PreguntaOpciones extends Pregunta {

	@ElementCollection
	@CollectionTable(name = "pregunta_opciones_opciones", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opciones")
	private List<String> opciones;

	public PreguntaOpciones() {
		super();

	}

	public PreguntaOpciones(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
	}

	public PreguntaOpciones(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
			List<String> opciones) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		this.opciones = opciones;
	}


	@Override
	public JPanel crearPanel() {
		return new PanelPreguntaOpciones(this);
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

}
