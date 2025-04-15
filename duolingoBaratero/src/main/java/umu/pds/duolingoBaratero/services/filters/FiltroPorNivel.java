package umu.pds.duolingoBaratero.services.filters;

import java.util.List;
import java.util.stream.Collectors;

import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;

public class FiltroPorNivel extends FiltroDecorador{
    private final Nivel nivel;
    public FiltroPorNivel(Filtro filtro, Nivel nivel) {
        super(filtro);
        this.nivel = nivel;
    }
    
    @Override
    public List<CursoPlantilla> filtrar(List<CursoPlantilla> lista){
        List<CursoPlantilla> l = lista.stream()
                .filter(c -> c.getNivel().equals(nivel))
                .collect(Collectors.toList());
        return super.filtrar(l);

    }

}