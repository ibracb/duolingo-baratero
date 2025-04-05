package umu.pds.apps;

import umu.pds.duolingoBaratero.controllers.ControladorCurso;
import umu.pds.duolingoBaratero.models.Aprendizaje;
import umu.pds.duolingoBaratero.models.BloqueContenido;
import umu.pds.duolingoBaratero.models.BloqueContenidoProgreso;
import umu.pds.duolingoBaratero.models.CursoEnProgreso;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.PreguntaAudio;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.models.Valoracion;

public class ProbarSerializacion {
	
	public static void main(String[] args) {
		
		Usuario usuario = new Usuario("ibrahim", "Ibra", "ibra@gmail.com", "1234", "src/main/resources/profile.png");
		
		PreguntaAudio pregunta1 = new PreguntaAudio(Nivel.BASICO, 1, "Pregunta 1?", "a", new String[] {"opcion1", "opcion2", "opcion3"}, "src/main/resources/audios/town-10169.mp3");
		PreguntaOpciones pregunta2 = new PreguntaOpciones(Nivel.PRINCIPIANTE, 2, "Pregunta 2?", "b", TipoPregunta.OPCIONES, new String[] {"opcion1", "opcion2", "opcion3"});
		BloqueContenido bloqueContenido1 = new BloqueContenido(0, pregunta1, pregunta2);
		
		PreguntaOpciones pregunta3 = new PreguntaOpciones(Nivel.INTERMEDIO, 3, "Pregunta 3?", "c", TipoPregunta.OPCIONES, new String[] {"opcion1", "opcion2", "opcion3"});
		PreguntaAudio pregunta4 = new PreguntaAudio(Nivel.AVANZADO, 4, "Pregunta 4?", "d", new String[] {"opcion1", "opcion2", "opcion3"}, "src/main/resources/audios/town-10169.mp3");
		BloqueContenido bloqueContenido2 = new BloqueContenido(1, pregunta3, pregunta4);
		
		CursoPlantilla plantilla1 = new CursoPlantilla("plantilla_prueba_1", usuario, "descripcion prueba", "objetivos prueba", Nivel.BASICO, bloqueContenido1, bloqueContenido2);
		
		CursoPlantilla plantilla2 = new CursoPlantilla("plantilla_prueba_2", usuario, "descripcion prueba", "objetivos prueba", Nivel.BASICO, bloqueContenido1, bloqueContenido2);
		
		BloqueContenidoProgreso bp1 = new BloqueContenidoProgreso(
			new CursoEnProgreso(usuario, plantilla1, Aprendizaje.SECUENCIAL, Valoracion.CERO, new BloqueContenidoProgreso(null, bloqueContenido1)),
			bloqueContenido1);
		CursoEnProgreso progreso2 = new CursoEnProgreso(usuario, plantilla1, Aprendizaje.SECUENCIAL, Valoracion.CUATRO, bp1);
		
		BloqueContenidoProgreso bp3 = new BloqueContenidoProgreso(
				new CursoEnProgreso(usuario, plantilla2, Aprendizaje.SECUENCIAL, Valoracion.CERO, new BloqueContenidoProgreso(null, bloqueContenido1)),
				bloqueContenido2);
		CursoEnProgreso progreso4 = new CursoEnProgreso(usuario, plantilla2, Aprendizaje.SECUENCIAL, Valoracion.CUATRO, bp3);
		
		ControladorCurso.INSTANCE.compartirCurso(progreso2);
		ControladorCurso.INSTANCE.compartirCurso(progreso4);
	}
	
}
