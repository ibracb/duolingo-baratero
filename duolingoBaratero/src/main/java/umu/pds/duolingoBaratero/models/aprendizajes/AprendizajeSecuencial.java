package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import umu.pds.duolingoBaratero.models.Pregunta;

/**
 * Implementación de {@link Aprendizaje} que selecciona las preguntas
 * en orden secuencial (orden natural definido por {@link Pregunta}).
 */
public class AprendizajeSecuencial implements Aprendizaje {

    /**
     * Selecciona las preguntas en orden natural utilizando {@link TreeSet}
     * y las mantiene en orden de inserción usando {@link LinkedHashSet}.
     *
     * @param disponibles conjunto de preguntas disponibles
     * @return conjunto ordenado secuencialmente
     */
    @Override
    public Set<Pregunta> seleccionarPreguntas(Set<Pregunta> disponibles) {
        return new LinkedHashSet<>(new TreeSet<>(disponibles));
    }

    /**
     * Retorna el tipo de aprendizaje representado.
     *
     * @return {@code AprendizajeSeleccionado.SECUENCIAL}
     */
    @Override
    public AprendizajeSeleccionado getSeleccion() {
        return AprendizajeSeleccionado.SECUENCIAL;
    }
}
