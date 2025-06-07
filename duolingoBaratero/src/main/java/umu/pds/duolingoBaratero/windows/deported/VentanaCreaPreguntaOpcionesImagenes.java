package umu.pds.duolingoBaratero.windows.deported;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.windows.components.BarraInferiorPreguntas;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import umu.pds.duolingoBaratero.windows.vista.VentanaCambiaImagenes;
import umu.pds.duolingoBaratero.windows.vista.VentanaCambioImagen;

public class VentanaCreaPreguntaOpcionesImagenes extends JFrame implements VentanaCreaPregunta, VentanaCambiaImagenes {
    private static final long serialVersionUID = 1L;
    private static final TipoPregunta TIPO_PREGUNTA = TipoPregunta.IMAGENES;
    private static final int BOTON_RESPUESTA_1 = 1;
    private static final int BOTON_RESPUESTA_2 = 2;
    private static final int BOTON_RESPUESTA_3 = 3;
    private static final int DEFAUL_HEIGHT_AND_WIDTH = 80;

    private JTextField txtPregunta;
    private JComboBox<String> comboOpciones;
    private JComboBox<Nivel> comboNiveles;
    private BarraSuperior barraSuperior;
    private BarraInferiorPreguntas barraInferior;
    private VentanaCreaTuCurso v;
    private JButton btnPregunta1, btnPregunta2, btnPregunta3;
    private URL url;
    private File destinationFile;
    private int botonSeleccionado;
    private JLabel lblPregunta1, lblPregunta2, lblPregunta3;
    private JLabel lblImagenPregunta1, lblImagenPregunta2, lblImagenPregunta3, lblRespuestaCorrecta, lblNivel;

    public VentanaCreaPreguntaOpcionesImagenes(VentanaCreaTuCurso v) {
        this.v = v;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setSize(550, 450);
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
        gbl_panel_2.columnWidths = new int[]{20, 0, 0, 0,0, 0 , 20}; // 4 columnas
        gbl_panel_2.rowHeights = new int[]{20, 55, 0, 0, 0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0}; // La columna 1 se expande
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0}; // Expande los JTextFields
        panel_2.setLayout(gbl_panel_2);
        ImageIcon iconoImagenNotFound = new ImageIcon(getClass().getResource("/image_not_found.png"));
        
                // Etiqueta
                JLabel lblNewLabel = new JLabel("Escribe tu pregunta:");
                GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
                gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
                gbc_lblNewLabel.gridwidth = 4;
                gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
                gbc_lblNewLabel.gridx = 1;
                gbc_lblNewLabel.gridy = 0;
                panel_2.add(lblNewLabel, gbc_lblNewLabel);

        // Campo Pregunta
        txtPregunta = new JTextField();
        txtPregunta.setText("Pregunta:");
        GridBagConstraints gbc_txtPregunta = new GridBagConstraints();
        gbc_txtPregunta.gridwidth = 4;
        gbc_txtPregunta.insets = new Insets(5, 5, 5, 5);
        gbc_txtPregunta.fill = GridBagConstraints.BOTH;
        gbc_txtPregunta.gridx = 1;
        gbc_txtPregunta.gridy = 1;
        gbc_txtPregunta.weightx = 1.0;
        gbc_txtPregunta.weighty = 1.0;
        panel_2.add(txtPregunta, gbc_txtPregunta);
        btnPregunta1 = new JButton("Elegir Imagen");
        btnPregunta1.addActionListener(e -> abrirVentanaCambioImagen(BOTON_RESPUESTA_1));
        lblPregunta1 = new JLabel("Respuesta 1");
        lblPregunta1.setHorizontalTextPosition(SwingConstants.LEFT);
        lblPregunta1.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblPregunta1 = new GridBagConstraints();
        gbc_lblPregunta1.anchor = GridBagConstraints.EAST;
        gbc_lblPregunta1.insets = new Insets(0, 0, 5, 5);
        gbc_lblPregunta1.gridx = 1;
        gbc_lblPregunta1.gridy = 2;
        panel_2.add(lblPregunta1, gbc_lblPregunta1);
        lblImagenPregunta1 = new JLabel();
		lblImagenPregunta1.setIcon(ControladorUsuario.INSTANCE.getScaledImage(iconoImagenNotFound, DEFAUL_HEIGHT_AND_WIDTH));
        GridBagConstraints gbc_lblImagenPregunta1 = new GridBagConstraints();
        gbc_lblImagenPregunta1.gridwidth = 2;
        gbc_lblImagenPregunta1.insets = new Insets(0, 0, 5, 5);
        gbc_lblImagenPregunta1.gridx = 2;
        gbc_lblImagenPregunta1.gridy = 2;
        panel_2.add(lblImagenPregunta1, gbc_lblImagenPregunta1);
        GridBagConstraints gbc_btnPregunta1 = new GridBagConstraints();
        gbc_btnPregunta1.insets = new Insets(0, 0, 5, 5);
        gbc_btnPregunta1.gridx = 4;
        gbc_btnPregunta1.gridy = 2;
        panel_2.add(btnPregunta1, gbc_btnPregunta1);
        
