package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPreguntaImagenes extends JPanel {

    private static final long serialVersionUID = 1L;
    private JToggleButton[] imagenes; // Botones de imagen
    private JLabel lblPregunta;
    private String pregunta = "¿Cuál le gusta más a Alex?";

    public VentanaPreguntaImagenes() {
        setLayout(new BorderLayout()); // Usamos BorderLayout para mejor distribución

        // Panel contenedor para las preguntas e imágenes
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        // Pregunta
        lblPregunta = new JLabel(pregunta, SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblPregunta);

        // Panel para las imágenes
        JPanel panelImagenes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        imagenes = new JToggleButton[3];
        ButtonGroup grupoImagenes = new ButtonGroup(); // Para selección única

        for (int i = 0; i < 3; i++) {
            imagenes[i] = new JToggleButton(new ImageIcon("src/main/resources/img" + (i + 1) + ".jpg"));
            imagenes[i].setPreferredSize(new Dimension(250, 250));
            grupoImagenes.add(imagenes[i]);
            panelImagenes.add(imagenes[i]);
        }

        // Agregar elementos al panel principal
        panelCentral.add(panelImagenes);
        add(panelCentral, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pregunta de Imágenes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.add(new VentanaPreguntaImagenes());
            frame.setVisible(true);
        });
    }
}
