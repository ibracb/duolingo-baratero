package umu.pds.duolingoBaratero.models;

public enum Nivel {

	BASICO(0),
	PRINCIPIANTE(1),
	INTERMEDIO(2),
	AVANZADO(3);
	
	private final int valor;
	
	Nivel(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static Nivel fromValor(int valor) {
		for (Nivel n : values()) {
			if (n.getValor() == valor) return n;
		}
		return BASICO;
	}
	
}
