package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.aprendizajes.Aprendizaje;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.models.aprendizajes.FactoriaAprendizaje;
import umu.pds.duolingoBaratero.services.IComprobador;
import umu.pds.duolingoBaratero.windows.components.BarraProgresoPanel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperiorPreguntas;
import umu.pds.duolingoBaratero.windows.components.MensajeTemporal;

public class VentanaPregunta extends JFrame {
	private static final int PANEL_INICIAL = 0;
	private static final long serialVersionUID = 1L;
	private final static boolean APROBADO = true;
	private final static boolean SUSPENSO = false;
	private JPanel contentPane;
	private JPanel panelCentral;
	private ArrayList<JPanel> paneles;
	private CardLayout cardLayout;
	private BarraProgresoPanel barraProgreso;
	private BarraSuperiorPreguntas barraSuperior;
	private JButton btnSiguiente, btnSaltar;
	private Component horizontalGlue;
	private int currentPanel;
	private CursoEnProgreso curso;
	private final ControladorCursoProgreso controladorCursoProgreso;
	private final ControladorPregunta controladorPregunta;
	private final ControladorUsuario controladorUsuario;

	public VentanaPregunta(CursoEnProgreso curso, ControladorCursoProgreso controladorCursoprogreso,
			ControladorPregunta controladorPregunta, ControladorUsuario controladorUsuario) {
		this.controladorCursoProgreso = controladorCursoprogreso;
		this.controladorPregunta = controladorPregunta;
		this.controladorUsuario = controladorUsuario;
		this.curso = curso;
		currentPanel = PANEL_INICIAL;
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
		barraSuperior = new BarraSuperiorPreguntas(this, controladorUsuario);
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

		btnSiguiente.addActionListener(e -> procesarSiguiente());

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

	private void procesarSiguiente() {
		IComprobador panel = (IComprobador) paneles.get(currentPanel);

		if (!panel.isOpcionElegida()) {
			JOptionPane.showMessageDialog(this, "Debe elegir una opción o saltar para ir a la siguiente pregunta.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean respuestaCorrecta = controladorPregunta.procesarRespuesta(panel.getPregunta(),
				panel.getRespuestaUsuario());

		if (respuestaCorrecta) {
			MensajeTemporal.mostrarMensaje("¡Correcto!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			MensajeTemporal.mostrarMensaje(
					"Fallaste, la respuesta correcta era: " + panel.getPregunta().getRespuestaCorrecta(),
					JOptionPane.ERROR_MESSAGE);

			if (controladorUsuario.restarVidaUsuario() <= 0) {
				new DialogoFinal(this, SUSPENSO).setVisible(true);
			}
			barraSuperior.updateVidas();
		}

		barraProgreso.avanzar(respuestaCorrecta);
		avanzarPregunta();
	}

	private void avanzarPregunta() {
		if (currentPanel < paneles.size() - 1) {
			currentPanel++;
			cardLayout.show(panelCentral, "panel" + currentPanel);
		} else {
			new DialogoFinal(this, APROBADO).setVisible(true);
		}

	}

	// -------- METODO DE PRUEBA --------------
	private ArrayList<JPanel> getPaneles() {

		AprendizajeSeleccionado seleccion = curso.getAprendizaje(); // O de usuario si se guarda ahí
		Aprendizaje aprendizaje = FactoriaAprendizaje.INSTANCE.getAprendizaje(seleccion);
		Set<Pregunta> preguntas = aprendizaje
				.seleccionarPreguntas(controladorPregunta.obtenerPreguntasDelBloque(curso));
		// Set<Pregunta> preguntas =
		// controladorPregunta.obtenerPreguntasDelBloque(curso);
		return preguntas.stream().map(Pregunta::crearPanel).collect(Collectors.toCollection(ArrayList::new));
	}

	public class DialogoFinal extends JDialog {

	    private static final long serialVersionUID = 1L;

	    public DialogoFinal(JFrame ventanaPregunta, boolean aprobado) {
	        super(ventanaPregunta, "Juego Terminado", true);
	        setSize(500, 220); // más ancho para que quepa el texto
	        setLocationRelativeTo(ventanaPregunta);
	        setLayout(new BorderLayout());
	        getContentPane().setBackground(new Color(245, 245, 245)); // blanco suave

	        controladorCursoProgreso.avanzar(curso, aprobado);
	        String resultado = aprobado 
	            ? "¡Vamos guerrero! No hace falta que te cambies de carrera."
	            : "Es el fin... Toca cambiarse a psicología, no has aprobado.";

	        JLabel mensaje = new JLabel("<html><div style='text-align: center;'>" + resultado + "</div></html>", JLabel.CENTER);
	        mensaje.setForeground(new Color(33, 37, 41)); // gris oscuro
	        mensaje.setFont(new Font("Arial", Font.BOLD, 20));
	        mensaje.setBorder(new EmptyBorder(30, 30, 30, 30));

	        add(mensaje, BorderLayout.CENTER);

	        JPanel panelBotones = new JPanel();
	        panelBotones.setBackground(new Color(245, 245, 245));
	        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));

	        JButton btnSalir = new JButton("Salir");
	        btnSalir.setBackground(new Color(52, 152, 219)); // azul suave
	        btnSalir.setForeground(Color.WHITE);
	        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
	        btnSalir.setFocusPainted(false);
	        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        btnSalir.setPreferredSize(new Dimension(100, 40));

	        btnSalir.addActionListener(e -> {
	            dispose();
	            ventanaPregunta.dispose();
	        });

	        panelBotones.add(btnSalir);
	        add(panelBotones, BorderLayout.SOUTH);
	    }
	}


} 