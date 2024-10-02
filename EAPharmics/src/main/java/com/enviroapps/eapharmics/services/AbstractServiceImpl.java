package com.enviroapps.eapharmics.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;

/**
 * @author EnviroApps
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public abstract class AbstractServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractServiceImpl.class);
	
	// Used to control transactions.
	protected HibernatePersistenceManager persistenceManager = null;

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
		logger.error(methodName, e);
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
