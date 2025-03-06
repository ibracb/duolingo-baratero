package umu.pds.duolingoBaratero.program;

import javax.swing.UIManager;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import umu.pds.duolingoBaratero.windows.LogInWindow;

public class Program {
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(new FastLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogInWindow loginWindow = new LogInWindow();
        loginWindow.setVisible(true);
    }
}
