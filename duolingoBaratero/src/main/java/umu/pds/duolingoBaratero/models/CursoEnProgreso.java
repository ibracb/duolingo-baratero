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
	@Transient
	private EstadoCursoEnProgreso estado;

	@Column(name = "bloque_actual")
	private int bloqueActual;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	// Y aqui falta meter una relacion hacia el usuario para que un curos en
	// progreso pertenezca a un usuario

	public CursoEnProgreso(CursoPlantilla cursoPlantilla,
			Usuario usuario) {
		this.cursoPlantilla = cursoPlantilla;
		this.usuario = usuario;
		setEstado(new EstadoNuevo(this));
		id = Constantes.getID();
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

	public void setAprendizajeConEnum(AprendizajeSeleccionado aprendizajeSeleccionado) {
		this.aprendizaje = aprendizajeSeleccionado;
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
				estado.finalizar(this);
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

	public void iniciar() {
		estado.iniciar(this);
	}

	public void reiniciar() {
		estado.iniciar(this);
		bloqueActual = BLOQUE_CONTENIDO_INICIAL;
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
