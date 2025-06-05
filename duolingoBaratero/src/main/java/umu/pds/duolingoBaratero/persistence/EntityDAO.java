package umu.pds.duolingoBaratero.persistence;

import java.util.List;

public interface EntityDAO<T> {
	
	void create(T entity);
	
	void update(T entity);
	
	void delete(long id);
	
	T get(long id);
	
	List<T> getAll();
}
