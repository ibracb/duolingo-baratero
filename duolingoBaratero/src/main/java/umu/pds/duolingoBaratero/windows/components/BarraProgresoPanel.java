package umu.pds.duolingoBaratero.windows.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Barra de progreso personalizada que muestra el avance en un conjunto de preguntas.
 * Cada vez que se avanza, se añade un tramo de color correspondiente al acierto o error.
 * 
 * Colores:
 * - Verde: Correcto
 * - Naranja: Avance
 * - Rojo: Incorrecto
 */
public class BarraProgresoPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Valor inicial del progreso.
	 */
	private static final int VALOR_INICIAL_PROGRESO = 0;
    
	/**
	 * Límite máximo del progreso.
	 */
	private static final int LIMITE_PROGRESO = 100;
    
	/**
	 * Progreso actual de la barra.
	 */
	private double progreso;
    
	/**
	 * Avance por cada pregunta respondida.
	 */
	private final double avance;

    /**
     * Color para respuestas correctas.
     */
	private static final Color VERDE_CORRECTO = new Color(34, 139, 34);  // Verde Bosque
    
    /**
	 * Color para avances sin respuesta.
	 */
    private static final Color NARANJA_AVANCE = new Color(255, 165, 0);  // Naranja Oscuro
    
    /**
     * Color para respuestas incorrectas.
     */
    private static final Color ROJO_INCORRECTO = new Color(220, 20, 60);  // Carmesí
    
    /**
	 * Lista que almacena los colores de cada tramo de progreso.
	 */
    private final List<Color> tramosColores;
    
    /**
     * Lista que almacena los tramos de progreso.
     */
    private final List<Double> tramosProgreso;
    
    /**
	 * Constructor de la barra de progreso.
	 * 
	 * @param numPreguntas Número total de preguntas para calcular el avance.
	 */
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
    
    /**
	 * Avanza la barra de progreso según el resultado de la pregunta.
	 * 
	 * @param acierto true si la respuesta es correcta, false si es incorrecta, null si es un avance sin respuesta.
	 */
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
