package umu.pds.duolingoBaratero.windows.components;

import javax.swing.*;

import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.Usuario;

import java.awt.*;

public class BarraSuperiorPreguntas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton btnSalir;
	private final JButton btnInfo;
	private final JFrame ventanaPregunta;
	private final ControladorUsuario cUsuario;
	private Component separacionDer;
	private JLabel vidasUsuario;
	private Component separacionIzq;

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

	private void salir() {
		Object[] opciones = { "Sí", "No" };
		int opcion = JOptionPane.showOptionDialog(ventanaPregunta,
				"¿Estás seguro de que quieres salir? Perderás todo el progreso de la lección.", "Aviso",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);

		if (opcion == JOptionPane.YES_OPTION) {
			ventanaPregunta.dispose();
		}
	}

	private void mostrarInformacion() {
		JOptionPane.showMessageDialog(ventanaPregunta,
				"Para poder pasar a la siguiente lección hay que aceptar al menos el 80% de las preguntas.",
				"Información", JOptionPane.INFORMATION_MESSAGE);
	}

	private int getVidas() {
		return cUsuario.getVidasUsuario();
	}

	public void updateVidas() {
		vidasUsuario.setText(Integer.toString(cUsuario.getVidasUsuario()));
	}
}
