package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;

import umu.pds.duolingoBaratero.models.Pregunta;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.services.IComprobador;

import java.awt.*;

public class PanelPreguntaOpciones extends JPanel implements IComprobador {

    private static final long serialVersionUID = 1L;
    private JLabel lblPregunta;
    private JToggleButton[] opciones; // Botones de imagen
    private JLabel lblAudio;
    private PreguntaOpciones pregunta;
	private String respuestaUsuario;

    public PanelPreguntaOpciones(PreguntaOpciones pregunta) {
        this.pregunta = pregunta;
        inicializar();
    }
    

	private void inicializar() {
		setLayout(new BorderLayout()); // Usamos BorderLayout para mejor distribución

		// Panel contenedor para las preguntas e imágenes
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		JPanel panelPregunta = new JPanel();
		panelPregunta.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));  // 20 píxeles de margen arriba
		panelPregunta.setLayout(new FlowLayout());

		// Etiqueta de la pregunta
		lblPregunta = new JLabel(pregunta.getPregunta(),
				SwingConstants.CENTER);
		lblPregunta.setFont(new Font("Arial", Font.BOLD, 24));
		lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Añadir la etiqueta y el botón al panel
		panelPregunta.add(lblPregunta);

		// Añadir el panelEscucha a tu panel principal
		panelCentral.add(panelPregunta);

		// Panel para las opciones
		JPanel panelOpciones = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.NONE; // Evita que los botones se expandan demasiado en altura
		gbc.insets = new Insets(10, 20, 15, 20);

		opciones = new JToggleButton[3];
		ButtonGroup grupoOpciones = new ButtonGroup();

		for (int i = 0; i < 3; i++) {
//			opciones[i] = new JToggleButton(pregunta.getOpciones()[i]);
			opciones[i].setFont(new Font("Arial", Font.PLAIN, 15));

			opciones[i].setMinimumSize(new Dimension(300, 75)); // Tamaño mínimo
			opciones[i].setPreferredSize(new Dimension(450, 170)); // Tamaño fijo
			opciones[i].setMaximumSize(new Dimension(500, 170)); // Tamaño máximo
			grupoOpciones.add(opciones[i]);
			panelOpciones.add(opciones[i], gbc);
			
			int index = i;
			opciones[i].addActionListener(e -> {respuestaUsuario = opciones[index].getText();} );
		}

		// Agregar elementos al panel principal
		panelCentral.add(panelOpciones);
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