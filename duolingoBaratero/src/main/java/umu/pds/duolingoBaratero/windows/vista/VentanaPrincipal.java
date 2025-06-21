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
import umu.pds.duolingoBaratero.controllers.ControladorEstadistica;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import umu.pds.duolingoBaratero.windows.components.CursoCellRenderer;

/**
 * Ventana principal de la aplicación que muestra los cursos en progreso del usuario.
 * Permite al usuario seleccionar un curso para continuar o reiniciar si ya ha finalizado.
 */
public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista que muestra los cursos en progreso del usuario.
	 */
	private JList<CursoEnProgreso> listaCursos;
	
	/**
	 * Modelo de la lista que contiene los cursos en progreso.
	 */
	private DefaultListModel<CursoEnProgreso> modeloCursos;
	
	/**
	 * Panel superior que contiene la barra de navegación y opciones del usuario.
	 */
	private BarraSuperior panelSuperior;
	
	/**
	 * Controlador para manejar las plantillas de curso.
	 */
	private final ControladorCursoPlantilla cPlantilla;
	
	/**
	 * Controlador para manejar el progreso de los cursos del usuario.
	 */
	private final ControladorUsuario cUsuario;
	
	/**
	 * Controlador para manejar el progreso de los cursos en curso.
	 */
	private final ControladorCursoProgreso cProgreso;
	
	/**
	 * Controlador para manejar las preguntas del curso.
	 */
	private final ControladorPregunta cPregunta;
	
	/**
	 * Controlador para manejar las estadísticas del usuario.
	 */
	private final ControladorEstadistica cEstadistica;
	
	/**
	 * Constructor de la ventana principal.
	 * 
	 * @param cUsuario Controlador de usuario para manejar las acciones del usuario.
	 * @param cPlantilla Controlador de plantillas de curso.
	 * @param cProgreso Controlador de progreso de cursos.
	 * @param cPregunta Controlador de preguntas del curso.
	 * @param cEstadistica Controlador de estadísticas del usuario.
	 */
	public VentanaPrincipal(ControladorUsuario cUsuario, ControladorCursoPlantilla cPlantilla,
			ControladorCursoProgreso cProgreso, ControladorPregunta cPregunta, ControladorEstadistica cEstadistica) {
		this.cUsuario = cUsuario;
		this.cPlantilla = cPlantilla;
		this.cProgreso = cProgreso;
		this.cPregunta = cPregunta;
		this.cEstadistica = cEstadistica;
		setTitle("Continúa tus cursos");
		setSize(600, 450);
		setDefaultCloseOperation(cerrarSesion());
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		panelSuperior = new BarraSuperior(this, cUsuario, cPlantilla, cProgreso, cPregunta, cEstadistica);
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
	
	/**
	 * Método para verificar si el usuario tiene vidas disponibles para practicar.
	 * 
	 * @return true si el usuario tiene vidas, false en caso contrario.
	 */
	private boolean userHasVidas() {
		if (cUsuario.recuperarVida()) {
			return true;
		}
		JOptionPane.showMessageDialog(this,
				"No te quedan vidas para practicar, tienes que esperar a recuperar almenos una vida", "Atención",
				JOptionPane.WARNING_MESSAGE);
		return false;
	}

	/**
	 * Método para obtener los cursos en progreso del usuario.
	 * 
	 * @return un conjunto de cursos en progreso.
	 */
	private Set<CursoEnProgreso> getCursosEnProgreso() {
		return cUsuario.getCursosUsuarioActual();
	}

	/**
	 * Método que maneja la selección de un curso en progreso.
	 * Si el curso está finalizado, se pregunta al usuario si desea reiniciarlo o eliminarlo.
	 * Si no está finalizado, se abre la ventana de preguntas del curso.
	 * 
	 * @param curso El curso seleccionado por el usuario.
	 */
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

	/**
	 * Maneja el caso en que un curso ha sido finalizado.
	 * Muestra un diálogo preguntando al usuario si desea reiniciar el curso o eliminarlo.
	 * 
	 * @param curso El curso que ha sido finalizado.
	 */
	private void manejarCursoFinalizado(CursoEnProgreso curso) {
		int opcion = mostrarDialogoReinicioCurso();

		if (opcion == JOptionPane.YES_OPTION) {
			cProgreso.reiniciar(curso);
		} else {
			eliminarCursoSeleccionado(curso);
		}
	}

	/**
	 * Muestra un diálogo para preguntar al usuario si desea reiniciar el curso finalizado.
	 * 
	 * @return La opción seleccionada por el usuario (Sí o No).
	 */
	private int mostrarDialogoReinicioCurso() {
		Object[] opciones = { "Sí", "No" };
		return JOptionPane.showOptionDialog(this, "Has finalizado este curso. ¿Quieres empezarlo de nuevo?", "Aviso",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);
	}

	/**
	 * Elimina el curso seleccionado de la lista y del controlador de usuario.
	 * 
	 * @param curso El curso que se desea eliminar.
	 */
	private void eliminarCursoSeleccionado(CursoEnProgreso curso) {
		int index = listaCursos.getSelectedIndex();
		if (index != -1) {
			DefaultListModel<CursoEnProgreso> model = (DefaultListModel<CursoEnProgreso>) listaCursos.getModel();
			model.remove(index);
		}
		cUsuario.borrarCurso(curso);
	}

	/**
	 * Maneja el caso en que un curso no ha sido finalizado.
	 * Abre la ventana de preguntas del curso o la ventana de estrategia si es un curso nuevo.
	 * 
	 * @param curso El curso que está en progreso.
	 */
	private void manejarCursoNoFinalizado(CursoEnProgreso curso) {
		if (curso.isNuevo()) {
			openVentanaEstrategia(curso);

		}
		VentanaPregunta ventanaPregunta = new VentanaPregunta(curso, cProgreso, cPregunta, cUsuario, cEstadistica);
		ventanaPregunta.setVisible(true);
	}

	/**
	 * Actualiza la lista de cursos en progreso.
	 * Limpia el modelo actual y añade los cursos en progreso del usuario.
	 */
	public void refreshCursos() {
		modeloCursos.clear();
		for (CursoEnProgreso curso : getCursosEnProgreso()) {
			modeloCursos.addElement(curso);
		}
		listaCursos.setModel(modeloCursos);
	}

	/**
	 * Abre la ventana para elegir un nuevo curso.
	 * Cierra la ventana actual para evitar confusiones al usuario.
	 */
	private void abrirVentanaElegirCurso() {
		VentanaElegirCurso ventana = new VentanaElegirCurso(this, cPlantilla, cUsuario, cProgreso, cPregunta, cEstadistica);
		ventana.setVisible(true);
		this.setVisible(false);
	}
	
	/**
	 * Abre la ventana de selección estratégica para un curso en progreso.
	 * 
	 * @param curso El curso en progreso para el cual se desea seleccionar una estrategia.
	 */
	private void openVentanaEstrategia(CursoEnProgreso curso) {
		VentanaSeleccionEstrategica ventana = new VentanaSeleccionEstrategica(this, curso, cProgreso);
		ventana.setVisible(true);
	}
	
	/**
	 * Cierra la sesión del usuario y devuelve la operación de cierre de la ventana.
	 * 
	 * @return La operación de cierre de la ventana.
	 */
	private int cerrarSesion() {
		cEstadistica.cerrarSesion();
		return JFrame.EXIT_ON_CLOSE;
	}

}
