package umu.pds.duolingoBaratero.services.serializers;

import java.util.List;
import java.util.function.Supplier;

/**
 * Fábrica singleton para obtener instancias de {@link Serializer}
 * según su extensión de archivo (por ejemplo, ".json" o ".yaml").
 *
 * Utiliza el patrón Singleton a través de un enum con una única instancia {@code INSTANCE}.
 */
public enum SerializerFactory {

    /** Instancia única de la fábrica. */
    INSTANCE;

    /**
     * Lista de proveedores de serializadores soportados.
     * Cada proveedor crea una nueva instancia de un {@link Serializer} concreto.
     */
    private final List<Supplier<Serializer>> serializers = List.of(
        JSONSerializer::new,
        YAMLSerializer::new
    );

    /**
     * Devuelve una instancia de {@link Serializer} cuya extensión coincida
     * con la proporcionada.
     *
     * @param extension la extensión del archivo (ej: ".json", ".yaml")
     * @return una instancia del serializador correspondiente
     * @throws java.util.NoSuchElementException si no se encuentra ningún serializador para la extensión
     */
    public Serializer getSerializer(String extension) {
        return serializers.stream()
                .map(Supplier::get)
                .filter(serializer -> serializer.getExtension().equals(extension))
                .findFirst()
                .get();
    }
}
