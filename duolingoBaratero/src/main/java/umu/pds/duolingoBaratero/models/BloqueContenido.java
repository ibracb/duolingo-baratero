package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.models.aprendizajes.FactoriaAprendizaje;

/**
 * Representa un bloque de contenido dentro de un curso de plantilla.
 * Contiene un conjunto de preguntas y métodos para manipularlas y obtenerlas
 * en diferentes formas según el tipo de aprendizaje.
 */
@Entity
@Table(name = "bloques_contenido")
public class BloqueContenido {

    /**
     * Identificador único del bloque de contenido.
     */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Curso al que pertenece este bloque de contenido.
     */
    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnore
    private CursoPlantilla curso;

    /**
     * Conjunto de preguntas asociadas a este bloque de contenido.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "bloque_de_contenido_id")
    private Set<Pregunta> preguntas;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public BloqueContenido() {
    }

    /**
     * Constructor que inicializa el bloque con un ID y una lista de preguntas.
     *
     * @param id        Identificador del bloque.
     * @param preguntas Preguntas que se agregarán al bloque.
     */
    public BloqueContenido(long id, Pregunta... preguntas) {
        this.preguntas = new HashSet<>();
        Collections.addAll(this.preguntas, preguntas);
        setNumPreguntas();
    }

    /** @return el ID del bloque. */
    public long getId() {
        return id;
    }

    /** @param id nuevo valor para el ID del bloque. */
    public void setId(long id) {
        this.id = id;
    }

    /** @return el curso al que pertenece este bloque. */
    public CursoPlantilla getCurso() {
        return curso;
    }

    /** @param curso curso al que pertenece este bloque. */
    public void setCurso(CursoPlantilla curso) {
        this.curso = curso;
    }

    /** @return conjunto de preguntas del bloque. */
    public Set<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * Devuelve las preguntas según el tipo de aprendizaje dado.
     * (Actualmente retorna todas sin aplicar lógica de filtrado).
     *
     * @param aprendizaje tipo de aprendizaje seleccionado.
     * @return conjunto de preguntas.
     */
    public Set<Pregunta> getPreguntas(AprendizajeSeleccionado aprendizaje) {
        return preguntas;
    }

    /** @param preguntas conjunto de preguntas a asignar al bloque. */
    public void setPreguntas(Set<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * Devuelve las preguntas en orden secuencial.
     *
     * @return conjunto de preguntas ordenadas secuencialmente.
     */
    public Set<Pregunta> getPreguntasSecuencialmente() {
        return FactoriaAprendizaje.INSTANCE.getAprendizaje(AprendizajeSeleccionado.SECUENCIAL)
                .seleccionarPreguntas(preguntas);
    }

    /**
     * Devuelve las preguntas en orden invertido.
     *
     * @return conjunto de preguntas ordenadas en reversa.
     */
    public Set<Pregunta> getPreguntasInvertidas() {
        return FactoriaAprendizaje.INSTANCE.getAprendizaje(AprendizajeSeleccionado.INVERTIDO)
                .seleccionarPreguntas(preguntas);
    }

    /**
     * Devuelve las preguntas en orden aleatorio.
     *
     * @return conjunto de preguntas aleatorias.
     */
    public Set<Pregunta> getPreguntasAleatoriamente() {
        return FactoriaAprendizaje.INSTANCE.getAprendizaje(AprendizajeSeleccionado.ALEATORIO)
                .seleccionarPreguntas(preguntas);
    }

    /**
     * Agrega una pregunta al conjunto de preguntas del bloque.
     *
     * @param pregunta pregunta a agregar.
     */
    public void addPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    /**
     * Elimina una pregunta del conjunto de preguntas del bloque.
     *
     * @param pregunta pregunta a eliminar.
     */
    public void removePregunta(Pregunta pregunta) {
        preguntas.remove(pregunta);
    }

    /**
     * Devuelve el conjunto de tipos de preguntas presentes en el bloque.
     *
     * @return conjunto de tipos de preguntas.
     */
    public Set<TipoPregunta> getTiposPreguntas() {
        return preguntas.stream().map(Pregunta::getTipo).collect(Collectors.toSet());
    }

    /**
     * Devuelve el número total de preguntas en el bloque.
     *
     * @return cantidad de preguntas.
     */
    @JsonIgnore
    public int getNumPreguntas() {
        return preguntas.size();
    }

    /**
     * Asigna un número secuencial a cada pregunta del bloque.
     */
    public void setNumPreguntas() {
        int i = 0;
        for (Pregunta p : preguntas) {
            p.setNumero(i++);
        }
    }
}
