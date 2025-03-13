package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;

import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;

import java.awt.*;

public class PanelPreguntaOpciones extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblPregunta;
    private JLabel lblAudio;
    private PreguntaOpciones pregunta;

    public PanelPreguntaOpciones(PreguntaOpciones pregunta) {
        this.pregunta = pregunta;
        inicializar();
    }
    

    public void inicializar() {
        setLayout(new GridBagLayout());
        GridBagLayout gbl_panelCentral = new GridBagLayout();
        gbl_panelCentral.columnWidths = new int[]{1, 205, 281, 200, 0};
        gbl_panelCentral.rowHeights = new int[]{1, 0, 43, 0, 40, 39, 19, 0, 0};
        gbl_panelCentral.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gbl_panelCentral);

        JPanel panelAudio = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        lblAudio = new JLabel("Completa el espacio en blanco con una de las siguientes palabras");
        lblAudio.setFont(new Font("Arial", Font.BOLD, 16));

        panelAudio.add(lblAudio);
        panelAudio.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints gbc_panelAudio = new GridBagConstraints();
        gbc_panelAudio.insets = new Insets(0, 0, 5, 5);
        gbc_panelAudio.gridx = 2;
        gbc_panelAudio.gridy = 2;
        add(panelAudio, gbc_panelAudio);

     // Pregunta
        lblPregunta = new JLabel(pregunta.getPregunta(), SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints gbc_lblPregunta = new GridBagConstraints();
        gbc_lblPregunta.insets = new Insets(0, 0, 5, 5);
        gbc_lblPregunta.gridx = 2;
        gbc_lblPregunta.gridy = 4;
        add(lblPregunta, gbc_lblPregunta);

        
        // Opciones como etiquetas esto deberia hacerse como un bucle pero el window builder no lo pilla
        JLabel opcion1 = new JLabel();
        JLabel opcion2 = new JLabel("Dog");  // Opción correcta
        JLabel opcion3 = new JLabel("Car");

        JLabel[] opciones = {opcion1, opcion2, opcion3};

        GridBagConstraints gbc_opcion1 = new GridBagConstraints();
        gbc_opcion1.insets = new Insets(0, 0, 5, 5);
        gbc_opcion1.gridx = 2;
        gbc_opcion1.gridy = 5;
        add(opcion1, gbc_opcion1);

        GridBagConstraints gbc_opcion2 = new GridBagConstraints();
        gbc_opcion2.insets = new Insets(0, 0, 5, 5);
        gbc_opcion2.gridx = 2;
        gbc_opcion2.gridy = 6;
        add(opcion2, gbc_opcion2);

        GridBagConstraints gbc_opcion3 = new GridBagConstraints();
        gbc_opcion3.insets = new Insets(0, 0, 0, 5);
        gbc_opcion3.gridx = 2;
        gbc_opcion3.gridy = 7;
        add(opcion3, gbc_opcion3);

        // Listener para seleccionar solo una opción
        for (JLabel opcion : opciones) {
            opcion.setFont(new Font("Arial", Font.PLAIN, 14));
            opcion.setOpaque(true);
            opcion.setBackground(Color.LIGHT_GRAY);
            opcion.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            opcion.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    for (JLabel opt : opciones) {
                        opt.setBackground(Color.LIGHT_GRAY); // Des-seleccionar todas
                    }
                    opcion.setBackground(Color.YELLOW); // Marcar la opción seleccionada
                }
            });
        }
    }

}