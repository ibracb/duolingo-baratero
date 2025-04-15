package umu.pds.duolingoBaratero.models.aprendizajes;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;

public class AprendizajeAleatorio implements Aprendizaje {

	@Override
	public void setPreguntasByEstrategia(CursoEnProgreso progreso) {
		progreso.getCursoPlantilla().getContenidos().forEach(bloque -> {
			bloque.setPreguntas(bloque.getPreguntasAleatoriamente());
		});
	}

	@Override
	public AprendizajeSeleccionado getSeleccion() {
		return AprendizajeSeleccionado.ALEATORIO;
	}

}
