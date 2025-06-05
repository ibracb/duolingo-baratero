package umu.pds.duolingoBaratero.program;

import javax.swing.UIManager;
import com.jtattoo.plaf.fast.FastLookAndFeel;

import jakarta.persistence.EntityManager;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.persistence.EntityManagerHelper;
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
