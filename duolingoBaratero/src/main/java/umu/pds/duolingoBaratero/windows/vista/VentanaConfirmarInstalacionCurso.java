package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
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

public class VentanaConfirmarInstalacionCurso extends JFrame {

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

		VentanaConfirmarInstalacionCurso instalarWindow = new VentanaConfirmarInstalacionCurso();
		instalarWindow.setLocationRelativeTo(null);
		instalarWindow.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public VentanaConfirmarInstalacionCurso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmarInstalacionCurso.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-confirm@2x.png")));
		setTitle("Confirmación instalación curso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAbajo = new JPanel();
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		
		JButton btnNo = new JButton("No");
		panelAbajo.add(btnNo);
		
		JButton btnSi = new JButton("Sí");
		panelAbajo.add(btnSi);
		
		JPanel panelArriba = new JPanel();
		contentPane.add(panelArriba, BorderLayout.CENTER);
		GridBagLayout gbl_panelArriba = new GridBagLayout();
		gbl_panelArriba.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelArriba.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelArriba.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelArriba.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelArriba.setLayout(gbl_panelArriba);
		
		JLabel mensaje = new JLabel("¿Seguro que quiere instalar este curso?");
		GridBagConstraints gbc_mensaje = new GridBagConstraints();
		gbc_mensaje.gridx = 4;
		gbc_mensaje.gridy = 3;
		gbc_mensaje.anchor = GridBagConstraints.CENTER;
		gbc_mensaje.weightx = 1.0;
		gbc_mensaje.weighty = 1.0;
		panelArriba.add(mensaje, gbc_mensaje);
	}

}
