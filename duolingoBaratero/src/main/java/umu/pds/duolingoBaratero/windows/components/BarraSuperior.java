package umu.pds.duolingoBaratero.windows.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.time.ZoneId;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorEstadistica;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.windows.vista.VentanaElegirCurso;
import umu.pds.duolingoBaratero.windows.vista.VentanaEstadisticas;
import umu.pds.duolingoBaratero.windows.vista.VentanaPrincipal;

/**
 * Barra superior que contiene botones para navegar entre diferentes ventanas
 * y un temporizador que muestra las vidas del usuario.
 */
public class BarraSuperior extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Duración en milisegundos para la recuperación de vidas del usuario.
	 */
	private final long DURACION = 5 * 60 * 1000; // 1 minuto

	/**
	 * Botón para volver a la ventana principal.
	 */
	private JButton btnHome;
	
	/**
	 * Botón para abrir la ventana de estadísticas.
	 */
	private JButton btnEstadisticas;
	
	/**
	 * Botón para importar un curso.
	 */
	private JButton btnImportarCurso;
	
	/**
	 * Botón para exportar un curso.
	 */
	private JButton btnExportarCurso;
	
	/**
	 * Ventana actual donde se muestra la barra superior.
	 */
	private JFrame ventanaActual;
	
	/**
	 * Controlador de usuario que maneja las operaciones relacionadas con el usuario.
	 */
	private final ControladorUsuario cUsuario;
	
	/**
	 * Controlador de curso plantilla que maneja las operaciones relacionadas con los cursos plantilla.
	 */
	private final ControladorCursoPlantilla controladorPlantilla;
	
	/**
	 * Controlador de progreso del curso que maneja las operaciones relacionadas con el progreso del curso.
	 */
	private final ControladorCursoProgreso cProgreso;
	
	/**
	 * Controlador de preguntas que maneja las operaciones relacionadas con las preguntas del curso.
	 */
	private final ControladorPregunta cPregunta;
	
	/**
	 * Controlador de estadísticas que maneja las operaciones relacionadas con las estadísticas del usuario.
	 */
	private final ControladorEstadistica cEstadistica;
	
	/**
	 * Etiqueta que muestra el temporizador de vidas del usuario.
	 */
	private JLabel labelTemporizador;
	
	/**
	 * Opciones disponibles para importar/exportar cursos.
	 */
	private final String[] opciones = { "YAML", "JSON" };
	
	/**
	 * Constructor de la barra superior.
	 * 
	 * @param ventanaActual La ventana actual donde se muestra la barra superior.
	 * @param cUsuario Controlador de usuario.
	 * @param controladorPlantilla Controlador de curso plantilla.
	 * @param cProgreso Controlador de progreso del curso.
	 * @param cPregunta Controlador de preguntas.
	 * @param cEstadistica Controlador de estadísticas.
	 */
	public BarraSuperior(JFrame ventanaActual, ControladorUsuario cUsuario,
			ControladorCursoPlantilla controladorPlantilla, ControladorCursoProgreso cProgreso,
			ControladorPregunta cPregunta, ControladorEstadistica cEstadistica) {
		this.cUsuario = cUsuario;
		this.controladorPlantilla = controladorPlantilla;
		this.cProgreso = cProgreso;
		this.cPregunta = cPregunta;
		this.cEstadistica = cEstadistica;
		setLayout(new BorderLayout());
		this.ventanaActual = ventanaActual;

		// Crear la barra de herramientas y deshabilitar el movimiento
		JToolBar barra = new JToolBar();
		barra.setFloatable(false);

		// Crear un panel para centrar los botones
		JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// Crear los botones
		btnHome = new JButton("Home 🏠");
		btnHome.addActionListener(e -> openVentanaPrincipal());
		btnEstadisticas = new JButton("Estadísticas 📊");
		btnEstadisticas.addActionListener(e -> openVentanaEstadisticas());
// Activar/desactivar modo nocturno
		btnImportarCurso = new JButton("Importar Curso");
		btnImportarCurso.addActionListener(e -> importarCurso());
		btnExportarCurso = new JButton("Exportar Curso");
		btnExportarCurso.addActionListener(e -> exportarCurso());

		labelTemporizador = new JLabel();
		labelTemporizador.setFont(new Font("Verdana", Font.BOLD, 14));
		labelTemporizador.setIcon(new ImageIcon(getClass().getResource("/corazon2.png")));
		inicializarTemporizador();

		// Agregar botones al panel central
		panelCentral.add(btnHome);
		panelCentral.add(btnEstadisticas);
		panelCentral.add(btnImportarCurso);
		panelCentral.add(btnExportarCurso);
		panelCentral.add(labelTemporizador);

		// Agregar el panel central dentro de la barra de herramientas
		barra.add(panelCentral);

		// Agregar la barra de herramientas a la parte superior del panel
		add(barra, BorderLayout.NORTH);
	}
	
	/**
	 * Método para abrir la ventana principal.
	 * Si ya se está en la ventana principal, muestra un mensaje de error.
	 */
	private void openVentanaPrincipal() {

		// Evitar cast incorrecto
		if (!(ventanaActual instanceof VentanaPrincipal)) {
			VentanaPrincipal ventana = new VentanaPrincipal(cUsuario, controladorPlantilla, cProgreso, cPregunta, cEstadistica);
			ventana.setVisible(true);
			ventanaActual.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Ya estás en la ventana principal.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método para abrir la ventana de estadísticas.
	 * Si ya se está en la ventana de estadísticas, muestra un mensaje de error.
	 */
	private void openVentanaEstadisticas() {

		// Evitar cast incorrecto
		if (!(ventanaActual instanceof VentanaEstadisticas)) {
			VentanaEstadisticas ventana = new VentanaEstadisticas(cUsuario, controladorPlantilla, cProgreso, cPregunta, cEstadistica);
			ventana.setVisible(true);
			ventanaActual.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Ya estás en la ventana de estadísticas.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método para exportar un curso.
	 * Muestra un diálogo para seleccionar el tipo de archivo y luego abre una ventana para elegir el curso a exportar.
	 */
	public void exportarCurso() {
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Selecciona el tipo de archivo:"));
		panel.add(comboBox);

		int resultado = JOptionPane.showConfirmDialog(null, panel, "Importar curso", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (resultado == JOptionPane.OK_OPTION) {

			String tipoSeleccionado = "." + comboBox.getSelectedItem().toString().toLowerCase(); // "yaml" o "json"
			VentanaElegirCurso ventana = new VentanaElegirCurso(null, controladorPlantilla, cUsuario, cProgreso, cPregunta,cEstadistica);
			ventana.setVisible(true);
			ventana.setEsParaExportar(true);
			ventana.setExtension(tipoSeleccionado);
			ventanaActual.dispose();
		}

	}
	
	/**
	 * Método para importar un curso.
	 * Muestra un diálogo para seleccionar el tipo de archivo y luego permite al usuario elegir un archivo para importar.
	 */
	public void importarCurso() {
		// Crear ComboBox dentro de un JPanel para pasarlo a JOptionPane
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Selecciona el tipo de archivo:"));
		panel.add(comboBox);

		int resultado = JOptionPane.showConfirmDialog(null, panel, "Importar curso", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (resultado == JOptionPane.OK_OPTION) {
			String tipoSeleccionado = comboBox.getSelectedItem().toString().toLowerCase(); // "yaml" o "json"
			String extensionEsperada = "." + tipoSeleccionado;

			JFileChooser fileChooser = new JFileChooser();
			int seleccionArchivo = fileChooser.showOpenDialog(null);

			if (seleccionArchivo == JFileChooser.APPROVE_OPTION) {
				File archivo = fileChooser.getSelectedFile();

				if (!archivo.getName().toLowerCase().endsWith(extensionEsperada)) {
					JOptionPane.showMessageDialog(null, "El archivo debe tener extensión " + extensionEsperada,
							"Formato incorrecto", JOptionPane.ERROR_MESSAGE);
			} else {
				String extensionConPunto = "." + tipoSeleccionado;
				CursoPlantilla curso = controladorPlantilla.importarCurso(archivo, extensionConPunto);
				if (curso != null) {
					JOptionPane.showMessageDialog(null, "Curso '" + curso.getNombre() + "' importado correctamente.",
							"Importación exitosa", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Error al importar el curso.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			}
		}
	}
	
	/**
	 * Método para inicializar el temporizador que muestra las vidas del usuario.
	 * Actualiza la etiqueta cada segundo con el número de vidas restantes o el tiempo de recuperación.
	 */
	private void inicializarTemporizador() {
		Timer timer = new Timer(1000, null);
		timer.addActionListener(e -> {
			int vidas = cUsuario.getVidasUsuario();
			if (vidas >= 1) {
				labelTemporizador.setText("" + vidas);
			} else {
				long siguiente = cUsuario.getUsuarioActual().getUltimaRecuperacion().atZone(ZoneId.systemDefault())
						.toInstant().toEpochMilli() + DURACION;

				long restante = siguiente - System.currentTimeMillis();
				if (restante > 0) {
					long m = (restante / 1000) / 60;
					long s = (restante / 1000) % 60;
					labelTemporizador.setText(m + ":" + String.format("%02d", s));
				} else {
					vidas = cUsuario.getVidasUsuario();
					labelTemporizador.setText("" + vidas);
				}
				
			}
		});
		timer.start();
	}

}
