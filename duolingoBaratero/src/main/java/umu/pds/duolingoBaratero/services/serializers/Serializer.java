package umu.pds.duolingoBaratero.services.serializers;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonInclude;
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
			mapper.writeValue(new File(path), cursoPlantilla);
		} catch (Exception e) {
			e.printStackTrace();
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
