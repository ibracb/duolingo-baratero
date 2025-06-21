package umu.pds.duolingoBaratero.controllers;

import umu.pds.duolingoBaratero.services.AudioService;

/**
 * Controlador para gestionar la reproducción de audio en el sistema.
 */
public enum ControladorAudio {
	
	/**
	 * Instancia única del controlador de audio.
	 */
	INSTANCE;
	
	/**
	 * Servicio de reproducción de audio.
	 */
	private final AudioService reproductor;
	
	/**
	 * Constructor privado para inicializar el reproductor de audio.
	 */
	private ControladorAudio() {
		this.reproductor = AudioService.INSTANCE;
	}
	
	/**
	 * Reproduce un archivo de audio desde la ruta especificada.
	 * 
	 * @param ruta Ruta del archivo de audio a reproducir.
	 */
	public void reproducir(String ruta) {
		reproductor.playAudio(ruta);
	}
	
}
