package umu.pds.duolingoBaratero.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "estadisticas")
public class Estadistica {

	@Transient
	private static final int VALOR_INICIAL = 0;
	@Transient
	private static final int RACHA_ACCESOS_INICIAL = 1;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(mappedBy = "estadistica")
	private Usuario usuario;

	@Column(name = "tiempo_uso")
	private long tiempoUso;

	@Column(name = "num_accesos")
	private int rachaAcceso;

	@Column(name = "ultimo_acceso")
	private LocalDate ultimoAcceso;

	@Column(name = "racha_victorias")
	private int rachaVictorias;

	@Column(name = "porcentaje_aciertos")
	private double porcentajeAciertos;

	@Transient
	private LocalDateTime inicioSesionActual;

	public Estadistica() {
	}

	public Estadistica(Usuario usuario) {
		this.usuario = usuario;
		this.tiempoUso = VALOR_INICIAL;
		this.rachaVictorias = VALOR_INICIAL;
		this.porcentajeAciertos = 0.0;
		this.iniciarSesion();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setTiempoUso(long tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public int getNumAccesos() {
		return rachaAcceso;
	}

	public void setNumAccesos(int numAccesos) {
		this.rachaAcceso = numAccesos;
	}

	public int getRachaVictorias() {
		return rachaVictorias;
	}

	public void setRachaVictorias(int rachaVictorias) {
		this.rachaVictorias = rachaVictorias;
	}

	public double getPorcentajeAciertos() {
		return porcentajeAciertos;
	}

	public void setPorcentajeAciertos(double porcentajeAciertos) {
		this.porcentajeAciertos = porcentajeAciertos;
	}

	public void incrementarRachaVictorias() {
		rachaVictorias++;
	}

	public void resetRachaVictorias() {
		rachaVictorias = VALOR_INICIAL;
	}

//	public void actualizarRachaAcceso() {
//		LocalDate hoy = LocalDate.now();
//		if (ultimoAcceso == null) {
//			rachaAcceso = RACHA_ACCESOS_INICIAL;
//		} else {
//			long diasDiferencia = ChronoUnit.DAYS.between(ultimoAcceso, hoy);
//			if (diasDiferencia == 1) {
//				// día siguiente, incrementa racha
//				rachaAcceso++;
//			} else if (diasDiferencia > 1) {
//				// más de un día sin entrar, resetea racha
//				rachaAcceso = 1;
//			}
//		}
//		ultimoAcceso = hoy;
//	}

	public void iniciarSesion() {
		inicioSesionActual = LocalDateTime.now();
		rachaAcceso++;
	}

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
