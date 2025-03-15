package umu.pds.duolingoBaratero.controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
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
		System.out.println(result);
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
		LinkedList<CursoEnProgreso> cursos = new LinkedList<>();
		if (cursos != null) {
			for (String nombreCurso : nombresCursos) {
				cursos.add(ControladorCurso.INSTANCE.getCursoEnProgreso(nombreCurso));
			}
			user.setCursos(cursos);
		}
		
	}
	
	public List<CursoEnProgreso> getCursosUsuarioActual() {
		return user.getCursos();
	}
	
	public List<CursoPlantilla> getCursosCreadosUsuarioActual(){
		return user.getCursosCreados();
	}
	
	public boolean isUserCreator() {
		return this.user.isCreador();
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


}
