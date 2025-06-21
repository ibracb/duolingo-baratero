package umu.pds.duolingoBaratero.models;

/**
 * Enum que representa los niveles de un curso.
 * Cada nivel tiene un valor asociado que indica su dificultad relativa.
 */
public enum Nivel {

	/**
	 * Nivel básico, con el valor 0.
	 * Representa el nivel más bajo de dificultad.
	 */
	BASICO(0),
	
	/**
	 * Nivel principiante, con el valor 1.
	 * Representa un nivel de dificultad ligeramente superior al básico.
	 */
	PRINCIPIANTE(1),
	
	/**
	 * Nivel intermedio, con el valor 2.
	 * Representa un nivel de dificultad medio.
	 */
	INTERMEDIO(2),
	
	/**
	 * Nivel avanzado, con el valor 3.
	 * Representa el nivel más alto de dificultad.
	 */
	AVANZADO(3);
	
	/** Valor asociado al nivel. */
	private final int valor;
	
	/**
	 * Constructor para inicializar el valor del nivel.
	 * 
	 * @param valor El valor asociado al nivel.
	 */
	Nivel(int valor) {
		this.valor = valor;
	}

	/**
	 * Obtiene el valor asociado al nivel.
	 * 
	 * @return El valor del nivel.
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Obtiene el nivel correspondiente al valor dado.
	 * Si el valor no coincide con ningún nivel, se devuelve BASICO por defecto.
	 * 
	 * @param valor El valor del nivel a buscar.
	 * @return El nivel correspondiente al valor, o BASICO si no se encuentra.
	 */
	public static Nivel fromValor(int valor) {
		for (Nivel n : values()) {
			if (n.getValor() == valor) return n;
		}
		return BASICO;
	}
	
}
