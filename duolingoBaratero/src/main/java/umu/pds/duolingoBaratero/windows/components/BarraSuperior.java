package umu.pds.duolingoBaratero.windows.components;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import umu.pds.duolingoBaratero.windows.vista.VentanaCreaPregunta;
import umu.pds.duolingoBaratero.windows.vista.VentanaCreaTuCurso;
import umu.pds.duolingoBaratero.windows.vista.VentanaEstadisticas;
import umu.pds.duolingoBaratero.windows.vista.VentanaPrincipal;

public class BarraSuperior extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnHome, btnDesafio, btnEstadisticas, btnModoNocturno, btnCreaTuCurso;
    private JFrame ventanaActual;
    private boolean modoOscuroActivo = false;

    public BarraSuperior(JFrame ventanaActual) {
        setLayout(new BorderLayout());
        this.ventanaActual = ventanaActual;

        // Crear la barra de herramientas y deshabilitar el movimiento
        JToolBar barra = new JToolBar();
        barra.setFloatable(false);

        // Crear un panel para centrar los botones
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear los botones
        btnHome = new JButton("Home 🏠");
        btnHome.addActionListener(e -> openVentanaPrincipal());
        btnDesafio = new JButton("Desafío ⚡");
        btnEstadisticas = new JButton("Estadísticas 📊");
        btnEstadisticas.addActionListener(e -> openVentanaEstadisticas());
        btnModoNocturno = new JButton("Modo Nocturno 🌙");
        btnModoNocturno.addActionListener(e -> toggleModoOscuro()); // Activar/desactivar modo nocturno
        btnCreaTuCurso = new JButton("Crea tu curso");
        btnCreaTuCurso.addActionListener(e -> openVentanaCreaTuCurso());

        // Agregar botones al panel central
        panelCentral.add(btnHome);
        panelCentral.add(btnDesafio);
        panelCentral.add(btnEstadisticas);
        panelCentral.add(btnModoNocturno);
        panelCentral.add(btnCreaTuCurso);

        // Agregar el panel central dentro de la barra de herramientas
        barra.add(panelCentral);

        // Agregar la barra de herramientas a la parte superior del panel
        add(barra, BorderLayout.NORTH);
    }

    private void openVentanaPrincipal() {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
        ventanaActual.dispose();
    }

    private void openVentanaEstadisticas() {
        VentanaEstadisticas ventana = new VentanaEstadisticas();
        ventana.setVisible(true);
        ventanaActual.dispose();
    }
    
    private void openVentanaCreaTuCurso() {
    	if (ventanaActual instanceof VentanaCreaPregunta) {
    		JOptionPane.showMessageDialog(this, "No puedes crear un curso desde esta ventana", "Error",
					JOptionPane.ERROR_MESSAGE);
    	}
    	else {
        	VentanaCreaTuCurso ventana = new VentanaCreaTuCurso();
            ventana.setVisible(true);
            ventanaActual.dispose();    		
    	}

    }

    private void toggleModoOscuro() {
        modoOscuroActivo = !modoOscuroActivo;

        // Aplicar los colores de modo oscuro o modo claro
        if (modoOscuroActivo) {
            setModoOscuro();
        } else {
            setModoClaro();
        }
    }

    private void setModoOscuro() {
        // Cambiar el fondo y los colores del texto a colores oscuros
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        btnHome.setBackground(Color.DARK_GRAY);
        btnHome.setForeground(Color.WHITE);

        btnDesafio.setBackground(Color.DARK_GRAY);
        btnDesafio.setForeground(Color.WHITE);

        btnEstadisticas.setBackground(Color.DARK_GRAY);
        btnEstadisticas.setForeground(Color.WHITE);

        btnModoNocturno.setBackground(Color.DARK_GRAY);
        btnModoNocturno.setForeground(Color.WHITE);

        btnCreaTuCurso.setBackground(Color.DARK_GRAY);
        btnCreaTuCurso.setForeground(Color.WHITE);
    }

    private void setModoClaro() {
        // Cambiar el fondo y los colores del texto a colores claros
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        btnHome.setBackground(Color.LIGHT_GRAY);
        btnHome.setForeground(Color.BLACK);

        btnDesafio.setBackground(Color.LIGHT_GRAY);
        btnDesafio.setForeground(Color.BLACK);

        btnEstadisticas.setBackground(Color.LIGHT_GRAY);
        btnEstadisticas.setForeground(Color.BLACK);

        btnModoNocturno.setBackground(Color.LIGHT_GRAY);
        btnModoNocturno.setForeground(Color.BLACK);

        btnCreaTuCurso.setBackground(Color.LIGHT_GRAY);
        btnCreaTuCurso.setForeground(Color.BLACK);

    }
}
