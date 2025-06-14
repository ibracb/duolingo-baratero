package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import umu.pds.duolingoBaratero.models.Pregunta;

public class AprendizajeAleatorio implements Aprendizaje {

	@Override
	public Set<Pregunta> seleccionarPreguntas(Set<Pregunta> disponibles) {
        List<Pregunta> lista = new ArrayList<>(disponibles);
        Collections.shuffle(lista);
        return new LinkedHashSet<>(lista);
    }

	@Override
	public AprendizajeSeleccionado getSeleccion() {
		return AprendizajeSeleccionado.ALEATORIO;
	}

}
