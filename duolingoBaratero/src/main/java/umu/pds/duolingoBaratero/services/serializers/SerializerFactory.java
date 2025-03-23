package umu.pds.duolingoBaratero.services.serializers;

import java.util.List;
import java.util.function.Supplier;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public enum SerializerFactory {
	
	INSTANCE;
	
	private final List<Supplier<Serializer>> serializers = List.of(
		JSONSerializer::new,
		YAMLSerializer::new
	);
	
	public Serializer getSerializer(CursoPlantilla cursoPlantilla) {
		return serializers.stream()
			.map(Supplier::get)
			.filter(serializer -> serializer.isBetter(cursoPlantilla))
			.findFirst()
			.get();
	}
	
}
