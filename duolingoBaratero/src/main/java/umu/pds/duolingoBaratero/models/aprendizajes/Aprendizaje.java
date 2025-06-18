package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.Set;
import umu.pds.duolingoBaratero.models.Pregunta;

/**
 * Interfaz que define un tipo de estrategia de aprendizaje,
 * que selecciona un conjunto ordenado de preguntas según un criterio.
 */
public interface Aprendizaje {

    /**
     * Selecciona un conjunto ordenado de preguntas de entre las disponibles,
     * aplicando la estrategia correspondiente.
     *
     * @param disponibles conjunto de preguntas disponibles
     * @return conjunto de preguntas ordenado según la estrategia
     */
    Set<Pregunta> seleccionarPreguntas(Set<Pregunta> disponibles);

    /**
     * Obtiene el tipo de aprendizaje representado.
     *
     * @return el valor de {@link AprendizajeSeleccionado} que identifica la estrategia
     */
    AprendizajeSeleccionado getSeleccion();
}
