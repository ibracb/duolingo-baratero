package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import umu.pds.duolingoBaratero.windows.components.CursoCellRenderer;
import umu.pds.duolingoBaratero.windows.components.CursoCreadoCellRenderer;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class VentanaElegirCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int VALORACION_DEFAULT = -1;
	private static final String VALOR_DEFAULT = null;
	private static final String ORDEN_DEFAULT = "Mas cursados";

	
	private JTextField textFieldNombre;
	private JTextField textFieldCreador;
	private JTextField textFieldValoracion;
	private DefaultListModel<CursoPlantilla> modeloCursosCreados;
	private JList<CursoPlantilla> listaCursosCreados;
	private VentanaPrincipal v;
	private JComboBox<String> comboBoxMasUsados;
	

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

		textFieldValoracion = new JTextField();
		textFieldValoracion.setText("Valoracion:");
		GridBagConstraints gbc_textFieldValoracion = new GridBagConstraints();
		gbc_textFieldValoracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldValoracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValoracion.gridx = 1;
		gbc_textFieldValoracion.gridy = 3;
		panelCentral.add(textFieldValoracion, gbc_textFieldValoracion);
		textFieldValoracion.setColumns(10);

		String[] comboBoxOptiones = {"Más cursados", "Menos cursados"};
		comboBoxMasUsados = new JComboBox<>(new DefaultComboBoxModel<>(comboBoxOptiones));

		GridBagConstraints gbc_comboBoxMasUsados = new GridBagConstraints();
		gbc_comboBoxMasUsados.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMasUsados.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMasUsados.gridx = 2;
		gbc_comboBoxMasUsados.gridy = 3;
		panelCentral.add(comboBoxMasUsados, gbc_comboBoxMasUsados);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(e -> buscarCursos());
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 4;
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
		for (CursoPlantilla curso : ControladorCurso.INSTANCE.buscarCursos(VALOR_DEFAULT,VALORACION_DEFAULT,VALOR_DEFAULT,ORDEN_DEFAULT)) {
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
	
	private void buscarCursos() {
		String nombre, propietario;
		nombre = textFieldNombre.getText();
		propietario = textFieldCreador.getText();
		int valoracion = textFieldValoracion.getText() != null && textFieldValoracion.getText().matches("\\d+") ?  Integer.parseInt(textFieldValoracion.getText()) : VALORACION_DEFAULT;
		String orden = (String)comboBoxMasUsados.getSelectedItem();
		
		modeloCursosCreados.clear();
		for (CursoPlantilla curso : ControladorCurso.INSTANCE.buscarCursos(nombre, valoracion, propietario, orden)) {
			modeloCursosCreados.addElement(curso);
		}
		modeloCursosCreados.addElement(new CursoPlantilla("Idiomas",ControladorUsuario.INSTANCE.getUsuarioActual(), null, "title", Nivel.AVANZADO, null));

		listaCursosCreados.setModel(modeloCursosCreados);
	}
	
	private void manejarSeleccionCurso(CursoPlantilla curso) {
		ControladorUsuario.INSTANCE.addCursosEnProgreso(curso);
		v.refreshCursos();
		this.closeWindow();
	}

	private void closeWindow() {
		v.setVisible(true);
		this.dispose();
	}

}
