package umu.pds.duolingoBaratero.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

public class TemasWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private final LogInWindow v;
	public TemasWindow(LogInWindow v) {
		this.v = v;
        setTitle("Temas Disponibles");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Panel superior con el mensaje
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(200, 220, 255));
        JLabel lblTitulo = new JLabel("🌍 ¡Tantos temas como puedas imaginar! 🚀📖");
        panelSuperior.add(lblTitulo);
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Panel central con los botones de temas
        JPanel panelCentral = new JPanel(new GridLayout(2, 3, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botones con iconos simulados
        ImageIcon iconoLiteratura = new ImageIcon(getClass().getResource("/Literatura.png"));
  		Image imagenLiteratura = iconoLiteratura.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
  		iconoLiteratura = new ImageIcon(imagenLiteratura);
        JButton botonIdiomas = new JButton("Idiomas");
        botonIdiomas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        botonIdiomas.setPreferredSize(new Dimension(140, 60));
        botonIdiomas.setIcon(iconoLiteratura);
        panelCentral.add(botonIdiomas);
        
        ImageIcon iconoOrdenador = new ImageIcon(getClass().getResource("/ordenador.png"));
		Image imagenOrdenador = iconoOrdenador.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		iconoOrdenador = new ImageIcon(imagenOrdenador);
        JButton botonProgramacion = new JButton("Informatica");
        botonProgramacion.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        botonProgramacion.setPreferredSize(new Dimension(140, 60));
        botonProgramacion.setIcon(iconoOrdenador);
        panelCentral.add(botonProgramacion);
        
        ImageIcon iconoRadio = new ImageIcon(getClass().getResource("/radio.png"));
		Image imagenRadio = iconoRadio.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		iconoRadio = new ImageIcon(imagenRadio);
        JButton botonMusica = new JButton("Música");
        botonMusica.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        botonMusica.setPreferredSize(new Dimension(140, 60));
        botonMusica.setIcon(iconoRadio);
        panelCentral.add(botonMusica);
        
        ImageIcon iconoCiencia = new ImageIcon(getClass().getResource("/ciencia.png"));
		Image imagenCiencia = iconoCiencia.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		iconoCiencia = new ImageIcon(imagenCiencia);        
        JButton botonCiencia = new JButton("Ciencia");
        botonCiencia.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        botonCiencia.setPreferredSize(new Dimension(140, 60));
        botonCiencia.setIcon(iconoCiencia);
        panelCentral.add(botonCiencia);
        
        ImageIcon iconoEstudioso = new ImageIcon(getClass().getResource("/pinguinoEstudioso.png"));
		Image imagenEstudioso = iconoEstudioso.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		iconoEstudioso = new ImageIcon(imagenEstudioso);
        JButton botonEstudios = new JButton("Estudios");
        botonEstudios.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        botonEstudios.setPreferredSize(new Dimension(140, 60));
        botonEstudios.setIcon(iconoEstudioso);
        panelCentral.add(botonEstudios);
        
        ImageIcon iconoDiseñar = new ImageIcon(getClass().getResource("/disenar.png"));
		Image imagenDiseñar = iconoDiseñar.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		iconoDiseñar = new ImageIcon(imagenDiseñar);  
        JButton botonDiseña = new JButton("Diseña");
        botonDiseña.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        botonDiseña.setPreferredSize(new Dimension(140, 60));
        botonDiseña.setIcon(iconoDiseñar);
        panelCentral.add(botonDiseña);

        getContentPane().add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con los botones "Volver" y "Regístrate"
        JPanel panelInferior = new JPanel();
        JButton btnVolver = new JButton("Volver");
        JButton btnRegistro = new JButton("Regístrate");
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                VentanaRegistro ventanaNueva = new VentanaRegistro();
                ventanaNueva.setLocation(getLocation());
                ventanaNueva.setVisible(true);
            }
        });
        btnRegistro.setBackground(Color.CYAN);
        panelInferior.add(btnVolver);
        panelInferior.add(btnRegistro);

        getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }

    private void closeWindow() {
    	v.setVisible(true);
    	this.dispose();
    }
}
