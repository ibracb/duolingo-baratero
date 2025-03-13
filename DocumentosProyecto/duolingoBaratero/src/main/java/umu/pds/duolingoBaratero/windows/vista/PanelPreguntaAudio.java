package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;

public class PanelPreguntaAudio extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblPregunta;
    private JLabel lblAudio;
    private JButton btnReproducir;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_1;
    private JRadioButton rdbtnNewRadioButton_2;


    public PanelPreguntaAudio() {
        setLayout(new GridBagLayout());
        GridBagLayout gbl_panelCentral = new GridBagLayout();
        gbl_panelCentral.columnWidths = new int[]{1, 205, 281, 200, 0};
        gbl_panelCentral.rowHeights = new int[]{1, 0, 43, 0, 40, 39, 19, 0, 0};
        gbl_panelCentral.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gbl_panelCentral);

        JPanel panelAudio = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        lblAudio = new JLabel("Escucha el siguiente audio:");
        lblAudio.setFont(new Font("Arial", Font.BOLD, 16));

        btnReproducir = new JButton(new ImageIcon("src/main/resources/boton-de-play.png"));
        btnReproducir.setPreferredSize(new Dimension(40, 30));
        btnReproducir.addActionListener(e -> reproducirAudio());

        panelAudio.add(lblAudio);
        panelAudio.add(btnReproducir);
        panelAudio.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints gbc_panelAudio = new GridBagConstraints();
        gbc_panelAudio.insets = new Insets(0, 0, 5, 5);
        gbc_panelAudio.gridx = 2;
        gbc_panelAudio.gridy = 2;
        add(panelAudio, gbc_panelAudio);


        lblPregunta = new JLabel("¿Cuál es la respuesta correcta?", SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints gbc_lblPregunta = new GridBagConstraints();
        gbc_lblPregunta.insets = new Insets(0, 0, 5, 5);
        gbc_lblPregunta.gridx = 2;
        gbc_lblPregunta.gridy = 4;
        add(lblPregunta, gbc_lblPregunta);

        ButtonGroup grupoOpciones = new ButtonGroup();
        rdbtnNewRadioButton = new JRadioButton("Opcion 1");
        GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
        gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnNewRadioButton.gridx = 2;
        gbc_rdbtnNewRadioButton.gridy = 5;
        add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("Opcion 2");
        GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
        gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnNewRadioButton_1.gridx = 2;
        gbc_rdbtnNewRadioButton_1.gridy = 6;
        add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);

        rdbtnNewRadioButton_2 = new JRadioButton("Opcion 3");
        GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
        gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 0, 5);
        gbc_rdbtnNewRadioButton_2.gridx = 2;
        gbc_rdbtnNewRadioButton_2.gridy = 7;
        add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);

        grupoOpciones.add(rdbtnNewRadioButton);
        grupoOpciones.add(rdbtnNewRadioButton_1);
        grupoOpciones.add(rdbtnNewRadioButton_2);
    }

    private void reproducirAudio() {
        System.out.println("Reproduciendo audio...");
    }
}