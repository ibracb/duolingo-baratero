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
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class VentanaCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	private final VentanaInicio v;
	private final ControladorCursoPlantilla cPlantilla;
	private final ControladorUsuario cUsuario;
	private final ControladorCursoProgreso cProgreso;
	private final ControladorPregunta cPregunta;

	public VentanaCursos(VentanaInicio v, ControladorCursoPlantilla cPlantilla, ControladorUsuario cUsuario, ControladorCursoProgreso cProgreso, ControladorPregunta cPregunta) {
		this.cPlantilla = cPlantilla;
		this.cUsuario = cUsuario;
		this.cProgreso = cProgreso;
		this.cPregunta = cPregunta;
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

	private void abrirVentanaRegistro() {
		VentanaRegistro ventanaNueva = new VentanaRegistro(this, cUsuario, cPlantilla, cProgreso, cPregunta);
		ventanaNueva.setLocationRelativeTo(null);
		ventanaNueva.setVisible(true);
		this.setVisible(false);
	}

	private void closeWindow() {
		v.setVisible(true);
		this.dispose();
	}

	private void abrirVentanaInformacion(Optional<CursoPlantilla> optional) {
		if (optional.isPresent()) {
			VentanaInformacion ventanaInformacion = new VentanaInformacion(optional.get(), this, cUsuario);
			ventanaInformacion.setVisible(true);
			this.setVisible(false);
		}
	}
}
