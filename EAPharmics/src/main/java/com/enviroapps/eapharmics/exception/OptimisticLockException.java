package com.enviroapps.eapharmics.exception;

/**
 * @author EnviroApps
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class OptimisticLockException extends EAPharmicsException {
	/**
	 * Constructor for OptimisticLockException.
	 */
	public OptimisticLockException() {
		super();
	}

	/**
	 * Constructor for OptimisticLockException.
	 *
	 * @param message
	 */
	public OptimisticLockException(String message) {
		super(message);
	}

	/**
	 * Constructor for OptimisticLockException.
	 *
	 * @param e
	 */
	public OptimisticLockException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor for OptimisticLockException.
	 *
	 * @param message
	 * @param e
	 */
	public OptimisticLockException(String message, Throwable e) {
		super(message, e);
	}
}
