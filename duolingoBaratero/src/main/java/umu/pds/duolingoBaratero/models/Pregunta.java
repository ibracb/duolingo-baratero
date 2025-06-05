package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({ @JsonSubTypes.Type(value = PreguntaOpciones.class, name = "OPCIONES"),
		@JsonSubTypes.Type(value = PreguntaAudio.class, name = "AUDIO"),
		@JsonSubTypes.Type(value = Flashcard.class, name = "FLASHCARD") })
public abstract class Pregunta implements Comparable<Pregunta> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "nivel")
	private Nivel nivel;

	@Column(name = "numero")
	private int numero;

	@Column(name = "pregunta")
	private String pregunta;

	@Column(name = "respuesta_correcta")
	private String respuestaCorrecta;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoPregunta tipo;

	public Pregunta() {
	}

	protected Pregunta(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
		this.nivel = nivel;
		this.numero = numero;
		this.pregunta = pregunta;
		this.respuestaCorrecta = respuestaCorrecta;
		this.tipo = tipo;
	}

	public abstract JPanel crearPanel(); // Método abstracto para crear el panel

	public boolean esRespuestaCorrecta(String respuestaUsuario) {
		return respuestaCorrecta.equals(respuestaUsuario);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@JsonIgnore
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
