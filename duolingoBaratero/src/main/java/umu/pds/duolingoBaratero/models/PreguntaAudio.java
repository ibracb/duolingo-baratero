package umu.pds.duolingoBaratero.models;

import java.util.List;

import javax.swing.JPanel;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaAudio;

@Entity
@Table(name = "pregunta_audio")
@DiscriminatorValue("AUDIO")
public class PreguntaAudio extends Pregunta {

	@ElementCollection
	@CollectionTable(name = "pregunta_audio_opciones", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opciones")
	private List<String> opciones;
	
	@Lob
	@Column(name="ruta_audio")
	private String rutaAudio;

	public PreguntaAudio() {
		super();
	}

	public PreguntaAudio(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
	}

	public PreguntaAudio(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, List<String> opciones,
			String rutaAudio) {
		super(nivel, numero, pregunta, respuestaCorrecta, TipoPregunta.AUDIO);
		this.opciones = opciones;
		this.rutaAudio = rutaAudio;
	}

	@Override
	public JPanel crearPanel() {
		return new PanelPreguntaAudio(this);
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public String getRutaAudio() {
		return rutaAudio;
	}

	public void setRutaAudio(String rutaAudio) {
		this.rutaAudio = rutaAudio;
	}

}
