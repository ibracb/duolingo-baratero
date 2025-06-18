package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.Set;
import java.util.function.Supplier;

/**
 * Fábrica singleton que proporciona instancias de {@link Aprendizaje}
 * según el tipo seleccionado en {@link AprendizajeSeleccionado}.
 */
public enum FactoriaAprendizaje {

    /** Instancia única de la fábrica. */
    INSTANCE;

    /** Conjunto de constructores disponibles para los distintos tipos de aprendizaje. */
    private static final Set<Supplier<Aprendizaje>> aprendizajes = Set.of(
        AprendizajeAleatorio::new,
        AprendizajeSecuencial::new,
        AprendizajeInvertido::new
    );

    /**
     * Devuelve una instancia del tipo de {@link Aprendizaje} correspondiente a la selección.
     *
     * @param seleccion tipo de aprendizaje deseado
     * @return instancia correspondiente o {@link AprendizajeSecuencial} por defecto
     */
    public Aprendizaje getAprendizaje(AprendizajeSeleccionado seleccion) {
        return aprendizajes.stream()
            .map(Supplier::get)
            .filter(aprendizaje -> aprendizaje.getSeleccion().equals(seleccion))
            .findFirst()
            .orElse(new AprendizajeSecuencial());
    }
}
