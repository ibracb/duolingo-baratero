package umu.pds.duolingoBaratero.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaImagenes;

@Entity
@Table(name = "pregunta_imagenes")
@DiscriminatorValue("IMAGENES")
public class PreguntaImagenes extends Pregunta {

	@ElementCollection
	@CollectionTable(name = "pregunta_imagenes_opciones", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opciones")
	private List<String> opciones;

	public PreguntaImagenes() {
		super();
	}

	public PreguntaImagenes(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
	}

	public PreguntaImagenes(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
			List<String> opciones) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		this.opciones = opciones;
	}

	public List<String> getOpciones() {
		return (ArrayList<String>) opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	@Override
	public JPanel crearPanel() {
		return new PanelPreguntaImagenes(this);
	}

}
