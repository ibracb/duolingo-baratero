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

/**
 * Subclase de Pregunta para preguntas con componente de audio. Contiene lista
 * de opciones y ruta del archivo de audio asociado. Implementa crearPanel para
 * devolver su panel Swing específico.
 */
@Entity
@Table(name = "pregunta_audio")
@DiscriminatorValue("AUDIO")
public class PreguntaAudio extends Pregunta {

	/**
	 * Lista de opciones de respuesta para la pregunta de audio.
	 * Cada opción puede ser un texto o una descripción asociada al audio.
	 */
	@ElementCollection
	@CollectionTable(name = "pregunta_audio_opciones", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opciones")
	private List<String> opciones;

	/**
	 * Ruta del archivo de audio asociado a la pregunta.
	 * Puede ser una URL o una ruta local al archivo de audio.
	 */
	@Lob
	@Column(name = "ruta_audio")
	private String rutaAudio;

	/**
	 * Constructor por defecto requerido por JPA.
	 */
	public PreguntaAudio() {
		super();
	}

	/**
	 * Constructor con parámetros para inicializar la pregunta de audio.
	 * 
	 * @param nivel            Nivel de dificultad de la pregunta
	 * @param numero           Número identificativo de la pregunta
	 * @param pregunta         Texto de la pregunta
	 * @param respuestaCorrecta Respuesta correcta esperada
	 * @param tipo             Tipo de pregunta (debe ser AUDIO)
	 */
	public PreguntaAudio(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
	}

	/**
	 * Constructor que incluye opciones y ruta de audio.
	 * 
	 * @param nivel            Nivel de dificultad
	 * @param numero           Número de pregunta
	 * @param pregunta         Texto de la pregunta
	 * @param respuestaCorrecta Respuesta correcta
	 * @param opciones         Lista de opciones de respuesta
	 * @param rutaAudio        Ruta del archivo de audio asociado a la pregunta
	 */
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

	/**
	 * Obtiene la lista de opciones de respuesta para la pregunta de audio.
	 * 
	 * @return Lista de opciones de respuesta
	 */
	public List<String> getOpciones() {
		return opciones;
	}

	/**
	 * Establece la lista de opciones de respuesta para la pregunta de audio.
	 * 
	 * @param opciones Lista de opciones a establecer
	 */
	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	/**
	 * Obtiene la ruta del archivo de audio asociado a la pregunta.
	 * 
	 * @return Ruta del archivo de audio
	 */
	public String getRutaAudio() {
		return rutaAudio;
	}

	/**
	 * Establece la ruta del archivo de audio asociado a la pregunta.
	 * 
	 * @param rutaAudio Ruta del archivo de audio
	 */
	public void setRutaAudio(String rutaAudio) {
		this.rutaAudio = rutaAudio;
	}

}
