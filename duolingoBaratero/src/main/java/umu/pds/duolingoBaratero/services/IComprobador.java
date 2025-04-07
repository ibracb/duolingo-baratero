package umu.pds.duolingoBaratero.services;

import umu.pds.duolingoBaratero.models.Pregunta;

public interface IComprobador {
    // Método abstracto para obtener la respuesta del usuario
    String getRespuestaUsuario();
    boolean isOpcionElegida();
    Pregunta getPregunta();
}