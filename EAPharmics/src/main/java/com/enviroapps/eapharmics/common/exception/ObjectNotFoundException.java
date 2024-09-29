package com.enviroapps.eapharmics.common.exception;

/**
 * Standard Exception to throw when Factory classes that extend
 * <code>PersistenceManagerFactory</code> does not return an expected object.
 * 
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class ObjectNotFoundException extends WrappedException {

	/**
	 * Constructor for ObjectNotFoundException.
	 */
	public ObjectNotFoundException() {
		super();
	}

	/**
	 * Constructor for ObjectNotFoundException.
	 * 
	 * @param message
	 */
	public ObjectNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructor for ObjectNotFoundException.
	 * 
	 * @param e
	 */
	public ObjectNotFoundException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor for ObjectNotFoundException.
	 * 
	 * @param message
	 * @param e
	 */
	public ObjectNotFoundException(String message, Throwable e) {
		super(message, e);
	}
}
