package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.aprendizajes.AprendizajeSeleccionado;

public class VentanaSeleccionEstrategica extends JDialog  {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private VentanaPrincipal v;
    private CursoEnProgreso curso;
    private ControladorCursoProgreso cProgreso;
    public VentanaSeleccionEstrategica(VentanaPrincipal v, CursoEnProgreso curso, ControladorCursoProgreso cProgreso
    		) {
    	super(v, "Selecciona una estrategia de aprendizaje", true);
    	this.v = v;
    	this.curso = curso;
    	this.cProgreso = cProgreso;
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSeleccionEstrategica.class.getResource("/com/jtattoo/plaf/icons/large/cup_24x24.png")));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panelArriba = new JPanel();
        contentPane.add(panelArriba, BorderLayout.NORTH);
        
        JLabel lblMensaje = new JLabel("Selecciona una estrategia de aprendizaje");
        panelArriba.add(lblMensaje);
        
        JPanel panelAbajo = new JPanel();
        contentPane.add(panelAbajo, BorderLayout.CENTER);
        GridBagLayout gbl_panelAbajo = new GridBagLayout();
        panelAbajo.setLayout(gbl_panelAbajo);
        
        // Tamaño cuadrado de los botones
        Dimension buttonSize = new Dimension(400, 400);
        
        JButton btnEstrategia1 = new JButton("📈Secuencial");
        btnEstrategia1.setPreferredSize(buttonSize);
        GridBagConstraints gbc_btnEstrategia1 = new GridBagConstraints();
        gbc_btnEstrategia1.insets = new Insets(5, 5, 5, 5);
        gbc_btnEstrategia1.gridx = 2;
        gbc_btnEstrategia1.gridy = 3;
        panelAbajo.add(btnEstrategia1, gbc_btnEstrategia1);
        btnEstrategia1.addActionListener(e -> setAprendizaje(AprendizajeSeleccionado.SECUENCIAL));
        
        JButton btnEstrategia2 = new JButton("🔄Invertido");
        btnEstrategia2.setPreferredSize(buttonSize);
        GridBagConstraints gbc_btnEstrategia2 = new GridBagConstraints();
        gbc_btnEstrategia2.insets = new Insets(5, 5, 5, 5);
        gbc_btnEstrategia2.gridx = 4;
        gbc_btnEstrategia2.gridy = 3;
        panelAbajo.add(btnEstrategia2, gbc_btnEstrategia2);
        btnEstrategia2.addActionListener(e -> setAprendizaje(AprendizajeSeleccionado.INVERTIDO));
        
        JButton btnEstrategia3 = new JButton("🎲Aleatorio");
        btnEstrategia3.setPreferredSize(buttonSize);
        GridBagConstraints gbc_btnEstrategia3 = new GridBagConstraints();
        gbc_btnEstrategia3.insets = new Insets(5, 5, 5, 5);
        gbc_btnEstrategia3.gridx = 7;
        gbc_btnEstrategia3.gridy = 3;
        panelAbajo.add(btnEstrategia3, gbc_btnEstrategia3);
        btnEstrategia3.addActionListener(e -> setAprendizaje(AprendizajeSeleccionado.ALEATORIO));

    }
    
    private void setAprendizaje(AprendizajeSeleccionado aprendizajeSeleccionado) {
    	if (cProgreso.configurarCursoProgreso(curso, aprendizajeSeleccionado)) {
    		// Mensaje de exito
    		v.refreshCursos();
    		v.setVisible(true);
    		this.dispose();
    	}else {
    		//Mensaje de error
    	}
    	
    }
    
    
}
