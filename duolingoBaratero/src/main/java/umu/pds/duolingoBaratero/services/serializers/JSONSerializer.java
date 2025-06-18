package umu.pds.duolingoBaratero.services.serializers;

import java.io.File;

import com.fasterxml.jackson.databind.json.JsonMapper;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Implementación concreta de {@link Serializer} para la serialización y
 * deserialización en formato JSON usando {@link JsonMapper}.
 */
public class JSONSerializer extends Serializer {

    /** Extensión de archivo usada para archivos JSON. */
    private static final String JSON_EXTENSION = ".json";

    /**
     * Constructor que inicializa el serializador JSON con un {@link JsonMapper}
     * y la extensión ".json".
     */
    public JSONSerializer() {
        super(new JsonMapper(), JSON_EXTENSION);
    }

    /**
     * Serializa un objeto {@link CursoPlantilla} completo en formato JSON
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
     * Serializa solo la parte base del objeto {@link CursoPlantilla} en formato JSON
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
