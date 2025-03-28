package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class VentanaElegirTipoPregunta extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private VentanaCreaTuCurso v;

	public VentanaElegirTipoPregunta(VentanaCreaTuCurso v) {
		this.v = v;	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setSize(200, 100);
		JPanel panelCombox = new JPanel();
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pregunta Audio", "Pregunta Fotos", "FlashCard", "Pregunta Opciones"}));
		comboBox.addActionListener(e -> manejarSeleccion((String)comboBox.getSelectedItem()));
		panelCombox.add(comboBox);
		getContentPane().add(panelCombox, BorderLayout.CENTER);
		JPanel panelEtiquieta= new JPanel();
		JLabel lblNewLabel = new JLabel("Elige el tipo de pregunta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelEtiquieta.add(lblNewLabel);
		getContentPane().add(panelEtiquieta, BorderLayout.NORTH);
	}
	
	private void manejarSeleccion(String tipoPregunta) {
		JFrame ventana = new JFrame();
		switch(tipoPregunta) {
		case "Pregunta Opciones": 
			ventana = new VentanaCreaPreguntaOpciones(v);
			break;
		case "Pregunta Audio":
			break;
		case "Pregunta Fotos":
			break;
		case "FlashCard":
			break;
		}
		ventana.setVisible(true);
		this.dispose();
	}

	
}
