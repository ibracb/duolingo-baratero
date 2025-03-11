package umu.pds.duolingoBaratero.models;

import java.util.List;

public class CursoPlantilla {
	
	private String nombre;
	private String descripcion;
	private List<TipoPregunta> tipoPreguntas;
	private String objetivos;
	private Nivel nivel;
	private BloqueContenido contenidos;
	
	
	
	public CursoPlantilla(String nombre, String descripcion, List<TipoPregunta> tipoPreguntas, String objetivos,
			Nivel nivel, BloqueContenido contenidos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoPreguntas = tipoPreguntas;
		this.objetivos = objetivos;
		this.nivel = nivel;
		//this.contenidos = contenidos;
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
	public List<TipoPregunta> getTipoPreguntas() {
		return tipoPreguntas;
	}
	public void setTipoPreguntas(List<TipoPregunta> tipoPreguntas) {
		this.tipoPreguntas = tipoPreguntas;
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
	public BloqueContenido getContenidos() {
		return contenidos;
	}
	public void setContenidos(BloqueContenido contenidos) {
		this.contenidos = contenidos;
	}
	
	
}
