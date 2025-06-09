package umu.pds.duolingoBaratero.services.serializers;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class YAMLSerializer extends Serializer {
	
	protected static final String YAML_EXTENSION = ".yaml";
	
	protected YAMLSerializer() {
		super(new YAMLMapper(), YAML_EXTENSION);
	}

	public boolean serialize(CursoPlantilla curso) {
		try {
			// Ruta base relativa al proyecto
			String basePath = "src/main/resources/cursos/";
			File carpeta = new File(basePath);
			
			// Crear carpeta si no existe
			if (!carpeta.exists()) {
				carpeta.mkdirs();
			}

			// Construir nombre del archivo
			String fileName = curso.getNombre() + "_" +
							  curso.getPropietario() + "_" +
							  curso.getNivel().toString() + extension;

			// Unir path completo
			String fullPath = basePath + fileName;

			// Serializar
			mapper.writeValue(new File(fullPath), curso);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean serializeCursoBase(CursoPlantilla curso) {
        this.mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		try {
			// Ruta base relativa al proyecto
			String basePath = "src/main/resources/cursosBase/";
			File carpeta = new File(basePath);
			
			// Crear carpeta si no existe
			if (!carpeta.exists()) {
				carpeta.mkdirs();
			}

			// Construir nombre del archivo
			String fileName = curso.getNombre() + "_" +
							  curso.getPropietario()+ "_" +
							  curso.getNivel().toString() + extension;

			// Unir path completo
			String fullPath = basePath + fileName;

			// Serializar
			mapper.writeValue(new File(fullPath), curso);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
