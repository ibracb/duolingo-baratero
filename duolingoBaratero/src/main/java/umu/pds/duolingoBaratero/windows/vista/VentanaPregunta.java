package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.services.IComprobador;
import umu.pds.duolingoBaratero.windows.components.BarraProgresoPanel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperiorPreguntas;
import umu.pds.duolingoBaratero.windows.utility.Constantes;

public class VentanaPregunta extends JFrame {
	private static final int PANEL_Y_PUNTUCAION_INICIAL = 0;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCentral;
	private ArrayList<JPanel> paneles;
	private CardLayout cardLayout;
	private BarraProgresoPanel barraProgreso;
	private BarraSuperiorPreguntas barraSuperior;
	private JButton btnSiguiente, btnSaltar;
	private Component horizontalGlue;
	private int currentPanel;
	private int puntuacion;
	private CursoEnProgreso curso;
	private final ControladorCursoProgreso controladorCursoProgreso;
	private final ControladorPregunta controladorPregunta;

	public VentanaPregunta(CursoEnProgreso curso, ControladorCursoProgreso controladorCursoprogreso, ControladorPregunta controladorPregunta) {
		this.controladorCursoProgreso = controladorCursoprogreso;
		this.controladorPregunta = controladorPregunta;
		this.curso = curso;
		currentPanel = PANEL_Y_PUNTUCAION_INICIAL;
		puntuacion = PANEL_Y_PUNTUCAION_INICIAL;
		paneles = this.getPaneles();
		inicializar();
		
	}

	/**
	 * Create the frame.
	 */
	public void inicializar() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 800, 600); // Tamaño recomendado
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// ------- barra superior-------
		barraSuperior = new BarraSuperiorPreguntas(this);
		barraProgreso = new BarraProgresoPanel(paneles.size());

		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.add(barraSuperior, BorderLayout.NORTH);
		panelSuperior.add(barraProgreso, BorderLayout.SOUTH);

		contentPane.add(panelSuperior, BorderLayout.NORTH);

		panelCentral = new JPanel(new CardLayout());
		cardLayout = (CardLayout) panelCentral.getLayout();

		// ------- OPTENCION DE PANELES-----

		int i = 0;
		for (JPanel panel : paneles) {
			panelCentral.add(panel, "panel" + i);
			i++;
		}

		// Panel para los botones de acción
		JPanel panelBotones = new JPanel(new FlowLayout());
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(new Color(0, 255, 0));
		btnSiguiente.setPreferredSize(new Dimension(100, 30));

		// FIXME: Esto deberia hacerlo un servicio no la clase
		btnSiguiente.addActionListener(e -> {
			IComprobador panel = (IComprobador) paneles.get(currentPanel);
			if (panel.isOpcionElegida()) {
				boolean respuestaCorrecta = controladorPregunta.procesarRespuesta(panel.getPregunta(),
						panel.getRespuestaUsuario());
				if (respuestaCorrecta) {
					puntuacion++;
					Constantes.mostrarMensaje("¡Correcto!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Constantes.mostrarMensaje(
							"Fallaste, la respuesta correcta era: " + panel.getPregunta().getRespuestaCorrecta(),
							JOptionPane.ERROR_MESSAGE);
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

	}

	private void avanzarPregunta() {
		if (currentPanel < paneles.size() - 1) {
			currentPanel++;
			cardLayout.show(panelCentral, "panel" + currentPanel);
		} else {
			new DialogoFinal(this, puntuacion).setVisible(true);
		}

	}

	// -------- METODO DE PRUEBA --------------
	private ArrayList<JPanel> getPaneles() {
		Set<Pregunta> preguntas = controladorPregunta.obtenerPreguntasDelBloque(curso);
		return preguntas.stream()
			    .map(Pregunta::crearPanel)
			    .collect(Collectors.toCollection(ArrayList::new));
	}

	public class DialogoFinal extends JDialog {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DialogoFinal(JFrame ventanaPregunta, int puntuacion) {
			super(ventanaPregunta, "Juego Completado", true); // Modal
			setSize(300, 150);
			setLocationRelativeTo(ventanaPregunta); // Centrar sobre la ventana principal
			setLayout(new BorderLayout());

			boolean aprobado = (puntuacion / (double) paneles.size() >= 0.0); //FIXME: Para la entrega hay que ponerlo en 0.8
			controladorCursoProgreso.avanzar(curso, aprobado);
			String resultado = aprobado ? "Aprobado :)" : "Suspenso :(";
			JLabel mensaje = new JLabel("¡Juego terminado! Resultado : " + resultado, JLabel.CENTER);

			// Mensaje de resultado

			add(mensaje, BorderLayout.CENTER);

			// Botones
			JPanel panelBotones = new JPanel();
			JButton btnSalir = new JButton("Salir");

			// Acción para salir
			btnSalir.addActionListener(e -> {
				dispose();
				ventanaPregunta.dispose();
			});

			panelBotones.add(btnSalir);
			add(panelBotones, BorderLayout.SOUTH);
		}
	}
}
