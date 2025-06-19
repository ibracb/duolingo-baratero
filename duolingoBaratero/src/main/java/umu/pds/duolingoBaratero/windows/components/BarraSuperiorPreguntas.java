package umu.pds.duolingoBaratero.windows.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import umu.pds.duolingoBaratero.controllers.ControladorUsuario;

/**
 * Barra superior de la ventana de preguntas.
 * Contiene botones para salir y mostrar información, así como un indicador de vidas del usuario.
 */
public class BarraSuperiorPreguntas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Botón para salir de la ventana de preguntas.
	 */
	private final JButton btnSalir;
	
	/**
	 * Botón para mostrar información sobre el progreso del usuario.
	 */
	private final JButton btnInfo;
	
	/**
	 * Ventana de preguntas a la que pertenece esta barra.
	 */
	private final JFrame ventanaPregunta;
	
	/**
	 * Controlador del usuario, utilizado para obtener el número de vidas del usuario.
	 */
	private final ControladorUsuario cUsuario;
	
	/**
	 * Separación a la derecha de los botones.
	 */
	private Component separacionDer;
	
	/**
	 * Etiqueta que muestra el número de vidas del usuario.
	 */
	private JLabel vidasUsuario;
	
	/**
	 * Separación a la izquierda de los botones.
	 */
	private Component separacionIzq;
	
	/**
	 * Constructor de la barra superior de preguntas.
	 * 
	 * @param ventanaPregunta La ventana de preguntas a la que pertenece esta barra.
	 * @param cUsuario El controlador del usuario, utilizado para obtener el número de vidas del usuario.
	 */
	public BarraSuperiorPreguntas(JFrame ventanaPregunta, ControladorUsuario cUsuario) {
		this.ventanaPregunta = ventanaPregunta;
		this.cUsuario = cUsuario;
		setLayout(new BorderLayout());

		JToolBar barra = new JToolBar();
		barra.setFloatable(false);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

		Dimension botonSize = new Dimension(150, 30);

		btnSalir = new JButton("❌ Salir");
		btnInfo = new JButton("ℹ️ Información");

		btnSalir.setPreferredSize(botonSize);
		btnSalir.addActionListener(e -> salir());

		btnInfo.setPreferredSize(botonSize);
		btnInfo.addActionListener(e -> mostrarInformacion());

		separacionIzq = Box.createHorizontalStrut(40);
		panelBotones.add(separacionIzq);

		panelBotones.add(btnSalir);
		panelBotones.add(btnInfo);

		barra.add(panelBotones);

		separacionDer = Box.createHorizontalStrut(30);
		panelBotones.add(separacionDer);

		vidasUsuario = new JLabel(Integer.toString(getVidas()));
		vidasUsuario.setFont(new Font("Verdana", Font.BOLD, 20));
		vidasUsuario.setIcon(new ImageIcon(getClass().getResource("/corazon.png")));
		panelBotones.add(vidasUsuario);
		add(barra, BorderLayout.NORTH);
	}
	
	/**
	 * Método para salir de la ventana de preguntas.
	 * Muestra un diálogo de confirmación antes de cerrar la ventana.
	 */
	private void salir() {
		Object[] opciones = { "Sí", "No" };
		int opcion = JOptionPane.showOptionDialog(ventanaPregunta,
				"¿Estás seguro de que quieres salir? Perderás todo el progreso de la lección.", "Aviso",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);

		if (opcion == JOptionPane.YES_OPTION) {
			ventanaPregunta.dispose();
		}
	}
	
	/**
	 * Método para mostrar información sobre el progreso del usuario.
	 * Muestra un diálogo informativo con las condiciones para pasar a la siguiente lección.
	 */
	private void mostrarInformacion() {
		JOptionPane.showMessageDialog(ventanaPregunta,
				"Para poder pasar a la siguiente lección hay que aceptar al menos el 80% de las preguntas.",
				"Información", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Método para obtener el número de vidas del usuario.
	 * 
	 * @return El número de vidas del usuario.
	 */
	private int getVidas() {
		return cUsuario.getVidasUsuario();
	}
	
	/**
	 * Actualiza el número de vidas del usuario en la etiqueta correspondiente.
	 */
	public void updateVidas() {
		vidasUsuario.setText(Integer.toString(cUsuario.getVidasUsuario()));
	}
}
