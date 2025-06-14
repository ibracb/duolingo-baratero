package umu.pds.duolingoBaratero.models.aprendizajes;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;

public class AprendizajeRepeticionEspaciada implements Aprendizaje {

	@Override
	public void setPreguntasByEstrategia(CursoEnProgreso progreso) {
		
		progreso.getCursoPlantilla().getContenidos().forEach(bloque -> {
			bloque.setPreguntas(bloque.getPreguntasSecuencialmente());
		});
	}

	@Override
	public AprendizajeSeleccionado getSeleccion() {
		return AprendizajeSeleccionado.REPETICION_ESPACIADA;
	}

}
