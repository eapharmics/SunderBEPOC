package com.enviroapps.eapharmics.exception;

import com.enviroapps.eapharmics.common.exception.WrappedException;

public class EAPharmicsException extends WrappedException {

	  /**
	 * Creates new OPSException without detail message.
	 */
	public EAPharmicsException()
	{
	}
	/**
	 * Constructs a new OPSException with the specified detail message.
	 *
	 * @param message detailed message
	 */
	public EAPharmicsException(String message)
	{
		super(message);
	}
	/**
	 * Constructs a new OPSException with the specified nested throwable.
	 *
	 * @param e nested throwable
	 */
	public EAPharmicsException(Throwable e)
	{
		super(e);
	}
	/**
	 * Constructs a new OPSException with the specified message
	 * and nested throwable.
	 *
	 * @param message   detailed message about this throwable
	 * @param e throwable that initiated creation of this throwable
	 */
	public EAPharmicsException(String message, Throwable e)
	{
		super(message, e);
	}
}
