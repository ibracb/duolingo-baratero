package umu.pds.duolingoBaratero.randoms;

import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.services.serializers.Serializer;
import umu.pds.duolingoBaratero.services.serializers.SerializerFactory;

public class DeserializarTest {
	
	public static void main(String[] args) {
		
		/*File testFile = new File("src/main/resources/cursos/Ciencia_Profesor B_BASICO.json");
		System.out.println("¿Existe el archivo? " + testFile.exists());
		System.out.println("Ruta absoluta: " + testFile.getAbsolutePath());*/
		
		Serializer servicioJson = SerializerFactory.INSTANCE.getSerializer("json");
		Serializer servicioYaml = SerializerFactory.INSTANCE.getSerializer("yaml");
		
		CursoPlantilla cursoJson = servicioJson.deserialize("src/main/resources/cursos/Ciencia_Profesor B_BASICO.json");
		CursoPlantilla cursoYaml = servicioYaml.deserialize("src/main/resources/cursos/Ciencia_Profesor B_BASICO.yaml");
		
		System.out.println(cursoJson.toString());
		
		System.out.println(cursoYaml.toString());
		
		
		
	}
	
}
