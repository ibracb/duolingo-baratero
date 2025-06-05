package umu.pds.duolingoBaratero.windows.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import umu.pds.duolingoBaratero.windows.deported.VentanaCreaPregunta;

public class BarraInferiorPreguntas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnCancelar, btnGuardar;
	private VentanaCreaPregunta ventanaActual;

	public BarraInferiorPreguntas(VentanaCreaPregunta ventanaActual) {
		setLayout(new BorderLayout());
		this.ventanaActual = ventanaActual;

		JToolBar barra = new JToolBar();
		barra.setFloatable(false);
		JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> cancelarPregunta());
		add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(e -> guardarPregunta());
		add(btnGuardar);

		panelCentral.add(btnGuardar);
		panelCentral.add(btnCancelar);

		barra.add(panelCentral);
		add(barra, BorderLayout.SOUTH);
	}

	public void guardarPregunta() {
		ventanaActual.guardarPregunta();
	}

	public void cancelarPregunta() {
		ventanaActual.closeWindow();
	}

}
