package umu.pds.duolingoBaratero.windows.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.jtattoo.plaf.fast.FastLookAndFeel;

public class VentanaCursoInstalado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FastLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        VentanaCursoInstalado inWindow = new VentanaCursoInstalado();
        inWindow.setLocationRelativeTo(null);
        inWindow.setVisible(true);
    }

    public VentanaCursoInstalado() {
        // Configuración de la ventana
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                VentanaCursoInstalado.class.getResource("/com/jtattoo/plaf/acryl/icons/medium/check_symbol_14x13.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 200);

        // Panel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Mensaje centrado
        JLabel lblMensaje = new JLabel("¡El curso fue instalado correctamente!");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(lblMensaje, BorderLayout.CENTER);

        // Botón en la parte inferior
        JPanel panelBoton = new JPanel();
        JButton btnOk = new JButton("OK!");
        panelBoton.add(btnOk);
        contentPane.add(panelBoton, BorderLayout.SOUTH);
    }
}