        btnPregunta2 = new JButton("Elegir Imagen");
        btnPregunta2.addActionListener(e -> abrirVentanaCambioImagen(BOTON_RESPUESTA_2));        
        lblPregunta2 = new JLabel("Respuesta 2");
        lblPregunta2.setHorizontalAlignment(SwingConstants.LEFT);
        lblPregunta2.setHorizontalTextPosition(SwingConstants.LEFT);
        GridBagConstraints gbc_lblPregunta2 = new GridBagConstraints();
        gbc_lblPregunta2.anchor = GridBagConstraints.EAST;
        gbc_lblPregunta2.insets = new Insets(0, 0, 5, 5);
        gbc_lblPregunta2.gridx = 1;
        gbc_lblPregunta2.gridy = 3;
        panel_2.add(lblPregunta2, gbc_lblPregunta2);        
        lblImagenPregunta2 = new JLabel();
        lblImagenPregunta2.setIcon(ControladorUsuario.INSTANCE.getScaledImage(iconoImagenNotFound, DEFAUL_HEIGHT_AND_WIDTH));
        GridBagConstraints gbc_lblImagenPregunta2 = new GridBagConstraints();
        gbc_lblImagenPregunta2.gridwidth = 2;
        gbc_lblImagenPregunta2.insets = new Insets(0, 0, 5, 5);
        gbc_lblImagenPregunta2.gridx = 2;
        gbc_lblImagenPregunta2.gridy = 3;
        panel_2.add(lblImagenPregunta2, gbc_lblImagenPregunta2);
        GridBagConstraints gbc_btnPregunta2 = new GridBagConstraints();
        gbc_btnPregunta2.insets = new Insets(0, 0, 5, 5);
        gbc_btnPregunta2.gridx = 4;
        gbc_btnPregunta2.gridy = 3;
        panel_2.add(btnPregunta2, gbc_btnPregunta2);
        
        btnPregunta3 = new JButton("Elegir Imagen");
        btnPregunta3.addActionListener(e -> abrirVentanaCambioImagen(BOTON_RESPUESTA_3));
        lblPregunta3 = new JLabel("Respuesta 3");
        lblPregunta3.setHorizontalTextPosition(SwingConstants.LEFT);
        lblPregunta3.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblPregunta3 = new GridBagConstraints();
        gbc_lblPregunta3.anchor = GridBagConstraints.EAST;
        gbc_lblPregunta3.insets = new Insets(0, 0, 5, 5);
        gbc_lblPregunta3.gridx = 1;
        gbc_lblPregunta3.gridy = 4;
        panel_2.add(lblPregunta3, gbc_lblPregunta3);        
        lblImagenPregunta3 = new JLabel();
        lblImagenPregunta3.setIcon(ControladorUsuario.INSTANCE.getScaledImage(iconoImagenNotFound, DEFAUL_HEIGHT_AND_WIDTH));
        GridBagConstraints gbc_lblImagenPregunta3 = new GridBagConstraints();
        gbc_lblImagenPregunta3.gridwidth = 2;
        gbc_lblImagenPregunta3.insets = new Insets(0, 0, 5, 5);
        gbc_lblImagenPregunta3.gridx = 2;
        gbc_lblImagenPregunta3.gridy = 4;
        panel_2.add(lblImagenPregunta3, gbc_lblImagenPregunta3);
        GridBagConstraints gbc_btnPregunta3 = new GridBagConstraints();
        gbc_btnPregunta3.insets = new Insets(0, 0, 5, 5);
        gbc_btnPregunta3.gridx = 4;
        gbc_btnPregunta3.gridy = 4;
        panel_2.add(btnPregunta3, gbc_btnPregunta3);

        // ComboBox de opciones
        String[] opciones = {"Respuesta 1", "Respuesta 2", "Respuesta 3"};        

