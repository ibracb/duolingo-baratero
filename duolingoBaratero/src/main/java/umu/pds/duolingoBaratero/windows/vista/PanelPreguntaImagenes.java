package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;

import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.services.RespuestaPanel;

import java.awt.*;

public class PanelPreguntaImagenes extends JPanel implements RespuestaPanel {

	private static final long serialVersionUID = 1L;
	private JToggleButton[] imagenes; // Botones de imagen
	private JLabel lblPregunta;
	private PreguntaOpciones pregunta;
	private String respuestaUsuario;

	public PanelPreguntaImagenes(PreguntaOpciones pregunta) {
		this.pregunta = pregunta;
		inicializar();
	}

	private void inicializar() {
		setLayout(new BorderLayout()); // Usamos BorderLayout para mejor distribución

		// Panel contenedor para las preguntas e imágenes
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		// Pregunta
		lblPregunta = new JLabel("Cual de estas imagenes respresenta la palabra \"milk\":", SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
		lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(lblPregunta);

		// Panel para las imágenes
		JPanel panelImagenes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		imagenes = new JToggleButton[3];
		ButtonGroup grupoImagenes = new ButtonGroup(); // Para selección única
		String[] photos = new String[3];
		photos[0] = "milk";
		photos[1] = "tea";
		photos[2] = "coffee";
		for (int i = 0; i < 3; i++) {
			imagenes[i] = new JToggleButton(new ImageIcon("src/main/resources/" + photos[i] + ".png"));
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
	public PreguntaOpciones getPregunta() {
		return pregunta;
	}

}
