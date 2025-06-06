package umu.pds.duolingoBaratero.program;

import javax.swing.UIManager;

import com.jtattoo.plaf.fast.FastLookAndFeel;

import umu.pds.duolingoBaratero.controllers.ControladorCursoPlantilla;
import umu.pds.duolingoBaratero.controllers.ControladorCursoProgreso;
import umu.pds.duolingoBaratero.controllers.ControladorPregunta;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;
import umu.pds.duolingoBaratero.persistence.DBBloqueContenidoDAO;
import umu.pds.duolingoBaratero.persistence.DBCursoEnProgresoDAO;
import umu.pds.duolingoBaratero.persistence.DBCursoPlantillaDAO;
import umu.pds.duolingoBaratero.persistence.DBPreguntaDAO;
import umu.pds.duolingoBaratero.persistence.DBUsuarioDAO;
import umu.pds.duolingoBaratero.services.AudioService;
import umu.pds.duolingoBaratero.services.ImageService;
import umu.pds.duolingoBaratero.services.ServicioCursoPlantilla;
import umu.pds.duolingoBaratero.services.ServicioCursoProgreso;
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
		ServicioCursoProgreso servicioProgreso = new ServicioCursoProgreso();
		ControladorCursoProgreso controladorProgreso = new ControladorCursoProgreso(servicioProgreso);
		DBUsuarioDAO usuarioDAO = DBUsuarioDAO.getDBUsuarioDAO(); 
		DBCursoPlantillaDAO plantillaDAO = DBCursoPlantillaDAO.getDBCursoPlantillaDAO();
		DBCursoEnProgresoDAO progresoDAO = DBCursoEnProgresoDAO.getDBCursoEnProgresoDAO();
		DBBloqueContenidoDAO bloqueDAO = DBBloqueContenidoDAO.getDBBloqueContenidoDAO();
		DBPreguntaDAO preguntaDAO = DBPreguntaDAO.getDBPreguntaDAOO();
		ImageService servicioImagenes = new ImageService();
		ServicioCursoPlantilla servicioPlantilla = new ServicioCursoPlantilla(plantillaDAO, progresoDAO, bloqueDAO, preguntaDAO, SerializerFactory.INSTANCE);
		ServicioUsuario servicioUsuario = new ServicioUsuario(usuarioDAO, controladorProgreso, null);
		ControladorUsuario controladorUsuario = new ControladorUsuario(servicioUsuario, servicioImagenes);
		ControladorCursoPlantilla controladorPlantilla = new ControladorCursoPlantilla(servicioPlantilla, servicioImagenes, AudioService.INSTANCE, controladorUsuario);
		ServicioPregunta servicioPregunta = new ServicioPregunta();
		ControladorPregunta controladorPregunta = new ControladorPregunta(servicioPregunta);
		VentanaInicio loginWindow = new VentanaInicio(controladorUsuario, controladorPlantilla, controladorProgreso, controladorPregunta);
		loginWindow.setLocationRelativeTo(null); // Esto centra la ventana
		loginWindow.setVisible(true);
	}
}
