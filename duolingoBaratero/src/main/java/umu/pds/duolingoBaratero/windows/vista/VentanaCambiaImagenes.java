package umu.pds.duolingoBaratero.windows.vista;

import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * Interfaz para la ventana que permite cambiar imágenes.
 * Esta interfaz define los métodos necesarios para interactuar con una ventana
 * que permite al usuario cambiar imágenes, establecer iconos y definir un archivo de destino.
 */
public interface VentanaCambiaImagenes{
	
	/**
	 * Devuelve el nombre de la ventana.
	 * @return
	 */
	public String getName();
	
	/**
	 * Establece el icono de la ventana.
	 * @param i
	 * @param url
	 */
	public void setIcon(ImageIcon i, URL url);
	
	/**
	 * Establece el destino del archivo donde se guardará la imagen.
	 * @param d
	 */
	public void setDestinationFile(File d);

}
