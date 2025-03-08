package umu.pds.duolingoBaratero.windows;

import javax.swing.*;
import java.awt.*;

public class BarraProgresoPanel extends JPanel {
    private JProgressBar progressBar;
    private int progreso = 0;

    public BarraProgresoPanel() {
        setLayout(new BorderLayout());
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(progreso);
        progressBar.setStringPainted(false);
        progressBar.setForeground(new Color(76, 175, 80)); 

        add(progressBar, BorderLayout.CENTER);
    }

    public void avanzar() {
        progreso += 10;
        progressBar.setValue(progreso);
    }
}
