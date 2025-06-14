package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.Set;

import umu.pds.duolingoBaratero.models.Pregunta;

public interface Aprendizaje {
	
	Set<Pregunta> seleccionarPreguntas(Set<Pregunta> disponibles);
	
	public AprendizajeSeleccionado getSeleccion();
	
}
