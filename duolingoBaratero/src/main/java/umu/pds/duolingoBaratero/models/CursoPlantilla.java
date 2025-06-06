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
import umu.pds.duolingoBaratero.windows.utility.Constantes;

@Entity
@Table(name = "cursos_plantilla")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CursoPlantilla implements Comparable<CursoPlantilla> {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "propietario")
	private String propietario;

	@Column(name = "descripcion")
	@Lob
	private String descripcion;

	@Column(name = "objetivos")
	@Lob
	private String objetivos;

	@Enumerated(EnumType.STRING)
	@Column(name="nivel")
	private Nivel nivel;

	@JsonProperty("contenidos")
	@OneToMany
	@JoinColumn(name = "curso_id")
	private List<BloqueContenido> contenidos;

	@Column(name = "imagen")
	private String imagen;

	public CursoPlantilla() {

	}

	public CursoPlantilla(String nombre, String propietario, String descripcion, String objetivos) {
		this.nombre = nombre;
		this.propietario = propietario;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.contenidos = new ArrayList<>();
		id = Constantes.getID();
	}

	public CursoPlantilla(String nombre, String propietario, String descripcion, String objetivos, Nivel nivel,
			BloqueContenido... contenidos) {
		this(nombre, propietario, descripcion, objetivos);
		this.nivel = nivel;
		if (contenidos != null) {
			Collections.addAll(this.contenidos, contenidos);
		}
	}

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

	public boolean isCursoFinalizado(int bloqueActual) {
		return bloqueActual == contenidos.size();
	}

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

	public boolean hasImage() {
		return getImagen() != null;
	}

	public Set<TipoPregunta> getTipoPreguntas() {
		HashSet<TipoPregunta> tipos = new HashSet<>();
		if (contenidos == null)
			return tipos;
		for (BloqueContenido bloque : contenidos) {
			if (tipos.containsAll(EnumSet.allOf(TipoPregunta.class))) {
				break;
			}
			tipos.addAll(bloque.getTiposPreguntas());
		}
		return tipos;
	}

	public Set<Pregunta> getPreguntasDeBloque(int bloque) {
		return contenidos.get(bloque).getPreguntas();
	}

	@Override
	public int compareTo(CursoPlantilla o) {
		return this.nivel.compareTo(o.nivel);
	}

}
