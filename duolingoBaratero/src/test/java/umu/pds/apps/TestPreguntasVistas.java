package umu.pds.apps;

import java.awt.EventQueue;

import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.Flashcard;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.PreguntaAudio;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.windows.vista.VentanaPregunta;

public class TestPreguntasVistas {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPregunta frame = new VentanaPregunta(69);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public static  JPanel[] getPaneles() {
		
		JPanel[] paneles = new JPanel[4];
		
		String[] opciones = { "Opción 1", "Opción 2", "Opción 3" };
		Pregunta[] preguntas = new Pregunta[4]; // Array de tamaño 5, pero vacío

		preguntas[0] = new PreguntaAudio(Nivel.INTERMEDIO, 1, "¿Qué sonido se escucha?", "Opción 2",
				opciones, "ruta/al/archivo/audio.mp3");
		preguntas[1] = new PreguntaOpciones(Nivel.INTERMEDIO, 1, "¿cual es la respuesta?", "Opción 2",
				TipoPregunta.OPCIONES, opciones);
		preguntas[2] = new Flashcard(Nivel.AVANZADO, 3, "¿Elemento químico Na?", "Sodio", TipoPregunta.FLASHCARD,
				69696969);
		preguntas[3] = new PreguntaOpciones(Nivel.INTERMEDIO, 1, "¿cual es la respuesta?", "Opción 2",
				TipoPregunta.IMAGEN, opciones);
		
		int i = 0;
		for (Pregunta pregunta : preguntas) {
			paneles[i] = pregunta.crearPanel();
			i++;
		}
		
		return paneles;
	}
}
