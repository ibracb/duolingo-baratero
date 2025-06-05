package umu.pds.duolingoBaratero.models;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import umu.pds.duolingoBaratero.windows.utility.Constantes;

@Entity
@Table(name = "bloques_contenido")
//@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
public class BloqueContenido {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany
	@JoinColumn(name = "bloque_de_contenido_id")
	private Set<Pregunta> preguntas;

	public BloqueContenido() {

	}

	public BloqueContenido(long id, Pregunta... preguntas) {
		this.preguntas = new HashSet<>();
		Collections.addAll(this.preguntas, preguntas);
		setNumPreguntas();
		id = Constantes.getID();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public Set<Pregunta> getPreguntasSecuencialmente() {
		return this.preguntas;
	}

	public Set<Pregunta> getPreguntasAleatoriamente() {
		Set<Pregunta> preguntasAleatorias = new HashSet<>(preguntas);
		// Collections.shuffle(preguntasAleatorias);
		return preguntasAleatorias;
	}

	public void addPregunta(Pregunta pregunta) {
		preguntas.add(pregunta);
	}

	public void removePregunta(Pregunta pregunta) {
		preguntas.remove(pregunta);
	}

	public Set<TipoPregunta> getTiposPreguntas() {
		return preguntas.stream().map(Pregunta::getTipo).collect(Collectors.toSet());
	}

	@JsonIgnore
	public int getNumPreguntas() {
		return preguntas.size();
	}

	public void setNumPreguntas() {
		int i = 0;
		for (Pregunta p : preguntas) {
			p.setNumero(i++);
		}
	}

}
