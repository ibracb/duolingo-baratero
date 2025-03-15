package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.filters.FiltradorCursos;
import umu.pds.duolingoBaratero.services.filters.FiltroCursos;

public enum ControladorCurso {
	INSTANCE;
	
	private ImageService sevicioImagenes;
	private FiltradorCursos<CursoPlantilla> filtradorCursos;

	private ControladorCurso() {
		this.sevicioImagenes = new ImageService();
		this.filtradorCursos = new FiltradorCursos<>();
	}
	
	public CursoPlantilla getCurso(String nombre) {
		LinkedList<TipoPregunta> lista = new LinkedList<>();
		lista.add(TipoPregunta.OPCIONES);
		CursoPlantilla cursoPlantilla = new CursoPlantilla("Idiomas", "Baratero's Company" , "🗣️ Curso de aprendizaje de idiomas", "📈 Mejorar tus habilidades lingüísticas", Nivel.AVANZADO, null);
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
	
	
	//------FILTROS--------
	
	public void setFiltroDecorador(FiltroCursos<CursoPlantilla> filtroDecorador) {
		this.filtradorCursos = new FiltradorCursos<>(filtroDecorador);
	}
	
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> cursos) {
		return filtradorCursos.filtrar(cursos);
	}
	
	//------RENDERIZACION PREGUNTAS--------
	
	
	/**
	 * Este metodo recibe el id de un bloque de contenido 
	 * y devuelve todos los paneles pregunta asociados a ese  bloque
	 * @param bloqueContenido
	 * @return
	 */
	//TODO: 		
	// Recuperar el bloque de contenido de alguna forma
	// obtener las preguntas relacionadas con ese bloque
	// Para cada pregutna crear su propio jpanel
	// añadirlo al array y devolverlo
	
	public JPanel[] generarLeccion(long bloqueContenido) {
		return new JPanel[0];
	}

	public CursoPlantilla crearCurso(String nombre, String descripcion, String objetivos, Nivel lvl) {
		return null;
	}

	public void setImagenACurso(Icon imagen) {
		
	}

}
