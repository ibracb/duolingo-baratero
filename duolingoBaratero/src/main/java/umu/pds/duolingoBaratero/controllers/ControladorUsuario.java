package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Set;

import javax.swing.ImageIcon;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.ServicioUsuario;

/**
 * Controlador para la gestión de usuarios y sus imágenes.
 */
public class ControladorUsuario {

	private final ServicioUsuario servicioUsuario;
	private final ImageService servicioImagenes;

	/**
	 * Constructor del controlador.
	 * 
	 * @param servicioUsuario  Servicio de lógica para usuarios.
	 * @param servicioImagenes Servicio para manipulación de imágenes.
	 */
	public ControladorUsuario(ServicioUsuario servicioUsuario, ImageService servicioImagenes) {
		this.servicioUsuario = servicioUsuario;
		this.servicioImagenes = servicioImagenes;
	}

	/**
	 * Registra un nuevo usuario.
	 * 
	 * @param nombre     Nombre del usuario.
	 * @param apellidos  Apellidos del usuario.
	 * @param correo     Correo electrónico.
	 * @param contrasena Contraseña.
	 * @return true si el registro fue exitoso, false si no.
	 */
	public boolean registrarUsuario(String nombre, String apellidos, String correo, String contrasena) {
		return servicioUsuario.registrarUsuario(nombre, apellidos, correo, contrasena);
	}

	/**
	 * Comprueba credenciales de usuario.
	 * 
	 * @param correo Correo del usuario.
	 * @param passwd Contraseña.
	 * @return true si las credenciales son válidas, false si no.
	 */
	public boolean comprobarUsuario(String correo, String passwd) {
		return servicioUsuario.comprobarUsuario(correo, passwd);
	}

	// ----------------------------------------------
	// Funciones usuarioActual
	// ----------------------------------------------

	/**
	 * Obtiene el usuario actualmente autenticado.
	 * 
	 * @return Usuario actual.
	 */
	public Usuario getUsuarioActual() {
		return servicioUsuario.getUsuarioActual();
	}

	/**
	 * Obtiene el nombre del usuario actual.
	 * 
	 * @return Nombre del usuario actual.
	 */
	public String getNombreUsuarioActual() {
		return servicioUsuario.getNombreUsuarioActual();
	}

	/**
	 * Cierra la sesión del usuario actual.
	 */
	public void logOut() {
		servicioUsuario.logOut();
	}

	/**
	 * Establece la imagen del usuario actual.
	 * 
	 * @param image Ruta o cadena de la imagen.
	 */
	public void setImagen(String image) {
		servicioUsuario.setImagen(image);
	}

	/**
	 * Establece los cursos del usuario actual.
	 * 
	 * @param nombresCursos Array de nombres de cursos.
	 */
	public void setCursos(String[] nombresCursos) {
		servicioUsuario.setCursos(nombresCursos);
	}

	/**
	 * Añade un curso en progreso al usuario actual.
	 * 
	 * @param curso Curso plantilla a añadir.
	 * @return true si se añadió correctamente, false si no.
	 */
	public boolean addCursosEnProgreso(CursoPlantilla curso) {
		return servicioUsuario.addCursosEnProgreso(curso);
	}

	/**
	 * Comprueba si el usuario actual está cursando un curso dado.
	 * 
	 * @param curso Curso plantilla.
	 * @return true si lo está cursando, false si no.
	 */
	public boolean estaCursando(CursoPlantilla curso) {
		return servicioUsuario.estaCursando(curso);
	}

	/**
	 * Añade una nueva plantilla de curso.
	 * 
	 * @param nombre      Nombre del curso.
	 * @param objetivos   Objetivos del curso.
	 * @param descripcion Descripción del curso.
	 * @return true si fue añadido correctamente, false si no.
	 */
	public boolean addCursoPlantilla(String nombre, String objetivos, String descripcion) {
		return servicioUsuario.addCursoPlantilla(nombre, objetivos, descripcion);
	}

	/**
	 * Obtiene los cursos en progreso del usuario actual.
	 * 
	 * @return Set de cursos en progreso.
	 */
	public Set<CursoEnProgreso> getCursosUsuarioActual() {
		return servicioUsuario.getCursosUsuarioActual();
	}

	// ----------------------------------------------
	// Funciones imagenes
	// ----------------------------------------------

	/**
	 * Obtiene una imagen escalada a las dimensiones dadas.
	 * 
	 * @param bufferedImage Imagen original.
	 * @param dimensiones   Tamaño al que se quiere escalar.
	 * @return Imagen escalada como ImageIcon.
	 */
	public ImageIcon getScaledImage(BufferedImage bufferedImage, int dimensiones) {
		return servicioImagenes.getScaledImage(bufferedImage, dimensiones);
	}

	/**
	 * Obtiene una imagen escalada a las dimensiones dadas.
	 * 
	 * @param image      Imagen original como ImageIcon.
	 * @param dimensiones Tamaño al que se quiere escalar.
	 * @return Imagen escalada como ImageIcon.
	 */
	public ImageIcon getScaledImage(ImageIcon image, int dimensiones) {
		return servicioImagenes.getScaledImage(image, dimensiones);
	}

	/**
	 * Obtiene una imagen por defecto escalada.
	 * 
	 * @param dimensiones Tamaño deseado.
	 * @return Imagen por defecto escalada como ImageIcon.
	 */
	public ImageIcon getScaledDefaultImage(int dimensiones) {
		ImageIcon image = new ImageIcon(getClass().getResource("/persona.png"));
		return servicioImagenes.getScaledImage(image, dimensiones);
	}

	/**
	 * Borra un curso en progreso del usuario.
	 * 
	 * @param curso Curso a borrar.
	 */
	public void borrarCurso(CursoEnProgreso curso) {
		servicioUsuario.borrarCurso(curso);
	}

	/**
	 * Resta una vida al usuario actual.
	 * 
	 * @return Número de vidas restantes después de restar.
	 */
	public int restarVidaUsuario() {
		int vidas = servicioUsuario.quitarVida();
		actualizarUsuario();
		return vidas;
	}

	/**
	 * Obtiene el número de vidas del usuario actual.
	 * 
	 * @return Número de vidas.
	 */
	public int getVidasUsuario() {
		return servicioUsuario.getVidasUsuario();
	}

	/**
	 * Intenta recuperar una vida para el usuario actual.
	 * 
	 * @return true si se recuperó vida, false si no.
	 */
	public boolean recuperarVida() {
		boolean resultado = servicioUsuario.recuperarVida();
		actualizarUsuario();
		return resultado;
	}

	/**
	 * Obtiene la fecha y hora de la última recuperación de vida.
	 * 
	 * @return Fecha y hora de última recuperación.
	 */
	public LocalDateTime getUltimaRecuperacion() {
		return servicioUsuario.getUltimaRecuperacion();
	}

	/**
	 * Actualiza el usuario actual en la base de datos.
	 */
	public void actualizarUsuario() {
		try {
			servicioUsuario.actualizarUsuario();
		} catch (Exception e) {
			System.err.println("Error al actualizar usuario: " + e.getMessage());
		}
	}
}
