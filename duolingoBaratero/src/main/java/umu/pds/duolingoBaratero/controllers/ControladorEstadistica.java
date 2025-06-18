package umu.pds.duolingoBaratero.controllers;

import umu.pds.duolingoBaratero.services.ServicioEstadistica;

/**
 * Controlador para gestionar estadísticas del usuario.
 */
public class ControladorEstadistica {

	private ServicioEstadistica servicio;

	/**
	 * Constructor del controlador.
	 * 
	 * @param servicio Servicio que maneja la lógica de estadísticas.
	 */
	public ControladorEstadistica(ServicioEstadistica servicio) {
		super();
		this.servicio = servicio;
	}

	/**
	 * Obtiene el tiempo total de uso.
	 * 
	 * @return tiempo de uso en unidades definidas por el servicio.
	 */
	public double getTiempoUso() {
		return servicio.getTiempoUso();
	}

	/**
	 * Cierra la sesión del usuario y registra los datos de la sesión.
	 */
	public void cerrarSesion() {
		servicio.cerrarSesion();
	}

	/**
	 * Obtiene la racha actual de victorias.
	 * 
	 * @return número de victorias consecutivas.
	 */
	public int getRachaVictorias() {
		return servicio.getRachaVictorias();
	}

	/**
	 * Actualiza la racha de victorias según si la última acción fue aprobada.
	 * 
	 * @param aprobado true si fue una victoria, false si no.
	 */
	public void actualizarRachaVictorias(boolean aprobado) {
		servicio.actualizarRachaVictorias(aprobado);
	}

	/**
	 * Obtiene la racha de accesos consecutivos.
	 * 
	 * @return número de accesos consecutivos.
	 */
	public int getRachaAcceso() {
		return servicio.getRachaAcceso();
	}

	/**
	 * Actualiza el conteo de aciertos según la respuesta dada.
	 * 
	 * @param respuestaCorrecta true si la respuesta fue correcta, false si no.
	 */
	public void actualizarAciertos(boolean respuestaCorrecta) {
		servicio.actualizarAciertos(respuestaCorrecta);
	}

	/**
	 * Obtiene el porcentaje de aciertos en formato String.
	 * 
	 * @return porcentaje de aciertos.
	 */
	public String getPorcentajeAciertos() {
		return servicio.getPorcentajeAciertos();
	}

}
