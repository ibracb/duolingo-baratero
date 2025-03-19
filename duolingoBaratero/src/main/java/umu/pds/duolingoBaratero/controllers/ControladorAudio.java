package umu.pds.duolingoBaratero.controllers;

import umu.pds.duolingoBaratero.services.AudioService;

public enum ControladorAudio {
	INSTANCE;
	
	private AudioService reproductor;
	
	private ControladorAudio() {
		reproductor = new AudioService();
	}
	
	public void playAudio(String ruta) {
		reproductor.playAudio(ruta);
	}
	
}
