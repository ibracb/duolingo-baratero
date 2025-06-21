package umu.pds.duolingoBaratero.persistence;

/**
 * Excepción que se lanza cuando ocurre un error en la capa de acceso a datos (DAO).
 */
@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

	/**
	 * Constructor que recibe un mensaje de error.
	 * 
	 * @param message Mensaje de error.
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
}