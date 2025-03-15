package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;

import umu.pds.duolingoBaratero.models.PreguntaAudio;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PanelPreguntaAudio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblPregunta;
	private JToggleButton[] opciones; // Botones de imagen
	private JLabel lblAudio;
	private JButton btnReproducir;
	private PreguntaAudio pregunta;

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
		panelEscucha.setLayout(new FlowLayout());

		// Etiqueta de la pregunta
		lblPregunta = new JLabel("Escucha atentamente el audio y selecciona la opción correcta: ",
				SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 18));
		lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Botón para reproducir el audio
		JButton botonReproducir = new JButton("Reproducir"); // Añade el texto al botón
		
		// Añadir la etiqueta y el botón al panel
		panelEscucha.add(lblPregunta);
		panelEscucha.add(botonReproducir);

		// Añadir el panelEscucha a tu panel principal
		panelCentral.add(panelEscucha);

		// Panel para las opciones
		JPanel panelOpciones = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(5, 20, 5, 20);

		opciones = new JToggleButton[3];
		ButtonGroup grupoOpciones = new ButtonGroup();

		for (int i = 0; i < 3; i++) {
			opciones[i] = new JToggleButton(pregunta.getOpciones()[i]);
			opciones[i].setFont(new Font("Arial", Font.PLAIN, 16));
			grupoOpciones.add(opciones[i]);

			// Escuchar cambios de tamaño en los botones
			opciones[i].addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					ajustarTamañoFuentePregunta();
					ajustarTamañoFuenteBotones((JToggleButton) e.getComponent());
				}
			});

			panelOpciones.add(opciones[i], gbc);
		}

		// Agregar elementos al panel principal
		panelCentral.add(panelOpciones);
		add(panelCentral, BorderLayout.CENTER);
	}

	// Método para ajustar el tamaño de la fuente de la pregunta dinámicamente
	private void ajustarTamañoFuentePregunta() {
		int altura = lblPregunta.getHeight();
		int tamañoFuente = Math.max(18, altura / 10); // Ajuste dinámico
		lblPregunta.setFont(new Font("Arial", Font.BOLD, tamañoFuente));
	}

	// Método para ajustar el tamaño de la fuente de los botones dinámicamente
	private void ajustarTamañoFuenteBotones(JToggleButton boton) {
		int altura = boton.getHeight();
		// Establecer un tamaño máximo para la fuente de las opciones
		int tamañoFuente = Math.max(14, Math.min(altura / 4, 20)); // No excede el tamaño máximo de 20px
		boton.setFont(new Font("Arial", Font.PLAIN, tamañoFuente));
	}

	private void reproducirAudio() {
		System.out.println("Reproduciendo audio...");
	}
}