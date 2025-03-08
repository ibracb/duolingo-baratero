package umu.pds.duolingoBaratero.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;

import javax.swing.border.EmptyBorder;

public class VentanaPreguntaListen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BarraProgresoPanel barraProgreso;
	private BarraSuperior barraSuperior;
	private JToggleButton[] imagenes; // Botones de imagen
	private JButton btnSiguiente, btnSaltar;
	private JLabel lblPregunta;
	private JLabel lblAudio;
	private Component horizontalGlue;
	private JButton btnReproducir;
	private JRadioButton[] opciones;
	private Container panelOpciones;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPreguntaListen frame = new VentanaPreguntaListen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPreguntaListen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// ------- PANEL SUPERIOR-------
		barraSuperior = new BarraSuperior();
		barraProgreso = new BarraProgresoPanel();

		// Panel que une la barra superior con la barra de progreso
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBackground(new Color(128, 128, 128));
		panelSuperior.add(barraSuperior, BorderLayout.NORTH);
		panelSuperior.add(barraProgreso, BorderLayout.SOUTH);

		contentPane.add(panelSuperior, BorderLayout.NORTH);

		// ------- PANEL CENTRAL
		JPanel panelCentral = new JPanel();
		opciones = new JRadioButton[3];

		
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[] { 1, 205, 281, 200, 0 };
		gbl_panelCentral.rowHeights = new int[] { 1, 0, 43, 0, 40, 39, 19, 0, 0 };
		gbl_panelCentral.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelCentral.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentral.setLayout(gbl_panelCentral);
		

		// ---------- AUDIO + BOTÓN ----------
		JPanel panelAudio = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		lblAudio = new JLabel("Escucha el siguiente audio:");
		lblAudio.setFont(new Font("Arial", Font.BOLD, 16));

		btnReproducir = new JButton(new ImageIcon("src/main/resources/boton-de-play.png"));
		btnReproducir.setPreferredSize(new Dimension(40, 30));
		btnReproducir.addActionListener(e -> reproducirAudio()); // Llamar a función de audio

		panelAudio.add(lblAudio);
		panelAudio.add(btnReproducir);
		panelAudio.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_panelAudio = new GridBagConstraints();
		gbc_panelAudio.insets = new Insets(0, 0, 5, 5);
		gbc_panelAudio.gridx = 2;
		gbc_panelAudio.gridy = 2;
		panelCentral.add(panelAudio, gbc_panelAudio);

		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		panelCentral.add(lblNewLabel, gbc_lblNewLabel);

		// ---------- TEXTO DE PREGUNTA ----------
		lblPregunta = new JLabel("¿Cuál es la respuesta correcta?", SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
		lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblPregunta = new GridBagConstraints();
		gbc_lblPregunta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPregunta.gridx = 2;
		gbc_lblPregunta.gridy = 4;
		panelCentral.add(lblPregunta, gbc_lblPregunta);
		
		// -------Panel Listen-------
		
		ButtonGroup grupoOpciones = new ButtonGroup(); // Para selección única
		
		rdbtnNewRadioButton = new JRadioButton("Opcion 1");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 2;
		gbc_rdbtnNewRadioButton.gridy = 5;
		panelCentral.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Opcion 2");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 2;
		gbc_rdbtnNewRadioButton_1.gridy = 6;
		panelCentral.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Opcion 3");
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 2;
		gbc_rdbtnNewRadioButton_2.gridy = 7;
		panelCentral.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);

		grupoOpciones.add(rdbtnNewRadioButton);
		grupoOpciones.add(rdbtnNewRadioButton_1);
		grupoOpciones.add(rdbtnNewRadioButton_2);
		
		// -------PANEL INFERIOR
		JPanel panelBotones = new JPanel(new FlowLayout());
		btnSiguiente = new JButton("siguiente");
		btnSiguiente.setBackground(new Color(0, 255, 0));

		btnSiguiente.addActionListener(e -> barraProgreso.avanzar());
		btnSaltar = new JButton("Saltar");
		btnSaltar.setBackground(new Color(255, 165, 0));
		panelBotones.add(btnSaltar);

		horizontalGlue = Box.createHorizontalGlue();
		panelBotones.add(horizontalGlue);
		panelBotones.add(btnSiguiente);

		contentPane.add(panelBotones, BorderLayout.SOUTH);

		setLocationRelativeTo(null);

	}

	private void reproducirAudio() {
		// Aquí iría la lógica para reproducir el audio
		System.out.println("Reproduciendo audio...");
	}

	private void ajustarImagenes() {
		// Obtener el tamaño actual de la ventana
		int width = getWidth();
		int height = getHeight();

		// Establecer un tamaño relativo para las imágenes en función del tamaño de la
		// ventana
		int imageSize = Math.min(width, height) / 5; // Hacemos que las imágenes tengan un tamaño proporcional

		for (JToggleButton imageButton : imagenes) {
			ImageIcon icon = (ImageIcon) imageButton.getIcon();
			Image img = icon.getImage();
			Image resizedImage = img.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
			imageButton.setIcon(new ImageIcon(resizedImage)); // Actualizar el icono con la nueva imagen redimensionada
		}
	}
}
