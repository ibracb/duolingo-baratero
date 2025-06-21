package umu.pds.duolingoBaratero.models;

/**
 * Enum que representa los estados posibles de un curso en progreso.
 * Los estados son:
 * - NUEVO: Curso recién creado, aún no iniciado.
 * - EN_MARCHA: Curso en progreso, se están realizando lecciones.
 * - FINALIZADO: Curso completado, todas las lecciones han sido realizadas.
 */
public enum EstadoCursoEnProgreso {
	
	/**
	 * Estado inicial del curso, indica que el curso ha sido creado pero aún no se ha comenzado.
	 */
	NUEVO, 
	
	/**
	 * Estado que indica que el curso está en progreso, se están realizando lecciones.
	 */
	EN_MARCHA,
	
	/**
	 * Estado final del curso, indica que todas las lecciones han sido completadas.
	 */
	FINALIZADO
}
