package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.models.Estadistica;
import umu.pds.duolingoBaratero.persistence.DBEstadisticaDAO;

public class ServicioEstadistica {

	private Estadistica estadistica;
	private DBEstadisticaDAO dbEstadisticaDAO;

	public ServicioEstadistica(DBEstadisticaDAO dbEstadisticaDAO) {
		this.dbEstadisticaDAO = dbEstadisticaDAO;
	}

	public void inicializarEstadistica(Estadistica estadistica) {
		this.estadistica = estadistica;
		estadistica.iniciarSesion();
	}
	public double getTiempoUso() {
		return estadistica.getTiempoUsoTotalActual();
	}

	public int getRachaVictorias() {
		return estadistica.getRachaVictorias();
	}

	public void actualizarRachaVictorias(boolean aprobado) {
		if (aprobado)
			estadistica.incrementarRachaVictorias();
		estadistica.resetRachaVictorias();
	}

	public int getRachaAcceso() {
		return estadistica.getNumAccesos();
	}
	
	
	public void cerrarSesion() {
		estadistica.cerrarSesion();
		dbEstadisticaDAO.update(estadistica);
	}

	public void actualizarAciertos(boolean respuestaCorrecta) {
		estadistica.actualizarAciertos(respuestaCorrecta);
	}

	public String getPorcentajeAciertos() {
		return String.valueOf(estadistica.getPorcentajeAciertos());
	}

}
