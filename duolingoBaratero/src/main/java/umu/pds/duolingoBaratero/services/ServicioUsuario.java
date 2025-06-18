package umu.pds.duolingoBaratero.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.persistence.DBUsuarioDAO;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;

/**
 * Servicio que gestiona la lógica y operaciones relacionadas con el usuario.
 */
public class ServicioUsuario {

	private final DBUsuarioDAO dbUsuarioDAO;
	private final ServicioCursoProgreso servicioCursoProgreso;
	private final ServicioCursoPlantilla servicioCursoPlantilla;
	private final ServicioEstadistica servicioEstadistica;

	private Usuario user;

	/**
	 * Constructor del servicio de usuario.
	 * 
	 * @param dbUsuarioDAO          DAO para acceso a datos de usuario.
	 * @param servicioCursoProgreso Servicio para gestionar cursos en progreso.
	 * @param servicioCursoPlantilla Servicio para gestionar plantillas de curso.
	 * @param servicioEstadistica   Servicio para gestionar estadísticas del usuario.
	 */
	public ServicioUsuario(DBUsuarioDAO dbUsuarioDAO, ServicioCursoProgreso servicioCursoProgreso,
			ServicioCursoPlantilla servicioCursoPlantilla, ServicioEstadistica servicioEstadistica) {
		this.dbUsuarioDAO = dbUsuarioDAO;
		this.servicioCursoProgreso = servicioCursoProgreso;
		this.servicioCursoPlantilla = servicioCursoPlantilla;
		this.servicioEstadistica = servicioEstadistica;
	}

	/**
	 * Registra un nuevo usuario en el sistema.
	 * 
	 * @param nombre     Nombre del usuario.
	 * @param apellidos  Apellidos del usuario.
	 * @param correo     Correo electrónico (identificador).
	 * @param contrasena Contraseña.
	 * @return true si se registró correctamente, false si el usuario ya existe.
	 */
	public boolean registrarUsuario(String nombre, String apellidos, String correo, String contrasena) {
		if (dbUsuarioDAO.existeUsuario(correo))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, correo, contrasena);
		dbUsuarioDAO.create(usuario);
		this.user = usuario;
		servicioEstadistica.inicializarEstadistica(user.getEstadistica());
		return true;
	}

	/**
	 * Comprueba si existe un usuario con el correo y contraseña dados.
	 * 
	 * @param correo Correo electrónico.
	 * @param passwd Contraseña.
	 * @return true si el usuario existe y las credenciales son válidas.
	 */
	public boolean comprobarUsuario(String correo, String passwd) {
		Usuario usuario = dbUsuarioDAO.get(correo);
		if (usuario != null) {
			this.user = usuario;
			servicioEstadistica.inicializarEstadistica(user.getEstadistica());
			return true;
		}
		return false;
	}

	/**
	 * Obtiene el usuario actualmente logueado.
	 * 
	 * @return Usuario actual o null si no hay sesión activa.
	 */
	public Usuario getUsuarioActual() {
		return user;
	}

	/**
	 * Obtiene el nombre del usuario actual.
	 * 
	 * @return Nombre del usuario o null si no hay sesión activa.
	 */
	public String getNombreUsuarioActual() {
		return user != null ? user.getNombre() : null;
	}

	/**
	 * Cierra la sesión actual.
	 */
	public void logOut() {
		user = null;
	}

	/**
	 * Establece la imagen de perfil del usuario actual.
	 * 
	 * @param image Imagen en formato String (base64 o path).
	 */
	public void setImagen(String image) {
		if (user != null) {
			user.setImagen(image);
		}
	}

	/**
	 * Establece los cursos actuales del usuario según nombres.
	 * 
	 * @param nombresCursos Array de nombres de cursos.
	 */
	public void setCursos(String[] nombresCursos) {
		if (user == null)
			return;

		Set<CursoEnProgreso> cursos = new HashSet<>();
		for (String nombre : nombresCursos) {
			servicioCursoPlantilla.buscarCursoPorNombre(nombre)
					.ifPresent(plantilla -> cursos.add(servicioCursoProgreso.crearCursoEnProgreso(plantilla, user)));
		}
		user.setCursos(cursos);
	}

	/**
	 * Añade un curso en progreso al usuario actual.
	 * 
	 * @param curso Plantilla del curso a añadir.
	 * @return true si se añadió correctamente, false si no.
	 */
	public boolean addCursosEnProgreso(CursoPlantilla curso) {
		if (user == null)
			return false;

		CursoEnProgreso cursoProgreso = servicioCursoPlantilla.crearCursoEnProgreso(curso, user);
		boolean resultado = user.addCursoEnProgreso(cursoProgreso);
		if (resultado) {
			RepositorioCurso.INSTANCE.agregarCursoEnProgreso(cursoProgreso);
		}
		return resultado;
	}

	/**
	 * Comprueba si el usuario está cursando un curso específico.
	 * 
	 * @param curso Curso a comprobar.
	 * @return true si el usuario está cursando el curso.
	 */
	public boolean estaCursando(CursoPlantilla curso) {
		return user != null && user.estaCursando(curso);
	}

	/**
	 * Añade una nueva plantilla de curso al sistema.
	 * 
	 * @param nombre      Nombre del curso.
	 * @param objetivos   Objetivos del curso.
	 * @param descripcion Descripción del curso.
	 * @return true si la operación fue exitosa (implementación pendiente).
	 */
	public boolean addCursoPlantilla(String nombre, String objetivos, String descripcion) {
		// TODO
		return true;
	}

	/**
	 * Obtiene los cursos en progreso del usuario actual.
	 * 
	 * @return Set de cursos en progreso o null si no hay usuario activo.
	 */
	public Set<CursoEnProgreso> getCursosUsuarioActual() {
		return user != null ? user.getCursos() : null;
	}

	/**
	 * Elimina un curso en progreso del usuario actual.
	 * 
	 * @param curso Curso a eliminar.
	 */
	public void borrarCurso(CursoEnProgreso curso) {
		if (user != null) {
			user.eliminarCurso(curso);
		}
	}

	/**
	 * Quita una vida al usuario actual.
	 * 
	 * @return Número de vidas restantes.
	 */
	public int quitarVida() {
		return user.perderVida();
	}

	/**
	 * Intenta recuperar vidas para el usuario actual.
	 * 
	 * @return true si se recuperó vida.
	 */
	public boolean recuperarVida() {
		return user.recuperarVidas();
	}

	/**
	 * Obtiene el número actual de vidas del usuario.
	 * 
	 * @return Número de vidas.
	 */
	public int getVidasUsuario() {
		this.recuperarVida();
		return user.getVidas();
	}

	/**
	 * Obtiene la fecha y hora de la última recuperación de vidas.
	 * 
	 * @return LocalDateTime de la última recuperación.
	 */
	public LocalDateTime getUltimaRecuperacion() {
		return user.getUltimaRecuperacion();
	}

	/**
	 * Actualiza el usuario en la base de datos.
	 */
	public void actualizarUsuario() {
		dbUsuarioDAO.update(user);
	}
}
