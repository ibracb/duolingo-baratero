package umu.pds.duolingoBaratero.windows;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaOpciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaOpciones frame = new VentanaOpciones();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaOpciones() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // Creación del panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Usando GridBagLayout para centrar los botones
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 }; // Reducido el número de filas
        gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE }; // Asignamos pesos para distribuir mejor el espacio
        contentPane.setLayout(gbl_contentPane);

        // Botón "Continuar"
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(new Color(34, 193, 34)); // Verde más agradable
        btnContinuar.setForeground(Color.WHITE); // Cambiar el color del texto
        btnContinuar.setBorder(new LineBorder(new Color(34, 193, 34), 2)); // Borde verde
        btnContinuar.setFocusPainted(false); // Elimina el borde de enfoque cuando el botón está seleccionado
        btnContinuar.setRolloverEnabled(true); // Activar rollover
        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Continuando el curso...");
                // Aquí puedes agregar la lógica para continuar en el curso
            }
        });
        GridBagConstraints gbc_btnContinuar = new GridBagConstraints();
        gbc_btnContinuar.insets = new Insets(5, 5, 5, 0); // Reducir espacio alrededor del botón
        gbc_btnContinuar.gridx = 0;
        gbc_btnContinuar.gridy = 1;  // Ubicarlo en la fila 1
        contentPane.add(btnContinuar, gbc_btnContinuar);

        // Botón "Salir"
        JButton btnSalir = new JButton("Salir al Home");
        btnSalir.setBackground(new Color(255, 59, 48)); // Rojo para la opción de salir
        btnSalir.setForeground(Color.WHITE); // Cambiar el color del texto
        btnSalir.setBorder(new LineBorder(new Color(255, 59, 48), 2)); // Borde rojo
        btnSalir.setFocusPainted(false); // Elimina el borde de enfoque cuando el botón está seleccionado
        btnSalir.setRolloverEnabled(true); // Activar rollover
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saliendo al Home...");
                // Aquí puedes agregar la lógica para salir al Home
            }
        });
        GridBagConstraints gbc_btnSalir = new GridBagConstraints();
        gbc_btnSalir.insets = new Insets(5, 5, 5, 0); // Reducir espacio alrededor del botón
        gbc_btnSalir.gridx = 0;
        gbc_btnSalir.gridy = 2;  // Ubicarlo en la fila 2
        contentPane.add(btnSalir, gbc_btnSalir);
    }
}
