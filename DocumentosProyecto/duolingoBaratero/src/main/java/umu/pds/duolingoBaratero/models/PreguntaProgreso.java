package umu.pds.duolingoBaratero.models;

public class PreguntaProgreso {
	
	private Pregunta pregunta;
	private EstadoPregunta estado;
	
	public PreguntaProgreso(Pregunta pregunta) {
		this.pregunta = pregunta;
		this.estado = EstadoPregunta.PENDIENTE;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public EstadoPregunta getEstado() {
		return estado;
	}

	public void setEstado(EstadoPregunta estado) {
		this.estado = estado;
	}
	
}
