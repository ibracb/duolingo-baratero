package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import umu.pds.duolingoBaratero.windows.components.CursoCellRenderer;
import umu.pds.duolingoBaratero.windows.components.CursoCreadoCellRenderer;
import umu.pds.duolingoBaratero.windows.utility.Constantes;

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
		JButton btnNuevoCurso = new JButton("Empieza un nuevo curso");
		btnNuevoCurso.addActionListener(e -> abrirVentanaElegirCurso());

		panelBotones.add(btnNuevoCurso);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Cargar datos iniciales
		refreshCursos();
	}

	private Set<CursoEnProgreso> getCursosEnProgreso() {
		return ControladorUsuario.INSTANCE.getCursosUsuarioActual();
	}

	private List<CursoPlantilla> getCursosCreados() {
		return ControladorUsuario.INSTANCE.getCursosCreadosUsuarioActual();
	}

	private synchronized void manejarSeleccionCursosEmpezados(CursoEnProgreso curso) {
	    // Aseguramos que solo un hilo acceda a este bloque a la vez.
	    if (curso != null) {
	        if (ControladorCurso.INSTANCE.isCursoFinalizado(curso)) {

	            Object[] opciones = { "Sí", "No" };
	            int opcion = JOptionPane.showOptionDialog(this,
	                    "Has finalizado este curso. ¿Quieres empezarlo de nuevo?", "Aviso", JOptionPane.YES_NO_OPTION,
	                    JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);

	            if (opcion == JOptionPane.YES_OPTION) {
	                ControladorCurso.INSTANCE.reiniciarCurso(curso);
	            } else {
	                int index = listaCursos.getSelectedIndex(); // Obtener el índice seleccionado
	                if (index != -1) { // Verificar que se haya seleccionado un curso
	                    DefaultListModel<CursoEnProgreso> model = (DefaultListModel<CursoEnProgreso>) listaCursos.getModel();
	                    model.remove(index);
	                }
	                ControladorUsuario.INSTANCE.borrarCurso(curso);
	            }
	        } else {
	            // Si el curso no está finalizado, mostrar la ventana de preguntas
	            VentanaPregunta ventanaPregunta = new VentanaPregunta(curso);
	            ventanaPregunta.setVisible(true);
	        }
	    }
	    listaCursos.clearSelection(); // Limpiar la selección

	    // Descomentamos esta línea si quieres mostrar un mensaje cuando no hay curso seleccionado
	    // else {
	    //     JOptionPane.showMessageDialog(this, "No hay ningún curso seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
	    // }
	}


	public void refreshCursos() {
		modeloCursos.clear();
		for (CursoEnProgreso curso : getCursosEnProgreso()) {
			modeloCursos.addElement(curso);
		}
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

	public void compartirCurso() {
		if (listaCursos.getSelectedIndex() != -1) {
			CursoEnProgreso curso = listaCursos.getSelectedValue();
			ControladorCurso.INSTANCE.compartirCurso(curso);
			Constantes.mostrarMensaje("Seguro que quieres exportar el curso: " + curso.getNombre(),
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			Constantes.mostrarMensaje("No hay ningun curso seleccionado ", JOptionPane.ERROR_MESSAGE);
		}

	}

}
