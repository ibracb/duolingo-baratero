package umu.pds.duolingoBaratero.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import umu.pds.duolingoBaratero.models.CursoPlantilla;
import umu.pds.duolingoBaratero.models.Nivel;

public class DBCursoPlantillaDAO extends DBEntityDAO<CursoPlantilla> {

	private static final String ERROR_MESSAGE_CREATION = "Exception creating curso plantilla";
	private static final String ERROR_MESSAGE_DELETE = "Exception deletin curso plantilla";
	private static final String ERROR_MESSAGE_UPDATE = "Exception updating curso plantilla";
	private static final String ERROR_MESSAGE_GET = "Exception getting curso plantilla";
	private static final String ERROR_MESSAGE_GETALL = "Exception getting all plantilla";
	private static final String QUERY_GET_ALL = "SELECT model FROM CursoPlantilla model";

	private static DBCursoPlantillaDAO unicaInstancia;

	public static DBCursoPlantillaDAO getDBCursoPlantillaDAO() {
		if (unicaInstancia == null) {
			unicaInstancia = new DBCursoPlantillaDAO();
		}
		return unicaInstancia;
	}

	public CursoPlantilla obtenerCursoConBloquesYPreguntas(Long cursoId) {
		EntityManager em = EntityManagerHelper.getEntityManager();

		return em
				.createQuery("SELECT c FROM CursoPlantilla c " + "LEFT JOIN FETCH c.bloques b "
						+ "LEFT JOIN FETCH b.preguntas " + "WHERE c.id = :id", CursoPlantilla.class)
				.setParameter("id", cursoId).getSingleResult();
	}

	public Long existeCursoPlantilla(String nombre, String propietario, Nivel nivel) {
	    EntityManager em = EntityManagerHelper.getEntityManager();
	    try {
	        return  (Long) em.createQuery(
	                "SELECT id FROM CursoPlantilla WHERE nombre = :nombre AND propietario = :propietario AND nivel = :nivel")
	            .setParameter("nombre", nombre)
	            .setParameter("propietario", propietario)
	            .setParameter("nivel", nivel) 
	            .getSingleResult();
	    } catch (NoResultException e) {
	        return null; // no encontrado
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        em.close();
	    }
	}


	private DBCursoPlantillaDAO() {
		super();
	}

	@Override
	protected Class<CursoPlantilla> getEntityClass() {
		return CursoPlantilla.class;
	}

	@Override
	protected String getCreateExceptionMessage() {
		return ERROR_MESSAGE_CREATION;
	}

	@Override
	protected String getUpdateExceptionMessage() {
		return ERROR_MESSAGE_UPDATE;
	}

	@Override
	protected String getDeleteExceptionMessage() {
		return ERROR_MESSAGE_DELETE;
	}

	@Override
	protected String getGetExceptionMessage() {
		return ERROR_MESSAGE_GET;
	}

	@Override
	protected String getGetAllExceptionMessage() {
		return ERROR_MESSAGE_GETALL;
	}

	@Override
	protected String getAllQuery() {
		return QUERY_GET_ALL;
	}

}