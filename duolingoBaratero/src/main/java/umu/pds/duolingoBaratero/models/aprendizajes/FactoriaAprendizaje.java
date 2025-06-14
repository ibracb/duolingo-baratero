package umu.pds.duolingoBaratero.models.aprendizajes;

import java.util.Set;
import java.util.function.Supplier;

public enum FactoriaAprendizaje {	
	INSTANCE;
	
	private static final Set<Supplier<Aprendizaje>> aprendizajes = Set.of(
			AprendizajeAleatorio::new,
			AprendizajeSecuencial::new,
			AprendizajeInvertido::new
			);
	
	public Aprendizaje getAprendizaje(AprendizajeSeleccionado seleccion) {
		return aprendizajes.stream()
				.map(Supplier::get)
				.filter(aprendizaje -> aprendizaje.getSeleccion().equals(seleccion))
				.findFirst()
				.orElse(new AprendizajeSecuencial());
	}
	
	
}
