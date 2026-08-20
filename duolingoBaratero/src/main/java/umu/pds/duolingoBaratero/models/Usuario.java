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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Representa un usuario del sistema con atributos básicos, manejo de vidas para
 * el juego y relación con cursos en progreso y estadísticas.
 */
@Entity
@Table(name = "usuarios")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario {

	/**
	 * Número máximo de vidas que un usuario puede tener.
	 */
	private static final int VIDAS_MAXIMAS = 5;
	
	/**
	 * Número de minutos necesarios para recuperar una vida.
	 */
	private static final int MINUTOS_POR_VIDA = 5;

	/**
	 * Identificador único del usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Nombre del usuario.
	 */
	@Column(name = "nombre")
	private String nombre;

	/**
	 * Apodo o nickname del usuario.
	 */
	@JsonIgnore
	@Column(name = "nickname")
	private String nickname;

	/**
	 * Correo electrónico del usuario.
	 */
	@JsonIgnore
	@Column(name = "correo")
	private String correo;

	/**
	 * Contraseña del usuario, almacenada de forma segura.
	 */
	@JsonIgnore
	@Column(name = "passwd")
	private String passwd;

	/**
	 * Ruta o identificador de imagen asociada al usuario.
	 */
	@JsonIgnore
	@Column(name = "imagen")
	private String imagen;

	/**
	 * Cursos en progreso del usuario, representados por un conjunto de
	 * CursoEnProgreso.
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Set<CursoEnProgreso> cursos;

	/**
	 * Estadísticas del usuario, como tiempo de uso, rachas, etc.
	 */
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Estadistica estadistica;

	/**
	 * Número de vidas disponibles para el usuario.
	 */
	@Column(name = "vidas")
	private int vidas;

	/**
	 * Fecha y hora de la última recuperación de vidas.
	 */
	@Column(name = "ultimaRecuperacion")
	private LocalDateTime ultimaRecuperacion;

	/**
	 * Constructor por defecto requerido por JPA.
	 */
	public Usuario() {
	}

	/**
	 * Constructor básico para usuario con datos esenciales. Inicializa con vidas
	 * máximas y fecha actual para última recuperación.
	 */
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

	/**
	 * Constructor con imagen opcional.
	 */
	public Usuario(String nombre, String nickname, String correo, String passwd, String imagen) {
		this(nombre, nickname, correo, passwd);
		this.imagen = imagen;
	}

	/**
	 * Constructor con todos los atributos del usuario.
	 */
	public Set<CursoEnProgreso> getCursos() {
		return cursos;
	}

	/**
	 * Establece los cursos en progreso del usuario.
	 * 
	 * @param cursos Conjunto de cursos en progreso
	 */
	public void setCursos(Set<CursoEnProgreso> cursos) {
		this.cursos = cursos;
	}

	/**
	 * Añade un curso en progreso al usuario.
	 * 
	 * @param curso Curso a añadir
	 * @return true si se añadió correctamente
	 */
	public boolean addCursoEnProgreso(CursoEnProgreso curso) {
		return cursos.add(curso);
	}

	/**
	 * Elimina un curso en progreso del usuario.
	 */
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

	/**
	 * Indica si el usuario tiene vidas disponibles.
	 */
	public boolean hasVidas() {
		return vidas > 0;
	}

	/**
	 * Indica si el usuario tiene imagen asignada.
	 */
	public boolean hasImage() {
		return imagen != null && !imagen.isEmpty();
	}

	public LocalDateTime getUltimaRecuperacion() {
		return ultimaRecuperacion;
	}

	public void setUltimaRecuperacion(LocalDateTime ultimaRecuperacion) {
		this.ultimaRecuperacion = ultimaRecuperacion;
	}

	/**
	 * Comprueba si el usuario está cursando el curso dado.
	 */
	public boolean estaCursando(CursoPlantilla curso) {
		return cursos.stream().anyMatch(c -> c.getCursoPlantilla().equals(curso));
	}

	/**
	 * Reduce una vida del usuario y actualiza la última recuperación si es
	 * necesario.
	 * 
	 * @return número de vidas restantes
	 */
	public int perderVida() {
		if (vidas == VIDAS_MAXIMAS) {
			ultimaRecuperacion = LocalDateTime.now();
		}
		if (vidas > 0) {
			vidas--;
		}
		return vidas;
	}

	/**
	 * Recupera vidas según el tiempo transcurrido desde la última recuperación.
	 * Recupera 1 vida por cada MINUTOS_POR_VIDA minutos. Actualiza la ultimarecuperacin.
	 * 
	 * @return true si el usuario tiene alguna vida tras la recuperación
	 */
	public boolean recuperarVidas() {
		LocalDateTime ahora = LocalDateTime.now();
		long minutosTranscurridos = ChronoUnit.MINUTES.between(ultimaRecuperacion, ahora);
		int vidasARecuperar = (int) (minutosTranscurridos / MINUTOS_POR_VIDA);

		if (vidasARecuperar > 0) {
			vidas = Math.min(vidas + vidasARecuperar, VIDAS_MAXIMAS);

			if (vidas < VIDAS_MAXIMAS) {
				ultimaRecuperacion = ultimaRecuperacion.plusMinutes(vidasARecuperar * MINUTOS_POR_VIDA);
			} else {
				ultimaRecuperacion = ahora;
			}
		}
		return vidas > 0;
	}
}
