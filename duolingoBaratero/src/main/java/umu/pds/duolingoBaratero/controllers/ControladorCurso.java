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
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import umu.pds.duolingoBaratero.models.BloqueContenido;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Flashcard;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;
import umu.pds.duolingoBaratero.services.AudioService;
import umu.pds.duolingoBaratero.services.CursoService;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.filters.Filtro;
import umu.pds.duolingoBaratero.services.filters.FiltroBasico;
import umu.pds.duolingoBaratero.services.filters.FiltroCursosPorNombre;
import umu.pds.duolingoBaratero.services.filters.FiltroCursosPorPropietario;
import umu.pds.duolingoBaratero.services.filters.FiltroCursosValoracion;
import umu.pds.duolingoBaratero.services.serializers.JSONSerializer;
import umu.pds.duolingoBaratero.services.serializers.Serializer;

public enum ControladorCurso {
	INSTANCE;
	
	private static final String ORDEN_DEFAULT = "Mas cursados";
	private List<CursoPlantilla> cursosPrueba = null;
	private CursoPlantilla cursoActual;
	private ImageService sevicioImagenes;
	private AudioService reproductor;
	private Serializer serializer;
	private CursoService servicioCursos;

	private ControladorCurso() {
		this.sevicioImagenes = new ImageService();
		this.reproductor = AudioService.INSTANCE;
		this.serializer = new JSONSerializer();
		this.servicioCursos = CursoService.INSTANCE;
		pruebas();
	}
	

