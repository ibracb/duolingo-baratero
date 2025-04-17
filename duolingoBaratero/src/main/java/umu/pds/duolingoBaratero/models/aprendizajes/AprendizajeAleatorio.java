package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.Set;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.Pregunta;

public class AprendizajeAleatorio implements Aprendizaje {

	@Override
	public void setPreguntasByEstrategia(CursoEnProgreso progreso) {
	    progreso.getCursoPlantilla().getContenidos().forEach(bloque -> {
	        // Aquí estás obteniendo el Set de preguntas aleatorias
	        Set<Pregunta> preguntasAleatorias = bloque.getPreguntasAleatoriamente();
	        // Estableces el Set aleatorio en el bloque
	        bloque.setPreguntas(preguntasAleatorias);
	    });
	}

	@Override
	public AprendizajeSeleccionado getSeleccion() {
		return AprendizajeSeleccionado.ALEATORIO;
	}

}
