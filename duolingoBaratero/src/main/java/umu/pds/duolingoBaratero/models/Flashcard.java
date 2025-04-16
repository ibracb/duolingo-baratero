package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import umu.pds.duolingoBaratero.windows.vista.PanelFlashcard;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Flashcard extends Pregunta {
	
	private long id;
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
	}

	public Flashcard(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
