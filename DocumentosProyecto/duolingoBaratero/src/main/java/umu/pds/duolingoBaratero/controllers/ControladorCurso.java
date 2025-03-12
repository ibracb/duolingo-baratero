package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.services.ImageService;

public enum ControladorCurso {
	INSTANCE;
	
	private ImageService sevicioImagenes;

	private ControladorCurso() {
		this.sevicioImagenes = new ImageService();
	}
	
	public CursoPlantilla getCurso(String nombre) {
		LinkedList<TipoPregunta> lista = new LinkedList<>();
		lista.add(TipoPregunta.COMPLETE);
		CursoPlantilla cursoPlantilla = new CursoPlantilla("Idiomas", "🗣️ Curso de aprendizaje de idiomas", "📈 Mejorar tus habilidades lingüísticas", Nivel.AVANZADO, null);
		return cursoPlantilla;
	}
	
	public ImageIcon getScaledImage(BufferedImage bufferedImage, int dimensiones) {
		return sevicioImagenes.getScaledImage(bufferedImage, dimensiones);
	}
	
	public ImageIcon getScaledImage(ImageIcon image, int dimensiones) {
		return sevicioImagenes.getScaledImage(image, dimensiones);
	}
	
	public ImageIcon getScaledDefaultImage(int dimensiones) {
		ImageIcon image = new ImageIcon(getClass().getResource("/persona.png"));
		return sevicioImagenes.getScaledImage(image, dimensiones);
	}

}
