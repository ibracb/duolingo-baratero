package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.controllers.ControladorAudio;
import umu.pds.duolingoBaratero.models.PreguntaAudio;
import umu.pds.duolingoBaratero.services.IComprobador;

/**
 * Panel que muestra una pregunta de tipo audio con opciones de respuesta.
 * Permite al usuario escuchar un audio y seleccionar la respuesta correcta.
 */
public class PanelPreguntaAudio extends JPanel implements IComprobador{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiqueta que muestra la pregunta.
	 */
	private JLabel lblPregunta;
	
	/**
	 * Botones de opción para las respuestas.
	 * Se utilizan JToggleButton para permitir la selección de una sola opción.
	 */
	private JToggleButton[] opciones; // Botones de imagen
	
	/**
	 * Botón para reproducir el audio de la pregunta.
	 */
	private JButton btnReproducir;
	
	/**
	 * La pregunta de tipo audio que contiene el texto y las opciones.
	 */
	private PreguntaAudio pregunta;
	
	/**
	 * Respuesta seleccionada por el usuario.
	 * Se almacena como texto de la opción elegida.
	 */
	private String respuestaUsuario;
	
	/**
	 * Constructor que inicializa el panel con una pregunta de tipo audio.
	 * @param pregunta La pregunta de tipo audio que se mostrará en el panel.
	 */
	public PanelPreguntaAudio(PreguntaAudio pregunta) {
		this.pregunta = pregunta;
		inicializar();
	}
	
	/**
	 * Método que inicializa el panel y sus componentes.
	 * Configura el diseño, crea los botones de opción y el botón de reproducción de audio.
	 */
	private void inicializar() {
		setLayout(new BorderLayout()); // Usamos BorderLayout para mejor distribución

		// Panel contenedor para las preguntas e imágenes
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		JPanel panelEscucha = new JPanel();
		panelEscucha.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));  // 20 píxeles de margen arriba
		panelEscucha.setLayout(new FlowLayout());

		// Etiqueta de la pregunta
		lblPregunta = new JLabel("Escucha atentamente el audio y selecciona la opción correcta: ",
				SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 24));
		lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		// Botón para reproducir el audio
		btnReproducir = new JButton("Reproducir"); // Añade el texto al botón
		btnReproducir.addActionListener(e -> reproducirAudio());
		
		// Añadir la etiqueta y el botón al panel
		panelEscucha.add(lblPregunta);
		panelEscucha.add(btnReproducir);

		// Añadir el panelEscucha a tu panel principal
		panelCentral.add(panelEscucha);

		// Panel para las opciones
		JPanel panelOpciones = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE; // Evita que los botones se expandan demasiado en altura
		gbc.insets = new Insets(10, 20, 15, 20);

		List<String> opcionesLista = new ArrayList<>(pregunta.getOpciones());  // conviertes a ArrayList "real"

		opciones = new JToggleButton[opcionesLista.size()];
		ButtonGroup grupoOpciones = new ButtonGroup();

		for (int i = 0; i < 3; i++) {
			opciones[i] = new JToggleButton(opcionesLista.get(i));
			opciones[i].setFont(new Font("Arial", Font.PLAIN, 16));

			opciones[i].setMinimumSize(new Dimension(100, 50)); // Tamaño mínimo
			opciones[i].setPreferredSize(new Dimension(450, 170)); // Tamaño fijo
			opciones[i].setMaximumSize(new Dimension(500, 170)); // Tamaño máximo
			grupoOpciones.add(opciones[i]);
			panelOpciones.add(opciones[i], gbc);
			
			int index = i;
			opciones[i].addActionListener(e -> {respuestaUsuario = opciones[index].getText();} );
		}

		// Agregar elementos al panel principal
		panelCentral.add(panelOpciones);
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
	public PreguntaAudio getPregunta() {
		return pregunta;
	}

	public void setPregunta(PreguntaAudio pregunta) {
		this.pregunta = pregunta;
	}

	/*private void ajustarTamañoFuentePregunta() {
		int altura = lblPregunta.getHeight();
		int tamañoFuente = Math.max(18, altura / 10); // Ajuste dinámico
		lblPregunta.setFont(new Font("Arial", Font.BOLD, tamañoFuente));
	}*/
	
	/**
	 * Método que reproduce el audio asociado a la pregunta.
	 * Utiliza el controlador de audio para reproducir el archivo de audio.
	 */
	private void reproducirAudio() {
		ControladorAudio.INSTANCE.reproducir(pregunta.getRutaAudio());
	}
}