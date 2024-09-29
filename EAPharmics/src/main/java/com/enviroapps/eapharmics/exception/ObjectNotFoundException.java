package com.enviroapps.eapharmics.exception;

/**
 * @author EnviroApps
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ObjectNotFoundException extends EAPharmicsException {
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
