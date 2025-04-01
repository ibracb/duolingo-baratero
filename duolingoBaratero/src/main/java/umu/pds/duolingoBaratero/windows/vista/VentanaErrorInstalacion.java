package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.fast.FastLookAndFeel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

public class VentanaErrorInstalacion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FastLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        VentanaErrorInstalacion errorWindow = new VentanaErrorInstalacion();
        errorWindow.setLocationRelativeTo(null);
        errorWindow.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public VentanaErrorInstalacion() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(
            VentanaErrorInstalacion.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-error.png")
        ));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JButton btnNewButton = new JButton("Aceptar");
        contentPane.add(btnNewButton, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        panel.setLayout(gbl_panel);

        JLabel lblMessage = new JLabel("Ha ocurrido un error durante la instalación del curso!");
        GridBagConstraints gbc_lblMessage = new GridBagConstraints();
        gbc_lblMessage.insets = new Insets(5, 5, 5, 5);
        gbc_lblMessage.gridx = 3;
        gbc_lblMessage.gridy = 2;
        gbc_lblMessage.gridwidth = 2;
        panel.add(lblMessage, gbc_lblMessage);
    }
}
