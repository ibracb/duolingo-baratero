package umu.pds.duolingoBaratero.windows.components;

import javax.swing.*;

import umu.pds.duolingoBaratero.windows.utility.Constantes;

import java.awt.*;

public class BarraProgresoPanel extends JPanel {
    private static final int VALOR_INICIAL_PROGRESO = 0;
    private static final int LIMITE_PROGRESO = 100;
    private int progreso;
    private boolean acierto;

    public BarraProgresoPanel() {
        this.progreso = VALOR_INICIAL_PROGRESO;
        this.acierto = true; // Por defecto, lo consideramos correcto (verde)
        setPreferredSize(new Dimension(300, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int ancho = getWidth();
        int alto = getHeight();

        // Calcular el ancho del progreso según el valor actual
        int progresoAncho = (int) ((double) progreso / LIMITE_PROGRESO * ancho);

        // Dibujar el fondo de la barra
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, ancho, alto);

        // Cambiar el color según si la respuesta es correcta o incorrecta
        if (acierto) {
            g.setColor(new Color(76, 175, 80));  // Verde (correcto)
        } else {
            g.setColor(Color.RED);  // (incorrecto)
        }

        // Dibujar la parte de la barra que representa el progreso
        g.fillRect(0, 0, progresoAncho, alto);
    }

    public void avanzar(boolean acierto) {
        this.acierto = acierto;  // Definir si la respuesta fue correcta o incorrecta
        progreso = Math.min(progreso + Constantes.PREGUNTAS_POR_BLOQUE, LIMITE_PROGRESO);  // Asegurarse de no superar el límite
        repaint();  // Redibujar la barra de progreso con el nuevo color y avance
    }
}
