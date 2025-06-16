package umu.pds.duolingoBaratero.windows.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class MensajeTemporal {
	private final static int SEGUNDOS_MOSTRADOS = 2000;

	public static void mostrarMensaje(String mensaje, int tipoMensaje) {
		// Mostrar el JOptionPane
		JOptionPane optionPane = new JOptionPane(mensaje, tipoMensaje);
		JDialog dialog = optionPane.createDialog("Respuesta");
		dialog.setModal(false); // Para que no bloquee la interfaz

		// Crear el Timer para cerrar el JOptionPane después de 2 segundos
		Timer timer = new Timer(SEGUNDOS_MOSTRADOS, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose(); // Cerrar el JOptionPane
			}
		});

		timer.setRepeats(false); // Solo ejecuta una vez
		timer.start(); // Iniciar el Timer
		dialog.setVisible(true);
	}

}
