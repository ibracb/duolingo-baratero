package umu.pds.duolingoBaratero.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * Representa una plantilla base para la creación de cursos.
 * Contiene información estática como nombre, descripción, bloques de contenido, nivel, etc.
 */
@Entity
@Table(name = "cursos_plantilla")
public class CursoPlantilla implements Comparable<CursoPlantilla> {

    /** Identificador único de la plantilla. */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Nombre del curso. */
    @Column(name = "nombre")
    private String nombre;

    /** Nombre del propietario/autores del curso. */
    @Column(name = "propietario")
    private String propietario;

    /** Descripción del curso. */
    @Column(name = "descripcion")
    @Lob
    private String descripcion;

    /** Objetivos del curso. */
    @Column(name = "objetivos")
    @Lob
    private String objetivos;

    /** Nivel del curso (básico, intermedio, avanzado, etc.). */
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel")
    private Nivel nivel;

    /** Bloques de contenido que componen el curso. */
    @JsonProperty("contenidos")
    @OneToMany
    @JoinColumn(name = "curso_id")
    private List<BloqueContenido> contenidos;

    /** Ruta o identificador de imagen asociada al curso. */
    @Column(name = "imagen")
    private String imagen;

    /** Constructor por defecto requerido por JPA. */
    public CursoPlantilla() {
    }

    /**
     * Constructor básico sin nivel ni bloques.
     */
    public CursoPlantilla(String nombre, String propietario, String descripcion, String objetivos) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.descripcion = descripcion;
        this.objetivos = objetivos;
        this.contenidos = new ArrayList<>();
    }

    /**
     * Constructor completo con nivel y bloques de contenido.
     */
    public CursoPlantilla(String nombre, String propietario, String descripcion, String objetivos, Nivel nivel,
                          BloqueContenido... contenidos) {
        this(nombre, propietario, descripcion, objetivos);
        this.nivel = nivel;
        if (contenidos != null) {
            Collections.addAll(this.contenidos, contenidos);
        }
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BloqueContenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<BloqueContenido> contenidos) {
        this.contenidos = contenidos;
    }

    /**
     * Verifica si un curso ha finalizado comparando el índice del bloque actual
     * con la cantidad de bloques.
     *
     * @param bloqueActual índice del bloque actual.
     * @return true si se ha completado el curso.
     */
    public boolean isCursoFinalizado(int bloqueActual) {
        return bloqueActual == contenidos.size();
    }

    /**
     * (Stub) Añadir una pregunta. No implementado.
     */
    public boolean addPregunta(Pregunta pregunta) {
        return true;
    }

    public void addBloqueContenido(BloqueContenido bloqueContenido) {
        contenidos.add(bloqueContenido);
    }

    public void removeBloqueContenido(BloqueContenido bloqueContenido) {
        contenidos.remove(bloqueContenido);
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /** @return true si la plantilla tiene imagen asignada. */
    public boolean hasImage() {
        return getImagen() != null;
    }

    /**
     * Obtiene los tipos de preguntas existentes en los bloques del curso.
     */
    public Set<TipoPregunta> getTipoPreguntas() {
        HashSet<TipoPregunta> tipos = new HashSet<>();
        if (contenidos == null)
            return tipos;
        for (BloqueContenido bloque : contenidos) {
            if (tipos.containsAll(EnumSet.allOf(TipoPregunta.class))) break;
            tipos.addAll(bloque.getTiposPreguntas());
        }
        return tipos;
    }

    /**
     * Devuelve las preguntas correspondientes a un bloque específico.
     */
    public Set<Pregunta> getPreguntasDeBloque(int bloque) {
        return contenidos.get(bloque).getPreguntas();
    }

    /**
     * Permite ordenar plantillas por nivel.
     */
    @Override
    public int compareTo(CursoPlantilla o) {
        return this.nivel.compareTo(o.nivel);
    }
}
