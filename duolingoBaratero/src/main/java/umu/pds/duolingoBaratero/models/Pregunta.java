package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "tipo"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = PreguntaOpciones.class, name = "OPCIONES"),
  @JsonSubTypes.Type(value = PreguntaAudio.class, name = "AUDIO"),
  @JsonSubTypes.Type(value = Flashcard.class, name = "FLASHCARD")
})

public abstract class Pregunta implements Comparable<Pregunta> {

	private Nivel nivel;
	private int numero;
	private String pregunta;
	private String respuestaCorrecta;
	private TipoPregunta tipo;

	public Pregunta() {
	}
	
	public Pregunta(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		this.nivel = nivel;
		this.numero = numero;
		this.pregunta = pregunta;
		this.respuestaCorrecta = respuestaCorrecta;
		this.tipo = tipo;
	}
	
	public abstract JPanel crearPanel();  // Método abstracto para crear el panel
	
	public boolean esRespuestaCorrecta(String respuestaUsuario) {
		return respuestaCorrecta.equals(respuestaUsuario);
	}
	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public TipoPregunta getTipo() {
		return tipo;
	}
	
	public boolean isImagen() {
		return tipo.equals(TipoPregunta.IMAGEN);
	}
	
	public void setTipo(TipoPregunta tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Pregunta o) {
		return Integer.compare(this.numero, o.numero);
	}

}
