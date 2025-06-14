package umu.pds.duolingoBaratero.windows.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.windows.vista.VentanaEstadisticas;
import umu.pds.duolingoBaratero.windows.vista.VentanaPrincipal;

public class BarraSuperior extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnHome, btnEstadisticas, btnImportarCurso, btnExportarCurso;
	private JFrame ventanaActual;
	private final ControladorUsuario cUsuario;
	private final ControladorCursoPlantilla controladorPlantilla;
	private final ControladorCursoProgreso cProgreso;
	private final ControladorPregunta cPregunta;

	public BarraSuperior(JFrame ventanaActual, ControladorUsuario cUsuario, ControladorCursoPlantilla controladorPlantilla, ControladorCursoProgreso cProgreso, 
			ControladorPregunta cPregunta) {
		this.cUsuario = cUsuario;
		this.controladorPlantilla = controladorPlantilla;
		this.cProgreso = cProgreso;
		this.cPregunta = cPregunta;
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

		// Agregar botones al panel central
		panelCentral.add(btnHome);
		panelCentral.add(btnEstadisticas);
		panelCentral.add(btnImportarCurso);
		panelCentral.add(btnExportarCurso);

		// Agregar el panel central dentro de la barra de herramientas
		barra.add(panelCentral);

		// Agregar la barra de herramientas a la parte superior del panel
		add(barra, BorderLayout.NORTH);
	}

	private void openVentanaPrincipal() {

		// Evitar cast incorrecto
		if (!(ventanaActual instanceof VentanaPrincipal)) {
			VentanaPrincipal ventana = new VentanaPrincipal(cUsuario, controladorPlantilla, cProgreso, cPregunta);
			ventana.setVisible(true);
			ventanaActual.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Ya estás en la ventana principal.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void openVentanaEstadisticas() {

		// Evitar cast incorrecto
		if (!(ventanaActual instanceof VentanaEstadisticas)) {
			VentanaEstadisticas ventana = new VentanaEstadisticas(cUsuario, controladorPlantilla, cProgreso, cPregunta);
			ventana.setVisible(true);
			ventanaActual.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Ya estás en la ventana de estadísticas.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void exportarCurso() {
		String[] opciones = { "YAML", "JSON" };
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Selecciona el tipo de archivo:"));
		panel.add(comboBox);

		int resultado = JOptionPane.showConfirmDialog(null, panel, "Importar curso", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (resultado == JOptionPane.OK_OPTION) {
			//String tipoSeleccionado = comboBox.getSelectedItem().toString().toLowerCase(); // "yaml" o "json"
			controladorPlantilla.exportarCurso();
		}

	}

	public void importarCurso() {
		// Crear ComboBox dentro de un JPanel para pasarlo a JOptionPane
		String[] opciones = { "YAML", "JSON" };
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
					//CursoPlantilla curso = ControladorCurso.INSTANCE.importarCurso(archivo, tipoSeleccionado);
					
					// Puedes mostrar algo aquí si quieres confirmar que se importó
				}
			}
		}
	}


}
