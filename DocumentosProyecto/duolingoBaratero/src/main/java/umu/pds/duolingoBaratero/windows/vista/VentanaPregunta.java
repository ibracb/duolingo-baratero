package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;

import umu.pds.duolingoBaratero.windows.components.BarraProgresoPanel;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;

public class VentanaPregunta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private BarraProgresoPanel barraProgreso;
    private BarraSuperior barraSuperior;
    private JButton btnSiguiente, btnSaltar;
    private Component horizontalGlue;
    private int currentPanel = 1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPregunta frame = new VentanaPregunta();
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
	public VentanaPregunta() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 772, 482);
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
	    
	    JPanel panelCentral = new JPanel(new CardLayout());
	    CardLayout cardLayout = (CardLayout) panelCentral.getLayout();

	    // Crear los cuatro paneles
	    JPanel panel1 = new VentanaPreguntaAudio();
	    JPanel panel2 = new VentanaPreguntaImagenes();  // Reemplaza con tu JPanel real
	    JPanel panel3 = new VentanaPreguntaOpciones(); // Reemplaza con tu JPanel real
//	    JPanel panel4 = new OtroPanel3(); // Reemplaza con tu JPanel real
	    
	    
	    panelCentral.add(panel1, "panel1");
	    panelCentral.add(panel2, "panel2");
	    panelCentral.add(panel3, "panel3");
	   
	    
	    
	    // Panel para los botones de acción
	    JPanel panelBotones = new JPanel(new FlowLayout());
	    btnSiguiente = new JButton("siguiente");
	    btnSiguiente.setBackground(new Color(0, 255, 0));

	    
	    btnSiguiente.addActionListener(e -> {
	    	
	    	barraProgreso.avanzar();
	    	currentPanel = (currentPanel % 4) + 1; // Ciclo entre 1 y 4
	        cardLayout.show(panelCentral, "panel" + currentPanel);

	    });	
	    
	    contentPane.add(panelCentral, BorderLayout.CENTER); // **Agregarlo al centro**

	    
	    btnSaltar = new JButton("Saltar");
	    btnSaltar.setBackground(new Color(255, 165, 0));
	    panelBotones.add(btnSaltar);
	    
	    horizontalGlue = Box.createHorizontalGlue();
	    panelBotones.add(horizontalGlue);
	    panelBotones.add(btnSiguiente);

	    contentPane.add(panelBotones, BorderLayout.SOUTH);

	    setLocationRelativeTo(null);

	}

}
