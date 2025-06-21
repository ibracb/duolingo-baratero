package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorEstadistica;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;

/**
 * Ventana de inicio de sesión que permite al usuario iniciar sesión o registrarse.
 * Presenta un diseño atractivo con un título, iconos y botones para navegar a otras ventanas.
 */
public class VentanaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** Controlador que maneja las operaciones de usuario */
	private final ControladorUsuario controladorUsuario;
	
	/** Controlador para manejar las plantillas de curso */
	private final ControladorCursoPlantilla cPlantilla;
	
	/** Controlador para manejar el progreso del curso */
	private final ControladorCursoProgreso cProgreso;
	
	/** Controlador para manejar las preguntas */
	private final ControladorPregunta cPregunta;
	
	/** Controlador para manejar las estadísticas */
	private final ControladorEstadistica cEstadistica;
	
	/**
	 * Constructor de la ventana de inicio.
	 * 
	 * @param controladorUsuario Controlador de usuario para manejar las operaciones de inicio de sesión y registro.
	 * @param cPlantilla Controlador de plantillas de curso.
	 * @param cProgreso Controlador del progreso del curso.
	 * @param cPregunta Controlador de preguntas.
	 * @param cEstadistica Controlador de estadísticas.
	 */
	public VentanaInicio(ControladorUsuario controladorUsuario, ControladorCursoPlantilla cPlantilla, ControladorCursoProgreso cProgreso, ControladorPregunta cPregunta, ControladorEstadistica cEstadistica) {
		this.controladorUsuario = controladorUsuario;
		this.cPlantilla = cPlantilla;
		this.cProgreso = cProgreso;
		this.cPregunta = cPregunta;
		this.cEstadistica = cEstadistica;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650, 400);
		setLocationRelativeTo(null);
        setTitle("Inicio de Sesión");
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel SouthPanel = new JPanel();
		SouthPanel.setBackground(new Color(200, 220, 255));
		getContentPane().add(SouthPanel, BorderLayout.SOUTH);
		SouthPanel.setLayout(new BorderLayout(0, 0));

		JLabel etiquetaSouthPanel = new JLabel("DISEÑA TUS PROPIOS TEMAS DE APRENDIZAJE");
		etiquetaSouthPanel.setForeground(SystemColor.desktop);
		etiquetaSouthPanel.setBackground(SystemColor.textHighlight);
		etiquetaSouthPanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		etiquetaSouthPanel.setHorizontalTextPosition(SwingConstants.CENTER);
		etiquetaSouthPanel.setHorizontalAlignment(SwingConstants.CENTER);
		SouthPanel.add(etiquetaSouthPanel, BorderLayout.NORTH);

		JLabel etiquetaSouthPanel2 = new JLabel("Idiomas Estudios Programación Música Ciencia");
		etiquetaSouthPanel2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		etiquetaSouthPanel2.setHorizontalTextPosition(SwingConstants.CENTER);
		etiquetaSouthPanel2.setHorizontalAlignment(SwingConstants.CENTER);
		SouthPanel.add(etiquetaSouthPanel2, BorderLayout.CENTER);

		JPanel centerPanel = new JPanel();
		centerPanel.setMinimumSize(new Dimension(10, 30));
		centerPanel.setFont(new Font("Perpetua", Font.PLAIN, 16));
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_CenterPanel = new GridBagLayout();
		gbl_CenterPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_CenterPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_CenterPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_CenterPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		centerPanel.setLayout(gbl_CenterPanel);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 2;
		gbc_verticalStrut_1.gridy = 2;
		centerPanel.add(verticalStrut_1, gbc_verticalStrut_1);

		JLabel etiquetaInicioSesion = new JLabel("Inicia sesion o registrate");
		etiquetaInicioSesion.setFont(new Font("Perpetua", Font.PLAIN, 20));
		GridBagConstraints gbc_etiquetaInicioSesion = new GridBagConstraints();
		gbc_etiquetaInicioSesion.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaInicioSesion.gridx = 2;
		gbc_etiquetaInicioSesion.gridy = 3;
		centerPanel.add(etiquetaInicioSesion, gbc_etiquetaInicioSesion);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 3;
		centerPanel.add(verticalStrut, gbc_verticalStrut);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setMinimumSize(new Dimension(30, 0));
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 4;
		centerPanel.add(horizontalStrut, gbc_horizontalStrut);

		JLabel etiquetaIconoCentro = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("/cerebro.png"));
		Image imagenEscala = icon.getImage().getScaledInstance(270, 270, Image.SCALE_SMOOTH);
		icon = new ImageIcon(imagenEscala);
		etiquetaIconoCentro.setIcon(icon);
		GridBagConstraints gbc_etiquetaIconoCentro = new GridBagConstraints();
		gbc_etiquetaIconoCentro.gridheight = 7;
		gbc_etiquetaIconoCentro.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaIconoCentro.gridx = 1;
		gbc_etiquetaIconoCentro.gridy = 2;
		centerPanel.add(etiquetaIconoCentro, gbc_etiquetaIconoCentro);

		JButton loginButton = new JButton("Ya tengo Cuenta");
		loginButton.setPreferredSize(new Dimension(120, 25));
		loginButton.setMinimumSize(new Dimension(120, 25));
		loginButton.setMaximumSize(new Dimension(120, 25));
		loginButton.addActionListener(e -> abrirVentanaLogin());

		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		verticalStrut_1_1.setMinimumSize(new Dimension(0, 40));
		GridBagConstraints gbc_verticalStrut_1_1 = new GridBagConstraints();
		gbc_verticalStrut_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1_1.gridx = 2;
		gbc_verticalStrut_1_1.gridy = 5;
		centerPanel.add(verticalStrut_1_1, gbc_verticalStrut_1_1);
		GridBagConstraints gbc_LogInButton = new GridBagConstraints();
		gbc_LogInButton.insets = new Insets(0, 0, 5, 5);
		gbc_LogInButton.gridx = 2;
		gbc_LogInButton.gridy = 6;
		centerPanel.add(loginButton, gbc_LogInButton);

		JButton StartButton = new JButton("Empieza Ahora");
		StartButton.setPreferredSize(new Dimension(120, 25));
		StartButton.setMinimumSize(new Dimension(120, 25));
		StartButton.setMaximumSize(new Dimension(120, 25));
		StartButton.addActionListener( e -> abrirTemasWindow());
		GridBagConstraints gbc_StartButton = new GridBagConstraints();
		gbc_StartButton.insets = new Insets(0, 0, 5, 5);
		gbc_StartButton.gridx = 2;
		gbc_StartButton.gridy = 7;
		centerPanel.add(StartButton, gbc_StartButton);

		JPanel NorthPanel = new JPanel();
		getContentPane().add(NorthPanel, BorderLayout.NORTH);
		ImageIcon icono = new ImageIcon(getClass().getResource("/pinguino.png"));
		Image imagenEscalada = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icono = new ImageIcon(imagenEscalada);
		JLabel etiquetaTitulo = new JLabel("Bienvenido a DuolingoBaratero", icono, JLabel.CENTER);
		etiquetaTitulo.setForeground(SystemColor.desktop);
		etiquetaTitulo.setFont(new Font("Monotype Corsiva", Font.PLAIN, 22));

		// Configurar la interfaz
		NorthPanel.add(etiquetaTitulo);

	}
	
	/**
	 * Método para abrir la ventana de temas.
	 */
	private void abrirTemasWindow() {
		VentanaCursos themeWindow = new VentanaCursos(this, cPlantilla, controladorUsuario, cProgreso, cPregunta, cEstadistica);
		themeWindow.setVisible(true);
		this.setVisible(false);
	}
	
	/**
	 * Método para abrir la ventana de inicio de sesión.
	 */
	private void abrirVentanaLogin() {
		VentanaLogin ventanaLogin = new VentanaLogin(this, controladorUsuario, cPlantilla, cProgreso, cPregunta, cEstadistica);
		ventanaLogin.setVisible(true);
		this.setVisible(false);
	}

}
