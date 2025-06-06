package umu.pds.duolingoBaratero.services;

import java.util.HashSet;
import java.util.Set;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.models.aprendizajes.FactoriaAprendizaje;
import umu.pds.duolingoBaratero.persistence.DBUsuarioDAO;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;

public class ServicioUsuario {

	private final DBUsuarioDAO dbUsuarioDAO;
	private final ControladorCursoProgreso controladorCursoProgreso;
	private final ControladorCursoPlantilla controladorCursoPlantilla;

	private Usuario user;

	public ServicioUsuario(
		DBUsuarioDAO dbUsuarioDAO,
		ControladorCursoProgreso controladorCursoProgreso,
		ControladorCursoPlantilla controladorCursoPlantilla
	) {
		this.dbUsuarioDAO = dbUsuarioDAO;
		this.controladorCursoProgreso = controladorCursoProgreso;
		this.controladorCursoPlantilla = controladorCursoPlantilla;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String correo, String contrasena) {
		if (dbUsuarioDAO.existeUsuario(correo)) return false;
		Usuario usuario = new Usuario(nombre, apellidos, correo, contrasena);
		dbUsuarioDAO.create(usuario);
		this.user = usuario;
		return true;
	}

	public boolean comprobarUsuario(String correo, String passwd) {
		Usuario usuario = dbUsuarioDAO.get(correo);
		if (usuario != null) {
			this.user = usuario;
			return true;
		}
		return false;
	}

	public Usuario getUsuarioActual() {
		return user;
	}

	public String getNombreUsuarioActual() {
		return user != null ? user.getNombre() : null;
	}

	public void logOut() {
		user = null;
	}

	public void setImagen(String image) {
		if (user != null) {
			user.setImagen(image);
		}
	}

	public void setCursos(String[] nombresCursos, AprendizajeSeleccionado seleccion) {
		if (user == null) return;

		Set<CursoEnProgreso> cursos = new HashSet<>();
		for (String nombre : nombresCursos) {
			controladorCursoPlantilla.getCursoPlantilla(nombre).ifPresent(plantilla -> 
				cursos.add(controladorCursoProgreso.crearCurso(plantilla, FactoriaAprendizaje.INSTANCE.getAprendizaje(seleccion).getSeleccion(), user))
			);
		}
		user.setCursos(cursos);
	}

	public boolean addCursosEnProgreso(CursoPlantilla curso, AprendizajeSeleccionado aprendizajeSeleccionado) {
		if (user == null) return false;

		CursoEnProgreso cursoProgreso = controladorCursoPlantilla.getCursoEnProgreso(curso, aprendizajeSeleccionado, user);
		boolean resultado = user.addCursoEnProgreso(cursoProgreso);
		if (resultado) {
			RepositorioCurso.INSTANCE.agregarCursoEnProgreso(cursoProgreso);
		}
		return resultado;
	}

	public boolean estaCursando(CursoPlantilla curso) {
		return user != null && user.estaCursando(curso);
	}

	public boolean addCursoPlantilla(String nombre, String objetivos, String descripcion) {
		// TODO
		return true;
	}

	public Set<CursoEnProgreso> getCursosUsuarioActual() {
		return user != null ? user.getCursos() : null;
	}

	public double getPorcentajeRespuestasCorrectas() {
		return user != null ? user.getPorcentajeAcierto() : 0;
	}

	public double getTiempoUso() {
		return user != null ? user.getTiempoUso() : 0;
	}

	public int getRachaVictorias() {
		return user != null ? user.getRachaVictorias() : 0;
	}

	public int getNumMaxAccesos() {
		return user != null ? user.getNumMaxAccesos() : 0;
	}

	public void borrarCurso(CursoEnProgreso curso) {
		if (user != null) {
			user.eliminarCurso(curso);
		}
	}
}
