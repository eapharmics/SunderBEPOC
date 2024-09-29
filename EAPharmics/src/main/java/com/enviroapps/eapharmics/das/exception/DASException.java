package com.enviroapps.eapharmics.das.exception;

import com.enviroapps.eapharmics.common.exception.WrappedException;

/**
 * DASException class is the root exception class in Data Access System.
 *
 * @author EnviroApps
 * @version $Revision: 1.1 $
 */
public class DASException extends WrappedException {

	/**
	 * Creates new DASException without detail message.
	 */
	public DASException() {
	}

	/**
	 * Constructs a new DASException with the specified detail message.
	 *
	 * @param message
	 *            detailed message
	 */
	public DASException(String message) {
		super(message);
	}

	/**
	 * Constructs a new DASException with the specified nested throwable.
	 *
	 * @param e
	 *            nested throwable
	 */
	public DASException(Throwable e) {
		super(e);
	}

	/**
	 * Constructs a new DASException with the specified message and nested
	 * throwable.
	 *
	 * @param message
	 *            detailed message about this throwable
	 * @param e
	 *            throwable that initiated creation of this throwable
	 */
	public DASException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * Passthrough override, still exists due to interface compatibility.
	 */
	public String getMessage() {
		return super.getMessage();
	}

}
