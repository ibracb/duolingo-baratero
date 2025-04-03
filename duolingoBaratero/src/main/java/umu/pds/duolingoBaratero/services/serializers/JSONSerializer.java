package umu.pds.duolingoBaratero.services.serializers;


import com.fasterxml.jackson.databind.json.JsonMapper;
import umu.pds.duolingoBaratero.models.CursoPlantilla;

public class JSONSerializer extends Serializer {
	
	private static final String JSON_EXTENSION = ".json";
	
	public JSONSerializer() {
		super(new JsonMapper(), JSON_EXTENSION);
		
		
	}

	@Override
	public boolean isBetter(CursoPlantilla cursoPlantilla) {
		return cursoPlantilla.mejorJSON();
	}
	
	

}
