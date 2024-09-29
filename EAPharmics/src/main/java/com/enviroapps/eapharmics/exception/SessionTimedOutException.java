package com.enviroapps.eapharmics.exception;

import com.enviroapps.eapharmics.common.exception.WrappedException;

public class SessionTimedOutException extends WrappedException {

	  /**
	 * Creates new OPSException without detail message.
	 */
	public SessionTimedOutException()
	{
	}
	/**
	 * Constructs a new OPSException with the specified detail message.
	 *
	 * @param message detailed message
	 */
	public SessionTimedOutException(String message)
	{
		super(message);
	}
	/**
	 * Constructs a new OPSException with the specified nested throwable.
	 *
	 * @param e nested throwable
	 */
	public SessionTimedOutException(Throwable e)
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
	public SessionTimedOutException(String message, Throwable e)
	{
		super(message, e);
	}
}
