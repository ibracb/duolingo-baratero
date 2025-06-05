package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.repositories.RepositorioCurso;
import umu.pds.duolingoBaratero.services.ImageService;

public enum ControladorUsuario {
	INSTANCE;

	private Usuario user;
	private ImageService sevicioImagenes;

	private ControladorUsuario() {
		this.sevicioImagenes = new ImageService();
	}

	public boolean registrarUsuario(String nombre, String apellidos, String telefono, String contrasena) {
		Usuario usuario = new Usuario(nombre, apellidos, telefono, contrasena);
		this.user = usuario;
//		if (usuario.isPresent()) {
//			Usuario u = usuario.get();
//			this.user = u;
//		}
//		return usuario.isPresent();
		return true;
	}

	public boolean comprobarUsuario(String correo, String passwd) {
		Usuario usuario = new Usuario("a", "a", "a", "A");
		boolean result = usuario != null;
		if (result) {
			this.user = usuario;
		}
		return result;
	}

	// ----------------------------------------------
	// Funciones usuarioActual
	// ----------------------------------------------

	public Usuario getUsuarioActual() {
		return this.user;
	}

	public String getNombreUsuarioActual() {
		return user.getNombre();
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
		Set<CursoEnProgreso> cursos = new HashSet<>();

		for (String nombre : nombresCursos) {
			ControladorCurso.INSTANCE.getCursoPlantilla(nombre).ifPresent(plantilla -> cursos
					.add(ContraldorCursoProgreso.INSTANCE.getCursoEnProgreso(plantilla, null, user)));
		}

		user.setCursos(cursos);
	}

	public boolean addCursosEnProgreso(CursoPlantilla curso, AprendizajeSeleccionado aprendizajeSeleccionado) {
		CursoEnProgreso cursoProgreso = ControladorCurso.INSTANCE.getCursoEnProgreso(curso, aprendizajeSeleccionado,
				user);
		boolean resultado = user.addCursoEnProgreso(cursoProgreso);
		if (resultado) {
			RepositorioCurso.INSTANCE.agregarCursoEnProgreso(cursoProgreso);
		}
		return resultado;
	}

	public boolean estaCursando(CursoPlantilla curso) {
		return user.estaCursando(curso);
	}

	public boolean addCursoPlantilla(String nombre, String objetivos, String descripcion) {
		return true;
	}

	public Set<CursoEnProgreso> getCursosUsuarioActual() {
		return user.getCursos();
	}

	public double getPorcentajeRespuestasCorrectas() {
		return user.getPorcentajeAcierto();
	}

	public double getTiempoUso() {
		return user.getTiempoUso();
	}

	public int getRachaVictorias() {
		return user.getRachaVictorias();
	}

	public int getNumMaxAccesos() {
		return user.getNumMaxAccesos();
	}

	// ----------------------------------------------
	// Funciones imagenes
	// ----------------------------------------------

	private ImageIcon whichImage(Usuario usuario, int dimensiones) throws IOException {
		BufferedImage image = null;
		String imagen = null;

		if (usuario.hasImage())
			imagen = usuario.getImagen();

		if (imagen != null) {
			if (sevicioImagenes.isURL(imagen)) {
				image = ImageIO.read(new URL(imagen));
			} else if (Files.exists(Paths.get(imagen))) {
				image = ImageIO.read(Paths.get(imagen).toFile());
			}
		}

		if (image != null) {
			return getScaledImage(image, dimensiones);
		} else {
			return getScaledDefaultImage(dimensiones);
		}
	}

	public ImageIcon getScaledImage(BufferedImage bufferedImage, int dimensiones) {
		return sevicioImagenes.getScaledImage(bufferedImage, dimensiones);
	}

	public ImageIcon getScaledImage(ImageIcon image, int dimensiones) {
		return sevicioImagenes.getScaledImage(image, dimensiones);
	}

	public ImageIcon getScaledDefaultImage(int dimensiones) {
		ImageIcon image = new ImageIcon(getClass().getResource("/persona.png"));
		return sevicioImagenes.getScaledImage(image, dimensiones);
	}

	public void borrarCurso(CursoEnProgreso curso) {
		user.eliminarCurso(curso);

	}

}
