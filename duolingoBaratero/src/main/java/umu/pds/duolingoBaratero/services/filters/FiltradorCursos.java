package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FiltradorCursos<T> {
	
	private FiltroCursos<T> filtro;
	
	@SafeVarargs
	public FiltradorCursos(Predicate<T>... filtros) {
		this.filtro = curso -> java.util.Arrays.stream(filtros).allMatch(f -> f.test(curso));
	}

	public List<T> filtrar(List<T> cursosPlantilla) {
		return cursosPlantilla.stream()
				.filter(filtro)
				.collect(Collectors.toList());
    }
	
}
