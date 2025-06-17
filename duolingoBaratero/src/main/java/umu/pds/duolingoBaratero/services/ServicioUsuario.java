package umu.pds.duolingoBaratero.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.persistence.DBUsuarioDAO;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;

public class ServicioUsuario {

	private final DBUsuarioDAO dbUsuarioDAO;
	private final ServicioCursoProgreso servicioCursoProgreso;
	private final ServicioCursoPlantilla servicioCursoPlantilla;
	private final ServicioEstadistica servicioEstadistica;

	private Usuario user;

	public ServicioUsuario(DBUsuarioDAO dbUsuarioDAO, ServicioCursoProgreso servicioCursoProgreso,
			ServicioCursoPlantilla servicioCursoPlantilla, ServicioEstadistica servicioEstadistica) {
		this.dbUsuarioDAO = dbUsuarioDAO;
		this.servicioCursoProgreso = servicioCursoProgreso;
		this.servicioCursoPlantilla = servicioCursoPlantilla;
		this.servicioEstadistica = servicioEstadistica;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String correo, String contrasena) {
		if (dbUsuarioDAO.existeUsuario(correo))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos, correo, contrasena);
		dbUsuarioDAO.create(usuario);
		this.user = usuario;
		servicioEstadistica.inicializarEstadistica(user.getEstadistica());
		return true;
	}

	public boolean comprobarUsuario(String correo, String passwd) {
		Usuario usuario = dbUsuarioDAO.get(correo);
		if (usuario != null) {
			this.user = usuario;
			servicioEstadistica.inicializarEstadistica(user.getEstadistica());
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

	public void setCursos(String[] nombresCursos) {
		if (user == null)
			return;

		Set<CursoEnProgreso> cursos = new HashSet<>();
		for (String nombre : nombresCursos) {
			servicioCursoPlantilla.buscarCursoPorNombre(nombre)
					.ifPresent(plantilla -> cursos.add(servicioCursoProgreso.crearCursoEnProgreso(plantilla, user)));
		}
		user.setCursos(cursos);
	}

	public boolean addCursosEnProgreso(CursoPlantilla curso) {
		if (user == null)
			return false;

		CursoEnProgreso cursoProgreso = servicioCursoPlantilla.crearCursoEnProgreso(curso, user);
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


	public void borrarCurso(CursoEnProgreso curso) {
		if (user != null) {
			user.eliminarCurso(curso);
		}
	}

	public int quitarVida() {
		return user.perderVida();
	}

	public boolean recuperarVida() {
		return user.recuperarVidas();
	}

	public int getVidasUsuario() {
		this.recuperarVida();
		return user.getVidas();
	}

	public LocalDateTime getUltimaRecuperacion() {
		return user.getUltimaRecuperacion();
	}
	
	public void actualizarUsuario() {
		dbUsuarioDAO.update(user);
		
		
	}


}
