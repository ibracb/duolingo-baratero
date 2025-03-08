package umu.pds.duolingoBaratero.windows;

import javax.swing.*;
import java.awt.*;

public class VentanaEjemplo extends JFrame {
    private BarraProgresoPanel barraProgreso;
    private BarraSuperior barraSuperior;

    public VentanaEjemplo() {
        setTitle("Ventana con Barra Reutilizable");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear componentes reutilizables
        barraSuperior = new BarraSuperior();
        barraProgreso = new BarraProgresoPanel();

        // Acción del botón de Modo Oscuro
        barraSuperior.btnModoOscuro.addActionListener(e -> cambiarModoOscuro());

        // Panel que une la barra superior con la barra de progreso
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(barraSuperior, BorderLayout.NORTH);
        panelSuperior.add(barraProgreso, BorderLayout.SOUTH);

        // Botón para avanzar la barra de progreso
        JButton boton = new JButton("Acertar Pregunta");
        boton.addActionListener(e -> barraProgreso.avanzar());

        add(panelSuperior, BorderLayout.NORTH);
        add(boton, BorderLayout.SOUTH);
    }

    private void cambiarModoOscuro() {
        getContentPane().setBackground(Color.DARK_GRAY);
        barraSuperior.setBackground(Color.DARK_GRAY);
        barraProgreso.setBackground(Color.DARK_GRAY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaEjemplo().setVisible(true));
    }
}
