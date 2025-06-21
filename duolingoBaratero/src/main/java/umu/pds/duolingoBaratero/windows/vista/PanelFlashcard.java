package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import umu.pds.duolingoBaratero.models.Flashcard;
import umu.pds.duolingoBaratero.services.IComprobador;

/**
 * PanelFlashcard es un componente de interfaz gráfica que muestra una pregunta
 * de tipo flashcard y permite al usuario interactuar con ella para ver la
 * respuesta y registrar su acierto o fallo.
 */
public class PanelFlashcard extends JPanel implements IComprobador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiqueta que muestra la pregunta de la flashcard.
	 */
	private JLabel label;
	
	/**
	 * Botón que permite al usuario ver la solución de la flashcard.
	 */
	private JButton button;
	
	/**
	 * La pregunta de tipo Flashcard que contiene la pregunta y la respuesta
	 * correcta.
	 */
	private Flashcard pregunta;
	
	/**
	 * Respuesta del usuario, que puede ser "acierto" o "fallo".
	 */
	private String respuestaUsuario;
	
	/**
	 * Constructor que inicializa el panel con una pregunta de tipo Flashcard.
	 * 
	 * @param pregunta La pregunta de tipo Flashcard que se mostrará en el panel.
	 */
	public PanelFlashcard(Flashcard pregunta) {
		this.pregunta = pregunta;
		inicializar();
	}
	
	/**
	 * Inicializa el panel configurando su diseño, añadiendo la etiqueta de la
	 * pregunta y el botón para ver la solución.
	 */
	private void inicializar() {
		setLayout(new BorderLayout());

		label = new JLabel(pregunta.getPregunta(), SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 20));

		button = new JButton("Ver solución");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarRespuesta();
			}
		});

		add(label, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}
	
	/**
	 * Muestra la respuesta correcta de la flashcard y permite al usuario
	 * registrar si ha acertado o fallado.
	 */
	private void mostrarRespuesta() {
		label.setText(pregunta.getRespuestaCorrecta());

		// Remover botón actual
		remove(button);

		// Crear botones de acierto y fallo
		JButton btnAcierto = new JButton("Acierto");
		JButton btnFallo = new JButton("Fallo");

		// Acción para registrar acierto
		btnAcierto.addActionListener(e -> registrarRespuesta("acierto"));
		btnFallo.addActionListener(e -> registrarRespuesta("fallo"));

		// Crear panel para los botones
		JPanel panelBotones = new JPanel();
		panelBotones.add(btnAcierto);
		panelBotones.add(btnFallo);

		add(panelBotones, BorderLayout.SOUTH);
		revalidate();
		repaint();
	}
	
	/**
	 * Registra la respuesta del usuario y resetea el flashcard para mostrar la
	 * pregunta nuevamente.
	 * 
	 * @param respuestaUsuario La respuesta del usuario, que puede ser "acierto" o
	 *                         "fallo".
	 */
	private void registrarRespuesta(String respuestaUsuario) {
		this.respuestaUsuario = respuestaUsuario;
		resetFlashcard();
	}
	
	/**
	 * Resetea el flashcard para mostrar la pregunta original y el botón para ver
	 * la solución.
	 */
	private void resetFlashcard() {
		label.setText(pregunta.getPregunta());

		// Restaurar botón inicial
		remove(getComponent(1)); // Quita el panel de botones
		button.setText("Ver solución");
		add(button, BorderLayout.SOUTH);

		revalidate();
		repaint();
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
	public Flashcard getPregunta() {
		return pregunta;
	}

}