        // ComboBox de niveles
        Nivel[] niveles = {Nivel.PRINCIPIANTE, Nivel.BASICO, Nivel.INTERMEDIO, Nivel.AVANZADO};
        
        lblRespuestaCorrecta = new JLabel("Respuesta Correcta:");
        GridBagConstraints gbc_lblRespuestaCorrecta = new GridBagConstraints();
        gbc_lblRespuestaCorrecta.insets = new Insets(0, 0, 5, 5);
        gbc_lblRespuestaCorrecta.anchor = GridBagConstraints.EAST;
        gbc_lblRespuestaCorrecta.gridx = 1;
        gbc_lblRespuestaCorrecta.gridy = 5;
        panel_2.add(lblRespuestaCorrecta, gbc_lblRespuestaCorrecta);
        comboOpciones = new JComboBox<>();
        GridBagConstraints gbc_comboOpciones = new GridBagConstraints();
        gbc_comboOpciones.insets = new Insets(5, 5, 5, 5);
        gbc_comboOpciones.fill = GridBagConstraints.BOTH;
        gbc_comboOpciones.gridx = 2;
        gbc_comboOpciones.gridy = 5;
        gbc_comboOpciones.weightx = 1.0;
        panel_2.add(comboOpciones, gbc_comboOpciones);
        
        lblNivel = new JLabel("Nivel:");
        lblNivel.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNivel = new GridBagConstraints();
        gbc_lblNivel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNivel.gridx = 3;
        gbc_lblNivel.gridy = 5;
        panel_2.add(lblNivel, gbc_lblNivel);
        comboNiveles = new JComboBox<>();
        GridBagConstraints gbc_comboNiveles = new GridBagConstraints();
        gbc_comboNiveles.insets = new Insets(5, 5, 5, 5);
        gbc_comboNiveles.fill = GridBagConstraints.BOTH;
        gbc_comboNiveles.gridx = 4; // Lo ubicamos en la columna 2
        gbc_comboNiveles.gridy = 5;
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
    	String pregunta, respuesta1, respuesta2, respuesta3, respuestaCorrecta;
    	Nivel lvl = (Nivel) comboNiveles.getSelectedItem();
    	pregunta = txtPregunta.getText();
    	respuestaCorrecta = (String)comboOpciones.getSelectedItem();
    	respuesta1 = lblImagenPregunta1.getText();
    	respuesta2 = lblImagenPregunta2.getText();
    	respuesta3 = lblImagenPregunta3.getText();    	
    	switch(respuestaCorrecta) {
    	case "Respuesta 1": respuestaCorrecta = "Respuesta 1";
    						break;
    	case "Respuesta 2": respuestaCorrecta = "Respuesta 2";
    						break;
    	case "Respuesta 3": respuestaCorrecta = "Respuesta 3";
    						break;
    	}
    	
    	if (!hasRequiredFileds(pregunta, respuesta1, respuesta2, respuesta3, respuestaCorrecta, lvl)) {
    		ArrayList<String> opciones = new ArrayList<>(Arrays.asList(respuesta1, respuesta2, respuesta3));
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
	
///////////////////////////////////////////////
///	Funciones cambios de imagen
///////////////////////////////////////////////
	
	private void abrirVentanaCambioImagen(int boton) {
		botonSeleccionado = boton;
		VentanaCambioImagen vci = new VentanaCambioImagen(this);
		vci.setVisible(true);
	}


	@Override
	public void setIcon(ImageIcon imageIcon, URL url) {
		if (url != null) {
			this.url = url;
			imageIcon = new ImageIcon(url);
		} else if (destinationFile != null) {
			String path = destinationFile.getAbsolutePath();
			imageIcon = new ImageIcon(path);
		}
		switch (botonSeleccionado) {
		case BOTON_RESPUESTA_1:
			lblImagenPregunta1.setIcon(ControladorUsuario.INSTANCE.getScaledImage(imageIcon, DEFAUL_HEIGHT_AND_WIDTH));
			break;
		case BOTON_RESPUESTA_2:
			lblImagenPregunta2.setIcon(ControladorUsuario.INSTANCE.getScaledImage(imageIcon, DEFAUL_HEIGHT_AND_WIDTH));
			break;
		case BOTON_RESPUESTA_3:
			lblImagenPregunta3.setIcon(ControladorUsuario.INSTANCE.getScaledImage(imageIcon, DEFAUL_HEIGHT_AND_WIDTH));
			break;
		}
	}

	@Override
	public void setDestinationFile(File d) {
		destinationFile = d;
	}

}
