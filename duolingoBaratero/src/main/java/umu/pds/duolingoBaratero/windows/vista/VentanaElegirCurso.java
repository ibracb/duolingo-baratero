package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import umu.pds.duolingoBaratero.windows.components.CursoCreadoCellRenderer;
import umu.pds.duolingoBaratero.windows.utility.Constantes;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaElegirCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private final static String VALOR_DEFAULT_FILTROS = "";

	private JTextField textFieldNombre;
	private JTextField textFieldCreador;
	private DefaultListModel<CursoPlantilla> modeloCursosCreados;
	private JList<CursoPlantilla> listaCursosCreados;
	private JComboBox<Nivel> comboBoxNiveles;
	private VentanaPrincipal v;


	public VentanaElegirCurso(VentanaPrincipal v) {
		this.v = v;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400); // Ajusta el tamaño de la ventana
		this.setLocationRelativeTo(null); // Centra la ventana en la pantalla
		this.setVisible(true); // Muestra la ventana
		getContentPane().setLayout(new BorderLayout(0, 0));
		BarraSuperior panelSuperior = new BarraSuperior(this);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);

		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(e -> closeWindow());
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
		for (CursoPlantilla curso : ControladorCurso.INSTANCE.buscarCursos()) {
			modeloCursosCreados.addElement(curso);
		}
		listaCursosCreados = new JList<>(modeloCursosCreados);
		listaCursosCreados.setCellRenderer(new CursoCreadoCellRenderer());
		listaCursosCreados.addListSelectionListener(e -> manejarSeleccionCurso(listaCursosCreados.getSelectedValue()));


		JScrollPane scrollPane = new JScrollPane(listaCursosCreados);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// Agrega el JScrollPane al panel en la posición CENTER para que ocupe todo el espacio
		panelCursosCreados.add(scrollPane, BorderLayout.CENTER);


	}
	
	private String isCompleted(String texto) {
		String [] partes = texto.split(":");
		return partes.length > 1 ? partes[1] : VALOR_DEFAULT_FILTROS;
	}
	
	private void buscarCursos() {
		String nombre, propietario;
		nombre = isCompleted(textFieldNombre.getText());
		propietario = isCompleted(textFieldCreador.getText());		
		Nivel lvl = (Nivel) comboBoxNiveles.getSelectedItem();
		modeloCursosCreados.clear();
		for (CursoPlantilla curso : ControladorCurso.INSTANCE.buscarCursos(nombre, propietario, lvl)) {
			modeloCursosCreados.addElement(curso);
		}
		listaCursosCreados.setModel(modeloCursosCreados);
	}
	
	/**
	 * Si el curso Progreso elegido ya lo esta realizando el usuario no se añade
	 * TODO: Falta ver pq dos cursos progreso que se creand en diferente vez no tienen el mismo id 
	 * Preguntar a Jorge en caso de duda.
	 * @param curso
	 */
	private void manejarSeleccionCurso(CursoPlantilla curso) {
		if (ControladorUsuario.INSTANCE.estaCursando(curso)) {
			Constantes.mostrarMensaje("Ya estas realizando este curso, elige otro por favor", JOptionPane.WARNING_MESSAGE);
		}
		else {
			openVentanaEstrategia(curso);
		}
	}
	
	private void openVentanaEstrategia(CursoPlantilla curso) {
		VentanaSeleccionEstrategica ventana = new VentanaSeleccionEstrategica(v,curso);
		ventana.setVisible(true);
		this.dispose();
	}

	private void closeWindow() {
		this.dispose();
	}

}
