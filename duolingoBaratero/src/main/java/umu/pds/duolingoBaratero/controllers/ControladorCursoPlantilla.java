package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.services.AudioService;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.ServicioCursoPlantilla;
import umu.pds.duolingoBaratero.services.ServicioUsuario;

/**
 * Controlador para la gestión de cursos plantilla. Responsabilidades: -
 * Coordinar las operaciones entre la vista y el servicio - Gestionar servicios
 * de UI (imágenes, audio) - Proporcionar una interfaz simplificada para la capa
 * de presentación - Manejar la inicialización y configuración del sistema
 */
public class ControladorCursoPlantilla {

	private final ServicioCursoPlantilla servicioCursoPlantilla;
	private final ImageService servicioImagenes;
	private final AudioService reproductor;
	private final ServicioUsuario servicioUsuario;

	// Constructor con inyección de dependencias
	public ControladorCursoPlantilla(ServicioCursoPlantilla servicioCursoPlantilla, ImageService servicioImagenes,
			AudioService reproductor, ServicioUsuario servicioUsuario) {
		this.servicioCursoPlantilla = servicioCursoPlantilla;
		this.servicioImagenes = servicioImagenes;
		this.reproductor = reproductor;
		this.servicioUsuario = servicioUsuario;

		// Inicializar cursos base al arrancar el sistema
		inicializarSistema();
	}

