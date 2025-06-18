package umu.pds.duolingoBaratero.models;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;

/**
 * Representa un curso en progreso asociado a un usuario.
 * Controla el estado, el bloque actual y el tipo de aprendizaje seleccionado.
 */
@Entity
@Table(name = "cursos_en_progreso")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CursoEnProgreso {

    /** Índice inicial del bloque de contenido. */
    @Transient
    private final int BLOQUE_CONTENIDO_INICIAL = 0;

    /** Identificador único del curso en progreso. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Curso base del cual se genera este progreso. */
    @ManyToOne
    @JoinColumn(name = "curso_plantilla", nullable = false)
    private CursoPlantilla cursoPlantilla;

    /** Tipo de aprendizaje seleccionado. */
    @Enumerated(EnumType.STRING)
    @Column(name = "aprendizaje")
    private AprendizajeSeleccionado aprendizaje;

    /** Estado actual del curso en progreso. */
    @Column(name = "estado")
    private EstadoCursoEnProgreso estado;

    /** Índice del bloque actual en el que se encuentra el usuario. */
    @Column(name = "bloque_actual")
    private int bloqueActual;

    /** Usuario que está realizando el curso. */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /** Constructor por defecto requerido por JPA. */
    public CursoEnProgreso() {
    }

    /**
     * Constructor que inicializa un curso en progreso desde una plantilla y un usuario.
     *
     * @param cursoPlantilla curso base.
     * @param usuario        usuario que realiza el curso.
     */
    public CursoEnProgreso(CursoPlantilla cursoPlantilla, Usuario usuario) {
        this.cursoPlantilla = cursoPlantilla;
        this.usuario = usuario;
        this.estado = EstadoCursoEnProgreso.NUEVO;
        bloqueActual = BLOQUE_CONTENIDO_INICIAL;
    }

    /** @return nombre del curso. */
    public String getNombre() {
        return cursoPlantilla.getNombre();
    }

    /** @return descripción del curso. */
    public String getDescripcion() {
        return cursoPlantilla.getDescripcion();
    }

    /** @return objetivos del curso. */
    public String getObjetivos() {
        return cursoPlantilla.getObjetivos();
    }

    /** @return nivel del curso. */
    public Nivel getNivel() {
        return cursoPlantilla.getNivel();
    }

    /** @return plantilla del curso. */
    public CursoPlantilla getCursoPlantilla() {
        return cursoPlantilla;
    }

    /** @param cursoPlantilla nueva plantilla del curso. */
    public void setCursoPlantilla(CursoPlantilla cursoPlantilla) {
        this.cursoPlantilla = cursoPlantilla;
    }

    /** @return tipo de aprendizaje. */
    public AprendizajeSeleccionado getAprendizaje() {
        return aprendizaje;
    }

    /** @param aprendizaje tipo de aprendizaje a asignar. */
    public void setAprendizaje(AprendizajeSeleccionado aprendizaje) {
        this.aprendizaje = aprendizaje;
    }

    /** @return índice del bloque actual. */
    public int getBloqueActual() {
        return bloqueActual;
    }

    /** @param bloqueActual nuevo índice de bloque actual. */
    public void setBloqueActual(int bloqueActual) {
        this.bloqueActual = bloqueActual;
    }

    /**
     * Avanza al siguiente bloque si fue aprobado. Finaliza el curso si es el último.
     *
     * @param aprobado indica si se aprobó el bloque actual.
     */
    public void avanzarBloqueActual(boolean aprobado) {
        if (aprobado) {
            bloqueActual++;
            if (cursoPlantilla.isCursoFinalizado(bloqueActual)) {
                this.finalizar();
            }
        }
    }

    /**
     * Devuelve las preguntas del bloque especificado.
     *
     * @param bloqueContenidoProgreso índice del bloque.
     * @return conjunto de preguntas del bloque.
     */
    public Set<Pregunta> getPreguntasBloqueContenido(int bloqueContenidoProgreso) {
        return cursoPlantilla.getPreguntasDeBloque(bloqueContenidoProgreso);
    }

    /**
     * Devuelve las preguntas del bloque actual.
     *
     * @return conjunto de preguntas del bloque actual.
     */
    public Set<Pregunta> getPreguntasBloqueContenido() {
        return cursoPlantilla.getPreguntasDeBloque(bloqueActual);
    }

    /** @return ID del curso en progreso. */
    public long getId() {
        return id;
    }

    /** @param id nuevo ID del curso. */
    public void setId(long id) {
        this.id = id;
    }

    /** @return estado actual del curso. */
    public EstadoCursoEnProgreso getEstado() {
        return estado;
    }

    /** @param estado nuevo estado del curso. */
    public void setEstado(EstadoCursoEnProgreso estado) {
        this.estado = estado;
    }

    /**
     * Reinicia el curso si ya fue finalizado.
     */
    public void reiniciar() {
        if (estado == EstadoCursoEnProgreso.FINALIZADO) {
            estado = EstadoCursoEnProgreso.NUEVO;
        } else {
            throw new IllegalStateException("No se puede reiniciar desde el estado: " + estado);
        }
    }

    /**
     * Inicia el curso si está en estado NUEVO.
     */
    public void iniciar() {
        if (estado == EstadoCursoEnProgreso.NUEVO) {
            estado = EstadoCursoEnProgreso.EN_MARCHA;
        } else {
            throw new IllegalStateException("No se puede iniciar desde el estado: " + estado);
        }
    }

    /**
     * Finaliza el curso si está en estado EN_MARCHA.
     */
    public void finalizar() {
        if (estado == EstadoCursoEnProgreso.EN_MARCHA) {
            estado = EstadoCursoEnProgreso.FINALIZADO;
            bloqueActual = BLOQUE_CONTENIDO_INICIAL;
        } else {
            throw new IllegalStateException("No se puede finalizar desde el estado: " + estado);
        }
    }

    /** @return true si el curso está en estado NUEVO. */
    public boolean isNuevo() {
        return estado.equals(EstadoCursoEnProgreso.NUEVO);
    }

    /** @return true si el curso está FINALIZADO. */
    public boolean isFinalizado() {
        return estado.equals(EstadoCursoEnProgreso.FINALIZADO);
    }

    /** @return true si el curso está EN_MARCHA. */
    public boolean isEnMarcha() {
        return estado.equals(EstadoCursoEnProgreso.EN_MARCHA);
    }

    /** Equals y hashCode basados en ID y cursoPlantilla. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoEnProgreso that = (CursoEnProgreso) o;
        return Objects.equals(id, that.id) && Objects.equals(cursoPlantilla, that.cursoPlantilla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cursoPlantilla);
    }
}
