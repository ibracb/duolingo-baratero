package umu.pds.duolingoBaratero.controllers;

import umu.pds.duolingoBaratero.services.AudioService;

public enum ControladorAudio {
	
	INSTANCE;
	
	private final AudioService reproductor;
	
	private ControladorAudio() {
		this.reproductor = AudioService.INSTANCE;
	}
	
	public void reproducir(String ruta) {
		reproductor.playAudio(ruta);
	}
	
}
