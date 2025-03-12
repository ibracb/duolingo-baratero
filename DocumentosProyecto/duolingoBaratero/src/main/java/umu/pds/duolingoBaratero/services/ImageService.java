package umu.pds.duolingoBaratero.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;

public class ImageService {
	
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
	public boolean isURL(String input) {
		try {
			new URL(input).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
