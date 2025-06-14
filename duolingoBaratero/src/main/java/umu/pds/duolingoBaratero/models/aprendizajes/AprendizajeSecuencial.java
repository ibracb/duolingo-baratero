package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import umu.pds.duolingoBaratero.models.Pregunta;

public class AprendizajeSecuencial implements Aprendizaje {

	@Override
	public Set<Pregunta> seleccionarPreguntas(Set<Pregunta> disponibles) {
        return new LinkedHashSet<>(new TreeSet<>(disponibles));
    }

	@Override
	public AprendizajeSeleccionado getSeleccion() {
		return AprendizajeSeleccionado.SECUENCIAL;
	}

}
