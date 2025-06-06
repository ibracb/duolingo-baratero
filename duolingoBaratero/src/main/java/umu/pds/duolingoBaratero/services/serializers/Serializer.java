package umu.pds.duolingoBaratero.services.serializers;

import java.io.File;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public abstract class Serializer {

	protected final ObjectMapper mapper;
	protected final String extension;

	protected Serializer(ObjectMapper mapper, String extension) {
		this.mapper = mapper;
        this.mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.WRAP_EXCEPTIONS);
        mapper.enable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

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
	
	public String getExtension() {
		return extension;
	}
	
	public abstract boolean serialize(CursoPlantilla curso);
	
	public abstract boolean serializeCursoBase(CursoPlantilla curso);

}
