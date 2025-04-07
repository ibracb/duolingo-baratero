package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import umu.pds.duolingoBaratero.windows.utility.Constantes;

@Entity
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
public class CursoEnProgreso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final int BLOQUE_COTENIDO_INICIAL = 0;
	private long id;
	
	private Usuario estudiante;
	
	private CursoPlantilla cursoPlantilla;
	
	private Aprendizaje aprendizaje;
	
	private List<BloqueContenidoProgreso> contenidosProgreso;
	private EstadoCursoEnProgreso estado;
	private Valoracion valoracion;
	private int bloqueActual;	//INFO: Se que se puede calcular cual es el bloque actual pero es ineficiente
	
	public CursoEnProgreso(Usuario usuario, CursoPlantilla cursoPlantilla, Aprendizaje aprendizaje, Valoracion valoracion, BloqueContenidoProgreso...contenidosProgreso) {
		this.estudiante = usuario;
		this.cursoPlantilla = cursoPlantilla;
		this.aprendizaje = aprendizaje;
		setEstado(new EstadoNuevo(this));
		setValoracion(valoracion);
		//Collections.addAll(this.contenidosProgreso, contenidosProgreso);
		id = Constantes.getID();

		this.contenidosProgreso = new LinkedList<>();
		bloqueActual = BLOQUE_COTENIDO_INICIAL;
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
	
	
    public int getBloqueActual() {
		return bloqueActual;
	}

	public void setBloqueActual(int bloqueActual) {
		this.bloqueActual = bloqueActual;
	}
	
	public void avanzarBloqueActual(boolean aprobado) {
		if (aprobado) {
			bloqueActual++;
			if (bloqueActual == contenidosProgreso.size()) {
				estado.finalizar(this);
			}
		}
	}

	public List<Pregunta> getPreguntasBloqueContenido(long bloqueContenidoProgreso) {
		return cursoPlantilla.getPreguntasDeBloque(bloqueContenidoProgreso);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<PreguntaProgreso> getTodasLasPreguntas() {
		List<BloqueContenidoProgreso> listaContenidos = getContenidosProgreso();
		List<PreguntaProgreso> listaPreguntas = new LinkedList<>();
		switch(this.aprendizaje) {
			case ALEATORIO:
				listaPreguntas.addAll(listaContenidos.get(0).getPreguntasAleatoriamente());
				break;
			case SECUENCIAL:
				listaPreguntas.addAll(listaContenidos.get(0).getPreguntasSecuencialmente());
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
