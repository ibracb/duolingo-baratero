package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import umu.pds.duolingoBaratero.models.Flashcard;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.services.IComprobador;

public class PanelFlashcard extends JPanel implements IComprobador {

	private JLabel label;
	private JButton button;
	private Flashcard pregunta;
	private String respuestaUsuario;

	public PanelFlashcard(Flashcard pregunta) {
		this.pregunta = pregunta;
		inicializar();
	}

	private void inicializar() {
		setLayout(new BorderLayout());

		label = new JLabel(pregunta.getPregunta(), SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 20));

		button = new JButton("Ver solución");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarRespuesta();
			}
		});

		add(label, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}

	private void mostrarRespuesta() {
		label.setText(pregunta.getRespuestaCorrecta());

		// Remover botón actual
		remove(button);

		// Crear botones de acierto y fallo
		JButton btnAcierto = new JButton("Acierto");
		JButton btnFallo = new JButton("Fallo");

		// Acción para registrar acierto
		btnAcierto.addActionListener(e -> registrarRespuesta("acierto"));
		btnFallo.addActionListener(e -> registrarRespuesta("fallo"));

		// Crear panel para los botones
		JPanel panelBotones = new JPanel();
		panelBotones.add(btnAcierto);
		panelBotones.add(btnFallo);

		add(panelBotones, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}

	private void registrarRespuesta(String respuestaUsuario) {
		this.respuestaUsuario = respuestaUsuario;
		resetFlashcard();
	}

	private void resetFlashcard() {
		label.setText(pregunta.getPregunta());

		// Restaurar botón inicial
		remove(getComponent(1)); // Quita el panel de botones
		button.setText("Ver solución");
		add(button, BorderLayout.SOUTH);

		revalidate();
		repaint();
	}

	@Override
	public String getRespuestaUsuario() {
		return respuestaUsuario;
	}

	@Override
	public boolean isOpcionElegida() {
		return respuestaUsuario != null;
	}

	@Override
	public Flashcard getPregunta() {
		return pregunta;
	}

}
