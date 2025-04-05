package umu.pds.duolingoBaratero.services.serializers;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public abstract class Serializer {
	
	private final ObjectMapper mapper;
	private final String extension;
	
	protected Serializer(ObjectMapper mapper, String extension) {
		this.mapper = mapper;
		this.extension = extension;
	}
	
	public void serialize(String path, CursoPlantilla cursoPlantilla) {
		assert(path.endsWith(extension));
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), cursoPlantilla);
			System.out.println("Serialized to " + path);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error serializing to " + path);
		}
	}
	
	public CursoPlantilla deserialize(String path) {
		assert(path.endsWith(extension));
		try {
			return mapper.readValue(new File(path), CursoPlantilla.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public abstract boolean isBetter(CursoPlantilla cursoPlantilla);
	
}
