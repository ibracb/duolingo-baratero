package umu.pds.duolingoBaratero.models;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;

	@JsonIgnore
	private String nickname;

	@JsonIgnore
	private String correo;

	@JsonIgnore
	private String passwd;

	@JsonIgnore
	private String imagen;

	@JsonIgnore
	private Set<CursoEnProgreso> cursos;

	@JsonIgnore
	private List<CursoPlantilla> cursosCreados;   // Esto hay que sacarlod e auqi esta mal deberia ser recuperado dinamicamente
	

	@JsonIgnore
	private Estadistica estadistica;

	public Usuario() {
	}

	public Usuario(String nombre, String nickname, String correo, String passwd) {
		this.nombre = nombre;
		this.nickname = nickname;
		this.correo = correo;
		this.passwd = passwd;
		this.cursos = new HashSet<>();
		this.cursosCreados = new LinkedList<>();
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

	public List<CursoPlantilla> getCursosCreados() {
		return cursosCreados;
	}

	public void setCursosCrados(List<CursoPlantilla> cursos) {
		this.cursosCreados = cursos;
	}

	public void addCursoPlantilla(CursoPlantilla curso) {
		this.cursosCreados.add(curso);
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
		estadistica.setPorcentajeAciertos(80.0);
		return estadistica.getPorcentajeAciertos();
	}

	@JsonIgnore
	public double getTiempoUso() {
		estadistica.setTiempoUso(6);
		return estadistica.getTiempoUso();
	}

	@JsonIgnore
	public int getRachaVictorias() {
		estadistica.setRachaVictorias(8);
		return estadistica.getRachaVictorias();
	}

	@JsonIgnore
	public int getNumMaxAccesos() {
		estadistica.setNumAccesos(4);
		return estadistica.getNumAccesos();
	}

	public boolean estaCursando(CursoPlantilla curso) {
		return cursos.stream().anyMatch(cursoProgreso -> cursoProgreso.getCursoPlantilla().equals(curso));
	}
}
