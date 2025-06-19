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
import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;

/**
 * Clase que implementa un renderizador de celdas para mostrar información de cursos en progreso.
 * Muestra el icono del curso, su nombre y nivel.
 */
public class CursoCellRenderer extends JPanel implements ListCellRenderer<CursoEnProgreso> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiqueta que muestra el icono del curso.
	 */
	private JLabel lblIcono = new JLabel();
    
	/**
	 * Etiqueta que muestra el nombre del curso.
	 */
	private JLabel lblNombre = new JLabel();
    
	/**
	 * Etiqueta que muestra el nivel del curso.
	 */
	private JLabel lblNivel = new JLabel();
    
	/**
	 * Controlador de curso plantilla, utilizado para obtener imágenes escaladas.
	 */
	private final ControladorCursoPlantilla controladorPlantilla;
	
	/**
	 * Constructor de la clase CursoCellRenderer.
	 * @param controladorPlantilla
	 */
    public CursoCellRenderer(ControladorCursoPlantilla controladorPlantilla) {
        this.controladorPlantilla = controladorPlantilla;
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
			image = controladorPlantilla.getScaledImage(image, 100);
			lblIcono.setIcon(image); // Imagen de ejemplo
			lblNombre.setText(curso.getNombre());
			lblNivel.setText("Nivel: " + curso.getNivel());
    	}
    	return this;
    }

}