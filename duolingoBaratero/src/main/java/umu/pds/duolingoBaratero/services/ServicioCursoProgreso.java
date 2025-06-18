package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.persistence.DBCursoEnProgresoDAO;

/**
 * Servicio para gestionar la lógica de cursos en progreso.
 */
public class ServicioCursoProgreso {

	private DBCursoEnProgresoDAO dbCursoEnProgresoDAO;

	/**
	 * Constructor del servicio.
	 * 
	 * @param dbCursoEnProgresoDAO DAO para persistencia de cursos en progreso.
	 */
	public ServicioCursoProgreso(DBCursoEnProgresoDAO dbCursoEnProgresoDAO) {
		super();
		this.dbCursoEnProgresoDAO = dbCursoEnProgresoDAO;
	}

	/**
	 * Crea una nueva instancia de curso en progreso para un usuario y plantilla.
	 * 
	 * @param curso   Plantilla del curso.
	 * @param usuario Usuario que cursa.
	 * @return CursoEnProgreso creado.
	 */
	public CursoEnProgreso crearCursoEnProgreso(CursoPlantilla curso, Usuario usuario) {
		return new CursoEnProgreso(curso, usuario);
	}

	/**
	 * Asigna un aprendizaje seleccionado a un curso en progreso.
	 * 
	 * @param curso       Curso en progreso.
	 * @param aprendizaje Aprendizaje seleccionado a asignar.
	 * @return true si se asignó correctamente.
	 */
	public boolean setAprendizaje(CursoEnProgreso curso, AprendizajeSeleccionado aprendizaje) {
		curso.setAprendizaje(aprendizaje);
		return true;
	}

	/**
	 * Inicia un curso en progreso.
	 * 
	 * @param curso Curso a iniciar.
	 */
	public void iniciarCurso(CursoEnProgreso curso) {
		curso.iniciar();
	}

	/**
	 * Indica si un curso está en estado nuevo.
	 * 
	 * @param curso Curso a evaluar.
	 * @return true si es nuevo, false si no o es null.
	 */
	public boolean esCursoNuevo(CursoEnProgreso curso) {
		return curso != null && curso.isNuevo();
	}

	/**
	 * Indica si un curso está en marcha.
	 * 
	 * @param curso Curso a evaluar.
	 * @return true si está en marcha, false si no o es null.
	 */
	public boolean esCursoEnMarcha(CursoEnProgreso curso) {
		return curso != null && curso.isEnMarcha();
	}

	/**
	 * Indica si un curso está finalizado.
	 * 
	 * @param curso Curso a evaluar.
	 * @return true si está finalizado, false si no o es null.
	 */
	public boolean esCursoFinalizado(CursoEnProgreso curso) {
		return curso != null && curso.isFinalizado();
	}

	/**
	 * Avanza el bloque actual del curso, registrando si se aprobó o no.
	 * 
	 * @param curso    Curso a avanzar.
	 * @param aprobado true si aprobó el bloque actual.
	 */
	public void avanzarBloque(CursoEnProgreso curso, boolean aprobado) {
		if (curso != null) {
			curso.avanzarBloqueActual(aprobado);
		}
	}

	/**
	 * Reinicia un curso en progreso.
	 * 
	 * @param curso Curso a reiniciar.
	 */
	public void reiniciarCurso(CursoEnProgreso curso) {
		curso.reiniciar();
	}

	/**
	 * Actualiza la información del curso en la base de datos.
	 * 
	 * @param curso Curso a actualizar.
	 * @return true si se actualizó correctamente, false si el curso es null.
	 */
	public boolean actualizarCurso(CursoEnProgreso curso) {
		if (curso != null) {
			dbCursoEnProgresoDAO.update(curso);
			return true;
		}
		return false;
	}
}
