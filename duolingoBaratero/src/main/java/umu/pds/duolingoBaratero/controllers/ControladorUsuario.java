package umu.pds.duolingoBaratero.controllers;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Set;

import javax.swing.ImageIcon;

import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.ServicioUsuario;

public class ControladorUsuario {

	private final ServicioUsuario servicioUsuario;
	private final ImageService servicioImagenes;

	public ControladorUsuario(ServicioUsuario servicioUsuario, ImageService servicioImagenes) {
		this.servicioUsuario = servicioUsuario;
		this.servicioImagenes = servicioImagenes;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String correo, String contrasena) {
		return servicioUsuario.registrarUsuario(nombre, apellidos, correo, contrasena);
	}

	public boolean comprobarUsuario(String correo, String passwd) {
		return servicioUsuario.comprobarUsuario(correo, passwd);
	}

	// ----------------------------------------------
	// Funciones usuarioActual
	// ----------------------------------------------

	public Usuario getUsuarioActual() {
		return servicioUsuario.getUsuarioActual();
	}

	public String getNombreUsuarioActual() {
		return servicioUsuario.getNombreUsuarioActual();
	}

	public void logOut() {
		servicioUsuario.logOut();
	}

	public void setImagen(String image) {
		servicioUsuario.setImagen(image);
	}

	public void setCursos(String[] nombresCursos) {
		servicioUsuario.setCursos(nombresCursos);
	}

	public boolean addCursosEnProgreso(CursoPlantilla curso) {
		return servicioUsuario.addCursosEnProgreso(curso);
	}

	public boolean estaCursando(CursoPlantilla curso) {
		return servicioUsuario.estaCursando(curso);
	}

	public boolean addCursoPlantilla(String nombre, String objetivos, String descripcion) {
		return servicioUsuario.addCursoPlantilla(nombre, objetivos, descripcion);
	}

	public Set<CursoEnProgreso> getCursosUsuarioActual() {
		return servicioUsuario.getCursosUsuarioActual();
	}

	public double getPorcentajeRespuestasCorrectas() {
		return servicioUsuario.getPorcentajeRespuestasCorrectas();
	}

	public double getTiempoUso() {
		return servicioUsuario.getTiempoUso();
	}

	public int getRachaVictorias() {
		return servicioUsuario.getRachaVictorias();
	}

	public int getNumMaxAccesos() {
		return servicioUsuario.getNumMaxAccesos();
	}

	// ----------------------------------------------
	// Funciones imagenes
	// ----------------------------------------------

	public ImageIcon getScaledImage(BufferedImage bufferedImage, int dimensiones) {
		return servicioImagenes.getScaledImage(bufferedImage, dimensiones);
	}

	public ImageIcon getScaledImage(ImageIcon image, int dimensiones) {
		return servicioImagenes.getScaledImage(image, dimensiones);
	}

	public ImageIcon getScaledDefaultImage(int dimensiones) {
		ImageIcon image = new ImageIcon(getClass().getResource("/persona.png"));
		return servicioImagenes.getScaledImage(image, dimensiones);
	}

	public void borrarCurso(CursoEnProgreso curso) {
		servicioUsuario.borrarCurso(curso);
	}

	public int restarVidaUsuario() {
		int vidas = servicioUsuario.quitarVida();
		actualizarUsuario(); // actualiza después del cambio
		return vidas;
	}

	public int getVidasUsuario() {
		return servicioUsuario.getVidasUsuario();
	}
	

	public boolean recuperarVida() {
		boolean resultado = servicioUsuario.recuperarVida();
		actualizarUsuario(); // actualiza si recuperó vida
		return resultado;
	}
	
	public LocalDateTime getUltimaRecuperacion() {
		return servicioUsuario.getUltimaRecuperacion();
	}

	public void actualizarUsuario() {
		try {
			servicioUsuario.actualizarUsuario(); // debe hacer commit de los cambios
		} catch (Exception e) {
			System.err.println("Error al actualizar usuario: " + e.getMessage());
		}
	}

}
