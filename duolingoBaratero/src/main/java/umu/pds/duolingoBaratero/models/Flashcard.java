package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.windows.vista.PanelFlashcard;

public class Flashcard extends Pregunta {
	private static final String ACIERTO = "acierto";
	private int tiempoLimite; // Ahora mismo es adaptable a cada pregutna pero si nos complica mucho la vida
								// podriamos hacer fijo para todas

	
	
	public Flashcard(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
			int tiempoLimite) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		this.tiempoLimite = tiempoLimite;
	}

	public Flashcard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flashcard(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel crearPanel() {
		return new PanelFlashcard(this);
	}
	
	@Override
	public boolean esRespuestaCorrecta(String respuestaUsuario) {
		// TODO Auto-generated method stub
		return respuestaUsuario.equals(ACIERTO);
	}

	public int getTiempoLimite() {
		return tiempoLimite;
	}

	public void setTiempoLimite(int tiempoLimite) {
		this.tiempoLimite = tiempoLimite;
	}
	
	

}
