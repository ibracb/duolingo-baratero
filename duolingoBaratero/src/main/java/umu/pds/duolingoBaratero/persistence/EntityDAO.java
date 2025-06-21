package umu.pds.duolingoBaratero.persistence;

import java.util.List;

/**
 * Interfaz para la capa de acceso a datos de entidades.
 * @param <T>
 */
public interface EntityDAO<T> {
	
	/**
	 * Crea una nueva entidad.
	 * @param entity La entidad a crear.
	 */
	void create(T entity);
	
	/**
	 * Actualiza una entidad existente.
	 * @param entity La entidad a actualizar.
	 */
	void update(T entity);
	
	/**
	 * Elimina una entidad por su ID.
	 * @param id El ID de la entidad a eliminar.
	 */
	void delete(long id);
	
	/**
	 * Obtiene una entidad por su ID.
	 * @param id El ID de la entidad a obtener.
	 * @return La entidad correspondiente al ID, o null si no existe.
	 */
	T get(long id);
	
	/**
	 * Obtiene todas las entidades.
	 * @return Una lista con todas las entidades.
	 */
	List<T> getAll();
}