	public boolean isCursoNuevo(CursoEnProgreso curso) {
		if (curso == null) {
			System.out.println("Curso nulo");
			return false;
		}
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
	
	public String getNombrePropietario(CursoPlantilla curso) {
		return curso.getPropietario().getNombre();
	}
	
	public CursoEnProgreso getCursoEnProgreso(String nombre) {
		Optional<CursoPlantilla> cursoPlantilla = this.getCursoPlantilla(nombre);
		if (cursoPlantilla.isPresent())
			return new CursoEnProgreso(ControladorUsuario.INSTANCE.getUsuarioActual(), cursoPlantilla.get(), null, null, null);
		return null;
	}
	
	public CursoEnProgreso getCursoEnProgreso(CursoPlantilla curso, Usuario user) {
        return new CursoEnProgreso(user, curso, null, null, null);
	}
	
	public void guardarPreguntas(List<Pregunta> preguntas, CursoPlantilla curso) {
	
	}
	
	public void playAudio(String ruta) {
		reproductor.playAudio(ruta);
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
			imagen = curso.getImagen().toString();

		if (imagen != null) {
			if (sevicioImagenes.isURL(imagen.toString())) {
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

	public CursoPlantilla crearCurso(String nombre, String descripcion, String objetivos) {
		return new CursoPlantilla(nombre,  ControladorUsuario.INSTANCE.getUsuarioActual() , descripcion, objetivos);
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
		Usuario usuarioPrueba = new Usuario("Profesor B", "Mr B", "profesorb@gmail.com", "1234");
		BloqueContenido bloque1Curso1 = new BloqueContenido(0,
				new PreguntaOpciones(Nivel.BASICO, 1, "¿Cómo se dice 'Hola' en inglés?", "Hello", TipoPregunta.OPCIONES,
						new String[] { "Hello", "Hi", "Bye", "Goodbye" }),
				new PreguntaOpciones(Nivel.BASICO, 2, "¿Qué significa 'Dog' en español?", "Perro",
						TipoPregunta.OPCIONES, new String[] { "Perro", "Gato", "Pájaro", "Pez" }),
				new PreguntaOpciones(Nivel.BASICO, 3, "Completa la frase: 'I ___ a student'", "am",
						TipoPregunta.OPCIONES, new String[] { "am", "is", "are", "be" }),
				new PreguntaOpciones(Nivel.BASICO, 4, "¿Cuál es el plural de 'child'?", "children",
						TipoPregunta.OPCIONES, new String[] { "childs", "childes", "children", "child" }),
				new Flashcard(Nivel.BASICO, 4, "Significado de: Green", "Verde", TipoPregunta.FLASHCARD, 30),
				new PreguntaOpciones(Nivel.BASICO, 6, "¿Cuál cual de estos es leche?", "CursosPDS/src/main/resources/milk.png", 
						TipoPregunta.IMAGEN, new String[] { "tea.png", "milk.png", "coffee.png" }));			
		

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

		CursoPlantilla curso1 = new CursoPlantilla("Ingles", usuarioPrueba, "Curso para principiantes",
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

		CursoPlantilla curso2 = new CursoPlantilla("Ingles", usuarioPrueba, "Curso intermedio",
				"Mejorar gramática y vocabulario", Nivel.INTERMEDIO, bloque1Curso2, bloque2Curso2, bloque3Curso2);
		
		// Curso de Informática
		BloqueContenido bloque1Informatica = new BloqueContenido(0,
			    new PreguntaOpciones(Nivel.BASICO, 1, "¿Qué significa CPU?", "Unidad Central de Procesamiento",
			            TipoPregunta.OPCIONES, new String[]{"Unidad Central de Procesamiento", "Unidad de Control", "Memoria RAM", "Tarjeta Gráfica"}),
			    new PreguntaOpciones(Nivel.BASICO, 2, "¿Cuál es un sistema operativo?", "Windows",
			            TipoPregunta.OPCIONES, new String[]{"Windows", "Google", "Intel", "HTML"}),
			    new PreguntaOpciones(Nivel.BASICO, 3, "¿Qué significa RAM?", "Memoria de acceso aleatorio",
			            TipoPregunta.OPCIONES, new String[]{"Memoria de acceso aleatorio", "Unidad de almacenamiento", "Procesador", "Tarjeta madre"}),
			    new PreguntaOpciones(Nivel.BASICO, 4, "¿Cuál es un lenguaje de programación?", "Python",
			            TipoPregunta.OPCIONES, new String[]{"Python", "Windows", "Google Chrome", "Facebook"}),
			    new PreguntaOpciones(Nivel.BASICO, 5, "¿Qué es un bit?", "La unidad más pequeña de información en un ordenador",
			            TipoPregunta.OPCIONES, new String[]{"La unidad más pequeña de información en un ordenador", "Un procesador", "Un programa", "Un sistema operativo"}),
			    new PreguntaOpciones(Nivel.BASICO, 6, "¿Cuál es la función del sistema operativo?", "Gestionar los recursos del hardware y software",
			            TipoPregunta.OPCIONES, new String[]{"Gestionar los recursos del hardware y software", "Crear archivos de texto", "Navegar por internet", "Acelerar la memoria RAM"}),
			    new PreguntaOpciones(Nivel.BASICO, 7, "¿Cuál es un navegador web?", "Google Chrome",
			            TipoPregunta.OPCIONES, new String[]{"Google Chrome", "Microsoft Word", "Windows", "Linux"}),
			    new PreguntaOpciones(Nivel.BASICO, 8, "¿Para qué sirve un antivirus?", "Proteger el sistema contra malware y virus",
			            TipoPregunta.OPCIONES, new String[]{"Proteger el sistema contra malware y virus", "Mejorar la velocidad del internet", "Eliminar archivos duplicados", "Aumentar la memoria RAM"}),
			    new PreguntaOpciones(Nivel.BASICO, 9, "¿Qué es HTML?", "Un lenguaje de marcado para crear páginas web",
			            TipoPregunta.OPCIONES, new String[]{"Un lenguaje de marcado para crear páginas web", "Un sistema operativo", "Un programa de edición de fotos", "Un lenguaje de programación"}),
			    new PreguntaOpciones(Nivel.BASICO, 10, "¿Qué es la nube en informática?", "Un servicio de almacenamiento y computación en internet",
			            TipoPregunta.OPCIONES, new String[]{"Un servicio de almacenamiento y computación en internet", "Un programa de edición de texto", "Un procesador", "Un navegador web"})
			);

		CursoPlantilla cursoInformatica = new CursoPlantilla("Informatica", usuarioPrueba, "Conceptos básicos de computación",
		        "Aprender sobre hardware y software", Nivel.BASICO, bloque1Informatica);

		// Curso de Música
		BloqueContenido bloque1Musica = new BloqueContenido(0,
			    new PreguntaOpciones(Nivel.BASICO, 1, "¿Cuántas notas musicales existen?", "Siete",
			            TipoPregunta.OPCIONES, new String[]{"Siete", "Cinco", "Doce", "Cuatro"}),
			    new PreguntaOpciones(Nivel.BASICO, 2, "¿Cuál es la clave musical más usada en partituras?", "Clave de sol",
			            TipoPregunta.OPCIONES, new String[]{"Clave de sol", "Clave de fa", "Clave de do", "Clave de la"}),
			    new PreguntaOpciones(Nivel.BASICO, 3, "¿Qué instrumento tiene teclas blancas y negras?", "Piano",
			            TipoPregunta.OPCIONES, new String[]{"Piano", "Guitarra", "Batería", "Violín"}),
			    new PreguntaOpciones(Nivel.BASICO, 4, "¿Cuántas cuerdas tiene una guitarra clásica?", "Seis",
			            TipoPregunta.OPCIONES, new String[]{"Seis", "Cuatro", "Ocho", "Cinco"}),
			    new PreguntaOpciones(Nivel.BASICO, 5, "¿Cómo se llama el signo que representa la duración del sonido en una partitura?", "Figura musical",
			            TipoPregunta.OPCIONES, new String[]{"Figura musical", "Clave", "Tempo", "Pentagrama"}),
			    new PreguntaOpciones(Nivel.BASICO, 6, "¿Cuál es el tempo musical más rápido?", "Presto",
			            TipoPregunta.OPCIONES, new String[]{"Presto", "Andante", "Adagio", "Largo"}),
			    new PreguntaOpciones(Nivel.BASICO, 7, "¿Qué familia de instrumentos incluye la trompeta y el trombón?", "Viento-metal",
			            TipoPregunta.OPCIONES, new String[]{"Viento-metal", "Cuerda", "Percusión", "Viento-madera"}),
			    new PreguntaOpciones(Nivel.BASICO, 8, "¿Qué elemento define la intensidad del sonido en la música?", "Dinámica",
			            TipoPregunta.OPCIONES, new String[]{"Dinámica", "Tempo", "Melodía", "Armonía"}),
			    new PreguntaOpciones(Nivel.BASICO, 9, "¿Cómo se llama la parte de la canción que se repite?", "Estribillo",
			            TipoPregunta.OPCIONES, new String[]{"Estribillo", "Verso", "Puente", "Coda"}),
			    new PreguntaOpciones(Nivel.BASICO, 10, "¿Cuál es el nombre de la escala musical más común en la música occidental?", "Escala mayor",
			            TipoPregunta.OPCIONES, new String[]{"Escala mayor", "Escala menor", "Escala cromática", "Escala pentatónica"})
			);

		CursoPlantilla cursoMusica = new CursoPlantilla("Música", usuarioPrueba, "Introducción a la teoría musical",
		        "Aprender sobre notas y claves musicales", Nivel.BASICO, bloque1Musica);

		// Curso de Ciencia
		BloqueContenido bloque1Ciencia = new BloqueContenido(0,
			    new PreguntaOpciones(Nivel.BASICO, 1, "¿Qué estudia la biología?", "Los seres vivos",
			            TipoPregunta.OPCIONES, new String[]{"Los seres vivos", "Los planetas", "Los elementos químicos", "Las rocas"}),
			    new PreguntaOpciones(Nivel.BASICO, 2, "¿Cuál es la fórmula del agua?", "H2O",
			            TipoPregunta.OPCIONES, new String[]{"H2O", "CO2", "O2", "H2SO4"}),
			    new PreguntaOpciones(Nivel.BASICO, 3, "¿Cuál es el planeta más grande del sistema solar?", "Júpiter",
			            TipoPregunta.OPCIONES, new String[]{"Júpiter", "Saturno", "Neptuno", "Tierra"}),
			    new PreguntaOpciones(Nivel.BASICO, 4, "¿Qué tipo de energía es producida por el sol?", "Energía solar",
			            TipoPregunta.OPCIONES, new String[]{"Energía solar", "Energía eólica", "Energía térmica", "Energía química"}),
			    new PreguntaOpciones(Nivel.BASICO, 5, "¿Qué gas es esencial para la respiración humana?", "Oxígeno",
			            TipoPregunta.OPCIONES, new String[]{"Oxígeno", "Nitrógeno", "Dióxido de carbono", "Helio"}),
			    new PreguntaOpciones(Nivel.BASICO, 6, "¿Cómo se llama el proceso mediante el cual las plantas producen su alimento?", "Fotosíntesis",
			            TipoPregunta.OPCIONES, new String[]{"Fotosíntesis", "Respiración", "Fermentación", "Digestión"}),
			    new PreguntaOpciones(Nivel.BASICO, 7, "¿Cuál es el metal más abundante en la corteza terrestre?", "Aluminio",
			            TipoPregunta.OPCIONES, new String[]{"Aluminio", "Hierro", "Cobre", "Oro"}),
			    new PreguntaOpciones(Nivel.BASICO, 8, "¿Qué científico formuló la teoría de la relatividad?", "Albert Einstein",
			            TipoPregunta.OPCIONES, new String[]{"Albert Einstein", "Isaac Newton", "Galileo Galilei", "Nikola Tesla"}),
			    new PreguntaOpciones(Nivel.BASICO, 9, "¿Qué partícula subatómica tiene carga negativa?", "Electrón",
			            TipoPregunta.OPCIONES, new String[]{"Electrón", "Protón", "Neutrón", "Quark"}),
			    new PreguntaOpciones(Nivel.BASICO, 10, "¿Cuál es el proceso por el cual el agua se convierte en vapor?", "Evaporación",
			            TipoPregunta.OPCIONES, new String[]{"Evaporación", "Condensación", "Sublimación", "Fusión"})
			);

		CursoPlantilla cursoCiencia = new CursoPlantilla("Ciencia", usuarioPrueba, "Principios básicos de la ciencia",
		        "Introducción a conceptos científicos", Nivel.BASICO, bloque1Ciencia);

		// Curso de Estudios
		BloqueContenido bloque1Estudios = new BloqueContenido(0,
			    new PreguntaOpciones(Nivel.BASICO, 1, "¿Cuál es una técnica efectiva de estudio?", "Mapas mentales",
			            TipoPregunta.OPCIONES, new String[]{"Mapas mentales", "Mirar videos", "Dormir más", "No tomar apuntes"}),
			    new PreguntaOpciones(Nivel.BASICO, 2, "¿Qué es la mnemotecnia?", "Un método de memorización",
			            TipoPregunta.OPCIONES, new String[]{"Un método de memorización", "Un idioma antiguo", "Una asignatura", "Un deporte"}),
			    new PreguntaOpciones(Nivel.BASICO, 3, "¿Qué técnica ayuda a resumir información visualmente?", "Diagramas de flujo",
			            TipoPregunta.OPCIONES, new String[]{"Diagramas de flujo", "Lectura en voz alta", "Copiar textos", "Escuchar música"}),
			    new PreguntaOpciones(Nivel.BASICO, 4, "¿Cuál es el beneficio de tomar apuntes?", "Facilita la retención de información",
			            TipoPregunta.OPCIONES, new String[]{"Facilita la retención de información", "Consume tiempo", "Es obligatorio", "Evita estudiar después"}),
			    new PreguntaOpciones(Nivel.BASICO, 5, "¿Qué estrategia ayuda a gestionar mejor el tiempo de estudio?", "La técnica Pomodoro",
			            TipoPregunta.OPCIONES, new String[]{"La técnica Pomodoro", "Leer sin pausas", "Estudiar sin planificación", "Solo estudiar antes del examen"}),
			    new PreguntaOpciones(Nivel.BASICO, 6, "¿Qué ayuda a mejorar la comprensión lectora?", "Hacer resúmenes",
			            TipoPregunta.OPCIONES, new String[]{"Hacer resúmenes", "Leer rápido", "No releer", "Memorizar palabra por palabra"}),
			    new PreguntaOpciones(Nivel.BASICO, 7, "¿Por qué es importante el descanso en el estudio?", "Mejora la concentración y la memoria",
			            TipoPregunta.OPCIONES, new String[]{"Mejora la concentración y la memoria", "Es una pérdida de tiempo", "Es solo para relajarse", "Evita aprender"}),
			    new PreguntaOpciones(Nivel.BASICO, 8, "¿Cómo se puede mejorar la retención de información?", "Repasando periódicamente",
			            TipoPregunta.OPCIONES, new String[]{"Repasando periódicamente", "Solo leyendo una vez", "Dejando todo para el final", "No revisando apuntes"}),
			    new PreguntaOpciones(Nivel.BASICO, 9, "¿Qué técnica facilita recordar listas de palabras?", "Asociaciones y acrónimos",
			            TipoPregunta.OPCIONES, new String[]{"Asociaciones y acrónimos", "Leer en silencio", "Estudiar en grupo", "Tomar más café"}),
			    new PreguntaOpciones(Nivel.BASICO, 10, "¿Qué puede ayudar a mantener la motivación al estudiar?", "Establecer metas claras",
			            TipoPregunta.OPCIONES, new String[]{"Establecer metas claras", "Estudiar sin plan", "Evitar descansos", "Solo estudiar antes del examen"})
			);
		CursoPlantilla cursoEstudios = new CursoPlantilla("Estudios", usuarioPrueba, "Estrategias para mejorar el aprendizaje",
		        "Técnicas y hábitos de estudio", Nivel.BASICO, bloque1Estudios);

		// Curso de Diseño
		BloqueContenido bloque1Diseno = new BloqueContenido(0,
		        new PreguntaOpciones(Nivel.BASICO, 1, "¿Cuál es un software de diseño gráfico?", "Photoshop",
		                TipoPregunta.OPCIONES, new String[]{"Photoshop", "Excel", "Word", "Windows"}),
		        new PreguntaOpciones(Nivel.BASICO, 2, "¿Qué es la teoría del color?", "El estudio de cómo los colores interactúan",
		                TipoPregunta.OPCIONES, new String[]{"El estudio de cómo los colores interactúan", "La combinación de colores", "El uso de filtros en fotos", "La elección de tipografías"}));

		CursoPlantilla cursoDiseno = new CursoPlantilla("Diseño", usuarioPrueba, "Fundamentos del diseño gráfico",
		        "Aprender sobre composición y colores", Nivel.BASICO, bloque1Diseno);

		cursosPrueba.add(cursoInformatica);
		cursosPrueba.add(cursoMusica);
		cursosPrueba.add(cursoCiencia);
		cursosPrueba.add(cursoEstudios);
		cursosPrueba.add(cursoDiseno);

		
		cursosPrueba.add(curso2);
		cursosPrueba.add(curso1);
		// Crear los cursos y agregar los bloques de contenido al repositorio
		RepositorioCurso.INSTANCE.agregarCursoPlantilla(curso1);
		RepositorioCurso.INSTANCE.agregarCursoPlantilla(curso2);
		RepositorioCurso.INSTANCE.agregarCursoPlantilla(cursoInformatica);
		RepositorioCurso.INSTANCE.agregarCursoPlantilla(cursoMusica);
		RepositorioCurso.INSTANCE.agregarCursoPlantilla(cursoCiencia);
		RepositorioCurso.INSTANCE.agregarCursoPlantilla(cursoEstudios);
		RepositorioCurso.INSTANCE.agregarCursoPlantilla(cursoDiseno);

		// Agregar bloques de contenido para el curso 1
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque1Curso1);
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque2Curso1);
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque3Curso1);

		// Agregar bloques de contenido para el curso 2
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque1Curso2);
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque2Curso2);
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque3Curso2);

		// Agregar bloques de contenido para el curso de informática
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque1Informatica);

		// Agregar bloques de contenido para el curso de música
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque1Musica);

		// Agregar bloques de contenido para el curso de ciencia
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque1Ciencia);

		// Agregar bloques de contenido para el curso de estudios
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque1Estudios);

		// Agregar bloques de contenido para el curso de diseño
		RepositorioCurso.INSTANCE.agregarBloqueContenido(bloque1Diseno);

	}


	public int getNumPreguntas(long bloqueContenido) {
		return RepositorioCurso.INSTANCE.obtenerBloqueContenido(bloqueContenido).getNumPreguntas();
	}


	public void compartirCurso(CursoEnProgreso curso) {
		if (curso == null) {
	        System.err.println("Error: El curso es null.");
	        return;  // Termina la ejecución del método si curso es null.
	    }
		serializer.serialize("src/main/resources/cursos/"+curso.getNombre() + ".json", curso.getCursoPlantilla());
	}


	public void avanzarBloqueContenido(CursoEnProgreso curso, boolean aprobado) {
		curso.avanzarBloqueActual(aprobado);
	}
}
