package com.enviroapps.eapharmics.services;

import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;

/**
 * @author EnviroApps
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public abstract class AbstractServiceImpl {
	// Used to control transactions.
	protected HibernatePersistenceManager persistenceManager = null;

	// Used for logging.
	protected ILogger log = UtilityServiceFactory.getLogger();
	
	/**
	 * Constructor for AbstractServiceImpl.
	 */
	protected AbstractServiceImpl() {
		super();
		persistenceManager = HibernatePersistenceManager.getInstance();
	}

	/**
	 * @param methodName
	 * @param e
	 */
	protected void logException(String methodName, Exception e) {
		// log remote exception
		UtilityServiceFactory.getLogger().error(this, methodName, e);
	}

	/**
	 * Begin a transaction when a delegate method is invoked
	 */
	protected void beginTransaction() {
		persistenceManager.beginTransaction();
	}
	
	/**
	 * Commit a transaction when a delegate method is invoked
	 */
	protected void commitTransaction() {
		persistenceManager.commitTransaction();
	}
	
	/**
	 * Rollback a transaction when a delegate method has failed
	 */
	protected void rollbackTransaction() {
		persistenceManager.rollbackTransaction();
	}
	
}
