package umu.pds.duolingoBaratero.windows.deported;

import javax.swing.*;
import java.awt.*;

import umu.pds.duolingoBaratero.models.Flashcard;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.windows.components.BarraInferiorPreguntas;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;

public class VentanaCreaPreguntaFlashcard extends JFrame implements VentanaCreaPregunta {
	private static final long serialVersionUID = 1L;
	private static final TipoPregunta TIPO_PREGUNTA = TipoPregunta.FLASHCARD;
	private JTextField txtPregunta;
	private JTextField txtRespuesta;
	private JComboBox<Nivel> comboNiveles;
	private BarraSuperior barraSuperior;
	private BarraInferiorPreguntas barraInferior;
	private VentanaCreaTuCurso v;

	public VentanaCreaPreguntaFlashcard(VentanaCreaTuCurso v) {
		this.v = v;
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setSize(550, 300);

		// Panel Inferior
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		barraInferior = new BarraInferiorPreguntas(this);
		panel.add(barraInferior);

		// Panel Superior
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		barraSuperior = new BarraSuperior(this);
		panel_1.add(barraSuperior);

		// Panel Central con GridBagLayout
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 20, 0, 20 }; // 4 columnas
		gbl_panel_2.rowHeights = new int[] { 0, 30, 30, 30 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0 }; // La columna 1 se expande
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 }; // Expande los JTextFields
		panel_2.setLayout(gbl_panel_2);

		// Etiqueta
		JLabel lblNewLabel = new JLabel("Escribe tu pregunta:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);

		// Campo Pregunta
		txtPregunta = new JTextField();
		txtPregunta.setText("Pregunta:");
		GridBagConstraints gbc_txtPregunta = new GridBagConstraints();
		gbc_txtPregunta.insets = new Insets(5, 5, 5, 5);
		gbc_txtPregunta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPregunta.gridx = 1;
		gbc_txtPregunta.gridy = 1;
		gbc_txtPregunta.weightx = 1.0;
		gbc_txtPregunta.weighty = 1.0;
		panel_2.add(txtPregunta, gbc_txtPregunta);

		// Campo Respuesta 1
		txtRespuesta = new JTextField("Respuesta:");
		GridBagConstraints gbc_txtRespuesta = new GridBagConstraints();
		gbc_txtRespuesta.insets = new Insets(5, 5, 5, 5);
		gbc_txtRespuesta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRespuesta.gridx = 1;
		gbc_txtRespuesta.gridy = 2;
		gbc_txtRespuesta.weightx = 1.0;
		gbc_txtRespuesta.weighty = 1.0;
		panel_2.add(txtRespuesta, gbc_txtRespuesta);

		// ComboBox de niveles
		Nivel[] niveles = { Nivel.PRINCIPIANTE, Nivel.BASICO, Nivel.INTERMEDIO, Nivel.AVANZADO };
		comboNiveles = new JComboBox<>(niveles);
		GridBagConstraints gbc_comboNiveles = new GridBagConstraints();
		gbc_comboNiveles.insets = new Insets(5, 5, 0, 5);
		gbc_comboNiveles.fill = GridBagConstraints.BOTH;
		gbc_comboNiveles.gridx = 1; // Lo ubicamos en la columna 2
		gbc_comboNiveles.gridy = 3;
		gbc_comboNiveles.weightx = 1.0;
		panel_2.add(comboNiveles, gbc_comboNiveles);
	}
	
	@Override
	public void closeWindow() {
		v.setVisible(true);
		this.dispose();
	}
	
	@Override
	public void guardarPregunta() {
		String pregunta, respuesta1;
		Nivel lvl = (Nivel) comboNiveles.getSelectedItem();
		pregunta = txtPregunta.getText();
		respuesta1 = txtRespuesta.getText();

		if (!hasRequiredFileds(pregunta, respuesta1, lvl)) {
			Flashcard preguntaFlashcard = new Flashcard(lvl, 0, pregunta, respuesta1, TIPO_PREGUNTA);
			v.guardarPregunta(preguntaFlashcard);
			v.setVisible(true);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Tienes que rellenar todos los parametros", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean hasRequiredFileds(String pregunta, String respuesta1, Nivel nivel) {
		return pregunta == null || respuesta1 == null || nivel == null;
	}
}
