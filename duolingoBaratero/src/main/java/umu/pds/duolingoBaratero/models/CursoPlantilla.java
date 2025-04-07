package umu.pds.duolingoBaratero.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import umu.pds.duolingoBaratero.windows.utility.Constantes;

@Entity
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
public class CursoPlantilla implements Comparable<CursoPlantilla> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	@JsonProperty("propietario")
	private Usuario propietario;
	
	private String descripcion;
	private String objetivos;
	private Nivel nivel;
	
	@JsonProperty("contenidos")
	private List<BloqueContenido> contenidos;
	
	private String imagen;

	public long getId() {
		return id;
	}

	public CursoPlantilla(String nombre, Usuario propietario, String descripcion, String objetivos) {
		this.nombre = nombre;
		this.propietario = propietario;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.contenidos = new ArrayList<>();
		id = Constantes.getID();
	}

	public CursoPlantilla(String nombre, Usuario propietario, String descripcion, String objetivos, Nivel nivel,
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

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
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

	public void setId(long id) {
		this.id = id;
	}

	public List<BloqueContenido> getContenidos() {
		return Collections.unmodifiableList(contenidos);
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
		for (BloqueContenido bloque : contenidos) {
			if (tipos.containsAll(EnumSet.allOf(TipoPregunta.class))) {
				break;
			}
			tipos.addAll(bloque.getTiposPreguntas());
		}
		return tipos;
	}

	public List<Pregunta> getPreguntasDeBloque(int bloque) {
		return contenidos.get(bloque).getPreguntas();
	}

	@Override
	public int compareTo(CursoPlantilla o) {
		return this.nivel.compareTo(o.nivel);
	}
	
	

}
