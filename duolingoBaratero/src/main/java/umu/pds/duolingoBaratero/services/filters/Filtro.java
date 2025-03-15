package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

public interface Filtro {
	public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista);
}
