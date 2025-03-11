package umu.pds.duolingoBaratero.controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.models.Usuario;

public class ControladorDuolingoBaratero {
	private static ControladorDuolingoBaratero unicaInstancia;
	private Usuario user;
	private ControladorDuolingoBaratero() {

	}

	// Singleton: obtener instancia única
	public static ControladorDuolingoBaratero getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new ControladorDuolingoBaratero();
		}
		return unicaInstancia;
	}

	public CursoPlantilla getCurso(String nombre) {
		LinkedList<TipoPregunta> lista = new LinkedList<>();
		lista.add(TipoPregunta.COMPLETE);
		CursoPlantilla cursoDemo = new CursoPlantilla("Idiomas", "🗣️ Curso de aprendizaje de idiomas", lista,
				"📈 Mejorar tus habilidades lingüísticas", Nivel.AVANZADO, null);
		return cursoDemo;
	}
}
	// ----------------------------------------------
	// Gestión de cursos
	// ----------------------------------------------
	