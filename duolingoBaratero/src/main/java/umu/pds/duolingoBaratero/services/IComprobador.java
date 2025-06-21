package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.models.Pregunta;

/**
 * Interfaz para comprobar respuestas de preguntas.
 * Proporciona métodos para obtener la respuesta del usuario, verificar si una opción fue elegida y obtener la pregunta asociada.
 */
public interface IComprobador {
    
		/**
	 * Obtiene la respuesta proporcionada por el usuario.
	 *
	 * @return La respuesta del usuario como una cadena de texto.
	 */
    String getRespuestaUsuario();
    
    /**
	 * Verifica si el usuario ha elegido una opción.
	 *
	 * @return true si se ha elegido una opción, false en caso contrario.
	 */
    boolean isOpcionElegida();
    
    /**
     * Devuelve la pregunta asociada al comprobador.
     * @return la pregunta que se está comprobando.
     */
    Pregunta getPregunta();
}