package umu.pds.duolingoBaratero.controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import umu.pds.duolingoBaratero.models.Usuario;

public class ControladorUsuario {

	private static ControladorUsuario unicaInstancia;
	private Usuario user;

	private ControladorUsuario() {

	}

	// Singleton: obtener instancia única
	public static ControladorUsuario getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new ControladorUsuario();
		}
		return unicaInstancia;
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
		Usuario usuario = null;
		boolean result = usuario != null;
		if (result) {
			this.user = usuario;
		}
		result = true;
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

	private ImageIcon whichImage(Object obj, int dimensiones) throws IOException {
		BufferedImage image = null;
		String imagen = null;

		Usuario usuario = (Usuario) obj;
		if (usuario.hasImage())
			imagen = usuario.getImagen();

		if (imagen != null) {
			if (isURL(imagen)) {
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

	public ImageIcon getScaledDefaultImage(int dimensiones) {
		return getScaledImage(new ImageIcon(getClass().getResource("/persona.png")), dimensiones);
	}

	public ImageIcon getScaledImage(BufferedImage bufferedImage, int dimensiones) {
		BufferedImage scaledImage = scaleAndMakeCircular(bufferedImage, dimensiones);
		return new ImageIcon(scaledImage);
	}

	public ImageIcon getScaledImage(ImageIcon image, int dimensiones) {
		BufferedImage bufferedImage = iconToBufferedImage(image);
		BufferedImage scaledImage = scaleAndMakeCircular(bufferedImage, dimensiones);
		return new ImageIcon(scaledImage);
	}

	private BufferedImage scaleAndMakeCircular(BufferedImage originalImage, int targetSize) {
		BufferedImage scaledImage = scaleImage(originalImage, targetSize, targetSize);
		BufferedImage circularImage = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = circularImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setClip(new java.awt.geom.Ellipse2D.Double(0, 0, targetSize, targetSize));
		g2d.drawImage(scaledImage, 0, 0, targetSize, targetSize, null);
		g2d.dispose();
		return circularImage;
	}

	private BufferedImage scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
		BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = scaledImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		g2d.dispose();
		return scaledImage;
	}

	private BufferedImage iconToBufferedImage(ImageIcon icon) {
		Image image = icon.getImage();
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return bufferedImage;
	}

	@SuppressWarnings("deprecation")
	private boolean isURL(String input) {
		try {
			new URL(input).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
