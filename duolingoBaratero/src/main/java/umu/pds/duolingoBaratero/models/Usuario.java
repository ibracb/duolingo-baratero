package umu.pds.duolingoBaratero.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="nombre")
	private String nombre;

	@JsonIgnore
	@Column(name="nickname")
	private String nickname;

	@JsonIgnore
	@Column(name="correo")
	private String correo;

	@JsonIgnore
	@Column(name="passwd")
	private String passwd;

	@JsonIgnore
	@Column(name="imagen")
	private String imagen;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name="usuario_id")
	private Set<CursoEnProgreso> cursos;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Estadistica estadistica;

	
	public Usuario() {
	}

	public Usuario(String nombre, String nickname, String correo, String passwd) {
		this.nombre = nombre;
		this.nickname = nickname;
		this.correo = correo;
		this.passwd = passwd;
		this.cursos = new HashSet<>();
		this.estadistica = new Estadistica(this);
		this.imagen = "";
	}

	public Usuario(String nombre, String nickname, String correo, String passwd, String imagen) {
		this(nombre, nickname, correo, passwd);
		this.nombre = nombre;
		this.nickname = nickname;
		this.correo = correo;
		this.passwd = passwd;
		this.imagen = imagen;
	}

	public Set<CursoEnProgreso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<CursoEnProgreso> cursos) {
		this.cursos = cursos;
	}

	public boolean addCursoEnProgreso(CursoEnProgreso curso) {
		return cursos.add(curso);
	}

	public void eliminarCurso(CursoEnProgreso curso) {
		cursos.remove(curso);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@JsonIgnore
	public Estadistica getEstadistica() {
		return estadistica;
	}

	@JsonIgnore
	public void setEstadistica(Estadistica estadistica) {
		this.estadistica = estadistica;
	}

	public boolean hasImage() {
		return imagen != null;
	}

	@JsonIgnore
	public void setRachaVictorias(int racha) {
		this.estadistica.setRachaVictorias(racha);
	}

	@JsonIgnore
	public void setTiempoUso(double tiempo) {
		this.estadistica.setTiempoUso(tiempo);
	}

	@JsonIgnore
	public void setPorcentajeAcierto(double porcentaje) {
		this.estadistica.setPorcentajeAciertos(porcentaje);
	}

	@JsonIgnore
	public void setNumMaxAccesos(int accesos) {
		this.estadistica.setNumAccesos(accesos);
	}

	@JsonIgnore
	public double getPorcentajeAcierto() {
		return estadistica.getPorcentajeAciertos();
	}

	@JsonIgnore
	public double getTiempoUso() {
		return estadistica.getTiempoUso();
	}

	@JsonIgnore
	public int getRachaVictorias() {
		return estadistica.getRachaVictorias();
	}

	@JsonIgnore
	public int getNumMaxAccesos() {
		return estadistica.getNumAccesos();
	}

	public boolean estaCursando(CursoPlantilla curso) {
		return cursos.stream().anyMatch(cursoProgreso -> cursoProgreso.getCursoPlantilla().equals(curso));
	}
}
