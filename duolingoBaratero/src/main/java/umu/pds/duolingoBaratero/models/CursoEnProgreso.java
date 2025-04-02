package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CursoEnProgreso {
	
	private Usuario estudiante;
	private CursoPlantilla cursoPlantilla;
	private Aprendizaje aprendizaje;
	private List<BloqueContenidoProgreso> contenidosProgreso;
	private EstadoCursoEnProgreso estado;
	private Valoracion valoracion;
	
	public CursoEnProgreso(Usuario usuario, CursoPlantilla cursoPlantilla, Aprendizaje aprendizaje, Valoracion valoracion, BloqueContenidoProgreso...contenidosProgreso) {
		this.estudiante = usuario;
		this.cursoPlantilla = cursoPlantilla;
		this.aprendizaje = aprendizaje;
		setEstado(new EstadoNuevo(this));
		setValoracion(valoracion);
		this.contenidosProgreso = new LinkedList<>();
		if (contenidosProgreso != null) {
			for (BloqueContenidoProgreso bloque : contenidosProgreso) {
				this.contenidosProgreso.add(bloque);
			}
		}
	
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
	
	public Valoracion getValoracion() {
		return valoracion;
	}
	
	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}
	
	public int getValoracionNumerica() {
		return getValoracion().getValor();
	}
	
    public List<Pregunta> getPreguntasBloqueContenido(long bloqueContenidoProgreso) {
		return cursoPlantilla.getPreguntasDeBloque(bloqueContenidoProgreso);
	}

	public List<PreguntaProgreso> getTodasLasPreguntas() {
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
	
	public boolean isNuevo() {
		return estado instanceof EstadoNuevo;
	}
	
	public boolean isFinalizado() {
		return estado instanceof EstadoFinalizado;
	}
	
	public boolean isEnMarcha() {
		return estado instanceof EstadoEnMarcha;
	}
	
	public long getNumLastBloqueContenido() {
		return (long) 69;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoEnProgreso that = (CursoEnProgreso) o;
        return Objects.equals(estudiante, that.estudiante) &&
               Objects.equals(cursoPlantilla, that.cursoPlantilla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudiante, cursoPlantilla);
    }
}
