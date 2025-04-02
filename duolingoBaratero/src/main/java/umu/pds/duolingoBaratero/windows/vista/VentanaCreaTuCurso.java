package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;

import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;

public class VentanaCreaTuCurso extends JFrame implements VentanaCambiaImagenes {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_HEIGHT_AND_WIDTH = 75;
    private JTextField textFieldNombre;
    private JTextArea textAreaObjetivos, textAreaDescripcion;
    private JLabel labelImagen;
    private File destinationFile = null;
    private URL url;
    private JButton btnCambiarImagen;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private LinkedList<Pregunta> listaPreguntas;

    public VentanaCreaTuCurso() {
        setTitle("Crear Curso");
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc;
        listaPreguntas = new LinkedList<>();

        // Barra superior
        BarraSuperior panelSuperior = new BarraSuperior(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTH;
        getContentPane().add(panelSuperior, gbc);

        // Nombre del curso
        textFieldNombre = new JTextField("Nombre del curso");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(textFieldNombre, gbc);

        // ComboBox
        // Label para la imagen
        JLabel lblImagen = new JLabel("Imagen:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(lblImagen, gbc);

        // Etiqueta de imagen
        labelImagen = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(labelImagen, gbc);
        setProfileImage();

        // Botón para cambiar imagen
        btnCambiarImagen = new JButton("Cambiar Imagen");
        btnCambiarImagen.addActionListener(e -> abrirVentanaCambioImagen());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(btnCambiarImagen, gbc);

        // Área de Objetivos
        textAreaObjetivos = new JTextArea("Objetivos:");
        textAreaObjetivos.setRows(5);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(new JScrollPane(textAreaObjetivos), gbc);

        // Área de Descripción
        textAreaDescripcion = new JTextArea("Descripción:");
        textAreaDescripcion.setRows(8);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(new JScrollPane(textAreaDescripcion), gbc);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarCurso());
        panelBotones.add(btnGuardar);
        btnAceptar = new JButton("Añadir Pregunta");
        btnAceptar.addActionListener(e -> añadirPregunta());
        panelBotones.add(btnAceptar);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(panelBotones, gbc);
    }
    
    private void añadirPregunta() {
        String[] opciones = {"Pregunta Audio", "Pregunta Fotos", "FlashCard", "Pregunta Opciones"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        
        int respuesta = JOptionPane.showConfirmDialog(
            null,
            comboBox,
            "Elige el tipo de pregunta",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (respuesta != JOptionPane.OK_OPTION) {
            return; // Si el usuario cancela, no se abre la nueva ventana
        }
        
        String seleccion = (String) comboBox.getSelectedItem();
        JFrame ventana = null;
        
        switch (seleccion) {
            case "Pregunta Opciones":
                ventana = new VentanaCreaPreguntaOpciones(this);
                break;
            case "Pregunta Fotos":
                ventana = new VentanaCreaPreguntaOpcionesImagenes(this);
                break;
            case "FlashCard":
                ventana = new VentanaCreaPreguntaFlashcard(this);
                break;
            case "Pregunta Audio":
                ventana = new VentanaCreaPreguntaAudio(this);
                break;
        }
        
        if (ventana != null) {
            ventana.setVisible(true);
            this.setVisible(false);
        }
    }
    
    private void guardarCurso() {
    	String nombre, objetivos, descripcion;
    	nombre = textFieldNombre.getText();
    	descripcion = textAreaDescripcion.getText();
    	objetivos = textAreaObjetivos.getText();
    	CursoPlantilla curso = ControladorCurso.INSTANCE.crearCurso(nombre, descripcion, objetivos);
    	if (curso == null)
    		JOptionPane.showMessageDialog(this, "Algo ha salido mal prueba otra vez", "Error",
					JOptionPane.ERROR_MESSAGE);
    	else 
    		JOptionPane.showMessageDialog(this, "Curso creado con exito", "Conseguido",
					JOptionPane.ERROR_MESSAGE);
    	if (destinationFile != null) {
			ControladorCurso.INSTANCE.setImagenACurso(curso, destinationFile.getAbsolutePath());
		} else if (url != null) {
			ControladorCurso.INSTANCE.setImagenACurso(curso, url.toString());
		}		
    	
    	if (listaPreguntas != null) {
    		ControladorCurso.INSTANCE.guardarPreguntas(listaPreguntas, curso);
    	}
    	
    	
    }

    private void setProfileImage() {
        URL imageUrl = getClass().getClassLoader().getResource("profile.png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            labelImagen.setIcon(ControladorUsuario.INSTANCE.getScaledImage(icon, DEFAULT_HEIGHT_AND_WIDTH));
        } else {
            labelImagen.setText("Imagen no encontrada");
        }
    }

    private void abrirVentanaCambioImagen() {
        String nombre = textFieldNombre.getText();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre antes de cambiar la imagen.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        VentanaCambioImagen vci = new VentanaCambioImagen(this);
        vci.setVisible(true);
    }

    public void setIcon() {
        String path = destinationFile.getAbsolutePath();
        labelImagen.setIcon(ControladorUsuario.INSTANCE.getScaledImage(new ImageIcon(path), DEFAULT_HEIGHT_AND_WIDTH));
    }

    public void setIcon(ImageIcon imageIcon, URL url) {
        if (url != null) {
            this.url = url;
            imageIcon = new ImageIcon(url);
        } else if (destinationFile != null) {
            String path = destinationFile.getAbsolutePath();
            imageIcon = new ImageIcon(path);
        }
        labelImagen.setIcon(ControladorUsuario.INSTANCE.getScaledImage(imageIcon, DEFAULT_HEIGHT_AND_WIDTH));
    }

    public void setDestinationFile(File d) {
        destinationFile = d;
    }
    
    public void guardarPregunta(Pregunta pregunta) {
    	listaPreguntas.add(pregunta);
    }
}
