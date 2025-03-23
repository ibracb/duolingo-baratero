package umu.pds.duolingoBaratero.services.serializers;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class YAMLSerializer extends Serializer {
	
	private static final String YAML_EXTENSION = ".yaml";
	
	protected YAMLSerializer() {
		super(new YAMLMapper(), YAML_EXTENSION);
	}

	@Override
	public boolean isBetter(CursoPlantilla cursoPlantilla) {
		return !cursoPlantilla.mejorJSON();
	}

}
