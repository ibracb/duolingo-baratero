package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;

import umu.pds.duolingoBaratero.models.PreguntaAudio;
import umu.pds.duolingoBaratero.services.RespuestaPanel;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PanelPreguntaAudio extends JPanel implements RespuestaPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lblPregunta;
	private JToggleButton[] opciones; // Botones de imagen
	private JLabel lblAudio;
	private JButton btnReproducir;
	private PreguntaAudio pregunta;
	private String respuestaUsuario;

	public PanelPreguntaAudio(PreguntaAudio pregunta) {
		this.pregunta = pregunta;
		inicializar();
	}

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

		opciones = new JToggleButton[3];
		ButtonGroup grupoOpciones = new ButtonGroup();

		for (int i = 0; i < 3; i++) {
			opciones[i] = new JToggleButton(pregunta.getOpciones()[i]);
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

	private void ajustarTamañoFuentePregunta() {
		int altura = lblPregunta.getHeight();
		int tamañoFuente = Math.max(18, altura / 10); // Ajuste dinámico
		lblPregunta.setFont(new Font("Arial", Font.BOLD, tamañoFuente));
	}

	private void reproducirAudio() {
		System.out.println("Reproduciendo audio...");
	}
}