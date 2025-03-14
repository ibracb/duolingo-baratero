package umu.pds.duolingoBaratero.models;

public enum Valoracion {
	
	CERO(0),
	UNO(1),
	DOS(2),
	TRES(3),
	CUATRO(4),
	CINCO(5);
	
	private final int valor;
	
	Valoracion(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
	
}
