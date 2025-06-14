package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.models.PreguntaImagenes;
import umu.pds.duolingoBaratero.services.IComprobador;

public class PanelPreguntaImagenes extends JPanel implements IComprobador {

	private static final long serialVersionUID = 1L;
	private JToggleButton[] imagenes; // Botones de imagen
	private JLabel lblPregunta;
	private PreguntaImagenes pregunta;
	private String respuestaUsuario;

	public PanelPreguntaImagenes(PreguntaImagenes preguntaImagenes) {
		this.pregunta = preguntaImagenes;
		inicializar();
	}

	private void inicializar() {
		setLayout(new BorderLayout()); // Usamos BorderLayout para mejor distribución

		// Panel contenedor para las preguntas e imágenes
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		// Pregunta
		lblPregunta = new JLabel(pregunta.getPregunta(), SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
		lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(lblPregunta);

		// Panel para las imágenes
		JPanel panelImagenes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		List<String> opcionesLista = new ArrayList<>(pregunta.getOpciones());  // conviertes a ArrayList "real"

		imagenes = new JToggleButton[opcionesLista.size()];
		ButtonGroup grupoImagenes = new ButtonGroup(); // Para selección única
		for (int i = 0; i < 3; i++) {
			imagenes[i] = new JToggleButton(new ImageIcon("src/main/resources/" + opcionesLista.get(i) ));
			imagenes[i].setPreferredSize(new Dimension(250, 250));
			grupoImagenes.add(imagenes[i]);
			panelImagenes.add(imagenes[i]);

			int index = i;
			imagenes[i].addActionListener(e -> {
				respuestaUsuario = imagenes[index].getText();
			});
		}

		// Agregar elementos al panel principal
		panelCentral.add(panelImagenes);
		add(panelCentral, BorderLayout.CENTER);
	}
	
	@Override
	public String getRespuestaUsuario() {
		return respuestaUsuario;
	}
	@Override
	public boolean isOpcionElegida() {
		return respuestaUsuario != null;
	}
	
	@Override
	public PreguntaImagenes getPregunta() {
		return pregunta;
	}

}
