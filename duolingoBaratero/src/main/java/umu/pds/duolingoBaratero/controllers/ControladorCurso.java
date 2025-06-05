package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.persistence.DBBloqueContenidoDAO;
import umu.pds.duolingoBaratero.persistence.DBCursoEnProgresoDAO;
import umu.pds.duolingoBaratero.persistence.DBCursoPlantillaDAO;
import umu.pds.duolingoBaratero.persistence.DBPreguntaDAO;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;
import umu.pds.duolingoBaratero.services.AudioService;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.filters.Filtro;
import umu.pds.duolingoBaratero.services.filters.FiltroBasico;
import umu.pds.duolingoBaratero.services.filters.FiltroPorNivel;
import umu.pds.duolingoBaratero.services.filters.FiltroPorNombre;
import umu.pds.duolingoBaratero.services.filters.FiltroPorPropietario;
import umu.pds.duolingoBaratero.services.serializers.Serializer;
import umu.pds.duolingoBaratero.services.serializers.SerializerFactory;
/**
 * Responsabilidad: crear, buscar y administrar cursos (alta, baja, modificación).
 */
public enum ControladorCurso {
	INSTANCE;

	private final static String VALOR_DEFAULT_FILTROS = "";
	// Estos dos atributos son de prueba hay que borrarlos
	private List<CursoPlantilla> cursosPrueba = null;
	private CursoPlantilla curso1;
	private ImageService sevicioImagenes;
	private AudioService reproductor;
	private DBCursoPlantillaDAO dbCursoPlantillaDAO;
	private DBCursoEnProgresoDAO dbCursoEnProgresoDAO;
	private DBBloqueContenidoDAO dbBloqueContenidoDAO;
	private DBPreguntaDAO dbPreguntaDAO;




	private ControladorCurso() {
		this.dbCursoPlantillaDAO = DBCursoPlantillaDAO.getDBCursoPlantillaDAO();
		this.dbPreguntaDAO = DBPreguntaDAO.getDBPreguntaDAOO();
		this.dbCursoEnProgresoDAO = DBCursoEnProgresoDAO.getDBCursoEnProgresoDAO();
		this.dbBloqueContenidoDAO = DBBloqueContenidoDAO.getDBBloqueContenidoDAO();

		this.sevicioImagenes = new ImageService();
		this.reproductor = AudioService.INSTANCE;
		this.cursosPrueba = dbCursoPlantillaDAO.getAll();
		//recuperarCursosBase();
	}

	public boolean isCursoNuevo(CursoEnProgreso curso) {
		if (curso == null) {
			System.out.println("Curso nulo");
			return false;
		}
		return curso.isNuevo();
	}
	

	public void recuperarCursosBase() {
		List<CursoPlantilla> cursos = new ArrayList<>();

		try {
			// Obtener la ruta real de la carpeta recursos/cursosBase
			URL resource = getClass().getClassLoader().getResource("cursosBase");
			if (resource == null) {
				System.err.println("No se encontró la carpeta 'resources/cursosBase'.");
			}

			File carpeta = new File(resource.toURI());
			File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".yaml"));

			if (archivos == null || archivos.length == 0) {
				System.out.println("No se encontraron archivos .yaml en cursosBase.");
			}

			for (File archivo : archivos) {
				CursoPlantilla curso = importarCurso(archivo, "yaml");
				if (curso != null) {
					cursos.add(curso);
				}
			}
			
