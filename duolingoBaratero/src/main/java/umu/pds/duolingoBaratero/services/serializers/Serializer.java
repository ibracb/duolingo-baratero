package umu.pds.duolingoBaratero.services.serializers;

import java.io.File;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Clase abstracta que define la estrategia general de serialización y
 * deserialización para objetos {@link CursoPlantilla}.
 * 
 * Esta clase sirve como base para implementaciones concretas de serializadores
 * (por ejemplo, JSON o YAML), encapsulando la configuración común del
 * {@link ObjectMapper}.
 */
public abstract class Serializer {

    /** Mapper de Jackson configurado para la serialización/deserialización. */
    protected final ObjectMapper mapper;

    /** Extensión de archivo asociada al formato de serialización (ej. ".json", ".yaml"). */
    protected final String extension;

    /**
     * Constructor protegido para inicializar el serializador con un {@link ObjectMapper}
     * personalizado y una extensión de archivo.
     *
     * @param mapper    el {@link ObjectMapper} a utilizar
     * @param extension la extensión de archivo correspondiente al formato
     */
    protected Serializer(ObjectMapper mapper, String extension) {
        this.mapper = mapper;
        this.mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.WRAP_EXCEPTIONS);
        mapper.enable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        this.extension = extension;
    }

    /**
     * Deserializa un archivo al objeto {@link CursoPlantilla}.
     *
     * @param path ruta del archivo a deserializar (debe terminar con la extensión adecuada)
     * @return una instancia de {@link CursoPlantilla} o null en caso de error
     */
    public CursoPlantilla deserialize(String path) {
        assert (path.endsWith(extension));
        try {
            return mapper.readValue(new File(path), CursoPlantilla.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obtiene la extensión asociada al formato de serialización.
     *
     * @return la extensión de archivo (ej. ".json", ".yaml")
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Serializa un objeto {@link CursoPlantilla} completo a archivo.
     *
     * @param curso el objeto a serializar
     * @return true si la operación fue exitosa, false en caso contrario
     */
    public abstract boolean serialize(CursoPlantilla curso);

    /**
     * Serializa únicamente la parte base de un objeto {@link CursoPlantilla}.
     *
     * @param curso el objeto a serializar
     * @return true si la operación fue exitosa, false en caso contrario
     */
    public abstract boolean serializeCursoBase(CursoPlantilla curso);

}
