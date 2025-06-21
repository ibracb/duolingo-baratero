 package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorEstadistica;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Ventana que muestra los cursos disponibles para el usuario.
 * Permite al usuario seleccionar un curso y ver su información.
 */
public class VentanaCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Referencia a la ventana de inicio para poder volver a ella.
	 */
	private final VentanaInicio v;
	
	/**
	 * Controlador de curso plantilla.
	 * Permite acceder a los cursos disponibles y sus detalles.
	 */
	private final ControladorCursoPlantilla cPlantilla;
	
	/**
	 * Controladores de usuario.
	 */
	private final ControladorUsuario cUsuario;
	
	/**
	 * Controlador de progreso del curso.
	 */
	private final ControladorCursoProgreso cProgreso;
	
	/**
	 * Controlador de preguntas.
	 */
	private final ControladorPregunta cPregunta;
	private final ControladorEstadistica cEstadistica;
	
	/**
	 * Constructor de la ventana de cursos.
	 * Inicializa la ventana y sus componentes.
	 *
	 * @param v Ventana de inicio para poder volver a ella.
	 * @param cPlantilla Controlador de curso plantilla.
	 * @param cUsuario Controlador de usuario.
	 * @param cProgreso Controlador de progreso del curso.
	 * @param cPregunta Controlador de preguntas.
	 * @param cEstadistica Controlador de estadísticas.
	 */
	public VentanaCursos(VentanaInicio v, ControladorCursoPlantilla cPlantilla, ControladorUsuario cUsuario, ControladorCursoProgreso cProgreso, ControladorPregunta cPregunta, ControladorEstadistica cEstadistica) {
		this.cPlantilla = cPlantilla;
		this.cUsuario = cUsuario;
		this.cProgreso = cProgreso;
		this.cPregunta = cPregunta;
		this.cEstadistica = cEstadistica;
		this.v = v;
		setTitle("Temas Disponibles");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Panel superior con el mensaje
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(200, 220, 255));
		JLabel lblTitulo = new JLabel("🌍 ¡Tantos temas como puedas imaginar! 🚀📖");
		panelSuperior.add(lblTitulo);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// Panel central con los botones de temas
		JPanel panelCentral = new JPanel(new GridLayout(2, 3, 10, 10));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Botones con iconos simulados
		ImageIcon iconoLiteratura = new ImageIcon(getClass().getResource("/Ingles.png"));
		Image imagenLiteratura = iconoLiteratura.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
		iconoLiteratura = new ImageIcon(imagenLiteratura);
		JButton botonIdiomas = new JButton("Ingles");
		botonIdiomas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		botonIdiomas.setPreferredSize(new Dimension(140, 60));
		botonIdiomas.setIcon(iconoLiteratura);
		botonIdiomas.addActionListener(
				e -> abrirVentanaInformacion(cPlantilla.getCursoPlantilla("Ingles")));
		panelCentral.add(botonIdiomas);

		ImageIcon iconoOrdenador = new ImageIcon(getClass().getResource("/Informatica.png"));
		Image imagenOrdenador = iconoOrdenador.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		iconoOrdenador = new ImageIcon(imagenOrdenador);
		JButton botonProgramacion = new JButton("Informatica");
		botonProgramacion.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		botonProgramacion.setPreferredSize(new Dimension(140, 60));
		botonProgramacion.setIcon(iconoOrdenador);
		botonProgramacion.addActionListener(
				e -> abrirVentanaInformacion(cPlantilla.getCursoPlantilla("Informatica")));
		panelCentral.add(botonProgramacion);

		ImageIcon iconoRadio = new ImageIcon(getClass().getResource("/Música.png"));
		Image imagenRadio = iconoRadio.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		iconoRadio = new ImageIcon(imagenRadio);
		JButton botonMusica = new JButton("Música");
		botonMusica.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		botonMusica.setPreferredSize(new Dimension(140, 60));
		botonMusica.setIcon(iconoRadio);
		botonMusica.addActionListener(
				e -> abrirVentanaInformacion(cPlantilla.getCursoPlantilla("Música")));
		panelCentral.add(botonMusica);

		ImageIcon iconoCiencia = new ImageIcon(getClass().getResource("/Ciencia.png"));
		Image imagenCiencia = iconoCiencia.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		iconoCiencia = new ImageIcon(imagenCiencia);
		JButton botonCiencia = new JButton("Ciencia");
		botonCiencia.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		botonCiencia.setPreferredSize(new Dimension(140, 60));
		botonCiencia.setIcon(iconoCiencia);
		botonCiencia.addActionListener(

				e -> abrirVentanaInformacion(cPlantilla.getCursoPlantilla("Ciencia")));

		panelCentral.add(botonCiencia);

		ImageIcon iconoEstudioso = new ImageIcon(getClass().getResource("/Estudios.png"));
		Image imagenEstudioso = iconoEstudioso.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		iconoEstudioso = new ImageIcon(imagenEstudioso);
		JButton botonEstudios = new JButton("Estudios");
		botonEstudios.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		botonEstudios.setPreferredSize(new Dimension(140, 60));
		botonEstudios.setIcon(iconoEstudioso);
		botonEstudios.addActionListener(
				e -> abrirVentanaInformacion(cPlantilla.getCursoPlantilla("Estudios")));
		panelCentral.add(botonEstudios);

		ImageIcon iconoDiseñar = new ImageIcon(getClass().getResource("/Diseño.png"));
		Image imagenDiseñar = iconoDiseñar.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		iconoDiseñar = new ImageIcon(imagenDiseñar);
		JButton botonDiseña = new JButton("Diseño");
		botonDiseña.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		botonDiseña.setPreferredSize(new Dimension(140, 60));
		botonDiseña.setIcon(iconoDiseñar);
		botonDiseña.addActionListener(
				e -> abrirVentanaInformacion(cPlantilla.getCursoPlantilla("Diseño")));
		panelCentral.add(botonDiseña);

		getContentPane().add(panelCentral, BorderLayout.CENTER);

		// Panel inferior con los botones "Volver" y "Regístrate"
		JPanel panelInferior = new JPanel();
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(e -> closeWindow());
		JButton btnRegistro = new JButton("Regístrate");
		btnRegistro.addActionListener(e -> abrirVentanaRegistro());
		btnRegistro.setBackground(Color.CYAN);
		panelInferior.add(btnVolver);
		panelInferior.add(btnRegistro);

		getContentPane().add(panelInferior, BorderLayout.SOUTH);
	}

	/**
	 * Abre una ventana de registro para el usuario.
	 * Permite al usuario registrarse si aún no lo ha hecho.
	 */
	private void abrirVentanaRegistro() {
		VentanaRegistro ventanaNueva = new VentanaRegistro(this, cUsuario, cPlantilla, cProgreso, cPregunta, cEstadistica);
		ventanaNueva.setLocationRelativeTo(null);
		ventanaNueva.setVisible(true);
		this.setVisible(false);
	}

	/**
	 * Cierra la ventana actual y vuelve a la ventana de inicio.
	 */
	private void closeWindow() {
		v.setVisible(true);
		this.dispose();
	}

	/**
	 * Abre una ventana de información del curso seleccionado.
	 * Si el curso está disponible, muestra sus detalles.
	 *
	 * @param optional CursoPlantilla opcional que contiene el curso seleccionado.
	 */
	private void abrirVentanaInformacion(Optional<CursoPlantilla> optional) {
		if (optional.isPresent()) {
			VentanaInformacion ventanaInformacion = new VentanaInformacion(optional.get(), this, cUsuario);
			ventanaInformacion.setVisible(true);
			this.setVisible(false);
		}
	}
}
