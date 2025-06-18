package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import umu.pds.duolingoBaratero.models.Pregunta;

/**
 * Implementación de {@link Aprendizaje} que prioriza preguntas según una lógica inversa,
 * típicamente ordenando por mayor número de fallos (según {@code getNumero()}).
 */
public class AprendizajeInvertido implements Aprendizaje {

    /**
     * Selecciona preguntas ordenándolas de mayor a menor según el valor retornado por {@code getNumero()}.
     *
     * @param disponibles conjunto de preguntas disponibles
     * @return conjunto ordenado de forma descendente
     */
    @Override
    public Set<Pregunta> seleccionarPreguntas(Set<Pregunta> disponibles) {
        return disponibles.stream()
            .sorted((p1, p2) -> Integer.compare(p2.getNumero(), p1.getNumero()))
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Retorna el tipo de aprendizaje representado.
     *
     * @return {@code AprendizajeSeleccionado.INVERTIDO}
     */
    @Override
    public AprendizajeSeleccionado getSeleccion() {
        return AprendizajeSeleccionado.INVERTIDO;
    }
}
