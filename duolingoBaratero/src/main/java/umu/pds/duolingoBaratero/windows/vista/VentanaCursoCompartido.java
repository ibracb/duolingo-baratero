package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.fast.FastLookAndFeel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaCursoCompartido extends JFrame {

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

		VentanaCursoCompartido compartidoWindow = new VentanaCursoCompartido();
		compartidoWindow.setLocationRelativeTo(null); // Esto centra la ventana
		compartidoWindow.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public VentanaCursoCompartido() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCursoCompartido.class.getResource("/com/jtattoo/plaf/acryl/icons/medium/check_symbol_14x13.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBoton = new JPanel();
		contentPane.add(panelBoton, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK!");
		panelBoton.add(btnOk);
		
		JPanel panelMensaje = new JPanel();
		contentPane.add(panelMensaje, BorderLayout.CENTER);
		GridBagLayout gbl_panelMensaje = new GridBagLayout();
		gbl_panelMensaje.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelMensaje.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelMensaje.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMensaje.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMensaje.setLayout(gbl_panelMensaje);
		
		JLabel lblMensaje = new JLabel("¡El curso ha sido compartido correctamente!");
		GridBagConstraints gbc_lblMensaje = new GridBagConstraints();
		gbc_lblMensaje.insets = new Insets(10, 10, 10, 10); // Espaciado uniforme
		gbc_lblMensaje.gridx = 0;
		gbc_lblMensaje.gridy = 0;
		gbc_lblMensaje.gridwidth = GridBagConstraints.REMAINDER; // Ocupa toda la fila
		gbc_lblMensaje.anchor = GridBagConstraints.CENTER; // Centra el texto
		gbc_lblMensaje.weightx = 1.0; // Distribuye espacio horizontalmente
		gbc_lblMensaje.weighty = 1.0; // Distribuye espacio verticalmente
		panelMensaje.add(lblMensaje, gbc_lblMensaje);

	}

}
