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
import jakarta.persistence.FetchType;
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
    @OneToMany(fetch = FetchType.EAGER)
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

    /**
	 * Obtiene el nombre del curso.
	 * @return nombre del curso.
	 */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del curso.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devolver el propietario del curso.
     * @return nombre del propietario.
     */
    public String getPropietario() {
        return propietario;
    }

    /**
	 * Establece el propietario del curso.
	 * @param propietario
	 */
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    /**
     * 
     * @return descripción del curso.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
	 * Establece la descripción del curso.
	 * @param descripcion
	 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
	 * Obtiene los objetivos del curso.
	 * @return objetivos del curso.
	 */
    public String getObjetivos() {
        return objetivos;
    }

    /**
     * Establece los objetivos del curso.
     * @param objetivos los objetivos del curso.
     */
    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    /**
	 * Obtiene el nivel del curso.
	 * @return nivel del curso.
	 */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * Establece el nivel del curso.
     * @param nivel el nivel del curso (básico, intermedio, avanzado, etc.).
     */
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    /**
     * 
     * @return identificador único de la plantilla.
     */
    public long getId() {
        return id;
    }

    /**
	 * Establece el identificador único de la plantilla.
	 * @param id identificador único.
	 */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return lista de bloques de contenido del curso.
     */
    public List<BloqueContenido> getContenidos() {
        return contenidos;
    }

    /**
	 * Establece los bloques de contenido del curso.
	 * @param contenidos lista de bloques de contenido.
	 */
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

    /**
     * Añade un bloque de contenido a la plantilla del curso.
     * @param bloqueContenido
     */
    public void addBloqueContenido(BloqueContenido bloqueContenido) {
        contenidos.add(bloqueContenido);
    }

    /**
	 * Elimina un bloque de contenido de la plantilla del curso.
	 * @param bloqueContenido
	 */
    public void removeBloqueContenido(BloqueContenido bloqueContenido) {
        contenidos.remove(bloqueContenido);
    }

    /**
	 * Obtiene la imagen asociada a la plantilla del curso.
	 * @return ruta o identificador de la imagen.
	 */
    public String getImagen() {
        return imagen;
    }

    /**
	 * Establece la imagen asociada a la plantilla del curso.
	 * @param imagen ruta o identificador de la imagen.
	 */
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
