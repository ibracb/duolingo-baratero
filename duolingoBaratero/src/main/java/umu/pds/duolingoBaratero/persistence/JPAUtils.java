package umu.pds.duolingoBaratero.persistence;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {
	
	private static final String PERSISTENCE_UNIT_NAME = "PDS";
	
	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
}
