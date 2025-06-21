package umu.pds.duolingoBaratero.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Representa las estadísticas de uso y rendimiento de un usuario en la aplicación.
 * Incluye información como tiempo de uso, número de accesos, racha de victorias,
 * total de aciertos y respuestas, y el último acceso.
 */
@Entity
@Table(name = "estadisticas")
public class Estadistica {

	/**
	 * Valor inicial para las estadísticas, utilizado para inicializar campos como
	 * tiempo de uso, racha de victorias, total de aciertos y respuestas.
	 */
	@Transient
	private static final int VALOR_INICIAL = 0;
	
	/**
	 * Valor inicial para el tiempo de uso, utilizado para inicializar el campo
	 * tiempoUso.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Usuario al que pertenecen estas estadísticas. Se establece una relación uno a
	 * uno con la entidad Usuario.
	 */
	@OneToOne(mappedBy = "estadistica")
	private Usuario usuario;

	/**
	 * Tiempo total de uso de la aplicación en minutos. Se inicializa a 0 y se
	 * actualiza al cerrar sesión.
	 */
	@Column(name = "tiempo_uso")
	private long tiempoUso;

	/**
	 * Número de accesos al sistema. Se incrementa cada vez que el usuario inicia
	 * sesión.
	 */
	@Column(name = "num_accesos")
	private int rachaAcceso;

	/**
	 * Fecha del último acceso del usuario. Se actualiza cada vez que el usuario
	 * inicia sesión.
	 */
	@Column(name = "ultimo_acceso")
	private LocalDate ultimoAcceso;

	/**
	 * Racha de victorias del usuario. Se incrementa cada vez que el usuario gana un
	 * desafío o actividad.
	 */
	@Column(name = "racha_victorias")
	private int rachaVictorias;

	/**
	 * Total de aciertos del usuario en actividades o desafíos. Se incrementa cada
	 * vez que el usuario responde correctamente.
	 */
	@Column(name = "total_aciertos")
	private int totalAciertos;

	/**
	 * Total de respuestas del usuario en actividades o desafíos. Se incrementa cada
	 * vez que el usuario responde, independientemente de si es correcto o no.
	 */
	@Column(name = "total_respuestas")
	private int totalRespuestas;
	
	/**
	 * Marca el inicio de la sesión actual del usuario. Se utiliza para calcular el
	 * tiempo de uso en la sesión actual.
	 */
	@Transient
	private LocalDateTime inicioSesionActual;

	/**
	 * Constructor por defecto requerido por JPA.
	 */
	public Estadistica() {
	}

	/**
	 * Constructor que inicializa las estadísticas para un usuario específico.
	 * 
	 * @param usuario El usuario al que pertenecen estas estadísticas.
	 */
	public Estadistica(Usuario usuario) {
		this.usuario = usuario;
		this.tiempoUso = VALOR_INICIAL;
		this.rachaVictorias = VALOR_INICIAL;
		this.totalAciertos = VALOR_INICIAL;
		this.totalRespuestas = VALOR_INICIAL;
		this.rachaAcceso = VALOR_INICIAL;
	}

	/**
	 * Devuelve el usuario al que pertenecen estas estadísticas.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Estbalece el usuario al que pertenecen estas estadísticas.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Establece el tiempo de uso.
	 */
	public void setTiempoUso(long tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	/**
	 * Devuelve el nº de accesos.
	 */
	public int getNumAccesos() {
		return rachaAcceso;
	}

	/**
	 * Establece el número de accesos.
	 */
	public void setNumAccesos(int numAccesos) {
		this.rachaAcceso = numAccesos;
	}

	/**
	 * Devuelve la racha de victorias.
	 */
	public int getRachaVictorias() {
		return rachaVictorias;
	}

	/**
	 * Establece la racha de victorias.
	 * 
	 * @param rachaVictorias La nueva racha de victorias a establecer.
	 */
	public void setRachaVictorias(int rachaVictorias) {
		this.rachaVictorias = rachaVictorias;
	}

	/**
	 * Devuelve el porcentaje de aciertos.
	 */
	public double getPorcentajeAciertos() {
		if (totalRespuestas > VALOR_INICIAL) {
			double porcentaje = (double) totalAciertos / totalRespuestas * 100;
			return Math.round(porcentaje * 100.0) / 100.0;
		}
		return 0.0;
	}

	/**
	 * Incrementa el número de respuestas y, si es un acierto, el número de aciertos también.
	 */
	public void actualizarAciertos(boolean acierto) {
		if (acierto) {
			totalAciertos++;
		}
		totalRespuestas++;
	}

	/**
	 * Incrementa la racha de victorias del usuario en 1.
	 */
	public void incrementarRachaVictorias() {
		rachaVictorias++;
	}

	/**
	 * Resetea la racha de victorias a su valor inicial.
	 */
	public void resetRachaVictorias() {
		rachaVictorias = VALOR_INICIAL;
	}

	/**
	 * Inicia una sesión para el usuario, marcando el inicio de la sesión
	 */
	public void iniciarSesion() {
		inicioSesionActual = LocalDateTime.now();
		rachaAcceso++;
	}
	
	public void setInicioSesion(LocalDateTime inicio) {
		this.inicioSesionActual = inicio;
	}

	/**
	 * Cierra la sesión actual del usuario y actualiza el tiempo de uso total.
	 */
	public void cerrarSesion() {
		Duration duracion = Duration.between(inicioSesionActual, LocalDateTime.now());
		tiempoUso += duracion.toMinutes();
	}

	/**
	 * Devuelve el tiempo de uso total incluyendo el tiempo actual en sesión. No
	 * modifica el estado interno.
	 */
	public long getTiempoUsoTotalActual() {
		Duration sesionActual = Duration.between(inicioSesionActual, LocalDateTime.now());
		return tiempoUso + sesionActual.toMinutes();
	}
}
