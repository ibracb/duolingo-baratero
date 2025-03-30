package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.windows.components.BarraInferiorPreguntas;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;

public class VentanaCreaPreguntaOpciones extends JFrame implements VentanaCreaPregunta {
    private static final long serialVersionUID = 1L;
    private static final TipoPregunta TIPO_PREGUNTA = TipoPregunta.OPCIONES;
    private JTextField txtPregunta;
    private JTextField txtRespuesta;
    private JTextField txtRespuesta_1;
    private JTextField txtRespuesta_2;
    private JComboBox<String> comboOpciones;
    private JComboBox<Nivel> comboNiveles;
    private BarraSuperior barraSuperior;
    private BarraInferiorPreguntas barraInferior;
    private VentanaCreaTuCurso v;
    

    public VentanaCreaPreguntaOpciones(VentanaCreaTuCurso v) {
        this.v = v;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setSize(550, 400);
        setLocationRelativeTo(null);

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
        gbl_panel_2.columnWidths = new int[]{20, 0,0 , 20}; // 4 columnas
        gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0}; // La columna 1 se expande
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0}; // Expande los JTextFields
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
        gbc_txtPregunta.fill = GridBagConstraints.BOTH;
        gbc_txtPregunta.gridx = 1;
        gbc_txtPregunta.gridy = 2;
        gbc_txtPregunta.gridwidth = 2;  // Ocupa 2 columnas
        gbc_txtPregunta.weightx = 1.0;
        gbc_txtPregunta.weighty = 1.0;
        panel_2.add(txtPregunta, gbc_txtPregunta);

        // Campo Respuesta 1
        txtRespuesta = new JTextField("Respuesta 1:");
        GridBagConstraints gbc_txtRespuesta = new GridBagConstraints();
        gbc_txtRespuesta.insets = new Insets(5, 5, 5, 5);
        gbc_txtRespuesta.fill = GridBagConstraints.BOTH;
        gbc_txtRespuesta.gridx = 1;
        gbc_txtRespuesta.gridy = 3;
        gbc_txtRespuesta.gridwidth = 2;  // Ocupa 2 columnas
        gbc_txtRespuesta.weightx = 1.0;
        gbc_txtRespuesta.weighty = 1.0;
        panel_2.add(txtRespuesta, gbc_txtRespuesta);

        // Campo Respuesta 2
        txtRespuesta_1 = new JTextField("Respuesta 2:");
        GridBagConstraints gbc_txtRespuesta_1 = new GridBagConstraints();
        gbc_txtRespuesta_1.insets = new Insets(5, 5, 5, 5);
        gbc_txtRespuesta_1.fill = GridBagConstraints.BOTH;
        gbc_txtRespuesta_1.gridx = 1;
        gbc_txtRespuesta_1.gridy = 4;
        gbc_txtRespuesta_1.gridwidth = 2;  // Ocupa 2 columnas
        gbc_txtRespuesta_1.weightx = 1.0;
        gbc_txtRespuesta_1.weighty = 1.0;
        panel_2.add(txtRespuesta_1, gbc_txtRespuesta_1);

        // Campo Respuesta 3
        txtRespuesta_2 = new JTextField("Respuesta 3:");
        GridBagConstraints gbc_txtRespuesta_2 = new GridBagConstraints();
        gbc_txtRespuesta_2.insets = new Insets(5, 5, 5, 5);
        gbc_txtRespuesta_2.fill = GridBagConstraints.BOTH;
        gbc_txtRespuesta_2.gridx = 1;
        gbc_txtRespuesta_2.gridy = 5;
        gbc_txtRespuesta_2.gridwidth = 2;  // Ocupa 2 columnas
        gbc_txtRespuesta_2.weightx = 1.0;
        gbc_txtRespuesta_2.weighty = 1.0;
        panel_2.add(txtRespuesta_2, gbc_txtRespuesta_2);

        // ComboBox de opciones
        String[] opciones = {"Respuesta 1", "Respuesta 2", "Respuesta 3"};
        comboOpciones = new JComboBox<>(opciones);
        GridBagConstraints gbc_comboOpciones = new GridBagConstraints();
        gbc_comboOpciones.insets = new Insets(5, 5, 5, 5);
        gbc_comboOpciones.fill = GridBagConstraints.BOTH;
        gbc_comboOpciones.gridx = 1;
        gbc_comboOpciones.gridy = 6;
        gbc_comboOpciones.weightx = 1.0;
        panel_2.add(comboOpciones, gbc_comboOpciones);

        // ComboBox de niveles
        Nivel[] niveles = {Nivel.PRINCIPIANTE, Nivel.BASICO, Nivel.INTERMEDIO, Nivel.AVANZADO};
        comboNiveles = new JComboBox<>(niveles);
        GridBagConstraints gbc_comboNiveles = new GridBagConstraints();
        gbc_comboNiveles.insets = new Insets(5, 5, 5, 5);
        gbc_comboNiveles.fill = GridBagConstraints.BOTH;
        gbc_comboNiveles.gridx = 2; // Lo ubicamos en la columna 2
        gbc_comboNiveles.gridy = 6;
        gbc_comboNiveles.weightx = 1.0;
        panel_2.add(comboNiveles, gbc_comboNiveles);
    }

    public VentanaCreaTuCurso getVentanaCreaTuCurso() {
        return v;
    }

    public void closeWindow() {
        v.setVisible(true);
        cerrar();
    }

    public void cerrar() {
        this.dispose();
    }
    
    public boolean guardarPregunta() {
    	String pregunta, respuesta1, respuesta2, respuesta3, respuestaCorrecta;
    	Nivel lvl = (Nivel)comboNiveles.getSelectedItem();
    	pregunta = txtPregunta.getText();
    	respuestaCorrecta = (String)comboOpciones.getSelectedItem();
    	respuesta1 = txtRespuesta.getText();
    	respuesta2 = txtRespuesta_1.getText();
    	respuesta3 = txtRespuesta_2.getText();    	
    	switch(respuestaCorrecta) {
    	case "Respuesta 1": respuestaCorrecta = respuesta1;
    						break;
    	case "Respuesta 2": respuestaCorrecta = respuesta2;
    						break;
    	case "Respuesta 3": respuestaCorrecta = respuesta3;
    						break;
    	}
    	
    	if (!hasRequiredFileds(pregunta, respuesta1, respuesta2, respuesta3, respuestaCorrecta, lvl)) {
    		String[] opciones = {respuesta1, respuesta2, respuesta3};
        	PreguntaOpciones preguntaOpciones = new PreguntaOpciones(lvl, 0, pregunta, respuestaCorrecta, TIPO_PREGUNTA, opciones);
        	v.guardarPregunta(preguntaOpciones);
        	return true;
    	}
    	else {
    		JOptionPane.showMessageDialog(this, "Tienes que rellenar todos los parametros", "Error",
					JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    	
    }
    


	private boolean hasRequiredFileds(String pregunta, String respuesta1, String respuesta2, String respuesta3,String respuestaCorrecta, Nivel nivel) {
    	return pregunta == null || respuesta1 == null || respuesta3 == null || respuesta3 == null ||  respuestaCorrecta == null || nivel == null;
    }
}
