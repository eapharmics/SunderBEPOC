package com.enviroapps.eapharmics.das.persistence;

/**
 * Persistence strategy interface for managing the ORM session. Implementations
 * must support non-JTS transactions if the
 * <code>userExternalTransactionController</code> is false.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public interface PersistenceManager {

	/**
	 * True if external transaction controller is used. By default it's set to
	 * TRUE. It can be controlled by setting the value of
	 * <dataaccess.use.external.txn.controller> key in the DataAccess property
	 * file
	 */
	boolean useExternalTransactionController = true;

	/**
	 * Before we start the init process, this method will be invoked
	 */
	void preInit();

	/**
	 * Initialize the persistence manager by loading the ORM XML
	 * configuration file and initializing the ORM Session. Sets the
	 * External Transaction Controller if it's set to true i.e. JTS is enabled
	 */
	void init();

	/**
	 * After we start the init process, this method will be invoked
	 */
	void postInit();

	/**
	 * Depending on the External Transaction Controller setting on the property
	 * file, begins a transaction. Used only when JTS is disabled.
	 */
	void beginTransaction();

	/**
	 * Depending on the External Transaction Controller setting on the property
	 * file, Commits a transaction. Used only when JTS is disabled.
	 */
	void commitTransaction();

	/**
	 * Depending on the External Transaction Controller setting on the property
	 * file, make a rollback transaction decision. Used only when JTS is
	 * disabled.
	 */
	void rollbackTransaction();

	/**
	 * login to the ORM session
	 */
	void login();
}