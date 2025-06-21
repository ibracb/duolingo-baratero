package umu.pds.duolingoBaratero.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Servicio para gestionar imágenes, incluyendo escalado y conversión a circular.
 */
public class ImageService {
	
	/**
	 * Escala una imagen a un tamaño específico y la convierte a circular.
	 * 
	 * @param bufferedImage Imagen original.
	 * @param dimensiones   Tamaño deseado para la imagen escalada.
	 * @return ImageIcon con la imagen escalada y circular.
	 */
	public ImageIcon getScaledImage(BufferedImage bufferedImage, int dimensiones) {
		BufferedImage scaledImage = scaleAndMakeCircular(bufferedImage, dimensiones);
		return new ImageIcon(scaledImage);
	}
	
	/**
	 * Escala una imagen a un tamaño específico y la convierte a circular.
	 * 
	 * @param image   Imagen original como ImageIcon.
	 * @param dimensiones Tamaño deseado para la imagen escalada.
	 * @return ImageIcon con la imagen escalada y circular.
	 */
	public ImageIcon getScaledImage(ImageIcon image, int dimensiones) {
		BufferedImage bufferedImage = iconToBufferedImage(image);
		BufferedImage scaledImage = scaleImage(bufferedImage, dimensiones, dimensiones);
		return new ImageIcon(scaledImage);
	}
	
	/**
	 * Escala una imagen a un tamaño específico y la convierte a circular.
	 * 
	 * @param imageURL URL de la imagen original.
	 * @param dimensiones Tamaño deseado para la imagen escalada.
	 * @return ImageIcon con la imagen escalada y circular.
	 */
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
	
	/**
	 * Escala una imagen a un tamaño específico.
	 * 
	 * @param originalImage Imagen original.
	 * @param targetWidth   Ancho deseado para la imagen escalada.
	 * @param targetHeight  Alto deseado para la imagen escalada.
	 * @return BufferedImage con la imagen escalada.
	 */
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
	
	/**
	 * Convierte un ImageIcon a BufferedImage.
	 * 
	 * @param icon ImageIcon a convertir.
	 * @return BufferedImage resultante.
	 */
	private BufferedImage iconToBufferedImage(ImageIcon icon) {
		Image image = icon.getImage();
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return bufferedImage;
	}
	
	/**
	 * Verifica si una cadena es una URL válida.
	 * 
	 * @param string Cadena a verificar.
	 * @return true si es una URL válida, false en caso contrario.
	 */
	public boolean isURL(String string) {
		try {
			new URL(string.toString()).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
