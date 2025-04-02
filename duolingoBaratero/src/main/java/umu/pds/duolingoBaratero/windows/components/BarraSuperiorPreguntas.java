package umu.pds.duolingoBaratero.windows.components;

import javax.swing.*;

import umu.pds.duolingoBaratero.windows.vista.VentanaPrincipal;

import java.awt.*;

public class BarraSuperiorPreguntas extends JPanel {
	private JButton btnOpciones;
	private JButton btnModoOscuro;

	public BarraSuperiorPreguntas() {
		setLayout(new BorderLayout());

		JToolBar barra = new JToolBar();
		barra.setFloatable(false);
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// Definir el tamaño fijo
		Dimension botonSize = new Dimension(150, 40);

		btnOpciones = new JButton("⚙ Opciones");
		btnModoOscuro = new JButton("🌙 Modo Oscuro");

		btnOpciones.setPreferredSize(botonSize);
		btnModoOscuro.setPreferredSize(botonSize);

		panelBotones.add(btnOpciones);
		panelBotones.add(btnModoOscuro);

		barra.add(panelBotones); // Agregar el panel centrado a la toolbar
		add(barra, BorderLayout.NORTH);
	}

}
