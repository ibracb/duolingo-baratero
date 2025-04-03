package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BloqueContenidoProgreso {
	
	private CursoEnProgreso cursoEnProgreso;
	private BloqueContenido bloqueContenido;
	private EstadoBloqueContenido estado;
	private List<PreguntaProgreso> preguntasProgreso;
	
	public BloqueContenidoProgreso(CursoEnProgreso cursoEnProgreso, BloqueContenido bloqueContenido) {
		this.cursoEnProgreso = cursoEnProgreso;
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
