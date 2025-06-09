package umu.pds.duolingoBaratero.services.serializers;

import java.util.List;
import java.util.function.Supplier;

public enum SerializerFactory {
	
	INSTANCE;
	
	private final List<Supplier<Serializer>> serializers = List.of(
		JSONSerializer::new,
		YAMLSerializer::new
	);
	
	public Serializer getSerializer(String extension) {
		return serializers.stream()
				.map(Supplier::get)
				.filter(serializer -> serializer.getExtension().equals(extension))
				.findFirst()
				.get();
	}
	
}
