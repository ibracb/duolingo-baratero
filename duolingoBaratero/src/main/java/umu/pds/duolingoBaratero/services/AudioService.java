package umu.pds.duolingoBaratero.services;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Servicio para reproducir audio utilizando JavaFX MediaPlayer.
 * Este servicio permite reproducir archivos de audio desde una ruta específica.
 * 
 * Singleton: Se utiliza una única instancia de AudioService en toda la aplicación.
 */
public enum AudioService {
	
	/**
	 * Instancia única del servicio de audio.
	 */
	INSTANCE;
	
	/**
	 * Reproductor de medios de JavaFX.
	 * Se utiliza para reproducir archivos de audio.
	 */
	private MediaPlayer mediaPlayer;
	
	/**
	 * Constructor privado para evitar la creación de instancias externas.
	 * Se utiliza el patrón Singleton para garantizar una única instancia.
	 */
	private AudioService() {
		//new JFXPanel();
	}
	
	/**
	 * Método para reproducir un archivo de audio desde una ruta específica.
	 * 
	 * @param ruta Ruta del archivo de audio a reproducir.
	 *              Debe ser una ruta válida y el archivo debe existir.
	 */
	public void playAudio(String ruta) {
		
		File fichero = new File(ruta);
		assert fichero.exists();
		
		Media media = new Media(fichero.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(1.0);
		mediaPlayer.play();
		
	}
	
}
