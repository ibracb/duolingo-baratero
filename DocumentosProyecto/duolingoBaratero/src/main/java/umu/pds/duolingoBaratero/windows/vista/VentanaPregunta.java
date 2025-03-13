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
import umu.pds.duolingoBaratero.windows.components.BarraProgresoPanel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;

public class VentanaPregunta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BarraProgresoPanel barraProgreso;
	private BarraSuperior barraSuperior;
	private JButton btnSiguiente, btnSaltar;
	private Component horizontalGlue;
	private int currentPanel = 1;
	private long bloqueContenido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPregunta frame = new VentanaPregunta(69);
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

		ControladorCurso controlador = ControladorCurso.INSTANCE; // Controlador

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// ------- barra superior-------
		barraSuperior = new BarraSuperior();
		barraProgreso = new BarraProgresoPanel();

		// Panel que une la barra superior con la barra de progreso
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.add(barraSuperior, BorderLayout.NORTH);
		panelSuperior.add(barraProgreso, BorderLayout.SOUTH);

		contentPane.add(panelSuperior, BorderLayout.NORTH);

		JPanel panelCentral = new JPanel(new CardLayout());
		CardLayout cardLayout = (CardLayout) panelCentral.getLayout();

		// -------Futura funcionalidad real------- NO BORRAR
//		JPanel[] paneles = controlador.generarLeccion(bloqueContenido);
//		panelCentral.add(paneles[0], "panel1");
//		panelCentral.add(paneles[1], "panel2");


		// Panel para los botones de acción
		JPanel panelBotones = new JPanel(new FlowLayout());
		btnSiguiente = new JButton("siguiente");
		btnSiguiente.setBackground(new Color(0, 255, 0));

		btnSiguiente.addActionListener(e -> {

			barraProgreso.avanzar();
			currentPanel = (currentPanel % 4) + 1; // Ciclo entre 1 y 4
			cardLayout.show(panelCentral, "panel" + currentPanel);

		});

		contentPane.add(panelCentral, BorderLayout.CENTER); // **Agregarlo al centro**

		btnSaltar = new JButton("Saltar");
		btnSaltar.setBackground(new Color(255, 165, 0));
		panelBotones.add(btnSaltar);

		horizontalGlue = Box.createHorizontalGlue();
		panelBotones.add(horizontalGlue);
		panelBotones.add(btnSiguiente);

		contentPane.add(panelBotones, BorderLayout.SOUTH);

		setLocationRelativeTo(null);

	}

}
