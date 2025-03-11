package umu.pds.duolingoBaratero.windows.components;

import javax.swing.*;
import java.awt.*;

public class BarraSuperior extends JPanel {
    public JButton btnOpciones, btnModoOscuro;

    public BarraSuperior() {
        setLayout(new BorderLayout());

        JToolBar barra = new JToolBar();
        barra.setFloatable(false);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnOpciones = new JButton("⚙ Opciones");
        btnModoOscuro = new JButton("🌙 Modo Oscuro");

        panelBotones.add(btnOpciones);
        panelBotones.add(btnModoOscuro);

        barra.add(panelBotones); // Agregar el panel centrado a la toolbar
        add(barra, BorderLayout.NORTH);
        
    }
    
    

}
