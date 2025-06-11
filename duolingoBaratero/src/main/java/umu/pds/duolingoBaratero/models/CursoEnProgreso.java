package umu.pds.duolingoBaratero.models;

import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.windows.utility.Constantes;

@Entity
@Table(name = "cursos_en_progreso")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	public class CursoEnProgreso {
	
		@Transient
		private final int BLOQUE_CONTENIDO_INICIAL = 0;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
	
		@ManyToOne
		@JoinColumn(name = "curso_plantilla", nullable = false)
		private CursoPlantilla cursoPlantilla;
	
		@Enumerated(EnumType.STRING)
		@Column(name = "aprendizaje")
		private AprendizajeSeleccionado aprendizaje;
	
		// TODO: REVISAR COMO HACER ESTO EN JPA
		@Column(name="estado")
		private EstadoCursoEnProgreso estado;
	
		@Column(name = "bloque_actual")
		private int bloqueActual;
	
		@ManyToOne
		@JoinColumn(name = "usuario_id", nullable = false)
		private Usuario usuario;
		
		public CursoEnProgreso() {

		}

	public CursoEnProgreso(CursoPlantilla cursoPlantilla,
			Usuario usuario) {
		this.cursoPlantilla = cursoPlantilla;
		this.usuario = usuario;
		this.estado = EstadoCursoEnProgreso.NUEVO;
		bloqueActual = BLOQUE_CONTENIDO_INICIAL;
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

	public CursoPlantilla getCursoPlantilla() {
		return cursoPlantilla;
	}

	public void setCursoPlantilla(CursoPlantilla cursoPlantilla) {
		this.cursoPlantilla = cursoPlantilla;
	}

	public AprendizajeSeleccionado getAprendizaje() {
		return aprendizaje;
	}

	public void setAprendizaje(AprendizajeSeleccionado aprendizaje) {
		this.aprendizaje = aprendizaje;
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
			if (cursoPlantilla.isCursoFinalizado(bloqueActual)) {
				this.finalizar();
			}
		}
	}

	/**
	 * Devuelve las pregunas del bloque de contenido pasado como parametro
	 * 
	 * @return preguntas
	 */
	public Set<Pregunta> getPreguntasBloqueContenido(int bloqueContenidoProgreso) {
		return cursoPlantilla.getPreguntasDeBloque(bloqueContenidoProgreso);
	}

	/**
	 * Devuelve las pregunas del bloque de contenido actual
	 * 
	 * @return preguntas
	 */

	public Set<Pregunta> getPreguntasBloqueContenido() {
		return cursoPlantilla.getPreguntasDeBloque(bloqueActual);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EstadoCursoEnProgreso getEstado() {
		return estado;
	}

	public void setEstado(EstadoCursoEnProgreso estado) {
		this.estado = estado;
	}
	
	public void reiniciar() {
        if (estado == EstadoCursoEnProgreso.FINALIZADO) {
            estado = EstadoCursoEnProgreso.NUEVO;
        } else {
            throw new IllegalStateException("No se puede reiniciar desde el estado: " + estado);
        }
    }

    public void iniciar() {
        if (estado == EstadoCursoEnProgreso.NUEVO) {
            estado = EstadoCursoEnProgreso.EN_MARCHA;
        } else {
            throw new IllegalStateException("No se puede iniciar desde el estado: " + estado);
        }
    }
    
    public void finalizar() {
        if (estado == EstadoCursoEnProgreso.EN_MARCHA) {
            estado = EstadoCursoEnProgreso.FINALIZADO;
        } else {
            throw new IllegalStateException("No se puede finalizar desde el estado: " + estado);
        }
    }




	public boolean isNuevo() {
		return estado.equals(EstadoCursoEnProgreso.NUEVO);
	}

	public boolean isFinalizado() {
		return estado.equals(EstadoCursoEnProgreso.FINALIZADO);
	}

	public boolean isEnMarcha() {
		return estado.equals(EstadoCursoEnProgreso.EN_MARCHA);
	}

	public long getNumLastBloqueContenido() {
		return (long) 69;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CursoEnProgreso that = (CursoEnProgreso) o;
		return Objects.equals(id, that.id) && Objects.equals(cursoPlantilla, that.cursoPlantilla);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cursoPlantilla);
	}
}
