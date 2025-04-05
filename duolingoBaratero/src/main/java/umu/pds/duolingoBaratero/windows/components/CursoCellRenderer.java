package umu.pds.duolingoBaratero.windows.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;

public class CursoCellRenderer extends JPanel implements ListCellRenderer<CursoEnProgreso> {

	private static final long serialVersionUID = 1L;
	private JLabel lblIcono = new JLabel();
    private JLabel lblNombre = new JLabel();
    private JLabel lblNivel = new JLabel();

    public CursoCellRenderer() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBackground(Color.LIGHT_GRAY);

        JPanel panelTexto = new JPanel(new GridLayout(2, 1));
        panelTexto.add(lblNombre);
        panelTexto.add(lblNivel);

        add(lblIcono, BorderLayout.WEST);
        add(panelTexto, BorderLayout.CENTER);
   }

    @Override
	public Component getListCellRendererComponent(JList<? extends CursoEnProgreso> list, CursoEnProgreso curso, int index,
			boolean isSelected, boolean cellHasFocus) {
    	
    	if(curso==null) {
    		lblIcono.setIcon(null);
			lblNombre.setText("Curso no encontrado");
			lblNivel.setText("Nivel: Sin curso, no hay nivel");
		} else {
			ImageIcon image = new ImageIcon(getClass().getResource("/" + curso.getNombre() +".png"));
			image = ControladorCurso.INSTANCE.getScaledImage(image, 100);
			lblIcono.setIcon(image); // Imagen de ejemplo
			lblNombre.setText(curso.getNombre());
			lblNivel.setText("Nivel: " + curso.getNivel());
    	}
    	return this;
    }

}