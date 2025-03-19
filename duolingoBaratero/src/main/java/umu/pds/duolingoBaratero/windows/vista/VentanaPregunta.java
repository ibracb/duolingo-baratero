package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;

import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.models.Flashcard;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.PreguntaAudio;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.services.RespuestaPanel;
import umu.pds.duolingoBaratero.windows.components.BarraProgresoPanel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperiorPreguntas;
import umu.pds.duolingoBaratero.windows.utility.Constantes;

public class VentanaPregunta extends JFrame {
	private static final int PANEL_Y_PUNTUCAION_INICIAL = 0;
	private static final long serialVersionUID = 1L;
	private ControladorCurso controlador;
	private JPanel contentPane;
	private JPanel panelCentral;
	private JPanel[] paneles;
	private CardLayout cardLayout;
	private BarraProgresoPanel barraProgreso;
	private BarraSuperiorPreguntas barraSuperior;
	private JButton btnSiguiente, btnSaltar;
	private Component horizontalGlue;
	private int currentPanel;
	private int puntuacion;
	private long bloqueContenido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPregunta frame = new VentanaPregunta(69);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Asegúrate de que la ventana cierre
																			// correctamente
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPregunta(long bloqueContenido) {
		this.bloqueContenido = bloqueContenido;
		currentPanel = PANEL_Y_PUNTUCAION_INICIAL;
		puntuacion = PANEL_Y_PUNTUCAION_INICIAL;
		inicializar();
	}

	/**
	 * Create the frame.
	 */
	public void inicializar() {

		controlador = ControladorCurso.INSTANCE; // Controlador

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// ------- barra superior-------
		barraSuperior = new BarraSuperiorPreguntas();
		barraProgreso = new BarraProgresoPanel();

		// Panel que une la barra superior con la barra de progreso
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.add(barraSuperior, BorderLayout.NORTH);
		panelSuperior.add(barraProgreso, BorderLayout.SOUTH);

		contentPane.add(panelSuperior, BorderLayout.NORTH);

		panelCentral = new JPanel(new CardLayout());
		cardLayout = (CardLayout) panelCentral.getLayout();

		// -------Futura funcionalidad real------- NO BORRAR
		// JPanel[] paneles = controlador.generarLeccion(bloqueContenido);
		paneles = this.getPaneles();
		panelCentral.add(paneles[0], "panel0");
		panelCentral.add(paneles[1], "panel1");
		panelCentral.add(paneles[2], "panel2");
		panelCentral.add(paneles[3], "panel3");

		// Panel para los botones de acción
		JPanel panelBotones = new JPanel(new FlowLayout());
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(new Color(0, 255, 0));
		btnSiguiente.setPreferredSize(new Dimension(100, 30));

		// TODO: Cambiar esto por un metodo
		btnSiguiente.addActionListener(e -> {
			RespuestaPanel panel = (RespuestaPanel) paneles[currentPanel];
			if (panel.isOpcionElegida()) {
				boolean respuestaCorrecta = controlador.procesarRespuesta(panel.getPregunta(),
						panel.getRespuestaUsuario());
				if (respuestaCorrecta) {
					Constantes.mostrarMensaje("¡Correcto!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Constantes.mostrarMensaje("Fallaste, la respuesta correcta era: " + panel.getPregunta().getRespuestaCorrecta(), JOptionPane.ERROR_MESSAGE);
				}
				barraProgreso.avanzar(respuestaCorrecta);
				avanzarPregunta();

			} else {
				JOptionPane.showMessageDialog(this, "Debe elegir una opción o saltar para ir a la siguiente pregunta.",
						"Error", JOptionPane.ERROR_MESSAGE);

			}

		});

		contentPane.add(panelCentral, BorderLayout.CENTER); // **Agregarlo al centro**

		btnSaltar = new JButton("Saltar");
		btnSaltar.setBackground(new Color(255, 140, 0));
		btnSaltar.setPreferredSize(new Dimension(100, 30)); // Ajusta el tamaño del botón

		btnSaltar.addActionListener(e -> {
			barraProgreso.avanzar(null);
			avanzarPregunta();
		});

		panelBotones.add(btnSaltar);

		horizontalGlue = Box.createHorizontalGlue();
		panelBotones.add(horizontalGlue);
		panelBotones.add(btnSiguiente);

		contentPane.add(panelBotones, BorderLayout.SOUTH);

		setLocationRelativeTo(null);

	}

	private void avanzarPregunta() {
		if (currentPanel < Constantes.PREGUNTAS_POR_BLOQUE - 1) {
			currentPanel++;
			cardLayout.show(panelCentral, "panel" + currentPanel);
		} else {
			new DialogoFinal(this, puntuacion).setVisible(true);
		}

	}

	// --------METODO DE PRUEBA --------------
	private JPanel[] getPaneles() {

		JPanel[] paneles = new JPanel[4];

		String[] opciones = { "Opción 1", "Opción 2", "Opción 3" };
		Pregunta[] preguntas = new Pregunta[4]; // Array de tamaño 5, pero vacío

		preguntas[0] = new PreguntaAudio(Nivel.INTERMEDIO, 1, "¿Qué sonido se escucha?", "Opción 2", opciones,
				"ruta/al/archivo/audio.mp3");
		preguntas[1] = new PreguntaOpciones(Nivel.INTERMEDIO, 1, "¿cual es la respuesta?", "Opción 2",
				TipoPregunta.OPCIONES, opciones);
		preguntas[2] = new Flashcard(Nivel.AVANZADO, 3, "¿Elemento químico Na?", "Sodio", TipoPregunta.FLASHCARD,
				69696969);
		preguntas[3] = new PreguntaOpciones(Nivel.INTERMEDIO, 1, "¿cual es la respuesta?", "Opción 2",
				TipoPregunta.IMAGEN, opciones);

		int i = 0;
		for (Pregunta pregunta : preguntas) {
			paneles[i] = pregunta.crearPanel();
			i++;
		}

		return paneles;
	}

	public class DialogoFinal extends JDialog {
		public DialogoFinal(JFrame ventanaPregunta, int puntuacion) {
			super(ventanaPregunta, "Juego Completado", true); // Modal
			setSize(300, 150);
			setLocationRelativeTo(ventanaPregunta); // Centrar sobre la ventana principal
			setLayout(new BorderLayout());

			// Mensaje de resultado
			JLabel mensaje = new JLabel("¡Juego terminado! Puntuación: " + puntuacion, JLabel.CENTER);
			add(mensaje, BorderLayout.CENTER);

			// Botones
			JPanel panelBotones = new JPanel();
			JButton btnRepetir = new JButton("Repetir");
			JButton btnSalir = new JButton("Salir");

			// Acción para repetir
			btnRepetir.addActionListener(e -> {
				setVisible(false); // Cierra el diálogo
				// Aquí puedes reiniciar el juego, llamando a un método de la ventana principal
			});

			// Acción para salir
			btnSalir.addActionListener(e -> {
				dispose();
				ventanaPregunta.dispose();
			});

			panelBotones.add(btnRepetir);
			panelBotones.add(btnSalir);
			add(panelBotones, BorderLayout.SOUTH);
		}
	}
}
