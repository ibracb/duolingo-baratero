package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.*;
import java.awt.*;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.windows.components.BarraSuperior;

public class VentanaEstadisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final ControladorUsuario cUsuario;
	private final ControladorCursoPlantilla cPlantilla;
	private final ControladorCursoProgreso cProgreso;
	private ControladorPregunta cPregunta;
	
	public VentanaEstadisticas(ControladorUsuario cUsuario, ControladorCursoPlantilla cPlantilla, ControladorCursoProgreso cProgreso,
			ControladorPregunta cPregunta) {
        // Configuración de la ventana
        this.cUsuario = cUsuario;
        this.cPlantilla = cPlantilla;
        this.cProgreso = cProgreso;
        this.cPregunta = cPregunta;
		setTitle("Estadísticas de Uso");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Barra superior con botones
        BarraSuperior barraSuperior = new BarraSuperior(this, cUsuario, cPlantilla, cProgreso, cPregunta);
        add(barraSuperior, BorderLayout.NORTH);

        // Panel central con título
        JPanel panelCentral = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("ESTADÍSTICAS DE USO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelCentral.add(titulo, BorderLayout.NORTH);

        // Panel para las estadísticas (2x2 Grid)
        JPanel panelEstadisticas = new JPanel(new GridLayout(2, 2, 10, 10));
        panelEstadisticas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Estilo para los paneles de estadísticas
        Color panelColor = new Color(240, 240, 240);
        Font font = new Font("Arial", Font.PLAIN, 14);

        // Panel 1 - Tiempo de uso
        JPanel panelTiempoUso = new JPanel(new GridLayout(2, 1));
        panelTiempoUso.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        panelTiempoUso.setBackground(panelColor);
        JLabel lblTiempoUso = new JLabel("⏳ Tiempo de uso:", SwingConstants.CENTER);
        lblTiempoUso.setFont(font);
        JLabel lblValorTiempoUso = new JLabel(getTiempoUso(), SwingConstants.CENTER);
        lblValorTiempoUso.setFont(new Font("Arial", Font.BOLD, 14));
        panelTiempoUso.add(lblTiempoUso);
        panelTiempoUso.add(lblValorTiempoUso);
        
        // Panel 2 - Mejor racha de victorias
        JPanel panelRachaVictorias = new JPanel(new GridLayout(2, 1));
        panelRachaVictorias.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        panelRachaVictorias.setBackground(panelColor);
        JLabel lblRachaVictorias = new JLabel("🏆 Mejor racha de victorias:", SwingConstants.CENTER);
        lblRachaVictorias.setFont(font);
        JLabel lblValorRachaVictorias = new JLabel(getMaxVictorias(), SwingConstants.CENTER);
        lblValorRachaVictorias.setFont(new Font("Arial", Font.BOLD, 14));
        panelRachaVictorias.add(lblRachaVictorias);
        panelRachaVictorias.add(lblValorRachaVictorias);
        
        // Panel 3 - Racha de accesos
        JPanel panelRachaAccesos = new JPanel(new GridLayout(2, 1));
        panelRachaAccesos.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        panelRachaAccesos.setBackground(panelColor);
        JLabel lblRachaAccesos = new JLabel("🔑 Racha de accesos:", SwingConstants.CENTER);
        lblRachaAccesos.setFont(font);
        JLabel lblValorRachaAccesos = new JLabel(getMaxNumAccesos(), SwingConstants.CENTER);
        lblValorRachaAccesos.setFont(new Font("Arial", Font.BOLD, 14));
        panelRachaAccesos.add(lblRachaAccesos);
        panelRachaAccesos.add(lblValorRachaAccesos);
        
        // Panel 4 - Porcentaje de respuestas correctas
        JPanel panelRespuestasCorrectas = new JPanel(new GridLayout(2, 1));
        panelRespuestasCorrectas.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        panelRespuestasCorrectas.setBackground(panelColor);
        JLabel lblRespuestasCorrectas = new JLabel("📊 % de respuestas correctas:", SwingConstants.CENTER);
        lblRespuestasCorrectas.setFont(font);
        JLabel lblValorRespuestasCorrectas = new JLabel(getPorcentajeAciertos(), SwingConstants.CENTER);
        lblValorRespuestasCorrectas.setFont(new Font("Arial", Font.BOLD, 14));
        panelRespuestasCorrectas.add(lblRespuestasCorrectas);
        panelRespuestasCorrectas.add(lblValorRespuestasCorrectas);

        // Añadir los paneles de estadísticas al panel principal
        panelEstadisticas.add(panelTiempoUso);
        panelEstadisticas.add(panelRachaVictorias);
        panelEstadisticas.add(panelRachaAccesos);
        panelEstadisticas.add(panelRespuestasCorrectas);

        panelCentral.add(panelEstadisticas, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

    }
	
	private String getPorcentajeAciertos() {
		return cUsuario.getPorcentajeRespuestasCorrectas() + "%";
	}
	
	private String getMaxNumAccesos() {
		return String.valueOf(cUsuario.getNumMaxAccesos());
	}
	
	private String getMaxVictorias() {
		return String.valueOf(cUsuario.getRachaVictorias());
	}
	
	private String getTiempoUso() {
		return String.valueOf(cUsuario.getTiempoUso()) + "horas";
	}
}
