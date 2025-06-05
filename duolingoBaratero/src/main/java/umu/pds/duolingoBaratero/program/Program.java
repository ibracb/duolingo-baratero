package umu.pds.duolingoBaratero.program;

import javax.swing.UIManager;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import umu.pds.duolingoBaratero.windows.vista.VentanaInicio;

public class Program {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FastLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}

		VentanaInicio loginWindow = new VentanaInicio();
		loginWindow.setLocationRelativeTo(null); // Esto centra la ventana
		loginWindow.setVisible(true);
	}
}
