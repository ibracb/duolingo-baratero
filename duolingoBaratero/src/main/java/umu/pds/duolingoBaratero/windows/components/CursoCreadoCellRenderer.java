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
import umu.pds.duolingoBaratero.models.CursoPlantilla;

/**
 * Clase que implementa un renderizador de celdas para mostrar información de cursos creados.
 * Muestra el icono, nombre, nivel y propietario del curso.
 */
public class CursoCreadoCellRenderer extends JPanel implements ListCellRenderer<CursoPlantilla> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiqueta para mostrar el icono del curso.
	 */
	private JLabel lblIcono = new JLabel();
    
	/**
	 * Etiqueta para mostrar el nombre del curso.
	 */
	private JLabel lblNombre = new JLabel();
    
	/**
	 * Etiqueta para mostrar el nivel del curso.
	 */
	private JLabel lblNivel = new JLabel();
    
	/**
	 * Etiqueta para mostrar el nombre del propietario del curso.
	 */
	private final JLabel lblNombrePropietario = new JLabel();
    
	/**
	 * Controlador para manejar la lógica de los cursos plantilla.
	 */
	private final ControladorCursoPlantilla controladorPlantilla;
	
	/**
	 * Inicializa un nuevo renderizador de celdas para cursos creados.
	 * @param controladorPlantilla
	 */
    public CursoCreadoCellRenderer(ControladorCursoPlantilla controladorPlantilla) {
    	this.controladorPlantilla = controladorPlantilla;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBackground(Color.LIGHT_GRAY);

        JPanel panelTexto = new JPanel(new GridLayout(2, 1));
        panelTexto.add(lblNombre);
        panelTexto.add(lblNivel);
        panelTexto.add(lblNombrePropietario);
        add(lblIcono, BorderLayout.WEST);
        add(panelTexto, BorderLayout.CENTER);
        
    }

    @Override
	public Component getListCellRendererComponent(JList<? extends CursoPlantilla> list, CursoPlantilla curso, int index,
			boolean isSelected, boolean cellHasFocus) {
    	ImageIcon image = new ImageIcon(getClass().getResource("/"+curso.getNombre()+".png"));
    	image = controladorPlantilla.getScaledImage(image, 100);
    	lblNombrePropietario.setText(controladorPlantilla.getNombrePropietario(curso));
    	lblIcono.setIcon(image); // Imagen de ejemplo
        lblNombre.setText(curso.getNombre());
        lblNivel.setText("Nivel: " + curso.getNivel());
        return this;
    }

}