package umu.pds.duolingoBaratero.models;

public class Estadistica {
	
	private Usuario usuario;
	private double tiempoUso;
	private int numAccesos;
	private int rachaVictorias;
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
