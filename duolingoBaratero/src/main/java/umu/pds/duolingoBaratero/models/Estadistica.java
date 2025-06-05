package umu.pds.duolingoBaratero.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="estadisticas")
public class Estadistica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(mappedBy="estadistica")
	private Usuario usuario;
	
	@Column(name="tiempo_uso")
	private double tiempoUso;
	
	@Column(name="num_accesos")
	private int numAccesos;
	
	@Column(name="racha_victorias")
	private int rachaVictorias;
	
	@Column(name="porcentaje_aciertos")
	private double porcentajeAciertos;

	public Estadistica(Usuario usuario) {
		this.usuario = usuario;
		this.tiempoUso = 0.0;
		this.numAccesos = 0;
		this.rachaVictorias = 0;
		this.porcentajeAciertos = 0.0;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getTiempoUso() {
		return tiempoUso;
	}

	public void setTiempoUso(double tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public int getNumAccesos() {
		return numAccesos;
	}

	public void setNumAccesos(int numAccesos) {
		this.numAccesos = numAccesos;
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
	
}
