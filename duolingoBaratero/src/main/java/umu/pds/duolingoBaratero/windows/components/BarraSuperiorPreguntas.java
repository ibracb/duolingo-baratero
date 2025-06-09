package umu.pds.duolingoBaratero.windows.components;

import javax.swing.*;
import java.awt.*;

public class BarraSuperiorPreguntas extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton btnSalir;
    private final JButton btnInfo;
    private final JFrame ventanaPregunta;

    public BarraSuperiorPreguntas(JFrame ventanaPregunta) {
        this.ventanaPregunta = ventanaPregunta;

        setLayout(new BorderLayout());

        JToolBar barra = new JToolBar();
        barra.setFloatable(false);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        Dimension botonSize = new Dimension(150, 30);

        btnSalir = new JButton("❌ Salir");
        btnInfo = new JButton("ℹ️ Información");

        btnSalir.setPreferredSize(botonSize);
        btnSalir.addActionListener(e -> salir());

        btnInfo.setPreferredSize(botonSize);
        btnInfo.addActionListener(e -> mostrarInformacion());

        panelBotones.add(btnSalir);
        panelBotones.add(btnInfo);

        barra.add(panelBotones);
        add(barra, BorderLayout.NORTH);
    }

    private void salir() {
    	Object[] opciones = {"Sí", "No"};
    	int opcion = JOptionPane.showOptionDialog(
    	    ventanaPregunta,
    	    "¿Estás seguro de que quieres salir? Perderás todo el progreso de la lección.",
    	    "Aviso",
    	    JOptionPane.YES_NO_OPTION,
    	    JOptionPane.WARNING_MESSAGE,
    	    null,
    	    opciones,
    	    opciones[1]
    	);

        if (opcion == JOptionPane.YES_OPTION) {
            ventanaPregunta.dispose();
        }
    }

    private void mostrarInformacion() {
        JOptionPane.showMessageDialog(
            ventanaPregunta,
            "Para poder pasar a la siguiente lección hay que aceptar al menos el 80% de las preguntas.",
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
