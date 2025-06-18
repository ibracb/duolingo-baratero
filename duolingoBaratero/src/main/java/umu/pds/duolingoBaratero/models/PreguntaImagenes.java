package umu.pds.duolingoBaratero.models;

import java.util.List;
import javax.swing.JPanel;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import umu.pds.duolingoBaratero.windows.vista.PanelPreguntaImagenes;
/**
 * Representa una pregunta con opciones basadas en imágenes.
 * Extiende la clase abstracta Pregunta.
 * Contiene una lista de opciones (URLs o identificadores de imágenes).
 * Implementa la creación del panel gráfico específico para preguntas con imágenes.
 */
@Entity
@Table(name = "pregunta_imagenes")
@DiscriminatorValue("IMAGENES")
public class PreguntaImagenes extends Pregunta {

    /**
     * Lista de opciones de imágenes para la pregunta.
     */
    @ElementCollection
    @CollectionTable(name = "pregunta_imagenes_opciones", joinColumns = @JoinColumn(name = "pregunta_id"))
    @Column(name = "opciones")
    private List<String> opciones;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public PreguntaImagenes() {
        super();
    }

    /**
     * Constructor con parámetros para inicializar la pregunta con tipo y opciones.
     * 
     * @param nivel           Nivel de dificultad de la pregunta
     * @param numero          Número identificativo de la pregunta
     * @param pregunta        Texto de la pregunta
     * @param respuestaCorrecta Respuesta correcta esperada
     * @param tipo            Tipo de pregunta (debería ser IMAGENES)
     */
    public PreguntaImagenes(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo) {
        super(nivel, numero, pregunta, respuestaCorrecta, tipo);
    }

    /**
     * Constructor que incluye opciones.
     * 
     * @param nivel           Nivel de dificultad
     * @param numero          Número de pregunta
     * @param pregunta        Texto de la pregunta
     * @param respuestaCorrecta Respuesta correcta
     * @param tipo            Tipo de pregunta
     * @param opciones        Lista de opciones de imágenes
     */
    public PreguntaImagenes(Nivel nivel, int numero, String pregunta, String respuestaCorrecta, TipoPregunta tipo,
                           List<String> opciones) {
        super(nivel, numero, pregunta, respuestaCorrecta, tipo);
        this.opciones = opciones;
    }

    /**
     * Obtiene la lista de opciones de imágenes.
     * 
     * @return Lista de opciones
     */
    public List<String> getOpciones() {
        return opciones;
    }

    /**
     * Establece la lista de opciones de imágenes.
     * 
     * @param opciones Lista de opciones a establecer
     */
    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    /**
     * Crea el panel Swing específico para mostrar esta pregunta con imágenes.
     * 
     * @return JPanel con la interfaz gráfica para la pregunta
     */
    @Override
    public JPanel crearPanel() {
        return new PanelPreguntaImagenes(this);
    }

}
