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

public enum ControladorDuolingoBaratero {
	INSTANCE;
	private Usuario user;

	private ControladorDuolingoBaratero() {
		this.user = null;

	}


}
// ----------------------------------------------
// Gestión de cursos
// ----------------------------------------------
