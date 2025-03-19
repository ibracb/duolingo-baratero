package umu.pds.duolingoBaratero.services;
import javafx.embed.swing.JFXPanel;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioService {
	
	private MediaPlayer mediaPlayer;
	
	public AudioService() {
		new JFXPanel();
	}
	
	public void playAudio(String ruta) {
		
		File fichero = new File(ruta);
		assert fichero.exists();
		
		Media media = new Media(fichero.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(1.0);
		mediaPlayer.play();
		
	}
	
}
