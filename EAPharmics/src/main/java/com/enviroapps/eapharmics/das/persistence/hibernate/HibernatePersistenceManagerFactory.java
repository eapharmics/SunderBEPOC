package com.enviroapps.eapharmics.das.persistence.hibernate;

import com.enviroapps.eapharmics.das.persistence.PersistenceManager;
import com.enviroapps.eapharmics.das.persistence.PersistenceManagerFactory;

/**
 * Factory Class to return the Hibernate persistence manager for retrieving BOM
 * objects by extending <code>PersistenceManagerFactory</code> class.
 * 
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class HibernatePersistenceManagerFactory implements
		PersistenceManagerFactory {

	/* Instance of PersistenceManagerFactory */
	private static PersistenceManagerFactory instance = new HibernatePersistenceManagerFactory();

	/* Instance of HibernatePersistenceManager */
	private static HibernatePersistenceManager persistenceManager = null;

	/**
	 * Default Constructor for HibernatePersistenceManagerFactory that gets the
	 * instance of HibernatePersistenceManager.
	 * 
	 */
	public HibernatePersistenceManagerFactory() {
		super();
		persistenceManager = HibernatePersistenceManager.getInstance();
	}

	/**
	 * Gets the instance of PersistenceManagerFactory
	 * 
	 * @return instance of PersistenceManagerFactory
	 */
	public static PersistenceManagerFactory getInstance() {
		return instance;
	}

	/**
	 * Accessor method for Persistence Manager that returns the application
	 * persistence instance
	 * 
	 * @return instance of HibernatePersistenceManager
	 */
	public PersistenceManager getPersistenceManager() {

		// Making sure by cheking NULL that Only one
		// instance of Persistence is created for the
		// application.
		if (persistenceManager == null) {
			persistenceManager = HibernatePersistenceManager.getInstance();
		}
		return persistenceManager;

	}

}