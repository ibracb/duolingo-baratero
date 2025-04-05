package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import umu.pds.duolingoBaratero.windows.utility.Constantes;

@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
public class BloqueContenido {
	
	private long id;
	private List<Pregunta> preguntas;

	public BloqueContenido(long id, Pregunta...preguntas) {
		this.preguntas = new LinkedList<>();
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

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public Set<Pregunta> getPreguntasSecuencialmente() {
		return Collections.unmodifiableSet(new TreeSet<>(this.preguntas));
	}
	
	public List<Pregunta> getPreguntasAleatoriamente() {
		List<Pregunta> preguntasAleatorias = new LinkedList<>(preguntas);
		Collections.shuffle(preguntasAleatorias);
		return preguntasAleatorias;
	}
	
	public void addPregunta(Pregunta pregunta) {
		preguntas.add(pregunta);
	}
	
	public void removePregunta(Pregunta pregunta) {
		preguntas.remove(pregunta);
	}
	
    public Set<TipoPregunta> getTiposPreguntas() {
        return preguntas.stream()
                .map(Pregunta::getTipo)
                .collect(Collectors.toSet());
    }
    
    public int getNumPreguntas() {
    	return preguntas.size();
    }
    
    public void setNumPreguntas() {
		int i = 0;
		for (Pregunta p: preguntas) {
			p.setNumero(i++);
		}
    }
	
}
