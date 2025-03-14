package umu.pds.duolingoBaratero.windows.components;

import javax.swing.*;

import umu.pds.duolingoBaratero.windows.vista.VentanaPrincipal;

import java.awt.*;

public class BarraSuperiorPreguntas extends JPanel {
    public JButton btnOpciones, btnModoOscuro, btnguardarProgreso;
    public BarraSuperiorPreguntas() {
        setLayout(new BorderLayout());

        JToolBar barra = new JToolBar();
        barra.setFloatable(false);
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnOpciones = new JButton("⚙ Opciones");
        btnModoOscuro = new JButton("🌙 Modo Oscuro");
        btnguardarProgreso = new JButton("Guardar Progreso");
        panelBotones.add(btnOpciones);
        panelBotones.add(btnModoOscuro);
        panelBotones.add(btnguardarProgreso);
        
        barra.add(panelBotones); // Agregar el panel centrado a la toolbar
        add(barra, BorderLayout.NORTH);
        
    }

}
