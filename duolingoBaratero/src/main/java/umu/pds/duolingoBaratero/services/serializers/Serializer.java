package umu.pds.duolingoBaratero.services.serializers;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public abstract class Serializer {

	protected final ObjectMapper mapper;
	protected final String extension;

	protected Serializer(ObjectMapper mapper, String extension) {
		this.mapper = mapper;
		this.extension = extension;
	}

	public CursoPlantilla deserialize(String path) {
		assert (path.endsWith(extension));
		try {
			return mapper.readValue(new File(path), CursoPlantilla.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public abstract boolean serialize(CursoPlantilla curso);
}
