package com.enviroapps.eapharmics.das.persistence;

/**
 * Factory to return different types of persitence managers for various types of
 * DAS services
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public interface PersistenceManagerFactory {


	/**
	 * @return
	 */
	public PersistenceManager getPersistenceManager();

}