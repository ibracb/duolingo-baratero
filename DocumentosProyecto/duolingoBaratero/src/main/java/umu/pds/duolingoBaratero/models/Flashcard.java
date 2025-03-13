package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.windows.vista.PanelFlashcard;

public class Flashcard extends Pregunta {

	private int tiempoLimite; // Ahora mismo es adaptable a cada pregutna pero si nos complica mucho la vida
								// podriamos hacer fijo para todas

	public Flashcard(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
			int tiempoLimite) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		this.tiempoLimite = tiempoLimite;
	}

	@Override
	public JPanel crearPanel() {
		return new PanelFlashcard(this);
	}

	public int getTiempoLimite() {
		return tiempoLimite;
	}

	public void setTiempoLimite(int tiempoLimite) {
		this.tiempoLimite = tiempoLimite;
	}

}
