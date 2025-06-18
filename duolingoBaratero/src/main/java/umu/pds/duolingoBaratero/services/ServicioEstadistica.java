package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.models.Estadistica;
import umu.pds.duolingoBaratero.persistence.DBEstadisticaDAO;

/**
 * Servicio para gestionar la lógica de estadísticas de uso y rendimiento.
 */
public class ServicioEstadistica {

	private Estadistica estadistica;
	private DBEstadisticaDAO dbEstadisticaDAO;

	/**
	 * Constructor del servicio.
	 * 
	 * @param dbEstadisticaDAO DAO para persistencia de estadísticas.
	 */
	public ServicioEstadistica(DBEstadisticaDAO dbEstadisticaDAO) {
		this.dbEstadisticaDAO = dbEstadisticaDAO;
	}

	/**
	 * Inicializa la estadística para una sesión activa.
	 * 
	 * @param estadistica Estadística a inicializar.
	 */
	public void inicializarEstadistica(Estadistica estadistica) {
		this.estadistica = estadistica;
		estadistica.iniciarSesion();
	}

	/**
	 * Obtiene el tiempo total de uso actual.
	 * 
	 * @return Tiempo de uso en unidades definidas (segundos, minutos, etc.).
	 */
	public double getTiempoUso() {
		return estadistica.getTiempoUsoTotalActual();
	}

	/**
	 * Obtiene la racha de victorias actual.
	 * 
	 * @return Número de victorias consecutivas.
	 */
	public int getRachaVictorias() {
		return estadistica.getRachaVictorias();
	}

	/**
	 * Actualiza la racha de victorias según si la acción fue aprobada o no.
	 * Si aprobado, incrementa; si no, resetea la racha.
	 * 
	 * @param aprobado true si se aprobó, false si no.
	 */
	public void actualizarRachaVictorias(boolean aprobado) {
		if (aprobado)
			estadistica.incrementarRachaVictorias();
		else
			estadistica.resetRachaVictorias();
	}

	/**
	 * Obtiene la racha o número de accesos consecutivos.
	 * 
	 * @return Número de accesos.
	 */
	public int getRachaAcceso() {
		return estadistica.getNumAccesos();
	}

	/**
	 * Cierra la sesión de estadísticas y persiste los datos.
	 */
	public void cerrarSesion() {
		estadistica.cerrarSesion();
		dbEstadisticaDAO.update(estadistica);
	}

	/**
	 * Actualiza las estadísticas de aciertos según si la respuesta fue correcta.
	 * 
	 * @param respuestaCorrecta true si la respuesta fue correcta.
	 */
	public void actualizarAciertos(boolean respuestaCorrecta) {
		estadistica.actualizarAciertos(respuestaCorrecta);
	}

	/**
	 * Obtiene el porcentaje de aciertos como cadena.
	 * 
	 * @return Porcentaje de aciertos en formato String.
	 */
	public String getPorcentajeAciertos() {
		return String.valueOf(estadistica.getPorcentajeAciertos());
	}
}
