package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.fast.FastLookAndFeel;

public class VentanaConfirmarComparteCurso extends JFrame {

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

		VentanaConfirmarComparteCurso comparteWindow = new VentanaConfirmarComparteCurso();
		comparteWindow.setLocationRelativeTo(null);
		comparteWindow.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public VentanaConfirmarComparteCurso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmarComparteCurso.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-confirm@2x.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNo = new JButton("No");
		panelBotones.add(btnNo);
		
		JButton btnSi = new JButton("Sí");
		panelBotones.add(btnSi);
		
		JPanel panelMensaje = new JPanel();
		contentPane.add(panelMensaje, BorderLayout.CENTER);
		GridBagLayout gbl_panelMensaje = new GridBagLayout();
		gbl_panelMensaje.columnWidths = new int[]{0};
		gbl_panelMensaje.rowHeights = new int[]{0};
		gbl_panelMensaje.columnWeights = new double[]{1.0}; // Permite expansión horizontal
		gbl_panelMensaje.rowWeights = new double[]{1.0}; // Permite expansión vertical
		panelMensaje.setLayout(gbl_panelMensaje);

		JLabel lblMensaje = new JLabel("¿Seguro que desea compartir este curso para otros usuarios?");
		GridBagConstraints gbc_lblMensaje = new GridBagConstraints();
		gbc_lblMensaje.gridx = 0;
		gbc_lblMensaje.gridy = 0;
		gbc_lblMensaje.anchor = GridBagConstraints.CENTER; // Centra el JLabel en el espacio disponible
		gbc_lblMensaje.weightx = 1.0; // Permite que el JLabel use el espacio horizontalmente
		gbc_lblMensaje.weighty = 1.0; // Permite que el JLabel use el espacio verticalmente
		panelMensaje.add(lblMensaje, gbc_lblMensaje);


	}

}
