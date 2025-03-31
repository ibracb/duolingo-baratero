package umu.pds.duolingoBaratero.windows.components;

import javax.swing.*;
import umu.pds.duolingoBaratero.windows.utility.Constantes;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BarraProgresoPanel extends JPanel {
    private static final int VALOR_INICIAL_PROGRESO = 0;
    private static final int LIMITE_PROGRESO = 100;
    private double progreso;
    private final double avance;

    private static final Color VERDE_CORRECTO = new Color(34, 139, 34);  // Verde Bosque
    private static final Color NARANJA_AVANCE = new Color(255, 165, 0);  // Naranja Oscuro
    private static final Color ROJO_INCORRECTO = new Color(220, 20, 60);  // Carmesí

    private final List<Color> tramosColores;
    private final List<Double> tramosProgreso;

    public BarraProgresoPanel(int numPreguntas) {
        this.progreso = VALOR_INICIAL_PROGRESO;
        this.tramosColores = new ArrayList<>();
        this.tramosProgreso = new ArrayList<>();
        setPreferredSize(new Dimension(250, 30));
        this.avance = (double) LIMITE_PROGRESO/ numPreguntas;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int ancho = getWidth();
        int alto = getHeight();

        // Dibujar el fondo de la barra
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, ancho, alto);

        // Dibujar cada tramo con su color correspondiente
        int acumulado = 0;
        for (int i = 0; i < tramosProgreso.size(); i++) {
            int tramoAncho = (int) ((double) tramosProgreso.get(i) / LIMITE_PROGRESO * ancho);
            g.setColor(tramosColores.get(i));
            g.fillRect(acumulado, 0, tramoAncho, alto);
            acumulado += tramoAncho;
        }
    }

    public void avanzar(Boolean acierto) {
        Color color;
        if (acierto == null) {
            color = NARANJA_AVANCE;
        } else if (acierto) {
            color = VERDE_CORRECTO;
        } else {
            color = ROJO_INCORRECTO;
        }
        
        double nuevoProgreso = Math.min(progreso + avance, LIMITE_PROGRESO);
        tramosColores.add(color);
        tramosProgreso.add(nuevoProgreso - progreso);
        progreso = nuevoProgreso;
        repaint();  // Redibujar la barra de progreso con el nuevo color y avance
    }
}
