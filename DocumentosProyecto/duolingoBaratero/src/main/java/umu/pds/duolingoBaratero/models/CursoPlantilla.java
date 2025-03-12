package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.List;

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
	
}
