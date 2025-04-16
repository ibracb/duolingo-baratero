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
	
	public Serializer getSerializer(String extension) {
		if (extension.equals("yaml"))
			return new YAMLSerializer();
		else if (extension.equals("json"))
			System.out.println("Si soy yo");
			return new JSONSerializer();
		
	}
	
}
