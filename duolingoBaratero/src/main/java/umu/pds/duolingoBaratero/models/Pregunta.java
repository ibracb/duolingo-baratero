package umu.pds.duolingoBaratero.models;

import javax.swing.JPanel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Clase abstracta que representa una pregunta genérica del curso.
 * Usa herencia para distinguir tipos de pregunta (opciones, imágenes, audio, flashcard).
 */
@Entity
@Table(name = "preguntas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = PreguntaOpciones.class, name = "OPCIONES"),
    @JsonSubTypes.Type(value = PreguntaImagenes.class, name = "IMAGENES"),
    @JsonSubTypes.Type(value = PreguntaAudio.class, name = "AUDIO"),
    @JsonSubTypes.Type(value = Flashcard.class, name = "FLASHCARD")
})
public abstract class Pregunta implements Comparable<Pregunta> {

    /** ID único generado por la base de datos. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /** Bloque de contenido al que pertenece la pregunta. */
    @ManyToOne
    @JoinColumn(name = "bloque_de_contenido_id")
    @JsonIgnore
    private BloqueContenido bloque;

    /** Nivel de dificultad de la pregunta. */
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel")
    private Nivel nivel;

    /** Número de orden de la pregunta dentro del bloque. */
    @Column(name = "numero")
    private int numero;

    /** Enunciado de la pregunta. */
    @Column(name = "pregunta")
    private String pregunta;

    /** Respuesta correcta esperada. */
    @Column(name = "respuesta_correcta")
    private String respuestaCorrecta;

    /** Tipo de pregunta según la subclase. */
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", insertable = false, updatable = false)
    private TipoPregunta tipo;

    /** Constructor vacío requerido por JPA. */
    public Pregunta() {
    }

    /**
     * Constructor protegido para subclases.
     */
    protected Pregunta(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
        this.nivel = nivel;
        this.numero = numero;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.tipo = tipo;
    }

    /**
     * Método abstracto que obliga a las subclases a definir su representación en Swing.
     */
    public abstract JPanel crearPanel();

    /**
     * Verifica si la respuesta del usuario es correcta.
     */
    public boolean esRespuestaCorrecta(String respuestaUsuario) {
        return respuestaCorrecta.equals(respuestaUsuario);
    }

    // Getters y setters
    
    /**
     * 
     * @return ID único de la pregunta.
     */
    public Long getId() {
        return id;
    }

    /**
	 * Establece el ID de la pregunta.
	 * 
	 * @param id ID único a establecer.
	 */
    public void setId(Long id) {
        this.id = id;
    }

    /**
	 * Obtiene el bloque de contenido al que pertenece la pregunta.
	 * 
	 * @return bloque de contenido asociado.
	 */
    public BloqueContenido getBloque() {
        return bloque;
    }

    /**
     * 
     * @param bloque Bloque de contenido al que se asigna la pregunta.
     */
    public void setBloque(BloqueContenido bloque) {
        this.bloque = bloque;
    }

    /**
	 * Obtiene el nivel de dificultad de la pregunta.
	 * 
	 * @return Nivel de dificultad.
	 */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * 
     * @param nivel Nivel de dificultad a establecer para la pregunta.
     */
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    /**
	 * 
	 * @return Número de orden de la pregunta dentro del bloque.
	 */
    public int getNumero() {
        return numero;
    }

    /**
     * 
     * @param numero Número de orden a establecer para la pregunta dentro del bloque.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
	 * 
	 * @return Texto de la pregunta.
	 */
    public String getPregunta() {
        return pregunta;
    }

    /**
	 * 
	 * @param pregunta Texto de la pregunta a establecer.
	 */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
	 * 
	 * @return Respuesta correcta esperada para la pregunta.
	 */
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    /**
	 * 
	 * @param respuestaCorrecta Respuesta correcta a establecer para la pregunta.
	 */
    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    /**
     * 
     * @return Tipo de pregunta (OPCIONES, IMAGENES, AUDIO, FLASHCARD).
     */
    public TipoPregunta getTipo() {
        return tipo;
    }

    /**
	 * Establece el tipo de pregunta.
	 * 
	 * @param tipo Tipo de pregunta a asignar.
	 */
    public void setTipo(TipoPregunta tipo) {
        this.tipo = tipo;
    }

    /**
     * Indica si el tipo de pregunta es de tipo imagen.
     */
    @JsonIgnore
    public boolean isImagen() {
        return tipo.equals(TipoPregunta.IMAGENES);
    }

    /**
     * Ordena preguntas por su número dentro del bloque.
     */
    @Override
    public int compareTo(Pregunta o) {
        return Integer.compare(this.numero, o.numero);
    }
}
