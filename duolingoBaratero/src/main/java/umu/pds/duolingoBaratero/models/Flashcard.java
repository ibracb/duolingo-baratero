package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import umu.pds.duolingoBaratero.windows.vista.PanelFlashcard;

@Entity
@Table(name = "flashcards")
@DiscriminatorValue("FLASHCARD")
public class Flashcard extends Pregunta {

	private static final String ACIERTO = "acierto";

	public Flashcard() {
		super();
	}

	public Flashcard(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		super(nivel, numero, pregunta, respuestaCorrecta, tipo);
	}

	@Override
	public JPanel crearPanel() {
		return new PanelFlashcard(this);
	}

	@Override
	public boolean esRespuestaCorrecta(String respuestaUsuario) {
		return respuestaUsuario.equals(ACIERTO);
	}

}
