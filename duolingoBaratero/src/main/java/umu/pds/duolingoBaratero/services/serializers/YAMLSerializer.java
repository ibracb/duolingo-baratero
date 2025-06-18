package umu.pds.duolingoBaratero.services.serializers;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Implementación concreta de {@link Serializer} para la serialización y
 * deserialización en formato YAML usando {@link YAMLMapper}.
 */
public class YAMLSerializer extends Serializer {

    /** Extensión de archivo usada para archivos YAML. */
    protected static final String YAML_EXTENSION = ".yaml";

    /**
     * Constructor que inicializa el serializador YAML con un {@link YAMLMapper}
     * y la extensión ".yaml".
     */
    protected YAMLSerializer() {
        super(new YAMLMapper(), YAML_EXTENSION);
    }

    /**
     * Serializa un objeto {@link CursoPlantilla} completo en formato YAML
     * dentro de la carpeta `src/main/resources/cursos/`.
     *
     * El nombre del archivo se construye a partir del nombre, propietario y nivel del curso.
     *
     * @param curso el objeto a serializar
     * @return true si la serialización fue exitosa, false si ocurrió un error
     */
    @Override
    public boolean serialize(CursoPlantilla curso) {
        try {
            String basePath = "src/main/resources/cursos/";
            File carpeta = new File(basePath);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            String fileName = curso.getNombre() + "_" +
                              curso.getPropietario() + "_" +
                              curso.getNivel().toString() + extension;

            String fullPath = basePath + fileName;

            mapper.writeValue(new File(fullPath), curso);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Serializa solo la parte base del objeto {@link CursoPlantilla} en formato YAML
     * dentro de la carpeta `src/main/resources/cursosBase/`.
     *
     * @param curso el objeto a serializar
     * @return true si la serialización fue exitosa, false si ocurrió un error
     */
    @Override
    public boolean serializeCursoBase(CursoPlantilla curso) {
        this.mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        try {
            String basePath = "src/main/resources/cursosBase/";
            File carpeta = new File(basePath);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            String fileName = curso.getNombre() + "_" +
                              curso.getPropietario() + "_" +
                              curso.getNivel().toString() + extension;

            String fullPath = basePath + fileName;

            mapper.writeValue(new File(fullPath), curso);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
