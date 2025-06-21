package umu.pds.duolingoBaratero.models;

/**
 * Enum que representa los diferentes tipos de preguntas que pueden existir en un curso.
 * Cada tipo de pregunta puede tener un formato o requerimiento específico.
 */
public enum TipoPregunta {
	
	/**
	 * Pregunta de tipo texto, donde se espera una respuesta escrita.
	 */
	FLASHCARD,
	
	/**
	 * Pregunta de tipo audio, donde se espera una respuesta basada en la escucha.
	 */
	AUDIO,
	
	/**
	 * Pregunta de tipo opciones, donde se presentan varias opciones para seleccionar la respuesta correcta.
	 */
	OPCIONES,
	
	/**
	 * Pregunta de tipo imágenes, donde se espera una respuesta relacionada con imágenes.
	 */
	IMAGENES;
}
