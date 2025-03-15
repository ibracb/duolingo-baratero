package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
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

		BarraSuperior panelSuperior = new BarraSuperior(this);
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
		btnNuevoCurso.addActionListener(e -> abrirVentanaElegirCurso());

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
		if (ControladorCurso.INSTANCE.isCursoNuevo(curso))
			//abrirVentanaConfiguracionCurso();			
			new VentanaPregunta(0).setVisible(true);
		else if (ControladorCurso.INSTANCE.isCursoEnMarcha(curso)) {
			VentanaPregunta ventana = new VentanaPregunta(ControladorCurso.INSTANCE.getNumLastBloqueContenido(curso));
			ventana.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(this, "Has finalizado este curso. ¿Quieres empezarlo de nuevo?",
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void refreshCursos() {
        modeloCursos.clear();
        for (CursoEnProgreso curso : getCursosEnProgreso()) {
            modeloCursos.addElement(curso);
        }
        CursoPlantilla cursoP = new CursoPlantilla("Idiomas", "Aprende nuevos idiomas", null, "title", Nivel.AVANZADO, null);
        CursoEnProgreso cursoEnProgreso = new CursoEnProgreso(ControladorUsuario.INSTANCE.getUsuarioActual(), cursoP, null, null, null);
		CursoEnProgreso cursoEP = cursoEnProgreso;
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
	
	private void abrirVentanaElegirCurso() {
		VentanaElegirCurso ventana = new VentanaElegirCurso(this);
		ventana.setVisible(true);
		this.setVisible(false);
	}


  
}
