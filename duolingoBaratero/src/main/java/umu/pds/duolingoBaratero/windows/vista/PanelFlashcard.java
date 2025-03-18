package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import umu.pds.duolingoBaratero.models.Flashcard;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.services.RespuestaPanel;

public class PanelFlashcard extends JPanel implements RespuestaPanel {

	private JLabel label;
	private JButton button;
	private boolean mostrandoRespuesta = false;
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
	                if (mostrandoRespuesta) {
	                    label.setText(pregunta.getPregunta());
	                    button.setText("Ver solución");
	                } else {
	                    label.setText(pregunta.getRespuestaCorrecta());
	                    button.setText("Ocultar respuesta");
	                }
	                mostrandoRespuesta = !mostrandoRespuesta;
	            }
	        });

	        add(label, BorderLayout.CENTER);
	        add(button, BorderLayout.SOUTH);
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
