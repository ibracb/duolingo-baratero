package umu.pds.duolingoBaratero.program;

import javax.swing.UIManager;
import com.jtattoo.plaf.fast.FastLookAndFeel;

import umu.pds.duolingoBaratero.controllers.*;
import umu.pds.duolingoBaratero.persistence.*;
import umu.pds.duolingoBaratero.services.*;
import umu.pds.duolingoBaratero.services.serializers.SerializerFactory;
import umu.pds.duolingoBaratero.windows.vista.VentanaInicio;

/**
 * Clase principal que inicia la aplicación Duolingo Baratero.
 * Configura el aspecto visual, inicializa los DAOs, servicios y controladores,
 * y muestra la ventana de inicio.
 */
public class Program {
	
	/**
	 * Método principal que se ejecuta al iniciar la aplicación.
	 * Configura el aspecto visual, inicializa los DAOs, servicios y controladores,
	 * y muestra la ventana de inicio.
	 *
	 * @param args Argumentos de línea de comandos (no utilizados).
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FastLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DAOs
		DBEstadisticaDAO estadisticaDAO = DBEstadisticaDAO.getDBEstadisticaDAO();
		DBCursoPlantillaDAO plantillaDAO = DBCursoPlantillaDAO.getDBCursoPlantillaDAO();
		DBCursoEnProgresoDAO progresoDAO = DBCursoEnProgresoDAO.getDBCursoEnProgresoDAO();
		DBBloqueContenidoDAO bloqueDAO = DBBloqueContenidoDAO.getDBBloqueContenidoDAO();
		DBPreguntaDAO preguntaDAO = DBPreguntaDAO.getDBPreguntaDAOO();
		DBUsuarioDAO usuarioDAO = DBUsuarioDAO.getDBUsuarioDAO();

		// Servicios
		ServicioCursoProgreso servicioProgreso = new ServicioCursoProgreso(progresoDAO);
		ServicioEstadistica servicioEstadistica = new ServicioEstadistica(estadisticaDAO);
		ServicioCursoPlantilla servicioPlantilla = new ServicioCursoPlantilla(plantillaDAO, progresoDAO, bloqueDAO,
				preguntaDAO, SerializerFactory.INSTANCE);
		
		ServicioUsuario servicioUsuario = new ServicioUsuario(usuarioDAO, servicioProgreso, servicioPlantilla,
				servicioEstadistica);
		
		ServicioPregunta servicioPregunta = new ServicioPregunta();
		ImageService servicioImagenes = new ImageService();

		// Controladores
		ControladorCursoProgreso controladorProgreso = new ControladorCursoProgreso(servicioProgreso);
		ControladorEstadistica controladorEstadistica = new ControladorEstadistica(servicioEstadistica);
		ControladorUsuario controladorUsuario = new ControladorUsuario(servicioUsuario, servicioImagenes);
		ControladorCursoPlantilla controladorPlantilla = new ControladorCursoPlantilla(servicioPlantilla,
				servicioImagenes, AudioService.INSTANCE, servicioUsuario);
		ControladorPregunta controladorPregunta = new ControladorPregunta(servicioPregunta);

		// Vista principal
		VentanaInicio loginWindow = new VentanaInicio(controladorUsuario, controladorPlantilla, controladorProgreso,
				controladorPregunta, controladorEstadistica);

		loginWindow.setLocationRelativeTo(null); // Centrar ventana
		loginWindow.setVisible(true);
	}
}
