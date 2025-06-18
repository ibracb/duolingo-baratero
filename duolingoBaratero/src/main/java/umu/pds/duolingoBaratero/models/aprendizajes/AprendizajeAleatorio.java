package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import umu.pds.duolingoBaratero.models.Pregunta;

/**
 * Implementación de {@link Aprendizaje} que selecciona preguntas en orden aleatorio.
 */
public class AprendizajeAleatorio implements Aprendizaje {

    /**
     * Selecciona preguntas mezclándolas aleatoriamente.
     *
     * @param disponibles conjunto de preguntas disponibles
     * @return conjunto de preguntas en orden aleatorio
     */
    @Override
    public Set<Pregunta> seleccionarPreguntas(Set<Pregunta> disponibles) {
        List<Pregunta> lista = new ArrayList<>(disponibles);
        Collections.shuffle(lista);
        return new LinkedHashSet<>(lista);
    }

    /**
     * Retorna el tipo de aprendizaje representado.
     *
     * @return {@code AprendizajeSeleccionado.ALEATORIO}
     */
    @Override
    public AprendizajeSeleccionado getSeleccion() {
        return AprendizajeSeleccionado.ALEATORIO;
    }
}
