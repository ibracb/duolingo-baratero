package umu.pds.duolingoBaratero.models;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
public class Usuario {

	private static final Rol ROL_POR_DEFECTO = Rol.ESTUDIANTE;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	private String nickname;
	private String correo;
	private String passwd;
	private String imagen;
	private Set<Rol> roles;
	private Set<CursoEnProgreso> cursos;
	private List<CursoPlantilla> cursosCreados;
	private Estadistica estadistica;

	public Usuario(String nombre, String nickname, String correo, String passwd) {
		this.nombre = nombre;
		this.nickname = nickname;
		this.correo = correo;
		this.passwd = passwd;
		this.cursos = new HashSet<>();
		this.cursosCreados = new LinkedList<>();
		roles = new HashSet<>();
		roles.add(ROL_POR_DEFECTO);
		roles.add(Rol.CREADOR);
		this.estadistica = new Estadistica(this);
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

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Estadistica getEstadistica() {
		return estadistica;
	}

	public void setEstadistica(Estadistica estadistica) {
		this.estadistica = estadistica;
	}

	public boolean hasImage() {
		return imagen != null;
	}

	public boolean isAdministrador() {
		return roles.contains(Rol.ADMINISTRADOR);
	}

	public boolean isCreador() {
		return roles.contains(Rol.CREADOR);
	}

	public boolean isEstudiante() {
		return roles.contains(Rol.ESTUDIANTE);
	}

	public double getPorcentajeAcierto() {
		estadistica.setPorcentajeAciertos(80.0);
		return estadistica.getPorcentajeAciertos();
	}

	public double getTiempoUso() {
		estadistica.setTiempoUso(6);
		return estadistica.getTiempoUso();
	}

	public int getRachaVictorias() {
		estadistica.setRachaVictorias(8);
		return estadistica.getRachaVictorias();
	}

	public int getNumMaxAccesos() {
		estadistica.setNumAccesos(4);
		return estadistica.getNumAccesos();
	}
}