	/**
	 * Inicializa el sistema cargando cursos base
	 */
	private void inicializarSistema() {
		try {
			servicioCursoPlantilla.cargarCursosBase();
		} catch (Exception e) {
			System.err.println("Error al inicializar cursos base: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// ============================================
	// MÉTODOS DE COORDINACIÓN CON EL SERVICIO
	// ============================================

	/**
	 * Obtiene un curso plantilla por nombre
	 */
	public Optional<CursoPlantilla> getCursoPlantilla(String nombre) {
		try {
			return servicioCursoPlantilla.buscarCursoPorNombre(nombre);
		} catch (Exception e) {
			System.err.println("Error al buscar curso: " + e.getMessage());
			return Optional.empty();
		}
	}

	/**
	 * Obtiene el nombre del propietario de un curso
	 */
	public String getNombrePropietario(CursoPlantilla curso) {
		if (curso == null) {
			return "";
		}
		return curso.getPropietario();
	}

	/**
	 * Crea un curso en progreso simple
	 */
	public CursoEnProgreso getCursoEnProgreso(String nombre, Usuario usuario) {
		try {
			return servicioCursoPlantilla.crearCursoEnProgreso(nombre, usuario);
		} catch (Exception e) {
			System.err.println("Error al crear curso en progreso: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Crea un curso en progreso con aprendizaje seleccionado
	 */
	public CursoEnProgreso getCursoEnProgreso(CursoPlantilla cursoPlantilla,

			Usuario usuario) {
		try {
			return servicioCursoPlantilla.crearCursoEnProgreso(cursoPlantilla, usuario);
		} catch (Exception e) {
			System.err.println("Error al crear curso en progreso: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Busca todos los cursos disponibles
	 */
	public List<CursoPlantilla> buscarCursos() {
		try {
			return servicioCursoPlantilla.obtenerTodosLosCursos();
		} catch (Exception e) {
			System.err.println("Error al buscar cursos: " + e.getMessage());
			return List.of(); // Lista vacía en caso de error
		}
	}

	/**
	 * Busca cursos con filtros específicos
	 */
	public List<CursoPlantilla> buscarCursos(String nombre, String propietario, Nivel nivel) {
		try {
			return servicioCursoPlantilla.buscarCursosConFiltros(nombre, propietario, nivel);
		} catch (Exception e) {
			System.err.println("Error al buscar cursos con filtros: " + e.getMessage());
			return List.of(); // Lista vacía en caso de error
		}
	}

	/**
	 * Crea un nuevo curso plantilla
	 */
	public CursoPlantilla crearCurso(String nombre, String descripcion, String objetivos) {
		try {
			String propietario = servicioUsuario.getUsuarioActual().getNickname();
			return servicioCursoPlantilla.crearCursoPlantilla(nombre, propietario, descripcion, objetivos);
		} catch (Exception e) {
			System.err.println("Error al crear curso: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Asigna una imagen a un curso
	 */
	public boolean setImagenACurso(CursoPlantilla curso, String rutaImagen) {
		try {
			servicioCursoPlantilla.actualizarImagenCurso(curso, rutaImagen);
			return true;
		} catch (Exception e) {
			System.err.println("Error al asignar imagen al curso: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Importa un curso desde archivo
	 */
	public CursoPlantilla importarCurso(File archivo, String extension) {
		try {
			return servicioCursoPlantilla.importarCurso(archivo, extension);
		} catch (Exception e) {
			System.err.println("Error al importar curso: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Exporta un curso específico
	 */
	public boolean exportarCurso(CursoPlantilla curso, String extension) {
		try {
			return servicioCursoPlantilla.exportarCurso(curso, extension);
		} catch (Exception e) {
			System.err.println("Error al exportar curso: " + e.getMessage());
			return false;
		}
	}


	/**
	 * Elimina un curso
	 */
	public boolean eliminarCurso(String nombreCurso) {
		try {
			return servicioCursoPlantilla.eliminarCurso(nombreCurso);
		} catch (Exception e) {
			System.err.println("Error al eliminar curso: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Actualiza un curso
	 */
	public boolean actualizarCurso(CursoPlantilla curso) {
		try {
			return servicioCursoPlantilla.actualizarCurso(curso);
		} catch (Exception e) {
			System.err.println("Error al actualizar curso: " + e.getMessage());
			return false;
		}
	}

	// ============================================
	// SERVICIOS DE UI - AUDIO
	// ============================================

	/**
	 * Reproduce un archivo de audio
	 */
	public void playAudio(String ruta) {
		try {
			if (ruta != null && !ruta.trim().isEmpty()) {
				reproductor.playAudio(ruta);
			}
		} catch (Exception e) {
			System.err.println("Error al reproducir audio: " + e.getMessage());
		}
	}

	// ============================================
	// SERVICIOS DE UI - IMÁGENES
	// ============================================

	/**
	 * Escala una imagen BufferedImage
	 */
	public ImageIcon getScaledImage(BufferedImage bufferedImage, int dimensiones) {
		try {
			if (bufferedImage != null && dimensiones > 0) {
				return servicioImagenes.getScaledImage(bufferedImage, dimensiones);
			}
			return getScaledDefaultImage(dimensiones);
		} catch (Exception e) {
			System.err.println("Error al escalar imagen: " + e.getMessage());
			return getScaledDefaultImage(dimensiones);
		}
	}

	/**
	 * Escala una ImageIcon
	 */
	public ImageIcon getScaledImage(ImageIcon image, int dimensiones) {
		try {
			if (image != null && dimensiones > 0) {
				return servicioImagenes.getScaledImage(image, dimensiones);
			}
			return getScaledDefaultImage(dimensiones);
		} catch (Exception e) {
			System.err.println("Error al escalar imagen: " + e.getMessage());
			return getScaledDefaultImage(dimensiones);
		}
	}

	/**
	 * Obtiene una imagen por defecto escalada
	 */
	public ImageIcon getScaledDefaultImage(int dimensiones) {
		try {
			ImageIcon image = new ImageIcon(getClass().getResource("/persona.png"));
			return servicioImagenes.getScaledImage(image, dimensiones);
		} catch (Exception e) {
			System.err.println("Error al obtener imagen por defecto: " + e.getMessage());
			// Retornar una imagen vacía como fallback
			return new ImageIcon();
		}
	}

	// ============================================
	// MÉTODOS DE UTILERÍA
	// ============================================

	/**
	 * Recarga los cursos base (útil para testing o reset)
	 */
	public void recargarCursosBase() {
		try {
			servicioCursoPlantilla.cargarCursosBase();
		} catch (Exception e) {
			System.err.println("Error al recargar cursos base: " + e.getMessage());
		}
	}
	
	

	/**
	 * Obtiene el servicio de cursos para operaciones avanzadas (Solo para casos
	 * especiales, generalmente no debería usarse desde la UI)
	 */
	protected ServicioCursoPlantilla getServicioCursoPlantilla() {
		return servicioCursoPlantilla;
	}
}