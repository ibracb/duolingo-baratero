package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.windows.components.CursoCellRenderer;
import umu.pds.duolingoBaratero.windows.components.CursoCreadoCellRenderer;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<CursoEnProgreso> listaCursos;
	private DefaultListModel<CursoEnProgreso> modeloCursos;
	private JList<CursoPlantilla> listaCursosCreados;
	private DefaultListModel<CursoPlantilla> modeloCursosCreados;

	public VentanaPrincipal() {
		setTitle("Continúa tus cursos");
		setSize(600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Panel superior con la barra de navegación
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(200, 220, 255));
		JButton btnHome = new JButton("Home 🏠");
		JButton btnDesafio = new JButton("Desafío⚡");
		JButton btnEstadisticas = new JButton("Estadísticas 📊");
		JButton btnModoNocturno = new JButton("Modo Nocturno🌙");
		JButton btnPerfil = new JButton("Mi Perfil 👤");

		panelSuperior.add(btnHome);
		panelSuperior.add(btnDesafio);
		panelSuperior.add(btnEstadisticas);
		panelSuperior.add(btnModoNocturno);
		panelSuperior.add(btnPerfil);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		// Panel central con etiquetas y listas de cursos
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(1, 2));

		// Panel izquierdo con etiqueta y lista de cursos en progreso
		JPanel panelCursosEnProgreso = new JPanel(new BorderLayout());
		JLabel labelCursosEnProgreso = new JLabel("Cursos empezados", SwingConstants.CENTER);
		panelCursosEnProgreso.add(labelCursosEnProgreso, BorderLayout.NORTH);
		modeloCursos = new DefaultListModel<>();
		listaCursos = new JList<>(modeloCursos);
		listaCursos.setCellRenderer(new CursoCellRenderer());
		listaCursos.addListSelectionListener(e -> manejarSeleccionCursosEmpezados(listaCursos.getSelectedValue()));
		panelCursosEnProgreso.add(new JScrollPane(listaCursos), BorderLayout.CENTER);
		panelCentral.add(panelCursosEnProgreso);

		// Si el usuario es creador, mostrar la lista de cursos creados con etiqueta
		// centrada
		if (ControladorUsuario.INSTANCE.isUserCreator()) {
			JPanel panelCursosCreados = new JPanel(new BorderLayout());
			JLabel labelCursosCreados = new JLabel("Mis cursos", SwingConstants.CENTER);
			panelCursosCreados.add(labelCursosCreados, BorderLayout.NORTH);
			modeloCursosCreados = new DefaultListModel<>();
			listaCursosCreados = new JList<>(modeloCursosCreados);
			listaCursosCreados.setCellRenderer(new CursoCreadoCellRenderer());
			panelCursosCreados.add(new JScrollPane(listaCursosCreados), BorderLayout.CENTER);
			panelCentral.add(panelCursosCreados);
		}

		getContentPane().add(panelCentral, BorderLayout.CENTER);

		// Panel de botones
		JPanel panelBotones = new JPanel();
		JButton btnCrearCurso = new JButton("Crea tu propio curso");
		JButton btnNuevoCurso = new JButton("Empieza un nuevo curso");

		if (ControladorUsuario.INSTANCE.isUserCreator()) {
			panelBotones.add(btnCrearCurso);
		}
		panelBotones.add(btnNuevoCurso);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Cargar datos iniciales
		refreshCursos();
	}

	private List<CursoEnProgreso> getCursosEnProgreso() {
		return ControladorUsuario.INSTANCE.getCursosUsuarioActual();
	}

	private List<CursoPlantilla> getCursosCreados() {
		return ControladorUsuario.INSTANCE.getCursosCreadosUsuarioActual();
	}
	
	private void manejarSeleccionCursosEmpezados(CursoEnProgreso curso) {
		//FIXME EL 69 ese hay que quitarlo jeje
        new VentanaPregunta(69).setVisible(true);
	}

	private void refreshCursos() {
        modeloCursos.clear();
        for (CursoEnProgreso curso : getCursosEnProgreso()) {
            modeloCursos.addElement(curso);
        }
        CursoPlantilla cursoP = new CursoPlantilla("Idiomas", "Aprende nuevos idiomas", null, Nivel.AVANZADO, null);
        CursoEnProgreso cursoEP = new CursoEnProgreso(ControladorUsuario.INSTANCE.getUsuarioActual(), cursoP, null);
        modeloCursos.addElement(cursoEP);
        listaCursos.setModel(modeloCursos);

        if (ControladorUsuario.INSTANCE.isUserCreator()) {
            modeloCursosCreados.clear();
            for (CursoPlantilla curso : getCursosCreados()) {
                modeloCursosCreados.addElement(curso);
            }
            listaCursosCreados.setModel(modeloCursosCreados);
        }
    }
}
