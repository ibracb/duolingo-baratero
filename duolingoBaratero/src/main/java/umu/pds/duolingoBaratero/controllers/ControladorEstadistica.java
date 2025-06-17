package umu.pds.duolingoBaratero.controllers;

import umu.pds.duolingoBaratero.services.ServicioEstadistica;

public class ControladorEstadistica {

	private ServicioEstadistica servicio;

	public ControladorEstadistica(ServicioEstadistica servicio) {
		super();
		this.servicio = servicio;
	}

	public double getTiempoUso() {
		return servicio.getTiempoUso();
	}

	public void cerrarSesion() {
		servicio.cerrarSesion();
	}

	public int getRachaVictorias() {
		return servicio.getRachaVictorias();
	}

	public void actualizarRachaVictorias(boolean aprobado) {
		servicio.actualizarRachaVictorias(aprobado);
	}

	public int getRachaAcceso() {
		return servicio.getRachaAcceso();
	}

	public void actualizarAciertos(boolean respuestaCorrecta) {
		servicio.actualizarAciertos(respuestaCorrecta);
	}

	public String getPorcentajeAciertos() {
		return servicio.getPorcentajeAciertos();
	}

}
