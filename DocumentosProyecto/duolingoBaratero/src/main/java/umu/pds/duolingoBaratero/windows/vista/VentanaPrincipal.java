package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.LineBorder;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.windows.components.CursoCellRenderer;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<CursoPlantilla> listaCursos;

	public VentanaPrincipal() {
		setTitle("Continúa tus cursos");
		setSize(600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Panel superior con la barra de navegación
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(200, 220, 255));
		panelSuperior.add(new JLabel("Home 🏠 Desafío⚡ Estadísticas 📊 Modo Nocturno🌙 Mi Perfil 👤"));
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// Crear lista de cursos
		getCursosDemo();

		// Panel de botones
		JPanel panelBotones = new JPanel();
		JButton btnCrearCurso = new JButton("Crea tu propio curso");
		JButton btnNuevoCurso = new JButton("Empieza un nuevo curso");
		panelBotones.add(btnCrearCurso);
		panelBotones.add(btnNuevoCurso);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
	}

	private void getCursosDemo() {
		List<CursoPlantilla> cursos = new ArrayList<>();
		cursos.add(new CursoPlantilla("Idiomas", "Aprende nuevos idiomas", null, "Mejorar tu comunicación",
				Nivel.AVANZADO, null));

		// Agregar más cursos si es necesario
		listaCursos = new JList<>(cursos.toArray(new CursoPlantilla[0]));
		listaCursos.setCellRenderer(new CursoCellRenderer());
		JScrollPane scrollPane = new JScrollPane(listaCursos);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
