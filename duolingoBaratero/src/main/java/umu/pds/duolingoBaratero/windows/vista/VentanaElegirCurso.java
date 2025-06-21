package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorEstadistica;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import umu.pds.duolingoBaratero.windows.components.MensajeTemporal;
import umu.pds.duolingoBaratero.windows.components.CursoCreadoCellRenderer;

/**
 * Ventana para elegir un curso de los cursos creados por el usuario.
 */
public class VentanaElegirCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Valor por defecto para los filtros de busqueda
	 */
	private final static String VALOR_DEFAULT_FILTROS = "";

	/**
	 * Campo de texto para el nombre del curso.
	 */
	private JTextField textFieldNombre;
	
	/**
	 * Campo de texto para el creador del curso.
	 */
	private JTextField textFieldCreador;
	
	/**
	 * Modelo de la lista de cursos creados.
	 */
	private DefaultListModel<CursoPlantilla> modeloCursosCreados;
	
	/**
	 * Lista que muestra los cursos creados.
	 */
	private JList<CursoPlantilla> listaCursosCreados;
	
	/**
	 * ComboBox para seleccionar el nivel del curso.
	 */
	private JComboBox<Nivel> comboBoxNiveles;
	
	/**
	 * Ventana principal de la aplicación.
	 */
	private VentanaPrincipal v;
	
	/**
	 * Controladores necesarios para la funcionalidad de la ventana.
	 */
	private final ControladorCursoPlantilla cPlantilla;
	
	
	/**
	 * Controlador de usuario.
	 */
	@SuppressWarnings("unused")
	private final ControladorUsuario cUsuario;
	
	/**
	 * Controlador de progreso del curso.
	 */
	@SuppressWarnings("unused")
	private final ControladorCursoProgreso cProgreso;
	
	/**
	 * Controlador de preguntas.
	 */
	@SuppressWarnings("unused")
	private final ControladorPregunta cPregunta;
	
	/**
	 * Controlador de estadísticas.
	 */
	private final ControladorEstadistica cEstadistica;
	
	/**
	 * Indica si la ventana es para exportar un curso.
	 */
	private boolean esParaExportar;
	
	/**
	 * Extensión del archivo para exportar el curso.
	 */
	private String extension;

	/**
	 * Constructor de la ventana para elegir un curso.
	 * 
	 * @param v Ventana principal de la aplicación.
	 * @param cPlantilla Controlador de cursos plantilla.
	 * @param cUsuario Controlador de usuario.
	 * @param cProgreso Controlador de progreso del curso.
	 * @param cPregunta Controlador de preguntas.
	 * @param cEstadistica Controlador de estadísticas.
	 */
	public VentanaElegirCurso(VentanaPrincipal v, ControladorCursoPlantilla cPlantilla, ControladorUsuario cUsuario, ControladorCursoProgreso cProgreso,
			ControladorPregunta cPregunta,ControladorEstadistica cEstadistica) {
		this.cPlantilla = cPlantilla;
		this.cUsuario = cUsuario;
		this.cPregunta = cPregunta;
		this.cProgreso = cProgreso;
		this.cEstadistica = cEstadistica;
		this.v = v;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400); // Ajusta el tamaño de la ventana
		this.setLocationRelativeTo(null); // Centra la ventana en la pantalla
		this.setVisible(true); // Muestra la ventana
		getContentPane().setLayout(new BorderLayout(0, 0));
		BarraSuperior panelSuperior = new BarraSuperior(this, cUsuario, cPlantilla, cProgreso, cPregunta, cEstadistica);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(e -> {this.dispose(); v.setVisible(true);} );
		panelInferior.add(btnNewButton);

		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[] { 20, 0, 0, 20, 0 };
		gbl_panelCentral.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCentral.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelCentral.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelCentral.setLayout(gbl_panelCentral);

		textFieldNombre = new JTextField();
		textFieldNombre.setText("Curso:");
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 1;
		panelCentral.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldCreador = new JTextField();
		textFieldCreador.setText("Propietario:");
		GridBagConstraints gbc_textFieldCreador = new GridBagConstraints();
		gbc_textFieldCreador.gridwidth = 2;
		gbc_textFieldCreador.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCreador.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCreador.gridx = 1;
		gbc_textFieldCreador.gridy = 2;
		panelCentral.add(textFieldCreador, gbc_textFieldCreador);
		textFieldCreador.setColumns(10);
		
		Nivel[] niveles = {Nivel.BASICO, Nivel.PRINCIPIANTE, Nivel.INTERMEDIO, Nivel.AVANZADO};
		comboBoxNiveles = new JComboBox<Nivel>(niveles);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		panelCentral.add(comboBoxNiveles, gbc_comboBox);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(e -> buscarCursos());
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 3;
		panelCentral.add(btnNewButton_1, gbc_btnNewButton_1);

		JPanel panelCursosCreados = new JPanel(new BorderLayout()); // Establece BorderLayout
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH; // Permite que el panel crezca
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 5;
		gbc_panel.weightx = 1.0; // Expande horizontalmente
		gbc_panel.weighty = 1.0; // Expande verticalmente
		panelCentral.add(panelCursosCreados, gbc_panel);

		JLabel labelCursosCreados = new JLabel("Cursos", SwingConstants.CENTER);
		panelCursosCreados.add(labelCursosCreados, BorderLayout.NORTH);

		modeloCursosCreados = new DefaultListModel<>();
		for (CursoPlantilla curso : cPlantilla.buscarCursos()) {
			modeloCursosCreados.addElement(curso);
		}
		listaCursosCreados = new JList<>(modeloCursosCreados);
		listaCursosCreados.setCellRenderer(new CursoCreadoCellRenderer(cPlantilla));
		listaCursosCreados.addListSelectionListener(e -> manejarSeleccionCurso(listaCursosCreados.getSelectedValue()));


		JScrollPane scrollPane = new JScrollPane(listaCursosCreados);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// Agrega el JScrollPane al panel en la posición CENTER para que ocupe todo el espacio
		panelCursosCreados.add(scrollPane, BorderLayout.CENTER);


	}
	
	/**
	 * Método para determinar si un campo de texto está completo.
	 * Si el campo contiene un valor, se devuelve el valor después de los dos puntos.
	 * Si no, se devuelve un valor por defecto.
	 * 
	 * @param texto El texto del campo a evaluar.
	 * @return El valor del campo o un valor por defecto si no está completo.
	 */
	private String isCompleted(String texto) {
		String [] partes = texto.split(":");
		return partes.length > 1 ? partes[1] : VALOR_DEFAULT_FILTROS;
	}
	
	/**
	 * Método para buscar cursos según los filtros introducidos por el usuario.
	 * Obtiene el nombre, propietario y nivel del curso, y actualiza la lista de cursos creados.
	 */
	private void buscarCursos() {
		String nombre, propietario;
		nombre = isCompleted(textFieldNombre.getText());
		propietario = isCompleted(textFieldCreador.getText());		
		Nivel lvl = (Nivel) comboBoxNiveles.getSelectedItem();
		modeloCursosCreados.clear();
		for (CursoPlantilla curso : cPlantilla.buscarCursos(nombre, propietario, lvl)) {
			modeloCursosCreados.addElement(curso);
		}
		listaCursosCreados.setModel(modeloCursosCreados);
	}
	
	/**
	 * Si el curso Progreso elegido ya lo esta realizando el usuario no se añade
	 * @param curso
	 */
	private void manejarSeleccionCurso(CursoPlantilla curso) {

		if (esParaExportar) {
			boolean exportado = cPlantilla.exportarCurso(curso, extension);

			if (exportado) {
			    JOptionPane.showMessageDialog(this,
			        "El curso se ha exportado correctamente en la carpeta 'cursos' del programa.",
			        "Exportación correcta",
			        JOptionPane.INFORMATION_MESSAGE);
			} else {
			    JOptionPane.showMessageDialog(this,
			        "No se pudo exportar el curso.",
			        "Error de exportación",
			        JOptionPane.ERROR_MESSAGE);
			}
			VentanaPrincipal ventana = new VentanaPrincipal(cUsuario, cPlantilla, cProgreso, cPregunta,cEstadistica);
			ventana.setVisible(true);
			this.dispose();

		}
		else if (cUsuario.estaCursando(curso)) {
			MensajeTemporal.mostrarMensaje("Ya estas realizando este curso, elige otro por favor", JOptionPane.WARNING_MESSAGE);

		}
		else {
			if (cUsuario.addCursosEnProgreso(curso)) {
	    		v.refreshCursos();
	    		v.setVisible(true);
	    		this.dispose();
	    	}else {
	    		System.err.println("Algo salio mal eligiendo el curso");
	    	}
		}
		
	}

	/**
	 * Método para establecer si la ventana es para exportar un curso.
	 * 
	 * @param esParaExportar Indica si la ventana es para exportar un curso.
	 */
	public void setEsParaExportar(boolean esParaExportar) {
		this.esParaExportar = esParaExportar;
	}

	/**
	 * Método para establecer la extensión del archivo para exportar el curso.
	 * 
	 * @param extension La extensión del archivo.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	

}
