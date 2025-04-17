package umu.pds.duolingoBaratero.windows.components;

import java.awt.Color;
import java.awt.BorderLayout;
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

import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.windows.deported.VentanaCreaPregunta;
import umu.pds.duolingoBaratero.windows.deported.VentanaCreaTuCurso;
import umu.pds.duolingoBaratero.windows.vista.VentanaEstadisticas;
import umu.pds.duolingoBaratero.windows.vista.VentanaPrincipal;

public class BarraSuperior extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnHome, btnEstadisticas, btnModoNocturno, btnImportarCurso, btnExportarCurso;
	private JFrame ventanaActual;
	private boolean modoOscuroActivo = false;

	public BarraSuperior(JFrame ventanaActual) {
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
		btnModoNocturno = new JButton("Modo Nocturno 🌙");
		btnModoNocturno.addActionListener(e -> toggleModoOscuro()); // Activar/desactivar modo nocturno
		btnImportarCurso = new JButton("Importar Curso");
		btnImportarCurso.addActionListener(e -> importarCurso());
		btnExportarCurso = new JButton("Exportar Curso");
		btnExportarCurso.addActionListener(e -> exportarCurso());

		// Agregar botones al panel central
		panelCentral.add(btnHome);
		panelCentral.add(btnEstadisticas);
		panelCentral.add(btnModoNocturno);
		panelCentral.add(btnImportarCurso);
		panelCentral.add(btnExportarCurso);

		// Agregar el panel central dentro de la barra de herramientas
		barra.add(panelCentral);

		// Agregar la barra de herramientas a la parte superior del panel
		add(barra, BorderLayout.NORTH);
	}

	private void openVentanaPrincipal() {
		if (ventanaActual instanceof VentanaCreaPregunta) {
			int respuesta = JOptionPane.showConfirmDialog(null,
					"Si continúas, perderás todo sobre el curso. ¿Deseas continuar?", "Advertencia",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			if (respuesta != JOptionPane.OK_OPTION) {
				return; // Si el usuario cancela, no se abre la nueva ventana
			}
		}

		// Evitar cast incorrecto
		if (!(ventanaActual instanceof VentanaPrincipal)) {
			VentanaPrincipal ventana = new VentanaPrincipal();
			ventana.setVisible(true);
			ventanaActual.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Ya estás en la ventana principal.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void openVentanaEstadisticas() {
		if (ventanaActual instanceof VentanaCreaPregunta) {
			int respuesta = JOptionPane.showConfirmDialog(null,
					"Si continúas, perderás la información sobre el curso. ¿Deseas continuar?", "Advertencia",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta != JOptionPane.OK_OPTION) {
				return; // Si el usuario cancela, no se abre la nueva ventana
			}
		}

		// Evitar cast incorrecto
		if (!(ventanaActual instanceof VentanaEstadisticas)) {
			VentanaEstadisticas ventana = new VentanaEstadisticas();
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
			String tipoSeleccionado = comboBox.getSelectedItem().toString().toLowerCase(); // "yaml" o "json"
			ControladorCurso.INSTANCE.exportarCurso();
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
					CursoPlantilla curso = ControladorCurso.INSTANCE.importarCurso(archivo, tipoSeleccionado);
					
					// Puedes mostrar algo aquí si quieres confirmar que se importó
				}
			}
		}
	}

	private void toggleModoOscuro() {
		modoOscuroActivo = !modoOscuroActivo;
		// Aplicar los colores de modo oscuro o modo claro
		if (modoOscuroActivo) {
			setModoOscuro();
		} else {
			setModoClaro();
		}
	}

	private void setModoOscuro() {
		// Cambiar el fondo y los colores del texto a colores oscuros
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);

		btnHome.setBackground(Color.DARK_GRAY);
		btnHome.setForeground(Color.WHITE);

		btnEstadisticas.setBackground(Color.DARK_GRAY);
		btnEstadisticas.setForeground(Color.WHITE);

		btnModoNocturno.setBackground(Color.DARK_GRAY);
		btnModoNocturno.setForeground(Color.WHITE);

		btnImportarCurso.setBackground(Color.DARK_GRAY);
		btnImportarCurso.setForeground(Color.WHITE);
	}

	private void setModoClaro() {
		// Cambiar el fondo y los colores del texto a colores claros
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);

		btnHome.setBackground(Color.LIGHT_GRAY);
		btnHome.setForeground(Color.BLACK);

		btnEstadisticas.setBackground(Color.LIGHT_GRAY);
		btnEstadisticas.setForeground(Color.BLACK);

		btnModoNocturno.setBackground(Color.LIGHT_GRAY);
		btnModoNocturno.setForeground(Color.BLACK);

		btnImportarCurso.setBackground(Color.LIGHT_GRAY);
		btnImportarCurso.setForeground(Color.BLACK);
	}
}
