package umu.pds.apps;
//
import java.awt.EventQueue;

import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.PreguntaAudio;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaAudio;
import umu.pds.duolingoBaratero.windows.vista.VentanaPregunta;

public class ProbarAudio {

	public static void main(String[] args) {
		
		String[] opciones = { "Op1", "Op2", "Op3" };
		
		PreguntaAudio pregunta = new PreguntaAudio(Nivel.PRINCIPIANTE, 1, "¿Qué escuchas?", "Op1", opciones, "src/main/resources/audios/town-10169.mp3");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					VentanaPregunta frame = new VentanaPregunta(1);
					PanelPreguntaAudio panel = new PanelPreguntaAudio(pregunta);
					frame.add(panel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		
	}

}
