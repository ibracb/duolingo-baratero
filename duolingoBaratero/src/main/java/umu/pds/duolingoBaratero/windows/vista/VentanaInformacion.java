package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * VentanaInformacion es una ventana que muestra información detallada sobre un curso específico.
 * Incluye el título del curso, una imagen representativa, una descripción, objetivos, tipos de preguntas,
 * nivel del curso y contenido.
 */
public class VentanaInformacion extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Referencia a la ventana de cursos para poder volver a ella.
	 */
	private VentanaCursos v;
	
	/**
	 * Controlador de usuario para manejar operaciones relacionadas con el usuario.
	 */
	@SuppressWarnings("unused")
	private final ControladorUsuario controladorUsuario;
	
	/**
	 * Constructor de la ventana de información del curso.
	 * 
	 * @param curso El curso del cual se mostrará la información.
	 * @param v La ventana de cursos desde la cual se accede a esta ventana.
	 * @param controladorUsuario El controlador de usuario para operaciones relacionadas con el usuario.
	 */
	public VentanaInformacion(CursoPlantilla curso, VentanaCursos v, ControladorUsuario controladorUsuario) {
		this.controladorUsuario = controladorUsuario;
		this.v = v;
		setTitle("📚 Aprende sobre " + curso.getNombre());
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Panel superior con el título y la imagen
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBackground(new Color(220, 230, 250));

		JLabel lblTitulo = new JLabel("📌 Aprende sobre " + curso.getNombre() + " 📌", SwingConstants.CENTER);
		panelSuperior.add(lblTitulo, BorderLayout.NORTH);

		// Cargar la imagen del curso
		ImageIcon iconoCurso = new ImageIcon(getClass().getResource("/"+ curso.getNombre() + ".png"));
		iconoCurso = controladorUsuario.getScaledImage(iconoCurso, 100);
		JLabel lblImagen = new JLabel(iconoCurso);
		panelSuperior.add(lblImagen, BorderLayout.CENTER);

		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// Panel central con la información del curso
		JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Descripción del curso
		JTextArea txtDescripcion = new JTextArea(
				"📖 Descripción breve:\n" + (curso.getDescripcion() != null ? curso.getDescripcion() : "❌ No disponible")
						+ "\n\n🎯 Objetivos del curso:\n" + (curso.getObjetivos() != null ? curso.getDescripcion() : "❌ No disponible") + "\n\n❓ Tipos de preguntas:\n"
						+ (curso.getTipoPreguntas().stream().map(tp -> "🔹 " + tp.name())
						.collect(Collectors.joining("\n"))));
		txtDescripcion.setEditable(false);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
		panelCentral.add(scrollDescripcion);

		// Panel derecho con nivel y contenido
		JPanel panelDerecho = new JPanel(new GridLayout(2, 1));
		JLabel lblNivel = new JLabel(
				"📊 Nivel del curso: " + (curso.getNivel() != null ? curso.getNivel().toString() : "❌ No especificado"));
		panelDerecho.add(lblNivel);

		panelCentral.add(panelDerecho);
		getContentPane().add(panelCentral, BorderLayout.CENTER);

		// Botón de volver
		JButton btnVolver = new JButton("⬅️ Volver");
		btnVolver.addActionListener(e -> cerrarVentana());
		JPanel panelInferior = new JPanel();
		panelInferior.add(btnVolver);
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
	}
	
	/**
	 * Cierra la ventana de información y vuelve a la ventana de cursos.
	 */
	public void cerrarVentana() {
		v.setVisible(true);
		this.dispose();
	}

}
