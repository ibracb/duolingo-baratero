package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CursoPlantilla {
	
	private String nombre;
	private String descripcion;
	private String objetivos;
	private Nivel nivel;
	private List<BloqueContenido> contenidos;
	
	public CursoPlantilla(String nombre, String descripcion, String objetivos, Nivel nivel, BloqueContenido... contenidos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.nivel = nivel;
		this.contenidos = new LinkedList<>();
		Collections.addAll(this.contenidos, contenidos);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObjetivos() {
		return objetivos;
	}
	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public List<BloqueContenido> getContenidos() {
		return Collections.unmodifiableList(contenidos);
	}
	public void setContenidos(List<BloqueContenido> contenidos) {
		this.contenidos = contenidos;
	}
	
	public void addBloqueContenido(BloqueContenido bloqueContenido) {
		contenidos.add(bloqueContenido);
	}
	
	public void removeBloqueContenido(BloqueContenido bloqueContenido) {
		contenidos.remove(bloqueContenido);
	}

	public Set<TipoPregunta> getTipoPreguntas(){
		HashSet<TipoPregunta> tipos = new HashSet<>();
//		for (BloqueContenido bloque : contenidos) {
//			if (tipos.containsAll(EnumSet.allOf(TipoPregunta.class))){
//				break;
//			}
//			tipos.addAll(bloque.getTiposPreguntas());
//		}
		tipos.add(TipoPregunta.COMPLETE);
		tipos.add(TipoPregunta.FLASHCARD);
		tipos.add(TipoPregunta.IMAGENES);
		tipos.add(TipoPregunta.LISTEN);

		return tipos;

	}
	
	
}
