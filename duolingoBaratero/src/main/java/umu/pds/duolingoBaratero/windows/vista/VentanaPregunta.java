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

public class VentanaPregunta extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorCurso controlador;
	private JPanel contentPane;
	private JPanel[] paneles;
	private BarraProgresoPanel barraProgreso;
	private BarraSuperiorPreguntas barraSuperior;
	private JButton btnSiguiente, btnSaltar;
	private Component horizontalGlue;
	private int currentPanel = 0;
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

		JPanel panelCentral = new JPanel(new CardLayout());
		CardLayout cardLayout = (CardLayout) panelCentral.getLayout();

		// -------Futura funcionalidad real------- NO BORRAR
		// JPanel[] paneles = controlador.generarLeccion(bloqueContenido);
		paneles = this.getPaneles();
		panelCentral.add(paneles[0], "panel1");
		panelCentral.add(paneles[1], "panel2");
		panelCentral.add(paneles[2], "panel3");
		panelCentral.add(paneles[3], "panel4");

		// Panel para los botones de acción
		JPanel panelBotones = new JPanel(new FlowLayout());
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(new Color(0, 255, 0));
		btnSiguiente.setPreferredSize(new Dimension(100, 30)); // Ajusta el tamaño del botón

		// TODO: Cambiar esto por un metodo
		btnSiguiente.addActionListener(e -> {
			RespuestaPanel panel = (RespuestaPanel) paneles[currentPanel];
			if (panel.isOpcionElegida()) {
				controlador.procesarRespuesta(panel.getPregunta(), panel.getRespuestaUsuario());
				barraProgreso.avanzar();
				currentPanel = (currentPanel % 4) + 1; // Ciclo entre 1 y 4
				cardLayout.show(panelCentral, "panel" + currentPanel);

			} else {
				JOptionPane.showMessageDialog(this, "Debe elegir una opción o saltar para ir a la siguiente pregunta.",
						"Error", JOptionPane.ERROR_MESSAGE);

			}

		});

		contentPane.add(panelCentral, BorderLayout.CENTER); // **Agregarlo al centro**

		btnSaltar = new JButton("Saltar");
		btnSaltar.setBackground(new Color(255, 140, 0));
		btnSaltar.setPreferredSize(new Dimension(100, 30)); // Ajusta el tamaño del botón
		panelBotones.add(btnSaltar);

		horizontalGlue = Box.createHorizontalGlue();
		panelBotones.add(horizontalGlue);
		panelBotones.add(btnSiguiente);

		contentPane.add(panelBotones, BorderLayout.SOUTH);

		setLocationRelativeTo(null);

	}

	// --------METODO DE PRUEBA --------------
	private JPanel[] getPaneles() {

		JPanel[] paneles = new JPanel[4];

		String[] opciones = { "Opción 1", "Opción 2", "Opción 3" };
		Pregunta[] preguntas = new Pregunta[4]; // Array de tamaño 5, pero vacío

		preguntas[0] = new PreguntaAudio(Nivel.INTERMEDIO, 1, "¿Qué sonido se escucha?", "Opción 2", TipoPregunta.AUDIO,
				opciones, "ruta/al/archivo/audio.mp3");
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

}
