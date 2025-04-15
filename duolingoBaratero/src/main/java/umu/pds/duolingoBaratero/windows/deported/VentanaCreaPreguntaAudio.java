package umu.pds.duolingoBaratero.windows.deported;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.windows.components.BarraInferiorPreguntas;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;

public class VentanaCreaPreguntaAudio extends JFrame implements VentanaCreaPregunta {
    private static final long serialVersionUID = 1L;
    private static final TipoPregunta TIPO_PREGUNTA = TipoPregunta.AUDIO;
    private JTextField txtPregunta;
    private JTextField txtRespuesta;
    private JTextField txtRespuesta_1;
    private JTextField txtRespuesta_2;
    private JComboBox<String> comboOpciones;
    private JComboBox<Nivel> comboNiveles;
    private BarraSuperior barraSuperior;
    private BarraInferiorPreguntas barraInferior;
    private VentanaCreaTuCurso v;
    private JLabel lblArchivoAudio;
    private JButton btnSeleccionarAudio;
    private File archivoAudioSeleccionado;


    public VentanaCreaPreguntaAudio(VentanaCreaTuCurso v) {
        this.v = v;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        barraInferior = new BarraInferiorPreguntas(this);
        panel.add(barraInferior);

        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        barraSuperior = new BarraSuperior(this);
        panel_1.add(barraSuperior);

        JPanel panel_2 = new JPanel();
        getContentPane().add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.weightx = 0.1;
        panel_2.add(Box.createHorizontalStrut(20), gbc);

        gbc.gridx = 1; gbc.weightx = 0;
        JLabel lblPregunta = new JLabel("Escribe tu pregunta:");
        panel_2.add(lblPregunta, gbc);

        gbc.gridx = 2; gbc.gridwidth = 2; gbc.weightx = 1.0;
        txtPregunta = new JTextField(30);
        panel_2.add(txtPregunta, gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 1;
        JLabel lblRespuesta1 = new JLabel("Respuesta 1:");
        panel_2.add(lblRespuesta1, gbc);
        
        gbc.gridx = 2; gbc.gridwidth = 2;
        txtRespuesta = new JTextField(30);
        panel_2.add(txtRespuesta, gbc);

        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 1;
        JLabel lblRespuesta2 = new JLabel("Respuesta 2:");
        panel_2.add(lblRespuesta2, gbc);
        
        gbc.gridx = 2; gbc.gridwidth = 2;
        txtRespuesta_1 = new JTextField(30);
        panel_2.add(txtRespuesta_1, gbc);

        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 1;
        JLabel lblRespuesta3 = new JLabel("Respuesta 3:");
        panel_2.add(lblRespuesta3, gbc);
        
        gbc.gridx = 2; gbc.gridwidth = 2;
        txtRespuesta_2 = new JTextField(30);
        panel_2.add(txtRespuesta_2, gbc);

        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 1;
        JLabel lblRespuestaCorrecta = new JLabel("Respuesta Correcta:");
        panel_2.add(lblRespuestaCorrecta, gbc);
        
        gbc.gridx = 2; gbc.gridwidth = 2;
        comboOpciones = new JComboBox<>(new String[]{"Respuesta 1", "Respuesta 2", "Respuesta 3"});
        panel_2.add(comboOpciones, gbc);

        gbc.gridx = 1; gbc.gridy = 5; gbc.gridwidth = 1;
        JLabel lblNivel = new JLabel("Nivel:");
        panel_2.add(lblNivel, gbc);
        
        gbc.gridx = 2; gbc.gridwidth = 2;
        comboNiveles = new JComboBox<>(new Nivel[]{Nivel.PRINCIPIANTE, Nivel.BASICO, Nivel.INTERMEDIO, Nivel.AVANZADO});
        panel_2.add(comboNiveles, gbc);

        gbc.gridx = 1; gbc.gridy = 6; gbc.gridwidth = 1;
        JLabel lblAudio = new JLabel("Archivo de Audio:");
        panel_2.add(lblAudio, gbc);
        
        gbc.gridx = 2; gbc.gridwidth = 2;
        btnSeleccionarAudio = new JButton("Elige el audio");
        panel_2.add(btnSeleccionarAudio, gbc);

        gbc.gridx = 2; gbc.gridy = 7; gbc.gridwidth = 2;
        lblArchivoAudio = new JLabel("No se ha seleccionado archivo");
        panel_2.add(lblArchivoAudio, gbc);

        gbc.gridx = 4; gbc.gridy = 0; gbc.gridheight = 8; gbc.weightx = 0.1;
        panel_2.add(Box.createHorizontalStrut(20), gbc);

        btnSeleccionarAudio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarArchivoAudio();
            }
        });
    }
  
    
    private void seleccionarArchivoAudio() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Selecciona un archivo de audio");
        
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivoAudioSeleccionado = fileChooser.getSelectedFile();
            lblArchivoAudio.setText(archivoAudioSeleccionado.getName());
        }
    }

	@Override
	public void closeWindow() {
        v.setVisible(true);
        this.dispose();
	}

	@Override
	public void guardarPregunta() {
    	String pregunta, respuesta1, respuesta2, respuesta3, respuestaCorrecta;
    	Nivel lvl = (Nivel)comboNiveles.getSelectedItem();
    	pregunta = txtPregunta.getText();
    	respuestaCorrecta = (String)comboOpciones.getSelectedItem();
    	respuesta1 = txtRespuesta.getText();
    	respuesta2 = txtRespuesta_1.getText();
    	respuesta3 = txtRespuesta_2.getText();    	
    	switch(respuestaCorrecta) {
    	case "Respuesta 1": respuestaCorrecta = "Respuesta 1";
    						break;
    	case "Respuesta 2": respuestaCorrecta = "Respuesta 2";
    						break;
    	case "Respuesta 3": respuestaCorrecta = "Respuesta 3";
    						break;
    	}
    	if (!hasRequiredFileds(pregunta, respuesta1, respuesta2, respuesta3, respuestaCorrecta, lvl)) {
    		String[] opciones = {respuesta1, respuesta2, respuesta3};
        	PreguntaOpciones preguntaOpciones = new PreguntaOpciones(lvl, 0, pregunta, respuestaCorrecta, TIPO_PREGUNTA, opciones);
        	v.guardarPregunta(preguntaOpciones);
        	v.setVisible(true);
        	this.dispose();
    	}
    	else {
    		JOptionPane.showMessageDialog(this, "Tienes que rellenar todos los parametros", "Error",
					JOptionPane.ERROR_MESSAGE);
    	}
    			
	}
	
	private boolean hasRequiredFileds(String pregunta, String respuesta1, String respuesta2, String respuesta3,String respuestaCorrecta, Nivel nivel) {
    	return pregunta == null || respuesta1 == null || respuesta2 == null || respuesta3 == null ||  respuestaCorrecta == null || nivel == null;
    }
}