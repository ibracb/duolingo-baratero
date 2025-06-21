package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.models.PreguntaImagenes;
import umu.pds.duolingoBaratero.services.IComprobador;

/**
 * Panel que muestra una pregunta de tipo imágenes con opciones de respuesta.
 * Permite al usuario seleccionar una imagen como respuesta a la pregunta.
 */
public class PanelPreguntaImagenes extends JPanel implements IComprobador {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Botones de opción para las respuestas.
	 * Se utilizan JToggleButton para permitir la selección de una sola opción.
	 */
	private JToggleButton[] imagenes; // Botones de imagen
	
	/**
	 * Etiqueta que muestra la pregunta.
	 */
	private JLabel lblPregunta;
	
	/**
	 * La pregunta de tipo imágenes que contiene el texto y las opciones.
	 */
	private PreguntaImagenes pregunta;
	
	/**
	 * Respuesta seleccionada por el usuario.
	 * Se almacena como texto de la opción elegida.
	 */
	private String respuestaUsuario;
	
	/**
	 * Constructor que inicializa el panel con una pregunta de tipo PreguntaImagenes.
	 * 
	 * @param preguntaImagenes La pregunta de tipo PreguntaImagenes que se mostrará en el panel.
	 */
	public PanelPreguntaImagenes(PreguntaImagenes preguntaImagenes) {
		this.pregunta = preguntaImagenes;
		inicializar();
	}

	/**
	 * Inicializa el panel configurando su diseño, añadiendo la etiqueta de la
	 * pregunta y los botones de imagen como opciones de respuesta.
	 */
	private void inicializar() {
		setLayout(new BorderLayout()); // Usamos BorderLayout para mejor distribución

		// Panel contenedor para las preguntas e imágenes
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		// Pregunta
		lblPregunta = new JLabel(pregunta.getPregunta(), SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
		lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(lblPregunta);

		// Panel para las imágenes
		JPanel panelImagenes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		List<String> opcionesLista = new ArrayList<>(pregunta.getOpciones());  // conviertes a ArrayList "real"

		imagenes = new JToggleButton[opcionesLista.size()];
		ButtonGroup grupoImagenes = new ButtonGroup(); // Para selección única
		for (int i = 0; i < 3; i++) {
			imagenes[i] = new JToggleButton(new ImageIcon("src/main/resources/" + opcionesLista.get(i) ));
			imagenes[i].setPreferredSize(new Dimension(250, 250));
			grupoImagenes.add(imagenes[i]);
			panelImagenes.add(imagenes[i]);

			int index = i;
			imagenes[i].addActionListener(e -> {
				respuestaUsuario = imagenes[index].getText();
			});
		}

		// Agregar elementos al panel principal
		panelCentral.add(panelImagenes);
		add(panelCentral, BorderLayout.CENTER);
	}
	
	@Override
	public String getRespuestaUsuario() {
		return respuestaUsuario;
	}
	@Override
	public boolean isOpcionElegida() {
		return respuestaUsuario != null;
	}
	
	@Override
	public PreguntaImagenes getPregunta() {
		return pregunta;
	}

}
