package umu.pds.duolingoBaratero.services;

import javax.swing.JFrame;

import umu.pds.duolingoBaratero.models.Pregunta;

/**
 * Esta clase es la encargada de crear la vista dinamicamente segun el atributo
 * "tipo" de la clase pregunta.
 */
class FactoryVista {  // FactoryVista es ahora una clase no estática
    public static JFrame crearVista(Pregunta pregunta) {
        switch (pregunta.getTipo()) {
            case OPCIONES:
                //return new VentanaPreguntaOpciones(pregunta);
            case FLASHCARD:
                //return new VistaFlashcard(pregunta);
            case AUDIO:
                //return new VistaAudio(pregunta);
            case IMAGEN:
                //return new VistaImagen(pregunta);
            default:
                throw new IllegalArgumentException("Tipo de pregunta no soportado");
        }
    }
}
