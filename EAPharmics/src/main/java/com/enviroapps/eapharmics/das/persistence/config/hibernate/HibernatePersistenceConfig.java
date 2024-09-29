package com.enviroapps.eapharmics.das.persistence.config.hibernate;

import com.enviroapps.eapharmics.common.Utility;


/**
 * Configuration class responsible for loading all the Hibernate related
 * config files and providing acessor method needed by the
 * <code>HibernatePersistenceManager<code>
 *  
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class HibernatePersistenceConfig {

	/* Hibernate configuration XML file name */
	private String sessionXmlFile;

	/* Hibernate Session name  for JNDI lookup */
	private String sessionBrokerName;

	/* External Transaction Controller boolean flag */
	private boolean useExternalTransactionController;

	/* External Transaction Controller class name */
	private String externalTransactionControllerClassName;

	/**
	 * Constructor for HibernatePersistenceConfig.
	 */
	public HibernatePersistenceConfig() {
		super();
	}

	/**
	 * Returns the useExternalTransactionController.
	 * 
	 * @return boolean
	 */
	public boolean isUseExternalTransactionController() {

		// Value of <dataaccess.use.external.txn.controller>
		// attribute of data access property file
		return useExternalTransactionController;
	}

	/**
	 * Sets the useExternalTransactionController.
	 * 
	 * @param useExternalTransactionController
	 *            The useExternalTransactionController to set
	 */
	public void setUseExternalTransactionController(boolean localTxnEnabled) {
		this.useExternalTransactionController = localTxnEnabled;
	}

	/**
	 * Dump the contents of this instance to a String.
	 */
	public String toString() {
		return Utility.toString(this);
	}

	/**
	 * @return Returns the sessionXmlFile.
	 */
	public String getSessionXmlFile() {
		return sessionXmlFile;
	}

	/**
	 * @param sessionXmlFile
	 *            The sessionXmlFile to set.
	 */
	public void setSessionXmlFile(String sessionXmlFile) {
		this.sessionXmlFile = sessionXmlFile;
	}

	/**
	 * Accessor method for sessionBrokerName
	 * 
	 * @return Returns the sessionBrokerName.
	 */
	public String getSessionBrokerName() {
		return sessionBrokerName;
	}

	/**
	 * @param sessionBrokerName
	 *            The sessionBrokerName to set.
	 */
	public void setSessionBrokerName(String sessionBrokerName) {
		this.sessionBrokerName = sessionBrokerName;
	}

	/**
	 * Accessor method for externalTransactionControllerClassName
	 * 
	 * @return Returns the externalTransactionControllerClassName.
	 */
	public String getExternalTransactionControllerClassName() {
		return externalTransactionControllerClassName;
	}

	/**
	 * @param externalTransactionControllerClassName
	 *            The externalTransactionControllerClassName to set.
	 */
	public void setExternalTransactionControllerClassName(
			String externalTransactionControllerClassName) {
		this.externalTransactionControllerClassName = externalTransactionControllerClassName;
	}

}
