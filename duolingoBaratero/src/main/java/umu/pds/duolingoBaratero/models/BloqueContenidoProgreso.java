package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BloqueContenidoProgreso {
	
	private BloqueContenido bloqueContenido;
	private EstadoBloqueContenido estado;
	private List<PreguntaProgreso> preguntasProgreso;
	private CursoEnProgreso cursoEnProgreso;
	
	public BloqueContenidoProgreso(CursoEnProgreso cursoEnprogreso, BloqueContenido bloqueContenido) {
		this.cursoEnProgreso = cursoEnprogreso;
		this.bloqueContenido = bloqueContenido;
		this.estado = EstadoBloqueContenido.INICIO;
	}

	public CursoEnProgreso getCursoEnProgreso() {
		return cursoEnProgreso;
	}

	public void setCursoEnProgreso(CursoEnProgreso cursoEnProgreso) {
		this.cursoEnProgreso = cursoEnProgreso;
	}

	public BloqueContenido getBloqueContenido() {
		return bloqueContenido;
	}

	public void setBloqueContenido(BloqueContenido bloqueContenido) {
		this.bloqueContenido = bloqueContenido;
	}

	public EstadoBloqueContenido getEstado() {
		return estado;
	}

	public void setEstado(EstadoBloqueContenido estado) {
		this.estado = estado;
	}

	public List<PreguntaProgreso> getPreguntasProgreso() {
		return preguntasProgreso;
	}

	public void setPreguntasProgreso(List<PreguntaProgreso> preguntasProgreso) {
		this.preguntasProgreso = preguntasProgreso;
	}
	
	public Set<PreguntaProgreso> getPreguntasSecuencialmente() {
		return Collections.unmodifiableSet(new HashSet<>(preguntasProgreso));
	}
	
	public List<PreguntaProgreso> getPreguntasAleatoriamente() {
		List<PreguntaProgreso> preguntasAleatorias = new LinkedList<>(preguntasProgreso);
		Collections.shuffle(preguntasAleatorias);
		return preguntasAleatorias;
	}

}
