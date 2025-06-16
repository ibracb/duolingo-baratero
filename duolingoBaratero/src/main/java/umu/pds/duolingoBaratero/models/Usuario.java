package umu.pds.duolingoBaratero.models;

import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
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
@Table(name = "usuarios")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario {

	private static final int VIDAS_MAXIMAS = 5;
	private static final int MINUTOS_POR_VIDA = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@JsonIgnore
	@Column(name = "nickname")
	private String nickname;

	@JsonIgnore
	@Column(name = "correo")
	private String correo;

	@JsonIgnore
	@Column(name = "passwd")
	private String passwd;

	@JsonIgnore
	@Column(name = "imagen")
	private String imagen;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private Set<CursoEnProgreso> cursos;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Estadistica estadistica;

	@Column(name = "vidas")
	private int vidas;

	@Column(name = "ultimaRecuperacion")
	private LocalDateTime ultimaRecuperacion;

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
		this.vidas = VIDAS_MAXIMAS;
		this.ultimaRecuperacion = LocalDateTime.now();
	}

	public Usuario(String nombre, String nickname, String correo, String passwd, String imagen) {
		this(nombre, nickname, correo, passwd);
		this.nombre = nombre;
		this.nickname = nickname;
		this.correo = correo;
		this.passwd = passwd;
		this.imagen = imagen;
		this.vidas = VIDAS_MAXIMAS;
		this.ultimaRecuperacion = LocalDateTime.now();
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

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public boolean hasVidas() {
		return vidas > 0;
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

	public LocalDateTime getUltimaRecuperacion() {
		return ultimaRecuperacion;
	}

	public void setUltimaRecuperacion(LocalDateTime ultimaRecuperacion) {
		this.ultimaRecuperacion = ultimaRecuperacion;
	}

	public boolean estaCursando(CursoPlantilla curso) {
		return cursos.stream().anyMatch(cursoProgreso -> cursoProgreso.getCursoPlantilla().equals(curso));
	}

	public int perderVida() {
		if (vidas == VIDAS_MAXIMAS) {
			ultimaRecuperacion = LocalDateTime.now();
		}
		if (vidas > 0) {
			vidas--;
		}

		return vidas;
	}

	public boolean recuperarVidas() {
		LocalDateTime ahora = LocalDateTime.now();

		// Calcular minutos transcurridos desde la última recuperación
		long minutosTranscurridos = ChronoUnit.MINUTES.between(ultimaRecuperacion, ahora);
		int vidasARecuperar = (int) (minutosTranscurridos / MINUTOS_POR_VIDA);

		if (vidasARecuperar > 0) {
			vidas = Math.min(vidas + vidasARecuperar, VIDAS_MAXIMAS);

			// Si no llegó al máximo, ajusta la marca de tiempo solo por las vidas
			if (vidas < VIDAS_MAXIMAS) {
				ultimaRecuperacion = ultimaRecuperacion.plusMinutes(vidasARecuperar * MINUTOS_POR_VIDA);
			} else {
				// Si llegó al máximo, la próxima recuperación será desde ahora
				ultimaRecuperacion = ahora;
			}
		}
		return vidas > 0;
	}
}
