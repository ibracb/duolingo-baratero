package umu.pds.duolingoBaratero.program;

import javax.swing.UIManager;

import com.jtattoo.plaf.fast.FastLookAndFeel;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorEstadistica;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.persistence.DBBloqueContenidoDAO;
import umu.pds.duolingoBaratero.persistence.DBCursoEnProgresoDAO;
import umu.pds.duolingoBaratero.persistence.DBCursoPlantillaDAO;
import umu.pds.duolingoBaratero.persistence.DBEstadisticaDAO;
import umu.pds.duolingoBaratero.persistence.DBPreguntaDAO;
import umu.pds.duolingoBaratero.persistence.DBUsuarioDAO;
import umu.pds.duolingoBaratero.services.AudioService;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.ServicioCursoPlantilla;
import umu.pds.duolingoBaratero.services.ServicioCursoProgreso;
import umu.pds.duolingoBaratero.services.ServicioEstadistica;
import umu.pds.duolingoBaratero.services.ServicioPregunta;
import umu.pds.duolingoBaratero.services.ServicioUsuario;
import umu.pds.duolingoBaratero.services.serializers.SerializerFactory;
import umu.pds.duolingoBaratero.windows.vista.VentanaInicio;

public class Program {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FastLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBEstadisticaDAO estadisticaDAO = DBEstadisticaDAO.getDBEstadisticaDAO();
		DBCursoPlantillaDAO plantillaDAO = DBCursoPlantillaDAO.getDBCursoPlantillaDAO();
		DBCursoEnProgresoDAO progresoDAO = DBCursoEnProgresoDAO.getDBCursoEnProgresoDAO();
		DBBloqueContenidoDAO bloqueDAO = DBBloqueContenidoDAO.getDBBloqueContenidoDAO();
		DBPreguntaDAO preguntaDAO = DBPreguntaDAO.getDBPreguntaDAOO();
		ServicioCursoProgreso servicioProgreso = new ServicioCursoProgreso(progresoDAO);
		ControladorCursoProgreso controladorProgreso = new ControladorCursoProgreso(servicioProgreso);
		DBUsuarioDAO usuarioDAO = DBUsuarioDAO.getDBUsuarioDAO(); 

		ImageService servicioImagenes = new ImageService();
		ServicioEstadistica servicioEstadistica = new ServicioEstadistica(estadisticaDAO);
		ServicioCursoPlantilla servicioPlantilla = new ServicioCursoPlantilla(plantillaDAO, progresoDAO, bloqueDAO, preguntaDAO, SerializerFactory.INSTANCE);
		ControladorEstadistica controladorEstadistica = new ControladorEstadistica(servicioEstadistica);
		ServicioUsuario servicioUsuario = new ServicioUsuario(usuarioDAO, servicioProgreso, servicioPlantilla, servicioEstadistica);
		ControladorUsuario controladorUsuario = new ControladorUsuario(servicioUsuario, servicioImagenes);
		ControladorCursoPlantilla controladorPlantilla = new ControladorCursoPlantilla(servicioPlantilla, servicioImagenes, AudioService.INSTANCE, servicioUsuario);
		ServicioPregunta servicioPregunta = new ServicioPregunta();
		ControladorPregunta controladorPregunta = new ControladorPregunta(servicioPregunta);
		VentanaInicio loginWindow = new VentanaInicio(controladorUsuario, controladorPlantilla, controladorProgreso, controladorPregunta,controladorEstadistica );
		loginWindow.setLocationRelativeTo(null); // Esto centra la ventana
		loginWindow.setVisible(true);
	}
}
