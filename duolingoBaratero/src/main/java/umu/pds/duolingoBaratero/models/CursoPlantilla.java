package umu.pds.duolingoBaratero.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CursoPlantilla implements Comparable<CursoPlantilla> {
	
	private String nombre;
	private String propietario;
	private String descripcion;
	private String objetivos;
	private Nivel nivel;
	private List<BloqueContenido> contenidos;
	private Set<CursoEnProgreso> cursosEnProgreso;
	private String imagen;
	private int numAlumnos;
	private int lastBloqueContenido;
	
	public CursoPlantilla(String nombre, String propietario, String descripcion, String objetivos, Nivel nivel, BloqueContenido... contenidos) {
		this.nombre = nombre;
		this.propietario = propietario;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.nivel = nivel;
		this.contenidos = new LinkedList<>();
		this.cursosEnProgreso = new HashSet<CursoEnProgreso>();
		numAlumnos = 0;
		lastBloqueContenido = 0;
		//Collections.addAll(this.contenidos, contenidos);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
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
	
	public Set<CursoEnProgreso> getCursosEnProgreso() {
		return cursosEnProgreso;
	}
	
	public void setCursosEnProgreso(Set<CursoEnProgreso> cursosEnProgreso) {
		this.cursosEnProgreso = cursosEnProgreso;
	}
	
	public int getNumCursosEnProgreso() {
		return cursosEnProgreso.size();
	}
	
	public double getValoracionMedia() {
		return getCursosEnProgreso().stream()
				.mapToDouble(c -> c.getValoracion().getValor())
				.average()
				.orElse(0.0);
	}
	
	public void addBloqueContenido(BloqueContenido bloqueContenido) {
		contenidos.add(bloqueContenido);
		lastBloqueContenido += 1;
	}
	
	public void removeBloqueContenido(BloqueContenido bloqueContenido) {
		contenidos.remove(bloqueContenido);
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public boolean hasImage() {
		return getImagen() != null;
	}
	public int getNumAlumnos() {
		return numAlumnos;
	}
	public void addAlumno() {
		numAlumnos += 1;
	}

	public Set<TipoPregunta> getTipoPreguntas(){
		HashSet<TipoPregunta> tipos = new HashSet<>();
//		for (BloqueContenido bloque : contenidos) {
//			if (tipos.containsAll(EnumSet.allOf(TipoPregunta.class))){
//				break;
//			}
//			tipos.addAll(bloque.getTiposPreguntas());
//		}
		tipos.add(TipoPregunta.OPCIONES);
		tipos.add(TipoPregunta.FLASHCARD);
		tipos.add(TipoPregunta.IMAGEN);
		tipos.add(TipoPregunta.AUDIO);

		return tipos;
	}

	public List<Pregunta> getPreguntasDeBloque(long bloque){
		return contenidos.stream()
				.filter(b -> b.getId() == bloque)
				.findFirst()
				.map(BloqueContenido::getPreguntas)
				.orElse(new LinkedList<>());
	}

	@Override
	public int compareTo(CursoPlantilla o) {
		return Integer.compare(this.getNumCursosEnProgreso(), o.getNumCursosEnProgreso());
	}
	
	
}
