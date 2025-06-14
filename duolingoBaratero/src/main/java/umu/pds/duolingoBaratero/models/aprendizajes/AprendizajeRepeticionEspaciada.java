package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.Pregunta;

public class AprendizajeRepeticionEspaciada implements Aprendizaje {

	@Override
	public Set<Pregunta> seleccionarPreguntas(Set<Pregunta> disponibles) {
        // Prioriza las preguntas que han sido falladas previamente.
        return disponibles.stream()
            .sorted((p1, p2) -> Integer.compare(p2.getErrores(), p1.getErrores()))
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

	@Override
	public AprendizajeSeleccionado getSeleccion() {
		return AprendizajeSeleccionado.REPETICION_ESPACIADA;
	}

}
