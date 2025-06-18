package umu.pds.duolingoBaratero.controllers;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.services.ServicioCursoProgreso;

/**
 * Controlador para manejar las operaciones relacionadas con cursos en progreso.
 */
public class ControladorCursoProgreso {

	private final ServicioCursoProgreso servicio;

	/**
	 * Constructor del controlador.
	 *
	 * @param servicio Servicio que gestiona la lógica de negocio de los cursos en progreso.
	 */
	public ControladorCursoProgreso(ServicioCursoProgreso servicio) {
		this.servicio = servicio;
	}

	/**
	 * Crea un nuevo curso en progreso para un usuario a partir de una plantilla.
	 *
	 * @param curso   Curso plantilla base.
	 * @param usuario Usuario que realizará el curso.
	 * @return Curso en progreso creado.
	 */
	public CursoEnProgreso crearCurso(CursoPlantilla curso, Usuario usuario) {
		return servicio.crearCursoEnProgreso(curso, usuario);
	}

	/**
	 * Configura un curso en progreso con el aprendizaje seleccionado e inicia el curso.
	 *
	 * @param curso       Curso en progreso a configurar.
	 * @param aprendizaje Aprendizaje a aplicar en el curso.
	 * @return true si la configuración fue exitosa, false en caso contrario.
	 */
	public boolean configurarCursoProgreso(CursoEnProgreso curso, AprendizajeSeleccionado aprendizaje) {
		servicio.iniciarCurso(curso);
		return servicio.setAprendizaje(curso, aprendizaje);
	}

	/**
	 * Verifica si un curso es nuevo.
	 *
	 * @param curso Curso en progreso.
	 * @return true si es nuevo, false si no.
	 */
	public boolean esNuevo(CursoEnProgreso curso) {
		return servicio.esCursoNuevo(curso);
	}

	/**
	 * Verifica si un curso está en marcha.
	 *
	 * @param curso Curso en progreso.
	 * @return true si está en marcha, false si no.
	 */
	public boolean estaEnMarcha(CursoEnProgreso curso) {
		return servicio.esCursoEnMarcha(curso);
	}

	/**
	 * Verifica si un curso ha finalizado.
	 *
	 * @param curso Curso en progreso.
	 * @return true si está finalizado, false si no.
	 */
	public boolean estaFinalizado(CursoEnProgreso curso) {
		return servicio.esCursoFinalizado(curso);
	}

	/**
	 * Avanza al siguiente bloque del curso, según si el bloque actual fue aprobado.
	 *
	 * @param curso    Curso en progreso.
	 * @param aprobado true si el bloque actual fue aprobado, false si no.
	 */
	public void avanzar(CursoEnProgreso curso, boolean aprobado) {
		servicio.avanzarBloque(curso, aprobado);
	}

	/**
	 * Reinicia el curso desde el principio y lo actualiza.
	 *
	 * @param curso Curso en progreso a reiniciar.
	 */
	public void reiniciar(CursoEnProgreso curso) {
		servicio.reiniciarCurso(curso);
		actualizarCurso(curso);
	}

	/**
	 * Actualiza un curso en progreso.
	 *
	 * @param curso Curso a actualizar.
	 * @return true si la actualización fue exitosa, false si ocurrió un error.
	 */
	public boolean actualizarCurso(CursoEnProgreso curso) {
		try {
			return servicio.actualizarCurso(curso);
		} catch (Exception e) {
			System.err.println("Error al actualizar cursoProgreso: " + e.getMessage());
			return false;
		}
	}
}
