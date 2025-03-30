package umu.pds.duolingoBaratero.windows.vista;

import umu.pds.duolingoBaratero.controllers.ControladorDuolingoBaratero;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class VentanaInformacion extends JFrame {
	private static final long serialVersionUID = 1L;
	private VentanaCursos v;
	public VentanaInformacion(CursoPlantilla curso, VentanaCursos v) {
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
		iconoCurso = ControladorUsuario.INSTANCE.getScaledImage(iconoCurso, 100);
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

		JTextArea txtContenido = new JTextArea(
				"📜 Contenido:\n" + (curso.getContenidos() != null || !curso.getContenidos().isEmpty() ? curso.getContenidos() : "❌ No disponible"));
		txtContenido.setEditable(false);
		panelDerecho.add(txtContenido);

		panelCentral.add(panelDerecho);
		getContentPane().add(panelCentral, BorderLayout.CENTER);

		// Botón de volver
		JButton btnVolver = new JButton("⬅️ Volver");
		btnVolver.addActionListener(e -> cerrarVentana());
		JPanel panelInferior = new JPanel();
		panelInferior.add(btnVolver);
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
	}
	
	public void cerrarVentana() {
		v.setVisible(true);
		this.dispose();
	}

}
