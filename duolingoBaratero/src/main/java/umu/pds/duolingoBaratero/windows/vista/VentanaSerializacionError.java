package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.fast.FastLookAndFeel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

public class VentanaSerializacionError extends JFrame {

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

		VentanaSerializacionError serErrorWindow = new VentanaSerializacionError();
		serErrorWindow.setLocationRelativeTo(null);
		serErrorWindow.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public VentanaSerializacionError() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSerializacionError.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-error@2x.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBoton = new JPanel();
		contentPane.add(panelBoton, BorderLayout.SOUTH);
		panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnVale = new JButton("Vale");
		panelBoton.add(btnVale);
		
		JPanel panelMessage = new JPanel();
		contentPane.add(panelMessage, BorderLayout.CENTER);
		GridBagLayout gbl_panelMessage = new GridBagLayout();
		gbl_panelMessage.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelMessage.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelMessage.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMessage.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMessage.setLayout(gbl_panelMessage);
		
		JLabel lblMessage = new JLabel("Ha surgido un error durante la serialización");
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.gridx = 0;
		gbc_lblMessage.gridy = 0;
		gbc_lblMessage.gridwidth = 5; // Abarca todas las columnas
		gbc_lblMessage.gridheight = 5; // Abarca todas las filas
		gbc_lblMessage.weightx = 1.0;
		gbc_lblMessage.weighty = 1.0;
		gbc_lblMessage.anchor = GridBagConstraints.CENTER;
		panelMessage.add(lblMessage, gbc_lblMessage);
	}

}
