package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import umu.pds.duolingoBaratero.windows.components.CursoCellRenderer;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<CursoEnProgreso> listaCursos;
	private DefaultListModel<CursoEnProgreso> modeloCursos;
	private BarraSuperior panelSuperior;
	private final ControladorCursoPlantilla cPlantilla;
	private final ControladorUsuario cUsuario;
	private final ControladorCursoProgreso cProgreso;
	private final ControladorPregunta cPregunta;

	public VentanaPrincipal(ControladorUsuario cUsuario, ControladorCursoPlantilla cPlantilla,
			ControladorCursoProgreso cProgreso, ControladorPregunta cPregunta) {
		this.cUsuario = cUsuario;
		this.cPlantilla = cPlantilla;
		this.cProgreso = cProgreso;
		this.cPregunta = cPregunta;
		setTitle("Continúa tus cursos");
		setSize(600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		panelSuperior = new BarraSuperior(this, cUsuario, cPlantilla, cProgreso, cPregunta);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// Panel central con etiquetas y listas de cursos
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 2));
		// Panel izquierdo con etiqueta y lista de cursos en progreso
		JPanel panelCursosEnProgreso = new JPanel(new BorderLayout());
		JLabel labelCursosEnProgreso = new JLabel("Cursos empezados", SwingConstants.CENTER);
		panelCursosEnProgreso.add(labelCursosEnProgreso, BorderLayout.NORTH);
		modeloCursos = new DefaultListModel<>();
		listaCursos = new JList<>(modeloCursos);
		listaCursos.setCellRenderer(new CursoCellRenderer(cPlantilla));
		listaCursos.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) { // Evita múltiples llamadas
				if (userHasVidas())
					manejarSeleccionCursosEmpezados(listaCursos.getSelectedValue());
			}
		});
		panelCursosEnProgreso.add(new JScrollPane(listaCursos), BorderLayout.CENTER);
		panelCentral.add(panelCursosEnProgreso);
		getContentPane().add(panelCentral, BorderLayout.CENTER);

		// Panel de botones
		JPanel panelBotones = new JPanel();
		JButton btnNuevoCurso = new JButton("Empieza un nuevo curso");
		btnNuevoCurso.addActionListener(e -> abrirVentanaElegirCurso());

		panelBotones.add(btnNuevoCurso);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Cargar datos iniciales
		refreshCursos();
	}

	private boolean userHasVidas() {
		if (cUsuario.recuperarVida()) {
			return true;
		}
		JOptionPane.showMessageDialog(this,
				"No te quedan vidas para practicar, tienes que esperar a recuperar almenos una vida", "Atención",
				JOptionPane.WARNING_MESSAGE);
		return false;
	}

	private Set<CursoEnProgreso> getCursosEnProgreso() {
		return cUsuario.getCursosUsuarioActual();
	}

	private synchronized void manejarSeleccionCursosEmpezados(CursoEnProgreso curso) {
		if (curso == null)
			return;

		if (cProgreso.estaFinalizado(curso)) {
			manejarCursoFinalizado(curso);
		} else {
			manejarCursoNoFinalizado(curso);
		}

		listaCursos.clearSelection();
	}

	private void manejarCursoFinalizado(CursoEnProgreso curso) {
		int opcion = mostrarDialogoReinicioCurso();

		if (opcion == JOptionPane.YES_OPTION) {
			cProgreso.reiniciar(curso);
		} else {
			eliminarCursoSeleccionado(curso);
		}
	}

	private int mostrarDialogoReinicioCurso() {
		Object[] opciones = { "Sí", "No" };
		return JOptionPane.showOptionDialog(this, "Has finalizado este curso. ¿Quieres empezarlo de nuevo?", "Aviso",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);
	}

	private void eliminarCursoSeleccionado(CursoEnProgreso curso) {
		int index = listaCursos.getSelectedIndex();
		if (index != -1) {
			DefaultListModel<CursoEnProgreso> model = (DefaultListModel<CursoEnProgreso>) listaCursos.getModel();
			model.remove(index);
		}
		cUsuario.borrarCurso(curso);
	}

	private void manejarCursoNoFinalizado(CursoEnProgreso curso) {
		if (curso.isNuevo()) {
			openVentanaEstrategia(curso);

		}
		VentanaPregunta ventanaPregunta = new VentanaPregunta(curso, cProgreso, cPregunta, cUsuario);
		ventanaPregunta.setVisible(true);
	}

	public void refreshCursos() {
		modeloCursos.clear();
		for (CursoEnProgreso curso : getCursosEnProgreso()) {
			modeloCursos.addElement(curso);
		}
		listaCursos.setModel(modeloCursos);
	}

	private void abrirVentanaElegirCurso() {
		VentanaElegirCurso ventana = new VentanaElegirCurso(this, cPlantilla, cUsuario, cProgreso, cPregunta);
		ventana.setVisible(true);
		this.setVisible(false);
	}

	private void openVentanaEstrategia(CursoEnProgreso curso) {
		VentanaSeleccionEstrategica ventana = new VentanaSeleccionEstrategica(this, curso, cProgreso);
		ventana.setVisible(true);
	}

}
