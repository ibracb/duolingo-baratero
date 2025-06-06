package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import umu.pds.duolingoBaratero.windows.components.CursoCellRenderer;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<CursoEnProgreso> listaCursos;
	private DefaultListModel<CursoEnProgreso> modeloCursos;
	private BarraSuperior panelSuperior;
	private final ControladorCursoPlantilla cPlantilla;
	private final ControladorUsuario cUsuario;
	private final ControladorCursoProgreso cProgreso;
	private final ControladorPregunta cPregunta;
	
	public VentanaPrincipal(ControladorUsuario cUsuario, ControladorCursoPlantilla cPlantilla, ControladorCursoProgreso cProgreso, ControladorPregunta cPregunta) {
		this.cUsuario = cUsuario;
		this.cPlantilla = cPlantilla;
		this.cProgreso  = cProgreso;
		this.cPregunta = cPregunta;
		setTitle("Continúa tus cursos");
		setSize(600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		panelSuperior = new BarraSuperior(this, cUsuario, cPlantilla, cProgreso, cPregunta);
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
		listaCursos.setCellRenderer(new CursoCellRenderer(cPlantilla));
		listaCursos.addListSelectionListener(e -> manejarSeleccionCursosEmpezados(listaCursos.getSelectedValue()));
		panelCursosEnProgreso.add(new JScrollPane(listaCursos), BorderLayout.CENTER);
		panelCentral.add(panelCursosEnProgreso);
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
		return cUsuario.getCursosUsuarioActual();
	}

	private synchronized void manejarSeleccionCursosEmpezados(CursoEnProgreso curso) {
	    // Aseguramos que solo un hilo acceda a este bloque a la vez.
	    if (curso != null) {
	        if (cProgreso.estaFinalizado(curso)) {
	            Object[] opciones = { "Sí", "No" };
	            int opcion = JOptionPane.showOptionDialog(this,
	                    "Has finalizado este curso. ¿Quieres empezarlo de nuevo?", "Aviso", JOptionPane.YES_NO_OPTION,
	                    JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);

	            if (opcion == JOptionPane.YES_OPTION) {
	                cProgreso.reiniciar(curso);
	            } else {
	                int index = listaCursos.getSelectedIndex(); // Obtener el índice seleccionado
	                if (index != -1) { // Verificar que se haya seleccionado un curso
	                    DefaultListModel<CursoEnProgreso> model = (DefaultListModel<CursoEnProgreso>) listaCursos.getModel();
	                    model.remove(index);
	                }
	                cUsuario.borrarCurso(curso);
	            }
	        } else {
	            // Si el curso no está finalizado, mostrar la ventana de preguntas
	            VentanaPregunta ventanaPregunta = new VentanaPregunta(curso, cProgreso, cPregunta);
	            ventanaPregunta.setVisible(true);
	        }
	    }
	    listaCursos.clearSelection(); // Limpiar la selección
	}


	public void refreshCursos() {
		modeloCursos.clear();
		for (CursoEnProgreso curso : getCursosEnProgreso()) {
			modeloCursos.addElement(curso);
		}
		listaCursos.setModel(modeloCursos);
	}

	private void abrirVentanaElegirCurso() {
		VentanaElegirCurso ventana = new VentanaElegirCurso(this, cPlantilla, cUsuario, cProgreso, cPregunta);
		ventana.setVisible(true);
		this.setVisible(false);
	}

}
