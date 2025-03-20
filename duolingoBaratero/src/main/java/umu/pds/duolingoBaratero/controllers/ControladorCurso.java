package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.BloqueContenido;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.PreguntaProgreso;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.filters.Filtro;
import umu.pds.duolingoBaratero.services.filters.FiltroBasico;
import umu.pds.duolingoBaratero.services.filters.FiltroCursosPorNombre;
import umu.pds.duolingoBaratero.services.filters.FiltroCursosPorPropietario;
import umu.pds.duolingoBaratero.services.filters.FiltroCursosValoracion;

public enum ControladorCurso {
	INSTANCE;
	
	private static final String ORDEN_DEFAULT = "Mas cursados";
	private List<CursoPlantilla> cursosPrueba = null;
	
	
	private ImageService sevicioImagenes;

	private ControladorCurso() {
		this.sevicioImagenes = new ImageService();
		pruebas();
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
	
	public Optional<CursoPlantilla> getCursoPlantilla(String nombre) {
		Optional<CursoPlantilla> optionalCurso = cursosPrueba.stream()
												.filter(c -> c.getNombre().equals(nombre))
												.findFirst();
		return optionalCurso;
	}
	
	public CursoEnProgreso getCursoEnProgreso(String nombre) {
		LinkedList<TipoPregunta> lista = new LinkedList<>();
		lista.add(TipoPregunta.OPCIONES);
		CursoPlantilla cursoPlantilla = new CursoPlantilla("Idiomas", "Baratero's Company" , "🗣️ Curso de aprendizaje de idiomas", "📈 Mejorar tus habilidades lingüísticas", Nivel.AVANZADO, null);
        CursoEnProgreso cursoEnProgreso = new CursoEnProgreso(ControladorUsuario.INSTANCE.getUsuarioActual(), cursoPlantilla, null, null, null);
		return cursoEnProgreso;
	}
	
	public CursoEnProgreso getCursoEnProgreso(CursoPlantilla curso, Usuario user) {
        return new CursoEnProgreso(user, curso, null, null, null);
	}
	
//	public List<CursoPlantilla> getAllCourses(String nombre, String propietario, String valoracion, String orden){
//		Filtro filtro = new FiltroCursos();
//	}
//	
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
	
	public List<CursoPlantilla> buscarCursos(String nombre, int valoracion, String propietario, String orden) {
		Filtro filtro = new FiltroBasico();
		if (nombre != null) {
			filtro = new FiltroCursosPorNombre(filtro, nombre);
		} else if (valoracion > 0) {
			filtro = new FiltroCursosValoracion(filtro, valoracion);
		} else if (propietario != null) {
			filtro = new FiltroCursosPorPropietario(filtro, propietario);
		}
		LinkedList<CursoPlantilla> lista = (LinkedList<CursoPlantilla>) filtro.filtrar(cursosPrueba);
		if (orden.equals(ORDEN_DEFAULT))
			return lista.stream()
			        .sorted(Comparator.comparingInt(CursoPlantilla::getNumAlumnos))
			        .collect(Collectors.toList());
		else 
			return lista.stream()
			        .sorted(Comparator.comparingInt(CursoPlantilla::getNumAlumnos).reversed())
			        .collect(Collectors.toList());
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

	public List<Pregunta> getPreguntasDeBloqueContenido(CursoEnProgreso curso, long numBloque) {
		return curso.getPreguntasBloqueContenido(numBloque);
	}

	public CursoPlantilla crearCurso(String nombre, String descripcion, String objetivos, Nivel lvl) {
		return new CursoPlantilla(nombre, ControladorUsuario.INSTANCE.getUsuarioActual().getNombre(), descripcion, objetivos, lvl);
	}

	public void setImagenACurso(CursoPlantilla curso, String imagen) {
		if (imagen != null) {
			curso.setImagen(imagen);
		}
		
	}
	
	//------Procesamiento preguntas y respuestas----------
	
	public boolean procesarRespuesta(Pregunta pregunta, String respuestaUsuario) {
		// TODO Si la respuesta es correcta 
		// Hacer algo si es falsa hacer algo
		boolean respuestaCorrecta = pregunta.esRespuestaCorrecta(respuestaUsuario);
		if (respuestaCorrecta) {
			
		}
		return respuestaCorrecta;
	}
	
	
	
	
	// *************************************************
	// PRUEBAS
	// ***************************************************
	private void pruebas() {
		cursosPrueba = new LinkedList<>();
		BloqueContenido bloque1Curso1 = new BloqueContenido(0,
				new PreguntaOpciones(Nivel.BASICO, 1, "¿Cómo se dice 'Hola' en inglés?", "Hello", TipoPregunta.OPCIONES,
						new String[] { "Hello", "Hi", "Bye", "Goodbye" }),
				new PreguntaOpciones(Nivel.BASICO, 2, "¿Qué significa 'Dog' en español?", "Perro",
						TipoPregunta.OPCIONES, new String[] { "Perro", "Gato", "Pájaro", "Pez" }),
				new PreguntaOpciones(Nivel.BASICO, 3, "Completa la frase: 'I ___ a student'", "am",
						TipoPregunta.OPCIONES, new String[] { "am", "is", "are", "be" }),
				new PreguntaOpciones(Nivel.BASICO, 4, "¿Cuál es el plural de 'child'?", "children",
						TipoPregunta.OPCIONES, new String[] { "childs", "childes", "children", "child" }));

		BloqueContenido bloque2Curso1 = new BloqueContenido(1,
				new PreguntaOpciones(Nivel.BASICO, 5, "¿Cómo se dice 'Gracias' en inglés?", "Thank you",
						TipoPregunta.OPCIONES, new String[] { "Thank you", "Thanks", "You're welcome", "Hello" }),
				new PreguntaOpciones(Nivel.BASICO, 6, "¿Qué significa 'Car' en español?", "Coche",
						TipoPregunta.OPCIONES, new String[] { "Coche", "Casa", "Camión", "Barco" }),
				new PreguntaOpciones(Nivel.BASICO, 7, "Completa la frase: 'She ___ a teacher'", "is",
						TipoPregunta.OPCIONES, new String[] { "is", "are", "am", "be" }),
				new PreguntaOpciones(Nivel.BASICO, 8, "¿Cómo se dice 'Libro' en inglés?", "Book", TipoPregunta.OPCIONES,
						new String[] { "Book", "Notebook", "Table", "Paper" }));

		BloqueContenido bloque3Curso1 = new BloqueContenido(2,
				new PreguntaOpciones(Nivel.BASICO, 9, "¿Cómo se dice 'Buenos días' en inglés?", "Good morning",
						TipoPregunta.OPCIONES, new String[] { "Good morning", "Good night", "Hello", "Bye" }),
				new PreguntaOpciones(Nivel.BASICO, 10, "¿Qué significa 'Water' en español?", "Agua",
						TipoPregunta.OPCIONES, new String[] { "Agua", "Fuego", "Aire", "Tierra" }),
				new PreguntaOpciones(Nivel.BASICO, 11, "¿Qué verbo es sinónimo de 'begin'?", "start",
						TipoPregunta.OPCIONES, new String[] { "start", "stop", "end", "pause" }),
				new PreguntaOpciones(Nivel.BASICO, 12, "Completa la frase: 'They ___ at home'", "are",
						TipoPregunta.OPCIONES, new String[] { "are", "is", "am", "be" }));

		CursoPlantilla curso1 = new CursoPlantilla("Ingles", "Profesor A", "Curso para principiantes",
				"Aprender vocabulario básico", Nivel.BASICO, bloque1Curso1, bloque2Curso1, bloque3Curso1);

		curso1.addBloqueContenido(bloque3Curso1);
		// Crear preguntas para el segundo curso
		BloqueContenido bloque1Curso2 = new BloqueContenido(0,
				new PreguntaOpciones(Nivel.INTERMEDIO, 1, "¿Cómo se dice 'Amigo' en inglés?", "Friend",
						TipoPregunta.OPCIONES, new String[] { "Friend", "Brother", "Sister", "Mother" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 2, "¿Qué significa 'Table' en español?", "Mesa",
						TipoPregunta.OPCIONES, new String[] { "Mesa", "Silla", "Cama", "Puerta" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 3, "Completa la frase: 'She ___ studying'", "is",
						TipoPregunta.OPCIONES, new String[] { "is", "are", "am", "be" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 4, "¿Cuál es el pasado de 'go'?", "went", TipoPregunta.OPCIONES,
						new String[] { "gone", "went", "goed", "going" }));

		BloqueContenido bloque2Curso2 = new BloqueContenido(1,
				new PreguntaOpciones(Nivel.INTERMEDIO, 5, "¿Cómo se dice 'Ventana' en inglés?", "Window",
						TipoPregunta.OPCIONES, new String[] { "Window", "Door", "Wall", "Floor" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 6, "¿Qué significa 'Chair' en español?", "Silla",
						TipoPregunta.OPCIONES, new String[] { "Silla", "Mesa", "Lámpara", "Pizarra" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 7, "Completa la frase: 'I ___ going to school'", "am",
						TipoPregunta.OPCIONES, new String[] { "am", "is", "are", "be" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 8, "¿Cuál es el opuesto de 'cold'?", "hot",
						TipoPregunta.OPCIONES, new String[] { "hot", "warm", "cool", "freezing" }));

		BloqueContenido bloque3Curso2 = new BloqueContenido(2,
				new PreguntaOpciones(Nivel.INTERMEDIO, 9, "¿Cómo se dice 'Sol' en inglés?", "Sun",
						TipoPregunta.OPCIONES, new String[] { "Sun", "Star", "Moon", "Sky" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 10, "¿Qué significa 'Fast' en español?", "Rápido",
						TipoPregunta.OPCIONES, new String[] { "Rápido", "Lento", "Fuerte", "Débil" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 11, "¿Cuál es el plural de 'mouse'?", "mice",
						TipoPregunta.OPCIONES, new String[] { "mice", "mouses", "mouse", "mousses" }),
				new PreguntaOpciones(Nivel.INTERMEDIO, 12, "Completa la frase: 'We ___ to the park'", "go",
						TipoPregunta.OPCIONES, new String[] { "go", "going", "went", "gone" }));

		CursoPlantilla curso2 = new CursoPlantilla("Ingles", "Profesor B", "Curso intermedio",
				"Mejorar gramática y vocabulario", Nivel.INTERMEDIO, bloque1Curso2, bloque2Curso2, bloque3Curso2);
		
		// Curso de Informática
		BloqueContenido bloque1Informatica = new BloqueContenido(0,
		        new PreguntaOpciones(Nivel.BASICO, 1, "¿Qué significa CPU?", "Unidad Central de Procesamiento",
		                TipoPregunta.OPCIONES, new String[]{"Unidad Central de Procesamiento", "Unidad de Control", "Memoria RAM", "Tarjeta Gráfica"}),
		        new PreguntaOpciones(Nivel.BASICO, 2, "¿Cuál es un sistema operativo?", "Windows",
		                TipoPregunta.OPCIONES, new String[]{"Windows", "Google", "Intel", "HTML"}));

		CursoPlantilla cursoInformatica = new CursoPlantilla("Informatica", "Profesor C", "Conceptos básicos de computación",
		        "Aprender sobre hardware y software", Nivel.BASICO, bloque1Informatica);

		// Curso de Música
		BloqueContenido bloque1Musica = new BloqueContenido(0,
		        new PreguntaOpciones(Nivel.BASICO, 1, "¿Cuántas notas musicales existen?", "Siete",
		                TipoPregunta.OPCIONES, new String[]{"Siete", "Cinco", "Doce", "Cuatro"}),
		        new PreguntaOpciones(Nivel.BASICO, 2, "¿Cuál es la clave musical más usada en partituras?", "Clave de sol",
		                TipoPregunta.OPCIONES, new String[]{"Clave de sol", "Clave de fa", "Clave de do", "Clave de la"}));

		CursoPlantilla cursoMusica = new CursoPlantilla("Música", "Profesor D", "Introducción a la teoría musical",
		        "Aprender sobre notas y claves musicales", Nivel.BASICO, bloque1Musica);

		// Curso de Ciencia
		BloqueContenido bloque1Ciencia = new BloqueContenido(0,
		        new PreguntaOpciones(Nivel.BASICO, 1, "¿Qué estudia la biología?", "Los seres vivos",
		                TipoPregunta.OPCIONES, new String[]{"Los seres vivos", "Los planetas", "Los elementos químicos", "Las rocas"}),
		        new PreguntaOpciones(Nivel.BASICO, 2, "¿Cuál es la fórmula del agua?", "H2O",
		                TipoPregunta.OPCIONES, new String[]{"H2O", "CO2", "O2", "H2SO4"}));

		CursoPlantilla cursoCiencia = new CursoPlantilla("Ciencia", "Profesor E", "Principios básicos de la ciencia",
		        "Introducción a conceptos científicos", Nivel.BASICO, bloque1Ciencia);

		// Curso de Estudios
		BloqueContenido bloque1Estudios = new BloqueContenido(0,
		        new PreguntaOpciones(Nivel.BASICO, 1, "¿Cuál es una técnica efectiva de estudio?", "Mapas mentales",
		                TipoPregunta.OPCIONES, new String[]{"Mapas mentales", "Mirar videos", "Dormir más", "No tomar apuntes"}),
		        new PreguntaOpciones(Nivel.BASICO, 2, "¿Qué es la mnemotecnia?", "Un método de memorización",
		                TipoPregunta.OPCIONES, new String[]{"Un método de memorización", "Un idioma antiguo", "Una asignatura", "Un deporte"}));

		CursoPlantilla cursoEstudios = new CursoPlantilla("Estudios", "Profesor F", "Estrategias para mejorar el aprendizaje",
		        "Técnicas y hábitos de estudio", Nivel.BASICO, bloque1Estudios);

		// Curso de Diseño
		BloqueContenido bloque1Diseno = new BloqueContenido(0,
		        new PreguntaOpciones(Nivel.BASICO, 1, "¿Cuál es un software de diseño gráfico?", "Photoshop",
		                TipoPregunta.OPCIONES, new String[]{"Photoshop", "Excel", "Word", "Windows"}),
		        new PreguntaOpciones(Nivel.BASICO, 2, "¿Qué es la teoría del color?", "El estudio de cómo los colores interactúan",
		                TipoPregunta.OPCIONES, new String[]{"El estudio de cómo los colores interactúan", "La combinación de colores", "El uso de filtros en fotos", "La elección de tipografías"}));

		CursoPlantilla cursoDiseno = new CursoPlantilla("Diseño", "Profesor G", "Fundamentos del diseño gráfico",
		        "Aprender sobre composición y colores", Nivel.BASICO, bloque1Diseno);

		cursosPrueba.add(cursoInformatica);
		cursosPrueba.add(cursoMusica);
		cursosPrueba.add(cursoCiencia);
		cursosPrueba.add(cursoEstudios);
		cursosPrueba.add(cursoDiseno);

		
		cursosPrueba.add(curso2);
		cursosPrueba.add(curso1);
	}
}
