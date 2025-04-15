package umu.pds.duolingoBaratero.windows.components;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
	private JButton btnHome, btnEstadisticas, btnModoNocturno;
	private JFrame ventanaActual;
	private boolean modoOscuroActivo = false;
	private JButton btnCompartirCurso;

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
		btnCompartirCurso = new JButton("Importar Curso");
		btnCompartirCurso.addActionListener(e -> importarCurso());

		// Agregar botones al panel central
		panelCentral.add(btnHome);
		panelCentral.add(btnEstadisticas);
		panelCentral.add(btnModoNocturno);
		panelCentral.add(btnCompartirCurso);
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
	
    public void importarCurso() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (!archivo.getName().toLowerCase().endsWith(".yaml")) {
                JOptionPane.showMessageDialog(
                    null,
                    "Solo se pueden importar ficheros YAML.",
                    "Formato incorrecto",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                CursoPlantilla curso = ControladorCurso.INSTANCE.importarCurso(archivo);
            }
        }
    }


	private void openVentanaCreaTuCurso() {
		if (ventanaActual instanceof VentanaCreaPregunta) {
			JOptionPane.showMessageDialog(this, "No puedes crear un curso desde esta ventana", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(ventanaActual instanceof VentanaCreaTuCurso)) {
			// Abrimos VentanaCreaTuCurso solo si no estamos ya en ella
			VentanaCreaTuCurso ventana = new VentanaCreaTuCurso();
			ventana.setVisible(true);
			ventanaActual.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Ya estás en la ventana de creación de curso.", "Error",
					JOptionPane.ERROR_MESSAGE);
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

		btnCompartirCurso.setBackground(Color.DARK_GRAY);
		btnCompartirCurso.setForeground(Color.WHITE);
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

		btnCompartirCurso.setBackground(Color.LIGHT_GRAY);
		btnCompartirCurso.setForeground(Color.BLACK);
	}
}
