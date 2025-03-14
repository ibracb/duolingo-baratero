package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CursoEnProgreso {
	
	private Usuario estudiante;
	private CursoPlantilla cursoPlantilla;
	private Aprendizaje aprendizaje;
	private List<BloqueContenidoProgreso> contenidosProgreso;
	private EstadoCursoEnProgreso estado;
	
	public CursoEnProgreso(Usuario usuario, CursoPlantilla cursoPlantilla, Aprendizaje aprendizaje, BloqueContenidoProgreso...contenidosProgreso) {
		this.estudiante = usuario;
		this.cursoPlantilla = cursoPlantilla;
		this.aprendizaje = aprendizaje;
		setEstado(new EstadoNuevo(this));
		Collections.addAll(this.contenidosProgreso, contenidosProgreso);
	}

	public String getNombre() {
		return cursoPlantilla.getNombre();
	}

	public String getDescripcion() {
		return cursoPlantilla.getDescripcion();
	}

	public String getObjetivos() {
		return cursoPlantilla.getObjetivos();
	}

	public Nivel getNivel() {
		return cursoPlantilla.getNivel();
	}

	public Usuario getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Usuario estudiante) {
		this.estudiante = estudiante;
	}

	public CursoPlantilla getCursoPlantilla() {
		return cursoPlantilla;
	}

	public void setCursoPlantilla(CursoPlantilla cursoPlantilla) {
		this.cursoPlantilla = cursoPlantilla;
	}

	public Aprendizaje getAprendizaje() {
		return aprendizaje;
	}

	public void setAprendizaje(Aprendizaje aprendizaje) {
		this.aprendizaje = aprendizaje;
	}
	
	public List<PreguntaProgreso> getPreguntas() {
		List<BloqueContenidoProgreso> listaContenidos = getContenidosProgreso();
		List<PreguntaProgreso> listaPreguntas = new LinkedList<>();
		switch(this.aprendizaje) {
			case ALEATORIO:
				listaPreguntas.add((PreguntaProgreso) listaContenidos.get(0).getBloqueContenido().getPreguntasAleatoriamente());
				break;
			case SECUENCIAL:
				listaPreguntas.add((PreguntaProgreso) listaContenidos.get(0).getBloqueContenido().getPreguntasSecuencialmente());
				break;
			default:
				break;
		}
		return listaPreguntas;
		
	}
	
	public List<BloqueContenidoProgreso> getContenidosProgreso() {
		return Collections.unmodifiableList(contenidosProgreso);
	}

	public EstadoCursoEnProgreso getEstado() {
		return estado;
	}

	public void setEstado(EstadoCursoEnProgreso estado) {
		this.estado = estado;
	}
	
	public void iniciar() {
		estado.iniciar(this);
	}
	
	public void finalizar() {
		estado.finalizar(this);
	}
}
