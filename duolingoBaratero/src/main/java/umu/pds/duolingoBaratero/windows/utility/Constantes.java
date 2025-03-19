package umu.pds.duolingoBaratero.windows.utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Constantes {
	public static final int MAX_SALTOS = 1;
	public static final int PREGUNTAS_POR_BLOQUE = 10;

	public static void mostrarMensaje(String mensaje, int tipoMensaje) {
		// Mostrar el JOptionPane
		JOptionPane optionPane = new JOptionPane(mensaje, tipoMensaje);
		JDialog dialog = optionPane.createDialog("Respuesta");
		dialog.setModal(false); // Para que no bloquee la interfaz

		// Crear el Timer para cerrar el JOptionPane después de 2 segundos
		Timer timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose(); // Cerrar el JOptionPane
			}
		});

		timer.setRepeats(false); // Solo ejecuta una vez
		timer.start(); // Iniciar el Timer
		dialog.setVisible(true);
	}
}
