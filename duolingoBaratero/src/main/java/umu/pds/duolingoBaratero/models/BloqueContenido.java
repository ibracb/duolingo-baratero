package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BloqueContenido {
	
	public static final int NUMERO_DE_PREGUNTAS = 10;
	private List<Pregunta> preguntas;
	

	public BloqueContenido(Pregunta...preguntas) {
		this.preguntas = new LinkedList<>();
		Collections.addAll(this.preguntas, preguntas);
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
	
}
