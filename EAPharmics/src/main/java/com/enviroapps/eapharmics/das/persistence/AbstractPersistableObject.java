package com.enviroapps.eapharmics.das.persistence;

import java.io.Serializable;

/**
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public abstract class AbstractPersistableObject extends AbstractBusinessObject {
	/**
	 * Constructor for AbstractPersistableObject.
	 */
	public AbstractPersistableObject() {
		super();
	}

	/**
	 * Supply primary key data for this object, used to dynamically instantiate
	 * an AbstractPersistableObject inside the <code>PersistenceFactory</code>.
	 *
	 * @param pks
	 */
	public abstract void loadPK(Object[] pks);

	/**
	 * Return the primary key data for this object, used to dynamically fetch an
	 * AbstractPersistableObject inside the <code>PersistenceFactory</code>.
	 *
	 * @return an object that implements Serializable and is recognized in the
	 *         ORM mapping as the primary key for the AbstractPersistableObject.
	 */
	public abstract Serializable getPK();
}
