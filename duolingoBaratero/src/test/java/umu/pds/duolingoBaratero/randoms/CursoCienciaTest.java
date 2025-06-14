package umu.pds.duolingoBaratero.randoms;

import umu.pds.duolingoBaratero.models.BloqueContenido;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;
import umu.pds.duolingoBaratero.models.PreguntaOpciones;
import umu.pds.duolingoBaratero.models.TipoPregunta;
import umu.pds.duolingoBaratero.models.Usuario;
import umu.pds.duolingoBaratero.services.serializers.Serializer;
import umu.pds.duolingoBaratero.services.serializers.SerializerFactory;

public class CursoCienciaTest {
	
	public static void main(String[] args) {
		
		//Usuario de prueba
		Usuario usuarioPrueba = new Usuario("Profesor B", "Mr B", "profesorb@gmail.com", "1234");
		
		// Curso de Ciencia
				BloqueContenido bloque1Ciencia = new BloqueContenido(0,
						new PreguntaOpciones(Nivel.BASICO, 1, "¿Qué estudia la biología?", "Los seres vivos",
								TipoPregunta.OPCIONES,
								new String[] { "Los seres vivos", "Los planetas", "Los elementos químicos", "Las rocas" }),
						new PreguntaOpciones(Nivel.BASICO, 2, "¿Cuál es la fórmula del agua?", "H2O", TipoPregunta.OPCIONES,
								new String[] { "H2O", "CO2", "O2", "H2SO4" }),
						new PreguntaOpciones(Nivel.BASICO, 3, "¿Cuál es el planeta más grande del sistema solar?", "Júpiter",
								TipoPregunta.OPCIONES, new String[] { "Júpiter", "Saturno", "Neptuno", "Tierra" }),
						new PreguntaOpciones(Nivel.BASICO, 4, "¿Qué tipo de energía es producida por el sol?", "Energía solar",
								TipoPregunta.OPCIONES,
								new String[] { "Energía solar", "Energía eólica", "Energía térmica", "Energía química" }),
						new PreguntaOpciones(Nivel.BASICO, 5, "¿Qué gas es esencial para la respiración humana?", "Oxígeno",
								TipoPregunta.OPCIONES, new String[] { "Oxígeno", "Nitrógeno", "Dióxido de carbono", "Helio" }),
						new PreguntaOpciones(Nivel.BASICO, 6,
								"¿Cómo se llama el proceso mediante el cual las plantas producen su alimento?", "Fotosíntesis",
								TipoPregunta.OPCIONES,
								new String[] { "Fotosíntesis", "Respiración", "Fermentación", "Digestión" }),
						new PreguntaOpciones(Nivel.BASICO, 7, "¿Cuál es el metal más abundante en la corteza terrestre?",
								"Aluminio", TipoPregunta.OPCIONES, new String[] { "Aluminio", "Hierro", "Cobre", "Oro" }),
						new PreguntaOpciones(Nivel.BASICO, 8, "¿Qué científico formuló la teoría de la relatividad?",
								"Albert Einstein", TipoPregunta.OPCIONES,
								new String[] { "Albert Einstein", "Isaac Newton", "Galileo Galilei", "Nikola Tesla" }),
						new PreguntaOpciones(Nivel.BASICO, 9, "¿Qué partícula subatómica tiene carga negativa?", "Electrón",
								TipoPregunta.OPCIONES, new String[] { "Electrón", "Protón", "Neutrón", "Quark" }),
						new PreguntaOpciones(Nivel.BASICO, 10, "¿Cuál es el proceso por el cual el agua se convierte en vapor?",
								"Evaporación", TipoPregunta.OPCIONES,
								new String[] { "Evaporación", "Condensación", "Sublimación", "Fusión" }));

				CursoPlantilla cursoCiencia = new CursoPlantilla("Ciencia", usuarioPrueba, "Principios básicos de la ciencia",
						"Introducción a conceptos científicos", Nivel.BASICO, bloque1Ciencia);
				
				Serializer serializadorJSON = SerializerFactory.INSTANCE.getSerializer(".json");
				Serializer serializadorYAML = SerializerFactory.INSTANCE.getSerializer(".yaml");
				
				if(serializadorJSON.serialize(cursoCiencia) && serializadorYAML.serialize(cursoCiencia)) {
					System.out.println("Curso bien serializado!");
				}
				else {
					System.out.println("Curso mal serializado...");
				}
		
	}
			
}
