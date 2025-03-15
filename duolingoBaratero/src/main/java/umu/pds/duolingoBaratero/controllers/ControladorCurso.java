package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.filters.FiltradorCursos;
import umu.pds.duolingoBaratero.services.filters.Filtro;

public enum ControladorCurso {
	INSTANCE;
	
	private ImageService sevicioImagenes;
	private FiltradorCursos<CursoPlantilla> filtradorCursos;

	private ControladorCurso() {
		this.sevicioImagenes = new ImageService();
		this.filtradorCursos = new FiltradorCursos<>();
	}
	
	public boolean isCursoNuevo(CursoEnProgreso curso) {
		return curso.isNuevo();
	}
	
	public boolean isCursoEnMarcha(CursoEnProgreso curso) {
		return curso.isEnMarcha();
	}
	
	public boolean isCursoFinalizado(CursoEnProgreso curso) {
		return curso.isFinalizado();
	}
	
	public long getNumLastBloqueContenido(CursoEnProgreso curso) {
		return curso.getNumLastBloqueContenido();
	}
	
	public CursoPlantilla getCursoPlantilla(String nombre) {
		LinkedList<TipoPregunta> lista = new LinkedList<>();
		lista.add(TipoPregunta.OPCIONES);
		CursoPlantilla cursoPlantilla = new CursoPlantilla("Idiomas", "Baratero's Company" , "🗣️ Curso de aprendizaje de idiomas", "📈 Mejorar tus habilidades lingüísticas", Nivel.AVANZADO, null);
		return cursoPlantilla;
	}
	
	public CursoEnProgreso getCursoEnProgreso(String nombre) {
		LinkedList<TipoPregunta> lista = new LinkedList<>();
		lista.add(TipoPregunta.OPCIONES);
		CursoPlantilla cursoPlantilla = new CursoPlantilla("Idiomas", "Baratero's Company" , "🗣️ Curso de aprendizaje de idiomas", "📈 Mejorar tus habilidades lingüísticas", Nivel.AVANZADO, null);
        CursoEnProgreso cursoEnProgreso = new CursoEnProgreso(ControladorUsuario.INSTANCE.getUsuarioActual(), cursoPlantilla, null, null, null);
		return cursoEnProgreso;
	}
	
	public List<CursoPlantilla> getAllCourses(String nombre, String propietario, String valoracion, String orden){
		Filtro filtro = new FiltroCursos();
	}
	
	// ----------------------------------------------
	// Funciones imagenes
	// ----------------------------------------------
	
	private ImageIcon whichImage(CursoPlantilla curso, int dimensiones) throws IOException {
		BufferedImage image = null;
		String imagen = null;

		if (curso.hasImage())
			imagen = curso.getImagen();

		if (imagen != null) {
			if (sevicioImagenes.isURL(imagen)) {
				image = ImageIO.read(new URL(imagen));
			} else if (Files.exists(Paths.get(imagen))) {
				image = ImageIO.read(Paths.get(imagen).toFile());
			}
		}

		if (image != null) {
			return getScaledImage(image, dimensiones);
		} else {
			return getScaledDefaultImage(dimensiones);
		}
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
	
	public void setFiltroDecorador(Filtro<CursoPlantilla> filtroDecorador) {
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

	public void setImagenACurso(CursoPlantilla curso, String imagen) {
		if (imagen != null) {
			curso.setImagen(imagen);
		}
		
	}

}
