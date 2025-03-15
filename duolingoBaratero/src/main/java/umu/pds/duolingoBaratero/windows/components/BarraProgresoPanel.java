package umu.pds.duolingoBaratero.windows.components;

import javax.swing.*;
import java.awt.*;

public class BarraProgresoPanel extends JPanel {
	private static final int VALOR_INICIAL_PROGRESO = 0;
	private static final int LIMITE_PROGRESO = 100;
	private static final int INCREMENTO = 10;
    private JProgressBar progressBar;
    private int progreso;

    public BarraProgresoPanel() {
    	this.progreso = VALOR_INICIAL_PROGRESO;
        setLayout(new BorderLayout());
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(progreso);
        progressBar.setStringPainted(false);
        progressBar.setForeground(new Color(76, 175, 80)); 

        add(progressBar, BorderLayout.CENTER);
    }

    public void avanzar() {
        progreso += INCREMENTO;
        progressBar.setValue(progreso);
    }
}
