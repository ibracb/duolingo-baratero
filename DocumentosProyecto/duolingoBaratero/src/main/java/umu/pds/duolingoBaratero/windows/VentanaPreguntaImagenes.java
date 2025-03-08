package umu.pds.duolingoBaratero.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;

public class VentanaPreguntaImagenes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private BarraProgresoPanel barraProgreso;
    private BarraSuperior barraSuperior;
    private JToggleButton[] imagenes; // Botones de imagen
    private JButton btnSiguiente, btnSaltar;
    private JLabel lblPregunta;
    private String pregunta = "Cual le gusta mas a alex";
    private Component horizontalGlue;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPreguntaImagenes frame = new VentanaPreguntaImagenes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPreguntaImagenes() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 450, 300);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(new BorderLayout());
	    setContentPane(contentPane);

	    //------- barra superior-------
	    barraSuperior = new BarraSuperior();
	    barraProgreso = new BarraProgresoPanel();

	    // Panel que une la barra superior con la barra de progreso
	    JPanel panelSuperior = new JPanel(new BorderLayout());
	    panelSuperior.add(barraSuperior, BorderLayout.NORTH);
	    panelSuperior.add(barraProgreso, BorderLayout.SOUTH);

	    contentPane.add(panelSuperior, BorderLayout.NORTH);

	    // Panel contenedor para las preguntas e imagenes
	    JPanel panelCentral = new JPanel();
	    panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

	    //----- Pregunta------
	    lblPregunta = new JLabel(pregunta, SwingConstants.CENTER);
	    lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
	    lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto

	    // Panel para las imágenes (FlowLayout centrado)
	    JPanel panelImagenes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
	    imagenes = new JToggleButton[3];

	    ButtonGroup grupoImagenes = new ButtonGroup(); // Para que solo una imagen pueda seleccionarse

	    for (int i = 0; i < 3; i++) {
	        imagenes[i] = new JToggleButton(new ImageIcon("src/main/resources/img" + (i + 1) + ".jpg")); // Reemplazar con rutas válidas
	        imagenes[i].setPreferredSize(new Dimension(250, 250)); // Tamaño fijo por ahora (lo ajustaremos más tarde)
	        grupoImagenes.add(imagenes[i]); // Se agrupan para selección única
	        panelImagenes.add(imagenes[i]);
	    }

	    // Añadir pregunta y panel de imágenes al panel central
	    panelCentral.add(lblPregunta);
	    panelCentral.add(panelImagenes);

	    // Añadir panelCentral al contentPane en el centro
	    contentPane.add(panelCentral, BorderLayout.CENTER);

	    // Panel para los botones de acción
	    JPanel panelBotones = new JPanel(new FlowLayout());
	    btnSiguiente = new JButton("siguiente");
	    btnSiguiente.setBackground(new Color(0, 255, 0));

	    
	    btnSiguiente.addActionListener(e -> barraProgreso.avanzar());
	    btnSaltar = new JButton("Saltar");
	    btnSaltar.setBackground(new Color(255, 165, 0));
	    panelBotones.add(btnSaltar);
	    
	    horizontalGlue = Box.createHorizontalGlue();
	    panelBotones.add(horizontalGlue);
	    panelBotones.add(btnSiguiente);

	    contentPane.add(panelBotones, BorderLayout.SOUTH);

	    setLocationRelativeTo(null);

	}

	private void ajustarImagenes() {
	    // Obtener el tamaño actual de la ventana
	    int width = getWidth();
	    int height = getHeight();

	    // Establecer un tamaño relativo para las imágenes en función del tamaño de la ventana
	    int imageSize = Math.min(width, height) / 5; // Hacemos que las imágenes tengan un tamaño proporcional

	    for (JToggleButton imageButton : imagenes) {
	        ImageIcon icon = (ImageIcon) imageButton.getIcon();
	        Image img = icon.getImage();
	        Image resizedImage = img.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
	        imageButton.setIcon(new ImageIcon(resizedImage)); // Actualizar el icono con la nueva imagen redimensionada
	    }
	}
}
