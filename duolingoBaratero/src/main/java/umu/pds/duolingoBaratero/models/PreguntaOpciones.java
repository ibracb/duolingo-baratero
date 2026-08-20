package umu.pds.duolingoBaratero.models;

import java.util.List;

import javax.swing.JPanel;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaOpciones;

/**
 * Representa una pregunta con opciones basadas en opciones.
 * Extiende la clase abstracta Pregunta.
 * Contiene una lista de opciones.
 * Implementa la creación del panel gráfico específico para preguntas con opcioens.
 */
@Entity
@DiscriminatorValue("OPCIONES")
public class PreguntaOpciones extends Pregunta {

    /**
     * Lista de opciones posibles para responder la pregunta.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pregunta_opciones_opciones", joinColumns = @JoinColumn(name = "pregunta_id"))
    @Column(name = "opciones")
    private List<String> opciones;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public PreguntaOpciones() {
        super();
    }

    /**
     * Constructor con parámetros para inicializar la pregunta sin opciones.
     * 
     * @param nivel Nivel de dificultad de la pregunta
     * @param numero Número identificativo de la pregunta
     * @param pregunta Texto de la pregunta
     * @param respuestaCorrecta Respuesta correcta esperada
     * @param tipo Tipo de pregunta (debe ser OPCIONES)
     */
    public PreguntaOpciones(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
        super(nivel, numero, pregunta, respuestaCorrecta, tipo);
    }

    /**
     * Constructor con opciones.
     * 
     * @param nivel Nivel de dificultad
     * @param numero Número de pregunta
     * @param pregunta Texto de la pregunta
     * @param respuestaCorrecta Respuesta correcta
     * @param tipo Tipo de pregunta
     * @param opciones Lista de opciones posibles
     */
    public PreguntaOpciones(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
                           List<String> opciones) {
        super(nivel, numero, pregunta, respuestaCorrecta, tipo);
        this.opciones = opciones;
    }

    /**
     * Crea el panel Swing específico para mostrar esta pregunta de opciones.
     * 
     * @return JPanel con la interfaz gráfica para la pregunta
     */
    @Override
    public JPanel crearPanel() {
        return new PanelPreguntaOpciones(this);
    }

    /**
     * Obtiene la lista de opciones.
     * 
     * @return Lista de opciones de respuesta
     */
    public List<String> getOpciones() {
        return opciones;
    }

    /**
     * Establece la lista de opciones.
     * 
     * @param opciones Lista de opciones a asignar
     */
    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }
}