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

	@Column(name = "total_aciertos")
	private int totalAciertos;

	@Column(name = "total_respuestas")
	private int totalRespuestas;
	@Transient
	private LocalDateTime inicioSesionActual;

	public Estadistica() {
	}

	public Estadistica(Usuario usuario) {
		this.usuario = usuario;
		this.tiempoUso = VALOR_INICIAL;
		this.rachaVictorias = VALOR_INICIAL;
		this.totalAciertos = VALOR_INICIAL;
		this.totalRespuestas = VALOR_INICIAL;
		this.rachaAcceso = VALOR_INICIAL;
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
		if (totalRespuestas > VALOR_INICIAL) {
			double porcentaje = (double) totalAciertos / totalRespuestas * 100;
			return Math.round(porcentaje * 100.0) / 100.0;
		}
		return 0.0;
	}

	public void actualizarAciertos(boolean acierto) {
		if (acierto) {
			totalAciertos++;
		}
		totalRespuestas++;
	}

	public void incrementarRachaVictorias() {
		rachaVictorias++;
	}

	public void resetRachaVictorias() {
		rachaVictorias = VALOR_INICIAL;
	}

	public void iniciarSesion() {
		inicioSesionActual = LocalDateTime.now();
		rachaAcceso++;
	}
	
	public void setInicioSesion(LocalDateTime inicio) {
		this.inicioSesionActual = inicio;
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