			for (CursoPlantilla curso : cursos) {
				dbCursoPlantillaDAO.create(curso);
				for (BloqueContenido bloque : curso.getContenidos()) {
					bloque.setCurso(curso);
					dbBloqueContenidoDAO.create(bloque);
					for (Pregunta pregunta: bloque.getPreguntas()) {
						pregunta.setBloque(bloque);
						dbPreguntaDAO.create(pregunta);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		Optional<CursoPlantilla> optionalCurso = cursosPrueba.stream().filter(c -> c.getNombre().equals(nombre))
				.findFirst();
		return optionalCurso;
	}

	public String getNombrePropietario(CursoPlantilla curso) {
		return curso.getPropietario();
	}

	public CursoEnProgreso getCursoEnProgreso(String nombre, Usuario usuario) {
		Optional<CursoPlantilla> cursoPlantilla = this.getCursoPlantilla(nombre);
		if (cursoPlantilla.isPresent())
			return new CursoEnProgreso(cursoPlantilla.get(), null, usuario);
		return null;
	}

	public CursoEnProgreso getCursoEnProgreso(CursoPlantilla cursoPlantilla, AprendizajeSeleccionado aprendizajeSeleccionado, Usuario usuario) {
		CursoEnProgreso curso = new CursoEnProgreso(cursoPlantilla, aprendizajeSeleccionado, usuario);
		dbCursoEnProgresoDAO.create(curso);
		return curso;
	}

	public void guardarPreguntas(List<Pregunta> preguntas, CursoPlantilla curso) {

	}

	public void playAudio(String ruta) {
		reproductor.playAudio(ruta);
	}

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

	// ------FILTROS--------

	public List<CursoPlantilla> buscarCursos() {
		return cursosPrueba;
	}

	public List<CursoPlantilla> buscarCursos(String nombre, String propietario, Nivel lvl) {
		Filtro filtro = new FiltroBasico();
		if (nombre != VALOR_DEFAULT_FILTROS) {
			filtro = new FiltroPorNombre(filtro, nombre);
		} else if (propietario != VALOR_DEFAULT_FILTROS) {
			filtro = new FiltroPorPropietario(filtro, propietario);
		} else if (lvl != null) {
			filtro = new FiltroPorNivel(filtro, lvl);
		}
		ArrayList<CursoPlantilla> lista = (ArrayList<CursoPlantilla>) filtro.filtrar(cursosPrueba);
		return lista;
	}

	// ------RENDERIZACION PREGUNTAS--------

	public JPanel[] generarLeccion(long bloqueContenido) {
		return new JPanel[0];
	}

	public Set<Pregunta> getPreguntasDeBloqueContenido(CursoEnProgreso curso) {
		return curso.getPreguntasBloqueContenido();
	}

	public CursoPlantilla crearCurso(String nombre, String descripcion, String objetivos) {
		return new CursoPlantilla(nombre, ControladorUsuario.INSTANCE.getUsuarioActual().getNickname(), descripcion, objetivos);
	}

	public void setImagenACurso(CursoPlantilla curso, String imagen) {
		if (imagen != null) {
			curso.setImagen(imagen);
		}

	}

	// ------Procesamiento preguntas y respuestas----------

	public boolean procesarRespuesta(Pregunta pregunta, String respuestaUsuario) {
		// TODO Si la respuesta es correcta
		// Hacer algo si es falsa hacer algo
		boolean respuestaCorrecta = pregunta.esRespuestaCorrecta(respuestaUsuario);
		if (respuestaCorrecta) {

		}
		return respuestaCorrecta;
	}

	public int getNumPreguntas(long bloqueContenido) {
		return RepositorioCurso.INSTANCE.obtenerBloqueContenido(bloqueContenido).getNumPreguntas();
	}

	public void avanzarBloqueContenido(CursoEnProgreso curso, boolean aprobado) {
		curso.avanzarBloqueActual(aprobado);
	}

	public void reiniciarCurso(CursoEnProgreso curso) {
		curso.reiniciar();
	}

	public CursoPlantilla importarCurso(File archivo, String extension) {
		Serializer serializer = SerializerFactory.INSTANCE.getSerializer(extension);
		return serializer.deserialize(archivo.getAbsolutePath());
	}

	public boolean exportarCurso(CursoPlantilla curso, String extension) {
		if (curso != null) {
			Serializer serializer = SerializerFactory.INSTANCE.getSerializer(extension);
			return serializer.serialize(curso);
		}
		return false;
	}
	
	public boolean exportarCurso() {
		Serializer serializerYAML = SerializerFactory.INSTANCE.getSerializer("yaml");
		Serializer serializerJSON= SerializerFactory.INSTANCE.getSerializer("json");
		for (CursoPlantilla curso: cursosPrueba) {
			serializerYAML.serializeCursoBase(curso);
			serializerJSON.serializeCursoBase(curso);			
		}
		return true;
	}

}
